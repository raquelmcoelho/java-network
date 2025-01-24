package org.example;
import org.example.Client;
import org.example.Server;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            Server.main(args);
            Client.main(args);
        } catch (IOException exception) {
            System.out.println("Error" + exception.getMessage());
        }

        System.out.println("Testing");
    }
}