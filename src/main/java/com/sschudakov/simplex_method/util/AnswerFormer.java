package com.sschudakov.simplex_method.util;

import com.sschudakov.simplex_method.table.SimplexTable;
import com.sschudakov.simplex_method.exception.TableNotOptimalException;

import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by Semen Chudakov on 20.10.2017.
 */
public class AnswerFormer {

    public static void formAnswer(SimplexTable table) {
        if (!table.isDualOptimal()) {
            throw new TableNotOptimalException();
        }

        System.out.println("\nANSWER\n");

        ArrayList<Double> answerVector = formAnswerVector(table);

        System.out.println("Optimal value is reached on the vector: " + answerVector.toString());
        System.out.println("Where function value is: " + findFunctionValue(table.getFunction(), answerVector));


    }

    public static ArrayList<Double> formAnswerVector(SimplexTable table) {

        ArrayList<Integer> basicVariables = table.getBasicVariables();
        ArrayList<Double> restrictions = table.getRestrictionsVector();
        ArrayList<Double> result = new ArrayList<>();

        for (int i = 0; i < table.getNumOfVariables(); i++) {
            if (hasVariable(basicVariables, i)) {
                result.add(restrictions.get(tellBasicVariablePosition(basicVariables, i)));
            } else {
                result.add(0.0);
            }
        }


        return result;
    }

    private static boolean hasVariable(ArrayList<Integer> basicVariables, int variable) {
        for (Integer basicVariable : basicVariables) {
            if (basicVariable == variable) {
                return true;
            }
        }
        return false;
    }

    private static double findFunctionValue(ArrayList<Double> function, ArrayList<Double> vector) {
        double result = 0;
        for (int i = 0; i < function.size(); i++) {
            result += function.get(i) * vector.get(i);
        }
        return result;
    }

    private static int tellBasicVariablePosition(ArrayList<Integer> basicVariable, int variable) {
        for (int i = 0; i < basicVariable.size(); i++) {
            if (basicVariable.get(i) == variable) {
                return i;
            }
        }
        throw new IllegalArgumentException("Vector " + basicVariable.toString() + " hasn`t variable " + variable);
    }
}
