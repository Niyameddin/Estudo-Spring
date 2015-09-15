package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * Created by guilherme on 14/09/15.
 */
public class DataSourceConfigurationTest {
    @Bean
    @Profile("test")
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/estudo-spring-testes");
        dataSource.setUsername("guilherme");
        dataSource.setPassword("guiadmin");
        return dataSource;
    }
}
