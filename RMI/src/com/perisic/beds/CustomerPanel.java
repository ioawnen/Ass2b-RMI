package com.perisic.beds;

/**
 * This class interfaces with the customer - the person who adds items into the machine 
 * and requests a receipt after that. 
 * @author MC
 *
 */
public class CustomerPanel {
	
	
	DepositItemReceiver receiver = null;  
	
	/** 
	 * Returns all items in the machine
	 * @return all items in the machine. 
	 */
	/**
	 * Returns the total number of items in the machine since its existence.
	 * If the machine is emptied, please restart this software to reset.
	 * @return number of items entered into the machine. 
	 */
	
	int getTotalNumberOfItems() { 
		return receiver.getTotalNumberOfItems(); 
	}
	
	
	/**
	 * Constructor for the CustomerPanel to be configured with the printer
	 * @param printer
	 */
	public CustomerPanel(PrinterInterface printer) {
		super();
		receiver = new DepositItemReceiver(printer); 
	}
	/**
	 * Whenever an item is inserted into a certain slot this method is called.
	 * @param slot
	 */
	public void itemReceived(int slot) { 
		receiver.classifyItem(slot); 
	}
	/**
	 * Whenever an item is inserted it is measured and the parameters of
	 * size and weight are passed on to the system. 
	 * @param slot
	 */
	public void itemReceived(int mySize, int myWeight) { 
		receiver.classifyItem(mySize, myWeight); 
	}
	/**
	 * When the receipt is requested. 
	 */
	public void printReceipt() { 
		receiver.printReceipt();
	}
}
