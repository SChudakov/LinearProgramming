package com.sschudakov.simplexmethod.solver;

import com.sschudakov.simplexmethod.table.LPTable;

import java.util.List;

public class LPSolution {
    private LPTable lpTable;

    private List<Double> solutionVector;
    private Double functionValue;

    private Exception solvingException;


    public LPTable getLpTable() {
        return lpTable;
    }

    public List<Double> getSolutionVector() {
        return solutionVector;
    }

    public Double getFunctionValue() {
        return functionValue;
    }

    public Exception getSolvingException() {
        return solvingException;
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
        return this.solvingException == null;
    }
}
