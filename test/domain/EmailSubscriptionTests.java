package domain;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import live.soilandpimp.domain.EmailSubscription;

public class EmailSubscriptionTests {

    @Test
    public void shouldContsturctNewEmailSubscription() {
        String email = "a@a.a";
        EmailSubscription emailSubscription = new EmailSubscription(email);
        assertNotNull(emailSubscription);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArguementExceptionDueTOBadEmail() {
        String email = "a@a";
        new EmailSubscription(email);
    }

}
