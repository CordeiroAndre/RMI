package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Product extends Remote {
     String getName() throws RemoteException;
     String getDescription() throws RemoteException;
     double getPrice() throws RemoteException;
     int getInventoryQty() throws RemoteException;
     boolean reduceQty() throws RemoteException;
}