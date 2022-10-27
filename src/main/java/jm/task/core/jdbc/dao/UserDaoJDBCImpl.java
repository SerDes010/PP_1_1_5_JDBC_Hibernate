package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDaoJDBCImpl implements UserDao {
    Connection connection = Util.getInstance().getConnection();

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() throws SQLException {
        try {
            Statement statement = connection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS user (id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(45), lastName VARCHAR(45), age TINYINT)";
            statement.executeUpdate(sql);
            connection.commit();
        } catch (SQLException var1) {
            connection.rollback();
            throw new RuntimeException(var1);
        }
    }

    public void dropUsersTable() throws SQLException {

        try {
            Statement statement = connection.createStatement();
            String sql = "DROP TABLE IF EXISTS user";
            statement.execute(sql);
            connection.commit();
        } catch (SQLException var2) {
            connection.rollback();
            throw new RuntimeException(var2);
        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        String sql = "INSERT INTO user (name, lastName, age) VALUES(?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User с именем " + name + "успешно добавлен в таблицу");
            connection.commit();
        } catch (SQLException var3) {
            connection.rollback();
            throw new RuntimeException(var3);
        }
    }

    public void removeUserById(long id) throws SQLException {
        try {
            Statement statement = connection.createStatement();
            String sql = "DELETE FROM user WHERE id = id";
            statement.executeUpdate(sql);
            System.out.println("User под номером " + id + " успешно удалён");
            connection.commit();
        } catch (SQLException var4) {
            connection.rollback();
            throw new RuntimeException(var4);
        }
    }

    public List<User> getAllUsers() throws SQLException {
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
            connection.commit();
        } catch (SQLException var5) {
            connection.rollback();
            throw new RuntimeException(var5);
        }
        return users;
    }

    public void cleanUsersTable() throws SQLException {
        try {
            Statement statement = connection.createStatement();
            String sql = "TRUNCATE table user";
            statement.executeUpdate(sql);
            System.out.println("Таблица успешно очищена");
            connection.commit();
        } catch (SQLException var6) {
            connection.rollback();
            throw new RuntimeException(var6);
        }
    }
}
