package com.sscudakov.simplex_method.table_building;

import com.sschudakov.simplex_method.table.SimplexTable;
import com.sschudakov.simplex_method.table_building.BasisFinder;
import org.junit.Assert;
import org.junit.Test;

import java.util.Vector;

/**
 * Created by Semen Chudakov on 08.10.2017.
 */
public class BasisFinderTest {
    @Test
    public void simpleTest() {
        SimplexTable table = new SimplexTable();
        BasisFinder basisFinder = new BasisFinder();
        double[][] mainTable = new double[][]{
                {1.0, 2.0, -1.0, 2.0, 1.0, 1.0, 0.0, 0.0},
                {-1.0, -1.0, 1.0, 3.0, -2.0, 0.0, 0.0, 1.0},
                {-2.0, 1.0, -3.0, 1.0, -2.0, 0.0, 1.0, 0.0},

        };

        Vector<Double> restrictions = new Vector<>();
        restrictions.add(8.0);
        restrictions.add(10.0);
        restrictions.add(4.0);
        Vector<Double> function = new Vector<>();
        function.add(1.0);
        function.add(-1.0);
        function.add(1.0);
        function.add(-3.0);
        function.add(2.0);
        function.add(0.0);
        function.add(0.0);
        function.add(11.0);
        Vector<Integer> basicVariables = new Vector<>();
        basicVariables.add(0);
        basicVariables.add(0);
        basicVariables.add(0);

        table.setFunction(function);
        table.setMainTable(mainTable);
        table.setRestrictionsVector(restrictions);
        table.setBasicVariables(basicVariables);
        table.setDeltasVector(new Vector<>());
        table.setNumOfVariables(8);
        table.setNumOfEquations(3);

        Vector<Integer> basicVariablesExpected = new Vector<>();
        basicVariablesExpected.add(5);
        basicVariablesExpected.add(7);
        basicVariablesExpected.add(6);


        try {
            basisFinder.findBasis(table);
            Assert.assertEquals(basicVariablesExpected, table.getBasicVariables());
        } catch (Exception e) {
            System.out.println(table.getBasicVariables());
            throw e;
        }
    }

    @Test
    public void hasNoBasisTest() {
        SimplexTable table = new SimplexTable();
        BasisFinder basisFinder = new BasisFinder();
        double[][] mainTable = new double[][]{
                {1, 0, 0},
                {0, 1, 1},
                {0, 0, 1}
        };

        table.setMainTable(mainTable);
        table.setNumOfVariables(3);
        table.setNumOfEquations(3);
        try {
            basisFinder.findBasis(table);
            System.out.println(table.getBasicVariables());
        } catch (Exception e) {
            System.out.println(table.getBasicVariables());
        }
    }


}
