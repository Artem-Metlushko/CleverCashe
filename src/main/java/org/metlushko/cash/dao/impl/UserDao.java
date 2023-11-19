package org.metlushko.cash.dao.impl;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.metlushko.cash.aspect.annotation.FindInCache;
import org.metlushko.cash.aspect.annotation.RemoveInCache;
import org.metlushko.cash.aspect.annotation.SaveCache;
import org.metlushko.cash.aspect.annotation.UpdateInCache;
import org.metlushko.cash.dao.Dao;
import org.metlushko.cash.entity.User;
import org.metlushko.cash.mapper.MapperUser;
import org.metlushko.cash.util.ConnectionManager;
import org.metlushko.cash.util.UuidWrapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

@Slf4j
@AllArgsConstructor
public class UserDao implements Dao<String, User> {

    private final UuidWrapper uuidWrapper;

    private static final String CREATE_USER = "INSERT INTO \"user\" (user_id, firstName, lastName, surName, email, phoneNumber) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_USER = "UPDATE  \"user\" SET firstName = ?, lastName = ?, surName = ?, email = ?, phoneNumber = ? WHERE user_id = ?;";
    private static final String FIND_BY_ID = "SELECT * FROM \"user\" WHERE user_id = ?";
    private static final String DELETE_USER = "DELETE FROM \"user\" WHERE user_id = ?";
    private static final String FIND_ALL = "SELECT * FROM \"user\"";

    @Override
    @SaveCache
    public User save(User user) {

        user.setUserId(uuidWrapper.randomUUID());

        try (var connection = ConnectionManager.get();
             var ps = connection.prepareStatement(CREATE_USER, RETURN_GENERATED_KEYS)
        ) {
            ps.setString(1, user.getUserId());
            ps.setString(2, user.getFirstName());
            ps.setString(3, user.getLastName());
            ps.setString(4, user.getSurName());
            ps.setString(5, user.getEmail());
            ps.setString(6, user.getPhoneNumber());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                user.setUserId(rs.getString(1));
            }

            log.info("User saved: {}", user.getUserId());
            return user;
        } catch (SQLException e) {
            log.error("Error saving user to database: {}", e.getMessage());
            throw new RuntimeException("Error saving user to database", e);
        }
    }


    @Override
    @UpdateInCache
    public User update(User user) {
        try (var connection = ConnectionManager.get();
             var ps = connection.prepareStatement(UPDATE_USER, RETURN_GENERATED_KEYS)
        ) {
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getSurName());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getPhoneNumber());
            ps.setString(6, user.getUserId());
            ps.executeUpdate();

            log.info("User updated: {}", user.getUserId());
            return user;
        } catch (SQLException e) {
            log.error("Error updating user in database: {}", e.getMessage());
            throw new RuntimeException("Error updating user in database", e);
        }
    }

    @Override
    @FindInCache
    public Optional<User> findById(String id) {
        try (var connection = ConnectionManager.get();
             var ps = connection.prepareStatement(FIND_BY_ID)
        ) {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User user = MapperUser.getUser(rs);
                log.info("User found: {}", id);
                return Optional.of(user);
            }
            log.info("User not found: {}", id);
        } catch (SQLException e) {
            log.error("Error fetching user from database: {}", e.getMessage());
            throw new RuntimeException("Error updating user in database", e);
        }
        return Optional.empty();
    }

    @RemoveInCache
    @Override
    public boolean deleteById(String id) {
        try (var connection = ConnectionManager.get();
             var ps = connection.prepareStatement(DELETE_USER)
        ) {
            ps.setString(1, id);
            int rowsAffected = ps.executeUpdate();
            boolean isDeleted = rowsAffected > 0;
            if (isDeleted) {
                log.info("User deleted: {}", id);
            } else {
                log.info("User not found for deletion: {}", id);
            }
            return isDeleted;
        } catch (SQLException e) {
            log.error("Error deleting user from database: {}", e.getMessage());
            throw new RuntimeException("Error deleting user from database", e);
        }
    }

    @Override
    public List<User> findAll() {
        try (var connection = ConnectionManager.get();
             var statement = connection.createStatement()
        ) {
            ResultSet rs = statement.executeQuery(FIND_ALL);
            List<User> userList = new ArrayList<>();
            while (rs.next()) {
                User user = MapperUser.getUser(rs);
                userList.add(user);
            }
            log.info("Found {} users", userList.size());
            return userList;
        } catch (SQLException e) {
            log.error("Error finding all users: {}", e.getMessage());
            throw new RuntimeException("Error finding all users", e);
        }
    }
}
