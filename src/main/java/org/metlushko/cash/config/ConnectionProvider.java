package org.metlushko.cash.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
@PropertySource("classpath:application.properties")
public class ConnectionProvider {
    @Value("${db.url}")
    private String uri;
    @Value("${db.username}")
    private String username;
    @Value("${db.password}")
    private String password;
    @Value("${datasource.driver-class-name}")
    private String driverClassName;

    /**
     * Obtain a database connection.
     *
     * @return A database connection object.
     * @throws SQLException If an error occurs while obtaining the connection.
     */
    public Connection get() {

        try {
            Class.forName(driverClassName);
            return DriverManager.getConnection(uri, username, password);
        } catch (Exception ex) {
            throw new RuntimeException("Error creating connection ");
        }
    }

}
