package org.metlushko.cash.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextProvider  {

    public static ApplicationContext getApplicationContext() {
        return new AnnotationConfigApplicationContext(ApplicationConfig.class);
    }
}
