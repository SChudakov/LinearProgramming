package com.sschudakov.transport_task.solving;

import com.sschudakov.transport_task.table.TransportTableBasis;
import com.sschudakov.transport_task.table.TransportTaskTable;
import com.sschudakov.transport_task.table.TransportTaskTableMesh;

/**
 * Created by Semen Chudakov on 20.11.2017.
 */
public class DeltasCalculator {
    public static void calculateDeltas(TransportTaskTable table, TransportTableBasis basis) {
        TransportTaskTableMesh[][] mainTable = table.getMainTable();

        for (int i = 0; i < mainTable.length; i++) {
            for (int j = 0; j < mainTable[i].length; j++) {
                if (!basis.contains(mainTable[i][j])) {
                    mainTable[i][j].setDelta(
                            mainTable[i][j].getPrice() -
                                    (table.getvPotentials().get(j)
                                            - table.getuPotentials().get(i))
                    );
                } else {
                    mainTable[i][j].setDelta(null);
                }
            }
        }
    }

}
