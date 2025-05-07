package Part10;

import java.sql.*;

/**
 * @author liyanpeng
 * @date 2025/5/7
 * @description TODO
 */

/**
 * java.sql.Data  java.time.LocalDate  转换方法
 * ┌─────────────────────────────┬──────────────────────────────┬────────────────────────────────────────────┬───────────────────────────────────────────────┐
 * │       传统类型（java.sql）    │        现代类型（java.time）   │            从 java.sql 转换为 java.time     │              从 java.time 转换为 java.sql       │
 * ├─────────────────────────────┼──────────────────────────────┼────────────────────────────────────────────┼───────────────────────────────────────────────┤
 * │ java.sql.Date               │ java.time.LocalDate          │ Date.toLocalDate()                         │ Date.valueOf(LocalDate)                        │
 * ├─────────────────────────────┼──────────────────────────────┼────────────────────────────────────────────┼───────────────────────────────────────────────┤
 * │ java.sql.Time               │ java.time.LocalTime          │ Time.toLocalTime()                         │ Time.valueOf(LocalTime)                        │
 * ├─────────────────────────────┼──────────────────────────────┼────────────────────────────────────────────┼───────────────────────────────────────────────┤
 * │ java.sql.Timestamp          │ java.time.LocalDateTime      │ Timestamp.toLocalDateTime()                │ Timestamp.valueOf(LocalDateTime)               │
 * │                             │ java.time.Instant            │ Timestamp.toInstant()                      │ Timestamp.from(Instant)                        │
 * └─────────────────────────────┴──────────────────────────────┴────────────────────────────────────────────┴───────────────────────────────────────────────┘
 */
public class A03_JDBC {
    // 自动生成 main 方法
    public static void main(String[] args) {
        // TODO
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "SELECT field1, field2, field3 FROM mytableA";
        try {
            String url = "jdbc:mysql://localhost:3306/golddb?" +
                    "verifyServerCertificate=false&" +
                    "useSSL=false&" +
                    "serverTimezone=Asia/Tokyo";
            con = DriverManager.getConnection(url, "liyp", "rieh1111");
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                java.sql.Date sqlDate = rs.getDate(1);
                java.time.LocalDate localDate = sqlDate.toLocalDate();
                System.out.println("localDate : " + localDate);

                java.sql.Time sqlTime = rs.getTime(2);
                java.time.LocalTime localTime = sqlTime.toLocalTime();
                System.out.println("localTime : " + localTime);

                java.sql.Timestamp timestamp = rs.getTimestamp(3);
                java.time.LocalDateTime localDateTime =
                        timestamp.toLocalDateTime();
                System.out.println("localDateTime : " + localDateTime);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        /**
         * localDate : 2020-10-30
         * localTime : 12:40
         * localDateTime : 2020-10-30T12:40
         */
    }
}