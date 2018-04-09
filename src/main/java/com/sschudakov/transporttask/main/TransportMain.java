package com.sschudakov.transporttask.main;

import com.sschudakov.task.TTTasks;
import com.sschudakov.transporttask.solving.TTSolver;
import com.sschudakov.transporttask.table.TTTable;
import com.sschudakov.transporttask.table_building.TTStringInput;
import com.sschudakov.transporttask.table_building.TTTableBuilder;

public class TransportMain {

    public static void main(String[] args) {
        TTTable ttTable = new TTTable();

        TTStringInput ttStringInput = new TTStringInput();
        ttStringInput.inputValues(ttTable, TTTasks.TASK_7);
        System.out.println("\nafter values inputILP\n");
        ttTable.outputTable();

        TTTableBuilder builder = new TTTableBuilder();
        builder.buildTransportTaskTable(ttTable);


        TTSolver ttSolver = new TTSolver();
        ttSolver.solve(ttTable);
    }
}
