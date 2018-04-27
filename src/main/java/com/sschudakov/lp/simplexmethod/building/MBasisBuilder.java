package com.sschudakov.lp.simplexmethod.building;

import com.sschudakov.lp.simplexmethod.table.LPRestriction;
import com.sschudakov.lp.simplexmethod.table.LPTable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Semen Chudakov on 08.10.2017.
 */
public class MBasisBuilder {

    private static Double M_VALUE = Double.MAX_VALUE;

    public void buildMBasis(LPTable table) {

        M_VALUE = 1_000.0/*MFinder.findM(table)*/;

        int numOfMVariables = tellNumOfMVariables(table);
        int firstMVariable = table.getNumOfVariables();

        rebuildMainTable(table, numOfMVariables);
        rebuildFunction(table.getFunction(), numOfMVariables);
        rewriteDataInTable(table, firstMVariable, numOfMVariables);
    }

    private void rewriteDataInTable(LPTable table, int firstMVariable, int numOfMVariables) {
        Integer initialNumberOfMVariable = table.getNumOfMVariables() == null ? 0 : table.getNumOfMVariables();

        table.setNumOfVariables(table.getNumOfVariables() + numOfMVariables);
        table.setNumOfMVariables(initialNumberOfMVariable + numOfMVariables);
    }

    private void rebuildMainTable(LPTable table, int numOfMVariables) {
        int numOfXVariables = table.getNumOfVariables();
        enlargeMainTable(table.getMainTable(), numOfMVariables);
        initializeMVariablesInTable(table.getMainTable(), numOfXVariables, numOfMVariables);
        fillBasicVariables(table.getMainTable(), numOfXVariables, numOfMVariables);
    }

    private void enlargeMainTable(List<LPRestriction> mainTable, int numOfMVariables) {
        for (LPRestriction lpRestriction : mainTable) {
            for (int i = 0; i < numOfMVariables; i++) {
                lpRestriction.getCondition().add(0.0);
            }
        }
    }

    private void initializeMVariablesInTable(List<LPRestriction> mainTable, int numOfXVariables, int numOfMVariables) {

        int numOfEquations = mainTable.size();
        int numOfVariables = numOfXVariables + numOfMVariables;

        List<Boolean> equationHasBasicVariable = new ArrayList<>();

        for (int i = 0; i < numOfEquations; i++) {
            equationHasBasicVariable.add(false);
        }
        for (int i = 0; i < mainTable.size(); i++) {
            if (mainTable.get(i).getBasicVariable() != -1) {
                equationHasBasicVariable.set(
                        tellPositionOfOne(mainTable, mainTable.get(i).getBasicVariable()),
                        true);
            }
        }
        for (int i = numOfXVariables; i < numOfVariables; i++) {
            for (int j = 0; j < numOfEquations; j++) {
                if (!equationHasBasicVariable.get(j)) {
                    mainTable.get(j).getCondition().set(i, 1.0);
                    equationHasBasicVariable.set(j, true);
                    break;
                }
            }
        }
    }

    private void fillBasicVariables(List<LPRestriction> mainTable, int numOfXVariables, int numOfMVariables) {
        for (int i = numOfXVariables; i < numOfXVariables + numOfMVariables; i++) {
            mainTable.get(tellPositionOfOne(mainTable, i)).setBasicVariable(i);
        }
    }

    private int tellPositionOfOne(List<LPRestriction> mainTable, int column) {
        for (int i = 0; i < mainTable.size(); i++) {
            if (mainTable.get(i).getCondition().get(column) == 1.0) {
                return i;
            }
        }
        throw new RuntimeException("there is no 1 in " + column + " column");
    }

    private void rebuildFunction(List<Double> function, int numOfYVariables) {
        for (int i = 0; i < numOfYVariables; i++) {
            function.add(M_VALUE);
        }
    }

    private int tellNumOfMVariables(LPTable table) {
        int result = 0;
        for (LPRestriction lpRestriction : table.getMainTable()) {
            if (lpRestriction.getBasicVariable() == -1) {
                result++;
            }
        }
        return result;
    }
}
