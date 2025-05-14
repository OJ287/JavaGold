package Part9;

/**
 * @author liyanpeng
 * @date 2025/5/4
 * @description TODO
 */

import java.io.*;

/**
 * 📌 DataInputStream / DataOutputStream 简介
 * DataInputStream 和 DataOutputStream 是 Java 提供的用于以平台无关方式读写基本数据类型的输入输出流类。
 * 它们通常包装在其他流（如 FileInputStream / FileOutputStream）外，用于读写 int, double, boolean, char, String 等类型，
 * 保证写入的数据可以按原始类型正确读出。
 * 这些类主要用于数据以二进制格式保存（非纯文本），适合于网络通信、文件存储等对数据精度与格式要求高的场景。到这里是教科书的内容
 * <p>
 * <p>
 * 📥 DataInputStream 主要构造函数 & 方法一览
 * +--------------------------------------+---------------------------------------------+------------------+------------------------+
 * | 构造函数/方法                         | 说明                                        | 返回值类型       | 抛出异常               |
 * +======================================+=============================================+==================+========================+
 * | DataInputStream(InputStream in)      | 用指定的输入流构造 DataInputStream          | -                | -                      |
 * +--------------------------------------+---------------------------------------------+------------------+------------------------+
 * | int readInt()                        | 读取 4 字节的 int                            | int              | IOException            |如果文件结束时，你尝试再读取一个字节，就会抛出 EOFException。
 * +--------------------------------------+---------------------------------------------+------------------+------------------------+
 * | String readUTF()                     | 读取使用 UTF-8 编码的字符串                  | String           | IOException            |如果文件结束时，你尝试再读取一个字节，就会抛出 EOFException。
 * +--------------------------------------+---------------------------------------------+------------------+------------------------+
 * <p>
 * ✅ DataOutputStream 主要构造函数 & 方法一览
 * +----------------------------------------+---------------------------------------------+------------------+------------------------+
 * | 构造函数/方法                           | 说明                                        | 返回值类型       | 抛出异常               |
 * +========================================+=============================================+==================+========================+
 * | DataOutputStream(OutputStream out)     | 用指定的输出流构造 DataOutputStream         | -                | -                      |
 * +----------------------------------------+---------------------------------------------+------------------+------------------------+
 * | void writeByte(int v)                  | 写入 1 字节                                 | void             | IOException            |
 * +----------------------------------------+---------------------------------------------+------------------+------------------------+
 * | void writeInt(int v)                   | 写入 4 字节的 int（正常int值）               | void             | IOException            |
 * +----------------------------------------+---------------------------------------------+------------------+------------------------+
 * | void writeUTF(String str)              | 写入一个使用 UTF-8 编码的字符串             | void             | IOException            |
 * +----------------------------------------+---------------------------------------------+------------------+------------------------+
 */
public class B03_DataInOutStream {
    // 自动生成 main 方法
    public static void main(String[] args) {
        // TODO
        try (DataOutputStream dos =
                     new DataOutputStream(new FileOutputStream("src/Part9/data_B03_1.txt"));
             DataInputStream dis =
                     new DataInputStream(new FileInputStream("src/Part9/data_B03_1.txt"))) {
            dos.writeInt(100);
            dos.writeUTF("tanaka");
            dos.writeUTF("田中");
            System.out.println(dis.readInt());
            System.out.println(dis.readUTF());
            System.out.println(dis.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }
        /**
         * 文件内容：   d  tanaka  田中
         * 100
         * tanaka
         * 田中
         */
        /**
         * 1. 为什么 dis.readUTF() 可以准确地读取单词 "tanaka"？
         * 在你写入数据时，使用了 dos.writeUTF("tanaka")。这个方法写入字符串的方式是先写入字符串的长度（2个字节，表示字符串长度的 short 类型值），然后写入字符串的 UTF-8 编码字节。具体过程如下：
         * writeUTF("tanaka") 会先写入字符串 "tanaka" 的长度信息（即字符串的字节长度，tanaka 的字节长度是 6，所以会先写入 6，占 2 个字节）。
         * 接着，写入 "tanaka" 的 UTF-8 字节内容。
         * 当你调用 dis.readUTF() 时，DataInputStream 会首先读取 2 个字节（字符串的长度信息），然后根据这个长度读取正确数量的字节（即 "tanaka" 的字节内容）。这样，它就能准确地读取出 "tanaka" 字符串。
         */
    }
}







