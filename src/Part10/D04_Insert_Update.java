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
 * 二、可更新的结果集（updatable）
 * 默认情况下，ResultSet 是只读的（read-only）和只能顺序读取的（forward-only），
 * 但你可以通过 Connection.prepareStatement() 的重载方法，
 * 生成可滚动 + 可更新的 ResultSet，从而直接对数据库记录进行更新、插入或删除操作。
 * <p>
 * 🔧 如何创建可更新的 ResultSet
 * String sql = "SELECT dept_code, dept_name FROM department";
 * PreparedStatement pstmt = con.prepareStatement(
 * sql,
 * ResultSet.TYPE_SCROLL_SENSITIVE,    // 支持滚动，能感知数据变更
 * ResultSet.CONCUR_UPDATABLE          // 允许更新结果集
 * );
 * ResultSet rs = pstmt.executeQuery();
 * <p>
 * <p>
 * ✅ ResultSet 接口用于更新的主要方法一览表
 * ┌─────────────────────────────────────────────┬────────────────────────────┬──────────────┬────────────────────────────────────────────┐
 * │ 方法签名                                   │ 参数说明                   │ 抛出异常     │ 说明                                         │
 * ├─────────────────────────────────────────────┼────────────────────────────┼──────────────┼────────────────────────────────────────────┤
 * │ void updateXXX(int columnIndex, value)      │ columnIndex: 列序号        │ SQLException │ 更新当前行的指定列的值，不立即反映到DB     │
 * │                                             │ value: 新的值               │              │ 必须后续调用 `updateRow()`                  │
 * ├─────────────────────────────────────────────┼────────────────────────────┼──────────────┼────────────────────────────────────────────┤
 * │ void updateXXX(String columnLabel, value)   │ columnLabel: 列名           │ SQLException │ 同上，按列名设定                            │
 * ├─────────────────────────────────────────────┼────────────────────────────┼──────────────┼────────────────────────────────────────────┤
 * │ void updateRow()                            │ 无                         │ SQLException │ 将当前行的修改同步到数据库                 │
 * ├─────────────────────────────────────────────┼────────────────────────────┼──────────────┼────────────────────────────────────────────┤
 * │ void moveToInsertRow()                      │ 无                         │ SQLException │ 移动到插入模式，准备调用 `updateXXX()` 插入值│
 * ├─────────────────────────────────────────────┼────────────────────────────┼──────────────┼────────────────────────────────────────────┤
 * │ void insertRow()                            │ 无                         │ SQLException │ 插入一行新记录到数据库                      │
 * ├─────────────────────────────────────────────┼────────────────────────────┼──────────────┼────────────────────────────────────────────┤
 * │ void deleteRow()                            │ 无                         │ SQLException │ 删除当前行                                  │
 * ├─────────────────────────────────────────────┼────────────────────────────┼──────────────┼────────────────────────────────────────────┤
 * │ void moveToCurrentRow()                     │ 无                         │ SQLException │ 返回到插入前的位置                         │
 * └─────────────────────────────────────────────┴────────────────────────────┴──────────────┴────────────────────────────────────────────┘
 * <p>
 * ✅ 使用示例
 * rs.absolute(2);  // 移动到第2行
 * rs.updateString("dept_name", "新名称");
 * rs.updateRow();  // 提交更新
 * <p>
 * rs.moveToInsertRow();  // 准备插入
 * rs.updateInt("dept_code", 999);
 * rs.updateString("dept_name", "新部门");
 * rs.insertRow();         // 插入到数据库
 * rs.moveToCurrentRow();  // 返回当前位置
 */
public class D04_Insert_Update {
    // 自动生成 main 方法
    public static void main(String[] args) {
        // TODO
        String sql = "SELECT dept_code, dept_address,dept_name,pilot_number FROM department " +
                "WHERE dept_code = 4";
        try (Connection con = DbConnector.getConnect();
             PreparedStatement pstmt = con.prepareStatement(
                     sql,
                     ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_UPDATABLE)) {
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) System.out.println(rs.getString(2));
            rs.updateString(2, "Chiba");
            rs.updateRow();


            rs.moveToInsertRow();  // 准备插入
            rs.updateInt("dept_code", 999);
            rs.updateString("dept_name", "新部门");
            rs.updateString("dept_address", "China");
            rs.updateString("pilot_number", "000-XXXX-XXXX");
            rs.insertRow();         // 插入到数据库
            rs.moveToCurrentRow();  // 返回当前位置

        } catch (SQLException e) {
            e.printStackTrace();
        }

        /**
         * Fukuoka
         */
    }
}