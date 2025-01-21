package shopping.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfig {

    Logger logger = LogManager.getLogger(DataSourceConfig.class);

    @Value("${db.driver}")
    private String driver;

    @Value("${db.host}")
    private String host;

    @Value("${db.database}")
    private String database;

    @Value("${db.schema}")
    private String schema;

    @Value("${db.username}")
    private String username;

    @Value("${db.password}")
    private String password;

    private static HikariConfig config = new HikariConfig();

    @Bean
    public HikariDataSource dataSource() {

        System.out.println("password: " + password);
        config.setDriverClassName(driver);
        config.setJdbcUrl("jdbc:postgresql://"+host+"/"+database+"?currentSchema="+schema);
        config.setUsername(username);
        config.setPassword(password);
        config.setMaximumPoolSize(5);

        logger.info("Datos de conexion PostgerSQL, host: " + host + ", database: " + database + ", schema: " + schema);
        logger.info("Maximum Pool Size Configuration: " + config.getMaximumPoolSize());
        logger.info("Url connection: " + config.getJdbcUrl());

        return new HikariDataSource(config);

    }

}