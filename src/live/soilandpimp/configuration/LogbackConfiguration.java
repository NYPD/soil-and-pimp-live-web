package live.soilandpimp.configuration;

import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.net.SMTPAppender;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.ConsoleAppender;
import ch.qos.logback.core.net.SMTPAppenderBase;
import ch.qos.logback.core.rolling.RollingFileAppender;
import ch.qos.logback.core.rolling.TimeBasedRollingPolicy;
import live.soilandpimp.annotation.DevelopmentProfile;
import live.soilandpimp.annotation.ProductionProfile;
import live.soilandpimp.annotation.TestProfile;
import live.soilandpimp.util.AppConstants;

@Configuration
public class LogbackConfiguration {

    private final String encoderPattern = "%d{[yyyy-MM-dd HH:mm:ss.SSS]} [%-5level] \\(%F{0}:%M\\(\\):%L\\) - %msg%n";
    private final String filePattern = "/tomcat/logs/" + AppConstants.PROJECT_NAME + "/goddess.%d{yyyy-MM-dd}.log";
    private final LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
    private final PatternLayoutEncoder defaultPatternLayout;

    {
        defaultPatternLayout = new PatternLayoutEncoder();
        defaultPatternLayout.setContext(loggerContext);
        defaultPatternLayout.setPattern(encoderPattern);
        defaultPatternLayout.start();
    }

    @Bean
    @DevelopmentProfile
    @TestProfile
    public ConsoleAppender<ILoggingEvent> consoleAppender() {

        ConsoleAppender<ILoggingEvent> consoleAppender = new ConsoleAppender<>();
        consoleAppender.setName("console");
        consoleAppender.setEncoder(defaultPatternLayout);
        consoleAppender.setContext(loggerContext);
        consoleAppender.start();

        return consoleAppender;

    }

    @Bean
    @DevelopmentProfile
    @ProductionProfile
    public RollingFileAppender<ILoggingEvent> rollingFileAppender() {

        RollingFileAppender<ILoggingEvent> rollingFileAppender = new RollingFileAppender<>();
        rollingFileAppender.setName("rolling");
        rollingFileAppender.setContext(loggerContext);

        TimeBasedRollingPolicy<ILoggingEvent> timeBasedRollingPolicy = new TimeBasedRollingPolicy<>();
        timeBasedRollingPolicy.setFileNamePattern(filePattern);
        timeBasedRollingPolicy.setMaxHistory(60);
        timeBasedRollingPolicy.setParent(rollingFileAppender);
        timeBasedRollingPolicy.setContext(loggerContext);
        timeBasedRollingPolicy.start();

        rollingFileAppender.setEncoder(defaultPatternLayout);
        rollingFileAppender.setRollingPolicy(timeBasedRollingPolicy);
        rollingFileAppender.start();

        return rollingFileAppender;

    }

    @Bean
    @ProductionProfile
    public SMTPAppenderBase<ILoggingEvent> smptAppender() {

        SMTPAppender smtpAppender = new SMTPAppender();
        smtpAppender.setContext(loggerContext);
        smtpAppender.setName("e-mail");
        smtpAppender.setFrom("exceptions@soilandpimp.live");
        smtpAppender.addTo("devs@soilandpimp.live");
        smtpAppender.setSubject(AppConstants.APPLICATION_NAME + " Exception");
        smtpAppender.setSMTPHost("localhost");
        smtpAppender.setSMTPPort(25);

        smtpAppender.setAsynchronousSending(false);
        smtpAppender.setLayout(defaultPatternLayout.getLayout());

        smtpAppender.start();

        return smtpAppender;

    }

}
