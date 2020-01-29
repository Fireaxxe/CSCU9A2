/* A simple arrays "laboratory" for exercises in Practical 5B.

   Provides basic storage for an array of strings, and a simple text area interface
   for displaying the array and for invoking actions through a button and a slider.

   Exercises consist of various array processing algorithms to be inserted
   into the doSomething method (at the bottom of the program), which is called when
   the button is pressed.

   sbj April 2014
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class Arrays extends JFrame
                    implements ChangeListener, ActionListener 
{
    /**
     * Frame coordinate constants
     */
    private static final int FRAME_X = 200;
    private static final int FRAME_Y = 200;

    /**
     * Frame size constants
     */
    private static final int FRAME_WIDTH = 400;
    private static final int FRAME_HEIGHT = 300;
    
    /**
     *  The number of data items to be held
     */
    private final int size = 10;

    /**
     *  Holds the data items to be manipulated.
     */
    private String[] items = new String[size];

    /**
     *  To indicate which item is currently selected, 0th item initially.
     */
    private int selected = 0;

    /**
     *  The slider for selecting a data item (selectable 0 to size-1).
     */
    private JSlider selector = new JSlider(JSlider.HORIZONTAL,0,size-1,selected);

    /**
     *  Click this button to do something.
     */
    private JButton act = new JButton("Act now");

    /**
     *  For displaying the data items - enough lines for the whole array.
     */
    private JTextArea display = new JTextArea( size+2, 20 );

    /**
     *  The main program launcher for the Arrays class.
     */
    public static void main( String[] args ) 
    {
        Arrays frame = new Arrays();
        frame.setSize( FRAME_WIDTH, FRAME_HEIGHT );
        frame.setUpData();
        frame.createGUI();
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

        // The Act Now button
        window.add(act);
        act.addActionListener(this);

        // The element selector slider
        window.add(selector);
        selector.addChangeListener(this);

        // Add text area display and immediately update it on the screen
        window.add( display );
        displayItems();
    }

    /**
     * Helper method to set up the array data
     */
    private void setUpData()
    {        
        // All the data is "built-in": set it up here
        // DO NOT CHANGE THESE ASSIGNMENT STATEMENTS!
        items[0] = "Alpha";
        items[1] = "Beta";
        items[2] = "Gamma";
        items[3] = "Delta";
        items[4] = "Fred";
        items[5] = "Joe";
        items[6] = "Bill";
        items[7] = "Charlie";
        items[8] = "Java";
        items[9] = "HTML";
    }


    /**
     *  This methods redisplay the array's contents on the screen,
     *  with the selected item followed by a *.
     */
    private void displayItems() 
    {
        // Erase the text area
        display.setText( "" );

        // Redisplay data in 
        for (int i = 0; i < size; i++)                     // Scan through the array indices
        {
            // Now display the item's index and value
            display.append(Integer.toString(i)+" :    ");  // The item's index in left column
            display.append(items[i]);                      // The item in right column
            // If this is the selected item, add a * to the line
            if (i == selected)                            // Is this, the i'th, item the "selected" one?
                display.append(" *");                     // Yes
            display.append("\n");                         // Finish each line by adding a new line character
        }
    }

    /**
     *  Reacts to adjustment of the slider.
     *  Notes the new selector setting, then redisplay the array's contents.
     */
    public void stateChanged( ChangeEvent e ) 
    {
        selected = selector.getValue();
        displayItems();
    }

    /**
     *  The button has been clicked: take action by calling doSomething,
     *  and then redisplay the array's contents.
     */
    public void actionPerformed(ActionEvent e) 
    {
          doSomething();
          displayItems();
    }

    /**
     *  This method is to have various algorithms programmed into its body,
     *  one at a time, to see the effect. (Some are already here.)
     *  It will be useful to keep the solution for each step as you work on the
     *  next, so comment out and keep each one as you start the next one!
     */
    private void doSomething() 
    {
        // Exercise to fill the array with "Hello"s
        for (int i = 0; i < size; i++)
            items[i] = "Hello";

        // Exercise to set just the item at index 2 to "Hello"
        // items[2] = "Hello";

        // Exercise to set just the selected item to "Hello"
        // items[selected] = "Hello";

    }

}
