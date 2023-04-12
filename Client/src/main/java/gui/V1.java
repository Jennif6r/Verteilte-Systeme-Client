package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class V1 {

	private JFrame frame;
	private JPanel panel;
	private JLabel titleLabel;
	private JList<String> pizzaList;
	private DefaultListModel<String> pizzaListModel;
	private JButton orderButton;
	private JTextField quantityTextField;

	public V1() {
//        frame = new JFrame("Pizza Bestellung");
//        panel = new JPanel();
//        titleLabel = new JLabel("Pizza Auswahl:");
//        quantityTextField = new JTextField();
//        orderButton = new JButton("Bestellen");
//
//        // ActionListener für Bestellen-Button
//        orderButton.addActionListener(e -> {
//            String quantityString = quantityTextField.getText();
//            // Logik für Bestellung mit eingegebener Anzahl hier durchführen
//            System.out.println("Anzahl: " + quantityString);
//        });
//
//        // Setzen der maximalen Anzahl an Zeichen und Spalten im Textfeld
//        quantityTextField.setColumns(5); // 5 Spalten
//        quantityTextField.setHorizontalAlignment(JTextField.RIGHT); // Rechtsausrichtung
//
//        // Komponenten dem Panel hinzufügen
//        panel.add(titleLabel);
//        panel.add(quantityTextField);
//        panel.add(orderButton);
//
//        // Panel dem Frame hinzufügen
//        frame.add(panel);
//
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.pack();
//        frame.setVisible(true);
    
//-------------------------------------------------------------------------------
	        frame = new JFrame("Pizza Bestellung");
	        panel = new JPanel(); // GridLayout mit 1 Spalte und dynamischer Anzahl an Zeilen
	        titleLabel = new JLabel("Pizza Auswahl:");
	        pizzaListModel = new DefaultListModel<>();
	        pizzaList = new JList<>(pizzaListModel);
	        orderButton = new JButton("Bestellen");
	        quantityTextField = new JTextField();
	        
	        // ActionListener für Bestellen-Button
	        orderButton.addActionListener(e -> {
	            String quantityString = quantityTextField.getText();
	            // Logik für Bestellung mit eingegebener Anzahl hier durchführen
	            System.out.println("Anzahl: " + quantityString);
	        });
	        // Setzen der maximalen Anzahl an Zeichen und Spalten im Textfeld
	        quantityTextField.setColumns(5); // 5 Spalten
	        quantityTextField.setHorizontalAlignment(JTextField.RIGHT); // Rechtsausrichtung
	        panel.add(quantityTextField);
	        

	        // Pizza-Auswahl zur Liste hinzufügen
	        pizzaListModel.addElement("Salami");
	        pizzaListModel.addElement("Schinken");
	        pizzaListModel.addElement("Salami+Schinken");
	        pizzaListModel.addElement("Margherita");
	        pizzaListModel.addElement("Diavolo Salami");
	        pizzaListModel.addElement("Special");

	        // ActionListener für Bestellen-Button
	        orderButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                String selectedPizza = pizzaList.getSelectedValue();
	                String quantityString = quantityTextField.getText();
	                int quantity = Integer.parseInt(quantityString);
	                // Logik für Bestellung mit ausgewählter Pizza und Anzahl hier durchführen
	                System.out.println("Bestellung: " + selectedPizza + ", Anzahl: " + quantity);
	            }
	        });

	        // Komponenten dem Panel hinzufügen
	        panel.add(titleLabel);
	        panel.add(pizzaList);
	        panel.add(new JLabel("Anzahl:"));
	        panel.add(quantityTextField);
	        panel.add(orderButton);

	        // Panel dem Frame hinzufügen
	        frame.add(panel);

	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setVisible(true);	        
	        frame.pack();

	    
	}

	public static void main(String[] args) {
		V1 pizzaOrderUI = new V1();
	}
}
