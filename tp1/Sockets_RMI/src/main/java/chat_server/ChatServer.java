package chat_server;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.*;
import java.util.*;

public class ChatServer extends UnicastRemoteObject implements InterfaceChatServer {
    private Map<String, InterfaceChatClient> clients = new HashMap<>();

    public ChatServer() throws RemoteException {
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
            LocateRegistry.createRegistry(Config.PORT);
        } catch (RemoteException e) {
            System.out.println("RMI Register is already running...");
        }

        try {
            System.setProperty("java.rmi.server.hostname", Config.IP_SERVER);
            TimeServer time_server = new TimeServer();
            ChatServer chat_server = new ChatServer();
            Naming.rebind(Config.CHAT_SERVER, chat_server);
            Naming.rebind(Config.TIME_SERVER, time_server);
            System.out.println("Chat and Time Server Online...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
