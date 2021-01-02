package scoreboard;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class IPV4AddressValidatorTest {

    @ParameterizedTest
    @ValueSource(strings = {
            "1.1.1.1",
            "192.168.1.1",
            "10.0.0.1",
            "127.0.0.1"
    })
    public void should_consider_as_valid_a_host_assignable_IP(String ip) {
        boolean isValid = IPV4AddressValidator.isHostAssignable(ip);

        assertThat(isValid, is(true));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "0.0.0.0",
            "255.255.255.0"
    })
    public void should_consider_as_invalid_a_network_IP(String ip) {
        boolean isValid = IPV4AddressValidator.isHostAssignable(ip);

        assertThat(isValid, is(false));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "1.1.1.255",
            "255.255.255.255",
    })
    public void should_consider_as_invalid_a_broadcast_IP(String ip) {
        boolean isValid = IPV4AddressValidator.isHostAssignable(ip);

        assertThat(isValid, is(false));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "1",
            "1.1.1",
    })
    public void should_consider_as_invalid_IP_with_less_than_four_octets(String ip) {
        boolean isValid = IPV4AddressValidator.isHostAssignable(ip);

        assertThat(isValid, is(false));
    }

}
