package server.view;

import server.controller.ServerWindowController;
import server.model.Product;
import server.model.ProductItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;


//TODO(Validar que os spinners aceitam apenas valores numéricos)
//TODO(Codigo duplicado d+)
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
                makeAddProduct();
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
        JFrame frame = new JFrame("Adicionar novo produto");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        BoxLayout boxlayoutY = new BoxLayout(panel, BoxLayout.Y_AXIS);

        panel.setLayout(boxlayoutY);

        JLabel nameLabel = new JLabel("Name: ");
        JTextField name = new JTextField();
        panel.add(nameLabel);
        panel.add(name);

        JLabel descriptionLabel = new JLabel(" Description: ");
        JTextField description = new JTextField();
        panel.add(descriptionLabel);
        panel.add(description);

        JLabel pricingLabel = new JLabel(" Price: ");
        JSpinner pricing = new JSpinner();
        pricing.setModel(new SpinnerNumberModel(0, 0, 100000, .01));
        pricing.setEditor(new JSpinner.NumberEditor(pricing));
        panel.add(pricingLabel);
        panel.add(pricing);

        JLabel quantityLabel = new JLabel(" Quantity: ");
        JSpinner quantity = new JSpinner();
        quantity.setModel(new SpinnerNumberModel(0, 0, 100, 1));
        quantity.setEditor(new JSpinner.NumberEditor(quantity));
        panel.add(quantityLabel);
        panel.add(quantity);

        JButton finalizar = new JButton("finalizar");
        finalizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    serverWindowController.registerProduct(new ProductItem(name.getText(), description.getText(), (Double) pricing.getValue()));
                    serverWindowController.addMoreUnitsToProduct(name.getText(),(int) quantity.getValue());
                    makeProduct();
                    frame.dispose();
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                }
            }
        });

        panel.add(finalizar);

        frame.getContentPane().add(panel, BorderLayout.CENTER);


        frame.pack();
        frame.setVisible(true);
    }

    private void updateProduct(ProductItem item) throws RemoteException {
        JFrame frame = new JFrame("Atualizar "+ item.getName() );
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        BoxLayout boxlayoutY = new BoxLayout(panel, BoxLayout.Y_AXIS);

        panel.setLayout(boxlayoutY);

        JLabel descriptionLabel = new JLabel(" Description: ");
        JTextField description = new JTextField();
        panel.add(descriptionLabel);
        panel.add(description);

        JLabel pricingLabel = new JLabel(" Price: ");
        JSpinner pricing = new JSpinner();
        pricing.setModel(new SpinnerNumberModel(0, 0, 100000, .01));
        pricing.setEditor(new JSpinner.NumberEditor(pricing));
        panel.add(pricingLabel);
        panel.add(pricing);

        JLabel quantityLabel = new JLabel(" Quantity: ");
        JSpinner quantity = new JSpinner();
        quantity.setModel(new SpinnerNumberModel(0, 0, 100, 1));
        quantity.setEditor(new JSpinner.NumberEditor(quantity));
        panel.add(quantityLabel);
        panel.add(quantity);

        JButton finalizar = new JButton("finalizar");
        finalizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ProductItem productItem = new ProductItem(item.getName(), description.getText(), (Double) pricing.getValue());
                    productItem.setQuantidadeEstoque((int)quantity.getValue());
                    serverWindowController.changeProduct(item.getName(), productItem);
                    makeProduct();
                    frame.dispose();
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                }
            }
        });

        panel.add(finalizar);

        frame.getContentPane().add(panel, BorderLayout.CENTER);


        frame.pack();
        frame.setVisible(true);
    }

    private void makeProduct() throws RemoteException {
        contentPanel.removeAll();


        for (Product product : serverWindowController.getProductItemList().values()) {
            Panel panel = new Panel();
            BoxLayout boxlayoutY = new BoxLayout(panel, BoxLayout.X_AXIS);
            panel.setLayout(boxlayoutY);


            JLabel nome = new JLabel("Nome: "+product.getName());
            panel.add(nome);
            JLabel descicao = new JLabel("Descricao: "+product.getDescription());
            panel.add(descicao);
            JLabel preco = new JLabel(String.valueOf("Preco: "+product.getPrice()) + " Reais.");
            panel.add(preco);
            JLabel quantidadeEstoque = new JLabel(String.valueOf("Estoque: "+product.getInventoryQty()));
            panel.add(quantidadeEstoque);

            JButton AdicionarEstoque = new JButton("Add");
            JSpinner quantity = new JSpinner();
            quantity.setModel(new SpinnerNumberModel(0, 0, 100, 1));
            quantity.setEditor(new JSpinner.NumberEditor(quantity));
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

            JButton atualizarItem = new JButton("Atualizar");
            atualizarItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        updateProduct(serverWindowController.getProductItemList().get(product.getName()));
                    } catch (RemoteException ex) {
                        ex.printStackTrace();
                    }
                }
            });

            panel.add(atualizarItem);

            contentPanel.add(panel);
            frame.pack();
            SwingUtilities.updateComponentTreeUI(frame);
        }
    }
}