package com.sschudakov.lp.simplexmethod.table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

public class LPSolution {
    private List<Double> solutionVector;
    private Double functionValue;

    private Exception solvingException;

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

    public LPSolution(List<Double> solutionVector, Double functionValue) {
        this.solutionVector = solutionVector;
        this.functionValue = functionValue;
    }

    public LPSolution(Exception solvingException) {
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
