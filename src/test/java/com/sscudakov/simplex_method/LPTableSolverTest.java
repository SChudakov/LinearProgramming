package com.sscudakov.simplex_method;

import com.sschudakov.simplexmethod.exception.TableAlreadyOptimalException;
import com.sschudakov.simplexmethod.solver.LPSolver;
import com.sschudakov.simplexmethod.table.LPTable;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by Semen Chudakov on 17.10.2017.
 */
public class LPTableSolverTest {

    @Ignore
    @Test
    public void testRecountDeltas() {
        LPTable table = new LPTable();
        LPSolver solver = new LPSolver();
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
        basicVariables.add(5);
        basicVariables.add(7);
        basicVariables.add(6);

        table.setFunction(function);
        table.setMainTable(mainTable);
        table.setRestrictionsVector(restrictions);
        table.setBasicVariables(basicVariables);
        table.setDeltasVector(new ArrayList<>());
        table.setNumOfVariables(8);
        table.setNumOfEquations(3);

//        solver.recountDeltas();

        ArrayList<Double> deltasExpected = new ArrayList<>();
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

    @Ignore
    @Test
    public void findResolvingLineNotOptimalTest() {

        LPTable table = new LPTable();
        LPSolver solver = new LPSolver();

        ArrayList<Double> restrictions = new ArrayList<>();
        restrictions.add(8.0);
        restrictions.add(10.0);
        restrictions.add(-4.0);

        table.setRestrictionsVector(restrictions);

//        Assert.assertEquals(2, solver.findResolvingLine());

    }

    @Ignore
    @Test
    public void findResolvingLineAlreadyOptimalTest() {

        LPTable table = new LPTable();
        LPSolver solver = new LPSolver();

        ArrayList<Double> restrictions = new ArrayList<>();
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

    @Ignore
    @Test
    public void findResolvingColumnTest() {
        LPTable table = new LPTable();
        LPSolver solver = new LPSolver();
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
        basicVariables.add(5);
        basicVariables.add(7);
        basicVariables.add(6);

        table.setFunction(function);
        table.setMainTable(mainTable);
        table.setRestrictionsVector(restrictions);
        table.setBasicVariables(basicVariables);
        ArrayList<Double> deltas = new ArrayList<>();
        ArrayList<Double> simplexRatios = new ArrayList<>();
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

    @Ignore
    @Test
    public void findResolvingColumnNoNegativesTest() {

    }

    @Ignore
    @Test
    public void recountSimplexRatiosTest() {

    }

    @Ignore
    @Test
    public void recountTableTest() {
        /*LPTable table = new LPTable();
        LPSolver solver = new LPSolver();
        double[][] mainTable = new double[][]{
                {1.0, 2.0, -1.0, 2.0, 1.0, 1.0, 0.0, 0.0},
                {-1.0, -1.0, 1.0, 3.0, -2.0, 0.0, 0.0, 1.0},
                {-2.0, 1.0, -3.0, 1.0, -2.0, 0.0, 1.0, 0.0},

        };

        ArrayList<Double> restrictions = new ArrayList<>();
        restrictions.add(8.0);
        restrictions.add(10.0);
        restrictions.add(4.0);
        ArrayList<Integer> basicVariables = new ArrayList<>();
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
        System.out.println(restrictions.toString());*/

    }

}
