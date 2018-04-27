package com.sschudakov.lp.branchandbound.solver;

import com.sschudakov.lp.branchandbound.building.ILPTableCopy;
import com.sschudakov.lp.branchandbound.building.TaskSplitter;
import com.sschudakov.lp.branchandbound.table.ILPTable;
import com.sschudakov.lp.branchandbound.util.Utils;
import com.sschudakov.lp.simplexmethod.building.LPTableBuilder;
import com.sschudakov.lp.simplexmethod.enumerable.TaskType;
import com.sschudakov.lp.simplexmethod.solving.LPSolution;
import com.sschudakov.lp.simplexmethod.solving.LPSolver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Implements solving process of the integer linear programming task.
 */
public class BABILPSolver {

    /**
     * logger.
     */
    private static Logger logger = LogManager.getLogger(BABILPSolver.class);

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
    private List<List<LPSolution>> integerSolutions;
    /**
     * Cope tables while solving.
     */
    private ILPTableCopy ilpTableCopy;
    /**
     * Builds generated tables.
     */
    private LPTableBuilder lpTableBuilder;


    /**
     * Constructor.
     */
    public BABILPSolver() {
        this.ilpTableCopy = new ILPTableCopy();
        this.lpTableBuilder = new LPTableBuilder();
        this.taskSplitter = new TaskSplitter();
        this.lpSolver = new LPSolver();
        this.tasksSolutions = new ArrayList<>();
        this.integerSolutions = new ArrayList<>();
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

        int iteration = 1;
        boolean integerSolutionFound = false;

        while (tables.size() != 0 && !integerSolutionFound) {

            logger.info("ITERATION " + iteration++);

            List<LPSolution> solutions = new ArrayList<>();
            List<LPSolution> intSolutions = new ArrayList<>();
            List<ILPTable> splitTables = new ArrayList<>();

            for (ILPTable table : tables) {
                logger.info("TABLE\n");
                table.outputTable();

                ILPTable copiedTable = this.ilpTableCopy.copy(table);
                this.lpTableBuilder.buildSimplexTable(table);
                LPSolution solution = this.lpSolver.solveLP(table);
                if (!solution.endedWithException()) {
                    fixSolution(solution);
                }
                solutions.add(solution);

                logger.info("SOLUTION\n");
                logger.info("solution vector: " + solution.getSolutionVector());
                logger.info("function value: " + solution.getFunctionValue());
                logger.info("exception: " + solution.getSolvingException());

                if (!solution.endedWithException()) {
                    int nonIntegerBasicVariablePosition = findFirstNonIntegerBasicVariable(table);

                    logger.info("NON-INTEGER BASIC VARIABLE INDEX: " + nonIntegerBasicVariablePosition);

                    if (nonIntegerBasicVariablePosition != -1) {
                        splitTables.addAll(this.taskSplitter.splitTask(
                                copiedTable,
                                table.getMainTable().get(nonIntegerBasicVariablePosition).getBasicVariable(),
                                table.getMainTable().get(nonIntegerBasicVariablePosition).getRightPartValue()
                        ));
                    } else {
                        intSolutions.add(solution);
                        integerSolutionFound = true;
                    }
                }
            }

            logger.info("SPLIT TABLES\n");
            for (ILPTable splitTable : splitTables) {
                splitTable.outputTable();
            }

            this.tasksSolutions.add(solutions);
            tables = splitTables;
            this.integerSolutions.add(intSolutions);
        }

        logger.info("SOLUTIONS: ");
        for (int i = 0; i < this.integerSolutions.size(); i++) {
            System.out.println("ITERATION: " + (i + 1));
            System.out.println(integerSolutions.get(i));
        }

        return findFinalSolution(ilpTable.getTaskType(), this.integerSolutions.get(this.integerSolutions.size() - 1));
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
            return Collections.min(solutions,
                    Comparator.nullsLast((o1, o2) ->
                            Comparator.nullsLast(Double::compareTo).compare(o1.getFunctionValue(), o2.getFunctionValue()))
            );
        }

        if (taskType.equals(TaskType.MAX)) {
            return Collections.max(solutions,
                    Comparator.nullsLast((o1, o2) ->
                            Comparator.nullsFirst(Double::compareTo).compare(o1.getFunctionValue(), o2.getFunctionValue()))
            );
        }

        throw new IllegalArgumentException("Unknown task type: " + taskType);
    }

    /**
     * This method finds the index of the first variable in the basis
     * that has non-integer value.
     *
     * @param ilpTable variable index should be found for
     * @return index of the first basic variable the has non-integer value
     */
    private int findFirstNonIntegerBasicVariable(ILPTable ilpTable) {
        int result = -1;
        for (int i = 0; i < ilpTable.getMainTable().size(); i++) {
            if (Utils.isNonInteger(ilpTable.getMainTable().get(i).getRightPartValue())
                    && ilpTable.getIntegerVariables().contains(ilpTable.getMainTable().get(i).getBasicVariable())) {
                result = i;
                break;
            }
        }
        return result;
    }

    private void fixSolution(LPSolution solution) {
        for (int i = 0; i < solution.getSolutionVector().size(); i++) {
            if (!Utils.isNonInteger(solution.getSolutionVector().get(i))) {
                solution.getSolutionVector().set(i, (double) Math.round(solution.getSolutionVector().get(i)));
            }
        }
        if (!Utils.isNonInteger(solution.getFunctionValue())) {
            solution.setFunctionValue((double) Math.round(solution.getFunctionValue()));
        }
    }
}
