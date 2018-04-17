package com.sschudakov.lp.simplexmethod.input;

import com.sschudakov.lp.simplexmethod.enumerable.Sign;

class InputUtils {
    private static final String EQUAL = "=";
    private static final String GREATER_THAN_OR_EQUAL_TO = ">=";
    private static final String GREATER_THAN = ">";
    private static final String LESS_THAN_OR_EQUAL_TO = "<=";
    private static final String LESS_THAN = "<";

    /**
     * Converts a string to a {@link Sign}
     *
     * @param sign string to be converted
     * @return sign
     * @see Sign
     */
    public static Sign inputSign(String sign) {
        if (sign.equals(EQUAL)) {
            return Sign.EQUAL;
        }
        if (sign.equals(GREATER_THAN_OR_EQUAL_TO)) {
            return Sign.GREATER_THAN_OR_EQUAL_TO;
        }
        if (sign.equals(GREATER_THAN)) {
            return Sign.GREATER_THAN;
        }
        if (sign.equals(LESS_THAN_OR_EQUAL_TO)) {
            return Sign.LESS_THAN_OR_EQUAL_TO;
        }
        if (sign.equals(LESS_THAN)) {
            return Sign.LESS_THAN;
        }
        throw new IllegalArgumentException("Input sign is not available. Available sings are: = >= > < <=");
    }
}
