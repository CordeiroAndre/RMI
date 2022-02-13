package server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Server {

    private HashMap<String,ProductItem> productItemList;


    public void registerProduct(String productName, ProductItem product){
        productItemList.put(productName, product);
    }

    public void changeProduct(String productName, ProductItem updatedProduct){
        if(!productItemList.containsKey(productName)) return;
        productItemList.put(productName, updatedProduct);
    }

    public void addMoreUnitsToProduct(String productName, int quantity){
        productItemList.get(productName).adicionaEstoque(quantity);
    }

    public HashMap<String, ProductItem> getProductItemList() {
        return productItemList;
    }

    public static void main(String [] args) {
        try {

            System.out.println("Inicializando servidor");

            System.setProperty("java.rmi.server.hostname","127.0.0.1");

            // Criamos

            ProductItem computador = new ProductItem("Computador", "Lenovo laptop", 800000.0);
            ProductItem celular = new ProductItem("Celular", "MI 9 mobile", 24000.0);
            computador.adicionaEstoque(10);
            celular.adicionaEstoque(1);

            // exportamos o objeto antes de colocar no registry
            Product stub1 = (Product) UnicastRemoteObject.exportObject(computador, 0);
            Product stub2 = (Product) UnicastRemoteObject.exportObject(celular, 0);

            // Cria do Registry
            Registry registry = LocateRegistry.createRegistry(9000);

            // Registered the exported object in rmi registry so that client can
            // lookup in this registry and call the object methods.
            registry.bind("Computador", stub1);
            registry.bind("Celular", stub2);



            System.out.println("Finalizamos a criacao do servidor!");

        } catch (Exception e) {
            System.out.println("Server creation error" + e);

        }

    }
}