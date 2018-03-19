package com.sschudakov.transport_task.table_building;

import com.sschudakov.transport_task.comparator.MeshNodePriceComparator;
import com.sschudakov.transport_task.table.MeshNode;
import com.sschudakov.transport_task.table.TransportTaskTable;
import com.sschudakov.transport_task.table.TransportTaskTableMesh;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Semen Chudakov on 14.11.2017.
 */
public class InitialValuesFiller {

    public void fillInitialValues(TransportTaskTable table) {
        List<MeshNode> meshes = new ArrayList<>();
        TransportTaskTableMesh[][] mainTable = table.getMainTable();
        for (int i = 0; i < mainTable.length; i++) {
            for (int j = 0; j < mainTable[i].length; j++) {
                meshes.add(new MeshNode(mainTable[i][j], i, j));
            }
        }
        meshes.sort(new MeshNodePriceComparator());
        for (MeshNode node : meshes) {
            fillMesh(table, node.getI(), node.getJ());
        }
    }

    private void fillMesh(TransportTaskTable table, int i, int j) {
        if (!rowIsFilled(table, i) && !columnIsFilled(table, j)) {
            table.getMainTable()[i][j].setValue(min(
                    table.getConsumersVector().get(i) - TransportTaskUtils.tellRowSum(table, i),
                    table.getProducersVector().get(j) - TransportTaskUtils.tellColumnSum(table, j),
                    table.getMainTable()[i][j].getRestriction()
            ));
        } else {
            table.getMainTable()[i][j].setValue(0);
        }
    }


    private boolean rowIsFilled(TransportTaskTable table, int row) {
        int consumerValue = table.getConsumersVector().get(row);
        int rowSum = TransportTaskUtils.tellRowSum(table, row);
        return consumerValue == rowSum;
    }

    private boolean columnIsFilled(TransportTaskTable table, int column) {
        int producerValue = table.getProducersVector().get(column);
        int columnSum = TransportTaskUtils.tellColumnSum(table, column);
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
