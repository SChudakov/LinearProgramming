package com.sschudakov.simplex_method.input;

import com.sschudakov.simplex_method.enumerable.Sign;
import com.sschudakov.simplex_method.enumerable.TaskType;
import com.sschudakov.simplex_method.table.LPTable;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Semen Chudakov on 30.09.2017.
 */
public class LPConsoleInput {

    private Scanner scanner;

    public LPConsoleInput() {
        this.scanner = new Scanner(System.in);
    }


    public LPTable inputValues() {

        LPTable table = new LPTable();

        int numOfVariables = inputNumOfVariables();
        int numOfEquations = inputNumOfEquations();

        table.setNumOfVariables(numOfVariables);
        table.setNumOfEquations(numOfEquations);

        table.setFunction(inputFunction(numOfVariables));
        table.setTaskType(inputTaskType());
        table.setMainTable(inputMainTable(numOfEquations, numOfVariables));
        table.setEquationsSigns(inputRestrictionsSigns(numOfEquations));
        table.setRestrictionsVector(inputRestrictions(numOfEquations));

        return table;
    }

    private int inputNumOfVariables() {
        System.out.println("Input number of variables");
        int result = this.scanner.nextInt();
        return result;
    }

    private int inputNumOfEquations() {
        System.out.println("Input number of equations");
        int result = this.scanner.nextInt();
        return result;
    }

    private List<Double> inputFunction(int numOfVariables) {
        List<Double> result = new ArrayList<>();
        System.out.println("enter variables coefficients in function");
        for (int i = 0; i < numOfVariables; i++) {
            result.add(this.scanner.nextDouble());
        }
        return result;
    }

    private TaskType inputTaskType() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter task type (max \\ min)");
        String taskType = scanner.next();

        if (taskType.equals("max")) {
            return TaskType.MAX;
        }
        if (taskType.equals("min")) {
            return TaskType.MIN;
        }
        throw new IllegalArgumentException("entered task type: " + taskType + " while expected: min \\ max");
    }

    private double[][] inputMainTable(int numOfRows, int numOfColumns) {

        double[][] result = new double[numOfRows][numOfColumns];

        for (int i = 0; i < numOfRows; i++) {
            System.out.println("inputILP variables coefficients for " + (i + 1) + " equation");
            for (int j = 0; j < numOfColumns; j++) {
                result[i][j] = scanner.nextDouble();
            }
        }


        return result;
    }

    private List<Sign> inputRestrictionsSigns(int numOfEquations) {

        List<Sign> result = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < numOfEquations; i++) {
            System.out.println("inputILP sign for " + (i + 1) + " equation (= <= < > >=)");
            result.add(inputSign(scanner));
        }

        return result;
    }

    private Sign inputSign(Scanner scanner) {

        String sing = scanner.next();

        if (sing.equals("=")) {
            return Sign.EQUAL;
        }
        if (sing.equals(">=")) {
            return Sign.GREATER_THAN_OR_EQUAL_TO;
        }
        if (sing.equals(">")) {
            return Sign.GREATER_THAN;
        }
        if (sing.equals("<=")) {
            return Sign.LESS_THAN_OR_EQUAL_TO;
        }
        if (sing.equals("<")) {
            return Sign.LESS_THAN;
        }

        throw new IllegalArgumentException("Input sign is not available. Available sings are: = >= > < <=");
    }

    private List<Double> inputRestrictions(int numOfEquations) {
        List<Double> result = new ArrayList<>();

        for (int i = 0; i < numOfEquations; i++) {
            System.out.println("inputILP restriction for " + (i + 1) + " equation");
            result.add(this.scanner.nextDouble());
        }

        return result;
    }
}
