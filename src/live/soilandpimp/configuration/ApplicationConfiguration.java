package live.soilandpimp.configuration;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.env.Environment;
import org.springframework.web.context.WebApplicationContext;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Appender;
import live.soilandpimp.beans.SoilAndPimpSessionBean;
import live.soilandpimp.configuration.api.GoogleConfiguration;
import live.soilandpimp.service.Service;
import live.soilandpimp.util.AppConstants;

@Configuration
@Import({LogbackConfiguration.class, CassandraConfiguration.class, GoogleConfiguration.class})
@PropertySource("classpath:resources/project.properties")
@ComponentScan(basePackageClasses = {Service.class})
public class ApplicationConfiguration {

    @Autowired(required = false)
    private ServletContext servletContext;
    @Autowired
    private Environment springEnvironment;
    @Autowired
    private List<Appender<ILoggingEvent>> appenders;
    @Autowired
    private SoilAndPimpSessionBean soilAndPimpSessionBean;

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public SoilAndPimpSessionBean moeSoundsSessionBean() {
        return new SoilAndPimpSessionBean();
    }

    @PostConstruct
    public void initLogbackAppenders() {

        Logger root = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
        root.detachAndStopAllAppenders();

        for (Appender<ILoggingEvent> appender : appenders)
            root.addAppender(appender);

        String[] activeProfiles = springEnvironment.getActiveProfiles();

        boolean isDevelopment = Arrays.stream(activeProfiles).filter(x -> AppConstants.DEVELOPMENT_PROFILE.equals(x))
                .findAny().orElse(null) != null;

        Level loggingLevel = isDevelopment? Level.DEBUG : Level.INFO;

        root.setLevel(loggingLevel);

    }

    @PostConstruct
    public void addServletContextProperties() {

        if (servletContext == null) return;

        servletContext.setAttribute("projectVersion", springEnvironment.getProperty("application.version"));
        servletContext.setAttribute("applicationName", AppConstants.APPLICATION_NAME);
        servletContext.setAttribute("projectName", AppConstants.PROJECT_NAME);
        servletContext.setAttribute("soilAndPimpSessionBean", soilAndPimpSessionBean);

    }
}
