package org.example.chat_serveur;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.*;
import java.util.Scanner;

public class ChatClient extends UnicastRemoteObject implements InterfaceChatClient {
    private String pseudo;
    private InterfaceChatServeur serveur;

    public ChatClient(String pseudo, InterfaceChatServeur serveur) throws RemoteException {
        this.pseudo = pseudo;
        this.serveur = serveur;
    }

    @Override
    public void diffuseMessage(Message m) throws RemoteException {
        System.out.println(m.getPseudo() + ": " + m.getMessage());
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

            System.out.println("Write your message (or 'exit' to quit):");
            while (true) {
                String msg = scanner.nextLine();
                if (msg.equalsIgnoreCase("exit")) {
                    serveur.disconnect(pseudo);
                    break;
                }
                client.sendMessage(msg);
            }

            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

