package com.perisic.beds;

import java.awt.Color;

import javax.swing.*; 
/** 
 * Displays text in a frame plus on System.out.
 */
public class Display2 extends JFrame implements PrinterInterface {
	/**
	 * A serialVersionUID is required by the JFrame class. 
	 */
	private static final long serialVersionUID = -8505887234618184162L;
	private JTextArea outputWindow; 
	
	/**
	 * when constructed the display will be directly visible. 
	 */	
	public Display2() {
		super();
		setSize(200, 600);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);			
		outputWindow = new JTextArea();
		outputWindow.setForeground(Color.BLUE);
		getContentPane().add(outputWindow);
		setVisible(true); 
		}
	/** 
	 * Prints the text str to the screen. Any previous text will be overwritten. 
	 * @see com.perisic.beds.PrinterInterface#print(java.lang.String)
	 */
	public void print(String str) { 
		System.out.println(str);
		
		outputWindow.setText(str); 
		outputWindow.repaint();
	}

}