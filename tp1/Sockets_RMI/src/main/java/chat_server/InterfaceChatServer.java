package chat_server;
import java.rmi.* ;

public interface InterfaceChatServer extends Remote
{
    /** Connection at the server
     * @param pseudo            pseudo chosen
     * @param url               url of the remote client
     * @throws RemoteException
     */
    public void connect (String pseudo,String url) throws RemoteException ;

    /**
     *
     * @param pseudo            pseudo which is going to be disconnected
     * @throws RemoteException
     */
    public void disconnect (String pseudo) throws RemoteException ;

    /**
     *
     * @param msg               broadcasted message
     * @throws RemoteException
     */
    public void broadcastMessage (Message msg) throws RemoteException ;
}