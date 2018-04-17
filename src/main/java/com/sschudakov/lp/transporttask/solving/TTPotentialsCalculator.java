package com.sschudakov.lp.transporttask.solving;

import com.sschudakov.lp.transporttask.table.TTBasis;
import com.sschudakov.lp.transporttask.table.TTTable;
import com.sschudakov.lp.transporttask.table.TTTableNode;
import com.sschudakov.lp.transporttask.table_building.TTUtils;

/**
 * Created by Semen Chudakov on 19.11.2017.
 */
public class TTPotentialsCalculator {

    public void calculatePotentials(TTTable table, TTBasis basis) {

        System.out.println("\ncalculate potentials\n");

        int numOfConsumers = table.getNumOfConsumers();
        int numOfProducers = table.getNumOfProducers();

        int numOfPotentials = numOfConsumers + numOfProducers;


        table.setuPotentials(TTUtils.formEmptyVector(numOfConsumers));
        table.setvPotentials(TTUtils.formEmptyVector(numOfProducers));

        int firstBasisElementColumn = basis.getBasicElements().get(0).getJ();

        //setting first potential
        table.getvPotentials().set(firstBasisElementColumn, 0);

        int basicElementI;
        int basicElementJ;
        for (int i = 0; i < numOfPotentials; i++) {
            for (TTTableNode basicElement : basis.getBasicElements()) {
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
