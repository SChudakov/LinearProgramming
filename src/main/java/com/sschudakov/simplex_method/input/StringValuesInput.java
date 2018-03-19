package com.sschudakov.simplex_method.input;

import com.sschudakov.simplex_method.enumerable.Sign;
import com.sschudakov.simplex_method.enumerable.TaskType;
import com.sschudakov.simplex_method.table.SimplexTable;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

/**
 * Created by Semen Chudakov on 24.10.2017.
 */
public class StringValuesInput {

    public SimplexTable inputValues(String taskString) {

        Scanner scanner = new Scanner(taskString);
        SimplexTable table = new SimplexTable();

        int numOfVariables;
        int numOfEquations;

        numOfVariables = inputNumOfVariables(scanner);
        table.setNumOfVariables(numOfVariables);

        numOfEquations = inputNumOfEquations(scanner);
        table.setNumOfEquations(numOfEquations);

        table.setFunction(inputFunction(scanner, numOfVariables));
        table.setTaskType(inputTaskType(scanner));
        table.setMainTable(inputMainTable(scanner, numOfEquations, numOfVariables));
        table.setEquationsSigns(inputRestrictionsSigns(scanner, numOfEquations));
        table.setRestrictionsVector(inputRestrictions(scanner, numOfEquations));

        return table;
    }

    private int inputNumOfVariables(Scanner scanner) {
        return scanner.nextInt();
    }

    private int inputNumOfEquations(Scanner scanner) {
        return scanner.nextInt();
    }

    private ArrayList<Double> inputFunction(Scanner scanner, int numOfVariables) {
        ArrayList<Double> result = new ArrayList<>(numOfVariables);
        for (int i = 0; i < numOfVariables; i++) {
            result.add(scanner.nextDouble());
        }
        return result;
    }

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

    private double[][] inputMainTable(Scanner scanner, int numOfRows, int numOfColumns) {

        double[][] result = new double[numOfRows][numOfColumns];

        for (int i = 0; i < numOfRows; i++) {
            for (int j = 0; j < numOfColumns; j++) {
                result[i][j] = scanner.nextDouble();
            }
        }


        return result;
    }

    private ArrayList<Sign> inputRestrictionsSigns(Scanner scanner, int numOfEquations) {

        ArrayList<Sign> result = new ArrayList<>();

        for (int i = 0; i < numOfEquations; i++) {
            result.add(inputSign(scanner));
        }

        return result;
    }

    private Sign inputSign(Scanner scanner) {

        String sign = scanner.next();

        if (sign.equals("=")) {
            return Sign.EQUAL;
        }
        if (sign.equals(">=")) {
            return Sign.GREATER_THAN_OR_EQUAL_TO;
        }
        if (sign.equals(">")) {
            return Sign.GREATER_THAN;
        }
        if (sign.equals("<=")) {
            return Sign.LESS_THAN_OR_EQUAL_TO;
        }
        if (sign.equals("<")) {
            return Sign.LESS_THAN;
        }

        throw new IllegalArgumentException("Input sign is not available. Available sings are: = >= > < <=");
    }

    private ArrayList<Double> inputRestrictions(Scanner scanner, int numOfEquations) {
        ArrayList<Double> result = new ArrayList<>();

        for (int i = 0; i < numOfEquations; i++) {
            System.out.println("input restriction for " + (i + 1) + " equation");
            result.add(scanner.nextDouble());
        }

        return result;
    }
}
