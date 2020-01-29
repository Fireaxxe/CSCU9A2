import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 * This program displays a cash register graphical user interface.
 *
 * The actual cash register data is held in a separate class.
 */
public class CashRegisterGUI extends JFrame
implements ActionListener
{
    /**
     * Configuration constants for the frame size and position
     */
    private static final int FRAME_WIDTH = 400;
    private static final int FRAME_HEIGHT = 300;
    private static final int FRAME_X = 200;
    private static final int FRAME_Y = 200;

    /**
     * Configuration constants for the text area size
     */
    private static final int AREA_ROWS = 10;
    private static final int AREA_COLUMNS = 30;

    /**
     * Hold the GUI components
     */
    private JLabel priceLabel;
    private JTextField priceField;
    private JButton addItemButton;
    private JButton newCustomer;
    private JLabel totalDisplay;
    private JLabel itemCountDisplay;

    /**
     * Hold the cash register key data
     */
    private CashRegister cashRegister;

    /**
     * The main launcher method
     */
    public static void main(String[] args)
    {
        CashRegisterGUI frame = new CashRegisterGUI();
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setLocation(FRAME_X, FRAME_Y);
        frame.setTitle("My cash register");
        frame.createGUI();
        frame.setUpData();
        frame.setVisible(true);
    }

    /**
     * This method builds the graphical user interface
     */
    public void createGUI()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container window = getContentPane();
        window.setLayout(new GridLayout(5,2));  // 5 rows and 2 columns

        // First row

        priceLabel = new JLabel("Item price: ");
        window.add(priceLabel);

        final int FIELD_WIDTH = 5;
        priceField = new JTextField(FIELD_WIDTH);
        priceField.setText("");
        window.add(priceField);

        // Second row

        addItemButton = new JButton("Add item");
        addItemButton.addActionListener(this);
        window.add(addItemButton);
        SwingUtilities.getRootPane(addItemButton).setDefaultButton(addItemButton); 
        //triggers the addItemButton with Enter button

        window.add(new JLabel());   // Dummy widget to add space on the right

        // Third row

        totalDisplay = new JLabel("Sales total:  0");
        window.add(totalDisplay);

        window.add(new JLabel());   // Dummy widget to add space on the right

        // Fourth row

        itemCountDisplay = new JLabel("Number of items:  0");
        window.add(itemCountDisplay);

        window.add(new JLabel());

        window.add(new JLabel());
        newCustomer = new JButton("New customer");
        newCustomer.addActionListener(this);
        window.add(newCustomer);

    }

    /**
     * This method sets up a single instance of the CashRegister class
     */
    private void setUpData()
    {
        cashRegister = new CashRegister();
    }

    /**
     * React to click on Add item button by adding a new item to the cash register
     * and updating the display. The item's price is taken from the priceField.
     * For the user's convenience, the price field is cleared, and the input focus is
     * returned to the price field ready for the next input.
     */
    public void actionPerformed(ActionEvent event)
    {
        // 

        try{  //try to find any runtime errors

            if(event.getActionCommand().equals("Add item")){
                // Get the new item price and record it
                double price = Double.parseDouble(priceField.getText());
                if (price <=0) {
                    //  itemsArea.append("ERROR! \n" );
                    JOptionPane.showMessageDialog(null, "My Goodness, error!");
                }
                else {
                    cashRegister.addItem(price);

                    // Update the cash register display
                    totalDisplay.setText("Sales total:  " +  cashRegister.getTotal());
                    itemCountDisplay.setText("Number of items:  " +  cashRegister.getCount());
                    priceField.setText("");             // Clear the old entry
                    priceField.requestFocusInWindow();  // And put the input focus back into the price field
                }

            }
            else {

                cashRegister.clear(); // calling clear method
                totalDisplay.setText("Sales total:  " +0);
                itemCountDisplay.setText("Number of items:  "+0);

                //Process Button 2 action event here 
            }
        }

        catch(Exception e){
            //  itemsArea.append("ERROR! \n" );
            JOptionPane.showMessageDialog(null, "My Goodness, error!");
        }
    }
}
