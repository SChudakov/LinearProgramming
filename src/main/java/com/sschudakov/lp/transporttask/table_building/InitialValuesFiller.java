package com.sschudakov.lp.transporttask.table_building;

import com.sschudakov.lp.transporttask.comparator.MeshNodePriceComparator;
import com.sschudakov.lp.transporttask.table.TTTableNode;
import com.sschudakov.lp.transporttask.table.TTTable;
import com.sschudakov.lp.transporttask.table.TTTableMesh;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Semen Chudakov on 14.11.2017.
 */
public class InitialValuesFiller {

    public void fillInitialValues(TTTable table) {
        List<TTTableNode> meshes = new ArrayList<>();
        TTTableMesh[][] mainTable = table.getMainTable();
        for (int i = 0; i < mainTable.length; i++) {
            for (int j = 0; j < mainTable[i].length; j++) {
                meshes.add(new TTTableNode(mainTable[i][j], i, j));
            }
        }
        meshes.sort(new MeshNodePriceComparator());
        for (TTTableNode node : meshes) {
            fillMesh(table, node.getI(), node.getJ());
        }
    }

    private void fillMesh(TTTable table, int i, int j) {
        if (!rowIsFilled(table, i) && !columnIsFilled(table, j)) {
            table.getMainTable()[i][j].setValue(min(
                    table.getConsumersVector().get(i) - TTUtils.tellRowSum(table, i),
                    table.getProducersVector().get(j) - TTUtils.tellColumnSum(table, j),
                    table.getMainTable()[i][j].getRestriction()
            ));
        } else {
            table.getMainTable()[i][j].setValue(0);
        }
    }


    private boolean rowIsFilled(TTTable table, int row) {
        int consumerValue = table.getConsumersVector().get(row);
        int rowSum = TTUtils.tellRowSum(table, row);
        return consumerValue == rowSum;
    }

    private boolean columnIsFilled(TTTable table, int column) {
        int producerValue = table.getProducersVector().get(column);
        int columnSum = TTUtils.tellColumnSum(table, column);
        return producerValue == columnSum;
    }


    private static Integer min(Integer... integers) {
        Integer result = integers[0];
        for (Integer integer : integers) {
            if (integer.doubleValue() < result.doubleValue()) {
                result = integer;
            }
        }
        return result;
    }
}
