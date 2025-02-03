package chat_server;
import java.rmi.* ;

public interface InterfaceTimeServer extends Remote
{
    public String getTime () throws RemoteException;
}