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
        boolean isNotValidOctet = Stream.of(octets).anyMatch(Octet::isNotValid);
        if (isNotValidOctet) return true;
        return haveLeadingZeros(octets);
    }

    private static boolean areDigits(String number) {
        return number.chars().allMatch(Character::isDigit);
    }

    private static boolean haveLeadingZeros(String[] numbers) {
        return Stream.of(numbers).anyMatch(Number::hasLeadingZeros);
    }

    private static boolean isBroadcastAddress(String ip) {
        return ip.endsWith(".255");
    }

    private static boolean isNetworkAddress(String ip) {
        return ip.endsWith(".0");
    }

    static class Octet {
        static boolean isNotValid(String octet) {
            boolean octetOnlyHasDigits = areDigits(octet);
            if (!octetOnlyHasDigits) return true;
            boolean haveLeadingZeros = Number.hasLeadingZeros(octet);
            if (haveLeadingZeros) return true;
            return Integer.parseInt(octet) < 0 || Integer.parseInt(octet) > 255;
        }
    }

    static class Number {
        static boolean hasLeadingZeros(String number) {
            return Integer.parseInt(number) != 0 && number.startsWith("0");
        }

    }
}
