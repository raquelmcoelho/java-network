package chat_server;

import java.rmi.*;
import java.rmi.server.*;
import java.util.Calendar;

public class TimeServer extends UnicastRemoteObject implements InterfaceTimeServer {
    public TimeServer() throws RemoteException {
        super();
    }

    @Override
    public String getTime() throws RemoteException {
        Calendar cal = Calendar.getInstance();
        return cal.getTime().toString();
    }
}
