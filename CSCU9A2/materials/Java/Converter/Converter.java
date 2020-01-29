/*
 * This is a very small application which converts a
 * temperature in degrees Fahrenheit to degrees
 * Celsius.
 * 
 * If it converted back again too, that would be even more useful. 
 * This is a small application, but it does illustrate
 * a few Swing widgets, and shows what you can do with them.
 * 
 * Simon Jones, April 2014
 * 
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Converter extends JFrame 
implements ActionListener 
{
    JPanel topPanel, guiPanel;           // some "invisible panels", or "layout areas"
                                         // for laying out the interface

    JTextField tempF;                    // a textfield to type in the temperature

    JLabel resultLabel;                  // a label to display the converted temperature
    
    JLabel fontLabel;                    // for later, when font size choice is implemented

    JButton convertTemp;                 // the idea is, when you press the button, it
                                         // converts the temperature for you

    JComboBox fontSizeChoice;            // to choose the size of font

    char deg = '\u00B0';                 // a useful Unicode character for later on...

    // main method, called when the program runs
    public static void main(String[] args) 
    {
        Converter myConv = new Converter();        // Creates the Swing frame and container.
        myConv.setTitle("Temperature Converter");
        myConv.setSize(400, 300);
        myConv.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myConv.setVisible(true);
    }

    // class constructor 
    // (is called at new Converter() instantiation)
    public Converter() 
    {
        // Now to create the GUI interface, using Swing components.

        guiPanel = new JPanel();
        guiPanel.setLayout(new BorderLayout()); // Using BorderLayout means that widgets can
                                                // either be added to the centre
                                                // of this panel or round the edges

        topPanel = new JPanel();               // this "invisible panel" will hold the button and textfield
        topPanel.setLayout(new FlowLayout());  // and it will lay components out from left to right

        convertTemp = new JButton("Convert");  // this is the button we'll press to convert the
                                               // temperature from Fahrenheit to Celsius

        // convertTemp = new JButton(new ImageIcon("fc.gif"));

        convertTemp.addActionListener(this); // Now we can listen to events from our button.

        topPanel.add(convertTemp);           // adding the convertTemp button to the top panel

        tempF = new JTextField(10);          // creating the text field tempF
        tempF.addActionListener(this);       // Now we can listen to events from our text field.
        topPanel.add(tempF);                 // adding it to the top panel

/*
        // GUI components for font size adjustment
        fontLabel = new JLabel("Font size:",SwingConstants.RIGHT);
        topPanel.add(fontLabel);

        // firstly setting up an array of font sizes we might use
        String[] fontSizes = {"8","10","11","12","14","16","18", "20"};

        fontSizeChoice = new JComboBox(fontSizes); // this JComboBox offers a drop-down menu of choices
        fontSizeChoice.addActionListener(this);    // listens out for action events which get generated when 
                                                   // we select from the choices
        topPanel.add(fontSizeChoice);
*/

        guiPanel.add(topPanel,BorderLayout.NORTH);    // This adds the top panel to the top of the GUI

        resultLabel = new JLabel("", SwingConstants.CENTER);
        // This label is where the result of the
        // temperature conversion will appear
        guiPanel.add(resultLabel,BorderLayout.CENTER);// the result label will appear in the centre of the GUI
        getContentPane().add(guiPanel);               // Adds the panel to the application.
    }


/*
    // This method adjusts the font size for various components
    private void setComponentFonts(int fontSize) 
    {
        // sets the font on the button
        Font f = new Font("Helvetica", Font.BOLD, fontSize);
        convertTemp.setFont(f);
    }
*/

    // React to button press, or Enter in the textfield
    public void actionPerformed(ActionEvent e) 
    {
        if (e.getSource() == convertTemp         // if button pressed
            || e.getSource() == tempF)           // or if the return key pressed (event from the textfield)
        { 
            try 
            {   // Interpret (parse) degrees Fahrenheit as a double and convert to Celsius.

                double tempInF = Double.parseDouble(tempF.getText());

                int tempInC = (int) ((tempInF-32)/1.8);

                //  Set resultLabel to new value
                resultLabel.setText(tempF.getText()+" degrees F  converts to  "+tempInC+" degrees C");
            }
            catch (Exception ex) 
            {   // catch to handle the case where the user didn't type in
                // an understandable number
                System.out.println("No number in textfield");
            }
        }

/*
        if (e.getSource() == fontSizeChoice)    // if a choice has been made from the JComboBox
        {
            int fontSize = Integer.parseInt((String)fontSizeChoice.getSelectedItem());
            setComponentFonts(fontSize);
        }
*/

    }
}

