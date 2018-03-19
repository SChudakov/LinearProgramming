package com.sschudakov.simplex_method.table_building;

import com.sschudakov.simplex_method.table.SimplexTable;

import java.util.Vector;

/**
 * Created by Semen Chudakov on 16.10.2017.
 */
public class MFinder {

    public static double findM(SimplexTable table) {
        return max(maxOfMainTable(table.getMainTable()),
                maxOfVector(table.getFunction()), maxOfVector(table.getRestrictionsVector())) + 1;
    }

    private static double maxOfMainTable(double[][] mainTable) {
        double result = Math.abs(mainTable[0][0]);
        for (double[] doubles : mainTable) {
            for (double aDouble : doubles) {
                if (result < Math.abs(aDouble)) {
                    result = Math.abs(aDouble);
                }
            }
        }
        return result;
    }

    private static double maxOfVector(Vector<Double> vector) {
        double result = Math.abs(vector.get(0));
        for (Double aDouble : vector) {
            if (result < Math.abs(aDouble)) {
                result = Math.abs(aDouble);
            }
        }
        return result;
    }

    private static double max(double... values) {
        double result = Math.abs(values[0]);
        for (double value : values) {
            if (result < Math.abs(value)) {
                result = Math.abs(value);
            }
        }
        return result;
    }

}
