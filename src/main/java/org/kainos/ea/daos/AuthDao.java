package org.kainos.ea.daos;

import org.kainos.ea.models.User;
import org.kainos.ea.requests.LoginRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthDao {
    public User getUser(final LoginRequest loginRequest) throws SQLException {
        try (Connection connection = DatabaseConnector.getConnection()) {
            String query =
                    "SELECT * FROM `User` where email = ? and password = ?;";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, loginRequest.getEmail());
            statement.setString(2, loginRequest.getPassword());

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                return new User(
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getInt("roleId")
                );
            }
        }
        return null;
    }
}
