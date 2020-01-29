import java.awt.*;
import java.awt.event.*; 
import javax.swing.*; 

public class CarPark extends JFrame                       
implements ActionListener
{ 
    private int carCount = 0;    
    private JButton enter, exit;     
    private JTextField text; 

    public static void main(String[] args) {   
        CarPark frame = new CarPark();   
        frame.setSize(300, 200); 
        frame.setTitle("Car Park");
        frame.createGUI();
        frame.setVisible(true);
    }

    private void createGUI() {     
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

    public void actionPerformed(ActionEvent event) {  
        if (event.getSource() == enter)  
        {   carCount = carCount + 1;  } 
        if (event.getSource() == exit)  
        {   carCount = carCount-1;  }  
        text.setText(Integer.toString(carCount)); }
}

