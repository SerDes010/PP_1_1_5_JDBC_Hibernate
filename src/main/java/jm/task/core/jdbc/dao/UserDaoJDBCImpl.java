package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDaoJDBCImpl implements UserDao {
    Connection connection = Util.getConnection();

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try {
            Statement statement = connection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS user (id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(45), lastName VARCHAR(45), age TINYINT)";
            statement.executeUpdate(sql);
            System.out.println("Таблица users успешно создана!");
        } catch (SQLException e) {
            System.err.println("При создании таблицы произошла ошибка!!!");
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {

        try {
            Statement statement = connection.createStatement();
            String sql = "DROP TABLE IF EXISTS user";
            statement.execute(sql);
            System.out.println("Таблица успешно удалена!");
        } catch (SQLException e) {
            System.err.println("При удалении таблицы произошла ошибка!");
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO user (name, lastName, age) VALUES(?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User с именем " + name + "успешно добавлен в таблицу");
        } catch (SQLException e) {
            System.err.println("При создании user произошла ошибка!");
        }
    }

    public void removeUserById(long id) {
        try {
            Statement statement = connection.createStatement();
            String sql = "DELETE FROM user WHERE id = id";
            statement.executeUpdate(sql);
            System.out.println("User под номером " + id + " успешно удалён");
        } catch (SQLException e) {
            System.err.println(" При удалении user произошла ошибка!");
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM user";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            System.out.println("Таблица user:");
            while (resultSet.next()) {
                User classUser = new User();
                classUser.setName(resultSet.getString(2));
                classUser.setLastName(resultSet.getString(3));
                classUser.setAge(resultSet.getByte(4));
                users.add(classUser);
            }
            System.out.println(users);
        } catch (SQLException e) {
            System.err.println("При получении таблицы произошла ошибка!");
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        try {
            Statement statement = connection.createStatement();
            String sql = "TRUNCATE table user";
            statement.executeUpdate(sql);
            System.out.println("Таблица успешно очищена");
        } catch (SQLException e) {
            System.err.println("При очищении таблицы users произошла ошибка");
        }
    }
}
