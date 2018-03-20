package com.sschudakov.transport_task.table_building;

import com.sschudakov.transport_task.table.TransportTaskTable;

/**
 * Created by Semen Chudakov on 16.11.2017.
 */
public class TransportTaskTableBuilder {

    private StringInput input;
    private InitialValuesFiller valuesFiller;
    private FictitiousParticipatorsAdder balancer;

    public TransportTaskTableBuilder() {
        this.input = new StringInput();
        this.valuesFiller = new InitialValuesFiller();
        this.balancer = new FictitiousParticipatorsAdder();
    }

    public TransportTaskTable buildTransportTaskTable(String string) {

        TransportTaskTable taskTable = new TransportTaskTable();

        this.input.inputValues(taskTable, string);
        System.out.println("\nafter values inputILP\n");
        taskTable.outputTable();

        this.valuesFiller.fillInitialValues(taskTable);
        System.out.println("\nafter values filling\n");
        taskTable.outputTable();

        TableBalancer.balanceTable(taskTable);
        System.out.println("\nafter table balancing\n");
        taskTable.outputTable();

        this.balancer.addFictitiousParticipators(taskTable);
        System.out.println("\nafter adding fictitious producer and consumer\n");
        taskTable.outputTable();

        return taskTable;
    }
}
