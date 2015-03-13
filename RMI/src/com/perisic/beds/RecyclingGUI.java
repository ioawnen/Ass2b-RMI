package com.perisic.beds;
import java.rmi.registry.*;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.*;

/**
 * A Simple Graphical User Interface for the Recycling Machine. This 
 * also doubles as a server that allows remotely to check the number
 * of items after successful authentication. 
 * @author Marc Conrad
 *
 */
public class RecyclingGUI extends JFrame implements ActionListener  {
	
	String storedPasswd = "bla78"; 
	private String storedCookie = null; 
	/**
	 * This method returns a random cookie if passwd is the as the
	 * stored password in the system. If the password doesn't match 
	 * the stored password it returns the string #noPassword# 
	 * @param passwd the password used for authentication. 
	 * @return
	 */
	public String enterPassword(String passwd) { 
		if( passwd.equals(storedPasswd)) { 
			storedCookie = "U"+Math.random();
			return storedCookie; 
		}
		return "#noPassword#"; 
	}
	/**
	 * returns the number of items entered into the recycling machine
	 * since it's existence. If the cookie matches the one generated
	 * after a successful login the number of items is returned. Otherwise 
	 * a negative number as error code is returned as follows
	 * <ul>
	 * <li> -1: the method has been called without a prior attempt of authentication
	 * <li> -2: attempt to call this method with the #noPassword# token.
	 * <li> -3: any other error.
	 * </ul>
	 * @param sessionCookie a string used for authentication.
	 * @return
	 */
	
	public int getNumberOfItemsInMachine(String sessionCookie) { 
		if( storedCookie == null ) { 
			return -1; 
		} else if ( sessionCookie.equals("#noPassword#")) { 
			return -2; 
		} else if( sessionCookie.equals(storedCookie) ) { 
			int numberOfItems = myCustomerPanel.getTotalNumberOfItems();
			return numberOfItems; 
		} else { 
			return -3; 
		}
	}
	
	
	
	/**
	 * Requirement to serialize the class. 
	 */
	private static final long serialVersionUID = -5772727482959492839L;
/**
 * This method receives the events that are fired by the buttons of the GUI. 
 */
	public void actionPerformed(ActionEvent e) {
		if( e.getSource().equals(slot1) ) { 
            myCustomerPanel.itemReceived(1);
		} else if (e.getSource().equals(slot2)) { 
			myCustomerPanel.itemReceived(2); 
		} else if ( e.getSource().equals(slot3)) { 
			myCustomerPanel.itemReceived(3);
		} else if( e.getSource().equals(receipt)) { 
			myCustomerPanel.printReceipt(); 
		}
	}
	
	JButton slot1 = new JButton("Can"); 
	JButton slot2 = new JButton("Bottle"); 
	JButton slot3 = new JButton("Crate"); 
	
	JButton receipt = new JButton("Receipt"); 
	
	CustomerPanel myCustomerPanel = new CustomerPanel(new Display()); 
	/**
	 * Construction of the GUI. Buttons are added and intialized with an ActionListener
	 * that directs the events into this object. 
	 */
	public RecyclingGUI() {
		super("RMI - Recycling - Machine");
		setSize(400, 100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		JPanel panel = new JPanel(); 
		panel.add(slot1); 
		panel.add(slot2);
		panel.add(slot3); 
		
		slot1.addActionListener(this); 
		slot2.addActionListener(this); 
		slot3.addActionListener(this); 
		
		panel.add(receipt); 
		receipt.addActionListener(this); 
		panel.setBackground(Color.YELLOW); 
		
		
		
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
	       System.out.println("Trouble: " + e);
	     }
		
	}

}