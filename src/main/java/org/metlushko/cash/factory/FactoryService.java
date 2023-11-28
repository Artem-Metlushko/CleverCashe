package org.metlushko.cash.factory;


import lombok.experimental.UtilityClass;
import org.metlushko.cash.dao.impl.UserDao;
import org.metlushko.cash.mapper.UserMapper;
import org.metlushko.cash.service.UserService;
import org.metlushko.cash.util.UuidWrapper;

@UtilityClass
public class FactoryService {

    private static volatile UserService userService;
    public static final UserMapper userMapper = FactoryGeneric.getUserMapper();
    public static final UuidWrapper uuidWrapper = FactoryGeneric.getUuidWrapper();
    public static final UserDao userDao = FactoryDao.getUserDao();


    public static UserService getUserService() {
        if (userService != null) {
            return userService;
        }
        synchronized (FactoryService.class) {
            if (userService == null) {
                userService = new UserService(userDao, userMapper);
            }
        }
        return userService;
    }


}

