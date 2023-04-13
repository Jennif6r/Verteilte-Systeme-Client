package gui;

import javax.swing.*;
import javax.swing.text.NumberFormatter;

import mainProgramm.Client;
import models.Order;
import models.Product;
import services.impl.StartOrderClient;

import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderGUI {
    private JFrame frame;
    private JPanel panel;
    private JLabel label;
    private JTextField textField;
    private JButton button;
    private Client client;

    public OrderGUI(Client client) {
    	this.client = client;
        frame = new JFrame("Pizza Bestellung");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new JPanel(new BorderLayout());
        label = new JLabel("Benutzername: ");
        textField = new JTextField(20);
        button = new JButton("Weiter");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = textField.getText();
                try {
					client.register();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
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
        JButton btn2 = new JButton("Eigene Bestellung");
        JButton btn3 = new JButton("Aktive Sammelbestellung beenden");
        
        btn1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openPizzaSelectionWindow(username, client.startOrder(username));
                StartOrderClient startOrder = new StartOrderClient();
                try {
					startOrder.startOrder(username);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
            	btn3.setEnabled(true);
            }
        });
        btn2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	// aktive Bestellungen rausfinden
            	try {
//					client.getActiveOrders();
					showActiveOrders(client.getActiveOrders(), username);
//	                openPizzaSelectionWindow(username, false, null);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
            }
        });
//      btn3.setEnabled(false);
        btn3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//            	System.out.println(client.getMergedOrder(client.orderId));

                JOptionPane.showMessageDialog(frame, "Die aktive Sammelbestellung wurde beendet.");
                JOptionPane.showMessageDialog(frame, "Hier sind die Bestellungen:"
                		+ client.getMergedOrder(client.getMergedOrder("1681393814815")));

            }
        });
        panel.add(btn1);
        panel.add(btn2);
        panel.add(btn3);
        frame.add(panel);
        frame.pack();
        frame.setSize(400,400);
    }
    
	private void showActiveOrders(Map<String, String> activeOrders, String username) throws Exception {
//      String[] options = {"Option 1", "Option 2", "Option 3", "Option 4"};

		frame.getContentPane().removeAll();
        frame.repaint();
        Panel panel = new Panel();
        
    	String[] options = activeOrders.keySet().toArray(new String[0]);
        Map<String, String> activeOrdersDropdown = new HashMap<>();
        activeOrdersDropdown = this.client.getActiveOrders();
        System.out.println("im options-array: " + options.toString());
        
        
        JComboBox<String> comboBox = new JComboBox<>(options);
//        comboBox.setSelectedIndex(0); // Optional: Setzen der ausgewählten Option
        panel.add(comboBox);
       
        frame.setSize(400,400);

        JButton btn1 = new JButton("Absenden");
        btn1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String selectedUser = (String)comboBox.getSelectedItem();
            	openPizzaSelectionWindow(username, activeOrders.get(selectedUser));
            }
        });
        
        JButton btn2 = new JButton("Hauptmenue");
        btn2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	openOptionsWindow(username);
            }
        });
        panel.add(btn1);
        panel.add(btn2);
        frame.add(panel);
        frame.setVisible(true);
        frame.setSize(400,400);

    }

    private void openPizzaSelectionWindow(String username, String orderId) {
    	frame.getContentPane().removeAll();
        frame.repaint();
    	panel = new JPanel(new GridLayout(1,3));

    	String[] pizzen = new String[]{"Salami","Schinken","jenny","rouven","Hallo","Mager"};

        JPanel pizzaPanel = new JPanel(new GridLayout(pizzen.length+1, 1));
        for(int i = 0; i < pizzen.length; i++) {
        	pizzaPanel.add(new JLabel(pizzen[i]));
        }
        panel.add(pizzaPanel);
        
        
        JPanel pricePanel = new JPanel(new GridLayout(pizzen.length+1, 1));
        for(int i = 0; i < 6; i++) {
        	pricePanel.add(new JLabel(""+i));
        }
        JFormattedTextField[] formattedTFields = new JFormattedTextField[pizzen.length];
        JButton btnPizzaSend = new JButton("Bestellen");
        btnPizzaSend.addActionListener(new ActionListener() {
        
			@Override
			public void actionPerformed(ActionEvent e) {
				Order order = new Order();
				ArrayList<Product> productList = new ArrayList<>();
				for(int i = 0; i < pizzen.length; i++) {
					String ftf = formattedTFields[i].getText();
					System.out.println(ftf);
					
					if(!ftf.equals("")) {
						int number = Integer.parseInt(ftf);
						if(number > 0) {
							productList.add(new Product(pizzen[i], 5.5, number));
						}
					}
					System.out.println(formattedTFields[i].getValue());
				}
				order.addProduct(productList);
				client.addOrderItem(order, orderId);
				JOptionPane orderSentInfo = new JOptionPane();
				orderSentInfo.showMessageDialog(frame, "Bestellung wurde an den Host gesendet.");
                openOptionsWindow(username);
			}
		});
        
        JButton btnGoToMenu = new JButton("Hauptmenue");
        btnGoToMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				openOptionsWindow(username);
			}
		});
        
        pricePanel.add(btnPizzaSend);
        panel.add(pricePanel);
        
        
        JPanel mengePanel = new JPanel(new GridLayout(pizzen.length+1, 1));
        NumberFormatter formatter = new NumberFormatter(NumberFormat.getInstance());
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(0);
        formatter.setMaximum(Integer.MAX_VALUE);
        formatter.setAllowsInvalid(false);
        
        formatter.setCommitsOnValidEdit(true);
//      JFormattedTextField[] formattedTFields = new JFormattedTextField[pizzen.length];
        for(int i = 0; i < pizzen.length; i++) {
        	JFormattedTextField formattedField = new JFormattedTextField(formatter);
        	formattedTFields[i] = formattedField;
        	mengePanel.add(formattedField);
        }
        mengePanel.add(btnGoToMenu);
        panel.add(mengePanel);
        frame.add(panel);        
        frame.setVisible(true);
        frame.setSize(400,400);

        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
//        JLabel lbl1 = new JLabel("Wählen Sie eine Pizza aus:");
//        panel.add(lbl1);
//        JRadioButton rbtn1 = new JRadioButton("Salami");
//        JRadioButton rbtn2 = new JRadioButton("Schinken");
//        JRadioButton rbtn3 = new JRadioButton("Salami + Schinken");
//        JRadioButton rbtn4 = new JRadioButton("Margherita");
//        JRadioButton rbtn5 = new JRadioButton("Diavolo Salami");
//        JRadioButton rbtn6 = new JRadioButton("Special");
//        ButtonGroup group = new ButtonGroup();
////        SpinnerNumberModel spinnerModel = new SpinnerNumberModel(1, 1, 10, 1); // Min: 1, Max: 10, Schritt: 1
////        JSpinner spinner = new JSpinner(spinnerModel);
//        group.add(rbtn1);
//        group.add(rbtn2);
//        group.add(rbtn3);
//        group.add(rbtn4);
//        group.add(rbtn5);
//        group.add(rbtn6);
//        panel.add(rbtn1);
//        panel.add(rbtn2);
//        panel.add(rbtn3);
//        panel.add(rbtn4);
//        panel.add(rbtn5);
//        panel.add(rbtn6);
////        panel.add(spinner);
//        JButton btn = new JButton("Bestellen");
//        btn.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                String pizza = "";
//                if (rbtn1.isSelected()) {
//                    pizza = "Salami";
//                } else if (rbtn2.isSelected()) {
//                    pizza = "Schinken";
//                } else if (rbtn3.isSelected())
//                {
//                    pizza = "Salami + Schinken";
//                } else if (rbtn4.isSelected()) {
//                    pizza = "Margherita";
//                } else if (rbtn5.isSelected()) {
//                    pizza = "Diavolo Salami";
//                } else if (rbtn6.isSelected()) {
//                    pizza = "Special";
//                } else {
//                    JOptionPane.showMessageDialog(frame, "Bitte wählen Sie eine Pizza aus.");
//                    return;
//                }
//                openOptionsWindow(username);
//            }
//        });
//        panel.add(btn);
//        frame.add(panel);
//        frame.pack();
//        frame.setSize(400,400);

    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                new OrderGUI();
//            }
//        });
//    	System.out.println("Hallo");
//    }
}
