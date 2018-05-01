package com.sschudakov.lp.simplexmethod.solving;

import com.sschudakov.lp.simplexmethod.exception.TableNotDualOptimalException;
import com.sschudakov.lp.simplexmethod.table.LPRestriction;
import com.sschudakov.lp.simplexmethod.table.LPSolution;
import com.sschudakov.lp.simplexmethod.table.LPTable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
                solutionVector,
                functionValue
        );
    }

    public LPSolution formSolution(Exception exception) {
        return new LPSolution(
                exception
        );
    }


    private List<Double> formSolutionVector(LPTable lpTable) {
        List<Double> restrictions = lpTable.getMainTable().stream().map(LPRestriction::getRightPartValue).collect(Collectors.toList());
        List<Double> result = new ArrayList<>();

        for (int i = 0; i < lpTable.getNumOfVariables(); i++) {
            if (hasVariable(lpTable.getMainTable(), i)) {
                Double variableValue = restrictions.get(tellBasicVariablePosition(lpTable.getMainTable(), i));
                result.add(variableValue);
            } else {
                result.add(0.0);
            }
        }

        return result;
    }

    private boolean hasVariable(List<LPRestriction> mainTable, int variable) {
        boolean result = false;
        for (LPRestriction lpRestriction : mainTable) {
            if (lpRestriction.getBasicVariable() == variable) {
                result = true;
            }
        }
        return result;
    }

    private Double calculateFunctionValue(List<Double> function, List<Double> vector) {
        Double result = 0.0D;
        for (int i = 0; i < function.size(); i++) {
            result += function.get(i) * vector.get(i);
        }
        return result;
    }

    private int tellBasicVariablePosition(List<LPRestriction> mainTable, int variable) {
        for (int i = 0; i < mainTable.size(); i++) {
            if (mainTable.get(i).getBasicVariable() == variable) {
                return i;
            }
        }
        throw new IllegalArgumentException("Vector " + mainTable + " has not variable " + variable);
    }

    private static void ensureIsOptimal(LPTable lpTable) {
        if (!lpTable.isDualOptimal()) {
            throw new TableNotDualOptimalException();
        }
    }
}
