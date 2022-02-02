package server;

import java.rmi.*;


public class ProdutoImplementacao implements Produto {
    // Define attributes and implement all the methods defined in product interface.

    // Define attributes.
    private String name;
    private String description;
    private double price;
    private int quantidadeEstoque;

    // Parametrized constructor.
    public ProdutoImplementacao(String newName, String newDescription, double newPrice) throws RemoteException {
        this.name  = newName;
        this.description = newDescription;
        this.price = newPrice;
    }

    public void adicionaEstoque(int quantidade){
        quantidadeEstoque+=quantidade;
    }

    @Override
    public String getNome() throws RemoteException {
        return null;
    }

    @Override
    public String getDescricao() throws RemoteException {
        return null;
    }

    @Override
    public double getPreco() throws RemoteException {
        return 0;
    }

    @Override
    public int getQuantidadeEstoque() throws RemoteException {
        return quantidadeEstoque;
    }

    @Override
    public boolean vender() {
       if(quantidadeEstoque == 0) return false;
       quantidadeEstoque--;
       return true;
    }
}