package com.sschudakov.lp.simplexmethod.solving;

import com.sschudakov.lp.simplexmethod.table.LPTable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

public class LPSolution {
    private LPTable lpTable;

    private List<Double> solutionVector;
    private Double functionValue;

    private Exception solvingException;

    public LPTable getLpTable() {
        return lpTable;
    }

    public void setLpTable(LPTable lpTable) {
        this.lpTable = lpTable;
    }

    public List<Double> getSolutionVector() {
        return solutionVector;
    }

    public void setSolutionVector(List<Double> solutionVector) {
        this.solutionVector = solutionVector;
    }

    public Double getFunctionValue() {
        return functionValue;
    }

    public void setFunctionValue(Double functionValue) {
        this.functionValue = functionValue;
    }

    public Exception getSolvingException() {
        return solvingException;
    }

    public void setSolvingException(Exception solvingException) {
        this.solvingException = solvingException;
    }

    LPSolution(LPTable lpTable, List<Double> solutionVector, Double functionValue) {
        this.lpTable = lpTable;
        this.solutionVector = solutionVector;
        this.functionValue = functionValue;
    }

    LPSolution(LPTable lpTable, Exception solvingException) {
        this.lpTable = lpTable;
        this.solvingException = solvingException;
    }


    public boolean endedWithException() {
        return this.solvingException != null;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE)
                .append(this.solutionVector)
                .append(this.functionValue)
                .append(this.solvingException).build();
    }
}
