package com.sschudakov.lp.simplexmethod.solver;

import com.sschudakov.lp.simplexmethod.exception.TableAlreadyOptimalException;
import com.sschudakov.lp.simplexmethod.exception.UnlimitedFunctionException;
import com.sschudakov.lp.simplexmethod.table.LPTable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Created by Semen Chudakov on 10.09.2017.
 */
public class LPSolver {

    private static Logger logger = LogManager.getLogger(LPSolver.class);

    private LPSolutionFormer solutionFormer;

    public LPSolver() {
        this.solutionFormer = new LPSolutionFormer();
    }

    public LPSolution solveLP(LPTable lpTable) {
        int iteration = 1;
        LPSolution result;
        try {
            while (!lpTable.isDualOptimal()) {
                recountSimplexTable(lpTable);
                logger.info("recounted for the " + iteration++ + " time\n");
                lpTable.outputTable();
            }
            result = this.solutionFormer.formSolution(lpTable);
        } catch (Exception e) {
            result = this.solutionFormer.formSolution(lpTable, e);
        }
        return result;
    }

    private void recountSimplexTable(LPTable lpTable) {

        recountDeltas(lpTable);

        int resolvingLine = findResolvingLine(lpTable);
        int resolvingColumn = findResolvingColumn(lpTable, resolvingLine);

        recountMainTable(lpTable, resolvingLine, resolvingColumn);
    }

    private void recountMainTable(LPTable lpTable, int resolvingLine, int resolvingColumn) {

        double[][] mainTable = lpTable.getMainTable();
        double[][] recountedTable = new double[mainTable.length][mainTable[0].length];

        List<Double> restrictions = lpTable.getRestrictionsVector();
        double resolvingElementValue = mainTable[resolvingLine][resolvingColumn];


        recountResolvingLine(mainTable, recountedTable, resolvingLine, resolvingColumn);
        setResolvingElementToOne(recountedTable, resolvingLine, resolvingColumn);
        recountRestrictions(restrictions, mainTable, resolvingLine, resolvingColumn, resolvingElementValue);
        changeRestrictionInResolvingLine(restrictions, resolvingLine, resolvingElementValue);
        recountOtherElementsInTable(mainTable, recountedTable, resolvingLine, resolvingColumn, resolvingElementValue);

        lpTable.setMainTable(recountedTable);
        lpTable.getBasicVariables().set(resolvingLine, resolvingColumn);
    }

    private void recountResolvingLine(double[][] mainTable, double[][] recountedTable,
                                      int resolvingLine, int resolvingColumn) {
        double resolvingElementValue = mainTable[resolvingLine][resolvingColumn];
        // recount resolving line in table
        double recountedValue;
        for (int i = 0; i < mainTable[resolvingLine].length; i++) {
            recountedValue = mainTable[resolvingLine][i] / resolvingElementValue;
            if (recountedValue != 0) {
                recountedTable[resolvingLine][i] = recountedValue;
            } else {
                recountedTable[resolvingLine][i] = Math.abs(recountedValue);
            }
        }
    }

    private void setResolvingElementToOne(double[][] recountedTable, int resolvingLine, int resolvingColumn) {
        recountedTable[resolvingLine][resolvingColumn] = 1;
    }

    private void recountRestrictions(List<Double> restrictions, double[][] mainTable,
                                     int resolvingLine, int resolvingColumn, double resolvingElementValue) {
        //recount restrictions
        double recountedRestrictionValue;
        for (int i = 0; i < restrictions.size(); i++) {
            if (i != resolvingLine) {
                recountedRestrictionValue = (restrictions.get(i) * mainTable[resolvingLine][resolvingColumn]
                        - restrictions.get(resolvingLine) * mainTable[i][resolvingColumn]) / resolvingElementValue;
                restrictions.set(i, recountedRestrictionValue);
            }
        }
    }

    private void changeRestrictionInResolvingLine(List<Double> restrictions, int resolvingLine,
                                                  double resolvingElementValue) {
        restrictions.set(resolvingLine, restrictions.get(resolvingLine) / resolvingElementValue);
    }

    private void recountOtherElementsInTable(double[][] mainTable, double[][] recountedTable,
                                             int resolvingLine, int resolvingColumn, double resolvingElementValue) {
        for (int i = 0; i < mainTable.length; i++) {
            if (i != resolvingLine) {
                for (int j = 0; j < mainTable[i].length; j++) {
                    if (j != resolvingColumn) {
                        recountedTable[i][j] = (mainTable[resolvingLine][resolvingColumn] * mainTable[i][j]
                                - mainTable[resolvingLine][j] * mainTable[i][resolvingColumn]) / resolvingElementValue;
                    }
                }
            }
        }
    }

    private void recountDeltas(LPTable lpTable) {
        List<Double> deltas = lpTable.getDeltasVector();

        /*if (deltas.size() == 0) {
            for (int i = 0; i < lpTable.getNumOfVariables(); i++) {
                deltas.add(recountDelta(lpTable, i));
            }
        } else {*/
        for (int i = 0; i < deltas.size(); i++) {
            deltas.set(i, recountDelta(lpTable, i));
        }
        /*}*/
    }

    private double recountDelta(LPTable lpTable, int column) {

        double result = lpTable.getFunction().get(column);
        int numOfEquations = lpTable.getNumOfEquations();
        double[][] mainTable = lpTable.getMainTable();

        for (int i = 0; i < numOfEquations; i++) {
            result -= lpTable.getFunction().get(
                    lpTable.getBasicVariables().get(i)) * mainTable[i][column];
        }
        return result;
    }

    private int findResolvingLine(LPTable lpTable) {

        if (lpTable.isDualOptimal()) {
            throw new TableAlreadyOptimalException();
        }
        List<Double> restrictions = lpTable.getRestrictionsVector();

        int position = tellPositionOfFirstNegative(restrictions);
        double value = restrictions.get(position);

        for (int i = 0; i < restrictions.size(); i++) {
            if (restrictions.get(i) < 0) {
                if (restrictions.get(i) < value) {
                    position = i;
                    value = restrictions.get(i);
                }
            }
        }
        return position;
    }

    private int findResolvingColumn(LPTable lpTable, int resolvingLine) {

        recountSimplexRatios(lpTable, resolvingLine);

        List<Double> simplexRatios = lpTable.getSimplexRatios();
        int position = 0;
        double currentRatio = simplexRatios.get(0);

        for (int i = 0; i < simplexRatios.size(); i++) {
            if (simplexRatios.get(i) < currentRatio) {
                position = i;
                currentRatio = simplexRatios.get(i);
            }
        }

        return position;
    }

    private void recountSimplexRatios(LPTable lpTable, int resolvingLine) {
        if (!hasNegatives(lpTable.getMainTable(), resolvingLine)) {
            throw new UnlimitedFunctionException("table is unlimited according to the line " + resolvingLine);
        }

        double[][] mainTable = lpTable.getMainTable();
        List<Double> deltas = lpTable.getDeltasVector();
        List<Double> simplexRatios = lpTable.getSimplexRatios();

        for (int i = 0; i < mainTable[resolvingLine].length; i++) {
            if (mainTable[resolvingLine][i] < 0) {
                simplexRatios.set(i, deltas.get(i) / Math.abs(mainTable[resolvingLine][i]));
            } else {
                simplexRatios.set(i, Double.MAX_VALUE);
            }
        }
    }

    private boolean hasNegatives(double[][] mainTable, int line) {
        for (double v : mainTable[line]) {
            if (v < 0) {
                return true;
            }
        }
        return false;
    }

    private int tellPositionOfFirstNegative(List<Double> vector) {
        for (int i = 0; i < vector.size(); i++) {
            if (vector.get(i) < 0) {
                return i;
            }
        }
        throw new IllegalArgumentException("vector " + vector.toString() + " has no negatives");
    }
}