package com.sscudakov.simplex_method.utils;

import com.sschudakov.simplexmethod.table.LPTable;
import com.sschudakov.simplexmethod.table_building.MFinder;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by Semen Chudakosv on 16.10.2017.
 */
public class MFinderTest {
    @Ignore
    @Test
    public void test() {

        LPTable table = new LPTable();
        double[][] mainTable = new double[][]{
                {1, 3, 234, 142},
                {0, 3, 0, 1424},
                {0, -12313, 231, 4},
                {0, 0, 0, 3}
        };
        ArrayList<Double> function = new ArrayList<>();
        function.add(1.0);
        function.add(-3.0);
        function.add(-300.0);
        function.add(23.0);
        ArrayList<Double> restrictions = new ArrayList<>();
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
