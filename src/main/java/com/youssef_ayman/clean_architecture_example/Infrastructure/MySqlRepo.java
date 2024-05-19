package com.youssef_ayman.clean_architecture_example.Infrastructure;

import com.youssef_ayman.clean_architecture_example.Domain.Entities.User;
import com.youssef_ayman.clean_architecture_example.Domain.Repositories.UserRepo;

import java.sql.*;

public class MySqlRepo implements UserRepo {
    private final String jdbcUrl = "jdbc:mysql://localhost:3307/userdb";
    private final String username = "user";
    private final String password = "user_password";

    private static String sqlQuery;

    public MySqlRepo() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(User user) {
        try(Connection connection= DriverManager.getConnection(jdbcUrl,username, password)){
            String sqlQuery = "INSERT INTO users (name, age, job) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sqlQuery);

            statement.setString(1, user.getName());
            statement.setInt(2, user.getAge());
            statement.setString(3, user.getJob());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User findByName(String name) {
        User user = null;
        try(Connection connection= DriverManager.getConnection(jdbcUrl,username, password)) {

            String sqlQuery = "SELECT * from users where name=?";
            PreparedStatement statement = connection.prepareStatement(sqlQuery);

            statement.setString(1, name);

            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){
                user = new User(resultSet.getString("name"),
                        resultSet.getInt("age"),
                        resultSet.getString("job"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return user;
    }
}
