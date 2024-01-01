package org.metlushko.cash;

import org.metlushko.cash.config.ApplicationContextProvider;
import org.metlushko.cash.config.ConnectionProvider;
import org.metlushko.cash.util.DatabaseInitializerUtil;
import org.springframework.context.ApplicationContext;

public class Main {
    public static void main(String[] args) {

        /*AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        ConnectionProvider connectionProvider = context.getBean(ConnectionProvider.class);
        System.out.println(connectionProvider.getPassword());
        System.out.println(connectionProvider.getUri());
        System.out.println(connectionProvider.getUsername());

        UserService userService = context.getBean(UserService.class);
        System.out.println(userService);*/

        ApplicationContext applicationContext = ApplicationContextProvider.getApplicationContext();
        ConnectionProvider connectionProvider = applicationContext.getBean(ConnectionProvider.class);
        System.out.println(connectionProvider.getDriverClassName());

        System.out.println(connectionProvider);

        DatabaseInitializerUtil.runSqlScripts();


    }

}
