package controller;

import model.ProductModel;
import remote.Product;
import view.SaleView;


import javax.swing.*;
import java.rmi.ConnectException;
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
    private Product selectedProduct = null;

    public SaleController() {
    }

    public SaleController(SaleView saleView) {
        this.saleView = saleView;
    }

    /**
     * Update list of products and list model, refreshing the view also
     */
    public void updateList(){
        try {
            this.registry = LocateRegistry.getRegistry(configController.getServerHost(), configController.getServerPort());
            productModelList.clear();
            DefaultListModel<ProductModel> listModel = new DefaultListModel<>();
            for (String s : registry.list()) {

                Product productItem = (Product) registry.lookup(s);
                ProductModel productModel = new ProductModel(productItem.getId(),productItem.getName(),productItem.getDescription(),productItem.getPrice(),productItem.getInventoryQty());
                productModelList.add(productModel);
                listModel.addElement(productModel);
            }

            saleView.setProductsList(listModel);

        } catch (ConnectException connectException){
            JOptionPane.showMessageDialog(null,"Erro ao se comunicar com server!","Erro - Conexão", JOptionPane.ERROR_MESSAGE);
        } catch (RemoteException e) {
            JOptionPane.showMessageDialog(null,e,"Erro!",JOptionPane.ERROR_MESSAGE);
        } catch (NotBoundException e) {
            JOptionPane.showMessageDialog(null,e,"Erro!",JOptionPane.ERROR_MESSAGE);
        }
    }

    public void filterList(String filter){
        DefaultListModel<ProductModel> listModel = new DefaultListModel<>();
        for (ProductModel productModel : productModelList) {
            if (productModel.getName().contains(filter) || productModel.getDescription().contains(filter))
                listModel.addElement(productModel);
        }
        saleView.setProductsList(listModel);

    }

    /**
     * Set the remote object
     * @param productModel
     * @return
     */
    public boolean updateSelectedProduct(ProductModel productModel){
        try {
            selectedProduct = (Product) registry.lookup(String.valueOf(productModel.getCode()));
        } catch (NotBoundException e) {
            JOptionPane.showMessageDialog(null,"Produto não existe no Estoque. Pode ter sido retirado!");
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
        if( selectedProduct == null)
            return false;

        return true;

    }

    /**
     * Sell the list's selected item
     * @return
     */
    public boolean sellSelectedItem(){
        boolean result = false;

        try {
            if (selectedProduct != null) {
                result = false;
                for (String s : registry.list()) {
                   if (s.equals(selectedProduct.getId())) {
                       result = selectedProduct.reduceQty();
                       break;
                   }
                }

                selectedProduct = null; //reset
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return result;
    }
}
