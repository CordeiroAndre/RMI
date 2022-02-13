package client.controller;

import server.model.Product;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;

public class ClientWindowController {

    private Registry registry;

    public ClientWindowController() {
        try {
            registry = LocateRegistry.getRegistry("127.0.0.1", 9000);
        } catch (Exception e) {
            System.out.println("execao no cliente " + e);
        }
    }

    public List<Product> visualizarProdutosEstoque(){
        try {
            List<String> productNames = List.of(registry.list());
            List<Product> products = new ArrayList<>();

            for (String name:productNames) {
                Product product = (Product) registry.lookup(name);
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
            Product product = (Product) registry.lookup(name);
            for(int i= 0; i<quantity; i++){
                product.reduceQty();
            }
            return true;
        }
        catch (Exception e){
            return false;
        }

    }
}
