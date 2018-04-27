package com.sschudakov.lp.simplexmethod.building;

import com.sschudakov.lp.simplexmethod.table.LPTable;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * Created by Semen Chudakov on 16.10.2017.
 */
public class MFinder {

    public static double findM(LPTable table) {
        return max(
                maxOfConditionsCoefficients(table),
                maxOfRestrictions(table),
                maxOfFunction(table)
        ) + 1;
    }

    private static Double maxOfFunction(LPTable table) {
        return Collections.max(
                table.getFunction().stream().map(Math::abs).collect(Collectors.toList())
        );
    }

    private static Double maxOfConditionsCoefficients(LPTable table) {

        return Math.abs(
                Collections.max(
                        table.getMainTable().stream()
                                .map(r -> Math.abs(Collections.max(r.getCondition()))).collect(Collectors.toList())
                )
        );
    }

    private static Double maxOfRestrictions(LPTable table) {
        return Collections.max(
                table.getMainTable().stream()
                        .map(r -> Math.abs(r.getRightPartValue())).collect(Collectors.toList())
        );
    }

    private static Double max(Double... values) {
        return Collections.max(Arrays.asList(values));
    }
}
