import java.util.Scanner;
import java.util.Arrays;

/**
 * This program measures how long it takes to sort an
 * array of a user-specified size with the selection
 * sort algorithm.
 */
public class SelectionSortTimer
{

   public static void main(String[] args)
   {
      Scanner in = new Scanner(System.in);
      System.out.print("Enter array size: ");
      int n = in.nextInt();

      // Construct random array of n values, each in the range 0 - n-1
      int[] a = ArrayUtil.randomIntArray(n, n);

      // Display the new array on the terminal
      System.out.println(Arrays.toString(a) + "\n\n");

      // Use stopwatch to time selection sort
      StopWatch timer = new StopWatch();

      timer.start();
      selectionSort(a);
      timer.stop();

      // Display the sorted array on the terminal
      System.out.println(Arrays.toString(a) + "\n\n");

      System.out.println("Elapsed time: "
            + timer.getElapsedTime() + " milliseconds");
   }

   /**
    * Sorts an array, using selection sort.
    * @param a the array to sort
    */
   public static void selectionSort(int[] a)
   {
      for (int i = 0; i < a.length - 1; i++)
      {
         int minPos = ArrayUtil.minimumPosition(a, i);
         ArrayUtil.swap(a, minPos, i);
      }
   }

}


