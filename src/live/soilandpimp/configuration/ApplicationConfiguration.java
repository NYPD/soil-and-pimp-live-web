package live.soilandpimp.configuration;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import live.soilandpimp.service.Service;

@Configuration
@Import({LogbackConfiguration.class, CassandraConfiguration.class})
@PropertySource("classpath:resources/project.properties")
@ComponentScan(basePackageClasses = {Service.class})
public class ApplicationConfiguration {

    @Autowired(required = false)
    private ServletContext servletContext;

    @Autowired
    private Environment springEnvironment;

    @PostConstruct
    public void addServletContextProperties() {
        if (servletContext == null) return;
        servletContext.setAttribute("projectVersion", springEnvironment.getProperty("application.version"));
    }
}
