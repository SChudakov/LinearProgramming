package com.sschudakov.lp.simplexmethod.building;

import com.sschudakov.lp.simplexmethod.table.LPTable;
import com.sschudakov.lp.simplexmethod.util.Utils;

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

        double[][] rebuiltTable = rebuildMainTable(table.getMainTable(), table.getBasicVariables(), numOfMVariables);
        table.setMainTable(rebuiltTable);

        rebuildFunction(table.getFunction(), numOfMVariables);
        rewriteDataInTable(table, firstMVariable, numOfMVariables);
    }

    private void rewriteDataInTable(LPTable table, int firstMVariable, int numOfMVariables) {
        List<Integer> basicVariables = table.getBasicVariables();

        double[][] mainTable = table.getMainTable();

        for (int i = firstMVariable; i < firstMVariable + numOfMVariables; i++) {
            basicVariables.set(tellPositionOfOne(mainTable, i), i);
        }

        Integer initialNumberOfMVariable = table.getNumOfMVariables() == null ? 0 : table.getNumOfMVariables();

        table.setNumOfVariables(table.getNumOfVariables() + numOfMVariables);
        table.setNumOfMVariables(initialNumberOfMVariable + numOfMVariables);
    }

    private double[][] rebuildMainTable(double[][] mainTable, List<Integer> basicVariables, int numOfMVariables) {

        double[][] result = new double[mainTable.length][mainTable[0].length + numOfMVariables];
        Utils.copyTable(mainTable, result);

        initializeMVariablesInTable(result, basicVariables, mainTable[0].length);

        return result;
    }

    private void initializeMVariablesInTable(double[][] mainTable, List<Integer> basicVariables, int numOfXVariables) {

        int numOfEquations = mainTable.length;
        int totalAmountOfVariables = mainTable[0].length;

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
                    mainTable[j][i] = 1;

                    equationHasBasicVariable.set(j, true);
                    break;
                }
            }
        }
    }

    private int tellPositionOfOne(double[][] mainTable, int column) {
        for (int i = 0; i < mainTable.length; i++) {
            if (mainTable[i][column] == 1.0) {
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
