package com.sschudakov.simplex_method.table;

import com.sschudakov.simplex_method.enumerable.Sign;
import com.sschudakov.simplex_method.enumerable.TaskType;
import com.sschudakov.simplex_method.exception.NoSolutionException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Semen Chudakov on 10.09.2017.
 */
public class LPTable {


    private int numOfVariables;
    private int numOfEquations;

    int numOfMVariables;

    private List<Double> function;
    private TaskType taskType;
    private double[][] mainTable;

    private List<Sign> equationsSigns;
    private List<Double> restrictionsVector;

    private List<Integer> basicVariables;

    private List<Double> deltasVector;
    private List<Double> simplexRatios;

    private double functionValue;

    //getters and setters
    public int getNumOfVariables() {
        return numOfVariables;
    }

    public void setNumOfVariables(int numOfVariables) {
        this.numOfVariables = numOfVariables;
    }

    public int getNumOfEquations() {
        return numOfEquations;
    }

    public void setNumOfEquations(int numOfEquations) {
        this.numOfEquations = numOfEquations;
    }

    public int getNumOfMVariables() {
        return numOfMVariables;
    }

    public void setNumOfMVariables(int numOfMVariables) {
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

    public double getFunctionValue() {
        return functionValue;
    }

    public void setFunctionValue(double functionValue) {
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
