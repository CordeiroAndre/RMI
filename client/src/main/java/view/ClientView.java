package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientView extends JFrame {
    private JPanel titlePanel;
    private JPanel mainPanel;
    private JButton btnSales;
    private JButton btnConfig;
    private JPanel menuPanel;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private ConfigView configPanel;
    private SaleView salePanel;

//    ConfigController configController;
//    SaleController saleController;
//    ClientController clientController;

    public ClientView() {
        super("Client - RMI Sales");
        try {
            this.applyMainStyle();
            this.applyButtonsActions();
            this.setContentPane(mainPanel);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


            this.mainPanel.setVisible(true);
            this.titlePanel.setVisible(true);

            this.menuPanel.setVisible(true);
            this.btnSales.setVisible(true);
            this.btnConfig.setVisible(true);


            salePanel.getSalePanel().setVisible(false);
            salePanel.setVisible(false);


            configPanel.getHostPanel().setVisible(false);
            configPanel.setVisible(false);

            applyButtonsStyle(this.btnSales, this.btnConfig);
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    private void applyButtonsActions() {
        this.btnConfig.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salePanel.getSalePanel().setVisible(false);
                configPanel.getHostPanel().setVisible(true);
            }
        });

        this.btnSales.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                configPanel.getHostPanel().setVisible(false);
                salePanel.getSalePanel().setVisible(true);
            }
        });
    }


    private void applyButtonsStyle(JButton... buttons){
        for (JButton button : buttons) {
            button.setHorizontalAlignment(SwingConstants.LEFT);
            button.setHorizontalTextPosition(SwingConstants.TRAILING);
            button.setIconTextGap(20);
            button.setMargin(new Insets(4,4,4,4));
            button.setBackground(Color.DARK_GRAY);
            button.setForeground(Color.WHITE);
            button.setSelected(false);
            button.setFocusPainted(false);
            button.setOpaque(true);
        }

    }


    private void applyMainStyle(){
        this.mainPanel.setBackground(Color.GRAY);
        this.mainPanel.setForeground(Color.LIGHT_GRAY);

    }


//    public void setContentPanel(JPanel component){
////        rightPanel.setBackground(Color.RED);
//        JPanel panel = configController.getConfigView();
//        panel.setSize(800,800);
//        panel.setVisible(true);
//
//        rightPanel.setBackground(Color.GREEN);
//
//        rightPanel.removeAll();
//        rightPanel.add(panel);
//        panel.validate();
//        panel.repaint();
//        rightPanel.validate();
//        rightPanel.repaint();
//        rightPanel.setVisible(true);

//    }

//    public void setControllers(ClientController clientController, ConfigController configController, SaleController saleController) {
//        this.clientController = clientController;
//        this.configController = configController;
//        this.saleController = saleController;
//    }
}
