package controller;

import model.Product;
import model.ProductItem;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

public class ServerWindowController {

    private HashMap<String,ProductItem> productItemList = new HashMap<>();
    private Registry registry;

    public ServerWindowController() {
        try {

            System.out.println("Inicializando servidor");

            System.setProperty("java.rmi.server.hostname","127.0.0.1");
            
            registry = LocateRegistry.createRegistry(9000);

            System.out.println("Finalizamos a criacao do servidor!");

        } catch (Exception e) {
            System.out.println("Server creation error" + e);

        }
    }

    public void registerProduct(ProductItem product){

        try {
            productItemList.put(product.getName(), product);
            Product stub = (Product) UnicastRemoteObject.exportObject(product, 0);
            registry.bind(product.getName(), stub);

        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        }

    }

    public void changeProduct(String productName, ProductItem updatedProduct){
        if(!productItemList.containsKey(productName)) return;

        try {
            registry.rebind(productName, updatedProduct);
            productItemList.put(productName, updatedProduct);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    public void addMoreUnitsToProduct(String productName, int quantity){
        productItemList.get(productName).adicionaEstoque(quantity);
    }

    public HashMap<String, ProductItem> getProductItemList() {
        return productItemList;
    }

}
