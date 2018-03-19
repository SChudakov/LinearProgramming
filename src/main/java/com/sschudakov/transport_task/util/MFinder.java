package com.sschudakov.transport_task.util;

import com.sschudakov.transport_task.table.TransportTaskTable;
import com.sschudakov.transport_task.table.TransportTaskTableMesh;

import java.util.Collections;
import java.util.Vector;

/**
 * Created by Semen Chudakov on 17.11.2017.
 */
public class MFinder {

    public static Integer findM(TransportTaskTable table) {
        Integer maxConsumersValues = maxVector(table.getConsumersVector());
        Integer maxProducersValues = maxVector(table.getProducersVector());
        Integer maxPrices = maxPrices(table.getMainTable());
        Integer maxRestrictions = maxRestrictions(table.getMainTable());

        return max(
                maxConsumersValues,
                maxProducersValues,
                maxPrices,
                maxRestrictions
        );
    }

    private static Integer maxVector(Vector<Integer> vector) {
        return Collections.max(vector);
    }

    private static Integer maxPrices(TransportTaskTableMesh[][] mainTable) {
        Integer result = mainTable[0][0].getPrice();

        for (TransportTaskTableMesh[] transportTaskTableMeshes : mainTable) {
            for (TransportTaskTableMesh transportTaskTableMesh : transportTaskTableMeshes) {
                if (transportTaskTableMesh.getPrice().compareTo(result) > 0) {
                    result = transportTaskTableMesh.getPrice();
                }
            }
        }
        return result;
    }

    private static Integer maxRestrictions(TransportTaskTableMesh[][] mainTable) {
        Integer result = mainTable[0][0].getRestriction();

        for (TransportTaskTableMesh[] transportTaskTableMeshes : mainTable) {
            for (TransportTaskTableMesh transportTaskTableMesh : transportTaskTableMeshes) {
                if (transportTaskTableMesh.getRestriction().compareTo(result) > 0) {
                    result = transportTaskTableMesh.getRestriction();
                }
            }
        }
        return result;
    }

    private static Integer max(Integer... integers) {
        Integer result = integers[0];
        for (Integer integer : integers) {
            if (integer.doubleValue() > result.doubleValue()) {
                result = integer;
            }
        }
        return result;
    }
}
