/**
 * An instance of this class holds the key data for a cash register, 
 * and provides public methods for managing that data.
*/
public class CashRegister
{

   /**
    * A cash register that tracks the item count and
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
   
   public int getCount(){
    return itemCount;

   }
   public void clear(){
    itemCount = 0;
    totalPrice = 0;
    }
 
}
