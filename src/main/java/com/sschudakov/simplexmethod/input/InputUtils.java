package com.sschudakov.simplexmethod.input;

import com.sschudakov.simplexmethod.enumerable.Sign;

public class InputUtils {

    /**
     * Converts a string to a {@link Sign}
     *
     * @param sign string to be converted
     * @return sign
     * @see Sign
     */
    public static Sign inputSign(String sign) {
        if (sign.equals("=")) {
            return Sign.EQUAL;
        }
        if (sign.equals(">=")) {
            return Sign.GREATER_THAN_OR_EQUAL_TO;
        }
        if (sign.equals(">")) {
            return Sign.GREATER_THAN;
        }
        if (sign.equals("<=")) {
            return Sign.LESS_THAN_OR_EQUAL_TO;
        }
        if (sign.equals("<")) {
            return Sign.LESS_THAN;
        }

        throw new IllegalArgumentException("Input sign is not available. Available sings are: = >= > < <=");
    }
}
