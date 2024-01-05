package org.metlushko.cash.service;

import lombok.extern.slf4j.Slf4j;
import org.metlushko.cash.dao.impl.UserDao;
import org.metlushko.cash.dto.UserDto;
import org.metlushko.cash.entity.User;
import org.metlushko.cash.mapper.UserMapper;
import org.metlushko.cash.validation.EmailValidator;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing users.
 */
@Slf4j
@Service
public class UserService {

    private final UserDao userDao;
    private final UserMapper userMapper;

    public UserService(final UserDao userDao,final UserMapper userMapper) {
        this.userDao = userDao;
        this.userMapper = userMapper;
    }

    /**
     * Saves a new user.
     *
     * @param userDto The DTO object containing user information.
     * @return The saved User entity.
     */
    @EmailValidator
    public User save(UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        return userDao.save(user);
    }

    /**
     * Finds a user by their account ID.
     *
     * @param accountId The account ID of the user to find.
     * @return The found User entity, or an empty User if not found.
     */
    public User findById(String accountId) {
        return userDao.findById(accountId).orElse(new User());
    }

    /**
     * Updates an existing user.
     *
     * @param userDto The DTO object containing updated user information.
     * @param user    The existing User entity to be updated.
     * @return The updated User entity.
     */
//    @EmailValidator
    public User update(UserDto userDto, User user) {
        User updateUser = userMapper.toEntity(user, userDto);
        return userDao.update(updateUser);
    }

    public User update(User user) {
        return userDao.update(user);
    }

    /**
     * Deletes a user by their ID.
     *
     * @param id The ID of the user to delete.
     */
    public void delete(String id) {
        userDao.deleteById(id);
    }

    /**
     * Retrieves a list of all users.
     *
     * @return The list of all User entities.
     */
    public List<User> findAll() {
        return userDao.findAll();
    }
}
