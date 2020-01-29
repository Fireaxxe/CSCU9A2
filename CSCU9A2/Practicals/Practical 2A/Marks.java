import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
/**
 * This program reads a student's marks into an array, discards the lowest mark,
 * adds up the remaining marks and displays the average.
 *
 * It is an exercise in a top-down structured development of the methods in a program.
 *
 * @author SBJ Spring 2014
 */
public class Marks
{
    static int [] ret;
    static double average;

    /**
     * The launch method
     *
     * @param  args   Command line parameters - not used
     */
    public static void main(String[] args)
    { // Step 1 - Break down the problem into subtasks
        int numberOfMarks = readInteger("How many marks?");
        int[] marks = new int[numberOfMarks]; 
        int [] retrn;

        readMarks(marks); 

        findLowestMark(marks);

        discardLowestMark(marks);

        averageTheMarksExceptTheLastOne(ret);

        System.out.println("The new average is: " + average);

    }

    /**
     * A standard method:
     *
     * Display the prompt and then read lines repeatedly until a valid integer is found, and
     * return it.
     *
     * @param prompt   The prompt to be displayed
     * @result         A valid integer
     */
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

    /**
     * Repeatedly read integers from the user, to fill all elements of the given marks array
     *
     * @param marks    An array of marks to be filled up
     */
    private static void readMarks(int[] marks)
    { // Step 1 - outline of subtask as a method       
        Scanner key = new Scanner(System.in);
        int value = marks[0];
        int valueReal = 1;
        double sum = 0;
        double average = 0;       

        for (value = 0; value < marks.length ; value++){
            System.out.println("Please enter the No." + valueReal + " mark." );
            valueReal++;

            while(!key.hasNextInt()) //while non-integers are present...
            {
                key.next(); //read and discard input, then prompt again*
                System.out.println ("Bad input. Enter an integer");
            }            
            int returnInt = key.nextInt();
            marks[value] = returnInt;
            sum += marks[value];  
            average = (double) sum / (int) marks.length ;
        }
        System.out.println("The sum of ALL the marks are " + sum);
        System.out.println("The average of ALL the marks are " + average);

    }

    private static void findLowestMark(int[] marks)
    { // Step 1 - outline of subtask as a method

        /*int min = marks[0];
        int lowestIntex = 1;

        for (int i = 0; i < marks.length; i++)
        {
        if ( min > marks[i])  //*Find the minimum value in an array of integers
        {
        min = marks[i];
        lowestIntex = i;
        }
        }

        System.out.println("The minimum mark is " + min);
        System.out.println("The position of the mark is " + lowestIntex);
        return min;*/

    }

    private static int discardLowestMark(int[] marks) {

        int[] min = marks;
        boolean swapped = true;
        int j = 0;
        int tmp;

        // Bubble sorting the array 
        while (swapped) {
            swapped = false;
            j++;
            for (int i = 0; i < min.length - j; i++) {
                if (min[i] > min[i + 1]) {
                    tmp = min[i];
                    min[i] = min[i + 1];
                    min[i + 1] = tmp;
                    swapped = true;
                }
            }
        }

        // import arraylist class to change the array into an arraylist so we can remove the first element
        ArrayList<Integer> list = new ArrayList<Integer>(min.length);
        for (int i = 0; i < min.length; i++)
        {
            list.add(Integer.valueOf(min[i]));
        }

        list.remove(0); // removes the first element in our list
        // Displays to user the starting positon of the array 
        System.out.println("The first element in this arraylist is: " + list.get(0));

        // Spacer between printlines 
        System.out.println("");

        // Shows each element in the arrayList
        list.forEach(System.out::println);
        ret = new int[list.size()];
        for (int i=0; i < ret.length; i++)
        {
            ret[i] = list.get(i).intValue();
        }

        System.out.println("The new first value of the array is " + ret[0]);
        return ret[0];

    }   

    private static double averageTheMarksExceptTheLastOne(int[] ret)
    { // Step 1 - outline of subtask as a method
        //new array list, all numbers except the last one! 
        double sum = 0;
        for (int i = 0; i < ret.length; i++)
        {sum = sum + ret[i];
        }

        average = sum / ret.length; //average of all numbers except the last one (smallest one)

        return average;

    }
    private static void removeMark(int[] marks){
    }

} 
