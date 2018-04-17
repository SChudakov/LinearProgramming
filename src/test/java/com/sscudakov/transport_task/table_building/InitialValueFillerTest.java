package com.sscudakov.transport_task.table_building;

import com.sschudakov.lp.transporttask.table.TTTable;
import com.sschudakov.lp.transporttask.table_building.InitialValuesFiller;
import com.sschudakov.lp.transporttask.table_building.TTStringInput;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Created by Semen Chudakov on 14.11.2017.
 */
public class InitialValueFillerTest {

    private static final String NUM_OF_CONSUMERS_AND_PRODUCERS = "3 4 ";
    private static final String CONSUMERS_VECTOR = "78 37 53 ";
    private static final String PRODUCERS_VECTOR = "38 60 30 40 ";
    private static final String PRICES = "5 6 10 3 " +
            "6 4 7 2 " +
            "8 8 3 7 ";
    private static final String RESTRICTIONS = "37 20 7 21 " +
            "5 26 7 11 " +
            "5 20 25 10";

    private static final String TASK = NUM_OF_CONSUMERS_AND_PRODUCERS + CONSUMERS_VECTOR
            + PRODUCERS_VECTOR + PRICES + RESTRICTIONS;

    @Ignore
    @Test
    public void buildTableTest() {
        TTTable taskTable = new TTTable();
        TTStringInput input = new TTStringInput();
        InitialValuesFiller filler = new InitialValuesFiller();

        input.inputValues(taskTable, TASK);
        System.out.println("\nafter values inputILP\n");
        taskTable.outputTable();

        filler.fillInitialValues(taskTable);
        System.out.println("\nafter values filling\n");
        taskTable.outputTable();
    }
}
