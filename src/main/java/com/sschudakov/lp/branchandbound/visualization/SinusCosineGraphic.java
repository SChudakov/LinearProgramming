package com.sschudakov.lp.branchandbound.visualization;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class SinusCosineGraphic extends JFrame {

    private SinusCosineGraphic() {
        setLayout(new BorderLayout());
        add(new DrawSine(), BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SinusCosineGraphic frame = new SinusCosineGraphic();
        frame.setSize(400, 300);
        frame.setTitle("SinusCosineGraphic");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    class DrawSine extends JPanel {

        double sin(double x) {
            return Math.sin(x);
        }

        double cos(double y) {
            return Math.cos(y);
        }

        protected void paintComponent(Graphics graphics)
        {
            super.paintComponent(graphics);

            graphics.drawLine(10, 100, 380, 100);
            graphics.drawLine(200, 30, 200, 190);

            graphics.drawLine(380, 100, 370, 90);
            graphics.drawLine(380, 100, 370, 110);
            graphics.drawLine(200, 30, 190, 40);
            graphics.drawLine(200, 30, 210, 40);

            graphics.drawString("X", 360, 80);
            graphics.drawString("Y", 220, 40);

            Polygon p = new Polygon();
            Polygon p2 = new Polygon();

            for (int x = -170; x <= 170; x++) {
                p.addPoint(x + 200, 100 - (int) (50 * sin((x / 100.0) * 2
                        * Math.PI)));

            }

            for (int x = -170; x <= 170; x++) {
                p2.addPoint(x + 200, 100 - (int) (50 * cos((x / 100.0) * 2
                        * Math.PI)));

            }

            graphics.setColor(Color.red);
            graphics.drawPolyline(p.xpoints, p.ypoints, p.npoints);
            graphics.drawString("-2\u03c0", 95, 115);
            graphics.drawString("2\u03c0", 305, 115);
            graphics.drawString("0", 200, 115);

            graphics.setColor(Color.blue);
            graphics.drawPolyline(p2.xpoints, p2.ypoints, p2.npoints);

        }
    }
}