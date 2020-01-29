import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Scanner;
import javax.swing.event.*;
import javax.swing.border.Border;
/**
 * This program displays a cash register graphical user interface.
 * Author:
 * Date:
 * Version:
 */
public class CashRegister extends JFrame
implements ActionListener
{
    /**
     * Configuration constants for the frame size
     */
    private static final int FRAME_WIDTH = 400;
    private static final int FRAME_HEIGHT = 300;

    /**
     * Configuration constants for the text area size
     */
    private static final int AREA_ROWS = 10;
    private static final int AREA_COLUMNS = 30;

    /**
     * Hold the GUI components
     */
    private JLabel priceLabel;
    private JLabel priceLabel2;
    private JLabel priceTotal;
    private JTextField priceField;
    private JTextField priceField2;
    private JButton addItemButton;
    private JTextArea itemsArea;
    private JTextField text;

    /**
     * The main launcher method
     */
    public static void main(String[] args)
    {
        CashRegister frame = new CashRegister();
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.createGUI();
        frame.setTitle("CashRegister");
        frame.setVisible(true);

    }

    /**
     * This method builds the graphical user interface
     */
    public void createGUI()
    {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container window = getContentPane();
        window.setLayout(new FlowLayout() ); 

        final int FIELD_WIDTH = 5;
        priceField = new JTextField(FIELD_WIDTH);

        priceLabel2 = new JLabel("Description: ");
        window.add(priceLabel2);

        priceField2 = new JTextField(FIELD_WIDTH);
        priceField2.setText("");
        window.add(priceField2);

        priceLabel = new JLabel("Item price (£): ");
        window.add(priceLabel);  

        priceField.setText("");
        window.add(priceField);

        addItemButton = new JButton("Add item");
        addItemButton.addActionListener(this); // trigger the button with click
        window.add(addItemButton);

        itemsArea = new JTextArea(AREA_ROWS, AREA_COLUMNS);
        itemsArea.setText("");
        itemsArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(itemsArea);
        window.add(scrollPane);
        SwingUtilities.getRootPane(addItemButton).setDefaultButton(addItemButton); 
        //triggers the addItemButton with Enter button

        priceTotal = new JLabel ("Total price (£): ");  //label is here because we need it under the itemsArea
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        priceTotal.setBorder(border);
        priceTotal.setPreferredSize(new Dimension(150, 20));
        window.add(priceTotal);

    }

    /**
     * React to click on Add item button by adding a new item to the cash register
     * and updating the display. The item's price is taken from the priceField.
     * For the user's convenience, the price field is cleared, and the input focus is
     * returned to the price field ready for the next input.
     */
    public void actionPerformed(ActionEvent event)
    {
        //How to change a String into an int, for comparison, or whatever
       // String age = "25";
       // int actualAge = Integer.parseInt(age);
        try{  //try to find any runtime errors

            String description = priceField2.getText();
            double price = Double.parseDouble(priceField.getText());

            if (price <= 0) {  //if price is lower than 0 then output Error
                //                itemsArea.append("ERROR" +"\n" );
                JOptionPane.showMessageDialog(null, "My Goodness, error!");

            }
            else
            {
                addItem(price);
                itemsArea.append(description + " "+ price + " £" + "\n");
                priceField.setText("");             // Clear the old entry
                priceField.requestFocusInWindow();  // And put the input focus back into the price field 

                priceTotal.setText("Total price (£): " + getTotal());

            }
        }
        catch(Exception e){
          //  itemsArea.append("ERROR! \n" );
          JOptionPane.showMessageDialog(null, "My Goodness, error!");
        }
    }
    // -----------------------------------------------------------------------------
    /**
     * A simulated cash register that tracks the item count and
     * the total amount due, both initially 0.
     */
    private int itemCount = 0;
    private double totalPrice = 0;

    /**
    Adds an item to this cash register.
    @param price the price of this item
     */
    public void addItem(double price)
    {
        itemCount++;
        totalPrice = totalPrice + price;
    }

    /**
    Adds multiple items to this cash register.
    @param price the price of single item
    @param number the number of items to be added
     */
    public void addMultipleItems(double price, int number)
    {
        itemCount = itemCount + number;
        totalPrice = totalPrice + price * number;
    }

    /**
    Gets the price of all items in the current sale.
    @return the total amount
     */
    public double getTotal()
    {
        return totalPrice;
    }

    /**
    Gets the number of items in the current sale.
    @return the item count
     */
    public int getCount()
    {
        return itemCount;
    }

    /**
    Clears the item count and the total.
     */
    public void clear()
    {
        itemCount = 0;
        totalPrice = 0;
    }

}
