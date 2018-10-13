package com.sschudakov.lp.branchandbound.visualization;

import com.sschudakov.lp.branchandbound.solver.BABILPSolver;
import com.sschudakov.lp.simplexmethod.table.LPRestriction;
import com.sschudakov.lp.simplexmethod.table.LPSolution;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.List;

public class DrawingPanel extends JPanel {

    private static final int AXISES_ARROWS_LENGTH = 10;
    private static final int SCALE = 50;
    private static final int SLEEP_TIME = 1000;

    private List<List<BABILPSolver.SolutionAndTable>> solvingIterations;
    private LPSolution integerSolution;
    private List<String> xLabels;
    private List<String> yLabels;

    public void setSolvingIterations(List<List<BABILPSolver.SolutionAndTable>> solvingIterations) {
        this.solvingIterations = solvingIterations;
    }

    public void setIntegerSolution(LPSolution integerSolution) {
        this.integerSolution = integerSolution;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        this.xLabels = new ArrayList<>();
        this.yLabels = new ArrayList<>();
        super.paintComponent(graphics);
        visualizeSolvingProcess(graphics, this.solvingIterations);
    }

    private void visualizeSolvingProcess(Graphics graphics, List<List<BABILPSolver.SolutionAndTable>> iterations) {
        drawCoordinateAxises(graphics);
        drawInitialTableRestrictions(graphics, iterations.get(0).get(0).getTable().getMainTable());
        drawSolutionVector(graphics, iterations.get(0).get(0).getSolution().getSolutionVector(), Color.BLACK);
        /*iterations.get(0).remove(0);*/

        for (int i = 1; i < iterations.size(); i++) {
            drawSolvingIteration(graphics, iterations.get(i));
        }

        drawSolutionVector(graphics, this.integerSolution.getSolutionVector(), Color.GREEN);
    }

    private void drawInitialTableRestrictions(Graphics graphics, List<LPRestriction> initialMainTable) {
        for (LPRestriction lpRestriction : initialMainTable) {
            drawRestriction(graphics, lpRestriction, Color.BLUE);
        }
    }

    private void drawSolvingIteration(Graphics graphics, List<BABILPSolver.SolutionAndTable> iteration) {
        for (int i = 0; i < iteration.size(); i++) {
            List<LPRestriction> mainTable = iteration.get(i).getTable().getMainTable();
            LPSolution solution = iteration.get(i).getSolution();

            drawRestriction(graphics, mainTable.get(mainTable.size() - 1), Color.RED);

            if (!solution.endedWithException()) {
                drawSolutionVector(graphics, solution.getSolutionVector(), Color.BLACK);
            }
        }
    }

    private void drawRestriction(Graphics graphics, LPRestriction restriction, Color color) {
        drawLine(graphics, restriction, color);
        drawAxisesCrossingsLabels(graphics, restriction);
    }

    private void drawLine(Graphics graphics, LPRestriction restriction, Color color) {
        double xCoefficient = restriction.getCondition().get(0);
        double yCoefficient = restriction.getCondition().get(1);
        double rightPartValue = restriction.getRightPartValue();

        Polygon line = new Polygon();

        int xLeftBorder = -getWidth();
        int xRightBorder = getWidth();

        if (xCoefficient == 0) {
            for (int i = xLeftBorder; i < xRightBorder; i++) {
                line.addPoint(
                        recountX(scale(i)),
                        recountY(scale(rightPartValue / yCoefficient))
                );
            }
        } else {
            if (yCoefficient == 0) {
                for (int i = xLeftBorder; i < xRightBorder; i++) {
                    line.addPoint(
                            recountX(scale(rightPartValue / xCoefficient)),
                            recountX(scale(i))
                    );
                }
            } else {
                for (int i = xLeftBorder; i < xRightBorder; i++) {
                    double x = i;
                    double y = calculateY(i, xCoefficient, yCoefficient, rightPartValue);

                    line.addPoint(
                            recountX(scale(x)),
                            recountY(scale(y))
                    );
                }
            }
        }
        graphics.setColor(color);
        graphics.drawPolyline(line.xpoints, line.ypoints, line.npoints);
    }

    private void drawAxisesCrossingsLabels(Graphics graphics, LPRestriction restriction) {
        double xAxisCrossingPoint = restriction.getRightPartValue() / restriction.getCondition().get(0);
        double yAxisCrossingPoint = restriction.getRightPartValue() / restriction.getCondition().get(1);

        graphics.setColor(Color.BLACK);

        String xLabel = formatLabelValue(xAxisCrossingPoint);
        String yLabel = formatLabelValue(yAxisCrossingPoint);

        if (!this.xLabels.contains(xLabel)) {
            graphics.drawString(
                    xLabel,
                    recountX(scale(xAxisCrossingPoint)),
                    recountY(scale(0)) + 10
            );
            this.xLabels.add(xLabel);
        }
        if (!this.yLabels.contains(yLabel)) {
            graphics.drawString(
                    yLabel,
                    recountX(scale(0)) - 20,
                    recountY(scale(yAxisCrossingPoint))
            );
            this.yLabels.add(yLabel);
        }
    }

    private String formatLabelValue(double number) {
        String result;
        if ((int) number == number) {
            StringBuilder label = new StringBuilder().append(number);
            label.substring(0, label.length() - 2);
            result = label.toString();
        } else {
            result = String.format("%.2f", number);
        }
        return result;
    }


    private double scale(double point) {
        return (point * SCALE);
    }

    private int recountX(double x) {
        int panelWidth = getWidth();
        return (int) (x + panelWidth / 2);
    }

    private int recountY(double y) {
        int panelHeight = getHeight();
        return (int) ((panelHeight - y) / 2);
    }

    private double calculateY(double x, double xCoefficient, double yCoefficient, double rightPartValue) {
        return (rightPartValue - x * xCoefficient) / yCoefficient;
    }

    private void drawSolutionVector(Graphics graphics, List<Double> solutionVector, Color color) {
        double xCentre = solutionVector.get(0);
        double yCentre = solutionVector.get(1);

        double recountedX = recountX(scale(xCentre));
        double recountedY = recountY(scale(yCentre));

        drawCircle(graphics, recountedX, recountedY, color);

        /*drawPointLabel(graphics, recountedX, recountedY, xCentre, yCentre);*/
        drawSolutionAxisesLabels(graphics, xCentre, yCentre, recountedX, recountedY);
    }

    private void drawCircle(Graphics graphics, double x, double y, Color color) {
        graphics.setColor(color);

        graphics.drawOval(
                (int) (x - 4),
                (int) (y - 4),
                8,
                8);
    }

    private void drawSolutionAxisesLabels(Graphics graphics, double xAxisLabelValue, double yAxisLabelValue,
                                          double recountedX, double recountedY) {
        graphics.setColor(Color.BLACK);


        String xLabel = String.valueOf(formatLabelValue(xAxisLabelValue));
        String yLabel = String.valueOf(formatLabelValue(yAxisLabelValue));

        if (!this.xLabels.contains(xLabel)) {
            graphics.drawString(
                    xLabel,
                    (int) recountedX,
                    recountY(0) - 2
            );
            this.xLabels.add(xLabel);
        }
        if (!this.yLabels.contains(yLabel)) {
            graphics.drawString(
                    yLabel,
                    recountX(0) + 5,
                    (int) recountedY
            );
            this.yLabels.add(yLabel);
        }
    }

    private void drawPointLabel(Graphics graphics, double labelX, double labelY, double x, double y) {
        graphics.setColor(Color.BLACK);

        StringBuilder label = new StringBuilder();
        label.append("(")
                .append(String.valueOf(x))
                .append(":")
                .append(String.valueOf(y))
                .append(")");

        graphics.drawString(
                label.toString(),
                (int) labelX + 5,
                (int) labelY - 5
        );
    }

    private void drawCoordinateAxises(Graphics graphics) {
        drawXAxis(graphics);
        drawYAxis(graphics);
    }

    private void drawXAxis(Graphics graphics) {
        int panelHeight = getHeight();
        int panelWidth = getWidth();
        int middleY = panelHeight / 2;
        graphics.drawLine(0, middleY, panelWidth, middleY);

        graphics.drawLine(panelWidth - AXISES_ARROWS_LENGTH, middleY - AXISES_ARROWS_LENGTH, panelWidth, middleY);
        graphics.drawLine(panelWidth - AXISES_ARROWS_LENGTH, middleY + AXISES_ARROWS_LENGTH, panelWidth, middleY);
    }

    private void drawYAxis(Graphics graphics) {
        int panelHeight = getHeight();
        int panelWidth = getWidth();
        int middleX = panelWidth / 2;
        graphics.drawLine(middleX, panelHeight, middleX, 0);

        graphics.drawLine(middleX - AXISES_ARROWS_LENGTH, AXISES_ARROWS_LENGTH, middleX, 0);
        graphics.drawLine(middleX + AXISES_ARROWS_LENGTH, AXISES_ARROWS_LENGTH, middleX, 0);
    }
}
