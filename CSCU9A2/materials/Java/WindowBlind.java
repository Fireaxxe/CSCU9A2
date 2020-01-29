import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 *  This program is a Java application showing a single slider and drawing
 *  a window frame with a  blind adjustable using the slider.
 *
 */
public class WindowBlind extends JFrame
                         implements ChangeListener
{

    /**
     *  This is the slider that appears in the display.
     */
    private JSlider slider;

    /**
     *  This is the panel that is drawn on.
     */
    private JPanel panel;

    /**
     * This holds the current height of the blind, initially 50
     */
    private int blindHeight = 50;

    /**
     *  The main method is the main launch action for the WindowBlind program.
     */
    public static void main (String[] args)
    {
        WindowBlind frame = new WindowBlind();
        frame.setSize(400,400);
        frame.setLocation(200,200);
        frame.createGUI();
        frame.setVisible(true);
    } // end of main

    /**
     *  The createGUI method is called by the main method
     *  to set up the graphical user interface.
     */
    private void createGUI()
    {
        // Set up main window characteristics
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container window = getContentPane();
        window.setLayout(new FlowLayout() );

        // Create the adjustable slider, with correct initial setting
        slider = new JSlider(JSlider.HORIZONTAL, 0, 100, blindHeight);
        window.add(slider);
        slider.addChangeListener(this);

        // Create the panel for drawing on
        panel = new JPanel()
        {
            // paintComponent is called automatically when a screen refresh is needed
            public void paintComponent(Graphics g)
            {
                // g is a cleared panel area
                super.paintComponent(g); // Paint the panel's background
                paintScreen(g);          // Then the required graphics
            }
        };
        panel.setPreferredSize(new Dimension(300, 300));
        panel.setBackground(Color.cyan);
        window.add(panel);
    } // end of createGUI

    /**
     *  The stateChanged method is called automatically
     *  when the slider is adjusted:
     *  Fetch the setting, update blindHeight and refersh the screen
     */
    public void stateChanged(ChangeEvent e)
    {
        blindHeight = slider.getValue();
        repaint();  // Forces a screen refresh
    } // end of stateChanged

    /**
     * Redraw the graphics panel when the screen is refreshed:
     * A window frame with a blind partically covering the opening
     */
    private void paintScreen(Graphics g)
    {
        g.setColor(Color.black);
        g.drawRect(120, 80, 60, 100);         // The window frame
        g.fillRect(120, 80, 60, blindHeight); // The blind - height controlled by blindHeight
    } // end of paintScreen

} // end of WindowBlind

