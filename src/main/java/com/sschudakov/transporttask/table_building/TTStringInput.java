package com.sschudakov.transporttask.table_building;

import com.sschudakov.transporttask.table.TTTable;
import com.sschudakov.transporttask.table.TTTableMesh;

import java.util.Scanner;
import java.util.Vector;

/**
 * Created by Semen Chudakov on 14.11.2017.
 */
public class TTStringInput {


    public void inputValues(TTTable table, String string) {

        Scanner scanner = new Scanner(string);

        int numOfConsumers = inputNumOfConsumers(scanner);
        int numOfProducers = inputNumOfProducers(scanner);

        table.setNumOfConsumers(numOfConsumers);
        table.setNumOfProducers(numOfProducers);


        table.setConsumersVector(inputConsumersVector(table, scanner));
        table.setProducersVector(inputProducersVector(table, scanner));
        table.setMainTable(inputMainTable(table, scanner));
    }

    private int inputNumOfConsumers(Scanner scanner) {
        return scanner.nextInt();
    }

    private int inputNumOfProducers(Scanner scanner) {
        return scanner.nextInt();
    }

    private Vector<Integer> inputConsumersVector(TTTable table, Scanner scanner) {
        Vector<Integer> result = new Vector<>();
        for (int i = 0; i < table.getNumOfConsumers(); i++) {
            result.add(scanner.nextInt());
        }
        return result;
    }

    private Vector<Integer> inputProducersVector(TTTable table, Scanner scanner) {
        Vector<Integer> result = new Vector<>();
        for (int i = 0; i < table.getNumOfProducers(); i++) {
            result.add(scanner.nextInt());
        }
        return result;
    }


    private TTTableMesh[][] inputMainTable(TTTable table, Scanner scanner) {
        TTTableMesh[][] result =
                new TTTableMesh[table.getNumOfConsumers()][table.getNumOfProducers()];

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                result[i][j] = new TTTableMesh();
            }
        }

        inputPrices(result, table.getNumOfConsumers(), table.getNumOfProducers(), scanner);
        inputRestrictions(result, table.getNumOfConsumers(), table.getNumOfProducers(), scanner);
        return result;
    }

    private void inputPrices(TTTableMesh[][] mainTable, int numOfRows, int numOfColumns, Scanner scanner) {
        for (int i = 0; i < numOfRows; i++) {
            for (int j = 0; j < numOfColumns; j++) {
                mainTable[i][j].setPrice(scanner.nextInt());
            }
        }
    }

    private void inputRestrictions(TTTableMesh[][] mainTable, int numOfRows, int numOfColumns, Scanner scanner) {
        for (int i = 0; i < numOfRows; i++) {
            for (int j = 0; j < numOfColumns; j++) {
                mainTable[i][j].setRestriction(scanner.nextInt());
            }
        }
    }


}
