package com.sschudakov.lp.simplexmethod.main;

import com.sschudakov.lp.simplexmethod.input.LPStringInput;
import com.sschudakov.lp.simplexmethod.solving.LPSolution;
import com.sschudakov.lp.simplexmethod.table.LPTable;
import com.sschudakov.lp.simplexmethod.solving.LPSolver;
import com.sschudakov.lp.simplexmethod.building.LPTableBuilder;
import com.sschudakov.lp.task.LPTasks;

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
        LPSolution lpSolution = solver.solveLP(lpTable);

        System.out.println("\nANSWER\n");

        System.out.println("Optimal value is reached on the vector: " + lpSolution.getSolutionVector());
        System.out.println("Where function value is: " + lpSolution.getFunctionValue());
    }
}
