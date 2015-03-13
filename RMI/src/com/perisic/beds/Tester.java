package com.perisic.beds;

/**
 * Class that tests the functionalities of the Recycling Machine using
 * the slots. 
 * @author MC
 *
 */
public class Tester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CustomerPanel thePanel = new CustomerPanel(new ReceiptPrinter()); 
		thePanel.itemReceived(1); 
		thePanel.itemReceived(2); 
	
		thePanel.itemReceived(1); 
		thePanel.itemReceived(3);
		thePanel.itemReceived(4);
		thePanel.printReceipt(); 
	}

}
