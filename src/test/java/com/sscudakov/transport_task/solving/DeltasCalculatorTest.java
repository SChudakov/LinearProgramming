package com.sscudakov.transport_task.solving;

import com.sschudakov.transport_task.solving.DeltasCalculator;
import com.sschudakov.transport_task.solving.PotentialsCalculator;
import com.sschudakov.transport_task.table.TransportTableBasis;
import com.sschudakov.transport_task.table.TransportTaskTable;
import com.sschudakov.transport_task.table_building.BasisBuilder;
import com.sschudakov.transport_task.table_building.TransportTaskTableBuilder;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Created by Semen Chudakov on 20.11.2017.
 */
public class DeltasCalculatorTest {
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
    public void calculateDeltasTest() {
        TransportTaskTableBuilder builder = new TransportTaskTableBuilder();
        TransportTaskTable table = builder.buildTransportTaskTable(TASK);
        TransportTableBasis basis = BasisBuilder.buildBasis(table);

        PotentialsCalculator.calculatePotentials(table, basis);
        DeltasCalculator.calculateDeltas(table, basis);

        table.outputTable();
    }
}
