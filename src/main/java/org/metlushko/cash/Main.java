package org.metlushko.cash;

import org.metlushko.cash.dao.impl.UserDao;
import org.metlushko.cash.dto.UserDto;
import org.metlushko.cash.entity.User;
import org.metlushko.cash.mapper.UserMapperImpl;
import org.metlushko.cash.service.UserService;
import org.metlushko.cash.util.DatabaseInitializerUtil;
import org.metlushko.cash.util.UuidWrapper;

public class Main {
    public static void main(String[] args) {
        DatabaseInitializerUtil.runSqlScripts();
        UuidWrapper uuidWrapper = new UuidWrapper();
        UserDao userDao = new UserDao(uuidWrapper);





        UserService userService = new UserService(userDao, new UserMapperImpl());
        User save = userService.save(new UserDto("BOb", "Bob", "Bob", "Bob", "11111111111"));
        System.out.println("=====>>saveUser"+userService.findById(save.getUserId()));
        System.out.println();
        System.out.println(userService.findById("7bd1f9fe-21ab-49ac-a461-d9ea85b987d3"));

//        userService.update(new UserDto("ADAM", "ADAM", "ADAM", "ADAM", "ADAM"), save);
//        userService.delete("7bd1f9fe-21ab-49ac-a461-d9ea85b987d3");
//        userService.delete(save.getUserId());

    }

    private static User getUser() {
        return User.builder()
                .userId("cfc53692-17ab-41e1-9c4a-e96e55e10b0e")
                .email("Amelia")
                .surName("Amelia")
                .firstName("Amelia")
                .lastName("Amelia")
                .phoneNumber("380000000000")
                .build();
    }
}
