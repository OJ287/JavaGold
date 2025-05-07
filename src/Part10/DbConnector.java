package Part10;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author liyanpeng
 * @date 2025/5/7
 * @description TODO
 */
public class DbConnector {
    // 自动生成 main 方法
    public static Connection getConnect() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/golddb?" +
                "verifyServerCertificate=false&" +
                "useSSL=false&" +
                "serverTimezone=Asia/Tokyo";
        String user = "liyp";
        String passwd = "rieh1111";
        Connection con = DriverManager.getConnection(url, user, passwd);
        return con;
    }
}