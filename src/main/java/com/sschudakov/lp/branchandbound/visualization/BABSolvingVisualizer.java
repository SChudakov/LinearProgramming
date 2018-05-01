package com.sschudakov.lp.branchandbound.visualization;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;

public class BABSolvingVisualizer extends JFrame {

    private static final double FRAME_SIZE_COEFFICIENT = 0.9;

    public BABSolvingVisualizer() throws HeadlessException {
        super();
        configureFrameSize();
        setTitle("Integer linear program solving process");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void configureFrameSize() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        double frameWidth = width * FRAME_SIZE_COEFFICIENT;
        double frameHeight = height * FRAME_SIZE_COEFFICIENT;
        this.setSize(
                (int) frameWidth,
                (int) frameHeight
        );
    }
}
