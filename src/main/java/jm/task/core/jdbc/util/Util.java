package jm.task.core.jdbc.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Util {
    private final Connection connection;
    private static volatile Util instance;
    private final String URL = "jdbc:mysql://localhost:3306/user";
    private final String USERNAME = "root";
    private final String PASSWORD = "1Qaz2Wsx3Edc!@#";


    private Util() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            connection.setAutoCommit(false);
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() {
        return connection;
        // реализуйте настройку соеденения с БД
    }
    public static Util getInstance() {
        if (instance == null) {
            Class var0 = Util.class;
            synchronized (Util.class){
                if (instance == null) {
                    instance = new Util();
                }
            }
        }
        return instance;
    }
}
