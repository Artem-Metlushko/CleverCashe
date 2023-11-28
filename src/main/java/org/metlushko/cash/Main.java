package org.metlushko.cash;

import org.metlushko.cash.dao.impl.UserDao;
import org.metlushko.cash.dto.UserDto;
import org.metlushko.cash.entity.User;
import org.metlushko.cash.factory.FactoryGeneric;
import org.metlushko.cash.mapper.UserMapperImpl;
import org.metlushko.cash.pdf.PdfPrint;
import org.metlushko.cash.service.UserService;
import org.metlushko.cash.util.DatabaseInitializerUtil;
import org.metlushko.cash.util.UuidWrapper;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        DatabaseInitializerUtil.runSqlScripts();

        UuidWrapper uuidWrapper = FactoryGeneric.getUuidWrapper();
        UserDao userDao = new UserDao(uuidWrapper);
        UserService userService = new UserService(userDao, new UserMapperImpl());
        UserDto userDto = new UserDto("BOb", "Bob", "Bob", "394723@mail.ru", "11111111111");


        User saveUser = userService.save(userDto);
        userService.findById(saveUser.getUserId());
        userService.update(new UserDto("ADAM", "ADAM", "ADAM", "qweqwe@mail.ru", "ADAM"), saveUser);
        userService.delete(saveUser.getUserId());

        List<User> all = userService.findAll();
        PdfPrint.createReport(all);


    }

}
