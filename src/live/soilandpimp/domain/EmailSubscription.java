package live.soilandpimp.domain;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "email_subscriptions")
public class EmailSubscription {

    @Id
    @Column(name = "email_address")
    private String emailAddress;
    @Column(name = "verification_token")
    private String verificationToken;
    @Column
    private boolean verified;
    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    @Column(name = "created_date")
    private Date createdDate;

    // JPA constructor
    protected EmailSubscription() {};

    public EmailSubscription(String emailAddress) {

        Pattern emailRegex = Pattern.compile(".+@.+\\..+");
        Matcher matcher = emailRegex.matcher(emailAddress);

        boolean invalidEmail = !matcher.matches();
        if (invalidEmail) throw new IllegalArgumentException("Invalid email Address");

        this.emailAddress = emailAddress;
        this.verificationToken = new BigInteger(130, new SecureRandom()).toString(32);
        this.createdDate = new Date();

    }
    
    // Modified Accessors ********************************************
    public boolean verifyEmailAddress(String userVerificationToken) {
        
        boolean validToken = this.verificationToken.equals(userVerificationToken);
        if(validToken) this.verified = true;
        
        return validToken;
        
    }

    // Default Accessors *********************************************
    public String getEmailAddress() {
        return emailAddress;
    }
    public String getVerificationToken() {
        return verificationToken;
    }
    public boolean isVerified() {
        return verified;
    }
    public Date getCreatedDate() {
        return createdDate;
    }

    @Override
    public String toString() {
        return "EmailSubscription [emailAddress=" + emailAddress + "]";
    }
}
