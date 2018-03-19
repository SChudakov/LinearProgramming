package com.sscudakov.transport_task.solving;

import com.sschudakov.transport_task.Tasks;
import com.sschudakov.transport_task.solving.TransportTaskSolver;
import com.sschudakov.transport_task.table.TransportTaskTable;
import com.sschudakov.transport_task.table_building.TransportTaskTableBuilder;
import org.junit.Test;

/**
 * Created by Semen Chudakov on 23.11.2017.
 */
public class TransportTaskSolverTest {


    @Test(timeout = 10_000)
    public void solveSeventhTaskTest() {
        TransportTaskTableBuilder builder = new TransportTaskTableBuilder();
        TransportTaskTable table = builder.buildTransportTaskTable(Tasks.TASK_7);

        TransportTaskSolver.solve(table);
    }

    @Test(timeout = 10_000)
    public void solveFirstTaskTest() {
        TransportTaskTableBuilder builder = new TransportTaskTableBuilder();
        TransportTaskTable table = builder.buildTransportTaskTable(Tasks.TASK_1);

        TransportTaskSolver.solve(table);
    }

    @Test(timeout = 10_000)
    public void solveSecondTaskTest() {
        TransportTaskTableBuilder builder = new TransportTaskTableBuilder();
        TransportTaskTable table = builder.buildTransportTaskTable(Tasks.TASK_2);

        TransportTaskSolver.solve(table);
    }

    @Test(timeout = 10_000)
    public void solveThirdTaskTest() {
        TransportTaskTableBuilder builder = new TransportTaskTableBuilder();
        TransportTaskTable table = builder.buildTransportTaskTable(Tasks.TASK_3);

        TransportTaskSolver.solve(table);
    }

    @Test(timeout = 10_000)
    public void solveFourthTaskTest() {
        TransportTaskTableBuilder builder = new TransportTaskTableBuilder();
        TransportTaskTable table = builder.buildTransportTaskTable(Tasks.TASK_4);

        TransportTaskSolver.solve(table);
    }

    @Test(timeout = 10_000)
    public void solveFifthTaskTest() {
        TransportTaskTableBuilder builder = new TransportTaskTableBuilder();
        TransportTaskTable table = builder.buildTransportTaskTable(Tasks.TASK_5);

        TransportTaskSolver.solve(table);
    }

    @Test(timeout = 10_000)
    public void solveSixthTaskTest() {
        TransportTaskTableBuilder builder = new TransportTaskTableBuilder();
        TransportTaskTable table = builder.buildTransportTaskTable(Tasks.TASK_6);

        TransportTaskSolver.solve(table);
    }

    @Test(timeout = 10_000)
    public void solveEighthTaskTest() {
        TransportTaskTableBuilder builder = new TransportTaskTableBuilder();
        TransportTaskTable table = builder.buildTransportTaskTable(Tasks.TASK_8);

        TransportTaskSolver.solve(table);
    }

    @Test(timeout = 10_000)
    public void solveNinthTaskTest() {
        TransportTaskTableBuilder builder = new TransportTaskTableBuilder();
        TransportTaskTable table = builder.buildTransportTaskTable(Tasks.TASK_9);

        TransportTaskSolver.solve(table);
    }

    @Test(timeout = 10_000)
    public void solveTenthTaskTest() {
        TransportTaskTableBuilder builder = new TransportTaskTableBuilder();
        TransportTaskTable table = builder.buildTransportTaskTable(Tasks.TASK_10);

        TransportTaskSolver.solve(table);
    }
}
