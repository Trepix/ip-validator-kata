package scoreboard;

import java.util.stream.Stream;

import static scoreboard.IPV4AddressValidator.Number.isNumeric;

public class IPV4AddressValidator {

    public static boolean isHostAssignable(String ip) {
        if (haveNotFourOctets(ip)) return false;
        if (haveNotNaturalNumbers(ip)) return false;
        if (haveInvalidOctets(ip)) return false;
        if (haveLeadingZeros(ip)) return false;
        if (isNetworkAddress(ip)) return false;
        if (isBroadcastAddress(ip)) return false;
        return true;
    }

    private static boolean haveNotNaturalNumbers(String ip) {
        String[] numbers = getOctets(ip);
        return !(isNumeric(numbers[0]) && isNumeric(numbers[1]) && isNumeric(numbers[2]) && isNumeric(numbers[3]));
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

        static boolean isNumeric(String number) {
            try {
                Integer.parseInt(number);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
    }
}
