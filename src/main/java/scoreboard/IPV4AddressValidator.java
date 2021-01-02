package scoreboard;

import java.util.stream.Stream;

public class IPV4AddressValidator {

    public static boolean isHostAssignable(String ip) {
        if (notHaveFourOctets(ip)) return false;
        if (haveInvalidOctets(ip)) return false;
        if (haveLeadingZeros(ip)) return false;
        if (isNetworkAddress(ip)) return false;
        if (isBroadcastAddress(ip)) return false;
        return true;
    }

    private static boolean haveLeadingZeros(String ip) {
        String[] octets = getOctets(ip);
        return numberHasLeadingZero(octets[0]) || numberHasLeadingZero(octets[1]) || numberHasLeadingZero(octets[2]);
    }

    private static boolean numberHasLeadingZero(String number) {
        return Integer.parseInt(number) != 0 && number.startsWith("0");
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
        return Stream.of(octets).anyMatch(Octet::isValid);
    }

    private static boolean isBroadcastAddress(String ip) {
        return ip.endsWith(".255");
    }

    private static boolean isNetworkAddress(String ip) {
        return ip.endsWith(".0");
    }

    static class Octet {
        static boolean isValid(String octet) {
            return Integer.parseInt(octet) > 255;
        }
    }
}
