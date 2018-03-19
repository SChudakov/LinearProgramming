package com.sscudakov.simplex_method.table_building;

import com.sschudakov.simplex_method.table_building.SimplexTableBuilder;
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
        SimplexTableBuilder simplexTableBuilder = new SimplexTableBuilder();

        simplexTableBuilder.buildSimplexTable();

    }
}
