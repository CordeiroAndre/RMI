package clienteComputador;

import server.Produto;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class Client {
    public static void main(String[] args) {
        try {

            Registry registry = LocateRegistry.getRegistry("127.0.0.1", 9000);

            Produto computador = (Produto) registry.lookup("Computador");
            boolean estaVendido = computador.vender();

            if(estaVendido){
                System.out.println("computador vendido com exito!");
            }
            else{
                System.out.println("nao ha mais computadores no estoque");
            }

        } catch (Exception e) {
            System.out.println("execao no cliente " + e);
        }
    }
}