package live.soilandpimp.configuration;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import live.soilandpimp.annotation.DevelopmentProfile;
import live.soilandpimp.annotation.ProductionProfile;
import live.soilandpimp.annotation.SoilAndPimpDataSource;

@Configuration
@DevelopmentProfile
@ProductionProfile
public class DatasourceConfiguration {

    @Bean(destroyMethod = "")
    @SoilAndPimpDataSource
    public DataSource dataSource() throws NamingException {

        Context context = new InitialContext();
        return (DataSource) context.lookup("java:comp/env/jdbc/ds_soil_and_pimp_live");

    }
}
