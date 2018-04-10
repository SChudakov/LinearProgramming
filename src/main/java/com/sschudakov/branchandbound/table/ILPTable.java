package com.sschudakov.branchandbound.table;

import com.sschudakov.simplexmethod.table.LPTable;

import java.util.List;

public class ILPTable extends LPTable {

    private Integer numOfInitialVariables;
    private List<Integer> integerVariables;

    public Integer getNumOfInitialVariables() {
        return numOfInitialVariables;
    }

    public void setNumOfInitialVariables(Integer numOfInitialVariables) {
        this.numOfInitialVariables = numOfInitialVariables;
    }

    public List<Integer> getIntegerVariables() {
        return integerVariables;
    }

    public void setIntegerVariables(List<Integer> integerVariables) {
        this.integerVariables = integerVariables;
    }
}
