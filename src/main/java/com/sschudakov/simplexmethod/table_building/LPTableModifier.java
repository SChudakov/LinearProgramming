package com.sschudakov.simplexmethod.table_building;

import com.sschudakov.simplexmethod.enumerable.Sign;
import com.sschudakov.simplexmethod.enumerable.TaskType;
import com.sschudakov.simplexmethod.table.LPTable;
import com.sschudakov.simplexmethod.util.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Semen Chudakov on 12.10.2017.
 */
public class LPTableModifier {

    private static final int LESS_THAN_INEQUATION_NORMALIZER_COEFFICIENT = 1;
    private static final int GREATER_INEQUATION_NORMALIZER_COEFFICIENT = -1;

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

        List<Integer> equationsForNewVariables = new ArrayList<>();

        for (int i = 0; i < table.getNumOfEquations(); i++) {
            if (table.getRestrictionsVector().get(i) > 0) {
                reverseEquationSign(table, i);
            }
        }
        for (int i = 0; i < table.getNumOfEquations(); i++) {
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
        if (table.getEquationsSigns().get(equation).equals(Sign.LESS_THAN)) {
            table.getEquationsSigns().set(equation, Sign.GREATER_THAN);
        }
        if (table.getEquationsSigns().get(equation).equals(Sign.LESS_THAN_OR_EQUAL_TO)) {
            table.getEquationsSigns().set(equation, Sign.GREATER_THAN_OR_EQUAL_TO);
        }
    }

    private void addVariables(LPTable table, List<Integer> equationsForNewVariables) {

        int numOfNewVariables = equationsForNewVariables.size();

        double[][] mainTable = table.getMainTable();
        double[][] copiedTable = new double[mainTable.length][mainTable[0].length + numOfNewVariables];

        Utils.copyTable(mainTable, copiedTable);

        for (int i = 0; i < numOfNewVariables; i++) {
            if (table.getEquationsSigns()
                    .get(equationsForNewVariables.get(i)).equals(Sign.LESS_THAN) ||
                    table.getEquationsSigns()
                            .get(equationsForNewVariables.get(i)).equals(Sign.LESS_THAN_OR_EQUAL_TO)) {

                copiedTable[equationsForNewVariables.get(i)][mainTable[0].length + i]
                        = LESS_THAN_INEQUATION_NORMALIZER_COEFFICIENT;

            }
            if (table.getEquationsSigns()
                    .get(equationsForNewVariables.get(i)).equals(Sign.GREATER_THAN) ||
                    table.getEquationsSigns()
                            .get(equationsForNewVariables.get(i)).equals(Sign.GREATER_THAN_OR_EQUAL_TO)) {

                copiedTable[equationsForNewVariables.get(i)][mainTable[0].length + i]
                        = GREATER_INEQUATION_NORMALIZER_COEFFICIENT;

            }

            table.getFunction().add(0.0);
        }

        table.setMainTable(copiedTable);

        table.setNumOfVariables(table.getNumOfVariables() + equationsForNewVariables.size());
    }

    private void changeInequationSignsToEqual(LPTable table) {
        for (int i = 0; i < table.getEquationsSigns().size(); i++) {
            table.getEquationsSigns().set(i, Sign.EQUAL);
        }
    }

}
