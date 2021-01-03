package scoreboard;

import java.util.stream.Stream;

public class IPV4AddressValidator {

    public static boolean isHostAssignable(String ip) {
        if (haveNotFourOctets(ip)) return false;
        if (haveInvalidOctets(ip)) return false;
        if (isNetworkAddress(ip)) return false;
        if (isBroadcastAddress(ip)) return false;
        return true;
    }

    private static boolean haveNotFourOctets(String ip) {
        String[] octets = getOctets(ip);
        return octets.length != 4;
    }

    private static String[] getOctets(String ip) {
        return ip.split("\\.");
    }

    private static boolean haveInvalidOctets(String ip) {
        String[] octets = getOctets(ip);
        return Stream.of(octets).anyMatch(Octet::isNotValid);
    }

    private static boolean isBroadcastAddress(String ip) {
        return ip.endsWith(".255");
    }

    private static boolean isNetworkAddress(String ip) {
        return ip.endsWith(".0");
    }

    static class Octet {
        static boolean isNotValid(String octet) {
            if (notAllAreDigits(octet)) return true;
            if (hasLeadingZeros(octet)) return true;
            return Integer.parseInt(octet) < 0 || Integer.parseInt(octet) > 255;
        }
    }

    private static boolean notAllAreDigits(String number) {
        return !number.chars().allMatch(Character::isDigit);
    }

    static boolean hasLeadingZeros(String number) {
        return Integer.parseInt(number) != 0 && number.startsWith("0");
    }
}
