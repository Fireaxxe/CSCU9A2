import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ColorValueSliderControl extends JFrame {
  public ColorValueSliderControl() {
    getContentPane().add(new TColor());
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(500, 500);
    setVisible(true);
  }

  public static void main(String arg[]) {
    new ColorValueSliderControl();
  }
}

class TColor extends JPanel {
  DrawingCanvas canvas = new DrawingCanvas();
  JLabel rgbValue = new JLabel("");

  JSlider sliderR, sliderG, sliderB, sliderH, sliderS, sliderBr,
      sliderAlpha;

  public TColor() {
    sliderR = getSlider(0, 255, 0, 50, 5);
    sliderG = getSlider(0, 255, 0, 50, 5);
    sliderB = getSlider(0, 255, 0, 50, 5);
    sliderH = getSlider(0, 10, 0, 5, 1);
    sliderS = getSlider(0, 10, 0, 5, 1);
    sliderBr = getSlider(0, 10, 0, 5, 1);
    sliderAlpha = getSlider(0, 255, 255, 50, 5);

    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(6, 2, 15, 0));

    panel.add(new JLabel("R-G-B Sliders (0 - 255)"));
    panel.add(new JLabel("H-S-B Sliders (ex-1)"));
    panel.add(sliderR);
    panel.add(sliderH);
    panel.add(sliderG);
    panel.add(sliderS);
    panel.add(sliderB);
    panel.add(sliderBr);

    panel.add(new JLabel("Alpha Adjustment (0 - 255): ", JLabel.RIGHT));
    panel.add(sliderAlpha);

    panel.add(new JLabel("RGB Value: ", JLabel.RIGHT));
    
    rgbValue.setBackground(Color.white);
    rgbValue.setForeground(Color.black);
    rgbValue.setOpaque(true);
    panel.add(rgbValue);

    add(panel, BorderLayout.SOUTH);
    add(canvas);
  }

  public JSlider getSlider(int min, int max, int init, int mjrTkSp, int mnrTkSp) {
    JSlider slider = new JSlider(JSlider.HORIZONTAL, min, max, init);
    slider.setPaintTicks(true);
    slider.setMajorTickSpacing(mjrTkSp);
    slider.setMinorTickSpacing(mnrTkSp);
    slider.setPaintLabels(true);
    slider.addChangeListener(new SliderListener());
    return slider;
  }

  class DrawingCanvas extends Canvas {
    Color color;
    int redValue, greenValue, blueValue;
    int alphaValue = 255;
    float[] hsbValues = new float[3];

    public DrawingCanvas() {
      setSize(350, 350);
      color = new Color(0, 0, 0);
      setBackgroundColor();
    }

    public void setBackgroundColor() {
      color = new Color(redValue, greenValue, blueValue, alphaValue);
      setBackground(color);
    }
  }

  class SliderListener implements ChangeListener {
    public void stateChanged(ChangeEvent e) {
      JSlider slider = (JSlider) e.getSource();

      if (slider == sliderAlpha) {
        canvas.alphaValue = slider.getValue();
        canvas.setBackgroundColor();
      } else if (slider == sliderR) {
        canvas.redValue = slider.getValue();
        displayRGBColor();
      } else if (slider == sliderG) {
        canvas.greenValue = slider.getValue();
        displayRGBColor();
      } else if (slider == sliderB) {
        canvas.blueValue = slider.getValue();
        displayRGBColor();
      } else if (slider == sliderH) {
        canvas.hsbValues[0] = (float) (slider.getValue() * 0.1);
        displayHSBColor();
      } else if (slider == sliderS) {
        canvas.hsbValues[1] = (float) (slider.getValue() * 0.1);
        displayHSBColor();
      } else if (slider == sliderBr) {
        canvas.hsbValues[2] = (float) (slider.getValue() * 0.1);
        displayHSBColor();
      }
      canvas.repaint();
    }

    public void displayRGBColor() {
      canvas.setBackgroundColor();
      Color.RGBtoHSB(canvas.redValue, canvas.greenValue, canvas.blueValue,canvas.hsbValues);
      sliderH.setValue((int) (canvas.hsbValues[0] * 10));
      sliderS.setValue((int) (canvas.hsbValues[1] * 10));
      sliderBr.setValue((int) (canvas.hsbValues[2] * 10));

      rgbValue.setText(Integer.toString(canvas.color.getRGB() & 0xffffff, 16));
    }

    public void displayHSBColor() {
      canvas.color = Color.getHSBColor(canvas.hsbValues[0],
          canvas.hsbValues[1], canvas.hsbValues[2]);
      canvas.redValue = canvas.color.getRed();
      canvas.greenValue = canvas.color.getGreen();
      canvas.blueValue = canvas.color.getBlue();

      sliderR.setValue(canvas.redValue);
      sliderG.setValue(canvas.greenValue);
      sliderB.setValue(canvas.blueValue);

      canvas.color = new Color(canvas.redValue, canvas.greenValue,
          canvas.blueValue, canvas.alphaValue);
      canvas.setBackground(canvas.color);
    }
  }
}