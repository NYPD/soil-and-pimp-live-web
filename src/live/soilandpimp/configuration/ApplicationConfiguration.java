package live.soilandpimp.configuration;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;

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

import live.soilandpimp.beans.SoilAndPimpSessionBean;
import live.soilandpimp.configuration.api.GoogleConfiguration;
import live.soilandpimp.service.Service;

@Configuration
@Import({LogbackConfiguration.class, CassandraConfiguration.class, GoogleConfiguration.class})
@PropertySource("classpath:resources/project.properties")
@ComponentScan(basePackageClasses = {Service.class})
public class ApplicationConfiguration {

    @Autowired(required = false)
    private ServletContext servletContext;

    @Autowired
    private Environment springEnvironment;

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public SoilAndPimpSessionBean moeSoundsSessionBean() {
        return new SoilAndPimpSessionBean();
    }

    @PostConstruct
    public void addServletContextProperties() {
        if (servletContext == null) return;
        servletContext.setAttribute("projectVersion", springEnvironment.getProperty("application.version"));
    }
}
