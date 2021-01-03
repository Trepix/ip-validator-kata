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

    private static boolean notAllOctetsContainDigits(String ip) {
        String[] numbers = getOctets(ip);
        return !Stream.of(numbers).allMatch(IPV4AddressValidator::areDigits);
    }

    private static boolean areDigits(String number) {
        return number.chars().allMatch(Character::isDigit);
    }

    private static boolean haveLeadingZeros(String ip) {
        String[] numbers = getOctets(ip);
        return Stream.of(numbers).anyMatch(Number::hasLeadingZeros);
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
        boolean notAllOctetsHaveDigits = notAllOctetsContainDigits(ip);
        if (notAllOctetsHaveDigits) return true;
        boolean haveLeadingZeros = haveLeadingZeros(ip);
        if (haveLeadingZeros) return true;
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
            return Integer.parseInt(octet) < 0 || Integer.parseInt(octet) > 255;
        }
    }

    static class Number {
        static boolean hasLeadingZeros(String number) {
            return Integer.parseInt(number) != 0 && number.startsWith("0");
        }

    }
}
