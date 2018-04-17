package com.sschudakov.lp.simplexmethod.table;

import com.sschudakov.lp.simplexmethod.enumerable.Sign;
import com.sschudakov.lp.simplexmethod.enumerable.TaskType;
import com.sschudakov.lp.simplexmethod.exception.NoSolutionException;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Semen Chudakov on 10.09.2017.
 */
public class LPTable {


    private Integer numOfVariables;
    private Integer numOfInitialVariables;
    private Integer numOfMVariables;
    private Integer numOfEquations;


    private List<Double> function;
    private TaskType taskType;
    private double[][] mainTable;

    private List<Sign> equationsSigns;
    private List<Double> restrictionsVector;

    private List<Integer> basicVariables;

    private List<Double> deltasVector;
    private List<Double> simplexRatios;

    private Double functionValue;

    //getters and setters
    public Integer getNumOfVariables() {
        return numOfVariables;
    }

    public void setNumOfVariables(Integer numOfVariables) {
        this.numOfVariables = numOfVariables;
    }

    public Integer getNumOfInitialVariables() {
        return numOfInitialVariables;
    }

    public void setNumOfInitialVariables(Integer numOfInitialVariables) {
        this.numOfInitialVariables = numOfInitialVariables;
    }

    public Integer getNumOfEquations() {
        return numOfEquations;
    }

    public void setNumOfEquations(Integer numOfEquations) {
        this.numOfEquations = numOfEquations;
    }

    public Integer getNumOfMVariables() {
        return numOfMVariables;
    }

    public void setNumOfMVariables(Integer numOfMVariables) {
        this.numOfMVariables = numOfMVariables;
    }

    public List<Double> getFunction() {
        return function;
    }

    public void setFunction(List<Double> function) {
        this.function = function;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
    }

    public double[][] getMainTable() {
        return mainTable;
    }

    public void setMainTable(double[][] mainTable) {
        this.mainTable = mainTable;
    }

    public List<Sign> getEquationsSigns() {
        return equationsSigns;
    }

    public void setEquationsSigns(List<Sign> equationsSigns) {
        this.equationsSigns = equationsSigns;
    }

    public List<Double> getRestrictionsVector() {
        return restrictionsVector;
    }

    public void setRestrictionsVector(List<Double> restrictionsVector) {
        this.restrictionsVector = restrictionsVector;
    }

    public List<Integer> getBasicVariables() {
        return basicVariables;
    }

    public void setBasicVariables(List<Integer> basicVariables) {
        this.basicVariables = basicVariables;
    }

    public List<Double> getDeltasVector() {
        return deltasVector;
    }

    public void setDeltasVector(List<Double> deltasVector) {
        this.deltasVector = deltasVector;
    }

    public List<Double> getSimplexRatios() {
        return simplexRatios;
    }

    public void setSimplexRatios(List<Double> simplexRatios) {
        this.simplexRatios = simplexRatios;
    }

    public Double getFunctionValue() {
        return functionValue;
    }

    public void setFunctionValue(Double functionValue) {
        this.functionValue = functionValue;
    }

    public LPTable() {
    }

    public boolean isOptimal() {
        for (Double evaluation : this.deltasVector) {
            if (evaluation < 0) {
                return false;
            }
        }
        return true;
    }

    public boolean isDualOptimal() {
        for (Double aDouble : restrictionsVector) {
            if (aDouble < 0) {
                return false;
            }
        }
        ensureCorrectTask();
        return true;
    }

    private void ensureCorrectTask() {
        for (Integer basicVariable : this.basicVariables) {
            if (basicVariable >= this.numOfVariables - this.numOfMVariables) {
                throw new NoSolutionException("The set of appropriate solving vectors is empty");
            }
        }
    }


    public void outputTable() {

        System.out.println("\nnum of variables: " + this.numOfVariables);
        System.out.println("num of initial variables: " + this.numOfInitialVariables);
        System.out.println("num of M variables: " + this.numOfMVariables);
        System.out.println("num of equations: " + this.numOfEquations + "\n");

        System.out.println("function: " + this.function.toString());

        System.out.println("task type: " + this.taskType);

        for (double[] row : mainTable) {
            System.out.println(Arrays.toString(row));
        }

        System.out.println("signs: " + this.equationsSigns.toString());

        System.out.println("restrictions: " + this.restrictionsVector.toString());

        if (this.basicVariables != null) {
            System.out.println("basic variables: " + this.basicVariables.toString());
        }

        if (this.deltasVector != null) {
            System.out.println("deltas: " + this.deltasVector.toString());
        }

        if (this.simplexRatios != null) {
            System.out.println("simplex ratios: " + this.simplexRatios.toString());
        }

//        if (this.basicVariablesCoefficients != null) {
//            System.out.println("basic variables coefficients: " + this.basicVariablesCoefficients.toString());
//        }
    }

}