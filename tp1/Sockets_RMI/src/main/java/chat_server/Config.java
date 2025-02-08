package chat_server;
import java.net.*;

public class Config {
    public static String IP_SERVER = null;
    public static final int PORT = 1099;
    public static final String LOCAL_IP = getLocalIP();
    public static final String RMI_URL_BASE = "rmi://";
    public static final String RMI_CLIENT = RMI_URL_BASE + LOCAL_IP + ":" + PORT +  "/";

    private static String getLocalIP() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (Exception e) {
            System.err.println("Could not get local IP address.");
            return "127.0.0.1"; // Fallback para localhost
        }
    }

    private static String getRMIServer() {
        return RMI_URL_BASE + IP_SERVER + ":" + PORT +  "/";
    }

    public static String getChatServer() {
        return getRMIServer() + "ChatServer";
    }

    public static String getTimeServer() {
        return getRMIServer() + "TimeServer";
    }
}
