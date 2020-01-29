import java.util.Scanner;
import java.util.ArrayList;

/**
 * This program reads a student's marks into an array, discards the lowest mark,
 * adds up the remaining marks and displays the average.
 *
 * It is an exercise in a top-down structured development of the methods in a program.
 *
 * 
 */

public class ScoresV2
{
    // This global array allows the main method to use an array enscapulated within another method
    static int[] primitiveArray;

    /**
     * The launch method
     *
     * @param  args   Command line parameters - not used
     */

    public static void main(String[] args)
    {
        // This int variable is the amount of elements within the array given by the user, it also passes a string variable to prompt the user of how many marks.
        int numberOfMarks = readInteger("How many marks?");

        // This int array it takes from the previous variables and create an array of that size
        int[] marks = new int[numberOfMarks];

        // This method prompts the user and takes input for each element. Once its complete it passes the completed array back to the main method.
        readMarks(marks);
        
        // This method passes the array (marks) into itself, and bubble sorts it, turns it into an ArrayList, then removes the lowest element before changing back into an int[] array and then
        // returning this array to the main method
        discardLowestMark(marks);
        
        // The method here takes the globally declared array, and passes to a method which is able to find out the average and then creates a double varaible where the return value is stored
        double average = averageTheMarksExceptTheLastOne(primitiveArray);
        
        // This method outputs the average of the final array
        System.out.println("The average mark is " + average);
    }

    private static int readInteger(String prompt)
    {
        Scanner scan = new Scanner(System.in);
        
        System.out.println(prompt);
        
        while (!scan.hasNextInt()) // while non-integers are present...
        {
            scan.next();           //...read and discard input, then prompt again
            System.out.println("Bad input. Please enter an integer");
        }
        
        int input = scan.nextInt();
        
        return input;
    }

    private static void readMarks(int[] marks)
    { 
        Scanner scan = new Scanner(System.in);
        
        int elementValue = marks[0]; 
        int elementValueReal = 1;
        double sum = 0, average = 0;      
        
        for (elementValue = 0; elementValue < marks.length ; elementValue++)
        {
            System.out.println("Please enter the marks of element " + elementValueReal + " mark." );
            elementValueReal++;
            
            while(!scan.hasNextInt()) //while non-integers are present...
            {
                scan.next(); //read and discard input, then prompt again*
                System.out.println ("Bad input. Enter an integer");
            }     
            
            int returnInt = scan.nextInt();
            
            marks[elementValue] = returnInt;
            
            sum += marks[elementValue];  
            
            average = (double) sum / (int) marks.length ;
        }
        
        System.out.println("The sum of ALL the marks are " + sum);
        System.out.println("The average of ALL the marks are " + average);

    }

    private static int[] discardLowestMark(int[] marks)
    {
        int[] myArray = marks;
        boolean swapped = true;
        int j = 0;
        int tmp;

        // Bubble sorting the array 
        while (swapped) {
            swapped = false;
            j++;
            for (int i = 0; i < myArray.length - j; i++) {
                if (myArray[i] > myArray[i + 1]) {
                    tmp = myArray[i];
                    myArray[i] = myArray[i + 1];
                    myArray[i + 1] = tmp;
                    swapped = true;
                }
            }
        }

        // Changing the array into an arraylist so we can remove the first element
        ArrayList<Integer> list = new ArrayList<Integer>(myArray.length);
        for (int i = 0; i < myArray.length; i++)
        {
            list.add(Integer.valueOf(myArray[i]));
        }

        list.remove(0); // removes the first element in our list

        // Displays to user the starting positon of the array 
        System.out.println("The first element in this arraylist is: " + list.get(0));

        // Spacer between printlines 
        System.out.println("");

        // Shows each element in the arrayList
        list.forEach(System.out::println);

        primitiveArray = new int[list.size()];
        for (int i=0; i < primitiveArray.length; i++)
        {
            primitiveArray[i] = list.get(i).intValue();
        }

       System.out.println("The first element in the primitive array is: " + primitiveArray[0]);

       return primitiveArray;

    }

    private static double averageTheMarksExceptTheLastOne(int[] primitiveArray)
    {
        double sum = 0; 

        for(int i=0;i<primitiveArray.length;i++)
        {
            sum = sum + primitiveArray[i];
        }

        double average = sum / primitiveArray.length;
        
        return average;
    }

    private static void removeMark(int[] marks)
    {

    }
    
    
    private static void findLowestMark(int[] marks)
    {

    }

}