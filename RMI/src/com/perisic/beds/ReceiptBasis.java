package com.perisic.beds;
import java.util.Vector; 

/**
 * The database of the system. 
 * @author MC
 *
 */
public class ReceiptBasis {
	private Vector<DepositItem> myItems = new Vector<DepositItem>();
	/**
	 * Insert an item (DepositItem) into the database. 
	 * @param item
	 */
	public void addItem(DepositItem item) { 
		myItems.add(item); 
		item.number = myItems.indexOf(item); 
	}
	/**
	 * Computes all the values of the items in the database and returns 
	 * as string that can be sent to the output. 
	 * @return
	 */
	public String computeSum() { 
		String receipt = ""; 
		int sum = 0; 
		for(int i=0; i < myItems.size(); i++ ) {
			DepositItem item = myItems.get(i); 
			String name = item.getClass().getSimpleName(); 
			receipt = receipt + item.number +": "+item.value + " ("+name+")"; 
			receipt = receipt + System.getProperty("line.separator");
			sum = sum + item.value; 
		}
		receipt = receipt + "Total: "+sum; 
		return receipt; 
	}
}
