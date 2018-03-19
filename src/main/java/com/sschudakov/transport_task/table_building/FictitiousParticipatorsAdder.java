package com.sschudakov.transport_task.table_building;

import com.sschudakov.transport_task.table.TransportTaskTable;
import com.sschudakov.transport_task.table.TransportTaskTableMesh;
import com.sschudakov.transport_task.util.MFinder;

import java.util.Vector;

/**
 * Created by Semen Chudakov on 16.11.2017.
 */
public class FictitiousParticipatorsAdder {

    private static final Integer INFINITY = Integer.MAX_VALUE;
    private static final Integer CORNER_MESHES_PRICE = 0;

    public void addFictitiousParticipators(TransportTaskTable table) {
        if (!fictitiousParticipatorsAreNessessary(table)) {
            addFictitiousProducerAndConsumer(table);
            fillFictitiousMeshes(table);
        }
    }

    private void addFictitiousProducerAndConsumer(TransportTaskTable table) {
        TransportTaskTableMesh[][] mainTable = table.getMainTable();
        TransportTaskTableMesh[][] newTable = new TransportTaskTableMesh[mainTable.length + 1][mainTable[0].length + 1];
        int numOfConsumers = table.getNumOfConsumers();
        int numOfProducers = table.getNumOfProducers();


        TransportTaskUtils.copyTable(mainTable, newTable);

        Integer mValue = MFinder.findM(table);

        table.getConsumersVector().add(0);
        table.getProducersVector().add(0);
        table.setNumOfConsumers(numOfConsumers + 1);
        table.setNumOfProducers(numOfProducers + 1);
        table.setMainTable(newTable);

        initializeFictitiousMeshes(table, mValue);
    }

    private void initializeFictitiousMeshes(TransportTaskTable table, Integer mValue) {
        TransportTaskTableMesh[][] mainTable = table.getMainTable();
        int numOfConsumers = table.getNumOfConsumers();
        int numOfProducers = table.getNumOfProducers();

        for (int i = 0; i < numOfConsumers - 1; i++) {
            mainTable[i][numOfProducers - 1] = new TransportTaskTableMesh(mValue, INFINITY);
        }

        for (int i = 0; i < numOfProducers - 1; i++) {
            mainTable[numOfConsumers - 1][i] = new TransportTaskTableMesh(mValue, INFINITY);
        }

        mainTable[numOfConsumers - 1][numOfProducers - 1] = new TransportTaskTableMesh(CORNER_MESHES_PRICE, INFINITY, 0);

    }

    private void fillFictitiousMeshes(TransportTaskTable table) {

        TransportTaskTableMesh[][] mainTable = table.getMainTable();
        int numOfConsumers = table.getNumOfConsumers();
        int numOfProducers = table.getNumOfProducers();

        for (int i = 0; i < numOfConsumers - 1; i++) {
            mainTable[i][numOfProducers - 1].setValue(
                    table.getConsumersVector().get(i) - TransportTaskUtils.tellRowSum(table, i)
            );
        }
        for (int i = 0; i < numOfProducers - 1; i++) {
            mainTable[numOfConsumers - 1][i].setValue(
                    table.getProducersVector().get(i) - TransportTaskUtils.tellColumnSum(table, i)
            );
        }

    }

    private boolean fictitiousParticipatorsAreNessessary(TransportTaskTable table) {

        Vector<Integer> consumers = table.getConsumersVector();
        Vector<Integer> producers = table.getProducersVector();

        for (int i = 0; i < table.getConsumersVector().size(); i++) {
            if (consumers.get(i) != TransportTaskUtils.tellRowSum(table, i)) {
                return false;
            }
        }

        for (int i = 0; i < table.getProducersVector().size(); i++) {
            if (producers.get(i) != TransportTaskUtils.tellColumnSum(table, i)) {
                return false;
            }
        }
        return true;
    }
}
