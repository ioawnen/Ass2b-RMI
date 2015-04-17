package com.perisic.beds;

/**
 * Represents the overall system. In fact it controls the working of the Recycling Machine. 
 * Central Logic (and so on). 
 * @author Jake Scott
 *
 */
public class DepositItemReceiver {
	ReceiptBasis theReceiptBasis = null; 
	Summary summ = null;
	PrintInterface printer = null; 
	int capacity = 15; //The current capacity of the machine is 15 items
	int items = 0; //current no of items
	

	DepositItemReceiver(PrintInterface printer) {
		super();
		this.printer = printer;
	}
	/**
	 * Initialises the receipt, with a list of items added.
	 */
	public void createReceiptBasis() { 
		theReceiptBasis = new ReceiptBasis(); 
	}
	/**
	 * Initialises a summary
	 */
	public void createSummary() {
		summ = new Summary();
	}
	/**
	 * Receives the slot where an item has been inserted and then adds 
	 * the respective item to the database. 
	 * @param slot
	 */
	public void classifyItem(int slot) {; 
		DepositItem item = null; 
		if( slot == 1 ) {    //Creates an item object for the respective item
			item = new Can(); 
		} else if( slot == 2 ) { 
			item = new Bottle(); 
		} else if ( slot == 3 ) { 
			item = new Crate(); 
		} else if (slot == 4) { 
			item = new PaperBag(); 
		}
		
		if( theReceiptBasis == null ) { //If there is currently no receipt, one is created
			createReceiptBasis();
		}
		if( summ == null ) {  //If there is currently no summary, one is created
			createSummary();
		}
		if ((summ.totalI < capacity)){  //if there is room left
			theReceiptBasis.addItem(item); //added to summary and receipt
			summ.addItem(item);
			printer.print("Added "+item.getName());
			items++;
		} else {  //if machine is full
			String str = "The machine cannot hold any more items";
			printer.print(str);; //output
		}
		
	}
	/**
	 * Provides an output when a request for a receipt is made.
	 */
	public void printReceipt() { 
		if(theReceiptBasis==null){  //If receipt is empty
			printer.print("You have not added anything to the machine.");
		} else {
		String str = theReceiptBasis.computeSum(); //Receipt is constructed
		printer.print(str); //output
		theReceiptBasis = null; //Receipt cleared
		}
	}
	/**
	 * Provides an output when a request for a summary is made.
	 */
	public void printSummary() {
		if(summ==null){  //If summary is empty
			printer.print("There is nothing inside the machine.");
		} else {
		String str = summ.summary();  //Summary is constructed
		printer.print(str);  //output
		}
	}
	/**
	 * When request to change text text colour is made, passes colour via the Print Interface
	 * @param c
	 */
	public void changeColour(String c) {
		printer.changeColour(c); //c is the colour (Passes from RecyclingGUI)
	}
	public int getNumberOfItems() {
		return items;
	}
	public int getCapacity() {
		return capacity;
	}
	public String getSummaryText() {
		return summ.summary();
	}
	public String getReceiptText() {
		return theReceiptBasis.computeSum();
	}
	}
