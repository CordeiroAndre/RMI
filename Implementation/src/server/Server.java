package server;

import server.model.Product;
import server.model.ProductItem;
import server.view.ServerWindow;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

public class Server {


    public static void main(String [] args) throws RemoteException {

        ServerWindow serverWindow = new ServerWindow();

    }
}