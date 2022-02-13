package controller;

import model.ProductItem;
import remote.Product;
import view.ServerView;

import javax.swing.*;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class ServerController {

    private List<ProductItem> productItemList = new ArrayList<>();
    private DefaultListModel<ProductItem> productItemListModel = new DefaultListModel<>();
    private ServerView serverView;
    private Registry registry;


    public ServerController(ServerView serverView) {

        // Cria do Registry
        try {
            registry = LocateRegistry.createRegistry(9000);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        this.serverView = serverView;
    }

    public boolean insertProduct(ProductItem product){
        this.productItemList.add(product);
        updateList();
        return true;
    }

    private void updateList(){



        for (ProductItem productItem : productItemList) {
            productItemListModel.addElement(productItem);

            // exportamos o objeto antes de colocar no registry
            Product stub = null;
            try {
                stub = (Product) UnicastRemoteObject.exportObject(productItem, 0);
                registry.bind(stub.getId(), stub);
            } catch (RemoteException e) {
                e.printStackTrace();
            } catch (AlreadyBoundException e) {
                e.printStackTrace();
            }

        }
        serverView.setProductsList(productItemListModel);
    }
}
