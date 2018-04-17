package com.sschudakov.branchandbound.solver;

import com.sschudakov.branchandbound.building.ILPTableCopy;
import com.sschudakov.branchandbound.building.TaskSplitter;
import com.sschudakov.branchandbound.table.ILPTable;
import com.sschudakov.branchandbound.util.Utils;
import com.sschudakov.simplexmethod.building.LPTableBuilder;
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

    private ILPTableCopy ilpTableCopy;
    private LPTableBuilder lpTableBuilder;

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
        this.ilpTableCopy = new ILPTableCopy();
        this.lpTableBuilder = new LPTableBuilder();
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

        int iteration = 1;
        while (taskSplit) {

            taskSplit = false;
            List<LPSolution> solutions = new ArrayList<>();
            List<ILPTable> splitTables = new ArrayList<>();


            System.out.println("\n\nITERATION: " + iteration++ + "\n\n");

            for (ILPTable table : tables) {

                System.out.println("\n\nTABLE\n\n");
                table.outputTable();

                ILPTable copiedTable = this.ilpTableCopy.copy(table);
                this.lpTableBuilder.buildSimplexTable(table);
                LPSolution solution = this.lpSolver.solveLP(table);

                System.out.println();
                System.out.println();
                System.out.println("solution vector: " + solution.getSolutionVector());
                System.out.println("function value: " + solution.getFunctionValue());
                System.out.println("exception: " + solution.getSolvingException());

                if (!solution.endedWithException()) {
                    int nonIntegerBasicVariablePosition = findFirstNonIntegerBasicVariable(table);

                    System.out.println("\n\nNON-INTEGER VARIABLE INDEX: " + nonIntegerBasicVariablePosition + "\n\n");

                    if (nonIntegerBasicVariablePosition != -1) {
                        splitTables.addAll(this.taskSplitter.splitTask(
                                copiedTable,
                                nonIntegerBasicVariablePosition,
                                table.getBasicVariables().get(nonIntegerBasicVariablePosition),
                                table.getRestrictionsVector().get(nonIntegerBasicVariablePosition)
                        ));
                        taskSplit = true;
                    }else {
                        solutions.add(solution);
                    }
                }
            }
            System.out.println("\n\nSPLIT TABLES\n\n");
            for (ILPTable splitTable : splitTables) {
                splitTable.outputTable();
            }

            this.tasksSolutions.add(solutions);
            tables = splitTables;

        }

        System.out.println("\nEND\n");
        System.out.println("\nSOLUTIONS\n");

        System.out.println(tasksSolutions);

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
    private int findFirstNonIntegerBasicVariable(ILPTable ilpTable) {
        List<Double> restrictions = ilpTable.getRestrictionsVector();
        System.out.println("restrictions vector: " + restrictions);
        int result = -1;
        for (int i = 0; i < restrictions.size(); i++) {
            if (Utils.isNonInteger(restrictions.get(i))
                    && ilpTable.getIntegerVariables().contains(ilpTable.getBasicVariables().get(i))) {
                result = i;
                break;
            }
        }
        return result;
    }
}
