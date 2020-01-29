import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/**
A frame that shows a blank drawing area.
 */
public class Drawing extends JFrame
{
    /**
     * Configuration constants for the position of the frame on screen
     */
    private static final int FRAME_X = 200;
    private static final int FRAME_Y = 200;

    /**
     * Configuration constants for the frame size
     */
    private static final int FRAME_WIDTH = 500;
    private static final int FRAME_HEIGHT = 500;

    /**
     * Configuration constants for the drawing area size
     */
    private static final int DRAWING_WIDTH = 450;
    private static final int DRAWING_HEIGHT = 450;

    /**
     *  This is the panel that is drawn on.
     */
    private JPanel drawingArea;

    /**
     *  The main method is the main launch action for the Drawing program.
     */
    public static void main(String[] args)
    {
        Drawing frame = new Drawing();
        frame.setTitle("A drawing area");
        frame.setLocation(FRAME_X, FRAME_Y);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.createGUI();
        frame.setVisible(true);
    }

    /**
     * This method builds the graphical user interface
     * - just one simple drawing panel in this application.
     */
    public void createGUI()
    {
        // Set up main window characteristics
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container window = getContentPane();

        window.setLayout(new FlowLayout() );

        // Create the panel for drawing on
        drawingArea = new JPanel()
        {
            // paintComponent is called automatically when a screen refresh is needed
            public void paintComponent(Graphics g)
            {
                // g is a cleared panel area
                super.paintComponent(g); // Paint the panel's background
                paintScreen(g);          // Then the required graphics
            }
        };
        drawingArea.setPreferredSize(new Dimension(DRAWING_WIDTH, DRAWING_HEIGHT));
        drawingArea.setBackground(Color.white);
        window.add(drawingArea);

    }

    /**
     * Redraw the drawing panel when the screen is refreshed:
     */
    private void paintScreen(Graphics g)
    {
        // Put drawing instructions here, e.g.
        g.setColor(Color.green);
        g.fillRect(0, 0, DRAWING_WIDTH, DRAWING_HEIGHT);
        g.setColor(Color.red);
        g.drawRect(20, 50, 200, 100);
    }

}
