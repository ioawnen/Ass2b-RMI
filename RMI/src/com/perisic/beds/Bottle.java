package com.perisic.beds;

/**
 * Represents a bottle in the database. 
 * @author MC
 *
 */
public class Bottle extends DepositItem {
	static int weight = 10; 
	static int size = 8; 
	/**
	 * Constructed with the value of a bottle. 
	 */
	public Bottle() { 
		value = 15; 
	}
}
