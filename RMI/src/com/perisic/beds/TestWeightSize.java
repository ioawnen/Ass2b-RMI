package com.perisic.beds;

/**
 * Class that tests the functionalities of the recycling machine 
 * using weight and size. 
 * @author MC
 *
 */
public class TestWeightSize {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CustomerPanel thePanel = new CustomerPanel(new Display2()); 
		for(int i = 0; i < 2; i++ ) {
			thePanel.itemReceived(Can.size, Can.weight);
			thePanel.itemReceived(Bottle.size, Bottle.weight);
			thePanel.itemReceived(Bottle.size, Bottle.weight);
			thePanel.itemReceived(Bag.size, Bag.weight);
			thePanel.itemReceived(10,30);
			thePanel.printReceipt(); 
		}
	}

}
