package com.sscudakov.simplex_method.table_building;

import com.sschudakov.simplex_method.input.LPConsoleInput;
import com.sschudakov.simplex_method.table.LPTable;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Created by Semen Chudakov on 12.10.2017.
 */
public class InputValuesTest {

    private static final String LINEAR_TASK =
            "5 3" +
                    "1 -1 1 -3 2" +
                    "1 2 -1 2 1" +
                    "-1 -1 1 3 -2" +
                    "2 -1 3 -1 2 " +
                    "<= = >=" +
                    "8 10 4";

    @Ignore
    @Test
    public void testInputValues() {
        LPConsoleInput input = new LPConsoleInput();
        LPTable table = input.inputValues();
        table.outputTable();
    }
}
