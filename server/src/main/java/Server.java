import model.ProductItem;
import remote.Product;
import view.ServerView;

import javax.swing.*;
import java.awt.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class Server {
    public static void main(String [] args) {
        try {

            System.out.println("Inicializando servidor");

            System.setProperty("java.rmi.server.hostname","127.0.0.1");

            JFrame jFrame = new ServerView();
            jFrame.setPreferredSize(new Dimension(800,800));
            jFrame.pack();
            jFrame.setVisible(true);

            // Criamos
//
//            ProductItem computador = new ProductItem("Computador", "Lenovo laptop", 800000.0);
//            ProductItem celular = new ProductItem("Celular", "MI 9 mobile", 24000.0);
//            computador.adicionaEstoque(10);
//            celular.adicionaEstoque(1);




            System.out.println("Finalizamos a criacao do servidor!");

        } catch (Exception e) {
            System.out.println("Server creation error" + e);

        }

    }
}