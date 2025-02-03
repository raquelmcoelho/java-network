package org.example.chat_serveur;
import java.rmi.* ;

public interface InterfaceHeureServeur extends Remote
{
    public String getHeure () throws RemoteException;
}