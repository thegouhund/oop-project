package com.project.dao;

import com.project.model.Passenger;
import com.project.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAO extends DAO<User> {

    public UserDAO(Connection connection) {
        super(connection);
    }

    @Override
    public User getById(int id) {
        User user;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM user WHERE id = ?");
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                user = new User(result.getString("email"), result.getString("username"), result.getString("password"), result.getBoolean("isAdmin"));
                user.setId(result.getInt("id"));
                return user;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public User login(String username, String password) {
        User user;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM user WHERE username = ? AND password = ?");
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                user = new User(result.getString("email"), result.getString("username"), result.getString("password"), result.getBoolean("isAdmin"));
                user.setId(result.getInt("id"));
                return user;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public ArrayList<User> getAll() {
        ArrayList<User> users = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM user");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                User user = new User(result.getString("email"), result.getString("username"), result.getString("password"), result.getBoolean("isAdmin"));
                user.setId(result.getInt("id"));
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    @Override
    public void update(User user, int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE user SET username = ?, password = ?, isAdmin = ? WHERE id = ?");
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setBoolean(3, user.isAdmin());
            statement.setInt(4, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE from user WHERE id = ?");
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void add(User user) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO user (email, username, password, isAdmin) VALUES (?, ?, ?, ?)");
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getUsername());
            statement.setString(3, user.getPassword());
            statement.setBoolean(4, user.isAdmin());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int size() {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) FROM user" );
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                return 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int addAndGetGeneratedId(User user) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO user (email, username, password, isAdmin) VALUES (?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getUsername());
            statement.setString(3, user.getPassword());
            statement.setBoolean(4, user.isAdmin());
            statement.executeUpdate();
            System.out.println(statement);

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            } else {
                throw new SQLException("Failed to retrieve auto-generated ID.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
