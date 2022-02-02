package server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {
    public static void main(String [] args) {
        try {

            System.out.println("Inicializando servidor");

            System.setProperty("java.rmi.server.hostname","127.0.0.1");

            // Criamos

            ProdutoImplementacao computador = new ProdutoImplementacao("Computador", "Lenovo laptop", 800000.0);
            ProdutoImplementacao celular = new ProdutoImplementacao("Celular", "MI 9 mobile", 24000.0);
            computador.adicionaEstoque(1);
            celular.adicionaEstoque(1);

            // exportamos o objeto antes de colocar no registry
            Produto stub1 = (Produto) UnicastRemoteObject.exportObject(computador, 0);
            Produto stub2 = (Produto) UnicastRemoteObject.exportObject(celular, 0);

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