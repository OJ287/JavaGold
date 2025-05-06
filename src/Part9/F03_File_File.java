package Part9;

/**
 * @author liyanpeng
 * @date 2025/5/6
 * @description TODO
 */

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * java.nio.file.Files 类（注意是 Files 而不是 File，它在 java.nio.file 包中负责文件操作的工具类
 * 📁 java.nio.file.Files 的主要方法一览（补充版）
 * <p>
 * +--------------------------------------------------------------------------+----------------------------------------------+-----------------------------------------+--------------------------------------------------------------+
 * | 方法签名                                                                 | 参数说明                                     | 抛出异常                              | 说明                                                         |
 * +==========================================================================+==============================================+=========================================+==============================================================+
 * | static boolean exists(Path path, LinkOption... options)                  | path: 要检查的路径                           | -                                       | 判断文件或目录是否存在                                       |
 * | static boolean notExists(Path path, LinkOption... options)               | path: 要检查的路径                           | -                                       | 判断文件或目录是否不存在                                     |
 * | static boolean isSameFile(Path path1, Path path2)                       | path1, path2: 比较的路径                      | IOException                            | 判断两个路径是否指向同一文件                                 |
 * | static boolean isDirectory(Path path, LinkOption... options)            | path: 检查路径                               | -                                       | 是否为目录                                                   |
 * | static boolean isRegularFile(Path path, LinkOption... options)          | path: 检查路径                               | -                                       | 是否为普通文件                                               |
 * | static boolean isReadable(Path path)                                    | path: 检查路径                               | -                                       | 是否可读取                                                   |
 * | static boolean isWritable(Path path)                                    | path: 检查路径                               | -                                       | 是否可写入                                                   |
 * | static boolean isExecutable(Path path)                                  | path: 检查路径                               | -                                       | 是否为可执行文件                                             |
 * | static Path createDirectory(Path path, FileAttribute<?>... attrs)       | path: 新目录路径                             | IOException                            | 创建一个新目录                                               |
 * | static Path createDirectories(Path path, FileAttribute<?>... attrs)     | path: 新目录路径                             | IOException                            | 创建多层目录（必要时递归创建父目录）                         |
 * | static Path copy(Path src, Path dest, CopyOption... options)            | src, dest: 源/目标路径                        | IOException, FileAlreadyExistsException| 复制文件或目录                                               |
 * | static Path move(Path src, Path dest, CopyOption... options)            | src, dest: 源/目标路径                        | IOException                            | 移动或重命名文件或目录                                       |
 * | static long size(Path path)                                             | path: 文件路径                               | IOException                            | 获取文件大小（单位：字节）                                   |
 * | static void delete(Path path)                                           | path: 要删除的路径                           | IOException, NoSuchFileException       | 删除存在的文件或空目录                                             |
 * | static boolean deleteIfExists(Path path)                                | path: 要删除的路径                           | IOException                            | 删除文件或空目录（不存在时不抛异常）                         |
 * | static List<String> readAllLines(Path path, Charset cs)                 | path: 文件路径, cs: 字符集                    | IOException                            | 读取整个文本文件内容（逐行）                                 |
 * | static UserPrincipal getOwner(Path path, LinkOption... options)         | path: 文件路径                               | IOException                            | 获取文件所属用户（属主）                                     |
 * | static Object getAttribute(Path path, String attribute, LinkOption...)  | path: 路径, attribute: 属性字符串             | IOException                            | 获取单个文件属性（如 "basic:size"）                           |
 * | static void setAttribute(Path path, String attr, Object value, LinkOp...)| path, attr: 属性名, value: 值               | IOException                            | 设置单个文件属性                                             |
 * | static Map<String,Object> readAttributes(Path, String, LinkOption...)   | path, 属性字符串（如 "basic:*"）             | IOException                            | 获取多个属性（返回为 Map）                                   |
 * | static <A extends BasicFileAttributes> A readAttributes(Path, Class<A>,LinkOption... options)| path, 属性接口Class                          | IOException                            | 获取多个文件属性（类型安全版）                               |
 * | static DirectoryStream<Path> newDirectoryStream(Path dir)              | dir: 目录路径                                | IOException                            | 打开目录流，可遍历目录内容                                   |
 * <p>
 * 下面不存在于教科书
 * | static Path createFile(Path path, FileAttribute<?>... attrs)            | path: 新文件路径                             | IOException                            | 创建一个新文件                                               |
 * | static byte[] readAllBytes(Path path)                                   | path: 文件路径                               | IOException                            | 一次性读取整个文件内容为 byte[]                              |
 * | static Path write(Path path, byte[] bytes, OpenOption... options)       | path, bytes: 路径与内容                      | IOException                            | 将字节内容写入文件                                           |
 * | static Stream<Path> list(Path dir)                                      | dir: 目录路径                                | IOException                            | 获取目录下的文件列表（非递归）                               |
 * | static Stream<Path> walk(Path start, int maxDepth)                      | start: 起始路径, maxDepth: 最大深度          | IOException                            | 递归遍历目录结构（返回路径流）                               |
 * | static FileTime getLastModifiedTime(Path path, LinkOption... options)   | path: 文件路径                               | IOException                            | 获取文件的最后修改时间                                       |
 * +--------------------------------------------------------------------------+----------------------------------------------+-----------------------------------------+--------------------------------------------------------------+
 * <p>
 * <p>
 * 📥 Files.copy(...) 可用的 CopyOption（StandardCopyOption, LinkOption）
 * +--------------------------+--------------------------------------------------------------+
 * | 常量名                   | 说明                                                         |
 * +==========================+==============================================================+
 * | REPLACE_EXISTING         | 如果目标已存在，则覆盖它                                      |
 * | COPY_ATTRIBUTES          | 尽可能复制文件属性（如创建时间、权限等）                     |
 * | NOFOLLOW_LINKS           | 不跟随符号链接（复制符号链接本身，而不是其目标）             |
 * +--------------------------+--------------------------------------------------------------+
 * <p>
 * 📦 Files.move(...) 可用的 MoveOption（StandardCopyOption）
 * +--------------------------+--------------------------------------------------------------+
 * | 常量名                   | 说明                                                         |
 * +==========================+==============================================================+
 * | REPLACE_EXISTING         | 如果目标已存在，则覆盖它                                      |
 * | ATOMIC_MOVE              | 执行原子移动（失败则整个操作取消）                            |
 * | NOFOLLOW_LINKS           | 不跟随符号链接（移动符号链接本身，而非其目标）               |
 * +--------------------------+--------------------------------------------------------------+
 */
public class F03_File_File {
    // 自动生成 main 方法
    public static void main(String[] args) throws IOException {
        // TODO
        // 判断文件的属性的方法：如exists isReadable  isExecutable
        Path p1 = Paths.get("src/Part9/data_F_1.txt");
        Path p2 = Paths.get("/Users/liyanpeng/IdeaProjects/JavaGold/src/Part9/data_F_1.txt");
        System.out.format("exists       : %s%n", Files.exists(p1));
        try {
            System.out.format(
                    "isSameFile   : %s%n", Files.isSameFile(p1, p2));
        } catch (java.io.IOException e) {
        }
        System.out.format("isDirectory  : %s%n", Files.isDirectory(p1));
        System.out.format("isRegularFile: %s%n", Files.isRegularFile(p1));
        System.out.format("isReadable   : %s%n", Files.isReadable(p1));
        System.out.format("isExecutable : %s%n", Files.isExecutable(p1));

        // Path接口的toFile()和File类的toPath()还是可以File←->Path互相转换的
        File f1 = p1.toFile();
        Path f1p = f1.toPath();
        try {
            System.out.format(
                    "isSameFile   : %s%n", Files.isSameFile(p1, f1p));
        } catch (java.io.IOException e) {
        }

        /**
         * exists       : true
         * isSameFile   : true
         * isDirectory  : false
         * isRegularFile: true
         * isReadable   : true
         * isExecutable : false
         * isSameFile   : true
         */


        // 操作文件夹
//        Path p3 = Paths.get("src/Part9/ren");
//        Files.createDirectory(p3);
//        Path p4 = Paths.get("src/Part9/ren/tmp/x/y");
//        Files.createDirectories(p4);
//        Files.delete(p3);//delete只能删除空文件夹。因为src/Part9/ren文件夹下还有内容，还有别的文件夹tmp/x/y，所以不能删除。Exception in thread "main" java.nio.file.DirectoryNotEmptyException: src/Part9/ren
//        Files.deleteIfExists(Paths.get("ren/9_9"));


        // 移动文件
        Path p5 = Paths.get("src/Part9/data_F03_1.txt");
        Path p6 = Paths.get("src/Part9/data_F03_1_cp.txt");
        Path p7 = Paths.get("src/Part9/data_F03_2_org.txt");
        Path p8 = Paths.get("src/Part9/data_F03_2_mv.txt");
        Files.copy(p5, p6, StandardCopyOption.REPLACE_EXISTING);
        Files.move(p7, p8, StandardCopyOption.REPLACE_EXISTING);
        Files.delete(p5);

        /**
         * 🧭 什么是符号链接（Symbolic Link）？
         * 符号链接是 Linux 系统中一种特殊类型的文件，它指向另一个文件或目录的路径，有点类似于 Windows 中的快捷方式。
         *
         * 🧪 常用命令查看符号链接
         *    ls -l
         *
         * 会看到类似如下输出：
         *lrwxrwxrwx 1 user user   12 May  6 13:00 link.txt -> /tmp/data.txt
         * l 开头代表是链接文件，-> 显示其指向目标
         *
         *
         *🧭 Files.copy 操作符号链接时的注意事项
         * 注意点	                            说明
         * 默认行为（不指定第三参数）	            默认会复制链接本身（即符号链接文件），而不是它所指向的目标文件
         * 若希望复制符号链接所指向的目标文件	        需指定 LinkOption.NOFOLLOW_LINKS 以 不跟随符号链接（仅复制链接）
         * 复制到已存在的文件	                    需指定 StandardCopyOption.REPLACE_EXISTING 否则抛出异常
         * 复制目录包含符号链接时	                默认复制的是链接本身，不会递归复制链接指向的内容
         *
         *📌 示例：只复制链接本身
         *Path link = Paths.get("link_to_file.txt");
         * Path dest = Paths.get("copy_of_link.txt");
         * Files.copy(link, dest); // 默认只复制链接本身
         *
         *📌 示例：复制链接所指向的文件
         *Files.copy(link, dest, LinkOption.NOFOLLOW_LINKS); // 注意：NOFOLLOW_LINKS 是用于获取属性，不影响 copy 的行为！
         *⚠️ 实际想复制链接所指的目标文件，需要 不指定 NOFOLLOW_LINKS，即使用默认行为就会复制目标。
         *
         */
    }
}