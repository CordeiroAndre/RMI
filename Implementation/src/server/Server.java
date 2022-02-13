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

//        Server server = new Server();
//        ProductItem productItem = new ProductItem("Farofa", "Fafora York bom d+", 5.00);
//        server.registerProduct(productItem);
//
//        ProductItem productItem2 = new ProductItem("Arroz", "combina bem com feijao", 2.25);
//        server.registerProduct(productItem2);
//
//        ProductItem productItem3 = new ProductItem("Feijao", "Gosto de d+", 7.25);
//        server.registerProduct(productItem3);
//
//        server.addMoreUnitsToProduct("Farofa", 50);
//        server.addMoreUnitsToProduct("Arroz", 50);
//        server.addMoreUnitsToProduct("Feijao", 50);

        ServerWindow serverWindow = new ServerWindow();

    }
}