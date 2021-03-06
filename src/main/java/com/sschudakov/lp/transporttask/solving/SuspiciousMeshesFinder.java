package com.sschudakov.lp.transporttask.solving;

import com.sschudakov.lp.transporttask.table.TTTableNode;
import com.sschudakov.lp.transporttask.table.TTTable;
import com.sschudakov.lp.transporttask.table.TTTableMesh;
import com.sschudakov.lp.transporttask.table_building.TTUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Semen Chudakov on 21.11.2017.
 */
public class SuspiciousMeshesFinder {

    public static List<TTTableNode> findSuspiciousMeshes(TTTable table) {

        List<TTTableNode> result = new ArrayList<>();
        TTTableMesh[][] mainTable = table.getMainTable();

        for (int i = 0; i < mainTable.length; i++) {
            for (int j = 0; j < mainTable[i].length; j++) {
                if (TTUtils.isSuspicious(mainTable[i][j])) {
                    result.add(new TTTableNode(mainTable[i][j], i, j));
//                    System.out.println(mainTable[i][j]);
//                    System.out.println("is l-mesh: " + TTUtils.isLMesh(mainTable[i][j]));
//                    System.out.println("is r-mesh: " + TTUtils.isRMesh(mainTable[i][j]));

                }
            }
        }
        return result;
    }


}
