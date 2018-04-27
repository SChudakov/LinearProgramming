package com.sschudakov.lp.branchandbound.building;

import com.sschudakov.lp.branchandbound.table.ILPTable;
import com.sschudakov.lp.simplexmethod.enumerable.Sign;
import com.sschudakov.lp.simplexmethod.table.LPRestriction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

class CuttingRestrictionsBuilder {
    public List<LPRestriction> buildCuttingRestrictions(ILPTable ilpTable, Integer basicVariable, Double basicVariableValue) {
        List<Double> restrictionsRightParts = convertToDoubleArray(splitRestrictionRightPart(basicVariableValue));

        List<Double> firstCondition = formCondition(ilpTable, basicVariable);
        List<Double> secondCondition = formCondition(ilpTable, basicVariable);

        LPRestriction firstRestriction = new LPRestriction(
                firstCondition,
                Sign.LESS_THAN_OR_EQUAL_TO,
                Collections.min(restrictionsRightParts),
                null
        );
        LPRestriction secondRestriction = new LPRestriction(
                secondCondition,
                Sign.GREATER_THAN_OR_EQUAL_TO,
                Collections.max(restrictionsRightParts),
                null
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
