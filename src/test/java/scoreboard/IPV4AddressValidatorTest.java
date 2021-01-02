package scoreboard;

import org.junit.Ignore;
import org.junit.jupiter.api.Disabled;
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
            "1.1.1.1.1",
            "255.255.255.1.254",
    })
    public void should_consider_as_invalid_an_IP_with_not_four_octets(String ip) {
        boolean isValid = IPV4AddressValidator.isHostAssignable(ip);

        assertThat(isValid, is(false));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "300.1.1.1",
            "1.400.1.1",
            "1.255.500.1",
            "1.1.1.256",
    })
    public void should_check_that_ip_has_valid_octets(String ip) {
        boolean isValid = IPV4AddressValidator.isHostAssignable(ip);

        assertThat(isValid, is(false));
    }

    @Test
    @Disabled
    public void validate_IP_has_no_leading_zeros(){}

    @Test
    @Disabled
    public void validate_IP_has_no_other_characters_than_numbers(){}

}
