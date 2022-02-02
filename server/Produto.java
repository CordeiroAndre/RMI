package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Produto extends Remote{
    // Define product behaviour (Methods)

     String getNome() throws RemoteException;
     String getDescricao() throws RemoteException;
     double getPreco() throws RemoteException;
     int getQuantidadeEstoque() throws RemoteException;
     boolean vender() throws RemoteException;
}