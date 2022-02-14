package view;

import controller.ServerController;
import model.ProductItem;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
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
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JPanel menuPanel;
    private JButton btnSales;
    private JButton btnConfig;
    private JPanel titlePanel;
    private JPanel rightConfigPanel;
    private ConfigView hostPanel;
    private JButton btAddQty;
    private JTextField tfAddQty;
    private JButton btUpdate;

    ServerController serverController = new ServerController(this);

    public ServerView() {

        applyButtonsStyle(btEditar,btnConfig,btnSales,btSave,btRemove,btAddQty,btUpdate);
        setTitle("Servidor - RMI");
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        btEditar.setEnabled(false);
        btAddQty.setEnabled(false);
        tfAddQty.setEnabled(false);
        btRemove.setEnabled(false);

        leftPanel.setVisible(true);
        rightPanel.setVisible(true);
        rightConfigPanel.setVisible(false);


        pack();
        setVisible(true);

        btSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProductItem productItem = null;
                try {
                    if(tfPrice.getText().isBlank() || tfQty.getText().isBlank()) {
                        JOptionPane.showMessageDialog(null,"Dado numérico em branco");
                        return;
                    }
                    if(Double.parseDouble(tfPrice.getText()) <=0 || Integer.parseInt(tfQty.getText()) <=0) {
                        JOptionPane.showMessageDialog(null,"Dado numérico inválido");
                        return;
                    }

                } catch (NumberFormatException numberFormatException){
                    JOptionPane.showMessageDialog(null,"Dado numérico incorreto");
                }

                boolean isValid = false;
                //edit
                if (!tfId.getText().isBlank()) {
                    try {
                        productItem = new ProductItem(tfId.getText(),tfName.getText(),tfDescription.getText(),Double.parseDouble(tfPrice.getText()),Integer.parseInt(tfQty.getText()));
                        isValid = serverController.editProduct(productItem);
                    } catch (RemoteException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null,ex.getMessage());
                        isValid = false;
                    }
                }
                else   // normal insert
                {
                    try {
                        productItem = new ProductItem(tfName.getText(),tfDescription.getText(),Double.parseDouble(tfPrice.getText()),Integer.parseInt(tfQty.getText()));
                        isValid = serverController.insertProduct(productItem);
                    } catch (RemoteException ex) {
                        ex.printStackTrace();
                        isValid = false;
                    }
                }

                if (isValid){
                    tfName.setText("");
                    tfDescription.setText("");
                    tfPrice.setText("");
                    tfQty.setText("");
                }
                tfId.setText("");
                lstProduct.clearSelection();
                btEditar.setEnabled(false);
                btAddQty.setEnabled(false);
                tfAddQty.setEnabled(false);
                btRemove.setEnabled(false);
                btSave.setText("Salvar");

            }
        });
        btnConfig.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rightPanel.setVisible(false);
                hostPanel.getHostPanel().setVisible(true);
                rightConfigPanel.setVisible(true);
            }
        });
        btnSales.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rightConfigPanel.setVisible(false);
                rightPanel.setVisible(true);
            }
        });
        lstProduct.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (lstProduct == null)
                    return;
                if (lstProduct.isSelectionEmpty())
                    return;
                btEditar.setEnabled(true);
                btAddQty.setEnabled(true);
                tfAddQty.setEnabled(true);
                btRemove.setEnabled(true);
            }
        });
        btEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                serverController.getProductInfo((ProductItem)lstProduct.getSelectedValue());
                btSave.setText("Salvar Edição");
            }
        });
        btAddQty.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (tfAddQty.getText().isBlank()) {
                    JOptionPane.showMessageDialog(null, "Dado numérico incorreto");
                }
                else if (Integer.parseInt(tfAddQty.getText()) <= 0) {
                    JOptionPane.showMessageDialog(null, "Dado numérico incorreto");
                }
                if(!serverController.addQty((ProductItem) lstProduct.getSelectedValue(), Integer.parseInt(tfAddQty.getText()))) {
                    JOptionPane.showMessageDialog(null, "Falha ao adicionar mais itens ao estoque do produto");
                    return;
                }
                tfAddQty.setText("");
            }
        });
        btRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isRemoved = serverController.removeProduct(((ProductItem) lstProduct.getSelectedValue()));
                if (isRemoved)
                    JOptionPane.showMessageDialog(null,"Objeto removido do Estoque");
            }
        });
        btUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                serverController.updateList();
            }
        });
    }

    public boolean setProductsList(ListModel<ProductItem> listModel){
        lstProduct.setModel(listModel);

        return true;
    }

    private void applyButtonsStyle(JButton... buttons){
        for (JButton button : buttons) {

            if (button.getIcon()!=null) {
                button.setHorizontalAlignment(SwingConstants.LEFT);
                button.setHorizontalTextPosition(SwingConstants.TRAILING);
                button.setIconTextGap(20);
            }

            button.setMargin(new Insets(4,4,4,4));
            button.setBackground(Color.DARK_GRAY);
            button.setForeground(Color.WHITE);
            button.setSelected(false);
            button.setFocusPainted(false);
            button.setOpaque(true);
        }

    }

    public void setProductFields(String id, String name, String description, String price, String qty){

        tfId.setText(id);
        tfName.setText(name);
        tfDescription.setText(description);
        tfPrice.setText(price);
        tfQty.setText(qty);

    }

}
