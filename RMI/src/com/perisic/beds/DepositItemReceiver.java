package com.perisic.beds;

/**
 * The controller class the represents the overall system. Responsible for 
 * classifying the items added into the recycling machine and printing the
 * receipt.
 * @author MC
 *
 */
public class DepositItemReceiver {
	/**
	 * Constructor for the Deposit item receiver that takes a configurable printer.
	 * @param printer
	 */
	public DepositItemReceiver(PrinterInterface printer) {
		super();
		this.printer = printer;
	}
	
	int totalNumberOfItems = 0; 
	ReceiptBasis theReceiptBasis = null; 
	PrinterInterface printer = null; 
	
	/**
	 * All items inserted into the machine since it starts to exist. 
	 * @return items in the machine. 
	 */
	int getTotalNumberOfItems() {
		return totalNumberOfItems; 
	}
	/**
	 * Creates the database where all items are stored. 
	 */
	
	public void createReceiptBasis() { 
		theReceiptBasis = new ReceiptBasis(); 
	}
	/**
	 * Checks in which item a slot has been inserted and then
	 * adds the relevant item into the database. 
	 * @param slot
	 */
	public void classifyItem(int slot) { 
		DepositItem item = null; 
		if( slot == 1 ) { 
			item = new Can(); 
		} else if( slot == 2 ) { 
			item = new Bottle(); 
		} else if ( slot == 3 ) { 
			item = new Crate(); 
		} else if (slot ==4 ) { 
			item = new Bag(); 
		}
		if( theReceiptBasis == null ) { 
			createReceiptBasis(); 
		}
		theReceiptBasis.addItem(item); 
		totalNumberOfItems = totalNumberOfItems + 1; 
	}
	
	/**
	 * @param mySize The size of the item entered into the machine.
	 * @param myWeight The weight of the item entered into the machine.
	 */
	public void classifyItem(int mySize, int myWeight) { 
		DepositItem item = null; 
		if( mySize == Can.size && myWeight == Can.weight) { 
			item = new Can(); 
		} else if( mySize == Bottle.size && myWeight == Bottle.weight ) { 
			item = new Bottle(); 
		} else if ( mySize == Crate.size && myWeight == Crate.weight ) { 
			item = new Crate(); 
		} else if ( mySize == Bag.size && myWeight == Bag.weight ) { 
			item = new Bag(); 
		}
		if( theReceiptBasis == null ) { 
			createReceiptBasis(); 
		}
		if( item != null ) {
			theReceiptBasis.addItem(item); 
		} else { 
			printer.print("Item not recognized: size="+mySize+", weight="+myWeight); 
		}
	}
	/**
	 * Send a print request to the output device. 
	 */
	public void printReceipt() { 
		if( theReceiptBasis == null ) { 
			printer.print("No item in the machine");
		} else { 
			String str = theReceiptBasis.computeSum(); 
			printer.print(str); 
			theReceiptBasis = null; 
		}
	}
}
