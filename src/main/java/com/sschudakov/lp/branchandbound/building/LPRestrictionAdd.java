package com.sschudakov.lp.branchandbound.building;

import com.sschudakov.lp.simplexmethod.table.LPRestriction;
import com.sschudakov.lp.simplexmethod.table.LPTable;

public class LPRestrictionAdd {
    public void addRestriction(LPTable lpTable, LPRestriction restriction) {
        increaseNumOfEquations(lpTable);
        lpTable.getMainTable().add(restriction);
    }

    private void increaseNumOfEquations(LPTable lpTable) {
        lpTable.setNumOfEquations(
                lpTable.getNumOfEquations() + 1
        );
    }
}
