package com.sschudakov.lp.simplexmethod.input;

import com.sschudakov.lp.simplexmethod.table.LPTable;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

public class LPStringInputTest {
    private static String LINEAR_TASK =
            "5 3" +
                    "1 -1 1 -3 2" +
                    "1 2 -1 2 1" +
                    "-1 -1 1 3 -2" +
                    "2 -1 3 -1 2 " +
                    "<= = >=" +
                    "8 10 4";
    private LPStringInput lpStringInput;
    private LPTable lpTable;

    private int expectedNumOfVariables = 5;
    private int expectedNumOfEquations = 3;
    private double[][] expectedMainTable = {
            {2.0, 1.0, -1.0, 2.0, 1.0},
            {-1.0, -1.0, 1.0, 3.0, -2.0},
            {2.0, -1.0, 3.0, -1.0, 2.0}
    };
    private List<Double> expectedFunction;

    @Before
    public void setUp() {
        this.lpStringInput = new LPStringInput();
        this.lpTable = new LPTable();
        expectedFunction.add(1.0);
        expectedFunction.add(-1.0);
        expectedFunction.add(1.0);
        expectedFunction.add(-3.0);
        expectedFunction.add(2.0);
    }

    @Ignore
    @Test
    public void inputLP() {


    }
}