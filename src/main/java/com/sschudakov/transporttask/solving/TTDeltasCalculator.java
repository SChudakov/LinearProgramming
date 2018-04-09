package com.sschudakov.transporttask.solving;

import com.sschudakov.transporttask.table.TTBasis;
import com.sschudakov.transporttask.table.TTTable;
import com.sschudakov.transporttask.table.TTTableMesh;

/**
 * Created by Semen Chudakov on 20.11.2017.
 */
public class TTDeltasCalculator {
    public void calculateDeltas(TTTable table, TTBasis basis) {
        TTTableMesh[][] mainTable = table.getMainTable();

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
