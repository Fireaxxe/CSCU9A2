import java.util.Scanner;
/**
 * Methods 
 * 
 * @author Silviya 
 * @version 24/01/2017
 */
public class InputLoop
{
    public static void main (String[] args)
    {
        int total = 0;  //total number of integers input
        int sum = 0;  //the sum of all integers input
        int times= 0; //times integers have been input

        Scanner scan = new Scanner(System.in);
        System.out.println ("Enter an integer");

        while (!scan.hasNextInt()) // while non-integers are present...
        {
            scan.next(); //...read and discard input, then prompt again
            System.out.println ("Bad input. Enter an integer"); 

            times ++; // Counter 

            System.out.println ("You entered " + times + " non-integers."); // Count how many (bad)inputs you have.
        }

        for (int i = 0; i < 1 ; i++) 
        {
            readInteger(); 
                
        }
    }
    //     int input = scan.nextInt();
    //     System.out.println ("You entered " + input + "!"); // It represents your current input.
    //     System.out.println ("You entered " + times + " non-integers."); //Count how many (bad)inputs you have. 
    //     System.out.println(readInteger());

    private static int readInteger ()
    {

        int average=0;
        int counter=1;
        int integer= 0;
        int total = 0;

        Scanner scan = new Scanner(System.in);

        System.out.println ("Please enter an integer");
        while (!scan.hasNextInt()) // while non-integers are present...
        {

            scan.next(); //...read and discard input, then prompt again
            System.out.println ("Bad input. Enter an integer"); 

        }

        while(counter <11 )
        {

            integer = scan.nextInt(); //gives value to variable integer of the current entered integer 
            total = total + integer; // Total of all 10 integers 
            average = total/counter;// Average of all 10 integers 
            counter = counter + 1; // Keeps count of the number of integers

            if(counter == 11)
            {
                System.out.println("The average of these ten numbers is  : "+ average);
            }
        }

        return integer;
    }
}

   