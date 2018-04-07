package com.sschudakov.simplex_method.solver;

import com.sschudakov.simplex_method.exception.TableNotDualOptimalException;
import com.sschudakov.simplex_method.table.LPTable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Semen Chudakov on 20.10.2017.
 */
public class AnswerFormer {

    public static LPSolution formSolution(LPTable table) {
        if (!table.isDualOptimal()) {
            throw new TableNotDualOptimalException();
        }

        List<Double> solutionVector = formSolutionVector(table);
        return new LPSolution(
                solutionVector,
                calculateFunctionValue(table.getFunction(), solutionVector)
        );
    }

    private static List<Double> formSolutionVector(LPTable table) {

        List<Integer> basicVariables = table.getBasicVariables();
        List<Double> restrictions = table.getRestrictionsVector();
        List<Double> result = new ArrayList<>();

        for (int i = 0; i < table.getNumOfVariables(); i++) {
            if (hasVariable(basicVariables, i)) {
                result.add(restrictions.get(tellBasicVariablePosition(basicVariables, i)));
            } else {
                result.add(0.0);
            }
        }


        return result;
    }

    private static boolean hasVariable(List<Integer> basicVariables, int variable) {
        for (Integer basicVariable : basicVariables) {
            if (basicVariable == variable) {
                return true;
            }
        }
        return false;
    }

    private static double calculateFunctionValue(List<Double> function, List<Double> vector) {
        double result = 0;
        for (int i = 0; i < function.size(); i++) {
            result += function.get(i) * vector.get(i);
        }
        return result;
    }

    private static int tellBasicVariablePosition(List<Integer> basicVariable, int variable) {
        for (int i = 0; i < basicVariable.size(); i++) {
            if (basicVariable.get(i) == variable) {
                return i;
            }
        }
        throw new IllegalArgumentException("Vector " + basicVariable.toString() + " hasn`t variable " + variable);
    }
}
