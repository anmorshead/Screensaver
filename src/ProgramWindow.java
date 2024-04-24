

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ProgramWindow extends JFrame {     //The class used as the main window for your GUI screensaver program.
    public ProgramWindow() {
        this.setBounds(100, 100, 1000, 1000);
        this.setTitle("Screen Saver");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        DrawingBoard drawing = new DrawingBoard();
        drawing.setBackground(Color.BLACK);
        drawing.setBorder(new EmptyBorder(5, 5, 5, 5));
        drawing.setLayout(new CardLayout(0, 0));

        this.add(drawing);
    }
}