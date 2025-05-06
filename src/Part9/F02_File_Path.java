package Part9;

/**
 * @author liyanpeng
 * @date 2025/5/6
 * @description TODO
 */

/**
 * ✅Path接口的主要方法
 * 📁 Path 接口的主要方法一览
 * +--------------------------------------------+-----------------------------+----------------------+---------------------------------------------------+
 * | 方法签名                                   | 参数说明                    | 抛出异常             | 说明                                              |
 * +============================================+=============================+======================+===================================================+
 * | String toString()                          | 无                          | 无                   | 返回此路径的字符串表示形式                        |
 * +--------------------------------------------+-----------------------------+----------------------+---------------------------------------------------+
 * | Path getFileName()                         | 无                          | 无                   | 返回路径中表示文件名的部分                        |
 * +--------------------------------------------+-----------------------------+----------------------+---------------------------------------------------+
 * | Path getName(int index)                    | index: 索引（从 0 开始）    | IllegalArgumentException | 返回指定索引处的路径名称元素                   |
 * +--------------------------------------------+-----------------------------+----------------------+---------------------------------------------------+
 * | int getNameCount()                         | 无                          | 无                   | 返回路径名称元素的数量                            |
 * +--------------------------------------------+-----------------------------+----------------------+---------------------------------------------------+
 * | Path subpath(int beginIndex, int endIndex) | beginIndex, endIndex(不包含) 索引  | IllegalArgumentException | 提取路径的子路径                                |
 * +--------------------------------------------+-----------------------------+----------------------+---------------------------------------------------+
 * | Path getParent()                           | 无                          | 无                   | 返回父路径（不包含文件名部分）                    |
 * +--------------------------------------------+-----------------------------+----------------------+---------------------------------------------------+
 * | Path getRoot()                             | 无                          | 无                   | 返回路径的根路径部分                              |
 * +--------------------------------------------+-----------------------------+----------------------+---------------------------------------------------+
 * | Path normalize()                           | 无                          | 无                   | 规范化路径，去除 “.” 和 “..” 等冗余部分          |
 * +--------------------------------------------+-----------------------------+----------------------+---------------------------------------------------+
 * | URI toUri()                                | 无                          | 无                   | 将路径转换为 URI                                  |
 * +--------------------------------------------+-----------------------------+----------------------+---------------------------------------------------+
 * | boolean isAbsolute()                       | 无                          | 无                   | 判断路径是否为绝对路径                            |
 * +--------------------------------------------+-----------------------------+----------------------+---------------------------------------------------+
 * | Path toAbsolutePath()                      | 无                          | SecurityException    | 将相对路径转换为绝对路径                          |
 * +--------------------------------------------+-----------------------------+-----------------------------+-----------------------------------------------------------+
 * | Path toRealPath(LinkOption... options)     | 可选：是否跟随符号链接         | IOException, SecurityException | 返回实际文件系统中的规范路径   	文件不存在时会抛 IOException|
 * +--------------------------------------------+-----------------------------+----------------------+---------------------------------------------------+
 * | Path resolve(Path other)                   | other: 要连接的路径        | 无                   | 将当前路径与 other 路径拼接，other如果是绝对路径那么就会忽略调用对象路径，而直接返回引数路径other                       |
 * +--------------------------------------------+-----------------------------+----------------------+---------------------------------------------------+
 * | Path resolve(String other)                 | other: 字符串路径          | 无                   | 同上（字符串形式）                                |
 * +--------------------------------------------+-----------------------------+----------------------+---------------------------------------------------+
 * | Path relativize(Path other)                | other: 目标路径             | IllegalArgumentException | 获取从当前路径到 other 的相对路径             |
 * +--------------------------------------------+-----------------------------+----------------------+---------------------------------------------------+
 * | Iterator<Path> iterator()                  | 无                          | 无                          | 返回可用于遍历路径中每个名称元素的迭代器                |
 * +--------------------------------------------+-----------------------------+-----------------------------+-----------------------------------------------------------+
 * | boolean startsWith(Path other)             | other: 另一个路径           | 无                   | 判断路径是否以 other 开头                         |
 * +--------------------------------------------+-----------------------------+----------------------+---------------------------------------------------+
 * | boolean endsWith(Path other)               | other: 另一个路径           | 无                   | 判断路径是否以 other 结尾                         |
 * +--------------------------------------------+-----------------------------+----------------------+---------------------------------------------------+
 */

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

public class F02_File_Path {
    // 自动生成 main 方法
    public static void main(String[] args) {
        // TODO
        // macOS 路径设置（使用您的实际项目路径）
        Path path = Paths.get("/Users/liyanpeng/IdeaProjects/JavaGold/src/Part9/data_F_1.txt");

        // 路径信息分析
        System.out.format("toString    : %s%n", path);
        System.out.format("getFileName : %s%n", path.getFileName());
        System.out.format("getName(0)  : %s%n", path.getName(0));  // Users
        System.out.format("getNameCount: %d%n", path.getNameCount()); // 7
        System.out.format("getRoot     : %s%n", path.getRoot());    // /

        // 遍历父目录
        while ((path = path.getParent()) != null) {
            System.out.format("  getParent   : %s%n", path);
        }

        // 相对路径测试
        Path p = Paths.get("src/Part9/data_F_1.txt");
        System.out.format("getRoot     : %s%n", p.getRoot()); // null（相对路径无根目录）
        /**
         * toString    : /Users/liyanpeng/IdeaProjects/JavaGold/src/Part9/data_F_1.txt
         * getFileName : data_F_1.txt
         * getName(0)  : Users
         * getNameCount: 7
         * getRoot     : /  （绝对路径文件的话mac返回/，windows返回盘符 C:\ 或网络路径 \\）
         *   getParent   : /Users/liyanpeng/IdeaProjects/JavaGold/src/Part9
         *   getParent   : /Users/liyanpeng/IdeaProjects/JavaGold/src
         *   getParent   : /Users/liyanpeng/IdeaProjects/JavaGold
         *   getParent   : /Users/liyanpeng/IdeaProjects
         *   getParent   : /Users/liyanpeng
         *   getParent   : /Users
         *   getParent   : /
         * getRoot     : null     （相对路径的文件的话都返回null）
         */

        // subPath
        path = Paths.get("/Users/liyanpeng/IdeaProjects/JavaGold/src/Part9/data_F_1.txt");
        System.out.format("1-4     : %s%n", path.subpath(1, 4));
        System.out.format("0-2     : %s%n", path.subpath(0, 2));
        /**
         * 超过要素数或者开始结束是一样的时候，
         * 运行抛出异常Exception in thread "main" java.lang.IllegalArgumentException
         */
//        System.out.format("0-8     : %s%n", path6.subpath(0,8));
//        System.out.format("2-2     : %s%n", path6.subpath(2,2));
        /**
         * 1-4     : liyanpeng/IdeaProjects/JavaGold
         * 0-2     : Users/liyanpeng
         */

        // 文件路径相关的方法
        Path path1 = Paths.get("./src/Part9/data_F_1.txt");
        System.out.format("toString()     : %s%n", path1);
        System.out.format("normalize()    : %s%n", path1.normalize());
        System.out.format("toUri()        : %s%n", path1.toUri());
        System.out.format("isAbsolute     : %s%n", path1.isAbsolute());
        System.out.format(
                "toAbsolutePath : %s%n", path1.toAbsolutePath());
        Path path2 = null;
        try {
            path2 = path1.toRealPath();
            System.out.format("toRealPath()   : %s%n", path2);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }

        /**
         * toString()     : ./src/Part9/data_F_1.txt
         * normalize()    : src/Part9/data_F_1.txt
         * toUri()        : file:///Users/liyanpeng/IdeaProjects/JavaGold/./src/Part9/data_F_1.txt
         * isAbsolute     : false
         * toAbsolutePath : /Users/liyanpeng/IdeaProjects/JavaGold/./src/Part9/data_F_1.txt
         * toRealPath()   : /Users/liyanpeng/IdeaProjects/JavaGold/src/Part9/data_F_1.txt
         */
        /**
         * 📌 toAbsolutePath() vs toRealPath() 比较一览
         * +---------------------+-------------------------------+------------------------------------------------------+
         * | 方法名              | 是否解析符号链接              | 说明                                                 |
         * +=====================+===============================+======================================================+
         * | toAbsolutePath()    | ❌ 不解析符号链接              | 只做路径补全，将相对路径 → 当前工作目录下的绝对路径 |
         * +---------------------+-------------------------------+------------------------------------------------------+
         * | toRealPath()        | ✅ 解析符号链接（默认）        | 返回实际存在的、符号链接解析后的规范路径            |
         * |                     |                               | ✔ 会验证路径是否真实存在，路径不存在则抛异常        |
         * +---------------------+-------------------------------+------------------------------------------------------+
         *
         * app/
         * ├── real/
         * │   └── file.txt
         * └── link -> real  （符号链接）
         * Path p1 = Paths.get("link/file.txt");
         * System.out.println(p1.toAbsolutePath());
         * // 输出: /Users/li/app/link/file.txt   ← 只是补全路径，不验证文件存在
         *
         * System.out.println(p1.toRealPath());
         * // 输出: /Users/li/app/real/file.txt   ← 解析符号链接，验证存在性
         */

        // resole
        Path path4 = Paths.get("./src/Part9");
        Path path5 = Paths.get("X");// 不能是/X。  /X在mac中被认为是绝对路径。/ 相当于windows的盘符
        System.out.format("resolve : %s%n", path4.resolve(path5));
        Path path3 = Paths.get("/Users/liyanpeng/IdeaProjects/JavaGold");
        System.out.format("resolve : %s%n", path4.resolve(path3));//如果 path3 是绝对路径，比如 /Users/...，那么 resolve() 会直接返回 path3，忽略 path4。
        Path path6 = Paths.get("/Users/liyanpeng/IdeaProjects/JavaGold/src/Part9");
        Iterator<Path> iter = path6.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }
        /**
         * resolve : ./src/Part9/X
         * resolve : /Users/liyanpeng/IdeaProjects/JavaGold
         * Users
         * liyanpeng
         * IdeaProjects
         * JavaGold
         * src
         * Part9
         */

        // relativize
        Path p1 = Paths.get("src/Part9/X");
        Path p2 = Paths.get("src/Part9/Y");
        System.out.println("X → Y 相対パス : " + p1.relativize(p2));
        System.out.println("Y → X 相対パス : " + p2.relativize(p1));
        /**
         * X → Y 相対パス : ../Y
         * Y → X 相対パス : ../X
         */


    }
}


