package org.example;

import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) throws IOException {
        final int port = 9092;
        ServerSocket serversock = null;
        try {
            serversock = new ServerSocket(port);
        }
        catch(IOException ioe) {
            System.out.println("Error on the server " + ioe);
            System.exit(1);
        }
        Socket service = null;
        System.out.println("Listening for connection on port: " + port);
        try {
            service = serversock.accept();
        }
        catch(IOException ioe) {
            System.out.println("Accept connection failed !");
            System.exit(1);
        }

        System.out.println("Connection successful");
        System.out.println("Waiting for data ...");
        BufferedReader input = new BufferedReader(new InputStreamReader(service.getInputStream()));
        PrintWriter output = new PrintWriter((service.getOutputStream()),true);
        do {
            String inputLine = input.readLine();
            if(inputLine.isEmpty()) {
                break;
            }
            System.out.println(" - Client: "+ inputLine);
            output.println(inputLine + " - " + inputLine);
        } while(true);
        output.println("Connection terminated - Bye");
        output.close();
        input.close();
        service.close();
        serversock.close();
    }
}

