package com.sschudakov.simplex_method.solver;

import com.sschudakov.simplex_method.table.SimplexTable;
import com.sschudakov.simplex_method.exception.TableAlreadyOptimalException;
import com.sschudakov.simplex_method.exception.UnlimitedFunctionException;
import com.sschudakov.simplex_method.util.AnswerFormer;

import java.util.Vector;

/**
 * Created by Semen Chudakov on 10.09.2017.
 */
public class SimplexTableSolver {

    private SimplexTable simplexTable;

    //getters and setters
    public SimplexTable getSimplexTable() {
        return simplexTable;
    }

    public void setSimplexTable(SimplexTable table) {
        this.simplexTable = table;
    }

    public SimplexTableSolver(SimplexTable table) {
        this.simplexTable = table;
    }


    public void solveSimplexTable() {
        int i = 1;
        while (!this.simplexTable.isDualOptimal()) {
            recountSimplexTable();
            System.out.println("\nrecounted " + i + " time\n");
            i++;
            this.simplexTable.outputTable();
        }

        AnswerFormer.formAnswer(this.simplexTable);
    }

    private void recountSimplexTable() {

        recountDeltas();

        int resolvingLine = findResolvingLine();
        int resolvingColumn = findResolvingColumn(resolvingLine);

        recountMainTable(resolvingLine, resolvingColumn);
    }

    public void recountMainTable(int resolvingLine, int resolvingColumn) {
        double[][] mainTable = this.simplexTable.getMainTable();
        Vector<Double> restrictions = this.simplexTable.getRestrictionsVector();
        double resolvingElementValue = mainTable[resolvingLine][resolvingColumn];
        double[][] recountedTable = new double[mainTable.length][mainTable[0].length];

        // recount resolving line in table
        double recountedValue;
        for (int i = 0; i < mainTable[resolvingLine].length; i++) {
            recountedValue = mainTable[resolvingLine][i] / resolvingElementValue;
            if (recountedValue != 0) {
                recountedTable[resolvingLine][i] = recountedValue;
            }else{
                recountedTable[resolvingLine][i] = Math.abs(recountedValue);
            }
        }
        // set resolving element to 1
        recountedTable[resolvingLine][resolvingColumn] = 1;

        //recount restrictions
        double recountedRestrictionValue;
        for (int i = 0; i < restrictions.size(); i++) {
            if (i != resolvingLine) {
                recountedRestrictionValue = (restrictions.get(i) * mainTable[resolvingLine][resolvingColumn]
                        - restrictions.get(resolvingLine) * mainTable[i][resolvingColumn]) / resolvingElementValue;
                restrictions.set(i, recountedRestrictionValue);
            }
        }
        // set restriction in resolving line
        restrictions.set(resolvingLine, restrictions.get(resolvingLine) / resolvingElementValue);
        // recount other elements in table
        for (int i = 0; i < mainTable.length; i++) {
            if (i != resolvingLine) {
                for (int j = 0; j < mainTable[i].length; j++) {
                    if (j != resolvingColumn) {
                        recountedTable[i][j] = (mainTable[resolvingLine][resolvingColumn] * mainTable[i][j]
                                - mainTable[resolvingLine][j] * mainTable[i][resolvingColumn]) / resolvingElementValue;
                    }
                }
            }
        }

        this.simplexTable.setMainTable(recountedTable);

        this.simplexTable.getBasicVariables().set(resolvingLine, resolvingColumn);
    }

    private void recountDeltas() {
        Vector<Double> deltas = this.simplexTable.getDeltasVector();

        if (deltas.size() == 0) {
            for (int i = 0; i < this.simplexTable.getNumOfVariables(); i++) {
                deltas.add(recountDelta(i));
            }
        } else {
            for (int i = 0; i < deltas.size(); i++) {
                deltas.set(i, recountDelta(i));
            }
        }
    }

    private double recountDelta(int column) {

        double result = this.simplexTable.getFunction().get(column);
        int numOfEquations = this.simplexTable.getNumOfEquations();
        double[][] mainTable = this.simplexTable.getMainTable();

        for (int i = 0; i < numOfEquations; i++) {
            result -= this.simplexTable.getFunction().get(
                    this.simplexTable.getBasicVariables().get(i)) * mainTable[i][column];
        }
        return result;
    }

    private int findResolvingLine() {

        if (this.simplexTable.isDualOptimal()) {
            throw new TableAlreadyOptimalException();
        }
        Vector<Double> restrictions = this.simplexTable.getRestrictionsVector();

        int position = tellPositionOfFirstNegative(restrictions);
        double value = restrictions.get(position);

        for (int i = 0; i < restrictions.size(); i++) {
            if (restrictions.get(i) < 0) {
                if (restrictions.get(i) < value) {
                    position = i;
                    value = restrictions.get(i);
                }
            }
        }
        return position;
    }

    private int findResolvingColumn(int resolvingLine) {

        recountSimplexRatios(resolvingLine);

        Vector<Double> simplexRatios = this.simplexTable.getSimplexRatios();
        int position = 0;
        double currentRatio = simplexRatios.get(0);

        for (int i = 0; i < simplexRatios.size(); i++) {
            if (simplexRatios.get(i) < currentRatio) {
                position = i;
                currentRatio = simplexRatios.get(i);
            }
        }

        return position;
    }

    private void recountSimplexRatios(int resolvingLine) {

        if (!hasNegatives(this.simplexTable.getMainTable(), resolvingLine)) {
            throw new UnlimitedFunctionException("table is unlimited according to the line " + resolvingLine);
        }

        double[][] mainTable = this.simplexTable.getMainTable();
        Vector<Double> deltas = this.simplexTable.getDeltasVector();
        Vector<Double> simplexRatios = this.simplexTable.getSimplexRatios();


        for (int i = 0; i < mainTable[resolvingLine].length; i++) {
            if (mainTable[resolvingLine][i] < 0) {
                simplexRatios.set(i, deltas.get(i) / Math.abs(mainTable[resolvingLine][i]));
            } else {
                simplexRatios.set(i, Double.MAX_VALUE);
            }
        }

    }

    private boolean hasNegatives(double[][] mainTable, int line) {
        for (double v : mainTable[line]) {
            if (v < 0) {
                return true;
            }
        }
        return false;
    }

    private int tellPositionOfFirstNegative(Vector<Double> vector){
        for(int i = 0; i < vector.size(); i++){
            if(vector.get(i) < 0){
                return i;
            }
        }
        throw new IllegalArgumentException("vector " + vector.toString() + " has no negatives");
    }
}
