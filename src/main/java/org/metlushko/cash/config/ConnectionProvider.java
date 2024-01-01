package org.metlushko.cash.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Getter
@Configuration
public class ConnectionProvider {
    @Value("${db.url:jdbc:postgresql://localhost:15432/ironbank}")
    private String uri;
    @Value("${db.username:postgres}")
    private String username;
    @Value("${db.password:1234}")
    private String password;
    @Value("${datasource.driver-class-name:org.postgresql.Driver}")
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
