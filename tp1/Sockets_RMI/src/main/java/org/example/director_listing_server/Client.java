package org.example.director_listing_server;
import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        String serverAddress = "127.0.0.1";
        int port = 12345;

        try (
                Socket socket = new Socket(serverAddress, port);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader console = new BufferedReader(new InputStreamReader(System.in))
        ) {
            System.out.print("Name the directory to be listed: ");
            String directory = console.readLine();
            out.println(directory);

            int fileCount = Integer.parseInt(in.readLine());
            if (fileCount == -1) {
                System.out.println("Error accessing files.");
            } else {
                System.out.println("Files founded: " + fileCount);
                for (int i = 0; i < fileCount; i++) {
                    System.out.println(in.readLine());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
