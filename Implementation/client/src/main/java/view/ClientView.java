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


            configPanel.getHostPanel().setVisible(true);
            configPanel.setVisible(true);
            applyButtonsStyle(this.btnSales, this.btnConfig);
            setMenuStyle();
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
                setMenuStyle();
            }
        });

        this.btnSales.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                configPanel.getHostPanel().setVisible(false);
                salePanel.getSalePanel().setVisible(true);
                setMenuStyle();
            }
        });
    }


    /**
     * apply buttons style
     * @param buttons
     */
    private void applyButtonsStyle(JButton... buttons){
        for (JButton button : buttons) {
            button.setHorizontalAlignment(SwingConstants.LEFT);
            button.setHorizontalTextPosition(SwingConstants.TRAILING);
            button.setIconTextGap(20);
            button.setMargin(new Insets(4,4,4,4));
            button.setBackground(new Color(28,43,50));
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



    private void setMenuStyle(){
        if (configPanel.getHostPanel().isVisible()){
            btnSales.setBackground(new Color(28,43,50));
            btnSales.setForeground(Color.WHITE);
            btnConfig.setBackground(new Color(240,240,240));
            btnConfig.setForeground(new Color(28,43,50));
        } else if(salePanel.getSalePanel().isVisible()){
            btnSales.setBackground(new Color(240,240,240));
            btnSales.setForeground(new Color(28,43,50));
            btnConfig.setBackground(new Color(28,43,50));
            btnConfig.setForeground(Color.WHITE);
        } else {
            btnSales.setBackground(new Color(28,43,50));
            btnSales.setForeground(Color.WHITE);
            btnConfig.setBackground(new Color(28,43,50));
            btnConfig.setForeground(Color.WHITE);
        }

    }
}
