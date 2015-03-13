package com.perisic.beds;

/**
 * Default output device of the Recycling machine. Prints to System.out.
 * @author MC
 *
 */

public class ReceiptPrinter implements PrinterInterface {
	/**
	 * Prints str to System.out
	 * @param str
	 */
	public void print(String str) { 
		System.out.println(str);
	}
}
