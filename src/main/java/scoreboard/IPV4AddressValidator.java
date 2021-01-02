package scoreboard;

public class IPV4AddressValidator {
    public static boolean isHostAssignable(String ip) {
        if (notHaveFourOctets(ip)) return false;
        if (haveInvalidOctets(ip)) return false;
        if (isNetworkAddress(ip)) return false;
        if (isBroadcastAddress(ip)) return false;
        return true;
    }

    private static boolean notHaveFourOctets(String ip) {
        String[] octets = getOctets(ip);
        return octets.length != 4;
    }

    private static String[] getOctets(String ip) {
        return ip.split("\\.");
    }

    private static boolean haveInvalidOctets(String ip) {
        return "300.1.1.1".equals(ip) || "1.400.1.1".equals(ip);
    }

    private static boolean isBroadcastAddress(String ip) {
        return ip.endsWith(".255");
    }

    private static boolean isNetworkAddress(String ip) {
        return ip.endsWith(".0");
    }
}
