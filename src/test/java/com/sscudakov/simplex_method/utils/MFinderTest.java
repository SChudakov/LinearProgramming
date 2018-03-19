package com.sscudakov.simplex_method.utils;

import com.sschudakov.simplex_method.table.SimplexTable;
import com.sschudakov.simplex_method.table_building.MFinder;
import org.junit.Assert;
import org.junit.Test;

import java.util.Vector;

/**
 * Created by Semen Chudakosv on 16.10.2017.
 */
public class MFinderTest {
    @Test
    public void test() {

        SimplexTable table = new SimplexTable();
        double[][] mainTable = new double[][]{
                {1, 3, 234, 142},
                {0, 3, 0, 1424},
                {0, -12313, 231, 4},
                {0, 0, 0, 3}
        };
        Vector<Double> function = new Vector<>();
        function.add(1.0);
        function.add(-3.0);
        function.add(-300.0);
        function.add(23.0);
        Vector<Double> restrictions = new Vector<>();
        restrictions.add(1.0);
        restrictions.add(-3.0);
        restrictions.add(-300.0);
        restrictions.add(23.0);

        table.setMainTable(mainTable);
        table.setFunction(function);
        table.setRestrictionsVector(restrictions);

        Assert.assertEquals(12313 + 1, (int) MFinder.findM(table));


    }

}
