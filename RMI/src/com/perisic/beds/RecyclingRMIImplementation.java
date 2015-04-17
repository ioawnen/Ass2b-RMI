package com.perisic.beds;

import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;

public class RecyclingRMIImplementation extends UnicastRemoteObject implements RecyclingRMI {

	private static final long serialVersionUID = 1L;
	
	public RecyclingRMIImplementation() throws RemoteException {
		// TODO Auto-generated constructor stub
		super();
	}

	public RecyclingRMIImplementation(int arg0) throws RemoteException {
		super(arg0);
		// TODO Auto-generated constructor stub
	}
	
	CustomerPanel myCustomerPanel = null; 
	void setCustomerPanel(CustomerPanel panel_in) { 
		myCustomerPanel = panel_in; 
	}
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
			if( myCustomerPanel == null ) { 
				return -4; 
			} else { 
				return myCustomerPanel.getNumberOfItems();
			}
		} else { 
			return -3; 
		}
	}
	
	public int testButton(String sessionCookie, int butNum) {
		if(storedCookie==null) {
			return -1;
		} else if(sessionCookie.equals("#noPassword#")) {
			return -2;
		} else if(sessionCookie.equals(storedCookie)) {
			//DO THE BUTTON PRESS
			myCustomerPanel.itemReceived(butNum);
			return 1;

		} else {
			return -3;
		}
	}
}