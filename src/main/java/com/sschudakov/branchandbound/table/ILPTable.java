package com.sschudakov.branchandbound.table;

import com.sschudakov.simplexmethod.table.LPTable;

import java.util.List;

public class ILPTable extends LPTable {

    int numOfInitialVariables;
    private List<Integer> integerVariables;

    public int getNumOfInitialVariables() {
        return numOfInitialVariables;
    }

    public void setNumOfInitialVariables(int numOfInitialVariables) {
        this.numOfInitialVariables = numOfInitialVariables;
    }

    public List<Integer> getIntegerVariables() {
        return integerVariables;
    }

    public void setIntegerVariables(List<Integer> integerVariables) {
        this.integerVariables = integerVariables;
    }
}
