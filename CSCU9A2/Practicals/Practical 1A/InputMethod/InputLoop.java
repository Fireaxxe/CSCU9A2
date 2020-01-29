/* NAME
 * DATE
 * DESCRIPTION
 */

//Constantinos Constantinou
//24/01/2017

package InputMethod;

import java.util.Scanner;

public class InputLoop

{ 

    //main method
    public static void main (String[] args)
    { 
        //creating new scaner
        Scanner scan = new Scanner(System.in);

        //Declaring static variable so main method can use non-static variables
        int input = 0;

        //declare int variable
        int value = 0;

        //declaring double variable
        double averageInt;

        //for loop 10 times
        for(int i = 0; i < 10; i++)
        {  
            //getting int from private method
            input = readInteger();

            //adding value and returnint data 
            value = value + input;
        }

        //average of all data
        averageInt = (double) value / 10; 

        System.out.println("Your average is: " + averageInt);
    }

    private static int readInteger()
    {
        //create scanner
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter an integer");

        //while loop to check the input from user if it is an integer
        while (!scan.hasNextInt()) // while non-integers are present...
        {
            scan.next();           //...read and discard input, then prompt again
            System.out.println("Bad input. Enter an integer"); 
        }
        //assigning value from user to return
        int input = scan.nextInt();

        //return data to main method
        return input;
    } 
}  

