package Part12;

/**
 * @author liyanpeng
 * @date 2025/5/10
 * @description TODO
 */

import Part10.DbConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Java Gold 出题不会考非常深入的攻击构造，但会问你“哪段写法更安全”“如何防止注入”，白名单、标准API、不可变性、最小权限原则是答题关键。
 * ✅✅✅推荐多练带有陷阱代码的判断题，注重 PreparedStatement、try-with-resources、escape 处理方式。
 * ✅✅✅还有一般不要使用SimpleData，而是使用线程安全的DataTimeFormatter
 * <p>
 * <p>
 * <p>
 * 🔐 トピック：攻击可能性代码与防止例
 * ┌────────────┬────────────────────────────┬──────────────────────────────────────────┐
 * │ トピック   │ 攻击可能性代码例          │ 防止被攻击的开发例                         │
 * ├────────────┼────────────────────────────┼──────────────────────────────────────────┤
 * │ SQL注入    │ Statement stmt =           │ PreparedStatement pstmt =                │
 * │            │ stmt.execute("SELECT *...");│ pstmt.setString(1, userInput);           │
 * ├────────────┼────────────────────────────┼──────────────────────────────────────────┤
 * │ コマンド注入│ Runtime.getRuntime().exec()│ new ProcessBuilder(args).start();        │
 * │            │ with user input             │ 且先做白名单验证                           │
 * ├────────────┼────────────────────────────┼──────────────────────────────────────────┤
 * │ XSS        │ out.println(userInput);     │ out.println(escapeHtml(userInput));      │
 * ├────────────┼────────────────────────────┼──────────────────────────────────────────┤
 * │ 路径遍历   │ new File("/dir/" + input)  │ Paths.get("/dir").resolve(input).normalize│
 * ├────────────┼────────────────────────────┼──────────────────────────────────────────┤
 * │ 反序列化攻击│ ObjectInputStream.readObject│ 使用白名单类，避免反序列化非信任数据      │
 * ├────────────┼────────────────────────────┼──────────────────────────────────────────┤
 * │ 信息泄露   │ e.printStackTrace();        │ 记录到安全日志，返回通用错误信息           │
 * └────────────┴────────────────────────────┴──────────────────────────────────────────┘
 * <p>
 * <p>
 * 🧼 出入力检查与无害化
 * 📌 定义说明：
 * 入力检查（Input Validation）：验证用户输入是否符合系统预期（格式、类型、长度等）。
 * 数据无害化（Sanitization / Neutralization）：将潜在有害的数据转化为无害形式（如 <script> 转义等）。
 * <p>
 * ✅ 输入验证典型方式
 * ┌────────────────────────────┬────────────────────────────────────────────────┐
 * │ 验证方式                   │ 说明                                           │
 * ├────────────────────────────┼────────────────────────────────────────────────┤
 * │ 正则表达式                 │ userInput.matches("[a-zA-Z0-9]{4,12}")         │
 * ├────────────────────────────┼────────────────────────────────────────────────┤
 * │ 类型验证                   │ Integer.parseInt(), EmailValidator等          │
 * ├────────────────────────────┼────────────────────────────────────────────────┤
 * │ 範囲チェック（白名单方式）│ Arrays.asList("admin", "user").contains(input)│
 * └────────────────────────────┴────────────────────────────────────────────────┘
 * <p>
 * 🧽 无害化方法一览
 * ┌───────────────────────────────┬─────────────────────────────────────────────┐
 * │ 无害化方法                     │ 说明                                        │
 * ├───────────────────────────────┼─────────────────────────────────────────────┤
 * │ HTML Escaping                  │ Apache Commons Text: StringEscapeUtils      │
 * ├───────────────────────────────┼─────────────────────────────────────────────┤
 * │ SQL参数化处理                  │ PreparedStatement / MyBatis bind方式        │
 * ├───────────────────────────────┼─────────────────────────────────────────────┤
 * │ コマンド用エスケープ           │ ProcessBuilder 不直接拼接用户输入           │
 * ├───────────────────────────────┼─────────────────────────────────────────────┤
 * │ パス正規化                     │ Path#normalize(), Files#isDirectory 等确认   │
 * └───────────────────────────────┴─────────────────────────────────────────────┘
 * <p>
 * 防止SQL注入风险的方法
 * 1.入力值的妥当性进行检查。文字数，限制符号输入，数字等
 * 2.使用PreparedStatement的占位符进行设置参数
 */
public class A01_00_IDS00_J_SQL {
    // 自动生成 main 方法
    public static void main(String[] args) {
        // TODO
        /**
         * mysql> select * from user;
         * +---------+-----------+---------------+
         * | user_id | user_name | user_password |
         * +---------+-----------+---------------+
         * | T100    | hana      | hana123       |
         * | T200    | taro      | taro888       |
         * +---------+-----------+---------------+
         * 2 rows in set (0.00 sec)
         */
        /**
         * 如果用户界面输入user_id是T100' OR '1'='1的话
         * 而且不使用PreparedStatement而是直接使用sql拼接的话就会被注入
         * UPDATE user set user_password='hana1234' where user_id = ''
         * UPDATE user set user_password='hana1234' where user_id = 'T100' OR '1'='1'
         */
//        String sql = "UPDATE user set " +
//                "user_password=" + user_password +
//                "where user_id = " + user_id;

        String sql = "UPDATE user set " +
                "user_password= ? " +
                "where user_id = ?";

        try (Connection con = DbConnector.getConnect();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, "hana123");
            pstmt.setString(2, "T100");
//            pstmt.setString(1, "T100' OR '1'='1");//没问题，PreparedStatement会把T100' OR '1'='1认成一个字符串
            // 对里面的单引号转成两个单引号
            // UPDATE user SET user_password='hana1234' WHERE user_id = 'T100'' OR ''1''=''1'
            int col = pstmt.executeUpdate();
            System.out.println("col : " + col);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}