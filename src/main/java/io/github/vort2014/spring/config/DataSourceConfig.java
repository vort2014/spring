package io.github.vort2014.spring.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import javax.sql.DataSource;

import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.DERBY;
import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.H2;

/**
 * Created by vort on 09.07.2017.
 */
@Configuration
@Profile("default") // this profile is used if active profile is not set
public class DataSourceConfig {

    /**
     * Much faster starts spring without autoconfiguration
     * <p>
     * Embedded database supports only one connection
     * To view database we need the file database
     * <p>
     * In case of autoconfiguration:
     * In documentation driver name doesn't need because of JDBC 4.0
     * Spring register org.apache.derby.jdbc.EmbeddedDriver by means of DatabaseDriver.fromJdbcUrl() based on jdbc url
     * Then spring loads embedded database and its scripts via spring.datasource.initialize property
     * JDBC API registers org.apache.derby.jdbc.AutoloadedDriver based on service provider
     * Then Hikari compare these two ones and fails to load EmbeddedDriver, so we need explicit define it:
     */
    // spring boot automatically loads schema.sql and data.sql files
    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(H2)
                .setName("MODE=Oracle")
                // see spring.datasource.initialize
                .addScript("default_schema.sql")
                .addScript("default_data.sql")
                .build();
    }

    // file database that can be viewed
//    @Bean
//    public DataSource dataSource() {
//        HikariDataSource hikariDataSource = new HikariDataSource();
//        hikariDataSource.setJdbcUrl("jdbc:derby:DERBY_DB;create=true");
//        hikariDataSource.setJdbcUrl("jdbc:derby:DERBY_DB");
//        hikariDataSource.setUsername("sa");
//        hikariDataSource.setDriverClassName(AutoloadedDriver.class.getName());
//        return hikariDataSource;
//    }
}
