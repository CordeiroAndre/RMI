package model;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Using server.model.model.Product otherwise it will throw ClassCastException because the client and server are in
 * the same project and have the same Interface model.Product. Keep it to use this when creating the jar
 */
public interface Product extends Remote {
    // Define product behaviour (Methods)

     String getName() throws RemoteException;
     String getDescription() throws RemoteException;
     double getPrice() throws RemoteException;
     int getInventoryQty() throws RemoteException;
     boolean reduceQty() throws RemoteException;
}