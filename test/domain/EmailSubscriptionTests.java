package domain;

import org.junit.Test;

import live.soilandpimp.domain.EmailSubscription;

public class EmailSubscriptionTests {

    @Test
    public void shouldContsturctNewEmailSubscription() {
        String email = "a@a.a";
        new EmailSubscription(email);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArguementExceptionDueTOBadEmail() {
        String email = "a@a";
        new EmailSubscription(email);
    }

}
