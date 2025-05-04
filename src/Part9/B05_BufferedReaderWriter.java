package Part9;

/**
 * @author liyanpeng
 * @date 2025/5/5
 * @description TODO
 */

import java.io.*;

/**
 * 🧾 BufferedReader / BufferedWriter 简介
 * BufferedReader：带缓冲的字符输入流，用于提高读取字符、数组、行的效率。适合逐行读取文本。
 * BufferedWriter：带缓冲的字符输出流，用于高效写入字符、数组、字符串。可以自动批量写入，减少磁盘操作。
 * 使用这两个类，可以避免频繁地进行磁盘或网络 IO，提升性能，尤其适用于处理大量文本数据。
 * <p>
 * <p>
 * 📥 BufferedReader 构造函数 & 方法一览
 * +--------------------------------------------+------------------+----------------------+-----------------------------------------------------+
 * | 构造方法 / 方法                             | 返回值           | 抛出异常               | 说明                                                  |
 * +============================================+==================+======================+=====================================================+
 * | BufferedReader(Reader in)                  | -                | -                    | 包装一个已有的 Reader                                |
 * | BufferedReader(Reader in, int sz)          | -                | -                    | 指定缓冲区大小                                       |
 * | int read()                                 | int              | IOException          | 读取单个字符，返回 Unicode 值或 -1                   |
 * | String readLine()                          | String           | IOException          | 读取一整行（不含换行符），到 EOF 返回 null           |
 * | void mark(int readAheadLimit)              | void             | IOException          | 标记当前位置，可用 reset() 回到该位置                |
 * | void reset()                               | void             | IOException          | 将流重置到上次 mark() 标记的位置                     |
 * | long skip(long n)                          | long             | IOException          | 跳过 n 个字符，返回实际跳过的字符数                 |
 * +--------------------------------------------+------------------+----------------------+-----------------------------------------------------+
 * <p>
 * <p>
 * ✍️ BufferedWriter 构造函数 & 方法一览
 * +---------------------------------------------+------------------+----------------------+-----------------------------------------------------+
 * | 构造方法 / 方法                              | 返回值           | 抛出异常               | 说明                                                  |
 * +=============================================+==================+======================+=====================================================+
 * | BufferedWriter(Writer out)                  | -                | -                    | 包装一个已有的 Writer                                |
 * | BufferedWriter(Writer out, int sz)          | -                | -                    | 指定缓冲区大小                                       |
 * | void write(String str)                      | void             | IOException          | 写入整个字符串                                       |
 * | void newLine()                              | void             | IOException          | 写入平台相关的换行符                                 |
 * | void flush()                                | void             | IOException          | 刷新缓冲区，立即写出数据                             |
 * +---------------------------------------------+------------------+----------------------+-----------------------------------------------------+
 */
public class B05_BufferedReaderWriter {
    // 自动生成 main 方法
    public static void main(String[] args) {
        // TODO
        try (BufferedWriter bw =
                     new BufferedWriter(new FileWriter("src/Part9/data_B05_1.txt"));
             BufferedReader br =
                     new BufferedReader(new FileReader("src/Part9/data_B05_1.txt"))) {
            bw.write("おはよう");
            bw.newLine();
            bw.write("こんにちは");
            bw.flush();
            String data = null;
            while ((data = br.readLine()) != null) {
                System.out.println(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        try (BufferedReader br =
                     new BufferedReader(new FileReader("src/Part9/data_B05_2.txt"))) {
            System.out.println(br.readLine());
            br.mark(256);
            System.out.println(br.readLine());
            System.out.println(br.readLine());
            br.reset();
            System.out.println(br.readLine());
            br.skip(2);
            System.out.println(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        /**
         * apple
         * orange
         * banana
         * orange
         * nana
         *
         *
         * mark()在FileInputStream不存在
         * 但是在InputStream里面有markSupported（true/false）
         */
    }
}