package chat_server;
import java.net.*;

public class Config {
    public static final int PORT = 1099;
    public static final String IP_SERVER = "10.215.13.121";
    public static final String LOCAL_IP = getLocalIP();
    public static final String RMI_URL_BASE = "rmi://";
    public static final String RMI_SERVER = RMI_URL_BASE + IP_SERVER + ":" + PORT +  "/";
    public static final String RMI_CLIENT = RMI_URL_BASE + LOCAL_IP + ":" + PORT +  "/";
    public static final String CHAT_SERVER = RMI_SERVER + "ChatServer";
    public static final String TIME_SERVER = RMI_SERVER + "TimeServer";

    private static String getLocalIP() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (Exception e) {
            System.err.println("Could not get local IP address.");
            return "127.0.0.1"; // Fallback para localhost
        }
    }
}
