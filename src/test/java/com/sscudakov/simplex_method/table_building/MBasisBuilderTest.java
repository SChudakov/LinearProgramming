package com.sscudakov.simplex_method.table_building;

import com.sschudakov.simplex_method.table.SimplexTable;
import com.sschudakov.simplex_method.table_building.MBasisBuilder;
import org.junit.Test;

import java.util.Arrays;
import java.util.Vector;

/**
 * Created by Semen Chudakov on 10.10.2017.
 */
public class MBasisBuilderTest {

    @Test
    public void nonZeroNumberOfYVariables() {
        SimplexTable table = new SimplexTable();

        MBasisBuilder basisBuilder = new MBasisBuilder();

        double[][] mainTable = new double[][]{
                {1, 0, 0, 0},
                {0, 3, 0, 0},
                {0, 4, 1, 4},
                {0, 0, 0, 3}
        };

        Vector<Double> function = new Vector<>();
        function.add(1.0);
        function.add(1.0);
        function.add(1.0);
        function.add(1.0);
        Vector<Integer> basicVariables = new Vector<>();
        basicVariables.add(0);
        basicVariables.add(2);

        table.setMainTable(mainTable);
        table.setFunction(function);
        table.setBasicVariables(basicVariables);
        table.setNumOfVariables(4);
        table.setNumOfEquations(4);

        basisBuilder.buildMBasis(table);

        System.out.println("result after initializing");
        for (double[] doubles : table.getMainTable()) {
            System.out.println(Arrays.toString(doubles));
        }

        System.out.println("function " + table.getFunction().toString());
    }

    @Test
    public void zeroNumberOfYVariables() {

        SimplexTable table = new SimplexTable();

        MBasisBuilder basisBuilder = new MBasisBuilder();

        double[][] mainTable = new double[][]{
                {1, 0, 0, 0},
                {0, 1, 0, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 1}
        };

        Vector<Double> function = new Vector<>();
        function.add(1.0);
        function.add(1.0);
        function.add(1.0);
        function.add(1.0);
        Vector<Integer> basicVariables = new Vector<>();
        basicVariables.add(0);
        basicVariables.add(1);
        basicVariables.add(2);
        basicVariables.add(3);

        table.setMainTable(mainTable);
        table.setFunction(function);
        table.setBasicVariables(basicVariables);
        table.setNumOfVariables(4);
        table.setNumOfEquations(4);

        basisBuilder.buildMBasis(table);

        System.out.println("result after initializing");
        for (double[] doubles : table.getMainTable()) {
            System.out.println(Arrays.toString(doubles));
        }

        System.out.println("function " + table.getFunction().toString());


    }

}
