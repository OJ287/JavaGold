package Part10;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author liyanpeng
 * @date 2025/5/7
 * @description TODO
 */

/**
 * SQLStatement_IN_Param
 * 对于占位符设置的项目值，之前使用了setInt，setString的基本数据类型
 * 但还有特殊的setObject，setNull方法
 * <p>
 * ❖ setObject(int parameterIndex, Object x)
 * PreparedStatement.setObject() 方法签名一览
 * ┌──────────────────────────────────────────────────────────────────────────────┬───────────────────────────────────────────────┬──────────────┬────────────────────────────────────────────────────────┐
 * │ 方法签名                                                                     │ 参数说明                                      │ 抛出异常     │ 说明                                                   │
 * ├──────────────────────────────────────────────────────────────────────────────┼───────────────────────────────────────────────┼──────────────┼────────────────────────────────────────────────────────┤
 * │ void setObject(int parameterIndex, Object x, int targetSqlType)              │ parameterIndex: 参数位置                       │ SQLException │ 显式指定对象的 SQL 类型（推荐）                       │
 * │                                                                              │ x: 对象值                                     │              │                                                        │
 * │                                                                              │ targetSqlType: SQL 类型常量                   │              │                                                        │
 * └──────────────────────────────────────────────────────────────────────────────┴───────────────────────────────────────────────┴──────────────┴────────────────────────────────────────────────────────┘
 * <p>
 * ❖ setNull(int parameterIndex, int sqlType)
 * PreparedStatement.setNull() 方法签名一览
 * ┌──────────────────────────────────────────────────────────────────────────────┬───────────────────────────────────────────────┬──────────────┬────────────────────────────────────────────────────────┐
 * │ 方法签名                                                                     │ 参数说明                                      │ 抛出异常     │ 说明                                                   │
 * ├──────────────────────────────────────────────────────────────────────────────┼───────────────────────────────────────────────┼──────────────┼────────────────────────────────────────────────────────┤
 * │ void setNull(int parameterIndex, int sqlType)                                │ parameterIndex: 参数位置                       │ SQLException │ 设定参数为 NULL（常用）                                │
 * │                                                                              │ sqlType: SQL 类型常量                         │              │                                                        │
 * └──────────────────────────────────────────────────────────────────────────────┴───────────────────────────────────────────────┴──────────────┴────────────────────────────────────────────────────────┘
 * <p>
 * <p>
 * ✅ 常用 java.sql.Types 类型一览：
 * Types.INTEGER      → 整数
 * Types.VARCHAR      → 字符串
 * Types.DOUBLE       → 双精度浮点
 * Types.DATE         → 日期（java.sql.Date）
 * Types.TIMESTAMP    → 时间戳（java.sql.Timestamp）
 * Types.BOOLEAN      → 布尔值
 * Types.NULL         → 未知类型的 NULL
 */
public class B04_SQLStatement_IN_Param {
    // 自动生成 main 方法
    public static void main(String[] args) {
        // TODO
        String sql = "INSERT INTO department VALUES " +
                "(?, ?, ?, ?)";
        Object pilot_number = null;//第一次实行
//        Object pilot_number = 1234;//第二次实行
        try (Connection con = DbConnector.getConnect();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, 8);
            pstmt.setString(2, "Support");
            pstmt.setString(3, "Miyagi");
            if (pilot_number == null) {
//                pstmt.setNull(4, java.sql.Types.NULL);
                // 上面的可以通过编译，但是实务上更建议使用下面，第二个参数不是要设置成的值，而是显示要处理字段的型→用setNull设置为Null
                pstmt.setNull(4, java.sql.Types.VARCHAR); // 指定字段原本的类型→setNull
            } else {
                pstmt.setObject(4, pilot_number, java.sql.Types.VARCHAR);
            }
            int col = pstmt.executeUpdate();
            System.out.println("col : " + col);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        /**
         * 第一次实行
         * col : 1
         * |         8 | Support     | Miyagi       | NULL         |
         *
         *第二次实行
         * col : 1
         * |         8 | Support     | Miyagi       | 1234         |
         */
    }
}