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
	private JButton button;
	private Client client;
	private boolean aktivOrder = false;

	public OrderGUI(Client client) {
		this.client = client;
		frame = new JFrame("Pizza Bestellung");
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					client.logout();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				System.exit(0);
			}
		});
		panel = new JPanel(new BorderLayout());
		JLabel userLabel = new JLabel("Benutzername: ");
		JTextField textFieldUserName = new JTextField(20);
		JButton btnSubmit = new JButton("Weiter");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = textFieldUserName.getText();
				client.setUsername(username);
				try {
					client.register();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				openOptionsWindow(username);
			}
		});
		panel.add(userLabel, BorderLayout.WEST);
		panel.add(textFieldUserName, BorderLayout.CENTER);
		panel.add(btnSubmit, BorderLayout.EAST);
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
	}

	private void openOptionsWindow(String username) {
		frame.getContentPane().removeAll();
		frame.repaint();
		panel = new JPanel(new GridLayout(3, 1));

		JButton btnStartOrder = new JButton("Sammelbestellung anfangen");
		JButton btnAddOrder = new JButton("Eigene Bestellung");
		JButton btnEndOrder = new JButton("Aktive Sammelbestellung beenden");

		btnStartOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aktivOrder = true;
				openPizzaSelectionWindow(username, client.startOrder(username));
			}
		});
		btnAddOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					showActiveOrders(client.getActiveOrders(), username);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnEndOrder.setEnabled(aktivOrder);
		btnEndOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frame, "Die aktive Sammelbestellung wurde beendet.");
				JOptionPane.showMessageDialog(frame,
						"Hier sind die Bestellungen:\n" + client.getMergedOrder(client.orderId));
				aktivOrder = false;
				openOptionsWindow(username);
			}
		});
		panel.add(btnStartOrder);
		panel.add(btnAddOrder);
		panel.add(btnEndOrder);
		frame.add(panel);
		frame.pack();
		frame.setSize(400, 400);
	}

	private void showActiveOrders(Map<String, String> activeOrders, String username) throws Exception {
		frame.getContentPane().removeAll();
		frame.repaint();
		Panel panel = new Panel();

		JComboBox<String> comboBox = new JComboBox<>(activeOrders.keySet().toArray(new String[0]));
		panel.add(comboBox);

		frame.setSize(400, 400);

		JButton btnSend = new JButton("Absenden");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openPizzaSelectionWindow(username, activeOrders.get((String) comboBox.getSelectedItem()));
			}
		});

		JButton btnMenue = new JButton("Hauptmenue");
		btnMenue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openOptionsWindow(username);
			}
		});
		panel.add(btnSend);
		panel.add(btnMenue);
		frame.add(panel);
		frame.setSize(400, 400);
		frame.setVisible(true);

	}

	private void openPizzaSelectionWindow(String username, String orderId) {
		frame.getContentPane().removeAll();
		frame.repaint();
		panel = new JPanel(new GridLayout(1, 3));

		List<Product> pizzen = client.getPizzen();
		JPanel pizzaPanel = new JPanel(new GridLayout(pizzen.size() + 1, 1));
		for(Product pizza : pizzen) {
			pizzaPanel.add(new JLabel(pizza.getName()));
		}
		panel.add(pizzaPanel);

		JPanel pricePanel = new JPanel(new GridLayout(pizzen.size() + 1, 1));
		for (Product pizza : pizzen) {
			pricePanel.add(new JLabel("" + pizza.getPrice()));
		}
		JFormattedTextField[] numberOfPizzaFields = new JFormattedTextField[pizzen.size()];
		JButton btnPizzaSend = new JButton("Bestellen");
		btnPizzaSend.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Order order = new Order();
				ArrayList<Product> productList = new ArrayList<>();
				for (int i = 0; i < pizzen.size(); i++) {
					String numberOfPizzaAsString = numberOfPizzaFields[i].getText();
					if (!numberOfPizzaAsString.equals("")) {
						int numberOfPizza = Integer.parseInt(numberOfPizzaAsString);
						if (numberOfPizza > 0) {
							Product pizza = pizzen.get(i);
							pizza.setNumber(numberOfPizza);
							productList.add(pizza);
						}
					}
				}
				order.addProduct(productList);
				client.addOrderItem(order, orderId);
				JOptionPane.showMessageDialog(frame, "Bestellung wurde an den Host gesendet."); 
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

		JPanel mengePanel = new JPanel(new GridLayout(pizzen.size() + 1, 1));
		NumberFormatter formatter = new NumberFormatter(NumberFormat.getInstance());
		formatter.setValueClass(Integer.class);
		formatter.setMinimum(0);
		formatter.setMaximum(Integer.MAX_VALUE);
		formatter.setAllowsInvalid(false);
		formatter.setCommitsOnValidEdit(true);
		
		for (int i = 0; i < pizzen.size(); i++) {
			JFormattedTextField numberField = new JFormattedTextField(formatter);
			numberOfPizzaFields[i] = numberField;
			mengePanel.add(numberField);
		}
		mengePanel.add(btnGoToMenu);
		panel.add(mengePanel);
		frame.add(panel);
		frame.setVisible(true);
		frame.setSize(400, 400);
	}
}
