package com.perisic.beds;
import java.rmi.registry.*;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.awt.Insets;

import javax.swing.*;

import com.perisic.beds.CustomerPanel;


public class RecyclingGUI extends JFrame implements ActionListener  {
	
	
	
	public static boolean green = false; //initially text is pink, not green
	CustomerPanel myCustomerPanel = new CustomerPanel( new Display());  //display window constructed
	private ArrayList<String> feedbackList = new ArrayList<String>();
	
	
	public int getSpaceRemaining() {
		int items = myCustomerPanel.getNumberOfItems();
		int capacity = myCustomerPanel.getCapacity();
		
		int result = (items-capacity)-((items-capacity)*2);
		
		return result;
	}
	
	
	
	/**
	 * Requirement to serialize the class. 
	 */
	private static final long serialVersionUID = -5772727482959492839L;
/**
 * This method receives the events that are fired by the buttons of the GUI. 
 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(slot1)) {  //if slot 1 is pressed, a can is added to the machine
			
			myCustomerPanel.itemReceived(1);
			if(getSpaceRemaining()==1) { status.setText("Can Inserted. " + getSpaceRemaining() + " space remaining."); }
			else if (getSpaceRemaining()!=0) { status.setText("Can Inserted. " + getSpaceRemaining() + " spaces remaining."); }
			else { status.setText("The machine is full"); }
			
		} else if (e.getSource().equals(slot2)) {
			myCustomerPanel.itemReceived(2);
			if(getSpaceRemaining()==1) { status.setText("Bottle Inserted. " + getSpaceRemaining() + " space remaining."); }
			else if (getSpaceRemaining()!=0) { status.setText("Bottle Inserted. " + getSpaceRemaining() + " spaces remaining."); }
			else { status.setText("The machine is full"); }
			
		} else if (e.getSource().equals(slot3)) {
			myCustomerPanel.itemReceived(3);
			if(getSpaceRemaining()==1) { status.setText("Crate Inserted. " + getSpaceRemaining() + " space remaining."); }
			else if (getSpaceRemaining()!=0) { status.setText("Crate Inserted. " + getSpaceRemaining() + " spaces remaining."); }
			else { status.setText("The machine is full"); }
			
		} else if (e.getSource().equals(slot4)) {
			myCustomerPanel.itemReceived(4);
			if(getSpaceRemaining()==1) { status.setText("Paper Bag Inserted. " + getSpaceRemaining() + " space remaining."); }
			else if (getSpaceRemaining()!=0) { status.setText("Paper Bag Inserted. " + getSpaceRemaining() + " spaces remaining."); }
			else { status.setText("The machine is full"); }
			
		} else if (e.getSource().equals(receipt)) { //receipt is output when this button is pressed
			status.setText("");
		myCustomerPanel.printReceipt(); //prints the receipt
		} else if (e.getSource().equals(printer)) { //pressing the button changes text colour between pink and green
			status.setText("");
			changeDisplay(); 
		} else if (e.getSource().equals(summary)) { //summary is output when this button is pressed
			myCustomerPanel.printSummary();
			status.setText("");
		} else if (e.getSource().equals(feedback)) {
			System.err.println("Feeback Button pressed");

			JFrame frame = new JFrame("Feedback");
		    // open prompt for feedback
		    String input = JOptionPane.showInputDialog(frame, "Leave Feedback");

		    if(input!=null){ 
		    	feedbackList.add(input); 
		    	myCustomerPanel.setFeedback(input);
		    }
		}
	}
	
	JButton slot1 = new JButton("Can"); 
	JButton slot2 = new JButton("Bottle"); 
	JButton slot3 = new JButton("Crate"); 
	JButton slot4 = new JButton("Paper Bag");
	JButton printer = new JButton("Change to Green Display");
	JButton summary = new JButton("Summary");
	JButton receipt = new JButton("Receipt"); 
	JButton feedback = new JButton("Feedback");
	JLabel status = new JLabel(" ",SwingConstants.CENTER); //status 
	int capacity = 15;
	
	 
	 
	/**
	 * Construction of the GUI. Buttons are added and intialized with an ActionListener
	 * that directs the events into this object. 
	 */
	public RecyclingGUI() {
		super();
		setTitle("Recycling Machine");
		setSize(400, 250);
		setLocation(500,450);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		JPanel panel = new JPanel(new GridBagLayout()); 
		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.insets = new Insets(5,5,5,5);
		c.fill = GridBagConstraints.HORIZONTAL;
		
		c.gridx = 0;
		c.gridy = 0;	
		panel.add(slot1,c); 
		c.gridy = 1;
		panel.add(slot2,c);
		c.gridy = 2;
		panel.add(slot3,c);
		c.gridy = 3;
		panel.add(slot4,c);
 		
		c.gridx = 1;
		c.gridy = 0;
		panel.add(receipt,c); 
		c.gridy = 1;
		panel.add(printer,c);
		c.gridy = 2;
		panel.add(summary,c);
		c.gridy = 3;
		panel.add(feedback,c);
		
		c.gridx = 0;
		c.gridy = 5;
		c.gridwidth = 2;
		panel.add(status,c);
		
		slot1.addActionListener(this); 
		slot2.addActionListener(this); 
		slot3.addActionListener(this); 
		slot4.addActionListener(this);
		receipt.addActionListener(this); 
		printer.addActionListener(this);
		summary.addActionListener(this);
		feedback.addActionListener(this);
		
		
		slot1.setBackground(Color.green);
		slot2.setBackground(Color.green);
		slot3.setBackground(Color.green);
		slot4.setBackground(Color.green);
		
		
		
		getContentPane().add(panel);
		panel.repaint();
	
	}
	/**
	 * Where everything starts. 
	 * @param args
	 */
	public static void main(String [] args ) { 
		RecyclingGUI myGUI = new RecyclingGUI(); 
		myGUI.setVisible(true);

		try {
			RecyclingRMIImplementation cImpl = new RecyclingRMIImplementation();
			cImpl.setCustomerPanel(myGUI.myCustomerPanel); 
			
			RecyclingRMI c = cImpl; 
			
			Registry reg = LocateRegistry.createRegistry(1099);
			reg.rebind("RecyclingService",c);
			System.out.println("Starting Service. Welcome to the Recycling World of RMI!");
	     } catch (Exception e) {
	       System.err.println("Trouble: " + e);
	     }
		
	}
	public void changeDisplay(){
		if (green==false){ //if the text is not set to green, sets to green
			green = true;
			printer.setText("Change to Pink Display"); //text on button changed
			myCustomerPanel.changeColour("GREEN");
		} else {
		green = false; //if it is currently green, the text is set to pink
		printer.setText("Change to Green Display");
		myCustomerPanel.changeColour("PINK");
		}
	}

}