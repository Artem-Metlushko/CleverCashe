package org.metlushko.cash.util;

import lombok.experimental.UtilityClass;
import org.metlushko.cash.exception.ConnectionPoolException;
import org.metlushko.cash.exception.DatabaseConnectionException;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
@UtilityClass
public class ConnectionManager {

    private static final String PASSWORD_KEY = "db.password";
    private static final String USERNAME_KEY = "db.username";
    private static final String URL_KEY = "db.url";
    private static final String POOL_SIZE_KEY = "db.pool.size";
    private static final String DEFAULT_POOL_SIZE = "db.default.pool.size";
    private static BlockingQueue<Connection> pool;
    private static List<Connection> sourceConnections;

    static {
        initConnectionPool();
    }


    private static void initConnectionPool() {
        var poolSize = PropertiesUtil.get(POOL_SIZE_KEY);
        var size = poolSize == null ? Integer.parseInt(DEFAULT_POOL_SIZE) : Integer.parseInt(poolSize);
        pool = new ArrayBlockingQueue<>(size);
        sourceConnections = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            var connection = open();
            var proxyConnection = (Connection)
                    Proxy.newProxyInstance(ConnectionManager.class.getClassLoader(), new Class[]{Connection.class},
                            (proxy, method, args) -> method.getName().equals("close")
                                    ? pool.add((Connection) proxy)
                                    : method.invoke(connection, args));
            pool.add(proxyConnection);
            sourceConnections.add(connection);
        }
    }

    public static Connection get() {
        try {
            return pool.take();
        } catch (InterruptedException e) {
            throw new ConnectionPoolException("Error while retrieving a connection from the pool.", e);
        }
    }


    private static Connection open() {
        try {
            return DriverManager.getConnection(
                    PropertiesUtil.get(URL_KEY),
                    PropertiesUtil.get(USERNAME_KEY),
                    PropertiesUtil.get(PASSWORD_KEY)
            );
        } catch (SQLException e) {
            throw new DatabaseConnectionException("Error while opening a database connection.", e);
        }
    }


}



/*    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;

    private static String poolSize = PropertiesUtil.get(POOL_SIZE_KEY);
    private static String url = PropertiesUtil.get(URL_KEY);
    private static String username = PropertiesUtil.get(USERNAME_KEY);
    private static String password = PropertiesUtil.get(PASSWORD_KEY);


    static {
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);
        config.setMaximumPoolSize(Integer.parseInt(poolSize));
        config.setAutoCommit(false);
        ds = new HikariDataSource(config);
    }

    public static Connection get()  {
        try {
            return ds.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private ConnectionManager() {
    }*/
