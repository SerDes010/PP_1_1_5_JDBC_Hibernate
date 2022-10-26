package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/user";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1Qaz2Wsx3Edc!@#";
    private static final Connection connection = Conn();

    private static Connection Conn() {
        try {
            System.out.println("заработало!!!");
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() {
        return connection;
        // реализуйте настройку соеденения с БД
    }
}
