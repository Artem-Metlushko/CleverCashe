package org.metlushko.cash.factory;


import lombok.experimental.UtilityClass;
import org.metlushko.cash.mapper.UserMapper;
import org.metlushko.cash.mapper.UserMapperImpl;
import org.metlushko.cash.util.UuidWrapper;

@UtilityClass
public class FactoryGeneric {

    private static volatile UuidWrapper uuidWrapper;

    private static volatile UserMapperImpl userMapper;


    public static UuidWrapper getUuidWrapper() {
        if (uuidWrapper != null) {
            return uuidWrapper;
        }
        synchronized (FactoryGeneric.class) {
            if (uuidWrapper == null) {
                uuidWrapper = new UuidWrapper();
            }
        }
        return uuidWrapper;
    }

    public static UserMapper getUserMapper() {
        if (userMapper != null) {
            return userMapper;
        }
        synchronized (FactoryGeneric.class) {
            if (userMapper == null) {
                userMapper = new UserMapperImpl();
            }
        }
        return userMapper;
    }


}
