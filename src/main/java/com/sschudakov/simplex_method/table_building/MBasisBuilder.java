package com.sschudakov.simplex_method.table_building;

import com.sschudakov.simplex_method.util.MainTableCopy;
import com.sschudakov.simplex_method.table.LPTable;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created by Semen Chudakov on 08.10.2017.
 */
public class MBasisBuilder {

    private static Double M_VALUE = Double.MAX_VALUE;

    public void buildMBasis(LPTable table) {

        M_VALUE = 1_000.0/*MFinder.findM(table)*/;

        int numOfMVariables = tellNumOfMVariables(table);
        table.setNumOfMVariables(numOfMVariables);
        int numOfXVariables = table.getNumOfVariables();

        table.setMainTable(rebuildMainTable(table.getMainTable(), table.getBasicVariables(), numOfMVariables));

        rebuildFunction(table.getFunction(), numOfMVariables);

        rewriteDataInTable(table, numOfXVariables, numOfMVariables);
    }

    private void rewriteDataInTable(LPTable table, int numOfXVariables, int numOfYVariables) {
        table.setNumOfVariables(table.getNumOfVariables() + numOfYVariables);

        List<Integer> basicVariables = table.getBasicVariables();

        double[][] mainTable = table.getMainTable();

        for (int i = numOfXVariables; i < numOfXVariables + numOfYVariables; i++) {
            basicVariables.set(tellPositionOfOne(mainTable, i), i);
        }
    }

    private double[][] rebuildMainTable(double[][] mainTable, List<Integer> basicVariables, int numOfYVariables) {

        double[][] result = new double[mainTable.length][mainTable[0].length + numOfYVariables];

        MainTableCopy.copyTable(mainTable, result);

//        System.out.println("result after copy");
//        for (double[] doubles : result) {
//            System.out.println(Arrays.toString(doubles));
//        }

        initializeYVariables(result, basicVariables, mainTable[0].length);

        return result;
    }

    private void initializeYVariables(double[][] result, List<Integer> basicVariables, int numOfXVariables) {

        int numOfEquations = result.length;
        int totalAmountOfVariables = result[0].length;

        List<Boolean> equationHasBasicVector = new ArrayList<>();

        for (int i = 0; i < numOfEquations; i++) {
            equationHasBasicVector.add(false);
        }

        for (Integer basicVariable : basicVariables) {
            if (basicVariable != -1) {
                equationHasBasicVector.set(tellPositionOfOne(result, basicVariable), true);
            }
        }

        for (int i = numOfXVariables; i < totalAmountOfVariables; i++) {
            for (int j = 0; j < numOfEquations; j++) {
                if (!equationHasBasicVector.get(j)) {
                    result[j][i] = 1;

                    equationHasBasicVector.set(j, true);
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

    private int tellNumOfMVariables(LPTable table){
        int result = 0;

        List<Integer> basicVariables = table.getBasicVariables();

        for (Integer basicVariable : basicVariables) {
            if(basicVariable == -1){
                result++;
            }
        }
        return result;
    }
}
