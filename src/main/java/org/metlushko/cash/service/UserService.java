package org.metlushko.cash.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.metlushko.cash.dao.impl.UserDao;
import org.metlushko.cash.dto.UserDto;
import org.metlushko.cash.entity.User;
import org.metlushko.cash.mapper.UserMapper;
import org.metlushko.cash.validation.EmailValidator;

import java.util.List;

@Slf4j
@AllArgsConstructor
public class UserService {

    private final UserDao userDao;

    private final UserMapper userMapper;
    @EmailValidator
    public User save( UserDto userDto) {

        User user = userMapper.toEntity(userDto);

        return userDao.save(user);
    }

    public User findById(String accountId) {

        return userDao.findById(accountId).orElse(new User());

    }

    @EmailValidator
    public User update(UserDto userDto, User user) {

        User updateUser = userMapper.toEntity(user, userDto);

        return userDao.update(updateUser);
    }

    public void delete(String id) {

        userDao.deleteById(id);

    }

    public List<User> findAll() {

        return userDao.findAll();

    }

}
