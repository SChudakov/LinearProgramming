package com.sschudakov.lp.branchandbound.util;

public class Utils {

    private static final Double PRECISION = 0.0000001;

    public static boolean isNonInteger(Double number) {
        return Math.abs(Math.ceil(number) - number) > PRECISION
                && Math.abs(Math.ceil(number) - number - 1) > PRECISION;
    }
}
