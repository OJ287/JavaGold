package Part9;

/**
 * @author liyanpeng
 * @date 2025/5/6
 * @description TODO
 */

import java.io.Console;
import java.io.PrintWriter;

/**
 * 📘 Console 类简介
 * java.io.Console 是用于与控制台进行交互的类，可以读取用户输入、读取密码（不回显），也可用于输出文本。
 * 它通常用于 命令行环境，在 IDE 中不一定可用。常用于需要交互的程序中，例如用户名密码验证。
 * 获取 Console 实例的方法：
 * Console console = System.console();
 * 若返回 null，表示当前环境不支持 Console（例如 IDE 环境）。
 * <p>
 * <p>
 * 📑 Console 主要方法一览
 * +--------------------------------------------------------------+--------------------------------------------------------------+
 * |                方法签名                                     |                       说明与返回值 / 异常                    |
 * +==============================================================+==============================================================+
 * | PrintWriter writer()                                         | 返回与此控制台关联的 PrintWriter  默认是自动 flush 只要调用 println() 或 printf() 就会自动刷新缓冲区。       |
 * |                                                              | 返回：PrintWriter（可用于更复杂输出）                            |
 * +--------------------------------------------------------------+--------------------------------------------------------------+
 * | Console format(String fmt, Object... args)                   | 向控制台输出格式化字符串（不换行）                          |
 * |                                                              | 返回：该 Console 对象（可链式调用）                         |
 * |                                                              | 抛出：IllegalFormatException, IOError                        |
 * +--------------------------------------------------------------+--------------------------------------------------------------+
 * | Console printf(String fmt, Object... args)                   | 向控制台输出格式化字符串（不换行），等同于 format           |
 * |                                                              | 返回：该 Console 对象                                       |
 * |                                                              | 抛出：IllegalFormatException, IOError                        |
 * +--------------------------------------------------------------+--------------------------------------------------------------+
 * | String readLine()                                            | 从控制台读取一行文本                                        |
 * |                                                              | 返回：输入的字符串                                          |
 * |                                                              | 抛出：IOError（输入输出错误）                               |
 * +--------------------------------------------------------------+--------------------------------------------------------------+
 * | String readLine(String fmt, Object... args)                  | 显示格式化提示信息，并读取用户输入的一行                     |
 * |                                                              | 返回：用户输入的字符串                                      |
 * |                                                              | 抛出：IllegalFormatException, IOError                        |
 * +--------------------------------------------------------------+--------------------------------------------------------------+
 * | char[] readPassword()                                        | 从控制台读取密码（不回显）                                  |
 * |                                                              | 返回：输入的密码字符数组                                    |
 * |                                                              | 抛出：IOError                                                |
 * +--------------------------------------------------------------+--------------------------------------------------------------+
 * | char[] readPassword(String fmt, Object... args)              | 显示格式化提示信息，读取密码（不回显）                      |
 * |                                                              | 返回：密码字符数组                                          |
 * |                                                              | 抛出：IllegalFormatException, IOError                        |
 * +--------------------------------------------------------------+--------------------------------------------------------------+以下不是教科书内容
 * | Reader reader()                                              | 返回与此控制台关联的 Reader                                  |
 * |                                                              | 返回：Reader（可用于更复杂读取）                            |
 * +--------------------------------------------------------------+--------------------------------------------------------------+
 * | void flush()                                                 | 刷新控制台输出缓冲区                                        |
 * +--------------------------------------------------------------+--------------------------------------------------------------+
 */
public class D01_Console {
    // 自动生成 main 方法
    public static void main(String[] args) {
        // TODO
        Console console = System.console();
        PrintWriter pw = console.writer();  //IDE实行，console是null：Exception in thread "main" java.lang.NullPointerException
        while (true) {
            String str = console.readLine();
            if (str.equals("")) {
                break;
            }
            pw.append(" 入力されたデータ : " + str + '\n');
            //pw.write(" 入力されたデータ : " + str + '\n');
            pw.flush();
        }


/**
 * 和System.in不一样的是。Console可以抑制エコーバック，输入密码的时候可以不显示
 */
        Console console1 = System.console();
        String name = console1.readLine("%s", "name : ");
        System.out.println("You are " + name);
        char[] pw1 = console1.readPassword("%s", "pw: ");
        System.out.print("Your password : ");
        for (char c : pw1)
            System.out.print(c);
    }
}









