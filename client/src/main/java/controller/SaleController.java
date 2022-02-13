package controller;

import model.ProductModel;
import remote.Product;
import view.SaleView;


import javax.swing.*;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;

public class SaleController {
    private static List<ProductModel> productModelList =  new ArrayList<>();

    private ConfigController configController = new ConfigController();
    private SaleView saleView;
    private Registry registry;
    private Product selectedProduct;

    public SaleController() {
    }

    public SaleController(SaleView saleView) {
        this.saleView = saleView;
    }

    public void updateList(){
        try {
            this.registry = LocateRegistry.getRegistry(configController.getServerHost(), configController.getServerPort());
            productModelList.clear();
            DefaultListModel<ProductModel> listModel = new DefaultListModel<>();
            for (String s : registry.list()) {

                System.out.println(s);

                // be aware if in the same project need to use the same class,
                // if we split the client and server we need to change de class reference but both must have same path
                Product productItem = (Product) registry.lookup(s);
                ProductModel productModel = new ProductModel(productItem.getId(),productItem.getName(),productItem.getDescription(),productItem.getPrice(),productItem.getInventoryQty());
                System.out.println(productModel.toString());
                productModelList.add(productModel);
                listModel.addElement(productModel);
//                while (productItem.getInventoryQty() > 0) {
//                    boolean estaVendido = productItem.reduceQty();
//
//                    if (estaVendido)  System.out.println("computador vendido com exito!");
//                    else System.out.println("nao ha mais computadores no estoque");
//                }
            }

            saleView.setProductsList(listModel);

        } catch (RemoteException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,e,"Erro!",JOptionPane.ERROR_MESSAGE);
        } catch (NotBoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,e,"Erro!",JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean updateSelectedProduct(ProductModel productModel){

        selectedProduct = null;
        try {
            selectedProduct = (Product) registry.lookup(String.valueOf(productModel.getCode()));
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        } catch (NotBoundException e) {
            e.printStackTrace();
            return false;
        }
        if( selectedProduct == null)
            return false;

        return true;

    }

    public boolean sellSelectedItem(){
        boolean result = false;

        try {
            if (selectedProduct != null)
                result  = selectedProduct.reduceQty();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return result;
    }
}
