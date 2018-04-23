package com.sschudakov.lp.simplexmethod.solver;

import com.sschudakov.lp.simplexmethod.exception.TableNotDualOptimalException;
import com.sschudakov.lp.simplexmethod.table.LPTable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Semen Chudakov on 20.10.2017.
 */
public class LPSolutionFormer {

    public LPSolution formSolution(LPTable lpTable) {
        ensureIsOptimal(lpTable);

        List<Double> solutionVector = formSolutionVector(lpTable);
        Double functionValue;
        if (lpTable.getModified()) {
            functionValue = -calculateFunctionValue(lpTable.getFunction(), solutionVector);
        } else {
            functionValue = calculateFunctionValue(lpTable.getFunction(), solutionVector);
        }

        return new LPSolution(
                lpTable,
                solutionVector,
                functionValue
        );
    }

    public LPSolution formSolution(LPTable lpTable, Exception exception) {
        return new LPSolution(
                lpTable,
                exception
        );
    }


    private List<Double> formSolutionVector(LPTable lpTable) {

        List<Integer> basicVariables = lpTable.getBasicVariables();
        List<Double> restrictions = lpTable.getRestrictionsVector();
        List<Double> result = new ArrayList<>();

        for (int i = 0; i < lpTable.getNumOfVariables(); i++) {
            if (hasVariable(basicVariables, i)) {
                Double variableValue = restrictions.get(tellBasicVariablePosition(basicVariables, i));
                Double roundedValue = (double) Math.round(variableValue);
                result.add(roundedValue);
            } else {
                result.add(0.0);
            }
        }

        return result;
    }

    private boolean hasVariable(List<Integer> basicVariables, int variable) {
        for (Integer basicVariable : basicVariables) {
            if (basicVariable == variable) {
                return true;
            }
        }
        return false;
    }

    private Double calculateFunctionValue(List<Double> function, List<Double> vector) {
        Double result = 0.0D;
        for (int i = 0; i < function.size(); i++) {
            result += function.get(i) * vector.get(i);
        }
        return result;
    }

    private int tellBasicVariablePosition(List<Integer> basicVariable, int variable) {
        for (int i = 0; i < basicVariable.size(); i++) {
            if (basicVariable.get(i) == variable) {
                return i;
            }
        }
        throw new IllegalArgumentException("Vector " + basicVariable + " has not variable " + variable);
    }

    private static void ensureIsOptimal(LPTable lpTable) {
        if (!lpTable.isDualOptimal()) {
            throw new TableNotDualOptimalException();
        }
    }
}
