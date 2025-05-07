package Part10;

/**
 * @author liyanpeng
 * @date 2025/5/7
 * @description TODO
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * SQLStatement
 * ┌─────────────────────────────┬────────────────────────────────────────────┐
 * │ 接口/类名                   │ 说明                                       │
 * ├─────────────────────────────┼────────────────────────────────────────────┤
 * │ java.sql.Statement          │ 最基本的 SQL 执行接口，用于执行静态 SQL   │
 * │                             │ 文，适合结构固定的 SQL                     │
 * ├─────────────────────────────┼────────────────────────────────────────────┤
 * │ java.sql.PreparedStatement  │ 预编译 SQL 接口，支持参数绑定，防止 SQL   │
 * │                             │ 注入，性能更优，推荐用于增删改查操作       │
 * ├─────────────────────────────┼────────────────────────────────────────────┤
 * │ java.sql.CallableStatement  │ 调用数据库存储过程用的接口                 │
 * │                             │ 通常用于复杂业务逻辑封装在数据库端         │
 * └─────────────────────────────┴────────────────────────────────────────────┘
 * <p>
 * ◆ PreparedStatement 简介
 * PreparedStatement 是 Statement 的子接口，主要用于执行参数化 SQL语句。与 Statement 相比有如下优势：
 * ✅ SQL 会被预编译，提高性能（尤其在重复执行时）
 * ✅ 可以防止 SQL 注入攻击
 * ✅ 使用 ? 占位符绑定参数，代码结构更清晰、可读性更高
 * <p>
 * <p>
 * ◆ PreparedStatement 的主要方法一览表
 * ┌────────────────────────────────────────────────────────────┬────────────────────────────┬──────────────┬──────────────────────────────────────────────┐
 * │ 方法签名                                                   │ 参数说明                   │ 抛出异常     │ 说明                                         │
 * ├────────────────────────────────────────────────────────────┼────────────────────────────┼──────────────┼──────────────────────────────────────────────┤
 * │ void setString(int parameterIndex, String value)           │ 参数位置、字符串值         │ SQLException │ 绑定字符串型参数                              │
 * ├────────────────────────────────────────────────────────────┼────────────────────────────┼──────────────┼──────────────────────────────────────────────┤
 * │ void setInt(int parameterIndex, int value)                 │ 参数位置、int值            │ SQLException │ 绑定整数型参数                                │
 * ├────────────────────────────────────────────────────────────┼────────────────────────────┼──────────────┼──────────────────────────────────────────────┤
 * │ void setDate(int parameterIndex, java.sql.Date value)      │ 参数位置、日期             │ SQLException │ 绑定 SQL 日期参数                             │
 * ├────────────────────────────────────────────────────────────┼────────────────────────────┼──────────────┼──────────────────────────────────────────────┤
 * │ ResultSet executeQuery()                                   │ 无                         │ SQLException │ 执行 SELECT 查询语句，返回结果集             │
 * ├────────────────────────────────────────────────────────────┼────────────────────────────┼──────────────┼──────────────────────────────────────────────┤
 * │ int executeUpdate()                                        │ 无                         │ SQLException │ 执行 INSERT、UPDATE 或 DELETE 语句           │
 * ├────────────────────────────────────────────────────────────┼────────────────────────────┼──────────────┼──────────────────────────────────────────────┤
 * │ boolean execute()                                          │ 无                         │ SQLException │ 可执行任意 SQL（SELECT、INSERT 等），         │
 * │                                                            │                            │              │ 返回是否有结果集（true: 有 SELECT 结果）     │
 * ├────────────────────────────────────────────────────────────┼────────────────────────────┼──────────────┼──────────────────────────────────────────────┤
 * │以下不在教科书
 * void setObject(int parameterIndex, Object value)           │ 参数位置、任意对象         │ SQLException │ 自动识别参数类型进行绑定                      │
 * ├────────────────────────────────────────────────────────────┼────────────────────────────┼──────────────┼──────────────────────────────────────────────┤
 * │ void clearParameters()                                     │ 无                         │ SQLException │ 清除当前绑定的参数，便于复用 PreparedStatement│
 * └────────────────────────────────────────────────────────────┴────────────────────────────┴──────────────┴──────────────────────────────────────────────┘
 */
public class B01_PreparedStatement {
    // 自动生成 main 方法
    public static void main(String[] args) {
        // TODO
        /**
         * 由于 ResultSet 没有放在 try-with-resources 语法中，它不会被自动关闭。但同时：
         * 当 PreparedStatement 被关闭时，相关联的 ResultSet 也会被关闭（这是 JDBC 驱动的规范行为）。
         * 所以 虽然你没显式 close()，但最终 rs 会随着 pstmt 的关闭而被间接关闭。
         *
         * | 是否自动关闭 ResultSet？ | 条件说明                                  |
         * | ----------------- | ------------------------------------- |
         * | 是                 | 如果它是通过 `PreparedStatement` 创建，并且后者关闭了 |
         * | 否                 | 如果是单独创建的，或者 `Statement` 没关闭           |
         */
        String sql = "SELECT dept_name FROM department " +
                "WHERE dept_code =  ?   OR  dept_code =  ?  ";
        try (Connection con = DbConnector.getConnect();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, 1);
            pstmt.setInt(2, 3);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("dept_name : " + rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        /**
         * dept_name : Sales
         * dept_name : Development
         */
    }
}