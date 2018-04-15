package com.sschudakov.branchandbound.solver;

import com.sschudakov.branchandbound.building.TaskSplitter;
import com.sschudakov.branchandbound.table.ILPTable;
import com.sschudakov.branchandbound.util.Utils;
import com.sschudakov.simplexmethod.enumerable.TaskType;
import com.sschudakov.simplexmethod.solver.LPSolution;
import com.sschudakov.simplexmethod.solver.LPSolver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Implements solving process of the integer linear programming task.
 */
public class BABILPSolver {
    /**
     * Is used to add new conditions on the non-integer variables
     * ad split the {@code ILPTask}.
     */
    private TaskSplitter taskSplitter;
    /**
     * Is used to solve generated tasks.
     */
    private LPSolver lpSolver;
    /**
     * List in which solutions of all tasks that are
     * generated while solving are kept. The index of
     * the solutions list steds for the iterations of
     * the solving process is was generated on.
     */
    private List<List<LPSolution>> tasksSolutions;

    /**
     * Constructor.
     */
    public BABILPSolver() {
        this.taskSplitter = new TaskSplitter();
        this.lpSolver = new LPSolver();
        this.tasksSolutions = new ArrayList<>();
    }


    /**
     * Executes the process of the branch and bounds
     * solving process.
     *
     * @param ilpTable table to be solved
     * @return {@code LPSolution} with min \ max function value
     * among other solutions on the las iteration
     * @see ILPTable
     */
    public LPSolution solve(ILPTable ilpTable) {
        List<ILPTable> tables = new ArrayList<>();
        tables.add(ilpTable);

        boolean taskSplit = true;

        while (taskSplit) {

            taskSplit = false;
            List<LPSolution> solutions = new ArrayList<>();
            List<ILPTable> splitTables = new ArrayList<>();

            for (ILPTable table : tables) {

                LPSolution solution = this.lpSolver.solveLP(table);
                solutions.add(solution);

                if (!solution.endedWithException()) {
                    int nonIntegerBasicVariableIndex = findFirstNonIntegerBasicVariableIndex(table);

                    if (nonIntegerBasicVariableIndex != -1) {
                        splitTables.addAll(this.taskSplitter.splitTask(table, nonIntegerBasicVariableIndex));
                        taskSplit = true;
                    }
                }
            }

            this.tasksSolutions.add(solutions);

            tables = splitTables;

        }
        return findFinalSolution(ilpTable.getTaskType(), this.tasksSolutions.get(this.tasksSolutions.size() - 1));
    }

    /**
     * Finds solutions with min \ max function value
     * among the elements of the {@code solutions} list
     *
     * @param taskType  task type of the table
     * @param solutions solutions the final one should be found among
     * @return solutions with min or max value of the function
     * @see LPSolution
     * @see TaskType
     */
    private LPSolution findFinalSolution(TaskType taskType, List<LPSolution> solutions) {
        if (taskType.equals(TaskType.MIN)) {
            return Collections.min(solutions, Comparator.nullsLast(
                    Comparator.comparingDouble(LPSolution::getFunctionValue)));
        }

        if (taskType.equals(TaskType.MAX)) {
            return Collections.max(solutions, Comparator.nullsFirst(
                    Comparator.comparingDouble(LPSolution::getFunctionValue)));
        }
        throw new IllegalArgumentException("Unknown task type: " + taskType);
    }

    /**
     * This method finds the index of the first variable in the basis
     *
     * @param ilpTable variable index should be found for
     * @return index of the first basic variable the has non-integer value
     */
    private int findFirstNonIntegerBasicVariableIndex(ILPTable ilpTable) {

        List<Double> restrictions = ilpTable.getRestrictionsVector();
        int result = -1;
        for (int i = 0; i < restrictions.size(); i++) {
            if (Utils.isInteger(restrictions.get(i)) && i < ilpTable.getNumOfInitialVariables()) {
                result = i;
                break;
            }
        }
        return result;
    }
}
