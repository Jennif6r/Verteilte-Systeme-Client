package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class OrderGUI {
    private JFrame frame;
    private JPanel panel;
    private JLabel label;
    private JTextField textField;
    private JButton button;

    public OrderGUI() {
        frame = new JFrame("Pizza Bestellung");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new JPanel(new BorderLayout());
        label = new JLabel("Benutzername: ");
        textField = new JTextField(20);
        button = new JButton("Weiter");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = textField.getText();
                openOptionsWindow(username);
            }
        });
        panel.add(label, BorderLayout.WEST);
        panel.add(textField, BorderLayout.CENTER);
        panel.add(button, BorderLayout.EAST);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    private void openOptionsWindow(String username) {
        frame.getContentPane().removeAll();
        frame.repaint();
        panel = new JPanel(new GridLayout(3, 1));
        JButton btn1 = new JButton("Sammelbestellung anfangen");
        btn1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openPizzaSelectionWindow(username, true);
            }
        });
        JButton btn2 = new JButton("Eigene Bestellung");
        btn2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openPizzaSelectionWindow(username, false);
            }
        });
        JButton btn3 = new JButton("Aktive Sammelbestellung beenden");
        btn3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Implementieren Sie hier die Logik zum Beenden der aktiven Sammelbestellung
                JOptionPane.showMessageDialog(frame, "Die aktive Sammelbestellung wurde beendet.");
            }
        });
        panel.add(btn1);
        panel.add(btn2);
        panel.add(btn3);
        frame.add(panel);
        frame.pack();
    }

    private void openPizzaSelectionWindow(String username, boolean isGroupOrder) {
        frame.getContentPane().removeAll();
        frame.repaint();
        panel = new JPanel(new GridLayout(6, 1));
        JLabel lbl1 = new JLabel("Wählen Sie eine Pizza aus:");
        panel.add(lbl1);
        JRadioButton rbtn1 = new JRadioButton("Salami");
        JRadioButton rbtn2 = new JRadioButton("Schinken");
        JRadioButton rbtn3 = new JRadioButton("Salami + Schinken");
        JRadioButton rbtn4 = new JRadioButton("Margherita");
        JRadioButton rbtn5 = new JRadioButton("Diavolo Salami");
        JRadioButton rbtn6 = new JRadioButton("Special");
        ButtonGroup group = new ButtonGroup();
        group.add(rbtn1);
        group.add(rbtn2);
        group.add(rbtn3);
        group.add(rbtn4);
        group.add(rbtn5);
        group.add(rbtn6);
        panel.add(rbtn1);
        panel.add(rbtn2);
        panel.add(rbtn3);
        panel.add(rbtn4);
        panel.add(rbtn5);
        panel.add(rbtn6);
        JButton btn = new JButton("Bestellen");
        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String pizza = "";
                if (rbtn1.isSelected()) {
                    pizza = "Salami";
                } else if (rbtn2.isSelected()) {
                    pizza = "Schinken";
                } else if (rbtn3.isSelected())
                {
                    pizza = "Salami + Schinken";
                } else if (rbtn4.isSelected()) {
                    pizza = "Margherita";
                } else if (rbtn5.isSelected()) {
                    pizza = "Diavolo Salami";
                } else if (rbtn6.isSelected()) {
                    pizza = "Special";
                } else {
                    JOptionPane.showMessageDialog(frame, "Bitte wählen Sie eine Pizza aus.");
                    return;
                }
                if (isGroupOrder) {
                    // Implementieren Sie hier die Logik für die Gruppenbestellung
                    JOptionPane.showMessageDialog(frame, "Die Pizza " + pizza + " wurde zur Sammelbestellung hinzugefügt.");
                } else {
                    // Implementieren Sie hier die Logik für die individuelle Bestellung
                    JOptionPane.showMessageDialog(frame, "Die Pizza " + pizza + " wurde für " + username + " bestellt.");
                }
            }
        });
        panel.add(btn);
        frame.add(panel);
        frame.pack();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new OrderGUI();
            }
        });
    	System.out.println("Hallo");
//    	new OrderGUI();
    }
}
