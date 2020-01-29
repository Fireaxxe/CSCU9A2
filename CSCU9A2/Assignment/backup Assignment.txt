/**  
 * CSCU9A2 Java assignment Spring 2017     
 * Starter seed file
 * Student Number - 2520796 
 */
/*
This is a Java application that could be used by, say, a tourist
information office to present the details of a cycle ride in
the area. The application could be left running on a computer screen
in the office, and visitors could browse the tour details as they
wished.

The route is described by the contents of three arrays:
spotHeights : containing the height (an int number of metres) at each kilometre point of the route,
including the start and end points
towns :       containing the town name (a String), if any, at each point
SBJ March 2017
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * The new class is called CycleTour and it is based on JFrame.A button listener must implement the ActionListener interface. 
 * ActionListener is an interface (not a class) that contains a single method:
 * public void actionPerformed( ActionEvent evt) ;
 */
public class CycleTour extends JFrame implements ActionListener
{

    /**
     * Frame layout constants
     */

    private static final int PANEL_WIDTH = 550;   // The graphics panel dimensions
    private static final int PANEL_HEIGHT = 400;

    private static final int FRAME_WIDTH = PANEL_WIDTH + 25;     // To allow suitable borders & space for widgets
    private static final int FRAME_HEIGHT = PANEL_HEIGHT + 140; 

    private final int[] spotHeights =
        { 100,  // At 0 km, the start                  // 50 kilometres of spot heights: metres above sea level at 1 kilometre intervals
            120,120,150,120,200,200,210,220,230,240,   // Heights at 1-10 km  // The kilometre points are at 0km, 1km, 2km, ... 49km, 50km, so 51 heights altogether
            300,300,300,250,250,200,150,150,150,130,   // Heights at 11-20 km
            120,120,150,120,200,200,210,220,230,240,   // Heights at 21-30 km
            300,300,300,250,250,200,150,150,150,130,   // Heights at 31-40 km
            120,120,150,120,200,200,210,220,230,240 }; // Heights at 41-50 km (the end)

    private final String[] towns =
        { "Berwick",  // At 0 km                             // Town names, if any, at the kilometre points (empty string where there is no town)
            "","Edinburgh","","","","Falkirk","","","","",   // Towns at 1-10 km
            "","","","","","Stirling","","","","",           // Towns at 11-20 km
            "","Doune","","","","","","Dunblane","","",      // Towns at 21-30 km
            "","","","Ashfield","","","","Kinbuck","","",    // Towns at 31-40 km
            "","","Perth","","","","","","","Aviemore" };    // Towns at 41-50 km      

    private final String[] arrayName =  // this array hold image paths as a string for each town
        { "1.jpg",       //Image for the town at 0 km                                    
            "0.jpg","2.jpg","0.jpg","0.jpg","0.jpg","3.jpg","0.jpg","0.jpg","0.jpg","0.jpg",     // images for towns at 1-10 km
            "0.jpg","0.jpg","0.jpg","0.jpg","0.jpg","4.jpg","0.jpg","0.jpg","0.jpg","0.jpg",     // images for towns at 11-20 km
            "0.jpg","5.jpg","0.jpg","0.jpg","0.jpg","0.jpg","0.jpg","6.jpg","0.jpg","0.jpg",     // images for towns at 21-30 km
            "0.jpg","0.jpg","0.jpg","7.jpg","0.jpg","0.jpg","0.jpg","8.jpg","0.jpg","0.jpg",     // images for towns at 31-40 km
            "0.jpg","0.jpg","9.jpg","0.jpg","0.jpg","0.jpg","0.jpg","0.jpg","0.jpg","10.jpg" };  // images for towns at 41-50 km      

    private int selectedLocation = 0;    // Initially the start of the cycle route
    // This variable will always hold the index in the arrays of the currently selected location,
    // which can only be at a kilometre point.

    final String FIRST = "FIRST";    
    final String NEXT = "NEXT";
    final String PREVIOUS = "PREVIOUS";
    final String LAST = "LAST";
    final String DISTANCE = "DISTANCE";

    private final Color selectionColour = Color.red.darker();

    //You may need to ADD to them. ****    
    private final int seaLevelY = 270,       // Pixels down from top of window for baseline of route diagram
    fromLeftX = 20,                          // Pixels from left of window for spotHeights[0]
    spotHeightInterval = 10;                 // Pixels per km horizontally (that is, between spot heights)    

    int rightSideX = fromLeftX+(spotHeights.length-1)*spotHeightInterval;  // Pixels from left of window for spotHeights[50]
    int verticalScale = 5;                                                 // Metres per pixel vertically

    // GUI widget definitions:
    private JPanel panel;                                             // For drawing the cycle route on;
    private Color backgroundColour =  Color.white;                    // Drawing panel background colour

    JLabel jlbInfo = new JLabel ();     
    private JButton enterDistance;    
    private JTextField jlbEnterDistancefield;
    private BufferedImage image;

    /**
     * main: Launch the CycleTour program
     * @param String[]: default input for java main method
     * This method creates the frame of the program.
     */
    public static void main( String[] args )
    {
        CycleTour frame = new CycleTour();                         //create frame
        frame.setSize( FRAME_WIDTH, FRAME_HEIGHT);                 //set size to the frame
        frame.createGUI();                                         //run the createGUI() method
        frame.setTitle("Cycle Tour Information: Student 2520796"); //title of the program
        frame.setVisible( true );                                  //set visibility to the end because we want to see the program after it execute all the code
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2); //the frame stays always in the middle of the screen at any monitor resolution

    } // End of main

    /**
     * createGUI: Set up the graphical user interface. 
     */
    private void createGUI()
    {
        // Set up main window characteristics
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        Container window = getContentPane();
        window.setLayout( new FlowLayout() );
        JLabel heading = new JLabel("Cycle Tour Information "); // This is the heading of the panel        
        heading.setFont(new Font("Corrier", Font.BOLD, 22));    // The font of the heading
        add(heading);                                           // Add the heading              

        JButton startButton = new JButton("Start <<");            // create 5 buttons
        JButton previousButton = new JButton("Previous <");
        JButton nextButton = new JButton("> Next");       
        JButton lastButton = new JButton(">> End");
        enterDistance = new JButton("Enter Distance (0-50km) :");

        int FIELD_WIDTH = 2;                                  
        jlbEnterDistancefield = new JTextField(FIELD_WIDTH);  //create a new textfield with width 2 
        jlbEnterDistancefield.setText("");                    //the textfield should be empty  

        startButton.setActionCommand(FIRST);                   //Sets the command name for the action event fired by this button. A string used to set the button's action command
        startButton.addActionListener(this);                   //Adds the specified action listener to receive action events from this button.
        previousButton.setActionCommand(PREVIOUS);
        previousButton.addActionListener(this);
        nextButton.setActionCommand(NEXT);
        nextButton.addActionListener(this); 
        lastButton.setActionCommand(LAST);          
        lastButton.addActionListener(this);
        enterDistance.setActionCommand(DISTANCE);          
        enterDistance.addActionListener(this);  

        JPanel centerPanel = new JPanel();               //create new panel 
        centerPanel.add(startButton);                    //add the buttons & textfield to the panel to be together
        centerPanel.add(previousButton);
        centerPanel.add(nextButton);
        centerPanel.add(lastButton);
        centerPanel.add(enterDistance);
        centerPanel.add(jlbEnterDistancefield);    
        add(centerPanel, BorderLayout.PAGE_START);   //add the panel to the start and centre of the frame      

        JLabel jlbStart = new JLabel("Start");                                              // New label  //create labels
        JLabel jlbEnd = new JLabel("End");                                                  
        JLabel jlbBlueBlob = new JLabel ("Blue blobs mark towns");                          
        JLabel jlbRedCircle = new JLabel ("The red circle indicated the selected location");                 

        SwingUtilities.getRootPane(enterDistance).setDefaultButton(enterDistance);    //triggers the enterDistance button with Enter key (or click)

        int LlabelX = fromLeftX;                                  // Left Label on X axis = 20. This is the Coordinate from the first index of the array
        int LlabelY = seaLevelY - spotHeights[0]/verticalScale;   // Left Label on Y axis = 270 (baseline) - 100/5 = 250
        int RlabelX = fromLeftX + spotHeightInterval*(towns.length-1);          // Right Label on X axis = 20 + 10(pixel per km) * 50 (km) = 520
        int RlabelY = seaLevelY - spotHeights[towns.length-1]/verticalScale;  // Right Label on Y axis = 270 - 240(last index) / 5 = 222

        JLabel jlbKm = new JLabel("km");                                                                                                                         // New label 
        JLabel jlbRuler = new JLabel("0                      10                     20                     30                     40                     50 ");  // New label ruler numbers
        Font font = new Font ("Corrier", Font.BOLD,15);    // new font       
        jlbRuler.setFont(font);                            // apply the font to the label
        jlbBlueBlob.setForeground(Color.BLUE);             // apply the colour to the label
        jlbRedCircle.setForeground(selectionColour);       // Set up the panel for drawing the cycle

        panel = new JPanel()

        {

            // paintComponent is called automatically when a screen refresh is needed
            @Override
            public void paintComponent(Graphics g)
            {
                // g is a cleared panel area
                super.paintComponent(g); // Paint the panel's background
                paintScreen(g);          // Then the required graphics 

                jlbStart.setLocation(LlabelX-15, LlabelY-20);  //Locations in the panel for the labels
                jlbEnd.setLocation(RlabelX-15, RlabelY-20);
                jlbKm.setLocation(RlabelX +10, RlabelY+68); 
                jlbRuler.setLocation(fromLeftX-3, LlabelY+40); 
                jlbBlueBlob.setLocation(fromLeftX+20, RlabelX-205);  
                jlbRedCircle.setLocation(fromLeftX+20 ,RlabelX-190); 
                jlbInfo.setLocation(10,10); 

                add(jlbStart);      //add the labels to the panel
                add(jlbEnd); 
                add(jlbKm);
                add(jlbRuler);
                add(jlbBlueBlob);
                add(jlbRedCircle);
                add(jlbInfo);

                Border border = BorderFactory.createLineBorder(Color.BLACK);    //create a new border for the jlbInfo label
                jlbInfo.setBorder(border);                                          
                jlbInfo.setPreferredSize(new Dimension(RlabelY+8,fromLeftX+50)); 

                jlbInfo.setText("<html>Information about the selected location: <br>Distance from start: " + selectedLocation + " km <br>Height: " + spotHeights[selectedLocation] + " m <br>Town: " + towns[selectedLocation]);
                //information about the Selected location, distance from start, height and town 

                g.setColor(Color.BLUE);   //draw an oval to the location
                g.fillOval(fromLeftX, LlabelY+70,5,5); 

                g.setColor(Color.BLACK);//set colour for the ruler
                g.drawLine(fromLeftX, seaLevelY+21, RlabelX ,seaLevelY+21); //draw the line for the ruler

                // draw horizontal rule (left to right)
                for (int x=0; x <= RlabelX-20; x++) {         //draw vertical lines using modulus equation               	
                    int height = LlabelY+41;         	                	
                    if (x % 100 == 0) {                        
                        g.drawLine(x+20, height -20, x+20, height);                             
                    }
                    else if (x % 10 == 0) {
                        g.drawLine(x+20, height - 10, x+20, height);
                    }
                    else if (x % 2 == 0) {
                        g.drawLine(x+20, height - 5, x+20, height);
                    }
                }

                if(selectedLocation == 0)                  // if the selectedLocation is zero then the startButton will disable
                {
                    startButton.setEnabled(false); 
                    previousButton.setEnabled(true); 
                    nextButton.setEnabled(true); 
                    lastButton.setEnabled(true); 

                }
                else if(selectedLocation == towns.length-1)           // if the selectedLocation is 50 then the lastButton will disable
                {

                    startButton.setEnabled(true); 
                    previousButton.setEnabled(true); 
                    nextButton.setEnabled(true); 
                    lastButton.setEnabled(false); 
                }
                else                                     
                {
                    startButton.setEnabled(true); 
                    previousButton.setEnabled(true); 
                    nextButton.setEnabled(true); 
                    lastButton.setEnabled(true); 

                }               

            }
        };
        panel.setPreferredSize( new Dimension( PANEL_WIDTH, PANEL_HEIGHT ) );
        panel.setBackground( backgroundColour );
        window.add( panel );

    } // End of createGUI
    
    /**
     * When the user clicks the buttons, the buttons fires an action event which invokes the action listener's 
     * actionPerformed method.
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        String cmd = e.getActionCommand();
        jlbEnterDistancefield.setBackground(Color.WHITE);

        if (cmd.equals("FIRST"))       
        {
            selectedLocation = 0;            //if the "FIRST" button is triggered then the selectedLocation became 0       
        } 
        else if (cmd.equals("PREVIOUS"))       
        {
            selectedLocation--;              //if the "PREVIOUS" button is triggered then the selectedLocation goes -1

            if (selectedLocation < 0)       //if the selectedLocation tries to go lower than 0 becomes 50 
            {
                selectedLocation = towns.length-1;  
            }            
        } 
        else if (cmd.equals("NEXT"))        //if the "NEXT" button is triggered then the selectedLocation goes +1  
        {
            selectedLocation++;            

            if (selectedLocation > towns.length-1)    //if the selectedLocation tries to go higher than 50 becomes 0 
            {
                selectedLocation = 0;                 
            }                    
        } 
        else if (cmd.equals("LAST"))    //if the "LAST" button is triggered then the selectedLocation became 50    
        {
            selectedLocation = towns.length-1;                          
        }
        else if (cmd.equals("DISTANCE"))   //when the "DISTANCE" button is triggered it takes the value from the textfield and replace the 
        //selectedLocation's current value, so it "jumps" to that value/location
        {
            try {
                int location = Integer.parseInt(jlbEnterDistancefield.getText());  //it get the string and parse it to an integer
                if (location < 0 || location > towns.length-1){                   //if the value is lower or higher than 50 then:
                    jlbEnterDistancefield.setBackground(Color.RED);  //set the textfield red                 
                    jlbEnterDistancefield.requestFocusInWindow();  //the cursor remains in the textfield                     
                    JOptionPane.showMessageDialog(null, "My Goodness, ERROR! Please enter a number from 0 to 50."); //pop up this message
                    jlbEnterDistancefield.selectAll(); // and select everything typed in the textfield to be easier for the user to delete it                
                }
                else                                          // if the value is between 0 and 50 then:
                {
                    selectedLocation = location;                   //jump to the location                               
                    jlbEnterDistancefield.setText("");             // Clear the old entry
                    jlbEnterDistancefield.requestFocusInWindow();  // And put the input focus back into the price field 
                }
            }
            catch(Exception event ) {                             //if user enter strings or symbols in the textfield then the error message appears again
                jlbEnterDistancefield.setBackground(Color.RED);  
                jlbEnterDistancefield.requestFocusInWindow();    
                JOptionPane.showMessageDialog(null, "My Goodness, ERROR! Please enter a number from 0 to 50.");
                jlbEnterDistancefield.selectAll();                

            }
        }
    }

    /**
     * paintScreen: Prepare graphics panel for redisplaying, and redisplay it
     * Will be called with a blanked graphics area
     */

    private void paintScreen(Graphics g)
    {
        for (int counter = 0; counter < towns.length-1; counter++) //this loop is for the creation of the graph
        {
            drawRouteSegment(g, counter);
        }        

        int blueCities = 0;  

        for (int indexOfTowns = 0; indexOfTowns <= towns.length-1; indexOfTowns++)
        {
            //This formula calculates the screen coordinates 
            int blackMarksX = fromLeftX + blueCities * spotHeightInterval;         //these X and Y locations are supposed to take all possible locations so they                                                                                      
            int blackMarksY = seaLevelY - spotHeights[blueCities] / verticalScale; // are outside of the if statement 

            if (towns[indexOfTowns].equals(""))                                    // if the Strings on the towns array is equal to nothing then : 
            {
                // do nothing
            }
            else                                                                   // else calculate the screen coordinates of the cities
            {                
                int locationX = fromLeftX + blueCities * spotHeightInterval;
                int locationY = seaLevelY - spotHeights[blueCities] / verticalScale;
                blueMarks(g, locationX, locationY);                                //sent the values to blueMarks method
            }

            if (blueCities == towns.length-1)                                      // a counter for all the locations
            {
                blueCities = 0;
            }
            else
            {
                blueCities++;
            }
            allMarks(g, blackMarksX, blackMarksY);                                //sent the values to allMarks method
        }        
        // Given that selectedLocation contains the index of the currently selected location,
        // here is how to calculate the screen coordinates of currently selected location
        // (This information can be used to draw the location red circle)
        int redMarksX = fromLeftX + selectedLocation * spotHeightInterval;
        int redMarksY = seaLevelY - spotHeights[selectedLocation] / verticalScale;
        redMark(g, redMarksX, redMarksY);                                         //sent the values to redMark method
 
    }// End of paintScreen

    /**
     *The method draws the route segment from index to index+1 at an appropriate place on the screen, determined by the 
     *constants above, as a filled green trapezium, a bit like this: It uses the fillPolygon Graphics method.   
     */
    private void drawRouteSegment(Graphics g, int index) {

        int leftY = seaLevelY-spotHeights[index]/verticalScale;        // Height at index above sea level.
        int rightY = seaLevelY-spotHeights[index+1]/verticalScale;     // Height at index+1 above sea level.
        int leftX = fromLeftX+index*spotHeightInterval;                // Distance from left of diagram to index.
        int rightX = leftX+spotHeightInterval;                         // Distance from left of diagram to index+1.

        int[] xArray = { leftX, leftX, rightX, rightX };               // (x,y) coordinates of the four corners,
        int[] yArray = { seaLevelY, leftY, rightY, seaLevelY };        // clockwise from the bottom left.
        g.setColor(Color.green);        
        g.fillPolygon(xArray, yArray, 4);                              // Array of x coords, of y coords, and the number of coords

        g.setColor(Color.red);                  
        g.drawLine(leftX,leftY,rightX,rightY);                         //draw a red line above the route 

    } // End of drawRouteSegment

    /**
     * this method takes the locations and create blue dots
     */
    private void blueMarks (Graphics g, int locationX, int locationY)      
    {
        g.setColor(Color.BLUE);
        g.fillOval(locationX-3, locationY-3, 5, 5);
    }

    /**
     * this method creates the red circle which the user controls with buttons
     */
    private void redMark (Graphics g, int redMarksX, int redMarksY)
    {
        Graphics2D g2D = (Graphics2D) g;
        g2D.setStroke (new BasicStroke (2F)); //bold the circle
        g2D.setColor(selectionColour);
        g2D.drawOval(fromLeftX,seaLevelY+64,10,10); 
        g2D.drawOval(redMarksX-5,redMarksY-5,10,10); // the start of the red circle
        g2D.setStroke (new BasicStroke (1F)); //set the thickness to default

        try {                //also when the circle moves to a city, a picture appears to the side
            image = ImageIO.read(new File(arrayName[selectedLocation]));
        } catch (IOException ex) {
            // handle exception...
        }                
        g.drawImage(image,seaLevelY-20,fromLeftX-5, 290, 180, null); //draw the image to the panel
    }

    /**
     *this method creates small black dots into the route to be easier for the user
     *to calculate the distance faster
     */
    private void allMarks (Graphics g, int blackMarksX, int blackMarksY) 

    {                                                                    
        g.setColor(Color.BLACK);
        g.fillOval(blackMarksX-2, blackMarksY-2, 3, 3);
    }
}
