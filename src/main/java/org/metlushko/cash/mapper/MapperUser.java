package org.metlushko.cash.mapper;

import lombok.experimental.UtilityClass;
import org.metlushko.cash.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Utility class for mapping {@link ResultSet} data to {@link User} entities.
 */
@UtilityClass
public class MapperUser {

    /**
     * Maps data from a {@link ResultSet} to a {@link User} entity.
     *
     * @param rs The ResultSet containing the data.
     * @return A User entity populated with data from the ResultSet.
     * @throws SQLException If a database access error occurs or this method is called on a closed result set.
     */

    public static User getUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setUserId(rs.getString(1));
        user.setFirstName(rs.getString(2));
        user.setLastName(rs.getString(3));
        user.setSurName(rs.getString(4));
        user.setEmail(rs.getString(5));
        user.setPhoneNumber(rs.getString(6));
        return user;
    }

}
