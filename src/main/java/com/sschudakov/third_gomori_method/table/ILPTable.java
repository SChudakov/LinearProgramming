package com.sschudakov.third_gomori_method.table;

import com.sschudakov.simplex_method.table.LPTable;

import java.util.List;

public class ILPTable extends LPTable {

    private List<Integer> nonNegativeVariables;

    public List<Integer> getNonNegativeVariables() {
        return nonNegativeVariables;
    }

    public void setNonNegativeVariables(List<Integer> nonNegativeVariables) {
        this.nonNegativeVariables = nonNegativeVariables;
    }
}
