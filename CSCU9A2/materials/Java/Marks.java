import java.util.Scanner;

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

    /**
     * The launch method
     *
     * @param  args   Command line parameters - not used
     */
    public static void main(String[] args)
    { // Step 1 - Break down the problem into subtasks
        int numberOfMarks = readInteger("How many marks?");
        int[] marks = new int[numberOfMarks];
        readMarks(marks);
        discardLowestMark(marks);
        float average = averageTheMarksExceptTheLastOne(marks);
        System.out.println("The average mark is " + average);
    }

    /**
     * Repeatedly read integers from the user, to fill all elements of the given marks array
     *
     * @param marks    An array of marks to be filled up
     */
    private static void readMarks(int[] marks)
    { // Step 1 - outline of subtask as a method
    }

    // More methods need adding...

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


}
