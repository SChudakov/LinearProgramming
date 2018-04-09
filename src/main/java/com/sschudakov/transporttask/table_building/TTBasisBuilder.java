package com.sschudakov.transporttask.table_building;

import com.sschudakov.transporttask.table.TTBasis;
import com.sschudakov.transporttask.table.TTTable;
import com.sschudakov.transporttask.table.TTTableMesh;
import com.sschudakov.transporttask.table.TTTableNode;

/**
 * Created by Semen Chudakov on 17.11.2017.
 */
public class TTBasisBuilder {


    public TTBasis buildBasis(TTTable table) {
        TTTableMesh[][] mainTable = table.getMainTable();
        int numOfBasisElements = table.getNumOfConsumers() + table.getNumOfProducers() - 1;
        TTBasis basis = new TTBasis(numOfBasisElements);

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

    private void addStartingElements(TTTable table, TTBasis basis) {
        TTTableMesh[][] mainTable = table.getMainTable();

        for (int i = 0; i < table.getNumOfConsumers(); i++) {
            for (int j = 0; j < table.getNumOfProducers(); j++) {
                if (mainTable[i][j].getValue().doubleValue() > 0 &&
                        mainTable[i][j].getValue().doubleValue() < mainTable[i][j].getRestriction().doubleValue()) {
                    addElement(basis, mainTable[i][j], i, j);
                }
            }
        }
    }

    private void complementBasis(TTTable table, TTBasis basis) {
        TTTableMesh[][] mainTable = table.getMainTable();

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

    private void addElement(TTBasis basis, TTTableMesh mesh, int i, int j) {
        TTTableNode node = new TTTableNode(mesh, i, j);
        if (!TTUtils.wouldCreateCycle(basis, node)) {
            basis.add(node);
        }
    }
}
