package Part12;

/**
 * @author liyanpeng
 * @date 2025/5/10
 * @description TODO
 */

/**
 * ✅ FIO02-J: ファイル関連エラーを検知し、処理する
 * ✅✅✅安全规范：使用 try-with-resources，尽可能捕捉全部异常进行处理，
 * ✅✅✅        参照下面的■ 文件错误处理方针
 * <p>
 * <p>
 * ■ 规则概要
 * 目的：确保文件处理中的异常不会被忽略，防止程序状态异常、数据泄露或服务中断。
 * <p>
 * Java 中的文件操作涉及诸多可能失败的场景（权限问题、路径不存在、磁盘空间不足等）。本规则要求在执行文件操作时：
 * 检测所有可能的异常（IOException 等）
 * 记录或处理这些异常，避免 silent failure无声的失败
 * <p>
 * <p>
 * ✅ 常见错误类型一览表
 * ┌────────────────────────────┬─────────────────────────────────────┐
 * │ エラー発生原因             │ 例                                 │
 * ├────────────────────────────┼─────────────────────────────────────┤
 * │ ファイル不存在             │ FileNotFoundException               │
 * │ アクセス権限がない         │ AccessDeniedException               │
 * │ ディスク容量不足           │ IOException（書き込み失敗）         │
 * │ ロック中/別プロセス利用中   │ FileSystemException                  │
 * │ 閉じられたストリーム操作   │ ClosedChannelException 等           │
 * └────────────────────────────┴─────────────────────────────────────┘
 * <p>
 * ■ 文件错误处理方针
 * ┌────────────────────────────┬────────────────────────────────────────────────┐
 * │ 方针                        │ 说明                                           │
 * ├────────────────────────────┼────────────────────────────────────────────────┤
 * │ 捕获所有 IOException        │ 包括其子类，确保不会直接泄漏到外层或终止程序  │
 * │ 日志记录                    │ 最好使用 logger 记录错误信息，便于追踪         │
 * │ 使用 try-with-resources     │ 自动释放资源，避免内存泄露和句柄泄漏            │
 * │ 对用户输入的路径做校验      │ 防止路径注入，确保不会操作未授权文件           │
 * └────────────────────────────┴────────────────────────────────────────────────┘
 * <p>
 * <p>
 * ✅还有File（实例化对象）.delete(),只有在没有权限删除的时候才会有SecurityException
 * 但如果用户输入了一个不存在的文件名的时候，方法只会返回一个false
 * 不会出现异常，就不能捕捉
 * 但是不会反馈到用户，到底删没删除
 * 所以就要使用if分歧，告诉用户删没删除。或输入日志
 * 只是针对File（实例化对象）.delete()的话，
 * 可以使用Files.delete()。如果不能删除的话会报IOException
 * Files.delete(file)
 * <p>
 * <p>
 * <p>
 * ■ 反例（错误示范）
 * public void writeToFile(String data) {
 * FileWriter writer = new FileWriter("/var/app/config.txt");
 * writer.write(data); // 没有捕获 IOException，若失败程序会异常终止
 * writer.close();
 * }
 * 上述代码如果目标文件不可写，程序将抛出未处理的 IOException，造成服务中断或未记录日志。
 * <p>
 * ■ 改善例（正确处理异常）
 * public void writeToFile(String data) {
 * try (FileWriter writer = new FileWriter("/var/app/config.txt")) {
 * writer.write(data);
 * } catch (IOException e) {
 * // 记录日志，并进行适当处理
 * System.err.println("ファイル書き込みに失敗: " + e.getMessage());
 * // 或抛出业务异常以告知上层
 * throw new RuntimeException("書き込みエラー", e);
 * }
 * }
 * <p>
 * ■ 推荐使用 try-with-resources（自动释放资源）
 */
public class B01_12_FIO02_J_FileError {
    // 自动生成 main 方法
    public static void main(String[] args) {
        // TODO
    }
}