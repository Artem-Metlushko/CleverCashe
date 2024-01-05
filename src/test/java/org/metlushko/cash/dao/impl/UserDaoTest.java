package org.metlushko.cash.dao.impl;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.metlushko.cash.config.ApplicationContextProvider;
import org.metlushko.cash.config.ConnectionProvider;
import org.metlushko.cash.entity.User;
import org.springframework.context.ApplicationContext;
import util.TestObjectUtil;
import util.UuidWrapperMock;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserDaoTest {
    private static ApplicationContext context = ApplicationContextProvider.getApplicationContext();
    private static ConnectionProvider connectionProvider = context.getBean(ConnectionProvider.class);

    private final UuidWrapperMock uuidWrapperMock = UuidWrapperMock.getInstance("45c39ef0-2268-0000-aa93-a425be52eada");
    private final UserDao userDao = new UserDao(uuidWrapperMock,connectionProvider);
    private User expected;

    private static final String CREATE_TABLE = """
                           
            DROP TABLE IF EXISTS "user";
            CREATE TABLE IF NOT EXISTS "user"
            (
                user_id     VARCHAR(255) PRIMARY KEY,
                firstName   VARCHAR(255) NOT NULL,
                lastName    VARCHAR(255) NOT NULL,
                surName     VARCHAR(255) NOT NULL,
                email     VARCHAR(255) NOT NULL,
                phoneNumber VARCHAR(255) NOT NULL
            );

            """;


    @BeforeAll
    static void setUpAll() {




        try (Connection connection = connectionProvider.get();
             Statement statement = connection.createStatement()
        ) {
            statement.execute(CREATE_TABLE);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @BeforeEach
    void setUp() {
        expected = userDao.save(TestObjectUtil.builder().build().buildUser());

    }

    @AfterEach
    void tearDown() {
        userDao.deleteById(expected.getUserId());
    }


    @Test
    void save() {
        assertEquals(expected, TestObjectUtil.builder().build().buildUser());

    }

    @Test
    void update() {
        User userForUpdate = TestObjectUtil.builder()
                .withFirstName("Jon")
                .withLastName("BonJovi")
                .build().buildUser();
        User update = userDao.update(userForUpdate);
        Optional<User> byId = userDao.findById(update.getUserId());
        User actual = byId.orElse(new User());

        assertEquals(userForUpdate, actual);
    }


    @Test
    void findById() {
        Optional<User> byId = userDao.findById(expected.getUserId());
        User user = byId.orElse(new User());
        assertEquals(expected, user);
    }

    @Test
    void deleteById() {
        assertTrue(userDao.deleteById(expected.getUserId()));
    }

    @Test
    void findAll() {
        assertNotNull(userDao.findAll());
    }
}
