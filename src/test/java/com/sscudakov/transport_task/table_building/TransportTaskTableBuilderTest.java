package com.sscudakov.transport_task.table_building;

import com.sschudakov.transport_task.table_building.TransportTaskTableBuilder;
import org.junit.Test;

/**
 * Created by Semen Chudakov on 16.11.2017.
 */
public class TransportTaskTableBuilderTest {

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

    @Test
    public void buildTableTest() {
        TransportTaskTableBuilder builder = new TransportTaskTableBuilder();
        builder.buildTransportTaskTable(TASK);
    }
}