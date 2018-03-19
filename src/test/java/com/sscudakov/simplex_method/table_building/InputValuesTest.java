package com.sscudakov.simplex_method.table_building;

import com.sschudakov.simplex_method.table.SimplexTable;
import com.sschudakov.simplex_method.table_building.input.ConsoleValuesInput;
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

        SimplexTable table = new SimplexTable();
        ConsoleValuesInput input = new ConsoleValuesInput();

        input.inputValues(table);

        table.outputTable();

    }
}
