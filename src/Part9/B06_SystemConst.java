package Part9;

/**
 * @author liyanpeng
 * @date 2025/5/5
 * @description TODO
 */

/**
 * 📌 System 类的主要静态常量（定数）一览表
 * +----------------------+-----------------------------+----------------------------------------------------+
 * | 定数名称             | 类型                        | 说明                                               |
 * +======================+=============================+====================================================+
 * | System.in            | InputStream                 | 标准输入流，通常关联到键盘输入                     |
 * | System.out           | PrintStream                 | 标准输出流，通常关联到控制台输出（打印）           |
 * | System.err           | PrintStream                 | 标准错误输出流，通常也关联到控制台（显示错误信息） |
 * +----------------------+-----------------------------+----------------------------------------------------+
 * <p>
 * <p>
 * 🔍 补充说明：
 * 这三个是 System 类中唯一的静态字段（变量），也是最常用的定数。
 * 它们默认在程序启动时就已初始化，不需要开发者手动创建。
 * System.out 和 System.err 虽然都是 java.io.PrintStream 类型，但可以通过 System.setOut()、System.setErr() 重定向。
 * <p>
 * ✅in：
 * new BufferedReader(new InputStreamReader(System.in))
 * 使用例：
 * BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
 * String line = reader.readLine();  // 用户输入一行，按 Enter 后读取
 * 🧠 为什么要这么写？
 * System.in 是字节流，不能直接处理字符或字符串。
 * InputStreamReader 把字节流转为字符流。
 * BufferedReader 再包装它，提供高效的字符读取功能，支持按行读取（例如：读取整行用户输入）。
 * <p>
 * <p>
 * ✅out：
 * new PrintWriter(new FileOutputStream("my.txt"))
 * 是创建一个 向文件 my.txt 写入字符数据的 PrintWriter 实例。
 * 它内部通过 FileOutputStream 将数据写入文件，但 PrintWriter 是面向字符的（自动使用平台默认编码），适用于写字符串、字符、数字等数据。
 * <p>
 * 🔧 PrintWriter 的常用方法说明一览
 * +----------------------+--------------------------------------------------+
 * | 方法                 | 说明                                             |
 * +----------------------+--------------------------------------------------+
 * | write(String str)    | 写入字符串，不换行                                |
 * | append(CharSequence) | 追加字符序列，返回 PrintWriter 本身（链式调用）    |
 * | print(Xxx)           | 写入任意类型（int, double, String...），不换行     |
 * | println(Xxx)         | 写入任意类型并换行                                 |
 * | flush()              | 强制将缓冲区数据写出（但不关闭）                   |
 * | close()              | 刷新并关闭流，之后不可再写入                       |
 * +----------------------+--------------------------------------------------+
 * <p>
 * 🔁 write / print / println / append 方法区别
 * +-----------+----------+----------------------+-------------+----------------------------------------+
 * | 方法      | 是否换行 | 是否支持任意数据类型 | 是否链式调用 | 说明                                   |
 * +-----------+----------+----------------------+-------------+----------------------------------------+
 * | write     | 否       | 否（仅限字符串/字符）| 否          | 低层级方法，直接写入字符序列           |
 * | print     | 否       | 是                   | 否          | 自动将任意类型转换为字符串后写出       |
 * | println   | ✅ 是     | 是                   | 否          | 自动换行（写出后追加换行符）           |
 * | append    | 否       | 否（仅限CharSequence）| ✅ 是       | 追加字符序列，返回 PrintWriter 实例    |
 * +-----------+----------+----------------------+-------------+----------------------------------------+
 * <p>
 * <p>
 * ⚠️ PrintWriter 默认何时 flush 一览
 * +----------------------------------------------+----------------+
 * | 情况                                         | 是否自动 flush |
 * +----------------------------------------------+----------------+
 * | 使用构造函数传入 autoFlush = true 且调用     |                |
 * | println / printf / format 方法               | ✅ 是           |
 * +----------------------------------------------+----------------+
 * | 手动调用 flush()  （强制刷新）                  | ✅ 是           |
 * +----------------------------------------------+----------------+
 * | 调用 close()    （强制刷新）                   | ✅ 是           |
 * +----------------------------------------------+----------------+
 * | 使用 write() 或 print()                      | ❌ 否           |
 * +----------------------------------------------+----------------+
 * autoFlush=true 的实际作用范围：println / printf / format 方法
 * 不会对以下方法自动刷新：write() 、 print()
 * 设计原因：
 * write() 是底层基础写入方法，性能优先
 * println() 常用于交互式输出，需要即时可见性
 * 避免频繁 I/O 操作影响性能
 */

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class B06_SystemConst {
    // 自动生成 main 方法
    public static void main(String[] args) {
        // TODO

        try {
//            PrintWriter pw = new PrintWriter(new FileOutputStream("src/Part9/data_B06_1.txt"));
            PrintWriter pw = new PrintWriter(new FileOutputStream("src/Part9/data_B06_1.txt"), true);

            // write 方法：直接写字符串
            pw.write("Hello");

            // print 方法：写任意类型（不换行）
            pw.print(123);
            pw.print("ABC");

            // println 方法：写任意类型并换行
            pw.println();  // 先换行
            pw.println("Line with println");
            pw.println(3.14);

            // append 方法：追加字符串（支持链式）
            pw.append("追加内容").append(" -> next");

            // flush 方法：立即写入文件（非必须）
            pw.flush();

            // close 方法：关闭流并刷新内容
            pw.close();

            System.out.println("数据已写入 my.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}