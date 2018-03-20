package com.sschudakov.simplex_method.table_building;

import com.sschudakov.simplex_method.enumerable.Sign;
import com.sschudakov.simplex_method.enumerable.TaskType;
import com.sschudakov.simplex_method.table.LPTable;
import com.sschudakov.simplex_method.util.MainTableCopy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Semen Chudakov on 12.10.2017.
 */
public class SimplexTableModifier {

    public void modifyTable(LPTable table, TaskType toType) {
        modifyFunctionType(table, toType);
        modifyInequationsToEquations(table);
    }

    private void modifyFunctionType(LPTable table, TaskType toType) {

        TaskType tableTaskType = table.getTaskType();

        if (!tableTaskType.equals(toType)) {
            changeFunctionCoefficients(table.getFunction());
        }

        table.setTaskType(toType);
    }

    private void changeFunctionCoefficients(List<Double> function) {
        for (int i = 0; i < function.size(); i++) {
            function.set(i, -function.get(i));
        }
    }

    private void modifyInequationsToEquations(LPTable table) {

        List<Sign> equationsSigns = table.getEquationsSigns();

        List<Integer> equationsForNewVariables = new ArrayList<>();

        for (int i = 0; i < equationsSigns.size(); i++) {
            if (table.getEquationsSigns().get(i).equals(Sign.GREATER_THAN)
                    || table.getEquationsSigns().get(i).equals(Sign.GREATER_THAN_OR_EQUAL_TO)) {
                reverseEquationSign(table, i);
            }
        }
        for (int i = 0; i < equationsSigns.size(); i++) {
            if (!table.getEquationsSigns().get(i).equals(Sign.EQUAL)) {
                equationsForNewVariables.add(i);
            }
        }

        addVariables(table, equationsForNewVariables);
        changeInequationSignsToEqual(table);
    }

    private void reverseEquationSign(LPTable table, int equation) {

        double[][] mainTable = table.getMainTable();

        for (int i = 0; i < mainTable[equation].length; i++) {
            mainTable[equation][i] = -mainTable[equation][i];
        }

        table.getRestrictionsVector().set(equation, -table.getRestrictionsVector().get(equation));

        if (table.getEquationsSigns().get(equation).equals(Sign.GREATER_THAN)) {
            table.getEquationsSigns().set(equation, Sign.LESS_THAN);
        }
        if (table.getEquationsSigns().get(equation).equals(Sign.GREATER_THAN_OR_EQUAL_TO)) {
            table.getEquationsSigns().set(equation, Sign.LESS_THAN_OR_EQUAL_TO);
        }
    }

    private void addVariables(LPTable table, List<Integer> equationsForNewVariables) {

        int numOfNewVariables = equationsForNewVariables.size();

        double[][] mainTable = table.getMainTable();
        double[][] newTable = new double[mainTable.length][mainTable[0].length + numOfNewVariables];

        MainTableCopy.copyTable(mainTable, newTable);

        for (int i = 0; i < numOfNewVariables; i++) {
            newTable[equationsForNewVariables.get(i)][mainTable[0].length + i] = 1;
            table.getFunction().add(0.0);
        }

        table.setMainTable(newTable);

        table.setNumOfVariables(table.getNumOfVariables() + equationsForNewVariables.size());

        //copy table
        //initialize variables according to the their positions
        //add them ti the function
        //change num of variables value in the table
    }

    private void changeInequationSignsToEqual(LPTable table) {
        for (int i = 0; i < table.getEquationsSigns().size(); i++) {
            table.getEquationsSigns().set(i, Sign.EQUAL);
        }
    }

}
