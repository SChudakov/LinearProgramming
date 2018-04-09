package com.sschudakov.transporttask.table_building;

import com.sschudakov.transporttask.table.TTTable;
import com.sschudakov.transporttask.table.TTTableMesh;

import java.util.Vector;

/**
 * Created by Semen Chudakov on 16.11.2017.
 */
public class FictitiousParticipatorsAdder {

    private static final Integer INFINITY = Integer.MAX_VALUE;
    private static final Integer CORNER_MESHES_PRICE = 0;

    public void addFictitiousParticipators(TTTable table) {
        if (!fictitiousParticipatorsAreNessessary(table)) {
            addFictitiousProducerAndConsumer(table);
            fillFictitiousMeshes(table);
        }
    }

    private void addFictitiousProducerAndConsumer(TTTable table) {
        TTTableMesh[][] mainTable = table.getMainTable();
        TTTableMesh[][] newTable = new TTTableMesh[mainTable.length + 1][mainTable[0].length + 1];
        int numOfConsumers = table.getNumOfConsumers();
        int numOfProducers = table.getNumOfProducers();


        TTUtils.copyTable(mainTable, newTable);

        Integer mValue = MFinder.findM(table);

        table.getConsumersVector().add(0);
        table.getProducersVector().add(0);
        table.setNumOfConsumers(numOfConsumers + 1);
        table.setNumOfProducers(numOfProducers + 1);
        table.setMainTable(newTable);

        initializeFictitiousMeshes(table, mValue);
    }

    private void initializeFictitiousMeshes(TTTable table, Integer mValue) {
        TTTableMesh[][] mainTable = table.getMainTable();
        int numOfConsumers = table.getNumOfConsumers();
        int numOfProducers = table.getNumOfProducers();

        for (int i = 0; i < numOfConsumers - 1; i++) {
            mainTable[i][numOfProducers - 1] = new TTTableMesh(mValue, INFINITY);
        }

        for (int i = 0; i < numOfProducers - 1; i++) {
            mainTable[numOfConsumers - 1][i] = new TTTableMesh(mValue, INFINITY);
        }

        mainTable[numOfConsumers - 1][numOfProducers - 1] = new TTTableMesh(CORNER_MESHES_PRICE, INFINITY, 0);

    }

    private void fillFictitiousMeshes(TTTable table) {

        TTTableMesh[][] mainTable = table.getMainTable();
        int numOfConsumers = table.getNumOfConsumers();
        int numOfProducers = table.getNumOfProducers();

        for (int i = 0; i < numOfConsumers - 1; i++) {
            mainTable[i][numOfProducers - 1].setValue(
                    table.getConsumersVector().get(i) - TTUtils.tellRowSum(table, i)
            );
        }
        for (int i = 0; i < numOfProducers - 1; i++) {
            mainTable[numOfConsumers - 1][i].setValue(
                    table.getProducersVector().get(i) - TTUtils.tellColumnSum(table, i)
            );
        }

    }

    private boolean fictitiousParticipatorsAreNessessary(TTTable table) {

        Vector<Integer> consumers = table.getConsumersVector();
        Vector<Integer> producers = table.getProducersVector();

        for (int i = 0; i < table.getConsumersVector().size(); i++) {
            if (consumers.get(i) != TTUtils.tellRowSum(table, i)) {
                return false;
            }
        }

        for (int i = 0; i < table.getProducersVector().size(); i++) {
            if (producers.get(i) != TTUtils.tellColumnSum(table, i)) {
                return false;
            }
        }
        return true;
    }
}
