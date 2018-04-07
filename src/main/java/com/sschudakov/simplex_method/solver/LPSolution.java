package com.sschudakov.simplex_method.solver;

import java.util.List;

public class LPSolution {

    private List<Double> solutionVector;
    private Double solution;

    public List<Double> getSolutionVector() {
        return solutionVector;
    }

    public Double getSolution() {
        return solution;
    }


    LPSolution(List<Double> solutionVector, Double solution) {
        this.solutionVector = solutionVector;
        this.solution = solution;
    }
}
