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
        String[] octets = getOctets(ip);
        return isInvalidValidOctet(octets[0]) || isInvalidValidOctet(octets[1]) || isInvalidValidOctet(octets[2]) || isInvalidValidOctet(octets[3]);
    }

    private static boolean isInvalidValidOctet(String octet) {
        return Integer.parseInt(octet) > 255;
    }

    private static boolean isBroadcastAddress(String ip) {
        return ip.endsWith(".255");
    }

    private static boolean isNetworkAddress(String ip) {
        return ip.endsWith(".0");
    }
}
