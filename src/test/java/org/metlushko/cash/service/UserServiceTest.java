package org.metlushko.cash.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.metlushko.cash.dao.impl.UserDao;
import org.metlushko.cash.dto.UserDto;
import org.metlushko.cash.entity.User;
import org.metlushko.cash.mapper.UserMapper;
import org.metlushko.cash.service.UserService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import util.TestObjectUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserDao userDao;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    @Test
    void testSave() {

        //given
        UserDto userDto = TestObjectUtil.builder().build().buildUserDto();
        User user = new User();
        when(userMapper.toEntity(userDto)).thenReturn(user);
        when(userDao.save(user)).thenReturn(user);

        //when
        User savedUser = userService.save(userDto);

        //then
        assertEquals(user, savedUser);
        verify(userDao, times(1)).save(user);
    }

    @Test
    void testFindById() {
        //given
        String userId = UUID.randomUUID().toString();
        User user = new User();
        when(userDao.findById(userId)).thenReturn(Optional.of(user));

        //when
        User foundUser = userService.findById(userId);

        //then
        assertEquals(user, foundUser);
        verify(userDao, times(1)).findById(userId);
    }

    @Test
    void testFindByIdNotFound() {
        String userId = UUID.randomUUID().toString();;
        when(userDao.findById(userId)).thenReturn(Optional.empty());

        User foundUser = userService.findById(userId);

        assertEquals(new User(), foundUser);
        verify(userDao, times(1)).findById(userId);
    }

    @Test
    void testUpdate() {
        UserDto userDto = TestObjectUtil.builder().build().buildUserDto();
        User existingUser = new User();
        User updatedUser = new User();
        when(userMapper.toEntity(existingUser, userDto)).thenReturn(updatedUser);
        when(userDao.update(updatedUser)).thenReturn(updatedUser);

        User resultUser = userService.update(userDto, existingUser);

        assertEquals(updatedUser, resultUser);
        verify(userDao, times(1)).update(updatedUser);
    }

    @Test
    void testDelete() {
        String userId = "1";

        userService.delete(userId);

        verify(userDao, times(1)).deleteById(userId);
    }

    @Test
    void testFindAll() {
        List<User> userList = new ArrayList<>();
        when(userDao.findAll()).thenReturn(userList);

        List<User> resultUserList = userService.findAll();

        assertEquals(userList, resultUserList);
        verify(userDao, times(1)).findAll();
    }
}
