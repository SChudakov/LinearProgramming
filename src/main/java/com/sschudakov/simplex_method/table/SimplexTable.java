package com.sschudakov.simplex_method.table;

import com.sschudakov.simplex_method.enumerable.Sign;
import com.sschudakov.simplex_method.enumerable.TaskType;
import com.sschudakov.simplex_method.exception.NoSolutionException;

import java.util.Arrays;
import java.util.Vector;

/**
 * Created by Semen Chudakov on 10.09.2017.
 */
public class SimplexTable {


    int numOfMVariables;
    private int numOfVariables;
    private int numOfEquations;

    private Vector<Double> function;
    private TaskType taskType;
    private double[][] mainTable;


    private Vector<Sign> equationsSigns;
    private Vector<Double> restrictionsVector;

    private Vector<Integer> basicVariables;

    //    private Vector<Double> basicVariablesCoefficients;

    private Vector<Double> deltasVector;

    private Vector<Double> simplexRatios;

    private double functionValue;

    //getters and setters

    public int getNumOfMVariables() {
        return numOfMVariables;
    }

    public void setNumOfMVariables(int numOfMVariables) {
        this.numOfMVariables = numOfMVariables;
    }

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

    public Vector<Integer> getBasicVariables() {
        return basicVariables;
    }

    public void setBasicVariables(Vector<Integer> basicVariables) {
        this.basicVariables = basicVariables;
    }

    public Vector<Double> getFunction() {
        return function;
    }

    public void setFunction(Vector<Double> function) {
        this.function = function;
    }

    public void setFunctionValue(double functionValue) {
        this.functionValue = functionValue;
    }

    public Vector<Double> getDeltasVector() {
        return deltasVector;
    }

    public void setDeltasVector(Vector<Double> deltasVector) {
        this.deltasVector = deltasVector;
    }

    public Vector<Double> getRestrictionsVector() {
        return restrictionsVector;
    }

    public void setRestrictionsVector(Vector<Double> restrictionsVector) {
        this.restrictionsVector = restrictionsVector;
    }

    public double getFunctionValue() {
        return functionValue;
    }

    public void setFunctionValue(Integer functionValue) {
        this.functionValue = functionValue;
    }

    public Vector<Double> getSimplexRatios() {
        return simplexRatios;
    }

    public void setSimplexRatios(Vector<Double> simplexRatios) {
        this.simplexRatios = simplexRatios;
    }

    public double[][] getMainTable() {
        return mainTable;
    }

    public void setMainTable(double[][] mainTable) {
        this.mainTable = mainTable;
    }

    public Vector<Sign> getEquationsSigns() {
        return equationsSigns;
    }

    public void setEquationsSigns(Vector<Sign> equationsSigns) {
        this.equationsSigns = equationsSigns;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
    }


    public SimplexTable() {
    }

    public SimplexTable(int numOfEquations, int numOfVariables) {

        this.numOfVariables = numOfVariables;
        this.numOfEquations = numOfEquations;

        this.function = new Vector<>(numOfVariables);

        this.deltasVector = new Vector<>(numOfVariables);
        this.restrictionsVector = new Vector<>(numOfEquations);

        this.basicVariables = new Vector<>(numOfEquations);
//        this.basicVariablesCoefficients = new Vector<>(numOfEquations);
//        this.nonbasicVariablesCoefficients = new Vector<>(numOfEquations);

        this.mainTable = new double[numOfEquations][numOfVariables];
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
            if(basicVariable >= this.numOfVariables - this.numOfMVariables){
                throw new NoSolutionException("The multitude of appropriate solving vectors is empty");
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
