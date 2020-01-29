import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * This programs counts the cars entering and leaving a car park, always displaying
 * the current number of cars in the car park.
 */
public class CarPark extends JFrame
    implements ActionListener
{
    /**
     * Globally accessible counter for the number of cars in the car park
     */
    private int carCount = 0;

    /**
     * Buttons to simulate cars entering and leaving the car park.
     */
    private JButton enter, exit;
    
    /**
     * Text field where the current number of cars is displayed
     */
    private JTextField text;

    /**
     * The main launch method
     */
    public static void main(String[] args)
    {
        CarPark frame = new CarPark();
        frame.setSize(300, 200);
        frame.setLocation(150, 150);
        frame.setTitle("Car Park");
        frame.createGUI();
        frame.setVisible(true);
    }

    /**
     * Helper method to build up the GUI
     */
    private void createGUI()
    {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container window = getContentPane();
        window.setLayout(new FlowLayout());

        enter = new JButton("Car Entering");
        window.add(enter);
        enter.addActionListener(this);

        text = new JTextField("0   ");
        text.setFont(new Font("Arial", Font.BOLD, 40));
        window.add(text);

        exit = new JButton("Car Exiting");
        window.add(exit);
        exit.addActionListener(this);
    }

    /**
     * React to a GUI button press by adjusting the car counter correctly,
     * and then updating the counter display
     */
    public void actionPerformed(ActionEvent event)
    {
        if (event.getSource() == enter)
        {
            carCount = carCount + 1;
        }
        if (event.getSource() == exit)
        {
            carCount = carCount-1;
        }
        text.setText(Integer.toString(carCount));
    }

}
