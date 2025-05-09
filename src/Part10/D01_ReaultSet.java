package Part10;

/**
 * @author liyanpeng
 * @date 2025/5/8
 * @description TODO
 */

/**
 * ✅ ResultSet 的扩展使用
 * 默认情况下，通过 Statement 或 PreparedStatement 创建的 ResultSet 是：
 * •	只读（read-only）
 * •	向前只能一行一行读取（forward-only）
 * 但其实，ResultSet 也支持以下更高级的模式：
 * <p>
 * <p>
 * 一、滚动访问（可前后滚动）
 * 通过创建支持滚动的 Statement 实例，可以让 ResultSet 支持以下方法：
 * •	previous()：向前一行
 * •	absolute(int row)：跳转到指定行
 * •	relative(int rows)：相对当前位置前进/后退若干行
 * •	first()、last()：跳转到首行或末行
 * <p>
 * 二、可更新的结果集（updatable）
 * 通过创建 ResultSet 类型为可更新时，你可以对其直接执行以下操作：
 * •	updateXXX()：修改当前行的字段值
 * •	insertRow()：插入新行
 * •	deleteRow()：删除当前行
 * •	updateRow()：提交当前行的更新
 * <p>
 * 三、如何开启这两种模式
 * Statement stmt = conn.createStatement(
 * ResultSet.TYPE_SCROLL_INSENSITIVE,
 * ResultSet.CONCUR_UPDATABLE
 * );
 * ResultSet rs = stmt.executeQuery("SELECT * FROM employee");
 * <p>
 * ✅ ResultSet 接口中的常量（定数）一览表
 * ResultSet 定数一览表
 * ┌──────────────────────────────────────────────┬────────────────────────────────────────┐
 * │ 定数名称                               　      │ 说明                                   │
 * ├──────────────────────────────────────────────┼────────────────────────────────────────┤
 * │ CONCUR_READ_ONLY                             │ 只读模式（默认）                       │
 * ├──────────────────────────────────────────────┼────────────────────────────────────────┤
 * │ CONCUR_UPDATABLE                             │ 可更新模式                             │
 * ├──────────────────────────────────────────────┼────────────────────────────────────────┤
 * │ TYPE_FORWARD_ONLY                            │ 只能从前往后读取（默认）               │
 * ├──────────────────────────────────────────────┼────────────────────────────────────────┤
 * │ TYPE_SCROLL_INSENSITIVE                      │ 可滚动，但不受数据库更改影响           │
 * ├──────────────────────────────────────────────┼────────────────────────────────────────┤
 * │ TYPE_SCROLL_SENSITIVE                        │ 可滚动，且能反映数据库变化             │
 * ├──────────────────────────────────────────────┼────────────────────────────────────────┤
 * │以下不在教科书
 * HOLD_CURSORS_OVER_COMMIT                     │ 提交事务后，保留游标（默认）           │
 * ├──────────────────────────────────────────────┼────────────────────────────────────────┤
 * │ CLOSE_CURSORS_AT_COMMIT                      │ 提交事务后，自动关闭游标               │
 * └──────────────────────────────────────────────┴────────────────────────────────────────┘
 * <p>
 * B01_PreparedStatement提到的生成PreparedStatement的方法
 * 第一参数是sql
 * 第二参数是：TYPE_FORWARD_ONLY，TYPE_SCROLL_INSENSITIVE，TYPE_SCROLL_SENSITIVE
 * 第三参数是：CONCUR_READ_ONLY，CONCUR_UPDATABLE
 * * │ PreparedStatement prepareStatement(String sql, int resultSetType,            │ sql：SQL文                                                   │ SQLException │ 创建带游标保留策略的滚动、更新结果集                                  │
 * * │ int resultSetConcurrency, int resultSetHoldability)                          │ resultSetConcurrency：并发类型                                │              │                                                                      │
 * * │                                                                              │ resultSetHoldability：游标提交时是否关闭                       │              │                                                                      │
 * *
 * 默认情况下，用 Connection.prepareStatement(sql) 创建的 PreparedStatement，生成的 ResultSet 是：
 * 只能向前移动（TYPE_FORWARD_ONLY）
 * 只读的（CONCUR_READ_ONLY）
 * <p>
 * ✅ DatabaseMetaData 的获取与使用
 * 一、如何获取 DatabaseMetaData
 * Connection conn = DriverManager.getConnection(url, user, password);
 * DatabaseMetaData metaData = conn.getMetaData();
 */
public class D01_ReaultSet {
    // 自动生成 main 方法
    public static void main(String[] args) {
        // TODO
    }
}