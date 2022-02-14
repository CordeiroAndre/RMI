package model;

import remote.Product;

import java.rmi.*;


public class ProductItem implements Product {
    static int counter;

    // Define attributes.
    private String name;
    private String description;
    private double price;
    private int quantidadeEstoque;
    private String id;


    public ProductItem(String newName, String newDescription, double newPrice, int qty) throws RemoteException {
        counter++;
        this.id = String.valueOf(counter);
        this.name  = newName;
        this.description = newDescription;
        this.price = newPrice;
        this.quantidadeEstoque = qty;
    }
    public ProductItem(String id, String newName, String newDescription, double newPrice, int qty) throws RemoteException {
        this.id = id;
        this.name  = newName;
        this.description = newDescription;
        this.price = newPrice;
        this.quantidadeEstoque = qty;
    }

    public synchronized void adicionaEstoque(int quantidade){
        quantidadeEstoque+=quantidade;
    }

    @Override
    public String getId() throws RemoteException {
        return this.id;
    }

    @Override
    public String getName() throws RemoteException {
        return name;
    }

    @Override
    public String getDescription() throws RemoteException {
        return description;
    }

    @Override
    public double getPrice() throws RemoteException {
        return price;
    }

    @Override
    public int getInventoryQty() throws RemoteException {
        return quantidadeEstoque;
    }

    @Override
    public synchronized boolean reduceQty() {
        if(quantidadeEstoque == 0) return false;
        quantidadeEstoque--;
        System.out.println(id +" - "+ name +" - "+  "foi vendido");
        return true;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    @Override
    public String toString() {
        return "Código:" + id +
                ", Nome: " + name +
                ", Descrição: " + description +
                ", Preço: R$" + price +
                ", Em estoque: " + quantidadeEstoque ;
    }
}