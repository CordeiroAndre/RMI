package client.view;

import client.controller.ClientWindowController;
import server.model.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

public class ClientWindow {
    ClientWindowController clientWindowController;
    JPanel contentPanel;
    JFrame frame;


    public ClientWindow() throws RemoteException {
        clientWindowController =new ClientWindowController();
        frame = new JFrame("FrameDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        contentPanel = new JPanel();
        BoxLayout boxlayoutY = new BoxLayout(contentPanel, BoxLayout.Y_AXIS);
        contentPanel.setLayout(boxlayoutY);

        //create the refresh button
        JPanel panel = createStaticComponents();
        makeProduct();



        panel.setLayout(new GridLayout(2,1));
        frame.getContentPane().add(panel, BorderLayout.CENTER);


        frame.pack();
        frame.setVisible(true);
    }

    private JPanel createStaticComponents() {
        JPanel panel = new JPanel();
        JButton refresh = new JButton("Refresh");

        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    makeProduct();
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                }
            }
        });


        panel.add(refresh);
        panel.add(contentPanel);
        return panel;
    }

    public void makeProduct() throws RemoteException {

        contentPanel.removeAll();


        for (Product product: clientWindowController.visualizarProdutosEstoque()) {
            Panel panel = new Panel();
            GridLayout staticPanelGridLayout = new GridLayout(1, 4);
            panel.setLayout(staticPanelGridLayout);



            JLabel nome = new JLabel(product.getName());
            panel.add(nome);
            JLabel descicao = new JLabel(product.getDescription());
            panel.add(descicao);
            JLabel preco = new JLabel(String.valueOf(product.getPrice())+" Reais.");
            panel.add(preco);
            JLabel quantidadeEstoque = new JLabel(String.valueOf(product.getInventoryQty()));
            panel.add(quantidadeEstoque);

            JButton comprar = new JButton("Comprar");

            comprar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        clientWindowController.efetuarVenda(product.getName(), 1);
                        makeProduct();
                    } catch (RemoteException ex) {
                        ex.printStackTrace();
                    }
                }
            });

            panel.add(comprar);

            contentPanel.add(panel);
            SwingUtilities.updateComponentTreeUI(frame);

        }




    }


}
