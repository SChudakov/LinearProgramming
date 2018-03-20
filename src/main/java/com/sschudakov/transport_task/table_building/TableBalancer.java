package com.sschudakov.transport_task.table_building;

import com.sschudakov.transport_task.table.TTTable;
import com.sschudakov.transport_task.table.TTTableMesh;

/**
 * Created by Semen Chudakov on 05.12.2017.
 */
public class TableBalancer {

    public static void balanceTable(TTTable table) {
        if (TTUtils.tellConsumersValuesSum(table) <
                TTUtils.tellProducersValuesSum(table)) {
            addBalancingConsumer(table);
        }
        if (TTUtils.tellProducersValuesSum(table) <
                TTUtils.tellConsumersValuesSum(table)) {
            addBalancingProducer(table);
        }
    }

    private static void addBalancingConsumer(TTTable table) {
        TTTableMesh[][] mainTable = table.getMainTable();
        TTTableMesh[][] newTable = new TTTableMesh[mainTable.length + 1][mainTable[0].length];
        int numOfConsumers = table.getNumOfConsumers();
        int numOfProducers = table.getNumOfProducers();


        TTUtils.copyTable(mainTable, newTable);

        Integer mValue = MFinder.findM(table);

        table.getConsumersVector().add(TTUtils.tellProducersValuesSum(table) -
                TTUtils.tellConsumersValuesSum(table));
        table.setNumOfConsumers(numOfConsumers + 1);
        table.setMainTable(newTable);

        initializeAddedConsumer(table);
    }

    private static void initializeAddedConsumer(TTTable table) {
        TTTableMesh[][] mainTable = table.getMainTable();
        int numOfConsumers = table.getNumOfConsumers();
        int numOfProducers = table.getNumOfProducers();

        Integer mValue = MFinder.findM(table);

        for (int i = 0; i < numOfProducers; i++) {
            mainTable[numOfConsumers - 1][i] = new TTTableMesh(0, mValue);
        }
    }

    private static void addBalancingProducer(TTTable table) {
        TTTableMesh[][] mainTable = table.getMainTable();
        TTTableMesh[][] newTable = new TTTableMesh[mainTable.length][mainTable[0].length + 1];
        int numOfConsumers = table.getNumOfConsumers();
        int numOfProducers = table.getNumOfProducers();


        TTUtils.copyTable(mainTable, newTable);

        table.getProducersVector().add(TTUtils.tellConsumersValuesSum(table) -
                TTUtils.tellProducersValuesSum(table));
        table.setNumOfProducers(numOfProducers + 1);
        table.setMainTable(newTable);

        initializeAddedProducer(table);
    }

    private static void initializeAddedProducer(TTTable table) {
        TTTableMesh[][] mainTable = table.getMainTable();
        int numOfConsumers = table.getNumOfConsumers();
        int numOfProducers = table.getNumOfProducers();

        Integer mValue = MFinder.findM(table);

        for (int i = 0; i < numOfConsumers; i++) {
            mainTable[i][numOfProducers - 1] = new TTTableMesh(0, mValue);
        }
    }
}
