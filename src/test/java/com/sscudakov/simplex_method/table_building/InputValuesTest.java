package com.sscudakov.simplex_method.table_building;

import com.sschudakov.simplex_method.input.ConsoleValuesInput;
import com.sschudakov.simplex_method.table.SimplexTable;
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

    @Test
    public void testInputValues() {
        ConsoleValuesInput input = new ConsoleValuesInput();
        SimplexTable table = input.inputValues();
        table.outputTable();
    }
}
