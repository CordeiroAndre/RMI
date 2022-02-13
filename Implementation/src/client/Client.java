package client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class Client {
    public static void main(String[] args) {
        try {

            Registry registry = LocateRegistry.getRegistry("127.0.0.1", 9000);
            for (String s : registry.list()) {
                System.out.println(s);
            }

            //keeping server.Product otherwise will throw ClassCastException because the client and server are in
            //the same git project and have the same Interface Product. Change it when creating the jar
            server.Product computador = (server.Product) registry.lookup("Computador");
            System.out.println(computador.getInventoryQty());
            while (computador.getInventoryQty() > 0) {
                boolean estaVendido = computador.reduceQty();

                if (estaVendido)  System.out.println("computador vendido com exito!");
                else System.out.println("nao ha mais computadores no estoque");
            }

        } catch (Exception e) {
            System.out.println("execao no cliente " + e);
        }
    }
}