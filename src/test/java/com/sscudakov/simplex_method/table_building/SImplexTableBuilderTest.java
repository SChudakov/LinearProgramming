package com.sscudakov.simplex_method.table_building;

import com.sschudakov.simplexmethod.table.LPTable;
import com.sschudakov.simplexmethod.building.LPTableBuilder;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Created by Semen Chudakov on 11.10.2017.
 */
public class SImplexTableBuilderTest {

    private static final String LINEAR_TASK =
            "5 3" +
                    "1 -1 1 -3 2" +
                    "1 2 -1 2 1" +
                    "-1 -1 1 3 -2" +
                    "2 -1 3 -1 2 " +
                    "<= = >=" +
                    "8 10 4";

    @Ignore
    @Test
    public void buildSimplexTableTest() {
        LPTable lpTable = new LPTable();
        LPTableBuilder LPTableBuilder = new LPTableBuilder();
        LPTableBuilder.buildSimplexTable(lpTable);

    }
}
