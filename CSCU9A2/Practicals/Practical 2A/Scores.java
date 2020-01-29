import java.util.Arrays;
import java.util.Scanner;
/**
This program computes a final score for a series of quiz scores: the sum after dropping
the lowest score. The program uses arrays.
 */
public class Scores
{
    public static void main(String[] args)
    {
        double[] scores = readInputs();
        if (scores.length == 0)
        {
            System.out.println("At least one score is required.");
        }
        else
        {
            double total = sum(scores) - minimum(scores);
            System.out.println("Final score: " + total);
        }
    }

    /**
    Reads a sequence of floating-point numbers.
    @return an array containing the numbers
     */
    public static double[] readInputs()
    {
        // Read the input values into an array
        final int INITIAL_SIZE = 10;
        double[] inputs = new double[INITIAL_SIZE];
        System.out.println("Please enter values, Q to quit:");
        Scanner in = new Scanner(System.in);
        int currentSize = 0;
        while (in.hasNextDouble())
        {
            // Grow the array if it has been completely filled
            if (currentSize >= inputs.length)
            {
                inputs = Arrays.copyOf(inputs, 2 * inputs.length);
            }
            inputs[currentSize] = in.nextDouble();
            currentSize++;
        }
        return Arrays.copyOf(inputs, currentSize);
    }

    /**
    Computes the sum of the values in an array.
    @param values an array
    @return the sum of the values in values
     */
    public static double sum(double[] values)
    {
        double total = 0;
        for (double element : values)
        {
            total = total + element;
        }
        return total;
    }

    /**
    Gets the minimum value from an array.
    @param values an array of size >= 1
    @return the smallest element of values
     */
    public static double minimum(double[] values)
    {
        double smallest = values[0];
        for (int i = 1; i < values.length; i++)
        {
            if (values[i] < smallest)
            {
                smallest = values[i];
            }
        }
        return smallest;
    }
}