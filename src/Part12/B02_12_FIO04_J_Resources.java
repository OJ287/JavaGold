package Part12;

/**
 * @author liyanpeng
 * @date 2025/5/10
 * @description TODO
 */

/**
 * ✅ FIO04-J：不要になったリソースは解放する
 * ✅✅✅安全规范：使用try-with-resources
 * ✅✅✅如果是在finally里面使用close的话，记得先判定resources对象是不是null
 * ✅✅✅如果是在finally里面使用close的话，close本身的异常也要捕捉
 * ✅✅✅如果是在finally里面使用close，且有多个resources对象的话，后面的对象在前面对象的finally里面进行关闭
 * <p>
 * <p>
 * ■ 规则概要
 * 目的：防止文件描述符（File Descriptor）、数据库连接、Socket 等资源泄露或枯竭，从而导致程序崩溃或拒绝服务（DoS）。
 * <p>
 * Java 中的 I/O资源（如文件、网络连接、数据库连接）必须在使用完后显式关闭或释放。
 * 如果忽视释放，资源会长期占用系统资源，最终可能造成系统资源耗尽，产生严重问题。
 * <p>
 * ■ 攻击可能性 / 危険性
 * 資源が解放されない → ファイルやソケット数が限界に達する → 新たな接続やファイル操作ができない → DoS（サービス不能）
 * <p>
 * ■ 推奨：try-with-resources
 * <p>
 * ■ 易遗忘资源类型一览表
 * ┌──────────────────────────┬─────────────────────────────────────┐
 * │ リソース種別             │ 解放方法                           │
 * ├──────────────────────────┼─────────────────────────────────────┤
 * │ FileReader / FileWriter  │ close()                            │
 * │ InputStream / OutputStream│ close()                            │
 * │ JDBC Connection           │ close()                            │
 * │ prepareStatement          │ close()                            │
 * │ ResultSet / Statement     │ close()（须按层级顺序）             │
 * │ ZipFile                   │ close()                            │
 * │ Socket / ServerSocket     │ close()                            │
 * └──────────────────────────┴─────────────────────────────────────┘
 * <p>
 * ■ 释放资源的建议手法
 * ┌──────────────────────┬─────────────────────────────────────────────┐
 * │ 方法                 │ 内容                                        │
 * ├──────────────────────┼─────────────────────────────────────────────┤
 * │ try-with-resources    │ Java 7起标准做法，推荐所有 AutoCloseable 对象使用 │
 * │ try-finally           │ Java 6 及以前版本兼容用法                   │
 * │ 使用连接池管理资源    │ 如 HikariCP 管理 JDBC Connection            │
 * └──────────────────────┴─────────────────────────────────────────────┘
 * <p>
 * <p>
 * ■ 不良例（リソースを解放しない）
 * public void readConfig() {
 * BufferedReader br = new BufferedReader(new FileReader("config.properties"));
 * String line;
 * while ((line = br.readLine()) != null) {
 * System.out.println(line);
 * }
 * // br.close() が呼ばれず、ファイルハンドルが解放されない
 * }
 * 🔴 多次执行此方法，将导致系统文件句柄耗尽（特别是在 Linux/macOS 上 ulimit -n 限制存在时）。
 * <p>
 * ■ 改善例（try-finallyで確実に解放）
 * public void readConfig() {
 * BufferedReader br = null;
 * try {
 * br = new BufferedReader(new FileReader("config.properties"));
 * String line;
 * while ((line = br.readLine()) != null) {
 * System.out.println(line);
 * }
 * } catch (IOException e) {
 * System.err.println("読み込みエラー: " + e.getMessage());
 * } finally {
 * if (br != null) {
 * try {
 * br.close();
 * } catch (IOException e) {
 * System.err.println("クローズ失敗: " + e.getMessage());
 * }
 * }
 * }
 * }
 */
public class B02_12_FIO04_J_Resources {
    // 自动生成 main 方法
    public static void main(String[] args) {
        // TODO
    }
}