package controller;

import model.ConfigModel;
import model.ProductItem;
import remote.Product;
import view.ServerView;

import javax.swing.*;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.ExportException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class ServerController {

    private List<ProductItem> productItemList = new ArrayList<>();
    private DefaultListModel<ProductItem> productItemListModel = new DefaultListModel<>();
    private ServerView serverView;
    private static Registry registry;

    public ServerController(ServerView serverView) {

        // Cria do Registry
        try {
            registry = LocateRegistry.createRegistry(9000);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        this.serverView = serverView;
    }

    public ServerController() {
    }

    public boolean insertProduct(ProductItem product){
        this.productItemList.add(product);
        updateList(product);
        return true;
    }
    public boolean editProduct(ProductItem product){
        productItemListModel.clear();
        for (ProductItem productItem : productItemList) {
            try {
                if (product.getId().equals(productItem.getId())) {
                    productItem.setName(product.getName());
                    productItem.setQuantidadeEstoque(product.getInventoryQty());
                    productItem.setPrice(product.getPrice());
                    productItem.setDescription(product.getDescription());
//                    Product stub = (Product) UnicastRemoteObject.exportObject(product, 0);
//                    registry.rebind(productItem.getId(),stub);
                }
            } catch (RemoteException e) {
                e.printStackTrace();
                return false;
            }
            productItemListModel.addElement(productItem);
        }
        serverView.setProductsList(productItemListModel);
        return true;
    }

    private void updateList(ProductItem productItem){
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
        serverView.setProductsList(productItemListModel);
    }

    public void updateList(){
        productItemListModel.clear();

        for (ProductItem productItem : productItemList) {
            productItemListModel.addElement(productItem);
            // exportamos o objeto antes de colocar no registry
            Product stub = null;
            try {
                stub = (Product) UnicastRemoteObject.exportObject(productItem, 0);
                registry.rebind(productItem.getId(), stub);
            } catch (RemoteException e) {
            }

        }

        serverView.setProductsList(productItemListModel);
    }


    public void getProductInfo(ProductItem productItem) {

        try {
            serverView.setProductFields(
                    String.valueOf(productItem.getId()),
                    productItem.getName(),
                    productItem.getDescription(),
                    String.valueOf(productItem.getPrice()),
                    String.valueOf(productItem.getInventoryQty())
            );
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }

    public boolean addQty(ProductItem productItem, int qty) {
        try {
            int actual = productItem.getInventoryQty();
            int expected = actual + qty;
            productItem.adicionaEstoque(qty);
            if ( expected != productItem.getInventoryQty())
                return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }

        return true;

    }

    public boolean updateRemoteConfig(int port) {
        try {
            registry = LocateRegistry.createRegistry(port);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean removeProduct(ProductItem productItem) {
        try {
            registry.unbind(productItem.getId());

            productItemListModel.removeElement(productItem);
            productItemList.remove(productItem);
            serverView.setProductsList(productItemListModel);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        } catch (NotBoundException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
