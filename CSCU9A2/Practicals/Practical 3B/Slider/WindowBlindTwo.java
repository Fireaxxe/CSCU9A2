import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Hashtable;

import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class WindowBlindTwo extends JFrame {
    JSlider slider1 = new JSlider();
    JSlider slider2 = new JSlider(JSlider.HORIZONTAL, -100, 100, 0);

    public WindowBlindTwo() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new GridLayout(2, 1));

        slider1.addChangeListener(new ChangeListener() {
                public void stateChanged(ChangeEvent e) {
                    System.out.println("Slider1: " + slider1.getValue());
                }
            });
        getContentPane().add(slider1);

        slider2.setPaintTicks(true);
        slider2.setMajorTickSpacing(50);
        slider2.setMinorTickSpacing(10);
        slider2.setPaintLabels(true);
        Hashtable ht = slider2.createStandardLabels(50);
        slider2.setLabelTable(ht);

        slider2.addChangeListener(new ChangeListener() {
                public void stateChanged(ChangeEvent e) {
                    System.out.println("Slider2: " + slider2.getValue());
                }
            });
        getContentPane().add(slider2);
        pack();
    }

    public static void main(String[] args) {
        new WindowBlindTwo().setVisible(true);
    }
}