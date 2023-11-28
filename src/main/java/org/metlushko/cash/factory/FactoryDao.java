package org.metlushko.cash.factory;


import lombok.experimental.UtilityClass;
import org.metlushko.cash.dao.Dao;
import org.metlushko.cash.dao.impl.UserDao;
import org.metlushko.cash.util.UuidWrapper;

@UtilityClass
public class FactoryDao {

    private static volatile UserDao userDao;

    private static UserDao getUserDao(UuidWrapper uuidWrapper) {
        if (userDao != null) {
            return userDao;
        }
        synchronized (FactoryDao.class) {
            if (userDao == null) {
                userDao = new UserDao(uuidWrapper);
            }
        }
        return userDao;

    }

    public static <T extends Dao> T getDaoInstance(Class<T> daoClass, UuidWrapper uuidWrapper) {
        if (daoClass == UserDao.class) {
            return daoClass.cast(getUserDao(uuidWrapper));
        }
        return null;
    }


}



