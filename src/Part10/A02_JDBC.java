package Part10;

/**
 * @author liyanpeng
 * @date 2025/5/7
 * @description TODO
 */

import java.sql.*;

/**
 * ResultSet 的 getXXX 方法和 Java 基本数据类型的对应关系一览表：
 * ┌────────────────────────────────────────┬────────────────────────────────┬────────────────────────────────────┬────────────────────────────────────┐
 * │ 方法签名                              │ 对应的 Java 基本数据类型        │ MySQL 数据类型                     │ 说明                               │
 * ├────────────────────────────────────────┼────────────────────────────────┼────────────────────────────────────┼────────────────────────────────────┤
 * │ public boolean getBoolean(int columnIndex)   │ boolean                        │ TINYINT(1)                        │ 获取布尔值                         │
 * │ public byte getByte(int columnIndex)         │ byte                           │ TINYINT                           │ 获取字节                           │
 * │ public short getShort(int columnIndex)       │ short                          │ SMALLINT                          │ 获取短整型                         │
 * │ public int getInt(int columnIndex)           │ int                            │ INT                               │ 获取整型                           │
 * │ public long getLong(int columnIndex)         │ long                           │ BIGINT                            │ 获取长整型                         │
 * │ public float getFloat(int columnIndex)       │ float                          │ FLOAT, DOUBLE                     │ 获取浮点数                         │
 * │ public double getDouble(int columnIndex)     │ double                         │ DOUBLE                            │ 获取双精度浮点数                   │
 * │ public BigDecimal getBigDecimal(int columnIndex) │ java.math.BigDecimal           │ DECIMAL, NUMERIC                  │ 获取大数值                         │
 * │ public String getString(int columnIndex)     │ String                         │ VARCHAR, TEXT, CHAR, TINYTEXT     │ 获取字符串                         │
 * │ public byte[] getBytes(int columnIndex)      │ byte[]                         │ BLOB, VARBINARY                   │ 获取字节数组                       │
 * │ public Date getDate(int columnIndex)         │ java.sql.Date                  │ DATE                              │ 获取日期                           │
 * │ public Time getTime(int columnIndex)         │ java.sql.Time                  │ TIME                              │ 获取时间                           │
 * │ public Timestamp getTimestamp(int columnIndex) │ java.sql.Timestamp             │ DATETIME, TIMESTAMP               │ 获取时间戳                         │
 * │ public Object getObject(int columnIndex)     │ Object                         │ 所有可支持的数据类型             │ 获取对象                           │
 * └────────────────────────────────────────┴────────────────────────────────┴────────────────────────────────────┴────────────────────────────────────┘
 */
public class A02_JDBC {
    // 自动生成 main 方法
    public static void main(String[] args) {
        // TODO
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "SELECT dept_code, dept_name FROM department";

        try {

            // ２ データベースの指定
            String url = "jdbc:mysql://localhost:3306/golddb?" +
                    "verifyServerCertificate=false&" +
                    "useSSL=false&" +
                    "serverTimezone=Asia/Tokyo";
            // ３ データベースとの接続
            con = DriverManager.getConnection(url, "liyp", "rieh1111");
            // ４ ステートメントの取得
            pstmt = con.prepareStatement(sql);
            // ５ SQL文の実行
            rs = pstmt.executeQuery();
            // ６ 結果の取得と処理
            Object dept_code_Object = null;
            Object dept_name_Object = null;
            int dept_code_int = 0;
            String dept_name_String = "";
            while (rs.next()) {
                dept_code_Object = rs.getObject("dept_code");
                dept_name_Object = rs.getObject("dept_name");
                if (dept_code_Object instanceof Integer) {
                    dept_code_int = (Integer) dept_code_Object;
                    System.out.println("dept_code  :: " + dept_code_int);
                }
                if (dept_code_Object instanceof Integer) {
                    dept_name_String = (String) dept_name_Object;
                    System.out.println("dept_code  :: " + dept_name_String);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
            // ７ 接続のクローズ
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
         * dept_code  :: 1
         * dept_code  :: Sales
         * dept_code  :: 2
         * dept_code  :: Engineering
         * dept_code  :: 3
         * dept_code  :: Development
         * dept_code  :: 4
         * dept_code  :: Marketing
         * dept_code  :: 5
         * dept_code  :: Education
         */
    }
}