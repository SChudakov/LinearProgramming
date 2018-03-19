package com.sschudakov.transport_task.table_building;

import com.sschudakov.transport_task.table.MeshNode;
import com.sschudakov.transport_task.table.TransportTableBasis;
import com.sschudakov.transport_task.table.TransportTaskTable;
import com.sschudakov.transport_task.table.TransportTaskTableMesh;

/**
 * Created by Semen Chudakov on 17.11.2017.
 */
public class BasisBuilder {


    public static TransportTableBasis buildBasis(TransportTaskTable table) {
        TransportTaskTableMesh[][] mainTable = table.getMainTable();
        int numOfBasisElements = table.getNumOfConsumers() + table.getNumOfProducers() - 1;
        TransportTableBasis basis = new TransportTableBasis(numOfBasisElements);

        addStartingElements(table, basis);
        //add corner element
        addElement(basis,
                mainTable[table.getNumOfConsumers() - 1][table.getNumOfProducers() - 1],
                table.getNumOfConsumers() - 1,
                table.getNumOfProducers() - 1);

        complementBasis(table, basis);
        if (!basis.isComplete()) {
            throw new RuntimeException("basis is not complete");
        }
        return basis;
    }

    private static void addStartingElements(TransportTaskTable table, TransportTableBasis basis) {
        TransportTaskTableMesh[][] mainTable = table.getMainTable();

        for (int i = 0; i < table.getNumOfConsumers(); i++) {
            for (int j = 0; j < table.getNumOfProducers(); j++) {
                if (mainTable[i][j].getValue().doubleValue() > 0 &&
                        mainTable[i][j].getValue().doubleValue() < mainTable[i][j].getRestriction().doubleValue()) {
                    addElement(basis, mainTable[i][j], i, j);
                }
            }
        }
    }

    private static void complementBasis(TransportTaskTable table, TransportTableBasis basis) {
        TransportTaskTableMesh[][] mainTable = table.getMainTable();

        for (int i = 0; i < table.getNumOfConsumers() - 1; i++) {
            for (int j = 0; j < table.getNumOfProducers() - 1; j++) {
                if (mainTable[i][j].getValue().doubleValue() == 0) {
                    addElement(basis, mainTable[i][j], i, j);
                    if (basis.isComplete()) {
                        return;
                    }
                }
            }
        }

        for (int i = 0; i < table.getNumOfConsumers() - 1; i++) {
            for (int j = 0; j < table.getNumOfProducers() - 1; j++) {
                if (mainTable[i][j].getValue().doubleValue() == mainTable[i][j].getRestriction().doubleValue()) {
                    addElement(basis, mainTable[i][j], i, j);
                    if (basis.isComplete()) {
                        return;
                    }
                }
            }
        }

    }

    private static void addElement(TransportTableBasis basis, TransportTaskTableMesh mesh, int i, int j) {
        MeshNode node = new MeshNode(mesh, i, j);
        if (!TransportTaskUtils.wouldCreateCycle(basis, node)) {
            basis.add(node);
        }
    }
}
