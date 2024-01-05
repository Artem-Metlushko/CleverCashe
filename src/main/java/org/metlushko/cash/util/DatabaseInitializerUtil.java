package org.metlushko.cash.util;

import lombok.experimental.UtilityClass;
import org.metlushko.cash.config.ApplicationContextProvider;
import org.metlushko.cash.config.ConnectionProvider;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@UtilityClass
public final class DatabaseInitializerUtil {
   private ApplicationContext context = ApplicationContextProvider.getApplicationContext();



    public static void runSqlScripts() {
        ConnectionProvider connectionProvider = context.getBean(ConnectionProvider.class);
        try(Connection connection = connectionProvider.get();
            Statement statement = connection.createStatement();
            ) {
            statement.execute(readScript());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static String readScript() {
        Path scriptPath = Paths.get("src/main/resources/initialization.sql");
        try {
            byte[] scriptBytes = Files.readAllBytes(scriptPath);
            return new String(scriptBytes, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
