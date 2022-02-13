package view;

import controller.SaleController;
import model.ProductModel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaleView  extends JPanel{
    private JButton btSaveHost;
    private JButton btDefaultHost;
    private JPanel salePanel;
    private JTextField tfFilter;
    private JButton btReset;
    private JButton btFilter;
    private JPanel listPanel;
    private JLabel lbList;
    private JLabel lbFilter;
    private JPanel actionPanel;
    private JButton btSell;
    private JButton btCancel;
    private JButton btUpdateList;
    private JList lsItems;

    SaleController saleController = new SaleController(this);

    public SaleView() {
        btUpdateList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lsItems.clearSelection();
                saleController.updateList();
            }
        });
        lsItems.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                boolean isSet = saleController.updateSelectedProduct((ProductModel) lsItems.getSelectedValue());
                if (isSet)
                    btSell.setEnabled(true);
                else
                    btSell.setEnabled(false);
            }
        });
        btSell.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (saleController.sellSelectedItem())
                    JOptionPane.showMessageDialog(null,"Produto Vendido!");
                else
                    JOptionPane.showMessageDialog(null, "Falha ao vender produto");
            }
        });
    }

    public JButton getBtSaveHost() {
        return btSaveHost;
    }

    public JButton getBtDefaultHost() {
        return btDefaultHost;
    }

    public JPanel getSalePanel() {
        return salePanel;
    }

    public JTextField getTfFilter() {
        return tfFilter;
    }

    public JButton getBtReset() {
        return btReset;
    }

    public JButton getBtFilter() {
        return btFilter;
    }

    public JPanel getListPanel() {
        return listPanel;
    }

    public JLabel getLbList() {
        return lbList;
    }

    public JLabel getLbFilter() {
        return lbFilter;
    }

    public JPanel getActionPanel() {
        return actionPanel;
    }

    public JButton getBtSell() {
        return btSell;
    }

    public JButton getBtCancel() {
        return btCancel;
    }

    public JButton getBtUpdateList() {
        return btUpdateList;
    }

    public JList getLsItems() {
        return lsItems;
    }

    public boolean setProductsList(ListModel<ProductModel> listModel){
        lsItems.setModel(listModel);

        return true;
    }

    private void sellSelected(){
        boolean isSold = saleController.sellSelectedItem();
        if (isSold)
            JOptionPane.showMessageDialog(null, "Produto Vendido");
        else
            JOptionPane.showMessageDialog(null, "Falha ao operar a venda");
        saleController.updateList();

    }

}
