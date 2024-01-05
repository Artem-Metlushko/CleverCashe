package org.metlushko.cash.validation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.metlushko.cash.dto.UserDto;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Aspect
public class EmailAspect {

    @Pointcut("@annotation(org.metlushko.cash.validation.EmailValidator)")
    public void validator() {
    }

    @Around(value = "validator()")
    public Object around(ProceedingJoinPoint jp) {
        try {
            UserDto userDto = (UserDto) jp.getArgs()[0];
            if (!validateEmail(userDto.email())) {
                System.out.println("Email validation failed for "+ userDto.email());
            }

            return jp.proceed();
        } catch (Throwable throwable) {
            throw new RuntimeException("Error during around", throwable);
        }
    }

    private boolean validateEmail(String email) {
        Pattern pattern = Pattern.compile("\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\\b");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

}
