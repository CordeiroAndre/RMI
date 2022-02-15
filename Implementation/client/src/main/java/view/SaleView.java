package view;

import controller.SaleController;
import model.ProductModel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;

public class SaleView  extends JPanel{
    private JPanel salePanel;
    private JTextField tfFilter;
    private JButton btReset;
    private JButton btFilter;
    private JPanel listPanel;
    private JLabel lbList;
    private JLabel lbFilter;
    private JPanel actionPanel;
    private JButton btSell;
    private JButton btUpdateList;
    private JList lsItems;

    SaleController saleController;

    public SaleView() {
        saleController = new SaleController(this);
        applyButtonsStyle(btUpdateList,btFilter,btReset,btSell);

        lsItems.clearSelection();

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
                if(lsItems.isSelectionEmpty())
                    return;
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
                sellSelected();
                lsItems.clearSelection();
                tfFilter.setText("");
                btFilter.setEnabled(false);
                btReset.setEnabled(false);
            }
        });
        tfFilter.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                btFilter.setEnabled(true);
                if (tfFilter.getText().isBlank())
                    btFilter.setEnabled(false);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                btFilter.setEnabled(true);
                if (tfFilter.getText().isBlank())
                    btFilter.setEnabled(false);

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        btReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfFilter.setText("");
                btFilter.setEnabled(false);
                btReset.setEnabled(false);
                saleController.updateList();
            }
        });
        btFilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!tfFilter.getText().isBlank()) {
                    saleController.filterList(tfFilter.getText());
                    btReset.setEnabled(true);
                }
            }
        });
    }

    public JPanel getSalePanel() {
        return salePanel;
    }

    public boolean setProductsList(ListModel<ProductModel> listModel){
        lsItems.clearSelection();
        lsItems.setModel(listModel);

        return true;
    }

    /**
     * Try to sell the product and check the result
     */
    private void sellSelected(){
        if (saleController.sellSelectedItem())
            JOptionPane.showMessageDialog(null, "Produto Vendido");
        else
            JOptionPane.showMessageDialog(null, "Falha ao operar a venda");
        saleController.updateList();
        lsItems.clearSelection();
        btSell.setEnabled(false);
    }

    /**
     * apply buttons style
     * @param buttons
     */
    private void applyButtonsStyle(JButton... buttons){
        for (JButton button : buttons) {
            button.setMargin(new Insets(4,4,4,4));
            button.setBackground(new Color(28,43,50));
            button.setForeground(Color.WHITE);
            button.setSelected(false);
            button.setFocusPainted(false);
            button.setOpaque(true);
        }

    }

}
