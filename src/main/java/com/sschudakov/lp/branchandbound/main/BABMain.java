package com.sschudakov.lp.branchandbound.main;

import com.sschudakov.lp.branchandbound.input.ILPStringInput;
import com.sschudakov.lp.branchandbound.solver.BABILPSolver;
import com.sschudakov.lp.branchandbound.table.ILPTable;
import com.sschudakov.lp.branchandbound.visualization.BABSolvingVisualizer;
import com.sschudakov.lp.branchandbound.visualization.DrawingPanel;
import com.sschudakov.lp.simplexmethod.table.LPSolution;

import java.awt.BorderLayout;

public class BABMain {

    private static final String TASK_1 = "2 2" +
            " -1 -3" +
            " min" +
            " 1 4 <= 14" +
            " 2 3 <= 12";

    private static final String TASK_2 = "2 3" +
            " 5 7" +
            " min" +
            " -10 1 <= -16" +
            " -3 -3 <= -12" +
            " -6 -2 <= -17";

    private static final String TASK_3 = "2 3" +
            " -2 -1" +
            " min" +
            " 6 4 <= 24" +
            " 1 -1 <= 3" +
            " -1 3 <= 3";

    private static final String TASK_4 = "2 3" +
            " 1 1" +
            " max" +
            " 2 11 <= 36" +
            " 1 1 <= 7" +
            " 4 -5 <= 5";


    private static final String INTEGER_VARIABLES = "0 1";

    public static void main(String[] args) {

        ILPStringInput ilpStringInput = new ILPStringInput();
        ILPTable ilpTable = new ILPTable();
        ilpStringInput.inputILP(ilpTable, TASK_4, INTEGER_VARIABLES);
        BABILPSolver babilpSolver = new BABILPSolver();

        LPSolution integerSolution = babilpSolver.solve(ilpTable);

        System.out.println();
        System.out.println(babilpSolver.getTasksSolutions());
        System.out.println();

        BABSolvingVisualizer visualizer = new BABSolvingVisualizer();
        visualizer.setVisible(true);

        DrawingPanel drawingPanel = new DrawingPanel();

        drawingPanel.setSolvingIterations(babilpSolver.getTasksSolutions());
        drawingPanel.setIntegerSolution(integerSolution);

        visualizer.add(drawingPanel, BorderLayout.CENTER);

        visualizer.setVisible(true);
    }
}
