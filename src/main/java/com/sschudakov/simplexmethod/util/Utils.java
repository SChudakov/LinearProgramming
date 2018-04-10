package com.sschudakov.simplexmethod.util;

/**
 * Created by Semen Chudakov on 12.10.2017.
 */
public class Utils {
    public static void copyTable(double[][] from, double[][] to) {

        if (from.length > to.length) {
            throw new IllegalArgumentException("from length (" + from.length + ") is greater than to length " + to.length);
        }

        for (int i = 0; i < from.length; i++) {
            if (from[i].length > to[i].length) {
                throw new IllegalArgumentException("from[" + i + "] length (" + from[i].length
                        + ") is greater than to[" + i + "] length (" + to[i].length + ")");
            }
        }


        for (int i = 0; i < from.length; i++) {
            System.arraycopy(from[i], 0, to[i], 0, from[i].length);
        }
    }

    public static double[][] copyTable(double[][] from) {
        double[][] result = createEmptyTable(from);
        copyTable(from, result);
        return result;
    }


    private static double[][] createEmptyTable(double[][] table) {
        double[][] result = new double[table.length][];
        for (int i = 0; i < table.length; i++) {
            result[i] = new double[table[i].length];
        }
        return result;
    }
}
