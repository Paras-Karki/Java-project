package dbmsconnector;

import java.sql.*;

public class DBUtils {
    static String dbHost = "localhost";
    static String dbPort = "3306";
    static String dbUser = "root";
    static String dbPass = "paras";
    static String dbName = "coursems";

    public static Connection getDbConnection() throws SQLException {
        String conString = "jdbc:mysql://"+dbHost + ":" + dbPort + "/" + dbName;

        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());

        return DriverManager.getConnection(conString,dbUser,dbPass);
    }
}
