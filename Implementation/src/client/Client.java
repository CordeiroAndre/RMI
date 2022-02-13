package client;


import server.ProductItem;
import server.Server;

import javax.swing.*;
import java.awt.*;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;


public class Client {

    Registry registry;

    public Client() {
        try {
            registry = LocateRegistry.getRegistry("127.0.0.1", 9000);
        } catch (Exception e) {
            System.out.println("execao no cliente " + e);
        }
    }

    public List<server.Product> visualizarProdutosEstoque(){
        try {
            List<String> productNames = List.of(registry.list());
            List<server.Product> products = new ArrayList<>();

            for (String name:productNames) {
                server.Product product = (server.Product) registry.lookup(name);
                products.add(product);
            }

            return products;

        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public boolean efetuarVenda(String name, int quantity){

        try {

            server.Product product = (server.Product) registry.lookup(name);

            for(int i= 0; i<quantity; i++){
                product.reduceQty();
            }
            return true;
        }
        catch (Exception e){
            return false;
        }

    }

    public static void main(String[] args) throws RemoteException {
       ClientWindow clientWindow = new ClientWindow();

    }
}