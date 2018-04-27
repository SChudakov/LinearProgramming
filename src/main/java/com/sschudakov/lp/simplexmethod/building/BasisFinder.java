package com.sschudakov.lp.simplexmethod.building;

import com.sschudakov.lp.simplexmethod.table.LPRestriction;
import com.sschudakov.lp.simplexmethod.table.LPTable;

import java.util.List;

/**
 * Created by Semen Chudakov on 30.09.2017.
 */
public class BasisFinder {

    public void findBasis(LPTable table) {

        setNegariveBasicVariablesCoefficients(table);

        int numOfVariables = table.getNumOfVariables();
        List<LPRestriction> mainTable = table.getMainTable();

        for (int i = 0; i < numOfVariables; i++) {
            if (isBasicColumn(mainTable, i)) {
                int positionOfOne = tellPositionOfOne(mainTable, i);
                if (table.getMainTable().get(positionOfOne).getBasicVariable() == -1) {
                    table.getMainTable().get(positionOfOne).setBasicVariable(i);
                }
            }
        }
    }

    private void setNegariveBasicVariablesCoefficients(LPTable lpTable) {
        for (int i = 0; i < lpTable.getMainTable().size(); i++) {
            lpTable.getMainTable().get(i).setBasicVariable(-1);
        }
    }

    private boolean isBasicColumn(List<LPRestriction> mainTable, int column) {
        boolean hasOne = false;
        for (LPRestriction lpRestriction : mainTable) {
            if (lpRestriction.getCondition().get(column) != 1.0
                    && lpRestriction.getCondition().get(column) != 0.0) {
                return false;
            }
            if (lpRestriction.getCondition().get(column) == 1.0) {
                if (hasOne) {
                    return false;
                } else {
                    hasOne = true;
                }
            }
        }
        return true;
    }

    private int tellPositionOfOne(List<LPRestriction> mainTable, int column) {
        for (int i = 0; i < mainTable.size(); i++) {
            if (mainTable.get(i).getCondition().get(column) == 1.0) {
                return i;
            }
        }
        throw new RuntimeException("there is no 1 in " + column + " column");
    }
}
