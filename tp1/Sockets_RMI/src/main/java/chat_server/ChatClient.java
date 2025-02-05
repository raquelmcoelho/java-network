package chat_server;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
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
            LocateRegistry.createRegistry(Config.PORT);
        } catch (RemoteException e) {
            System.out.println("RMI Register is already running...");
        }

        try {
            System.setProperty("java.rmi.server.hostname", Config.IP_SERVER);
            Scanner scanner = new Scanner(System.in);
            System.out.print("Insert your pseudo: ");
            String pseudo = scanner.nextLine();

            InterfaceChatServer server = (InterfaceChatServer) Naming.lookup(Config.CHAT_SERVER);
            ChatClient client = new ChatClient(pseudo, server);
            Naming.rebind(Config.RMI_CLIENT + pseudo, client);
            server.connect(pseudo, Config.RMI_CLIENT + pseudo);

            client.gui.launchUI(client);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

