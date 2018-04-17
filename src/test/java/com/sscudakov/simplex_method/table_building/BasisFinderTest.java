package com.sscudakov.simplex_method.table_building;

import com.sschudakov.lp.simplexmethod.table.LPTable;
import com.sschudakov.lp.simplexmethod.building.BasisFinder;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by Semen Chudakov on 08.10.2017.
 */
public class BasisFinderTest {
    
    @Ignore
    @Test
    public void simpleTest() {
        LPTable table = new LPTable();
        BasisFinder basisFinder = new BasisFinder();
        double[][] mainTable = new double[][]{
                {1.0, 2.0, -1.0, 2.0, 1.0, 1.0, 0.0, 0.0},
                {-1.0, -1.0, 1.0, 3.0, -2.0, 0.0, 0.0, 1.0},
                {-2.0, 1.0, -3.0, 1.0, -2.0, 0.0, 1.0, 0.0},

        };

        ArrayList<Double> restrictions = new ArrayList<>();
        restrictions.add(8.0);
        restrictions.add(10.0);
        restrictions.add(4.0);
        ArrayList<Double> function = new ArrayList<>();
        function.add(1.0);
        function.add(-1.0);
        function.add(1.0);
        function.add(-3.0);
        function.add(2.0);
        function.add(0.0);
        function.add(0.0);
        function.add(11.0);
        ArrayList<Integer> basicVariables = new ArrayList<>();
        basicVariables.add(0);
        basicVariables.add(0);
        basicVariables.add(0);

        table.setFunction(function);
        table.setMainTable(mainTable);
        table.setRestrictionsVector(restrictions);
        table.setBasicVariables(basicVariables);
        table.setDeltasVector(new ArrayList<>());
        table.setNumOfVariables(8);
        table.setNumOfEquations(3);

        ArrayList<Integer> basicVariablesExpected = new ArrayList<>();
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

    @Ignore
    @Test
    public void hasNoBasisTest() {
        LPTable table = new LPTable();
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
