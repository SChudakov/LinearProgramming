package com.sschudakov.transport_task.table_building;

import com.sschudakov.transport_task.table.TTTable;
import com.sschudakov.transport_task.table.TTTableMesh;

import java.util.Collections;
import java.util.Vector;

/**
 * Created by Semen Chudakov on 17.11.2017.
 */
public class MFinder {

    public static Integer findM(TTTable table) {
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

    private static Integer maxPrices(TTTableMesh[][] mainTable) {
        Integer result = mainTable[0][0].getPrice();

        for (TTTableMesh[] TTTableMeshes : mainTable) {
            for (TTTableMesh TTTableMesh : TTTableMeshes) {
                if (TTTableMesh.getPrice().compareTo(result) > 0) {
                    result = TTTableMesh.getPrice();
                }
            }
        }
        return result;
    }

    private static Integer maxRestrictions(TTTableMesh[][] mainTable) {
        Integer result = mainTable[0][0].getRestriction();

        for (TTTableMesh[] TTTableMeshes : mainTable) {
            for (TTTableMesh TTTableMesh : TTTableMeshes) {
                if (TTTableMesh.getRestriction().compareTo(result) > 0) {
                    result = TTTableMesh.getRestriction();
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
