package com.sschudakov.simplex_method.util;

import com.sschudakov.simplex_method.table.SimplexTable;
import com.sschudakov.simplex_method.exception.TableNotOptimalException;

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

        Vector<Double> answerVector = formAnswerVector(table);

        System.out.println("Optimal value is reached on the vector: " + answerVector.toString());
        System.out.println("Where function value is: " + findFunctionValue(table.getFunction(), answerVector));


    }

    public static Vector<Double> formAnswerVector(SimplexTable table) {

        Vector<Integer> basicVariables = table.getBasicVariables();
        Vector<Double> restrictions = table.getRestrictionsVector();
        Vector<Double> result = new Vector<>();

        for (int i = 0; i < table.getNumOfVariables(); i++) {
            if (hasVariable(basicVariables, i)) {
                result.add(restrictions.get(tellBasicVariablePosition(basicVariables, i)));
            } else {
                result.add(0.0);
            }
        }


        return result;
    }

    private static boolean hasVariable(Vector<Integer> basicVariables, int variable) {
        for (Integer basicVariable : basicVariables) {
            if (basicVariable == variable) {
                return true;
            }
        }
        return false;
    }

    private static double findFunctionValue(Vector<Double> function, Vector<Double> vector) {
        double result = 0;
        for (int i = 0; i < function.size(); i++) {
            result += function.get(i) * vector.get(i);
        }
        return result;
    }

    private static int tellBasicVariablePosition(Vector<Integer> basicVariable, int variable) {
        for (int i = 0; i < basicVariable.size(); i++) {
            if (basicVariable.get(i) == variable) {
                return i;
            }
        }
        throw new IllegalArgumentException("Vector " + basicVariable.toString() + " hasn`t variable " + variable);
    }
}
