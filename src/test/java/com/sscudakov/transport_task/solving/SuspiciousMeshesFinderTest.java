package com.sscudakov.transport_task.solving;

import com.sschudakov.transporttask.solving.TTDeltasCalculator;
import com.sschudakov.transporttask.solving.TTPotentialsCalculator;
import com.sschudakov.transporttask.solving.SuspiciousMeshesFinder;
import com.sschudakov.transporttask.table.TTTableNode;
import com.sschudakov.transporttask.table.TTBasis;
import com.sschudakov.transporttask.table.TTTable;
import com.sschudakov.transporttask.table_building.TTBasisBuilder;
import com.sschudakov.transporttask.table_building.TTStringInput;
import com.sschudakov.transporttask.table_building.TTTableBuilder;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

/**
 * Created by Semen Chudakov on 23.11.2017.
 */
public class SuspiciousMeshesFinderTest {
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
    public void findSuspiciousMeshesTest() {
        TTTable ttTable = new TTTable();

        TTStringInput ttStringInput = new TTStringInput();
        ttStringInput.inputValues(ttTable, TASK);

        TTTableBuilder builder = new TTTableBuilder();
        TTTable table = builder.buildTransportTaskTable(ttTable);

        TTBasisBuilder ttBasisBuilder = new TTBasisBuilder();
        TTBasis basis = ttBasisBuilder.buildBasis(table);

        TTPotentialsCalculator ttPotentialsCalculator = new TTPotentialsCalculator();
        ttPotentialsCalculator.calculatePotentials(table, basis);

        TTDeltasCalculator ttDeltasCalculator = new TTDeltasCalculator();
        ttDeltasCalculator.calculateDeltas(table, basis);

        List<TTTableNode> suspiciousNodes = SuspiciousMeshesFinder.findSuspiciousMeshes(table);

        System.out.println(suspiciousNodes);
    }
}
