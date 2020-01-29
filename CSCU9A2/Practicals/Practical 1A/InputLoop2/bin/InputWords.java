// For Practical 1A: Contains many syntax errors...

import java.util.Scanner;

public class InputLoop
{
    public static void main (String[] args)
    {
        Scanner scan = new Scanner(System.in);
        System.out.println ("Enter an integer");
        while (!scan.hasNextInt()) // while non-integers are present...
        {
            scan.next();           //...read and discard input, then prompt again
            System.out.println ("Bad input. Enter an integer"); 
        }
        int input = scan.nextInt();     
        System.out.println ("You entered "+ input+ "!");
    }
}
