package org.example.chat_serveur;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.*;
import java.util.Scanner;

public class ChatClient extends UnicastRemoteObject implements InterfaceChatClient {
    private String pseudo;
    private InterfaceChatServeur serveur;
    public ChatClientGUI gui;

    public ChatClient(String pseudo, InterfaceChatServeur serveur) throws RemoteException {
        this.pseudo = pseudo;
        this.serveur = serveur;
        this.gui = new ChatClientGUI();
    }

    @Override
    public void diffuseMessage(Message m) throws RemoteException {
        System.out.println(m.getPseudo() + ": " + m.getMessage());
        gui.receiveMessage(m.getPseudo(), m.getMessage());
    }

    public void sendMessage(String message) throws RemoteException {
        serveur.broadcastMessage(new Message(pseudo, message));
    }

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Insert your pseudo: ");
            String pseudo = scanner.nextLine();

            InterfaceChatServeur serveur = (InterfaceChatServeur) Naming.lookup("rmi://localhost/ChatServeur");
            ChatClient client = new ChatClient(pseudo, serveur);
            Naming.rebind("rmi://localhost/" + pseudo, client);
            serveur.connect(pseudo, "rmi://localhost/" + pseudo);

            client.gui.launchUI(client);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

