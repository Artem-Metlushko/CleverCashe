/*
package org.metlushko.cash.factory;


import lombok.experimental.UtilityClass;
import org.metlushko.cash.dao.impl.UserDao;
import org.metlushko.cash.util.UuidWrapper;


@UtilityClass
public class FactoryDao {
    public static final UuidWrapper uuidWrapper = FactoryGeneric.getUuidWrapper();

    private static volatile UserDao userDao;

    public static UserDao getUserDao() {

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


}



*/
