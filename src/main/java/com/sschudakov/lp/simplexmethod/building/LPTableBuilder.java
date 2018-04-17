package com.sschudakov.lp.simplexmethod.building;

import com.sschudakov.lp.simplexmethod.enumerable.TaskType;
import com.sschudakov.lp.simplexmethod.table.LPTable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Semen Chudakov on 30.09.2017.
 */
public class LPTableBuilder {

    private static Logger logger = LogManager.getLogger(LPTableBuilder.class);

    private LPTableModifier tableModifier;
    private BasisFinder basisFinder;
    private MBasisBuilder basisBuilder;

    public LPTableBuilder() {
        this.tableModifier = new LPTableModifier();
        this.basisFinder = new BasisFinder();
        this.basisBuilder = new MBasisBuilder();
    }

    public LPTable buildSimplexTable(LPTable lpTable) {
        initializeTableLists(lpTable);

        this.tableModifier.modifyTable(lpTable, TaskType.MIN);
        logger.info("after table modification and lists rebuilding\n");
        lpTable.outputTable();

        buildBasicVariablesList(lpTable);
        this.basisFinder.findBasis(lpTable);
        logger.info("after finding basis\n");
        lpTable.outputTable();

        this.basisBuilder.buildMBasis(lpTable);
        buildDeltasAndSimplexRatiosLists(lpTable);
        logger.info("after mbasis building\n");
        lpTable.outputTable();

        return lpTable;
    }

    private void buildBasicVariablesList(LPTable table) {
        List<Integer> basicVariables = table.getBasicVariables();

        for (int i = basicVariables.size(); i < table.getNumOfEquations(); i++) {
            basicVariables.add(-1);
        }
    }


    private void buildDeltasAndSimplexRatiosLists(LPTable table) {
        List<Double> deltas = table.getDeltasVector();
        List<Double> simplexRatios = table.getSimplexRatios();

        for (int i = deltas.size(); i < table.getNumOfVariables(); i++) {
            deltas.add(0.0);
        }
        for (int i = simplexRatios.size(); i < table.getNumOfVariables(); i++) {
            simplexRatios.add(0.0);
        }
    }

    private void initializeTableLists(LPTable table) {
        if (table.getBasicVariables() == null) {
            table.setBasicVariables(new ArrayList<>());
        }
        if (table.getDeltasVector() == null) {
            table.setDeltasVector(new ArrayList<>());
        }
        if (table.getSimplexRatios() == null) {
            table.setSimplexRatios(new ArrayList<>());
        }
    }
}
