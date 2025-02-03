package chat_server;
import java.rmi.*;
import java.rmi.server.*;
import java.util.Scanner;

public class ChatClient extends UnicastRemoteObject implements InterfaceChatClient {
    private String pseudo;
    private InterfaceChatServer server;
    public ChatClientGUI gui;

    public ChatClient(String pseudo, InterfaceChatServer server) throws RemoteException {
        this.pseudo = pseudo;
        this.server = server;
        this.gui = new ChatClientGUI();
    }

    public String getPseudo() {
        return pseudo;
    }

    @Override
    public void diffuseMessage(Message m) throws RemoteException {
        System.out.println(m.getPseudo() + ": " + m.getMessage());
        gui.receiveMessage(m);
    }

    public void sendMessage(String message) throws RemoteException {
        server.broadcastMessage(new Message(pseudo, message));
    }

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Insert your pseudo: ");
            String pseudo = scanner.nextLine();

            InterfaceChatServer server = (InterfaceChatServer) Naming.lookup("rmi://localhost/ChatServer");
            ChatClient client = new ChatClient(pseudo, server);
            Naming.rebind("rmi://localhost/" + pseudo, client);
            server.connect(pseudo, "rmi://localhost/" + pseudo);

            client.gui.launchUI(client);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

