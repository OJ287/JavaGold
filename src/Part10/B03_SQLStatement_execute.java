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
 * execute()   public boolean execute() throws SQLException
 * ✅ PreparedStatement.execute() 的作用与返回值：
 * execute() 方法是 JDBC 中较为通用的方法，用于执行任意的 SQL 语句，它返回一个 boolean 类型的值，用来表示执行结果的类型。
 * <p>
 * <p>
 * 返回值说明：
 * | 返回值     | 含义                                                                                |
 * | ------- | --------------------------------------------------------------------------------- |
 * | `true`  | 执行的是一个 **`SELECT` 查询语句**，结果是一个 `ResultSet`，可通过 `getResultSet()` 获取                |
 * | `false` | 执行的是 **`INSERT`、`UPDATE`、`DELETE` 等更新语句**，结果不是结果集，可通过 `getUpdateCount()` 获取受影响的行数 |
 * <p>
 * ┌──────────────────────────────────────────────────────────────┬──────────────────────────────┬──────────────┬────────────────────────────────────────────────────────┐
 * │ 方法签名                                                     │ 参数说明                     │ 抛出异常     │ 说明                                                   │
 * ├──────────────────────────────────────────────────────────────┼──────────────────────────────┼──────────────┼────────────────────────────────────────────────────────┤
 * │ ResultSet getResultSet()                                     │ 无                            │ SQLException │ 在 execute() 返回 true 后，获取对应的结果集             │
 * ├──────────────────────────────────────────────────────────────┼──────────────────────────────┼──────────────┼────────────────────────────────────────────────────────┤
 * │ int getUpdateCount()                                         │ 无                            │ SQLException │ 在 execute() 返回 false 后，获取受影响的记录数          │
 * └──────────────────────────────────────────────────────────────┴──────────────────────────────┴──────────────┴────────────────────────────────────────────────────────┘
 * <p>
 * <p>
 * PreparedStatement の execute 系列メソッド 一覧
 * ┌────────────────────────────────────────────────────────────┬──────────────────────────────┬──────────────┬────────────────────────────────────────────────────────┐
 * │ 方法签名                                                   │ 返回值类型                   │ 抛出异常     │ 说明                                                   │
 * ├────────────────────────────────────────────────────────────┼──────────────────────────────┼──────────────┼────────────────────────────────────────────────────────┤
 * │ boolean execute()                                           │ boolean                       │ SQLException │ 执行任意 SQL（查询、更新等），返回 true 表示有结果集getResultSet   │
 * │                                                            │                              │              │ 返回 false 表示为更新语句，需用 getUpdateCount() 获取  │
 * ├────────────────────────────────────────────────────────────┼──────────────────────────────┼──────────────┼────────────────────────────────────────────────────────┤
 * │ ResultSet executeQuery()                                   │ ResultSet                     │ SQLException │ 专用于执行 SELECT 查询语句，返回查询结果集             │
 * │                                                            │                              │              │ 如果用于非查询语句，会抛出异常                          │
 * ├────────────────────────────────────────────────────────────┼──────────────────────────────┼──────────────┼────────────────────────────────────────────────────────┤
 * │ int executeUpdate()                                        │ int                           │ SQLException │ 执行 INSERT、UPDATE、DELETE 等更新语句，返回更新行数   │
 * │                                                            │                              │              │ 对于 DDL 语句（如 CREATE TABLE）可能返回 0             │
 * └────────────────────────────────────────────────────────────┴──────────────────────────────┴──────────────┴────────────────────────────────────────────────────────┘
 */

public class B03_SQLStatement_execute {
    // 自动生成 main 方法
    public static void main(String[] args) {
        // TODO
        String insertSQL = "INSERT INTO department VALUES " +
                "(7, 'Planning', 'Yokohama', '045-333-xxxx')";

        String selectSQL = "SELECT dept_name FROM department " +
                "WHERE dept_code = 2";
        try (Connection con = DbConnector.getConnect();
             PreparedStatement ins_pstmt = con.prepareStatement(insertSQL);
             PreparedStatement sel_pstmt = con.prepareStatement(selectSQL)) {
            disp(ins_pstmt);
            disp(sel_pstmt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void disp(PreparedStatement pstmt) throws SQLException {
        boolean isResultSet = pstmt.execute();
        if (isResultSet) {
            ResultSet rs = pstmt.getResultSet();
            if (rs.next()) {
                System.out.println("検索結果 : " + rs.getString(1));
            }
        } else {
            int count = pstmt.getUpdateCount();
            System.out.println("更新行数 : " + count);
        }
        /**
         * 更新行数 : 1
         * 検索結果 : Engineering
         */
    }
}