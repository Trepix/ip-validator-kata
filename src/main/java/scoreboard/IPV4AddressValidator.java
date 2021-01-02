package scoreboard;

public class IPV4AddressValidator {
    public static boolean isHostAssignable(String ip) {
        if (isNetworkAddress(ip)) return false;
        if ("255.255.255.255".equals(ip) || "1.1.1.255".equals(ip)) return false;
        return true;
    }

    private static boolean isNetworkAddress(String ip) {
        return ip.endsWith(".0");
    }
}
