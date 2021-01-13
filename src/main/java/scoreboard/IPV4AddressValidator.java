package scoreboard;

import java.util.stream.Stream;

public class IPV4AddressValidator {

    public static boolean isAssignableHost(String ip) {
        if (hasNotFourOctets(ip)) return false;
        if (hasInvalidOctets(ip)) return false;
        if (isNetworkAddress(ip)) return false;
        if (isBroadcastAddress(ip)) return false;
        return true;
    }

    private static boolean hasNotFourOctets(String ip) {
        String[] octets = getOctets(ip);
        return octets.length != 4;
    }

    private static String[] getOctets(String ip) {
        return ip.split("\\.");
    }

    private static boolean hasInvalidOctets(String ip) {
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
            if (notOnlyContainDigits(octet)) return true;
            if (hasLeadingZeros(octet)) return true;
            return Integer.parseInt(octet) < 0 || Integer.parseInt(octet) > 255;
        }
    }

    private static boolean notOnlyContainDigits(String number) {
        return !number.chars().allMatch(Character::isDigit);
    }

    static boolean hasLeadingZeros(String number) {
        return Integer.parseInt(number) != 0 && number.startsWith("0");
    }
}
