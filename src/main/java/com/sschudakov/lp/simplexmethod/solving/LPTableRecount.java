package com.sschudakov.lp.simplexmethod.solving;

import com.sschudakov.lp.simplexmethod.exception.UnlimitedFunctionException;
import com.sschudakov.lp.simplexmethod.table.LPRestriction;
import com.sschudakov.lp.simplexmethod.table.LPTable;

import java.util.ArrayList;
import java.util.List;

public class LPTableRecount {
    public void recountSimplexTable(LPTable lpTable) {

        recountDeltas(lpTable);
        int resolvingLine = findResolvingLine(lpTable.getMainTable());

        recountSimplexRatios(lpTable, resolvingLine);
        int resolvingColumn = findResolvingColumn(lpTable, resolvingLine);

        recountMainTable(lpTable, resolvingLine, resolvingColumn);
    }

    private void recountMainTable(LPTable lpTable, int resolvingLine, int resolvingColumn) {

        List<LPRestriction> mainTable = lpTable.getMainTable();
        List<LPRestriction> recountedTable = createTableWithTheSameSize(mainTable);

        Double resolvingElementValue = mainTable.get(resolvingLine).getCondition().get(resolvingColumn);


        recountResolvingLine(mainTable, recountedTable, resolvingLine, resolvingElementValue);
        setResolvingElementToOne(recountedTable, resolvingLine, resolvingColumn);
        recountRestrictions(mainTable, recountedTable, resolvingLine, resolvingColumn, resolvingElementValue);
        changeRestrictionInResolvingLine(mainTable, recountedTable, resolvingLine, resolvingElementValue);
        recountOtherElementsInTable(mainTable, recountedTable, resolvingLine, resolvingColumn, resolvingElementValue);

        lpTable.setMainTable(recountedTable);
        lpTable.getMainTable().get(resolvingLine).setBasicVariable(resolvingColumn);
    }

    private List<LPRestriction> createTableWithTheSameSize(List<LPRestriction> table) {
        List<LPRestriction> result = new ArrayList<>();
        for (LPRestriction lpRestriction : table) {
            List<Double> condition = new ArrayList<>();
            for (int i = 0; i < lpRestriction.getCondition().size(); i++) {
                condition.add(0.0);
            }
            result.add(new LPRestriction(
                    condition,
                    lpRestriction.getSign(),
                    lpRestriction.getRightPartValue(),
                    lpRestriction.getBasicVariable()
            ));
        }
        return result;
    }

    private void recountResolvingLine(List<LPRestriction> mainTable, List<LPRestriction> recountedTable,
                                      int resolvingLine, Double resolvingElementValue) {
        List<Double> resolvingLineCondition = mainTable.get(resolvingLine).getCondition();
        List<Double> recountedCondition = recountedTable.get(resolvingLine).getCondition();

        double recountedValue;
        for (int i = 0; i < resolvingLineCondition.size(); i++) {
            recountedValue = resolvingLineCondition.get(i) / resolvingElementValue;

            //there is -0 value of double variable we should ger reed of
            if (recountedValue != 0) {
                recountedCondition.set(i, recountedValue);
            } else {
                recountedCondition.set(i, Math.abs(recountedValue));
            }
        }
    }

    private void setResolvingElementToOne(List<LPRestriction> recountedTable,
                                          int resolvingLine, int resolvingColumn) {
        recountedTable.get(resolvingLine).getCondition().set(resolvingColumn, 1.0);
    }

    private void recountRestrictions(List<LPRestriction> mainTable, List<LPRestriction> recountedTable,
                                     int resolvingLine, int resolvingColumn, double resolvingElementValue) {
        double recountedRestrictionValue;
        for (int i = 0; i < mainTable.size(); i++) {
            if (i != resolvingLine) { //the right part value in resolving line is not recounted
                recountedRestrictionValue = (
                        mainTable.get(i).getRightPartValue()
                                * resolvingElementValue

                                - mainTable.get(resolvingLine).getRightPartValue() *
                                mainTable.get(i).getCondition().get(resolvingColumn)
                )
                        / resolvingElementValue;
                recountedTable.get(i).setRightPartValue(recountedRestrictionValue);
            }
        }
    }

    private void changeRestrictionInResolvingLine(List<LPRestriction> mainTable, List<LPRestriction> recountedTable,
                                                  int resolvingLine, Double resolvingElementValue) {
        recountedTable.get(resolvingLine).setRightPartValue(
                mainTable.get(resolvingLine).getRightPartValue() / resolvingElementValue
        );
    }

    private void recountOtherElementsInTable(List<LPRestriction> mainTable, List<LPRestriction> recountedTable,
                                             int resolvingLine, int resolvingColumn, double resolvingElementValue) {
        for (int i = 0; i < mainTable.size(); i++) {
            if (i != resolvingLine) {

                List<Double> condition = mainTable.get(i).getCondition();
                List<Double> resolvingCondition = mainTable.get(resolvingLine).getCondition();

                List<Double> recountedCondition = recountedTable.get(i).getCondition();

                Double recountedValue;
                for (int j = 0; j < condition.size(); j++) {
                    if (j != resolvingColumn) {

                        recountedValue = (resolvingElementValue * condition.get(j)
                                - resolvingCondition.get(j) * condition.get(resolvingColumn))
                                / resolvingElementValue;

                        recountedCondition.set(
                                j,
                                recountedValue
                        );
                    }
                }
            }
        }
    }


    private int findResolvingLine(List<LPRestriction> mainTable) {
        int position = tellPositionOfFirstNegativeRestriction(mainTable);
        Double negativeRestrictionValue = mainTable.get(position).getRightPartValue();

        for (int i = 0; i < mainTable.size(); i++) {
            if (mainTable.get(i).getRightPartValue() < 0) {
                if (mainTable.get(i).getRightPartValue() < negativeRestrictionValue) {
                    position = i;
                    negativeRestrictionValue = mainTable.get(i).getRightPartValue();
                }
            }
        }
        return position;
    }

    private int findResolvingColumn(LPTable lpTable, int resolvingLine) {

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

    private void recountDeltas(LPTable lpTable) {
        List<Double> deltas = lpTable.getDeltasVector();
        for (int i = 0; i < deltas.size(); i++) {
            deltas.set(i, recountDelta(lpTable, i));
        }
    }

    private double recountDelta(LPTable lpTable, int column) {

        Double result = lpTable.getFunction().get(column);
        int numOfEquations = lpTable.getNumOfEquations();

        for (int i = 0; i < numOfEquations; i++) {
            result -= lpTable.getFunction().get(lpTable.getMainTable().get(i).getBasicVariable())
                    * lpTable.getMainTable().get(i).getCondition().get(column);
        }
        return result;
    }

    private void recountSimplexRatios(LPTable lpTable, int resolvingLine) {
        if (!hasNegatives(lpTable.getMainTable(), resolvingLine)) {
            throw new UnlimitedFunctionException("table is unlimited according to the line " + resolvingLine);
        }

        List<LPRestriction> mainTable = lpTable.getMainTable();
        List<Double> deltas = lpTable.getDeltasVector();
        List<Double> simplexRatios = lpTable.getSimplexRatios();
        List<Double> resolvingCondition = mainTable.get(resolvingLine).getCondition();

        for (int i = 0; i < resolvingCondition.size(); i++) {
            if (resolvingCondition.get(i) < 0) {
                simplexRatios.set(i, deltas.get(i) / Math.abs(resolvingCondition.get(i)));
            } else {
                simplexRatios.set(i, Double.MAX_VALUE);
            }
        }
    }

    private boolean hasNegatives(List<LPRestriction> mainTable, int line) {
        for (Double aDouble : mainTable.get(line).getCondition()) {
            if (aDouble < 0) {
                return true;
            }
        }
        return false;
    }

    private int tellPositionOfFirstNegativeRestriction(List<LPRestriction> mainTable) {
        for (int i = 0; i < mainTable.size(); i++) {
            if (mainTable.get(i).getRightPartValue() < 0) {
                return i;
            }
        }
        throw new IllegalArgumentException("table has no negative restriction");
    }
}
