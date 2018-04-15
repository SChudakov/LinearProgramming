package com.sschudakov.branchandbound.table;

import com.sschudakov.simplexmethod.table.LPTable;

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
