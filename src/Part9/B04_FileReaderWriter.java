package Part9;

/**
 * @author liyanpeng
 * @date 2025/5/4
 * @description TODO
 */

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 📝 使用说明：
 * FileReader 和 FileWriter 是 字符流（Reader/Writer） 的子类，适合处理 文本数据，不是二进制。
 * 写入时，如果文件不存在，会自动创建；若存在则默认覆盖（除非使用 append=true）。
 * read() 方法读取的是单个字符（char），返回的是 int 类型（为了兼容 -1 作为 EOF 的标志）。
 * flush() 通常在写入结束但不立即关闭流时使用，确保数据写入文件。
 * <p>
 * <p>
 * 📘 FileReader 构造函数 & 方法一览（字符输入）
 * +------------------------------------------+-------------------------------+-----------------+--------------------------------------+
 * | 构造方法 / 方法                           | 返回值                        | 抛出异常        | 说明                                 |
 * +==========================================+===============================+=================+======================================+
 * | FileReader(File file)                    | -                             | FileNotFoundException | 通过 File 对象读取字符文件     |
 * | FileReader(String fileName)              | -                             | FileNotFoundException | 通过路径字符串读取字符文件     |
 * | int read()                               | int                           | IOException     | 读取单个字符，返回 Unicode 值或 -1    |
 * +------------------------------------------+-------------------------------+-----------------+--------------------------------------+
 * <p>
 * <p>
 * ✍️ FileWriter 构造函数 & 方法一览（字符输出）
 * +-----------------------------------------------+-------------------------------+-----------------+--------------------------------------+
 * | 构造方法 / 方法                                | 返回值                        | 抛出异常        | 说明                                 |
 * +================================================+===============================+=================+======================================+
 * | FileWriter(File file)                          | -                             | IOException     | 通过 File 对象创建写入器（覆盖模式）|
 * | FileWriter(String fileName)                    | -                             | IOException     | 通过路径字符串创建写入器（覆盖模式）|
 * | FileWriter(File file, boolean append)          | -                             | IOException     | 第二参数指定是否以追加方式写入      |
 * | FileWriter(String fileName, boolean append)    | -                             | IOException     | 同上，用路径字符串指定文件          |
 * | void write(String str)                         | void                          | IOException     | 写入一个字符串                       |
 * | void flush()                                   | void                          | IOException     | 刷新缓冲区，立即写出所有数据        |
 * +-----------------------------------------------+-------------------------------+-----------------+--------------------------------------+
 */
public class B04_FileReaderWriter {
    // 自动生成 main 方法
    public static void main(String[] args) {
        // TODO
        try (FileWriter fw = new FileWriter(new File("src/Part9/data_B04_1.txt"));
             FileReader fr = new FileReader(new File("src/Part9/data_B04_1.txt"))) {
            fw.write("田中");
            fw.flush();
            int i = 0;
            while ((i = fr.read()) != -1) {
                System.out.print((char) i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}