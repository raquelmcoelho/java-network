package director_listing_server;
import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        int port = 12345;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Waiting for clients...");

            while (true) {
                Socket socket = serverSocket.accept();
                new ClientHandler(socket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ClientHandler extends Thread {
    private Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            String directoryPath = in.readLine();
            File dir = new File(directoryPath);

            if (dir.exists() && dir.isDirectory()) {
                String[] files = dir.list();
                out.println(files.length);
                for (String file : files) {
                    out.println(file);
                }
            } else {
                out.println("-1");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
