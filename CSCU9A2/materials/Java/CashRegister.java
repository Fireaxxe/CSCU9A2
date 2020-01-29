import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 * This program displays a cash register graphical user interface.
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
   private JTextField priceField;
   private JButton addItemButton;
   private JTextArea itemsArea;

   /**
    * The main launcher method
    */
   public static void main(String[] args)
    {
       CashRegister frame = new CashRegister();
       frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
       frame.createGUI();
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

      priceLabel = new JLabel("Item price: ");
      window.add(priceLabel);

      final int FIELD_WIDTH = 5;
      priceField = new JTextField(FIELD_WIDTH);
      priceField.setText("");
      window.add(priceField);

      addItemButton = new JButton("Add item");
      addItemButton.addActionListener(this);
      window.add(addItemButton);

      itemsArea = new JTextArea(AREA_ROWS, AREA_COLUMNS);
      itemsArea.setText("");
      itemsArea.setEditable(false);

      JScrollPane scrollPane = new JScrollPane(itemsArea);
      window.add(scrollPane);

   }

   /**
    * React to click on Add item button by adding a new item to the cash register
    * and updating the display. The item's price is taken from the priceField.
    * For the user's convenience, the price field is cleared, and the input focus is
    * returned to the price field ready for the next input.
    */
   public void actionPerformed(ActionEvent event)
   {
      double price = Double.parseDouble(priceField.getText());
      addItem(price);
      itemsArea.append(getTotal() + "\n");
      priceField.setText("");             // Clear the old entry
      priceField.requestFocusInWindow();  // And put the input focus back into the price field
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
