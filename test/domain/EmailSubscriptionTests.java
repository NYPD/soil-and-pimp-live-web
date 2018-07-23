package domain;

import java.math.BigInteger;
import java.security.SecureRandom;

import org.junit.Test;

import live.soilandpimp.domain.EmailSubscription;

public class EmailSubscriptionTests {

    @Test
    public void shouldContsturctNewEmailSubscription() {
        String email = "a@a.a";
        new EmailSubscription(email);

        System.out.println(new BigInteger(130, new SecureRandom()).toString(32));

    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArguementExceptionDueTOBadEmail() {
        String email = "a@a";
        new EmailSubscription(email);
    }

}
