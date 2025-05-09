package Part9;

/**
 * @author liyanpeng
 * @date 2025/5/4
 * @description TODO
 */

/**
 * +----------------------------------------------------------+--------------------------------------------------------------+------------------------------+
 * | 构造方法 / 方法                                           | 说明                                                         | 抛出异常                     |
 * +==========================================================+==============================================================+==============================+
 * | FileInputStream(File file)                               | 通过 File 对象创建输入流                                     | FileNotFoundException        |
 * +----------------------------------------------------------+--------------------------------------------------------------+------------------------------+
 * | FileInputStream(String name)                             | 通过文件路径字符串创建输入流                                 | FileNotFoundException        |
 * +----------------------------------------------------------+--------------------------------------------------------------+------------------------------+
 * | FileOutputStream(File file)                              | 通过 File 对象创建输出流（会覆盖已有文件）                   | FileNotFoundException        |
 * +----------------------------------------------------------+--------------------------------------------------------------+------------------------------+
 * | FileOutputStream(File file, boolean append)              | 通过 File 对象创建输出流，append=true 时追加内容             | FileNotFoundException        |
 * +----------------------------------------------------------+--------------------------------------------------------------+------------------------------+
 * | FileOutputStream(String name)                            | 通过文件路径字符串创建输出流（会覆盖已有文件）               | FileNotFoundException        |
 * +----------------------------------------------------------+--------------------------------------------------------------+------------------------------+
 * | FileOutputStream(String name, boolean append)            | 通过路径字符串创建输出流，append=true 时追加内容             | FileNotFoundException        |
 * +----------------------------------------------------------+--------------------------------------------------------------+------------------------------+
 * | int read()                                               | 读取单个字节，返回值为 0–255，返回 -1 表示已到达文件末尾     | IOException                  |
 * +----------------------------------------------------------+--------------------------------------------------------------+------------------------------+
 * | void write(int b)                                        | 将指定字节的低 8 位写入输出流（一个byte=255以内是正常256以后不对了）| IOException                  |
 * +----------------------------------------------------------+--------------------------------------------------------------+------------------------------+
 * | void write(byte[] b)                                     | 将整个字节数组写入输出流                                     | IOException                  |
 * +----------------------------------------------------------+--------------------------------------------------------------+------------------------------+
 * | void close()                                             | 关闭流并释放系统资源                                         | IOException                  |
 * +----------------------------------------------------------+--------------------------------------------------------------+------------------------------+
 */

import java.io.*;

public class B02_FileInputOutputStream {
    // 自动生成 main 方法
    public static void main(String[] args) {
        // TODO
        FileOutputStream fos = null;
        FileInputStream fis = null;
        try {
            fos = new FileOutputStream(new File("src/Part9/data_B02_1.txt"));
            fos.write(0);
            fos.write("abc".getBytes());
            fos.write(99);
            fis = new FileInputStream(new File("src/Part9/data_B02_1.txt"));
            int data = 0;
            while ((data = fis.read()) != -1) {
                System.out.print(data + " ");   // 読み込んだデータを表示. // 0 97 98 99 99
            }
            // 指针已经到最后，而且FileInputStream没有和RandomAccessFile一样设置指针，只能关闭旧的stream在重新打开new FileInputStream
//            System.out.println();
//            while((data = fis.read()) != -1){
//                System.out.print((char) data); //  abcc(<空字符>a b c c)
//            }
        } catch (FileNotFoundException e) {
            System.err.println("ファイルがありません");
        } catch (IOException e) {
            System.err.println("IO Error");
        } finally {
            try {
                fos.close();
                fis.close();
            } catch (IOException e) {
            }
        }


        // 使用 try-with-resources
        try (FileOutputStream fos1 =
                     new FileOutputStream(new File("src/Part9/data_B02_2.txt"));
             FileInputStream fis1 =
                     new FileInputStream(new File("src/Part9/data_B02_2.txt"))) {
            fos1.write(0);
            fos1.write("abc".getBytes());
            fos1.write(99);
            int data = 0;
            while ((data = fis1.read()) != -1) {
                // 読み込んだデータの表示
                System.out.print(data + " ");
            }
        } catch (FileNotFoundException e) {
            System.err.println(" ファイルがありません");
        } catch (IOException e) {
            System.err.println("IO Error");
        }
    }
}