package com.sschudakov.transport_task.solving;

import com.sschudakov.transport_task.table.MeshNode;
import com.sschudakov.transport_task.table.TransportTaskTable;
import com.sschudakov.transport_task.table.TransportTaskTableMesh;
import com.sschudakov.transport_task.table_building.TransportTaskUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Semen Chudakov on 21.11.2017.
 */
public class SuspiciousMeshesFinder {

    public static List<MeshNode> findSuspiciousMeshes(TransportTaskTable table) {

        List<MeshNode> result = new ArrayList<>();
        TransportTaskTableMesh[][] mainTable = table.getMainTable();

        for (int i = 0; i < mainTable.length; i++) {
            for (int j = 0; j < mainTable[i].length; j++) {
                if (TransportTaskUtils.isSuspicious(mainTable[i][j])) {
                    result.add(new MeshNode(mainTable[i][j], i, j));
//                    System.out.println(mainTable[i][j]);
//                    System.out.println("is l-mesh: " + TransportTaskUtils.isLMesh(mainTable[i][j]));
//                    System.out.println("is r-mesh: " + TransportTaskUtils.isRMesh(mainTable[i][j]));

                }
            }
        }
        return result;
    }


}
