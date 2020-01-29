import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

/**
 * This program displays an empty frame.
 */
public class SimpleFrame extends JFrame
{

    static int Value = 0 ;
    private JTextField text;
    /**
     * The main launcher method
     */
    public static void main(String[] args)
    {
        SimpleFrame frame = new SimpleFrame();
        

        final int FRAME_WIDTH = 700;
        final int FRAME_HEIGHT = 400;

        final int X_COORDINATE = 600;
        final int Y_COORDINATE = 200;

        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setTitle("My evil window");

        frame.createGUI();
        frame.setLocation(X_COORDINATE, Y_COORDINATE);              
        /*frame.setResizable(false);

        frame.setAlwaysOnTop(false);
        frame.setUndecorated(false);*/
        
        
        

        frame.setVisible(true);
        /*int n = 0;
        while(true)
        {
        System.out.println(n++);
        }*/
        

    }           
    

    /**
     * This method sets up the graphical user interface.
     */
    private void createGUI()
    {
        setDefaultCloseOperation( EXIT_ON_CLOSE);
        Container window = getContentPane();
        window.setLayout(new FlowLayout());
        
        

        int a;
        int b;
        int c;
        
        a = returnInt();
        b = returnInt();
        c = a + b ;
        
        
        
        
        text = new JTextField("               ");    
        text.setFont(new Font("Arial", Font.BOLD, 40));     
        window.add(text); 
        text.setText("a is " + a);
        
        text = new JTextField("               ");    
        text.setFont(new Font("Arial", Font.BOLD, 40));     
        window.add(text); 
        text.setText("b is " + b);
        
        text = new JTextField("               ");    
        text.setFont(new Font("Arial", Font.BOLD, 40));     
        window.add(text); 
        text.setText("Sum of a + b is " + c);
        
        

        // Nothing in the window yet!
    }

    private static int returnInt ()
    {
        Scanner key = new Scanner(System.in);
        System.out.println("What is your integer?");
        Value = key.nextInt();

        return Value;

    }
}