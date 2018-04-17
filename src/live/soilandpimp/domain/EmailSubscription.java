package live.soilandpimp.domain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "email_subscriptions")
public class EmailSubscription {

    @Id
    @Column(name = "email_address")
    private String emailAddress;

    // Cassandra constructor
    protected EmailSubscription() {};

    public EmailSubscription(String emailAddress) {

        Pattern emailRegex = Pattern.compile(".+@.+\\..+");
        Matcher matcher = emailRegex.matcher(emailAddress);

        boolean invalidEmail = !matcher.matches();

        if (invalidEmail) throw new IllegalArgumentException("Invalid email Address");

        this.emailAddress = emailAddress;

    }

    // Default Accessors *********************************************
    public String getEmailAddress() {
        return emailAddress;
    }

    @Override
    public String toString() {
        return "EmailSubscription [emailAddress=" + emailAddress + "]";
    }
}
