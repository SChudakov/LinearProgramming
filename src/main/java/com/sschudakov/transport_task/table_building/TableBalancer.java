package com.sschudakov.transport_task.table_building;

import com.sschudakov.transport_task.table.TransportTaskTable;
import com.sschudakov.transport_task.table.TransportTaskTableMesh;
import com.sschudakov.transport_task.util.MFinder;

/**
 * Created by Semen Chudakov on 05.12.2017.
 */
public class TableBalancer {

    public static void balanceTable(TransportTaskTable table) {
        if (TransportTaskUtils.tellConsumersValuesSum(table) <
                TransportTaskUtils.tellProducersValuesSum(table)) {
            addBalancingConsumer(table);
        }
        if (TransportTaskUtils.tellProducersValuesSum(table) <
                TransportTaskUtils.tellConsumersValuesSum(table)) {
            addBalancingProducer(table);
        }
    }

    private static void addBalancingConsumer(TransportTaskTable table) {
        TransportTaskTableMesh[][] mainTable = table.getMainTable();
        TransportTaskTableMesh[][] newTable = new TransportTaskTableMesh[mainTable.length + 1][mainTable[0].length];
        int numOfConsumers = table.getNumOfConsumers();
        int numOfProducers = table.getNumOfProducers();


        TransportTaskUtils.copyTable(mainTable, newTable);

        Integer mValue = MFinder.findM(table);

        table.getConsumersVector().add(TransportTaskUtils.tellProducersValuesSum(table) -
                TransportTaskUtils.tellConsumersValuesSum(table));
        table.setNumOfConsumers(numOfConsumers + 1);
        table.setMainTable(newTable);

        initializeAddedConsumer(table);
    }

    private static void initializeAddedConsumer(TransportTaskTable table) {
        TransportTaskTableMesh[][] mainTable = table.getMainTable();
        int numOfConsumers = table.getNumOfConsumers();
        int numOfProducers = table.getNumOfProducers();

        Integer mValue = MFinder.findM(table);

        for (int i = 0; i < numOfProducers; i++) {
            mainTable[numOfConsumers - 1][i] = new TransportTaskTableMesh(0, mValue);
        }
    }

    private static void addBalancingProducer(TransportTaskTable table) {
        TransportTaskTableMesh[][] mainTable = table.getMainTable();
        TransportTaskTableMesh[][] newTable = new TransportTaskTableMesh[mainTable.length][mainTable[0].length + 1];
        int numOfConsumers = table.getNumOfConsumers();
        int numOfProducers = table.getNumOfProducers();


        TransportTaskUtils.copyTable(mainTable, newTable);

        table.getProducersVector().add(TransportTaskUtils.tellConsumersValuesSum(table) -
                TransportTaskUtils.tellProducersValuesSum(table));
        table.setNumOfProducers(numOfProducers + 1);
        table.setMainTable(newTable);

        initializeAddedProducer(table);
    }

    private static void initializeAddedProducer(TransportTaskTable table) {
        TransportTaskTableMesh[][] mainTable = table.getMainTable();
        int numOfConsumers = table.getNumOfConsumers();
        int numOfProducers = table.getNumOfProducers();

        Integer mValue = MFinder.findM(table);

        for (int i = 0; i < numOfConsumers; i++) {
            mainTable[i][numOfProducers - 1] = new TransportTaskTableMesh(0, mValue);
        }
    }
}
