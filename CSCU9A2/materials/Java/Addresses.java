import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 * A simple address database for Practical 5B
 * Demonstrates arrays, graphics, selection.
 */
public class Addresses extends JFrame
                       implements ChangeListener 
{
    /**
     * Frame coordinate constants
     */
    private static final int FRAME_X = 200;
    private static final int FRAME_Y = 200;

    /**
     * Frame size constants
     */
    private static final int FRAME_WIDTH = 500;
    private static final int FRAME_HEIGHT = 400;
    
    /**
     *  The slider for selecting an address record (selectable 0 to size-1).
     */
    private JSlider selector;

    /**
     * Array to hold the database.
     */
    private String[] names;

    /**
     * To indicate which entry is currently selected.
     */
    private int selected;

    /**
     *  The drawing panel for display of information.
     */
    private JPanel panel;

    /**
     *  Drawing panel size constants
     */
    private final int PANEL_WIDTH = 400;
    private final int PANEL_HEIGHT = 300;

    /**
     *  The main program launcher for the Addresses class.
     *
     * @param  args  The command line arguments (ignored here).
     */
    public static void main( String[] args ) 
    {
        Addresses frame = new Addresses();
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setUpData();       // Initial data set-up
        frame.createGUI();       // Initial GUI set-up
        frame.setVisible( true );
    } 

    /**
     *  Sets up the graphical user interface.
     */
    private void createGUI() 
    {
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        Container window = getContentPane();
        window.setLayout( new FlowLayout() );

        // Slider for selecting an address entry
        selector = new JSlider(JSlider.VERTICAL, 0, 0, 0);
                   // Note: the useable range of the slider is 0 - 0 at the moment!
        window.add(selector);
        selector.addChangeListener(this);

        // Graphics panel for displaying the address list
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
        panel.setPreferredSize( new Dimension( PANEL_WIDTH, PANEL_HEIGHT ) );
        panel.setBackground( Color.white );
        window.add( panel );
    }

    /**
     * Helper method to set up the array data, and the associated variable selected,
     * with their initial configuration.
     */
    private void setUpData()
    {  
        // All the data is "built-in": set it up here - just one entry at the moment
        names = new String[1];         // Create the array with space for one entry
        names[0] = "James";            // The entry

        selected = 0;                  // Indicate that entry 0 is selected
    }

    /**
     * This methods redraws the screen.
     */
    private void paintScreen(Graphics g) 
    {
        displayList(g);
        displaySelected(g);
    }

    /**
     * Display all the elements of array names in a column on the screen.
     */
    private void displayList(Graphics g) 
    {
        int y = 100;                       // Top y coordinate of the column

        g.setColor(Color.black);
        g.drawString(names[0], 20, y);

 /*
        for (int i = ...; ...; ...)
            g.drawString(names[i], 20, y+15*i);
 */
    } 

    /**
     * Display the single element of array names that is currently selected by the slider.
     */
    private void displaySelected(Graphics g) 
    {
        g.setColor(Color.black);
        g.drawString("Current selection is:", 200, 135);
        g.drawString(names[0], 200, 150);
    }

    /**
     *  Reacts to adjustment of the slider.
     *  Notes the new selector setting, then forces screen refresh.
     */
    public void stateChanged( ChangeEvent e ) 
    {
        // selector has been adjusted: record the new setting
        selected = selector.getValue();
        repaint(); // Refresh the screen
    }

}
