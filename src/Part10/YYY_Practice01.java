package Part10;

/**
 * @author liyanpeng
 * @date 2025/5/9
 * @description TODO
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 答え：
 * 1:EF→DE
 * 2:E
 * 3:B→C
 * 4:ACD
 * 5:D
 * 6:B→E
 * 7:A→E
 * 8:C→E
 * 9:DE
 * 10:F
 * 11:A
 * 12:C
 * 13:E
 */
public class YYY_Practice01 {
    // 自动生成 main 方法
    public static void main(String[] args) {
        // TODO
        /**
         * 3
         * Connections是接口不能直接使用new
         * 要使用DriverManager.getConnection(url, user, passwd);
         */

//        String url = "jdbc:mysql://localhost:3306/golddb" +
//                "?verifyServerCertificate=false&" +
//                "useSSL=false&serverTimezone=JST" ;
//        String user = "liyp";
//        String pass = "ehri1111";
//        Connection con = new Connection(url, user, pass);
//        Statement stmt = con.createStatement();
//        String sql = "SELECT count(*) FROM department";
//        ResultSet rs = stmt.executeQuery(sql);
//        if(rs.next()) System.out.println(rs.getInt(1));

        /**
         * 6
         * DbConnector.getConnect()
         * con.prepareStatement
         * pstmt.executeUpdate()
         * 都会抛出SQLException。不catch或不在方法throws的话不行
         */
//        String sql = "UPDATE department set " +
//                "dept_address='Tokyo' where dept_code = 5 ";
//        try(Connection con = DbConnector.getConnect();
//            PreparedStatement pstmt = con.prepareStatement(sql)) {
//            int col = pstmt.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }

        /**
         * 7
         *             ResultSet rs = pstmt.executeQuery();
         *             int num = pstmt.executeUpdate(sql2);
         * 使用同一个PreparedStatement对象进行多次执行 executeQuery() 或 executeUpdate() 时，
         * 会自动关闭上一次生成的 ResultSet。
         * 因此，不应在 ResultSet 被使用期间重复执行 executeXXX() 方法，
         * 否则会导致 ResultSet 被关闭，从而触发 SQLException。
         *
         *
         * PreparedStatement的executeUpdate和executeQuery
         * 除了无参方法，还有有参的重载方法！！永远不要使用带参数的重载方法：！！
         * 2. 带参数的重载方法
         *executeQuery(String sql)（继承自Statement）
         *    ResultSet executeQuery(String sql) throws SQLException;
         *executeUpdate(String sql)（继承自Statement）
         *    int executeUpdate(String sql) throws SQLException;
         *
         */
        String sql1 = "SELECT count(*) FROM department";
        String sql2 = "INSERT INTO department " +
                "VALUES (13,'a','b', 'c')";
        try (Connection con = DbConnector.getConnect();
             PreparedStatement pstmt = con.prepareStatement(sql1)) {
            ResultSet rs = pstmt.executeQuery();
            int num = pstmt.executeUpdate(sql2);
            System.out.print(num + " ");
            if (rs.next()) System.out.println(rs.getString(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}