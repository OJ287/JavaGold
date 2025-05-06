package Part9;

/**
 * @author liyanpeng
 * @date 2025/5/6
 * @description TODO
 */

import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 📘 Path 接口说明
 * Path 是 Java NIO (java.nio.file) 中的一个接口，用于表示文件路径（既可以是绝对路径，也可以是相对路径）。
 * 它取代了旧版 IO 中的 File 类，提供更现代、面向对象、平台无关的路径操作方式。
 * Path 是通过 Paths.get(...) 或 FileSystems.getDefault().getPath(...) 方法获取的，不能直接 new。
 * <p>
 * ✅ Path 获取方法一览表
 * 🧭 Path 的取得方式（来自 Paths 类）
 * +------------------------------------------------------------+-----------------------------------------+-------------------------------+-----------------------------------------------------+
 * | 方法签名                                                   | 参数说明                                | 抛出异常                      | 说明                                                |
 * +============================================================+=========================================+===============================+=====================================================+
 * | static Path get(String first, String... more)              | first: 起始路径，more: 其他路径片段      | IllegalArgumentException       | 从多个字符串拼接并转换为 Path 对象                   |
 * |                                                            |                                         | InvalidPathException           |                                                     |
 * +------------------------------------------------------------+-----------------------------------------+-------------------------------+-----------------------------------------------------+
 * | static Path get(URI uri)                                   | uri: URI 对象                           | InvalidPathException           | 将 URI 转换为 Path 对象（仅支持 "file" scheme）     |
 * +------------------------------------------------------------+-----------------------------------------+-------------------------------+-----------------------------------------------------+
 * <p>
 * <p>
 * 📌 FileSystems.getDefault() 方法
 * +---------------------------------------------+---------------------------+-------------+------------------------------------------------+
 * | 方法签名                                    | 参数说明                  | 抛出异常    | 说明                                           |
 * +=============================================+===========================+=============+================================================+
 * | static FileSystem getDefault()              | 无参数                    | 无           | 返回当前默认的 FileSystem 对象（通常是本地文件系统） |
 * +---------------------------------------------+---------------------------+-------------+------------------------------------------------+
 * <p>
 * <p>
 * 📌 FileSystem.getPath() 方法（需通过 FileSystems.getDefault() 获取 FileSystem 对象）
 * +-----------------------------------------------------+-----------------------------------------+------------------------+--------------------------------------------------+
 * | 方法签名                                            | 参数说明                                | 抛出异常               | 说明                                             |
 * +=====================================================+=========================================+========================+==================================================+
 * | Path getPath(String first, String... more)          | first: 起始路径，more: 其他路径片段      | InvalidPathException    | 从 FileSystem 中创建 Path 对象（等价于 Paths.get） |
 * +-----------------------------------------------------+-----------------------------------------+------------------------+--------------------------------------------------+
 */
public class F01_File_Path {
    // 自动生成 main 方法
    public static void main(String[] args) {
        // TODO
        /**
         * 使用Path类的get得到Path对象
         * 不会创建文件
         */
        // 相对路径
        Path path1 = Paths.get("data.txt");
        // 绝对路径：windows下使用¥
        Path path2 = Paths.get("C:\\sample\\chap9\\13\\data.txt");
        // 可变长引数，string型的文件夹名和文件名分别指定，将自动添加分隔符セパレータ
        Path path3 =
                Paths.get("C:", "sample", "chap9", "13", "data.txt");
        System.out.println(path1);
        System.out.println(path2);
        System.out.println(path3);


        /**
         * 使用FileSystems类的getDefault得到FileSystem对象后
         * 使用FileSystem类的getPath得到Path对象
         * 不会创建文件
         */
        FileSystem fs = FileSystems.getDefault();
        Path path4 = fs.getPath("data.txt");
        Path path5 = fs.getPath("C:\\sample\\chap9\\14\\data.txt");
        Path path6 =
                fs.getPath("C:", "sample", "chap9", "14", "data.txt");
        System.out.println(path4);
        System.out.println(path5);
        System.out.println(path6);
    }
}