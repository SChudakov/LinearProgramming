package com.sschudakov.branchandbound.building;

import com.sschudakov.branchandbound.table.ILPTable;
import com.sschudakov.simplexmethod.enumerable.Sign;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

class CuttingRestrictionsBuilder {
    public List<LPRestriction> buildCuttingRestrictions(ILPTable ilpTable, Integer basicVariableIndex) {
        Double variableValue = ilpTable.getRestrictionsVector().get(basicVariableIndex);
        List<Double> restrictionsRightParts = convertToDoubleArray(splitRestrictionRightPart(variableValue));

        Integer basicVariableNumber = ilpTable.getBasicVariables().get(basicVariableIndex);
        List<Double> firstCondition = formCondition(ilpTable, basicVariableNumber);
        List<Double> secondCondition = formCondition(ilpTable, basicVariableNumber);

        LPRestriction firstRestriction = new LPRestriction(
                firstCondition,
                Sign.LESS_THAN_OR_EQUAL_TO,
                Collections.min(restrictionsRightParts)
        );
        LPRestriction secondRestriction = new LPRestriction(
                secondCondition,
                Sign.GREATER_THAN_OR_EQUAL_TO,
                Collections.max(restrictionsRightParts)
        );

        List<LPRestriction> result = new ArrayList<>();
        result.add(firstRestriction);
        result.add(secondRestriction);

        return result;
    }

    private List<Double> formCondition(ILPTable ilpTable, Integer basicVariableNumber) {
        List<Double> result = new ArrayList<>();
        for (int i = 0; i < ilpTable.getFunction().size(); i++) {
            result.add(0.0);
        }
        result.set(basicVariableNumber, 1.0);
        return result;
    }

    private List<Integer> splitRestrictionRightPart(Double restrictionRightPart) {
        List<Integer> result = new ArrayList<>();
        result.add(restrictionRightPart.intValue());
        result.add(restrictionRightPart.intValue() + 1);
        return result;
    }

    private List<Double> convertToDoubleArray(List<Integer> integers) {
        return integers.stream().map(Integer::doubleValue).collect(Collectors.toList());
    }
}
