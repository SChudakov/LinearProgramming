package com.sschudakov.lp.simplexmethod.building;

import com.sschudakov.lp.simplexmethod.table.LPTable;

import java.util.List;

/**
 * Created by Semen Chudakov on 30.09.2017.
 */
public class BasisFinder {

    public void findBasis(LPTable table) {

        setNegariveBasicVariablesCoefficients(table);

        List<Integer> basicVariables = table.getBasicVariables();

        int numOfVariables = table.getNumOfVariables();
        double[][] mainTable = table.getMainTable();

        for (int i = 0; i < numOfVariables; i++) {
            if (isBasicColumn(mainTable, i)) {
                int positionOfOne = tellPositionOfOne(mainTable, i);
                if (basicVariables.get(positionOfOne) == -1) {
                    basicVariables.set(positionOfOne, i);
                }
            }
        }
    }

    private void setNegariveBasicVariablesCoefficients(LPTable lpTable) {
        for (int i = 0; i < lpTable.getBasicVariables().size(); i++) {
            lpTable.getBasicVariables().set(i, -1);
        }
    }

    private boolean isBasicColumn(double[][] mainTable, int column) {
        boolean hasOne = false;
        for (double[] equation : mainTable) {
            if (equation[column] != 1.0 && equation[column] != 0.0) {
                return false;
            }
            if (equation[column] == 1.0) {
                if (hasOne) {
                    return false;
                } else {
                    hasOne = true;
                }
            }
        }
        return true;
    }

    private int tellPositionOfOne(double[][] mainTable, int column) {
        for (int i = 0; i < mainTable.length; i++) {
            if (mainTable[i][column] == 1.0) {
                return i;
            }
        }
        throw new RuntimeException("there is no 1 in " + column + " column");
    }
}
