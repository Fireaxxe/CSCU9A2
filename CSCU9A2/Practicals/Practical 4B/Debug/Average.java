import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *  A debugging exercise for CSC9A2
 *
 *  This application holds a collection of integer values in an array.
 *  Values can be added to the end of the collection by entering them into
 *  a text field and pressing Enter.
 *  The current list is always displayed in a text area, together with
 *  the average of those values in the collection that fall in the range
 *  10 - 20 inclusive.
 *
 *  Note: This is a debugging exercise, so the code below contains bugs,
 *  although it compiles perfectly well.
 *  
 *  SBJ April 2014
 */
public class Average extends JFrame
implements ActionListener
{
    /**
     * Frame coordinate constants
     */
    private static final int FRAME_X = 200;
    private static final int FRAME_Y = 200;

    /**
     * Configuration constants for the frame size
     */
    private static final int FRAME_WIDTH = 500;
    private static final int FRAME_HEIGHT = 400;

    /**
     * Configuration constants for the text area size
     */
    private static final int AREA_ROWS = 20;
    private static final int AREA_COLUMNS = 30;

    /**
     * Hold the GUI components, including the averages display area
     * that holds the actual rainfall data.
     */
    private JTextField dataEntryField;
    private JTextArea displayArea;

    /** 
     * A constant to be used as the maximum array size. 
     */
    private final int size = 10;

    /** 
     * An array to hold data to be averaged.
     * This array will not always have actual data in every element.
     * As data is added it will be placed in elements 0, 1, 2 in that order,
     * and the variable "top" declared below will always indicate where the
     * most recently added item was placed.
     */
    private int[] data;

    /** 
     * This holds the index of the last inserted data element.
     * So: -1 if there is no data, 0 when one item is present
     *      1 when there are two items, and so on
     */
    private int top;

    /**
     * The main launcher method
     * @param args Unused
     */
    public static void main(String[] args)
    {  
        Average frame = new Average();
        frame.setTitle("Averages application - debugging exercise");
        frame.setLocation(FRAME_X, FRAME_Y);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.createGUI();       // Initial GUI set-up
        frame.setUpData();       // Initial data set-up
        frame.setVisible(true);
    }

    /**
     * Helper method to build the GUI
     */
    private void createGUI()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container window = getContentPane();
        window.setLayout(new FlowLayout());

        // Data entry label and text field
        JLabel dataEntryLabel = new JLabel("Type an integer and press Enter: ");
        dataEntryField = new JTextField("", 5);     // Constructor: initial text and size
        dataEntryField.addActionListener(this);

        // Display area for data and average display
        displayArea = new JTextArea("", AREA_ROWS, AREA_COLUMNS);  // Constructor: initial text and size
        displayArea.setEditable(false);

        // And now build the whole GUI
        window.add(dataEntryLabel);
        window.add(dataEntryField);
        window.add(displayArea);
    }

    /**
     * Helper method to set up the array data, and the associated variable top
     * with their initial configuration.
     */
    private void setUpData()
    {  
        // Create the array storage
        data = new int[size];
        // Set top to indicate that there is no actual data yet
        top = -1;

        /*// Here's some starter data: just two items
        top++;                                     // Increase top to indicate the next 'vacant' element
        data[top] = 9;                            // ... and add some data into that element (element 0).
        top++;                                     // And again
        data[top] = 21;                            // ... with data into element 1 this time.
         */
        // Make sure that the text area is up-to-date immediately
        doDisplay();
    }

    /**
     * actionPerformed method for adding a new data item to the record when Enter is pressed,
     * calculating the new average of values in the range 10 - 20 inclusive,
     * and updating the display with the data and the average.
     */
    public void actionPerformed(ActionEvent event)
    {
        // Fetch and process the new data item:
        // Grab the item and convert to an int
        try{
            int n = Integer.parseInt(dataEntryField.getText());

            // Adjust top to indicate next free element and put the new value in that element
            top++; 
            data[top] = n;
            // Clear the text field
            dataEntryField.setText("");
            // And refresh the display - show the data, calculate and show the average
            doDisplay();
        }
        catch (Exception e)
        {
        }
    }            

    /**
     * Redisplay the data list on-screen, calculate average and display it.
     */
    public void doDisplay() 
    {
        // Clear the text area (set to an empty string)
        displayArea.setText("");

        // Display the elements from 0 to top-1
        for (int i = 0; i<size-1; i++)
            displayArea.append(data[i] + "\n");  //Note: "\n" means a new-line

        // Calculate and display the average of only those elements
        // with values from 10 to 20 inclusive
        int total = 0;  // This accumulates the total
        int count = 0;   // This counts how many have been added to the total
        for (int i = 0; i<size-1; i++) 
        {
            if (10 <= data[i] && data[i] <= 20)    // Is next item in range 10 - 20?
            { total = total + data[i];         // Yes, add to total
                count++;}                         // And count it
            else
            {}
        }

        // Finally calculate and display the average
        float average = (float)total/count;        // Note: (float) forces a float division
        displayArea.append("\nAverage is " + average);

    }

}
