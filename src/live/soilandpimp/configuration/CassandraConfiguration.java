package live.soilandpimp.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

import com.datastax.driver.core.AuthProvider;
import com.datastax.driver.core.PlainTextAuthProvider;

import live.soilandpimp.annotation.DevelopmentProfile;
import live.soilandpimp.annotation.ProductionProfile;
import live.soilandpimp.domain.Domain;
import live.soilandpimp.repository.Repository;
import live.soilandpimp.util.AppConstants;

@Configuration
@PropertySource("classpath:resources/cassandra.properties")
@DevelopmentProfile
@ProductionProfile
@EnableCassandraRepositories(basePackageClasses = {Repository.class})
public class CassandraConfiguration extends AbstractCassandraConfiguration {

    @Value("${cassandra.contact_points}")
    private String cassandraContactPoints;

    @Value("${cassandra.username}")
    private String cassandraUsername;

    @Value("${cassandra.password}")
    private String cassandraPassword;

    @Override
    protected String getKeyspaceName() {
        return AppConstants.CASSANDRA_KEYSPACE;
    }

    @Override
    protected String getContactPoints() {
        return cassandraContactPoints;
    }

    @Override
    protected AuthProvider getAuthProvider() {
        return new PlainTextAuthProvider(cassandraUsername, cassandraPassword);
    }

    @Override
    public String[] getEntityBasePackages() {
        return new String[] {Domain.class.getPackage().getName()};
    }

}