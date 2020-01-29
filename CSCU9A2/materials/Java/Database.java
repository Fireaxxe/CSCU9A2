import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/** An object oriented program managing a student's personal academic record.
 *
 *  - The GUI parts are in this class.
 *  - The parts managing the student's record are in class StudentRecord.
 *
 * One record is displayed, for Pythagoras, and the address and credits obtained details can be updated.
 *
 *  SBJ April 2014
 */
public class Database extends JFrame
implements ActionListener 
{
    // GUI variables: interface widgets
    private JButton changeAddress,                  // Click to set the student's address from the text field
                    modulePassed;                   // Click when another module has been passed
    private JTextField addressEntry;                // For entering a new address
    private JTextArea display;                      // For displaying the student's details

    private StudentRecord record;                  // To hold the instance of class StudentRecord

    /**
     *  The main program launcher for the Database class.
     *
     * @param  args  The command line arguments (ignored here).
     */
    public static void main(String[] args) 
    {
        Database frame = new Database();
        frame.setSize( 600, 500 );
        frame.createGUI();
        frame.setUpData();
        frame.setVisible( true );
    }

    /**
     *  Sets up the graphical user interface.
     */
    private void createGUI() 
    {
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        Container window = getContentPane();
        window.setLayout( new FlowLayout() );

        changeAddress = new JButton("Change address to:");           // Change address button
        window.add(changeAddress);
        changeAddress.addActionListener(this);

        addressEntry = new JTextField("Enter new address here", 20); // New address field
        window.add(addressEntry);

        modulePassed = new JButton("Passed a module");               // New credit button
        window.add(modulePassed);
        modulePassed.addActionListener(this);

        display = new JTextArea(20, 30);                             // To display the student's details
        window.add(display);
        display.setEditable(false);
        display.setFont(new Font("Sans Serif", Font.BOLD, 14));
    }

    /**
     *  Sets up the initial student's details.
     */
    private void setUpData() 
    {
        record = new StudentRecord("Pythagoras", "00214159");     // Set up the student record

        displayDetails();    // Update the display with current details
    }

    // Display the student's details in the text area
    private void displayDetails() 
    {
        display.setText("Student record for: "+record.getName()+"\n");
        display.append("Registration number: "+record.getRegistrationNo()+"\n");
        display.append("Address: "+record.getAddress()+"\n");
        display.append("Credits: "+record.getCreditsObtained()+"\n");
        display.append("\n");
    }

    // Respond appropriately to a button click
    public void actionPerformed(ActionEvent event) 
    {
        if (event.getSource() == changeAddress) {    // Change address: obtain address and set it into record
            record.setAddress(addressEntry.getText());
            addressEntry.setText("");
        }
        if (event.getSource() == modulePassed) {     // Module passed: increment the number of credits
            record.addACredit();
        }

        displayDetails();
    }

}