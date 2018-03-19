package com.sschudakov.transport_task.solving;

import com.sschudakov.transport_task.table.MeshNode;
import com.sschudakov.transport_task.table.TransportTableBasis;
import com.sschudakov.transport_task.table.TransportTaskTable;
import com.sschudakov.transport_task.table_building.TransportTaskUtils;

/**
 * Created by Semen Chudakov on 19.11.2017.
 */
public class PotentialsCalculator {

    public static void calculatePotentials(TransportTaskTable table, TransportTableBasis basis) {

        System.out.println("\ncalculate potentials\n");

        int numOfConsumers = table.getNumOfConsumers();
        int numOfProducers = table.getNumOfProducers();

        int numOfPotentials = numOfConsumers + numOfProducers;


        table.setuPotentials(TransportTaskUtils.formEmptyVector(numOfConsumers));
        table.setvPotentials(TransportTaskUtils.formEmptyVector(numOfProducers));

        int firstBasisElementColumn = basis.getBasicElements().get(0).getJ();

        //setting first potential
        table.getvPotentials().set(firstBasisElementColumn, 0);

        int basicElementI;
        int basicElementJ;
        for (int i = 0; i < numOfPotentials; i++) {
            for (MeshNode basicElement : basis.getBasicElements()) {
                basicElementI = basicElement.getI();
                basicElementJ = basicElement.getJ();

                if (table.getuPotentials().get(basicElementI) != null
                        && table.getvPotentials().get(basicElementJ) == null) {
                    table.getvPotentials().set(
                            basicElementJ,
                            table.getuPotentials().get(basicElementI) +
                                    basicElement.getMesh().getPrice()
                    );
                    break;
                }

                if (table.getvPotentials().get(basicElementJ) != null
                        && table.getuPotentials().get(basicElementI) == null) {
                    table.getuPotentials().set(
                            basicElementI,
                            table.getvPotentials().get(basicElementJ) -
                                    basicElement.getMesh().getPrice()
                    );
                    break;
                }
            }
        }
    }
}
