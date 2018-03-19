package com.sscudakov.simplex_method;

import com.sschudakov.simplex_method.exception.TableAlreadyOptimalException;
import com.sschudakov.simplex_method.solver.SimplexTableSolver;
import com.sschudakov.simplex_method.table.SimplexTable;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Vector;

/**
 * Created by Semen Chudakov on 17.10.2017.
 */
public class SimplexTableSolverTest {

    @Test
    public void testRecountDeltas() {
        SimplexTable table = new SimplexTable();
        SimplexTableSolver solver = new SimplexTableSolver(table);
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
        basicVariables.add(5);
        basicVariables.add(7);
        basicVariables.add(6);

        table.setFunction(function);
        table.setMainTable(mainTable);
        table.setRestrictionsVector(restrictions);
        table.setBasicVariables(basicVariables);
        table.setDeltasVector(new Vector<>());
        table.setNumOfVariables(8);
        table.setNumOfEquations(3);

//        solver.recountDeltas();

        Vector<Double> deltasExpected = new Vector<>();
        deltasExpected.add(12.0);
        deltasExpected.add(10.0);
        deltasExpected.add(-10.0);
        deltasExpected.add(-36.0);
        deltasExpected.add(24.0);
        deltasExpected.add(0.0);
        deltasExpected.add(0.0);
        deltasExpected.add(0.0);

        Assert.assertEquals(deltasExpected, table.getDeltasVector());
    }

    @Test
    public void findResolvingLineNotOptimalTest() {

        SimplexTable table = new SimplexTable();
        SimplexTableSolver solver = new SimplexTableSolver(table);

        Vector<Double> restrictions = new Vector<>();
        restrictions.add(8.0);
        restrictions.add(10.0);
        restrictions.add(-4.0);

        table.setRestrictionsVector(restrictions);

//        Assert.assertEquals(2, solver.findResolvingLine());

    }

    @Test
    public void findResolvingLineAlreadyOptimalTest() {

        SimplexTable table = new SimplexTable();
        SimplexTableSolver solver = new SimplexTableSolver(table);

        Vector<Double> restrictions = new Vector<>();
        restrictions.add(8.0);
        restrictions.add(10.0);
        restrictions.add(4.0);

        table.setRestrictionsVector(restrictions);

        try {
//            solver.findResolvingLine();
            throw new AssertionError("table is already optimal");
        } catch (TableAlreadyOptimalException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void findResolvingColumnTest() {
        SimplexTable table = new SimplexTable();
        SimplexTableSolver solver = new SimplexTableSolver(table);
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
        basicVariables.add(5);
        basicVariables.add(7);
        basicVariables.add(6);

        table.setFunction(function);
        table.setMainTable(mainTable);
        table.setRestrictionsVector(restrictions);
        table.setBasicVariables(basicVariables);
        Vector<Double> deltas = new Vector<>();
        Vector<Double> simplexRatios = new Vector<>();
        for (int i = 0; i < table.getNumOfVariables(); i++) {
            deltas.add(0.0);
            simplexRatios.add(0.0);
        }
        table.setDeltasVector(deltas);
        table.setSimplexRatios(simplexRatios);
        table.setNumOfVariables(8);
        table.setNumOfEquations(3);

//        solver.recountDeltas();

//        Assert.assertEquals(2, solver.findResolvingColumn(0));
    }

    @Test
    public void findResolvingColumnNoNegativesTest() {

    }

    @Test
    public void recountSimplexRatiosTest() {

    }

    @Test
    public void recountTableTest() {
        SimplexTable table = new SimplexTable();
        SimplexTableSolver solver = new SimplexTableSolver(table);
        double[][] mainTable = new double[][]{
                {1.0, 2.0, -1.0, 2.0, 1.0, 1.0, 0.0, 0.0},
                {-1.0, -1.0, 1.0, 3.0, -2.0, 0.0, 0.0, 1.0},
                {-2.0, 1.0, -3.0, 1.0, -2.0, 0.0, 1.0, 0.0},

        };

        Vector<Double> restrictions = new Vector<>();
        restrictions.add(8.0);
        restrictions.add(10.0);
        restrictions.add(4.0);
        Vector<Integer> basicVariables = new Vector<>();
        basicVariables.add(5);
        basicVariables.add(7);
        basicVariables.add(6);

        table.setMainTable(mainTable);
        table.setRestrictionsVector(restrictions);
        table.setBasicVariables(basicVariables);

        solver.recountMainTable(2, 0);

        for (double[] doubles : table.getMainTable()) {
            System.out.println(Arrays.toString(doubles));
        }
        System.out.println(restrictions.toString());

    }

}
