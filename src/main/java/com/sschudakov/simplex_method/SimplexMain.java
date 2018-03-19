package com.sschudakov.simplex_method;

import com.sschudakov.simplex_method.table.SimplexTable;
import com.sschudakov.simplex_method.solver.SimplexTableSolver;
import com.sschudakov.simplex_method.table_building.SimplexTableBuilder;

public class SimplexMain {

    private static final String LINEAR_TASK_1 = // no solutions
            "5 3" +
                    "1 -1 1 -3 2" +
                    "min" +
                    "1 2 -1 2 1" +
                    "-1 -1 1 -3 -2" +
                    "2 -1 3 -1 2" +
                    "<= = >=" +
                    "8 10 4";
    private static final String LINEAR_TASK_2 =
            "2 3" +
                    "2 1" +
                    "min" +
                    "3 1" +
                    "4 3" +
                    "1 2" +
                    ">= >= <=" +
                    "3 6 3";
    private static final String LINEAR_TASK_3 =
            "2 2" +
                    "4 2" +
                    "min" +
                    "1 2" +
                    "1 -1" +
                    ">= >=" +
                    "2 3";
    private static final String LINEAR_TASK_4 =
            "4 3" +
                    "0 3 5 8" +
                    "min" +
                    "-2 0 0 -2" +
                    "1 1 -1 -2" +
                    "0 0 -2 1" +
                    "<= <= <=" +
                    "-8 -6 6";
    private static final String LINEAR_TASK_5 =
            "3 4" +
                    "1 4 0" +
                    "min" +
                    "-2 -3 -4" +
                    "-5 1 -2" +
                    "1 2 -1" +
                    "1 4 -2" +
                    "<= <= <= <=" +
                    "-20 -12 2 1";
    private static final String LINEAR_TASK_6 = //18.0
            "3 3" +
                    "15 7 12" +
                    "min" +
                    "-1 -1 -2" +
                    "-3 -1 -1" +
                    "-5 -1 -4" +
                    "<= <= <=" +
                    "-2 -3 -1";
    private static final String LINEAR_TASK_7 = // 25.764
            "3 3" +
                    "3 5 4" +
                    "min" +
                    "-4 -6 -5" +
                    "-2 -2 -3" +
                    "2 -3 4" +
                    "<= <= <=" +
                    "-21 -15 6";
    private static final String LINEAR_TASK_8 = //49.9090909090
            "3 3" +
                    "9 10 6" +
                    "min" +
                    "-8 -6 -2" +
                    "2 7 -6" +
                    "-2 4 -6" +
                    "<= <= <=" +
                    "-28 42 -34";
    private static final String LINEAR_TASK_9 = //20.0
            "8 3" +
                    "0 0 0 0 0 0 1 1" +
                    "min" +
                    "1 1 1 1 0 0 0 0" +
                    "2 1 -1 0 -1 0 1 0" +
                    "0 -1 1 0 0 -1 0 1" +
                    "= = =" +
                    "40 10 10";
    private static final String LINEAR_TASK_10 =
            "2 3" +
                    "6 12" +
                    "min" +
                    "-2 3" +
                    "4 -3" +
                    "3 1" +
                    "<= <= >=" +
                    "6 12 3";
    private static final String LINEAR_TASK_11 = // no solution
            "5 3" +
                    "1 -1 1 -3 2" +
                    "min" +
                    "1 2 -1 2 1" +
                    "-1 -1 1 -3 -2" +
                    "2 -1 3 -1 2" +
                    ">= = <=" +
                    "8 10 4";
    private static final String LINEAR_TASK_12 = // 4.571
            "3 2" +
                    "6 5 2" +
                    "min" +
                    "2 1 1" +
                    "1 5 4" +
                    ">= <=" +
                    "2 6";
    private static final String LINEAR_TASK_13 = //
            "2 3" +
                    "78 52" +
                    "min" +
                    "6 2" +
                    "-10 14" +
                    "11 -1" +
                    ">= >= >=" +
                    "9 13 6";
    private static final String LINEAR_TASK_14 = //
            "2 3" +
                    "6 4" +
                    "min" +
                    "2 1" +
                    "3 2" +
                    "-1 -1" +
                    ">= >= >=" +
                    "3 1 6";

    public static void main(String[] args) {

        SimplexTableBuilder builder = new SimplexTableBuilder();
        SimplexTable table = builder.buildSimplexTable();
        SimplexTableSolver solver = new SimplexTableSolver(table);
        solver.solveSimplexTable();

    }
}
