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
            LocateRegistry.createRegistry(1099);
            TimeServer time_server = new TimeServer();
            ChatServer chat_server = new ChatServer();
            Naming.rebind("rmi://localhost/ChatServer", chat_server);
            Naming.rebind("rmi://localhost/TimeServer", time_server);
            System.out.println("Chat and Time Server Online...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
