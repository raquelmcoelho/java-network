package chat_server;
import java.net.*;
import java.util.Enumeration;

public class Config {
    public static String IP_SERVER = null;
    public static final int PORT = 1099;
    public static final String LOCAL_IP = getLocalIP();
    public static final String RMI_URL_BASE = "rmi://";
    public static final String RMI_CLIENT = RMI_URL_BASE + LOCAL_IP + ":" + PORT +  "/";

    private static String getLocalIP() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface netIf = networkInterfaces.nextElement();
                Enumeration<InetAddress> addresses = netIf.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress addr = addresses.nextElement();
                    if (!addr.isLoopbackAddress() && addr instanceof Inet4Address) {
                        return addr.getHostAddress();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "127.0.0.1"; // Fallback
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
