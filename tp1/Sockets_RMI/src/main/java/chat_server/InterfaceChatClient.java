package chat_server;
import java.rmi.* ;

public interface InterfaceChatClient extends Remote
{
    /**
     *
     * @param m Message received from server
     * @throws RemoteException
     */
    public void diffuseMessage (Message m) throws RemoteException ;
}