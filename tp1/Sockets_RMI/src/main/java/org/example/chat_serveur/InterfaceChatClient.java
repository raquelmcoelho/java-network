package org.example.chat_serveur;
import java.rmi.* ;

public interface InterfaceChatClient extends Remote
{
    /**
     *
     * @param m Message recu de la part du serveur
     * @throws RemoteException
     */
    public void diffuseMessage (Message m) throws RemoteException ;
}