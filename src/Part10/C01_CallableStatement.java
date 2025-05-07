package Part10;

/**
 * @author liyanpeng
 * @date 2025/5/7
 * @description TODO
 */

/**
 * DELIMITER //
 * CREATE PROCEDURE myprocedure (IN value INT, OUT total INT)
 * BEGIN
 * SELECT SUM(field2) INTO total FROM mytableB WHERE field2 > value;
 * END//
 * DELIMITER ;
 * <p>
 * mysql> select * from mytableB;
 * +--------+--------+--------+
 * | field1 | field2 | field3 |
 * +--------+--------+--------+
 * |      1 | 100000 | TESTA  |
 * |      2 | 110000 | TESTB  |
 * |      3 | 120000 | TESTC  |
 * |      4 | 130000 | TESTD  |
 * +--------+--------+--------+
 * 4 rows in set (0.00 sec)
 */

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * ✅ 什么是 CallableStatement
 *      CallableStatement 是 JDBC 提供的用于调用数据库存储过程（Stored Procedure）的接口，继承自 PreparedStatement。
 *
 *  ✅ CallableStatement 的生成方式
 *  String sql = "{call stored_proc(?, ?, ?)}";
 *  CallableStatement cs = connection.prepareCall(sql);
 *          使用 Connection.prepareCall() 方法创建。
 *           {call stored_proc(?, ?, ?)} 是存储过程的调用语法，其中 ? 为参数占位符。
 *
 *✅ 参数类型
 * | 类型   | 说明                                                            |
 * | ---- | ------------------------------------------------------------- |
 * | 输入参数 | 通过 `setXXX(int parameterIndex, XXX value)` 指定                 |
 * | 输出参数 | 通过 `registerOutParameter(int parameterIndex, int sqlType)` 注册返回值的型 |
 * | 输入输出 | 先使用 `setXXX()` 设定初始值，再使用 `registerOutParameter()` 注册          |
 *
 *✅ CallableStatement 方法一览说明表
 * CallableStatement 主要方法一览
 * ┌──────────────────────────────────────────────────────────────────────┬──────────────────────────────────────┬──────────────┬────────────────────────────────────────────────────┐
 * │ 方法签名                                                             │ 参数说明                             │ 抛出异常     │ 说明                                               │
 * ├──────────────────────────────────────────────────────────────────────┼──────────────────────────────────────┼──────────────┼────────────────────────────────────────────────────┤
 * │ void registerOutParameter(int parameterIndex, int sqlType)          │ parameterIndex: 参数位置              │ SQLException  │ 注册输出参数类型，如 Types.INTEGER、Types.VARCHAR │
 * │                                                                      │ sqlType: SQL类型常量                 │              │                                                    │
 * ├───────────────────────────────────────────────────────────────────────┼────────────────────────────────────┼──────────────┼────────────────────────────────────────────────────────┤
 * │ void registerOutParameter(int parameterIndex, int sqlType, int scale) │ parameterIndex: 参数位置            │ SQLException │ 注册输出参数并指定小数位数（用于 DECIMAL, NUMERIC 类型）│
 * │                                                                       │ sqlType: SQL类型常量                │              │                                                        │
 * │                                                                       │ scale: 小数位数                     │              │                                                        │
 * ├──────────────────────────────────────────────────────────────────────┼─────────────────────────────────────┼──────────────┼────────────────────────────────────────────────────┤
 * │ void setXXX(int parameterIndex, XXX value)                           │ parameterIndex: 参数位置（从1开始）    │ SQLException │ 设置输入参数                                       │
 * │                                                                      │ value: 输入值                        │              │                                                    │
 * ├──────────────────────────────────────────────────────────────────────┼──────────────────────────────────────┼──────────────┼────────────────────────────────────────────────────┤
 * │ XXX getXXX(int parameterIndex)                                       │ parameterIndex: 参数位置             │ SQLException │ 获取输出参数的值（执行后调用）                     │
 * ├──────────────────────────────────────────────────────────────────────┼──────────────────────────────────────┼──────────────┼────────────────────────────────────────────────────┤
 * │ boolean execute()                                                    │ 无                                   │ SQLException │ 执行存储过程调用                                   │
 * └──────────────────────────────────────────────────────────────────────┴──────────────────────────────────────┴──────────────┴────────────────────────────────────────────────────┘
 */
public class C01_CallableStatement {
    // 自动生成 main 方法
    public static void main(String[] args) {
        // TODO
        String csql = "{call myprocedure(?, ?)}";
        try (Connection con = DbConnector.getConnect();
             CallableStatement cstmt = con.prepareCall(csql)) {
            cstmt.setInt(1, 110000);
            cstmt.registerOutParameter(2, java.sql.Types.BIGINT);
            cstmt.execute();
            int result = cstmt.getInt(2);
            System.out.println(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        /**
         * 250000
         */
    }
}