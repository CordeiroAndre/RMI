package clienteCelular;

import server.Product;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class Client {
    public static void main(String[] args) {
        try {

            Registry registry = LocateRegistry.getRegistry("127.0.0.1", 9000);

            Product computador = (Product) registry.lookup("Celular");
            boolean estaVendido = computador.reduceQty();

            if(estaVendido){
                System.out.println("Celular vendido com exito!");
            }
            else{
                System.out.println("nao ha mais Celulares no estoque");
            }

        } catch (Exception e) {
            System.out.println("execao no cliente " + e);
        }
    }
}