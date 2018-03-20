package com.sscudakov.transport_task.solving;

import com.sschudakov.task.TTTasks;
import com.sschudakov.transport_task.solving.TTSolver;
import com.sschudakov.transport_task.table.TTTable;
import com.sschudakov.transport_task.table_building.TTStringInput;
import com.sschudakov.transport_task.table_building.TTTableBuilder;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Created by Semen Chudakov on 23.11.2017.
 */
public class TTSolverTest {

    private static TTStringInput ttStringInput;
    private static TTTableBuilder ttTableBuilder;
    private static TTSolver ttSolver;
    private TTTable ttTable;

    @BeforeClass
    public static void initClass() {
        ttStringInput = new TTStringInput();
        ttTableBuilder = new TTTableBuilder();
        ttSolver = new TTSolver();
    }

    @Before
    public void init() {
        this.ttTable = new TTTable();
    }


    @Ignore
    @Test(timeout = 1000)
    public void solveFirstTaskTest() {
        ttStringInput.inputValues(this.ttTable, TTTasks.TASK_1);
        ttTableBuilder.buildTransportTaskTable(this.ttTable);
        ttSolver.solve(ttTable);
    }

    @Ignore
    @Test(timeout = 1000)
    public void solveSecondTaskTest() {
        ttStringInput.inputValues(this.ttTable, TTTasks.TASK_2);
        ttTableBuilder.buildTransportTaskTable(this.ttTable);
        ttSolver.solve(ttTable);
    }

    @Ignore
    @Test(timeout = 1000)
    public void solveThirdTaskTest() {
        ttStringInput.inputValues(this.ttTable, TTTasks.TASK_3);
        ttTableBuilder.buildTransportTaskTable(this.ttTable);
        ttSolver.solve(ttTable);
    }

    @Ignore
    @Test(timeout = 1000)
    public void solveFourthTaskTest() {
        ttStringInput.inputValues(this.ttTable, TTTasks.TASK_4);
        ttTableBuilder.buildTransportTaskTable(this.ttTable);
        ttSolver.solve(ttTable);
    }

    @Ignore
    @Test(timeout = 1000)
    public void solveFifthTaskTest() {
        ttStringInput.inputValues(this.ttTable, TTTasks.TASK_5);
        ttTableBuilder.buildTransportTaskTable(this.ttTable);
        ttSolver.solve(ttTable);
    }

    @Ignore
    @Test(timeout = 1000)
    public void solveSixthTaskTest() {
        ttStringInput.inputValues(this.ttTable, TTTasks.TASK_6);
        ttTableBuilder.buildTransportTaskTable(this.ttTable);
        ttSolver.solve(ttTable);
    }

    @Ignore
    @Test(timeout = 1000)
    public void solveSeventhTaskTest() {
        ttStringInput.inputValues(this.ttTable, TTTasks.TASK_7);
        ttTableBuilder.buildTransportTaskTable(this.ttTable);
        ttSolver.solve(ttTable);
    }

    @Ignore
    @Test(timeout = 1000)
    public void solveEighthTaskTest() {
        ttStringInput.inputValues(this.ttTable, TTTasks.TASK_8);
        ttTableBuilder.buildTransportTaskTable(this.ttTable);
        ttSolver.solve(ttTable);
    }

    @Ignore
    @Test(timeout = 1000)
    public void solveNinthTaskTest() {
        ttStringInput.inputValues(this.ttTable, TTTasks.TASK_9);
        ttTableBuilder.buildTransportTaskTable(this.ttTable);
        ttSolver.solve(ttTable);
    }

    @Ignore
    @Test(timeout = 1000)
    public void solveTenthTaskTest() {
        ttStringInput.inputValues(this.ttTable, TTTasks.TASK_10);
        ttTableBuilder.buildTransportTaskTable(this.ttTable);
        ttSolver.solve(ttTable);
    }
}
