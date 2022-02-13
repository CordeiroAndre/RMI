package server.view;

import server.controller.ServerWindowController;
import server.model.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

public class ServerWindow {

    JFrame frame;
    JPanel contentPanel;
    ServerWindowController serverWindowController;

    public ServerWindow() {

        serverWindowController = new ServerWindowController();
        frame = new JFrame("Server");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        contentPanel = new JPanel();
        BoxLayout boxlayoutY = new BoxLayout(contentPanel, BoxLayout.Y_AXIS);
        contentPanel.setLayout(boxlayoutY);

        //create the refresh button
        JPanel panel = createStaticComponents();

        panel.setLayout(new GridLayout(2, 1));
        frame.getContentPane().add(panel, BorderLayout.CENTER);


        frame.pack();
        frame.setVisible(true);
    }

    private JPanel createStaticComponents() {

        JPanel panel = new JPanel();
        JButton refresh = new JButton("Refresh");

        JButton adicionarProduto = new JButton("adicionar novo produto");

        adicionarProduto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });


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
        panel.add(adicionarProduto);
        panel.add(contentPanel);

        return panel;
    }


    private void makeAddProduct(){

    }

    private void makeProduct() throws RemoteException {
        contentPanel.removeAll();


        for (Product product : serverWindowController.getProductItemList().values()) {
            Panel panel = new Panel();
            GridLayout staticPanelGridLayout = new GridLayout(1, 4);
            panel.setLayout(staticPanelGridLayout);


            JLabel nome = new JLabel(product.getName());
            panel.add(nome);
            JLabel descicao = new JLabel(product.getDescription());
            panel.add(descicao);
            JLabel preco = new JLabel(String.valueOf(product.getPrice()) + " Reais.");
            panel.add(preco);
            JLabel quantidadeEstoque = new JLabel(String.valueOf(product.getInventoryQty()));
            panel.add(quantidadeEstoque);

            JButton AdicionarEstoque = new JButton("Adicionar");
            JSpinner quantity = new JSpinner();
            quantity.setModel(new SpinnerNumberModel(0, 0, 100, 1));
            quantity.setEditor(new JSpinner.NumberEditor(quantity, "##.#"));
            panel.add(quantity);

            AdicionarEstoque.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        serverWindowController.addMoreUnitsToProduct(product.getName(), (int) quantity.getValue());
                        makeProduct();
                    } catch (RemoteException ex) {
                        ex.printStackTrace();
                    }
                }
            });

            panel.add(AdicionarEstoque);

            contentPanel.add(panel);
            SwingUtilities.updateComponentTreeUI(frame);
        }
    }
}