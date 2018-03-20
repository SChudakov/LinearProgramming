package com.sschudakov.transport_task.table_building;

import com.sschudakov.transport_task.table.TTTable;

/**
 * Created by Semen Chudakov on 16.11.2017.
 */
public class TTTableBuilder {
    private InitialValuesFiller valuesFiller;
    private FictitiousParticipatorsAdder balancer;

    public TTTableBuilder() {
        this.valuesFiller = new InitialValuesFiller();
        this.balancer = new FictitiousParticipatorsAdder();
    }

    public TTTable buildTransportTaskTable(TTTable ttTable) {
        this.valuesFiller.fillInitialValues(ttTable);
        System.out.println("\nafter values filling\n");
        ttTable.outputTable();

        TableBalancer.balanceTable(ttTable);
        System.out.println("\nafter table balancing\n");
        ttTable.outputTable();

        this.balancer.addFictitiousParticipators(ttTable);
        System.out.println("\nafter adding fictitious producer and consumer\n");
        ttTable.outputTable();

        return ttTable;
    }
}
