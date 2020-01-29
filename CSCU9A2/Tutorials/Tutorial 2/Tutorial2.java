
/**
 * Write a description of class Tutorial2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tutorial2
{
    public static void main (String[] args) {
        /*int total = 0;
        int i = 1;
        while (i != 11)
        {
        total = total + i;
        i = i + 1;
        }
        System.out.println(total);
        System.out.println(i);
        }*/
        int[] list = { 88, 12, 6, 99, 4, 5 };
        for (int i = 0; i < list.length-1; i++)
        {
            if (list[i] > list[i+1])
            {
                int temp = list[i];
                list[i] = list[i+1];
                list[i+1] = temp;
            }
        }
        System.out.println(list[0]);
    }
}