
import controller.ConfigController;
import view.ServerView;

import javax.swing.*;
import java.awt.*;

public class Server {
    public static void main(String [] args) {
        try {

            System.out.println("Inicializando servidor");

            ConfigController configController = new ConfigController();
            System.setProperty("java.rmi.server.hostname",configController.getServerHost());

            JFrame jFrame = new ServerView();
            jFrame.setPreferredSize(new Dimension(800,800));
            jFrame.pack();
            jFrame.setVisible(true);

            System.out.println("Finalizamos a criacao do servidor!");

        } catch (Exception e) {
            System.out.println("Server creation error" + e);

        }

    }
}