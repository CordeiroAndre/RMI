import controller.ClientController;
import view.ClientView;

import javax.swing.*;
import java.awt.*;


public class Client {
    public static void main(String[] args) {
        try {
            ClientController clientController = new ClientController();
            ClientView clientView = new ClientView();

//            ConfigController configController = new ConfigController();
//            ConfigView configView = configController.getConfigView();

//            SaleController saleController = new SaleController();
//            SaleView saleView;

//            clientView.setControllers(clientController, configController,saleController);

            JFrame jFrame = clientView;
            jFrame.setPreferredSize(new Dimension(1000,600));
            jFrame.pack();
            jFrame.setVisible(true);


//            for (Component component : jFrame.getContentPane().getComponents()) {
//                if (component.getName().equals("content"))
//                    (component).setContentPanel(configView);
//            }

//            Registry registry = LocateRegistry.getRegistry(configController.getServerHost(), configController.getServerPort());
//            for (String s : registry.list()) {
//                System.out.println(s);
//            }

////            keeping server.Product otherwise will throw ClassCastException because the client and server are in
////            the same git project and have the same Interface Product. Change it when creating the jar
//            server.Product computador = (server.Product) registry.lookup("Computador");
//            System.out.println(computador.getInventoryQty());
//            while (computador.getInventoryQty() > 0) {
//                boolean estaVendido = computador.reduceQty();
//
//                if (estaVendido)  System.out.println("computador vendido com exito!");
//                else System.out.println("nao ha mais computadores no estoque");
//            }

        } catch (Exception e) {
            System.out.println("execao no cliente " + e);
        }
    }
}