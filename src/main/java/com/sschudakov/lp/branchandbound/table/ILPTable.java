package com.sschudakov.lp.branchandbound.table;

import com.sschudakov.lp.simplexmethod.table.LPTable;

import java.util.List;

public class ILPTable extends LPTable {

    private List<Integer> integerVariables;

    public List<Integer> getIntegerVariables() {
        return integerVariables;
    }

    public void setIntegerVariables(List<Integer> integerVariables) {
        this.integerVariables = integerVariables;
    }
}
