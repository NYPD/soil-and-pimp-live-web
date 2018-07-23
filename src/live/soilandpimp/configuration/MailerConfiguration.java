package live.soilandpimp.configuration;

import org.simplejavamail.mailer.Mailer;
import org.simplejavamail.mailer.MailerBuilder;
import org.simplejavamail.mailer.config.TransportStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import live.soilandpimp.annotation.DevelopmentProfile;
import live.soilandpimp.annotation.ProductionProfile;
import live.soilandpimp.annotation.TestProfile;

@Configuration
@PropertySource("classpath:resources/mailer.properties")
public class MailerConfiguration {

    @Autowired
    private Environment springEnvironment;

    @Bean
    @TestProfile
    @DevelopmentProfile
    public Mailer devMailer() {
        return MailerBuilder.withSMTPServerHost(springEnvironment.getProperty("mailer.dev.host"))
                            .withSMTPServerPort(Integer.parseInt(springEnvironment.getProperty("mailer.dev.port")))
                            .withSMTPServerUsername(springEnvironment.getProperty("mailer.dev.username"))
                            .withSMTPServerPassword(springEnvironment.getProperty("mailer.dev.password"))
                            .withTransportStrategy(TransportStrategy.SMTPS)
                            .buildMailer();
    }

    @Bean
    @ProductionProfile
    public Mailer prodMailer() {
        return MailerBuilder.withSMTPServerHost(springEnvironment.getProperty("mailer.prod.host"))
                            .withSMTPServerPort(Integer.parseInt(springEnvironment.getProperty("mailer.prod.port")))
                            .buildMailer();
    }
}
