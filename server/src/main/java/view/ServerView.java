package view;

import controller.ServerController;
import model.ProductItem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

public class ServerView extends JFrame{
    private JPanel mainPanel;
    private JLabel lbNome;
    private JTextField tfName;
    private JTextField tfDescription;
    private JTextField tfPrice;
    private JTextField tfQty;
    private JTextField tfId;
    private JLabel lbDescription;
    private JLabel lbQty;
    private JButton btSave;
    private JButton btRemove;
    private JButton btEditar;
    private JList lstProduct;

    ServerController serverController = new ServerController(this);

    public ServerView() {

        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pack();
        setVisible(true);

        btSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProductItem productItem = null;
                try {
                    if(tfPrice.getText().isBlank() || tfQty.getText().isBlank()) {
                        JOptionPane.showMessageDialog(null,"Dado numérico incorreto");
                        return;
                    }
                    if(Double.parseDouble(tfPrice.getText()) <=0 || Integer.parseInt(tfQty.getText()) <=0) {
                        JOptionPane.showMessageDialog(null,"Dado numérico incorreto");
                        return;
                    }
                    productItem = new ProductItem(tfName.getText(),tfDescription.getText(),Double.parseDouble(tfPrice.getText()),Integer.parseInt(tfQty.getText()));
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null,ex.getMessage());
                }
                serverController.insertProduct(productItem);
            }
        });
    }

    public boolean setProductsList(ListModel<ProductItem> listModel){
        lstProduct.setModel(listModel);

        return true;
    }
}
