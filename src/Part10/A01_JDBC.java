package Part10;

/**
 * @author liyanpeng
 * @date 2025/5/7
 * @description TODO
 */

/**
 * ✅ JDBC 是什么？
 * JDBC（Java Database Connectivity） 是 Java 提供的一套用于访问关系型数据库的 API（应用程序接口）。
 * JDBC 使得 Java 程序可以使用统一的方式连接各种数据库，执行 SQL 查询、更新、事务管理等操作。
 * <p>
 * 🎯 JDBC 的主要功能：
 * 连接数据库（如 MySQL、PostgreSQL、Oracle 等）
 * 执行 SQL 查询（SELECT、INSERT、UPDATE、DELETE）
 * 获取查询结果
 * 管理事务（commit / rollback）
 * 使用 PreparedStatement 进行预处理（防止 SQL 注入）
 * <p>
 * 🧱 JDBC 的基本组成类：
 * | 类 / 接口                            | 说明                |
 * | --------------------------------- | ----------------- |
 * | `DriverManager`                   | 用于加载数据库驱动，管理数据库连接 |
 * | `Connection`                      | 表示数据库连接对象         |
 * | `Statement` / `PreparedStatement` | 用于执行 SQL 语句       |
 * | `ResultSet`                       | 用于存储 SQL 查询结果     |
 * | `SQLException`                    | 用于捕捉数据库操作中发生的异常   |
 * <p>
 * <p>
 * 🧩 JDBC 驱动（JDBC Driver）是什么？
 * JDBC Driver 是数据库厂商提供的实现 JDBC 接口的类库，用于连接特定类型的数据库。
 * Java 本身并不内置所有数据库的驱动，因此你需要为你的数据库导入相应的 JDBC 驱动（JAR 包）。
 * <p>
 * 📦 常见 JDBC 驱动例子：
 * | 数据库类型      | JDBC 驱动 JAR 包（常用类）                            |
 * | ---------- | --------------------------------------------- |
 * | MySQL      | `com.mysql.cj.jdbc.Driver`（mysql-connector-j） |
 * | PostgreSQL | `org.postgresql.Driver`（postgresql-xx.jar）    |
 * | Oracle     | `oracle.jdbc.driver.OracleDriver`（ojdbc8.jar） |
 * | SQLite     | `org.sqlite.JDBC`（sqlite-jdbc.jar）            |
 */

import java.sql.*;

public class A01_JDBC {
    // 自动生成 main 方法
    public static void main(String[] args) {
        // TODO
        /**
         * 使用 JDBC 进行数据库连接和操作时，常用的主要接口和类的简要说明：
         *+----------------------------+-------------------------------------------+------------------------------------+---------------------------------------+
         * | 类/接口                     | 方法签名                                  | 参数说明                           | 返回值说明                            |
         * +----------------------------+-------------------------------------------+------------------------------------+---------------------------------------+
         * | java.sql.DriverManager       | static Connection getConnection(String url) | String url, String user, String password | 返回数据库连接对象                     |
         * +----------------------------+-------------------------------------------+------------------------------------+---------------------------------------+
         * | javax.sql.DataSource         | Connection getConnection()                | 无                                 | 返回一个数据库连接对象               |
         * +----------------------------+-------------------------------------------+------------------------------------+---------------------------------------+
         * | java.sql.Connection          | Statement createStatement()               | 无                                 | 返回一个Statement对象                |
         * |                             | void close()                              | 无                                 | 无返回值，关闭数据库连接               |
         * +----------------------------+-------------------------------------------+------------------------------------+---------------------------------------+
         * | java.sql.PreparedStatement   | ResultSet executeQuery()                  | 无                                 | 返回一个ResultSet对象                 |
         * |                             | int executeUpdate()                       | 无                                 | 返回更新影响的行数                    |
         * +----------------------------+-------------------------------------------+------------------------------------+---------------------------------------+
         * | java.sql.ResultSet           | boolean next()                            | 无                                 | 如果有更多行数据，返回true            |
         * |                             | String getString(int columnIndex)         | int columnIndex                    | 返回指定列的字符串值                  |
         * +----------------------------+-------------------------------------------+------------------------------------+---------------------------------------+
         * 以下不是教科书内容
         * | java.sql.ResultSetMetaData   | int getColumnCount()                      | 无                                 | 返回ResultSet中的列数                |
         * +----------------------------+-------------------------------------------+------------------------------------+---------------------------------------+
         * | java.sql.DataSource          | Connection getConnection()                | 无                                 | 返回一个数据库连接对象               |
         * +----------------------------+-------------------------------------------+------------------------------------+---------------------------------------+
         * | java.sql.Statement           | ResultSet executeQuery(String sql)        | String sql                         | 返回一个ResultSet对象，包含查询结果   |
         * |                             | int executeUpdate(String sql)             | String sql                         | 返回更新影响的行数                    |
         * +----------------------------+-------------------------------------------+------------------------------------+---------------------------------------+
         * | java.sql.SQLException         | String getMessage()                       | 无                                 | 返回异常的错误信息                    |
         * +----------------------------+-------------------------------------------+------------------------------------+---------------------------------------+
         * | java.sql.Driver              | boolean acceptsURL(String url)            | String url                         | 返回是否接受该URL                     |
         * +----------------------------+-------------------------------------------+------------------------------------+---------------------------------------+
         * | java.sql.CallableStatement   | ResultSet executeQuery()                  | 无                                 | 返回一个ResultSet对象                 |
         * +----------------------------+-------------------------------------------+------------------------------------+---------------------------------------+
         * | java.sql.DatabaseMetaData    | String getDatabaseProductName()           | 无                                 | 返回数据库的产品名称                 |
         * +----------------------------+-------------------------------------------+------------------------------------+---------------------------------------+
         *
         */
        /**
         * Connection的两个取得方法（需要用户名密码和不需要用户名密码）
         *DriverManager.getConnection() 方法签名一览
         * ┌────────────────────────────────────────────────────────────────────────────┬─────────────────────────────┬────────────────┬────────────────────────────────────────┐
         * │ 方法签名                                                                  │ 参数说明                    │ 抛出异常       │ 说明                                   │
         * ├────────────────────────────────────────────────────────────────────────────┼─────────────────────────────┼────────────────┼────────────────────────────────────────┤
         * │ public static Connection getConnection(String url)                        │ url: 数据库连接URL           │ SQLException   │ 使用默认配置连接数据库（不含用户名密码）│
         * │                                                                            │                             │                │ 一般不推荐使用                           │
         * ├────────────────────────────────────────────────────────────────────────────┼─────────────────────────────┼────────────────┼────────────────────────────────────────┤
         * │ public static Connection getConnection(String url, String user, String pwd)│ url: 数据库连接URL           │ SQLException   │ 通过显式用户名和密码连接数据库           │
         * │                                                                            │ user: 用户名                │                │                                        │
         * │                                                                            │ pwd: 密码                   │                │                                        │
         * └────────────────────────────────────────────────────────────────────────────┴─────────────────────────────┴────────────────┴────────────────────────────────────────┘
         */


        /**
         * mysql> select * from department;
         * +-----------+-------------+--------------+--------------+
         * | dept_code | dept_name   | dept_address | pilot_number |
         * +-----------+-------------+--------------+--------------+
         * |         1 | Sales       | Tokyo        | 03-3333-xxxx |
         * |         2 | Engineering | Yokohama     | 045-444-xxxx |
         * |         3 | Development | Osaka        | NULL         |
         * |         4 | Marketing   | Fukuoka      | 092-222-xxxx |
         * |         5 | Education   | Tokyo        | NULL         |
         * +-----------+-------------+--------------+--------------+
         * 5 rows in set (0.01 sec)
         */

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
            while (rs.next()) {
                System.out.println("dept_code : " + rs.getInt(1));
                System.out.println("dept_name : " + rs.getString(2));
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
         * dept_code : 1
         * dept_name : Sales
         * dept_code : 2
         * dept_name : Engineering
         * dept_code : 3
         * dept_name : Development
         * dept_code : 4
         * dept_name : Marketing
         * dept_code : 5
         * dept_name : Education
         */

    }
}