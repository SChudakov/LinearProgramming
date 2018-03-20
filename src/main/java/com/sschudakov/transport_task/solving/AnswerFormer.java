package com.sschudakov.transport_task.solving;

import com.sschudakov.transport_task.table.TTTable;

import java.util.Arrays;

/**
 * Created by Semen Chudakov on 05.12.2017.
 */
public class AnswerFormer {

    public static void fromAnswer(TTTable table) {
        fromTransportMatrix(table);
        fromFunctionValue(table);
    }

    private static void fromTransportMatrix(TTTable table) {
        int[][] transportMatrix = new int[table.getNumOfConsumers()][table.getNumOfProducers()];
        for (int i = 0; i < table.getNumOfConsumers(); i++) {
            for (int j = 0; j < table.getNumOfProducers(); j++) {
                transportMatrix[i][j] = table.getMainTable()[i][j].getValue();
            }
        }

        System.out.println("\ntransport matrix\n");

        for (int[] matrix : transportMatrix) {
            System.out.println(Arrays.toString(matrix));
        }
    }

    private static void fromFunctionValue(TTTable table) {
        int functionValue = 0;
        for (int i = 0; i < table.getNumOfConsumers(); i++) {
            for (int j = 0; j < table.getNumOfProducers(); j++) {
                functionValue += table.getMainTable()[i][j].getValue() * table.getMainTable()[i][j].getPrice();
            }
        }
        System.out.println("\n function value: " + functionValue);
    }
}
