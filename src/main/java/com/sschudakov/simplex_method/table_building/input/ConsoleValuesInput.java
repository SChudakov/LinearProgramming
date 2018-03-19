package com.sschudakov.simplex_method.table_building.input;

import com.sschudakov.simplex_method.table.SimplexTable;
import com.sschudakov.simplex_method.enums.Sign;
import com.sschudakov.simplex_method.enums.TaskType;

import java.util.Scanner;
import java.util.Vector;

/**
 * Created by Semen Chudakov on 30.09.2017.
 */
public class ConsoleValuesInput implements ValuesInput {

    private Scanner scanner;

    public ConsoleValuesInput() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void inputValues(SimplexTable table) {

        int numOfVariables = inputNumOfVariables();
        int numOfEquations = inputNumOfEquations();

        table.setNumOfVariables(numOfVariables);
        table.setNumOfEquations(numOfEquations);

        table.setFunction(inputFunction(numOfVariables));
        table.setTaskType(inputTaskType());
        table.setMainTable(inputMainTable(numOfEquations, numOfVariables));
        table.setEquationsSigns(inputRestrictionsSigns(numOfEquations));
        table.setRestrictionsVector(inputRestrictions(numOfEquations));
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

    private Vector<Double> inputFunction(int numOfVariables) {
        Vector<Double> result = new Vector<>(numOfVariables);
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
            System.out.println("input variables coefficients for " + (i + 1) + " equation");
            for (int j = 0; j < numOfColumns; j++) {
                result[i][j] = scanner.nextDouble();
            }
        }


        return result;
    }

    private Vector<Sign> inputRestrictionsSigns(int numOfEquations) {

        Vector<Sign> result = new Vector<>();

        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < numOfEquations; i++) {
            System.out.println("input sign for " + (i + 1) + " equation (= <= < > >=)");
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

    private Vector<Double> inputRestrictions(int numOfEquations) {
        Vector<Double> result = new Vector<>();

        for (int i = 0; i < numOfEquations; i++) {
            System.out.println("input restriction for " + (i + 1) + " equation");
            result.add(this.scanner.nextDouble());
        }

        return result;
    }
}
