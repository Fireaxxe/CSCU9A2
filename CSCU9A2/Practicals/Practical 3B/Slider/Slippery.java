import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Slippery {
  public static void main(String[] args) {
    JFrame frame = new JFrame();

    JPanel main = new JPanel(new GridLayout(2, 1));
    JPanel scrollBarPanel = new JPanel();
    final JScrollBar scrollBar = new JScrollBar(JScrollBar.HORIZONTAL, 0, 48, 0, 255);
    int height = scrollBar.getPreferredSize().height;
    scrollBar.setPreferredSize(new Dimension(175, height));
    scrollBarPanel.add(scrollBar);
    main.add(scrollBarPanel);

    JPanel sliderPanel = new JPanel();
    final JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 255, 128);
    slider.setMajorTickSpacing(48);
    slider.setMinorTickSpacing(16);
    slider.setPaintTicks(true);
    sliderPanel.add(slider);
    main.add(sliderPanel);

    frame.add(main, BorderLayout.CENTER);

    scrollBar.addAdjustmentListener(new AdjustmentListener() {
      public void adjustmentValueChanged(AdjustmentEvent e) {
        System.out.println("JScrollBar's current value = " + scrollBar.getValue());
      }
    });

    slider.addChangeListener(new ChangeListener() {
      public void stateChanged(ChangeEvent e) {
        System.out.println("JSlider's current value = " + slider.getValue());
      }
    });

    frame.pack();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }
}