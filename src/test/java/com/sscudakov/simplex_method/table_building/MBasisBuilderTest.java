package com.sscudakov.simplex_method.table_building;

import com.sschudakov.simplex_method.table.SimplexTable;
import com.sschudakov.simplex_method.table_building.MBasisBuilder;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.ArrayList;

/**
 * Created by Semen Chudakov on 10.10.2017.
 */
public class MBasisBuilderTest {

    @Ignore
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

        ArrayList<Double> function = new ArrayList<>();
        function.add(1.0);
        function.add(1.0);
        function.add(1.0);
        function.add(1.0);
        ArrayList<Integer> basicVariables = new ArrayList<>();
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

    @Ignore
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

        ArrayList<Double> function = new ArrayList<>();
        function.add(1.0);
        function.add(1.0);
        function.add(1.0);
        function.add(1.0);
        ArrayList<Integer> basicVariables = new ArrayList<>();
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
