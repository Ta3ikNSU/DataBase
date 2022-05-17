package ta3ikdb.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class Configure {
    @Value("${spring.datasource.driver-class-name}")
    String driver;

    @Value("${spring.datasource.username}")
    String username;

    @Value("${spring.datasource.password}")
    String pwd;

    @Value("${spring.datasource.url}")
    String url;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(driver);
        dataSource.setUsername(username);
        dataSource.setPassword(pwd);
        dataSource.setUrl(url);

        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        DataSource dataSource = dataSource();
        return new JdbcTemplate(dataSource);
    }


}
