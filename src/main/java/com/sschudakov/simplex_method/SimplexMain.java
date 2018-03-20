package com.sschudakov.simplex_method;

import com.sschudakov.simplex_method.input.LPStringInput;
import com.sschudakov.simplex_method.table.LPTable;
import com.sschudakov.simplex_method.solver.LPSolver;
import com.sschudakov.simplex_method.table_building.LPTableBuilder;
import com.sschudakov.task.LPTasks;

public class SimplexMain {
    public static void main(String[] args) {
        LPTable lpTable = new LPTable();

        LPStringInput lpStringInput = new LPStringInput();
        lpStringInput.inputLP(lpTable, LPTasks.LINEAR_TASK_2);
        System.out.println("\nafter values input LP\n");
        lpTable.outputTable();

        LPTableBuilder builder = new LPTableBuilder();
        builder.buildSimplexTable(lpTable);

        LPSolver solver = new LPSolver();
        solver.solveSimplexTable(lpTable);
    }
}
