package clienteCelular;

import server.Produto;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class Client {
    public static void main(String[] args) {
        try {

            Registry registry = LocateRegistry.getRegistry("127.0.0.1", 9000);

            Produto computador = (Produto) registry.lookup("Celular");
            boolean estaVendido = computador.vender();

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