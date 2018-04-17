package com.sschudakov.lp.branchandbound.util;

public class Utils {
    public static boolean isNonInteger(Double number) {
        return number.intValue() != number;
    }
}
