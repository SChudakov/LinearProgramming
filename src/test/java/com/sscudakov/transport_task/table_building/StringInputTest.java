package com.sscudakov.transport_task.table_building;

import com.sschudakov.transport_task.table.TransportTaskTable;
import com.sschudakov.transport_task.table_building.StringInput;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Created by Semen Chudakov on 14.11.2017.
 */
public class StringInputTest {

    private static final String NUM_OF_CONSUMERS_AND_PRODUCERS = "3 4 ";
    private static final String CONSUMERS_VECTOR = "78 37 53 ";
    private static final String PRODUCERS_VECTOR = "38 60 30 40 ";
    private static final String PRICES = "5 6 10 3 " +
            "6 4 7 2 " +
            "8 8 3 7 ";
    private static final String RESTRICTIONS = "37 20 7 21 " +
            "5 26 7 11 " +
            "5 20 25 10";

    @Ignore
    @Test
    public void inputValuesTest() {
        TransportTaskTable table = new TransportTaskTable();
        StringInput stringInput = new StringInput();
        stringInput.inputValues(table, NUM_OF_CONSUMERS_AND_PRODUCERS +
                CONSUMERS_VECTOR +
                PRODUCERS_VECTOR +
                PRICES +
                RESTRICTIONS);
//        System.out.println(NUM_OF_CONSUMERS_AND_PRODUCERS +
//                CONSUMERS_VECTOR +
//                PRODUCERS_VECTOR +
//                PRICES +
//                RESTRICTIONS);
        table.outputTable();

//        Scanner scanner = new Scanner(NUM_OF_CONSUMERS_AND_PRODUCERS +
//                CONSUMERS_VECTOR +
//                PRODUCERS_VECTOR +
//                PRICES +
//                RESTRICTIONS);
//        System.out.println(scanner.nextInt());
//        System.out.println(scanner.nextInt());
    }
}
