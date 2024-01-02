package org.metlushko.cash.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.metlushko.cash.cache.Cache;
import org.metlushko.cash.entity.User;
import org.metlushko.cash.util.CacheManager;

import java.util.Optional;


@Aspect
public class AspectCache {
    private final Cache<User, String> cache = CacheManager.getCacheType();

    @Pointcut("@annotation(org.metlushko.cash.aspect.annotation.SaveCache)")
    public void save() {

    }


    @Around(value = "save()")
    public User saveMethod(ProceedingJoinPoint jp) {

        try {
            User user = (User) jp.getArgs()[0];

            User savedUser = (User) jp.proceed();

            cache.put(savedUser.getUserId(), savedUser);

            return user;
        } catch (Throwable throwable) {
            throw new RuntimeException("Error during saveMethod", throwable);
        }

    }

    @Pointcut("@annotation(org.metlushko.cash.aspect.annotation.FindInCache)")
    public void find() {

    }

    @Around(value = "find()")
    @SuppressWarnings("unchecked")
    public Optional<User> findMethod(ProceedingJoinPoint jp) {
        try {
            String id = (String) jp.getArgs()[0];

            Optional<User> cachedUser = Optional.ofNullable(cache.get(id));

            if (cachedUser.isPresent()) {
                return cachedUser;
            }

            Optional<User> userFromDatabase = (Optional<User>) jp.proceed();

            userFromDatabase.ifPresent(user -> cache.put(id, user));

            return userFromDatabase;
        } catch (Throwable throwable) {
            throw new RuntimeException("Error during findMethod", throwable);
        }
    }


    @Pointcut("@annotation(org.metlushko.cash.aspect.annotation.UpdateInCache)")
    public void update() {

    }

    @Around(value = "update()")
    public User updateMethod(ProceedingJoinPoint jp) {


        try {
            User user = (User) jp.getArgs()[0];

            cache.update(user.getUserId(), user);

            User updatedUser = (User) jp.proceed();

            return updatedUser;
        } catch (Throwable throwable) {
            throw new RuntimeException("Error during updateMethod", throwable);
        }


    }

    @Pointcut("@annotation(org.metlushko.cash.aspect.annotation.RemoveInCache)")
    public void remove() {

    }

    @Around(value = "remove()")
    public boolean removeMethod(ProceedingJoinPoint jp) {
        try {
            String id = (String) jp.getArgs()[0];

            Optional<User> cachedUser = Optional.ofNullable(cache.get(id));

            if (cachedUser.isPresent()) {

                cache.remove(id);
                return (boolean) jp.proceed();
            }

            return (boolean) jp.proceed();
        } catch (Throwable throwable) {
            throw new RuntimeException("Error during removeMethod", throwable);
        }
    }


}
