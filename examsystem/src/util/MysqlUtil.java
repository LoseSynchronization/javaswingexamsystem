package util;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author 失去同步
 */
public class MysqlUtil {
    private static final String URL = "jdbc:mysql://47.100.95.175/geek";
    private static final String USER = "student";
    private static final String PASSWORD = "465128451284651238456";

    private static Connection connection;

    private static void connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            JOptionPane.showMessageDialog(null, "连接数据库成功！");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            Util.exceptionDialog(e, null);
        }
    }

    public static Connection getConnection() {
        try {
            if (connection == null || !connection.isValid(2) || connection.isClosed()){
                connect();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Util.exceptionDialog(e, null);
        }
        return connection;
    }
}
