package org.example.chat_serveur;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.*;
import java.util.*;

public class ChatServeur extends UnicastRemoteObject implements InterfaceChatServeur {
    private Map<String, InterfaceChatClient> clients = new HashMap<>();

    public ChatServeur() throws RemoteException {
        super();
    }

    @Override
    public synchronized void connect(String pseudo, String url) throws RemoteException {
        try {
            InterfaceChatClient client = (InterfaceChatClient) Naming.lookup(url);
            clients.put(pseudo, client);
            System.out.println(pseudo + " connected.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void disconnect(String pseudo) throws RemoteException {
        clients.remove(pseudo);
        System.out.println(pseudo + " disconnected.");
    }

    @Override
    public synchronized void broadcastMessage(Message msg) throws RemoteException {
        for (InterfaceChatClient client : clients.values()) {
            client.diffuseMessage(msg);
        }
    }

    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099);
            HeureServeur heure_server = new HeureServeur();
            ChatServeur chat_server = new ChatServeur();
            Naming.rebind("rmi://localhost/ChatServeur", chat_server);
            Naming.rebind("rmi://localhost/HeureServeur", heure_server);
            System.out.println("Chat and Time Server Online...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
