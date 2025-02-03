package org.example.chat_serveur;

import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.*;
import java.util.Calendar;

public class HeureServeur extends UnicastRemoteObject implements InterfaceHeureServeur {
    public HeureServeur() throws RemoteException {
        super();
    }

    @Override
    public String getHeure() throws RemoteException {
        Calendar cal = Calendar.getInstance();
        return cal.getTime().toString();
    }
}
