package com.sschudakov.branchandbound.building;

import com.sschudakov.simplexmethod.enumerable.Sign;
import com.sschudakov.simplexmethod.table.LPTable;
import com.sschudakov.simplexmethod.util.Utils;

import java.util.List;

public class LPRestrictionAdd {
    public void addRestriction(LPTable lpTable, LPRestriction restriction) {
        // increase num of equations
        increaseNumOfEquations(lpTable);
        // create main table [n+1][m]
        double[][] createdTable = createLargerTable(lpTable);
        // copy table
        copyMainTableContent(lpTable.getMainTable(), createdTable);
        // set new table
        setCreatedTable(lpTable, createdTable);
        // insert new condition coefficients
        insertConditionCoefficients(lpTable, restriction.getCondition());
        // add sing to signs list
        addSign(lpTable, restriction.getSign());
        // add restriction to restrictions list
        addRestriction(lpTable, restriction.getRightPartValue());
    }

    private void increaseNumOfEquations(LPTable lpTable) {
        lpTable.setNumOfEquations(
                lpTable.getNumOfEquations() + 1
        );
    }

    private double[][] createLargerTable(LPTable lpTable) {
        return new double[lpTable.getMainTable().length + 1][lpTable.getMainTable()[0].length];
    }

    private void copyMainTableContent(double[][] old, double[][] created) {
        Utils.copyTable(old, created);
    }

    private void setCreatedTable(LPTable lpTable, double[][] mainTable) {
        lpTable.setMainTable(mainTable);
    }

    private void insertConditionCoefficients(LPTable lpTable, List<Double> condition) {
        double[][] mainTable = lpTable.getMainTable();

        int tableColumnLength = mainTable.length;

        for (int i = 0; i < condition.size(); i++) {
            mainTable[tableColumnLength - 1][i] = condition.get(i);
        }
    }

    private void addSign(LPTable lpTable, Sign sign) {
        lpTable.getEquationsSigns().add(sign);
    }

    private void addRestriction(LPTable table, Double restriciton) {
        table.getRestrictionsVector().add(restriciton);
    }
}
