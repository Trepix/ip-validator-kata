package scoreboard;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class IPV4AddressValidatorTest {

    @Test
    public void should_consider_as_valid_a_host_assignable_ip() {
        boolean isValid = IPV4AddressValidator.isHostAssignable("1.1.1.1");

        assertThat(isValid, is(true));
    }
}
