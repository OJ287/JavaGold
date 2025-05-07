package Part10;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author liyanpeng
 * @date 2025/5/7
 * @description TODO
 */
public class B02_SQLStatement_execute {
    // 自动生成 main 方法
    public static void main(String[] args) {
        // TODO
        /**
         * executeQuery
         */
        String sql = "SELECT dept_name FROM department " +
                "WHERE pilot_number =  ?";
        try (Connection con = DbConnector.getConnect();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
//            pstmt.setString(1, "092-222-xxxx");//第一次实行
            pstmt.setString(1, "1111");//第二次实行
            ResultSet rs = pstmt.executeQuery();
            if (rs != null) System.out.println("rs != null");
            if (rs.next()) {
                System.out.println("dept_name : " + rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        /**
         * 第一次实行
         * rs != null
         * dept_name : Marketing
         *
         * 第二次实行
         * rs != null
         *  哪怕检索结果是空，executeQuery()也是返回的空的ResultSet对象，而不是null
         *  rs.next()因为没有数据，所有返回false
         */


        /**
         * executeUpdate:Insert
         */
        String sql1 = "INSERT INTO department VALUES " +
                "(6, 'Planning', 'Yokohama', '045-333-xxxx')";
        try (Connection con = DbConnector.getConnect();
             PreparedStatement pstmt = con.prepareStatement(sql1)) {
//            int col = pstmt.executeUpdate();
//            System.out.println("col : " + col);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        /**
         * col : 1
         *
         * executeUpdate返回的是更新的行数
         * 再次实行的话，报错java.sql.SQLIntegrityConstraintViolationException: Duplicate entry '6' for key 'department.PRIMARY'
         * 因为dept_code=6刚才已经插入了，因为dept_code是主键，所以发生SQLException
         */


        /**
         * executeUpdate:Update
         */
        String sql2 = "UPDATE department set " +
                "dept_address='Tokyo', pilot_number='03-6666-xxxx' " +
                "where dept_code = ?";
        try (Connection con = DbConnector.getConnect();
             PreparedStatement pstmt = con.prepareStatement(sql2)) {
//            pstmt.setInt(1, 6);//第一次实行
            pstmt.setInt(1, 9);//第二次实行
            int col = pstmt.executeUpdate();
            System.out.println("col : " + col);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        /**
         * 第一次实行
         * col : 1
         *
         * 第二次实行
         * col : 0
         *
         * executeUpdate返回的是更新的行数
         */


        /**
         * executeUpdate:Delete
         */
        String sql3 = "DELETE FROM department " +
                "where dept_code = ?";
        try (Connection con = DbConnector.getConnect();
             PreparedStatement pstmt = con.prepareStatement(sql3)) {
//            pstmt.setInt(1, 6);//第一次实行
            pstmt.setInt(1, 8);//第二次实行
            int col = pstmt.executeUpdate();
            System.out.println("col : " + col);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        /**
         * 第一次实行
         * col : 1
         *
         * 第二次实行
         * col : 0
         *
         * executeUpdate返回的是更新的行数
         */

    }
}