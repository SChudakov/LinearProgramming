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

        rebuildMainTable(table.getMainTable(), table.getBasicVariables(), numOfMVariables);

        rebuildFunction(table.getFunction(), numOfMVariables);
        rewriteDataInTable(table, firstMVariable, numOfMVariables);
    }

    private void rewriteDataInTable(LPTable table, int firstMVariable, int numOfMVariables) {
        List<Integer> basicVariables = table.getBasicVariables();

        List<LPRestriction> mainTable = table.getMainTable();

        for (int i = firstMVariable; i < firstMVariable + numOfMVariables; i++) {
            basicVariables.set(tellPositionOfOne(mainTable, i), i);
        }

        Integer initialNumberOfMVariable = table.getNumOfMVariables() == null ? 0 : table.getNumOfMVariables();

        table.setNumOfVariables(table.getNumOfVariables() + numOfMVariables);
        table.setNumOfMVariables(initialNumberOfMVariable + numOfMVariables);
    }

    private void rebuildMainTable(List<LPRestriction> mainTable, List<Integer> basicVariables, int numOfMVariables) {
        int numOfXVariables = mainTable.get(0).getCondition().size();
        enlargeMainTable(mainTable, numOfMVariables);
        initializeMVariablesInTable(mainTable, basicVariables, numOfXVariables);
    }

    private void enlargeMainTable(List<LPRestriction> maintable, int numOfMVariables) {
        for (LPRestriction lpRestriction : maintable) {
            for (int i = 0; i < numOfMVariables; i++) {
                lpRestriction.getCondition().add(0.0);
            }
        }
    }

    private void initializeMVariablesInTable(List<LPRestriction> mainTable, List<Integer> basicVariables, int numOfXVariables) {

        int numOfEquations = mainTable.size();
        int totalAmountOfVariables = mainTable.get(0).getCondition().size();

        List<Boolean> equationHasBasicVariable = new ArrayList<>();

        for (int i = 0; i < numOfEquations; i++) {
            equationHasBasicVariable.add(false);
        }
        for (int i = 0; i < basicVariables.size(); i++) {
            if (basicVariables.get(i) != -1) {
                equationHasBasicVariable.set(tellPositionOfOne(mainTable, basicVariables.get(i)), true);
            }
        }

        for (int i = numOfXVariables; i < totalAmountOfVariables; i++) {
            for (int j = 0; j < numOfEquations; j++) {
                if (!equationHasBasicVariable.get(j)) {

                    mainTable.get(j).getCondition().set(i, 1.0);
                    equationHasBasicVariable.set(j, true);
                    break;
                }
            }
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

        List<Integer> basicVariables = table.getBasicVariables();

        for (Integer basicVariable : basicVariables) {
            if (basicVariable == -1) {
                result++;
            }
        }
        return result;
    }
}
