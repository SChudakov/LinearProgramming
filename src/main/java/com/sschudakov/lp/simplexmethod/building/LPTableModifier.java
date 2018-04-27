package com.sschudakov.lp.simplexmethod.building;

import com.sschudakov.lp.simplexmethod.enumerable.Sign;
import com.sschudakov.lp.simplexmethod.enumerable.TaskType;
import com.sschudakov.lp.simplexmethod.table.LPRestriction;
import com.sschudakov.lp.simplexmethod.table.LPTable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Semen Chudakov on 12.10.2017.
 */
public class LPTableModifier {

    private static final Double LESS_THAN_INEQUATION_NORMALIZER_COEFFICIENT = 1.0;
    private static final Double GREATER_INEQUATION_NORMALIZER_COEFFICIENT = -1.0;

    public void modifyTable(LPTable table, TaskType toType) {
        modifyFunctionType(table, toType);
        modifyInequationsToEquations(table);
    }

    private void modifyFunctionType(LPTable table, TaskType toType) {

        TaskType tableTaskType = table.getTaskType();

        if (!tableTaskType.equals(toType)) {
            changeFunctionCoefficients(table.getFunction());
            table.setModified(true);
        } else {
            table.setModified(false);
        }

        table.setTaskType(toType);
    }

    private void changeFunctionCoefficients(List<Double> function) {
        for (int i = 0; i < function.size(); i++) {
            function.set(i, -function.get(i));
        }
    }

    private void modifyInequationsToEquations(LPTable table) {
        List<LPRestriction> mainTable = table.getMainTable();
        List<Integer> equationsForNewVariables = new ArrayList<>();

        for (int i = 0; i < mainTable.size(); i++) {
            if (mainTable.get(i).getRightPartValue() > 0 && !mainTable.get(i).getSign().equals(Sign.EQUAL)) {
                reverseEquationSign(table, i);
            }
        }
        for (int i = 0; i < table.getMainTable().size(); i++) {
            if (!mainTable.get(i).getSign().equals(Sign.EQUAL)) {
                equationsForNewVariables.add(i);
            }
        }

        addVariables(table, equationsForNewVariables);
        changeInequationSignsToEqual(table);
        table.setNumOfInitialVariables(
                table.getNumOfInitialVariables() + equationsForNewVariables.size()//!!!!!!!!!!!!!!!!!!!!!!!!!!!
        );
    }

    private void reverseEquationSign(LPTable table, int equation) {

        List<LPRestriction> mainTable = table.getMainTable();

        for (int i = 0; i < mainTable.get(equation).getCondition().size(); i++) {
            Double recountedValue = -mainTable.get(equation).getCondition().get(i);
            mainTable.get(equation).getCondition().set(
                    i,
                    recountedValue);
        }

        mainTable.get(equation).setRightPartValue(
                -mainTable.get(equation).getRightPartValue()
        );

        if (mainTable.get(equation).getSign().equals(Sign.GREATER_THAN)) {
            mainTable.get(equation).setSign(Sign.LESS_THAN);
        } else {
            if (mainTable.get(equation).getSign().equals(Sign.GREATER_THAN_OR_EQUAL_TO)) {
                mainTable.get(equation).setSign(Sign.LESS_THAN_OR_EQUAL_TO);
            } else {
                if (mainTable.get(equation).getSign().equals(Sign.LESS_THAN)) {
                    mainTable.get(equation).setSign(Sign.GREATER_THAN);
                } else {
                    if (mainTable.get(equation).getSign().equals(Sign.LESS_THAN_OR_EQUAL_TO)) {
                        mainTable.get(equation).setSign(Sign.GREATER_THAN_OR_EQUAL_TO);
                    }
                }
            }
        }

    }

    private void addVariables(LPTable table, List<Integer> equationsForNewVariables) {

        int numOfNewVariables = equationsForNewVariables.size();

        List<LPRestriction> mainTable = table.getMainTable();

        //make main table larger
        for (LPRestriction lpRestriction : mainTable) {
            for (int i = 0; i < equationsForNewVariables.size(); i++) {
                lpRestriction.getCondition().add(0.0);
            }
        }

        for (int i = 0; i < numOfNewVariables; i++) {
            if (mainTable.get(equationsForNewVariables.get(i)).getSign().equals(Sign.LESS_THAN) ||
                    mainTable.get(equationsForNewVariables.get(i)).getSign().equals(Sign.LESS_THAN_OR_EQUAL_TO)) {

                List<Double> condition = mainTable.get(equationsForNewVariables.get(i)).getCondition();

                condition.set(condition.size() - equationsForNewVariables.size() + i, LESS_THAN_INEQUATION_NORMALIZER_COEFFICIENT);
            }
            if (mainTable.get(equationsForNewVariables.get(i)).getSign().equals(Sign.GREATER_THAN) ||
                    mainTable.get(equationsForNewVariables.get(i)).getSign().equals(Sign.GREATER_THAN_OR_EQUAL_TO)) {

                List<Double> condition = mainTable.get(equationsForNewVariables.get(i)).getCondition();

                condition.set(condition.size() - equationsForNewVariables.size() + i, GREATER_INEQUATION_NORMALIZER_COEFFICIENT);

            }

            table.getFunction().add(0.0);
        }
        table.setNumOfVariables(table.getNumOfVariables() + equationsForNewVariables.size());
    }

    private void changeInequationSignsToEqual(LPTable table) {
        for (int i = 0; i < table.getMainTable().size(); i++) {
            table.getMainTable().get(i).setSign(Sign.EQUAL);
        }
    }
}
