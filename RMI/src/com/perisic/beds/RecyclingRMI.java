package com.perisic.beds;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RecyclingRMI extends Remote { 
	
	public String enterPassword(String passwd) throws RemoteException; 
	
	public int getNumberOfItemsInMachine(String sessionCookie)throws RemoteException; 
 
   
} 

