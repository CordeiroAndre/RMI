package client;

import javax.swing.*;
import java.awt.*;

public class ClientWindow {


    public ClientWindow() {
        JFrame frame = new JFrame("FrameDemo");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        GridLayout gridLayout = new GridLayout(2, 2);
        JButton jButton = new JButton("Visualizar produtos em estoque");

        frame.setLayout(gridLayout);
        frame.getContentPane().add(jButton, BorderLayout.CENTER);

        frame.pack();
        frame.setVisible(true);
    }
}
