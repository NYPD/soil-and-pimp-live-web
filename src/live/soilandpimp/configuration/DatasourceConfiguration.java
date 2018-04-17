package live.soilandpimp.configuration;

import java.util.Arrays;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import live.soilandpimp.annotation.DevelopmentProfile;
import live.soilandpimp.annotation.ProductionProfile;
import live.soilandpimp.annotation.SoilAndPimpDataSource;
import live.soilandpimp.util.AppConstants;

@Configuration
@DevelopmentProfile
@ProductionProfile
@PropertySource(value = {"classpath:resources/mysql.properties"})
public class DatasourceConfiguration {

    @Autowired
    private Environment springEnvironment;

    @Bean(destroyMethod = "")
    @SoilAndPimpDataSource
    public DataSource dataSource() {

        String[] activeProfiles = springEnvironment.getActiveProfiles();

        boolean isDevelopment = Arrays.stream(activeProfiles)
                                      .filter(x -> AppConstants.DEVELOPMENT_PROFILE.equals(x))
                                      .findAny()
                                      .orElse(null) != null;

        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setUrl(isDevelopment? AppConstants.MYSQL_DEVELOPMENT_URL : AppConstants.MYSQL_PRODUCTION_URL);
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUsername(springEnvironment.getProperty("mysql.username"));
        dataSource.setPassword(springEnvironment.getProperty("mysql.password"));

        return dataSource;

    }
}
