package scoreboard;

public class IPV4AddressValidator {
    public static boolean isHostAssignable(String ip) {
        if (isNetworkAddress(ip)) return false;
        return true;
    }

    private static boolean isNetworkAddress(String ip) {
        return ip.endsWith(".0");
    }
}
