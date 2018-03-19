package com.sschudakov.simplex_method.table_building;

import com.sschudakov.simplex_method.enumerable.TaskType;
import com.sschudakov.simplex_method.input.ConsoleValuesInput;
import com.sschudakov.simplex_method.table.SimplexTable;

import java.util.ArrayList;

/**
 * Created by Semen Chudakov on 30.09.2017.
 */
public class SimplexTableBuilder {

    private ConsoleValuesInput valuesInput;
    private SimplexTableModifier tableModifier;
    private BasisFinder basisFinder;
    private MBasisBuilder basisBuilder;

    public SimplexTableBuilder() {
        this.valuesInput = new ConsoleValuesInput();
        this.tableModifier = new SimplexTableModifier();
        this.basisFinder = new BasisFinder();
        this.basisBuilder = new MBasisBuilder();
    }

    public SimplexTable buildSimplexTable() {

        SimplexTable table = this.valuesInput.inputValues();
        buildTableVectors(table);
        System.out.println("\nafter values input\n");
        table.outputTable();

        this.tableModifier.modifyTable(table, TaskType.MIN);
        rebuildVectors(table);
        System.out.println("\nafter table modification\n");
        table.outputTable();

        this.basisFinder.findBasis(table);
        System.out.println("\nafter finding basis\n");
        table.outputTable();

        this.basisBuilder.buildMBasis(table);
        rebuildVectors(table);
        System.out.println("\nafter mbasis building\n");
        table.outputTable();

        return table;
    }

    private void buildTableVectors(SimplexTable table) {
        ArrayList<Integer> basicVariables = new ArrayList<>();
        ArrayList<Double> deltas = new ArrayList<>();
        ArrayList<Double> simplexRatios = new ArrayList<>();

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

    private void rebuildVectors(SimplexTable table) {
        ArrayList<Double> deltas = table.getDeltasVector();
        ArrayList<Double> simplexRatios = table.getSimplexRatios();

        for (int i = deltas.size(); i < table.getNumOfVariables(); i++) {
            deltas.add(0.0);
            simplexRatios.add(0.0);
        }
    }
}
