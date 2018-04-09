package com.sschudakov.simplexmethod.table_building;

import com.sschudakov.simplexmethod.enumerable.TaskType;
import com.sschudakov.simplexmethod.table.LPTable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Semen Chudakov on 30.09.2017.
 */
public class LPTableBuilder {

    private LPTableModifier tableModifier;
    private BasisFinder basisFinder;
    private MBasisBuilder basisBuilder;

    public LPTableBuilder() {
        this.tableModifier = new LPTableModifier();
        this.basisFinder = new BasisFinder();
        this.basisBuilder = new MBasisBuilder();
    }

    public LPTable buildSimplexTable(LPTable lpTable) {
        System.out.println("\nafter building table lists\n");
        buildTableLists(lpTable);
        lpTable.outputTable();


        this.tableModifier.modifyTable(lpTable, TaskType.MIN);
        rebuildTableLists(lpTable);
        System.out.println("\nafter table modification\n");
        lpTable.outputTable();

        this.basisFinder.findBasis(lpTable);
        System.out.println("\nafter finding basis\n");
        lpTable.outputTable();

        this.basisBuilder.buildMBasis(lpTable);
        rebuildTableLists(lpTable);
        System.out.println("\nafter mbasis building\n");
        lpTable.outputTable();

        return lpTable;
    }

    private void buildTableLists(LPTable table) {
        List<Integer> basicVariables = new ArrayList<>();
        List<Double> deltas = new ArrayList<>();
        List<Double> simplexRatios = new ArrayList<>();

        for (int i = 0; i < table.getNumOfEquations(); i++) {
            basicVariables.add(-1);
        }
        for (int i = 0; i < table.getNumOfVariables(); i++) {
            deltas.add(0.0);
            simplexRatios.add(0.0);
        }
        table.setBasicVariables(basicVariables);
        table.setDeltasVector(deltas);
        table.setSimplexRatios(simplexRatios);
    }

    private void rebuildTableLists(LPTable table) {
        List<Double> deltas = table.getDeltasVector();
        List<Double> simplexRatios = table.getSimplexRatios();

        for (int i = deltas.size(); i < table.getNumOfVariables(); i++) {
            deltas.add(0.0);
            simplexRatios.add(0.0);
        }
    }
}
