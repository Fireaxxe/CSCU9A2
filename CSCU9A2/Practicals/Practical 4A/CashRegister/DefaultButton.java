import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Scanner;
import javax.swing.event.*;

public class DefaultButton {

    public static void main(String[] args) {
        new DefaultButton();
    }

    public DefaultButton() {
        EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    }

                    JFrame frame = new JFrame("Test");
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setLayout(new BorderLayout());
                    frame.add(new TestPane());
                    frame.pack();
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                }

            });
    }

    public class TestPane extends JPanel {

        private JButton button;
        private JLabel label;
        private int count;

        public TestPane() {

            label = new JLabel("Press the button");
            button = new JButton("Press me");

            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridy = 0;
            add(label, gbc);
            gbc.gridy++;
            add(button, gbc);
            gbc.gridy++;
            add(new JButton("No Action Here"), gbc);

            button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        doButtonPressed(e);
                    }

                });

            InputMap im = button.getInputMap(WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
            ActionMap am = button.getActionMap();

            im.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), "spaced");
            am.put("spaced", new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        doButtonPressed(e);
                    }

                });

        }

        @Override
        public void addNotify() {
            super.addNotify();
            SwingUtilities.getRootPane(button).setDefaultButton(button);
        }

        protected void doButtonPressed(ActionEvent evt) {
            count++;
            label.setText("Pressed " + count + " times");
        }

    }

}