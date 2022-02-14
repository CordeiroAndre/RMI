package view;

import controller.ConfigController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfigView extends JPanel{
    private JTextField tfHost;
    private JLabel lbHost;
    private JButton btSaveHost;
    private JButton btDefaultHost;
    private JPanel hostPanel;
    private JLabel lbPort;
    private JTextField tfPort;

    private ConfigController configController = new ConfigController();

    public ConfigView() {
        applyButtonsStyle(btSaveHost,btDefaultHost);
        tfHost.setText(configController.getServerHost());
        tfPort.setText(String.valueOf(configController.getServerPort()));
        btDefaultHost.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isDefault = configController.setDefaultHostAndPort();
                if (!isDefault){
                    JOptionPane.showMessageDialog(null,"Erro ao retornar à configuração padrão!");
                    return;
                }

                tfHost.setText(configController.getServerHost());
                tfPort.setText(String.valueOf(configController.getServerPort()));
            }
        });

        btSaveHost.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isSaved = configController.saveHostAndPort(tfHost.getText(),tfPort.getText());

                if (!isSaved){
                    JOptionPane.showMessageDialog(null,"Não foi possível salvar a alteração!");
                    return;
                }
                else
                    JOptionPane.showMessageDialog(null, "Dados salvos: " + tfHost.getText() + " : " + tfPort.getText());

            }
        });
    }

    public JPanel getHostPanel() {
        return hostPanel;
    }

    private void applyButtonsStyle(JButton... buttons){
        for (JButton button : buttons) {
            button.setMargin(new Insets(4,4,4,4));
            button.setBackground(Color.DARK_GRAY);
            button.setForeground(Color.WHITE);
            button.setSelected(false);
            button.setFocusPainted(false);
            button.setOpaque(true);
        }

    }
}
