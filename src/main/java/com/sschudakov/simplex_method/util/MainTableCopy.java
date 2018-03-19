package com.sschudakov.simplex_method.util;

/**
 * Created by Semen Chudakov on 12.10.2017.
 */
public class MainTableCopy {
    public static void copyTable(double[][] from, double[][] to) {

        if (from.length > to.length) {
            throw new IllegalArgumentException("from length (" + from.length + ") is greater than to length " + to.length);
        }

        for (int i = 0; i < from.length; i++) {
            if (from[i].length > to[i].length) {
                throw new IllegalArgumentException("from[" + i + "] length (" + from[i].length
                        + ") is greater than to[" + i + "] length ("  + to[i].length + ")");
            }
        }


        for (int i = 0; i < from.length; i++) {
            for (int j = 0; j < from[i].length; j++) {
                to[i][j] = from[i][j];
            }
        }
    }
}