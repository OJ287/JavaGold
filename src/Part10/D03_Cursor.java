package Part10;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author liyanpeng
 * @date 2025/5/9
 * @description TODO
 */

/**
 * ✅ absolute(int row) 的行为：
 * | 参数值            | 说明                                                                         |
 * | -------------- | -------------------------------------------------------------------------- |
 * | `> 总行数`        | 光标移动失败，返回 `false`，不抛异常。后续调用 `getXXX()` 会抛出 `SQLException`                  |
 * | `< -总行数`       | 同上，倒数位置无效，返回 `false`                                                       |
 * | `absolute(0)`  | **特殊情况**：光标移动到「第一行前的位置」（等同于 `beforeFirst()`）<br>返回 `false`，此时不能 `getXXX()` |
 * | `absolute(1)`  | 移动到第一行，返回 `true`                                                           |
 * | `absolute(-1)` | 移动到最后一行，返回 `true`                                                          |
 * <p>
 * ✅ relative(int rows) 的行为：
 * | 参数值           | 说明                                                |
 * | ------------- | ------------------------------------------------- |
 * | 超出正范围         | 移动失败，返回 `false`，不抛异常，`getXXX()` 会抛 `SQLException` |
 * | 超出负范围         | 同上                                                |
 * | `relative(0)` | 保持光标在当前位置不变，返回 `true`（如果当前在有效行上）<br>否则返回 `false`  |
 * | `relative(n)` | 若 `n` 合理，移动到目标行，返回 `true`                         |
 * <p>
 * <p>
 * ❗ getXXX() 抛出异常的条件：
 * 只要光标不在有效行上（如调用 absolute(100)、relative(-10)、或在 beforeFirst()、afterLast() 状态），
 * 一旦调用 getXXX() 方法访问数据，就会抛出：
 * java.sql.SQLException: Before start of result set
 * or
 * java.sql.SQLException: After end of result set
 */
public class D03_Cursor {
    // 自动生成 main 方法
    public static void main(String[] args) {
        // TODO
        String sql = "SELECT dept_code FROM department " +
                "ORDER BY dept_code";
        try (Connection con = DbConnector.getConnect();
             PreparedStatement pstmt = con.prepareStatement(
                     sql,
                     ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_UPDATABLE)) {
            ResultSet rs = pstmt.executeQuery();
            rs.absolute(1);
            System.out.print(rs.getString(1) + " ");
            rs.absolute(-1);
            System.out.print(rs.getString(1) + " ");
            rs.absolute(-2);
            System.out.println(rs.getString(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        /**
         * 1 8 5
         */
    }
}