import view.ClientView;

import javax.swing.*;
import java.awt.*;


public class Client {
    public static void main(String[] args) {
        try {

            JFrame jFrame = new ClientView();
            jFrame.setPreferredSize(new Dimension(1000,600));
            jFrame.pack();
            jFrame.setVisible(true);

        } catch (Exception e) {
            System.out.println("execao no cliente " + e);
        }
    }
}