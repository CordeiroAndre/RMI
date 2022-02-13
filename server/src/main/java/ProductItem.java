import remote.Product;

import java.rmi.*;


public class ProductItem implements Product {
    static int counter;
    // Define attributes and implement all the methods defined in product interface.

    // Define attributes.
    private String name;
    private String description;
    private double price;
    private int quantidadeEstoque;
    private int id;

    // Parametrized constructor.
    public ProductItem(String newName, String newDescription, double newPrice) throws RemoteException {
        counter++;
        this.id = counter;
        this.name  = newName;
        this.description = newDescription;
        this.price = newPrice;
        this.quantidadeEstoque = 0;
    }

    public void adicionaEstoque(int quantidade){
        quantidadeEstoque+=quantidade;
    }

    @Override
    public int getId() throws RemoteException {
        return this.id;
    }

    @Override
    public String getName() throws RemoteException {
        return null;
    }

    @Override
    public String getDescription() throws RemoteException {
        return null;
    }

    @Override
    public double getPrice() throws RemoteException {
        return 0;
    }

    @Override
    public int getInventoryQty() throws RemoteException {
        return quantidadeEstoque;
    }

    @Override
    public boolean reduceQty() {
        if(quantidadeEstoque == 0) return false;
        quantidadeEstoque--;
        return true;
    }
}