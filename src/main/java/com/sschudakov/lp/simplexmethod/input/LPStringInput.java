package com.sschudakov.lp.simplexmethod.input;

import com.sschudakov.lp.simplexmethod.enumerable.Sign;
import com.sschudakov.lp.simplexmethod.enumerable.TaskType;
import com.sschudakov.lp.simplexmethod.table.LPTable;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Defines a class that inputs a
 * {@linnk LPTable} from a string
 *
 * @author Semen Chudakov
 * @since 24.10.2017
 */
public class LPStringInput {

    /**
     * <p>
     * Deals with controlling the flow
     * of the table data being input
     * <p>
     * Creates a {@link Scanner} object with a {@code lpString} as a parameter.
     * Invokes setter of an {@code lpTable} object to put data in there. The
     * data for the setters invocations is formed with corresponding methods/
     *
     * @param lpTable  table to be filled with data
     * @param lpString string representation of lp task
     */
    public void inputLP(LPTable lpTable, String lpString) {

        Scanner scanner = new Scanner(lpString);

        int numOfVariables = inputNumOfVariables(scanner);
        int numOfEquations = inputNumOfEquations(scanner);

        lpTable.setNumOfVariables(numOfVariables);
        lpTable.setNumOfInitialVariables(numOfVariables);
        lpTable.setNumOfEquations(numOfEquations);

        lpTable.setFunction(inputFunction(scanner, numOfVariables));
        lpTable.setTaskType(inputTaskType(scanner));
        lpTable.setMainTable(inputMainTable(scanner, numOfEquations, numOfVariables));
        lpTable.setEquationsSigns(inputRestrictionsSigns(scanner, numOfEquations));
        lpTable.setRestrictionsVector(inputRestrictions(scanner, numOfEquations));
    }

    /**
     * This method parses num of variables integer using {@code scanner}
     *
     * @param scanner scanner of a lp task string
     * @return num of variables
     */
    private int inputNumOfVariables(Scanner scanner) {
        return scanner.nextInt();
    }

    /**
     * This method parses num of equations integer using {@code scanner}
     *
     * @param scanner scanner of a lp task string
     * @return num of equations
     */
    private int inputNumOfEquations(Scanner scanner) {
        return scanner.nextInt();
    }

    /**
     * This method parses function coefficients using {@code scanner}
     *
     * @param scanner scanner of a lp task string
     * @return list of function coefficients
     */
    private List<Double> inputFunction(Scanner scanner, int numOfVariables) {
        List<Double> result = new ArrayList<>(numOfVariables);
        for (int i = 0; i < numOfVariables; i++) {
            result.add(scanner.nextDouble());
        }
        return result;
    }

    /**
     * This method parses task type using {@code scanner}
     *
     * @param scanner scanner of a lp task string
     * @return task type
     */
    private TaskType inputTaskType(Scanner scanner) {
        String taskType = scanner.next();

        if (taskType.equals("max")) {
            return TaskType.MAX;
        }
        if (taskType.equals("min")) {
            return TaskType.MIN;
        }
        throw new IllegalArgumentException("entered task type: " + taskType + " while expected: min \\ max");
    }

    /**
     * This method parses main table of a task using {@code scanner}
     *
     * @param scanner scanner of a lp task string
     * @return main table
     */
    private double[][] inputMainTable(Scanner scanner, int numOfRows, int numOfColumns) {

        double[][] result = new double[numOfRows][numOfColumns];

        for (int i = 0; i < numOfRows; i++) {
            for (int j = 0; j < numOfColumns; j++) {
                result[i][j] = scanner.nextDouble();
            }
        }


        return result;
    }

    /**
     * This method parses restrictions signs list using {@code scanner}
     * <p>
     * Invokes inputSign() method {@code numOfEquations} times.
     *
     * @param scanner scanner of a lp task string
     * @return restrictions signs list
     */
    private List<Sign> inputRestrictionsSigns(Scanner scanner, int numOfEquations) {

        ArrayList<Sign> result = new ArrayList<>();

        for (int i = 0; i < numOfEquations; i++) {
            String sign = scanner.next();
            result.add(InputUtils.inputSign(sign));
        }

        return result;
    }


    /**
     * This method parses restrictions right part values list using {@code scanner}
     *
     * @param scanner scanner of a lp task string
     * @return list of restrictions values
     */
    private List<Double> inputRestrictions(Scanner scanner, int numOfEquations) {
        List<Double> result = new ArrayList<>();

        for (int i = 0; i < numOfEquations; i++) {
            result.add(scanner.nextDouble());
        }

        return result;
    }
}
