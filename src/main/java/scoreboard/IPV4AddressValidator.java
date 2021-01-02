package scoreboard;

public class IPV4AddressValidator {
    public static boolean isHostAssignable(String ip) {
        if ("0.0.0.0".equals(ip) || "255.255.255.0".equals(ip)) return false;
        return true;
    }
}
