package Part9;

import java.io.IOException;
import java.nio.file.*;
import java.util.stream.Collectors;

/**
 * @author liyanpeng
 * @date 2025/5/6
 * @description TODO
 */

/**
 * 方法签名                                                          参数说明                              抛出异常                    说明
 * ------------------------------------------------------------------ ------------------------------------- --------------------------- ----------------------------------------------------------
 * Stream<Path> walk(Path start, FileVisitOption... options)            start：起始路径，options：如 FOLLOW_LINKS   maxDepth不指定默认是Integer.MAX_VALUE      IOException     递归遍历目录，允许设置是否跟随符号链接
 * Stream<Path> walk(Path start, int maxDepth, FileVisitOption... options) start：起始路径，maxDepth：最大深度，options：遍历选项      IOException     最完整的形式，控制深度与符号链接等行为
 * Stream<Path> find(Path start, int maxDepth, BiPredicate<Path,BasicFileAttributes> matcher, FileVisitOption... options)
 * start：起始路径，maxDepth：深度       IOException                 根据条件过滤递归路径，返回匹配 Path 的 Stream
 * Stream<Path> list(Path dir)                                       dir：目录路径                         IOException                 返回指定目录中一层的文件/子目录 Path 的 Stream
 * Stream<String> lines(Path path)                                   path：要读取的文件路径                IOException                 以 Stream<String> 形式逐行读取文本文件（默认 UTF-8 编码）
 * Stream<String> lines(Path path, Charset cs)                       path：文件路径，cs：字符编码         IOException                 使用指定字符编码逐行读取文件
 * ------------------------------------------------------------------ ------------------------------------- --------------------------- ----------------------------------------------------------
 */
public class G01_Directory {
    // 自动生成 main 方法
    public static void main(String[] args) {
        // TODO
        // ディレクトリへのアクセス
        /**
         * FileSystem.getRootDirectories():取得root文件夹，windows上就是取得所有的盘符
         * Files.newDirectoryStream()：文件夹内所有的文件夹和文件（内容），不递归
         */
        FileSystem fs = FileSystems.getDefault();
        Iterable<Path> dirs = fs.getRootDirectories();
        for (Path name : dirs) {
            System.out.println("RootDirectories : " + name);
        }
        Path path = Paths.get("src/Part9");
        try (DirectoryStream<Path> stream =
                     Files.newDirectoryStream(path)) {
            for (Path file : stream) {
                System.out.println(file.getFileName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        /**
         * RootDirectories : /
         * "src/Part9"路径下所有的文件和文件夹，不递归
         */


        System.out.println("=============");
        //ファイルツリーの探索
        // walk, 递归检索
        Path path1 = Paths.get("src/Part9/ren");
        try {
            Files.walk(path1).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
        /**
         * src/Part9/ren
         * src/Part9/ren/ren.txt
         * src/Part9/ren/tmp
         * src/Part9/ren/tmp/x
         * src/Part9/ren/tmp/x/.DS_Store
         * src/Part9/ren/tmp/x/y
         * src/Part9/ren/tmp/x/y/ren.txt
         */


        System.out.println("=============");
        // walk,filter,是中间操作。返回Stream
        // 有filter的取得文件进行操作
        Path path2 = Paths.get("src/Part9/ren");
        try {
            Files.walk(path2)
                    .filter(s -> s.toString().endsWith(".jpg"))
                    .forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
        /**
         * 没有输出
         */


        System.out.println("=============");
        // list是中间操作。返回Stream
        // 取得指定文件夹下的所有文件夹
        // list()。不递归进行取得
        Path path3 = Paths.get("src/Part9/ren");
        try {
            Files.list(path3).forEach(System.out::println);
            System.out.println("--------------");
            Files.list(path3)
                    .filter(s -> !Files.isDirectory(s))
                    .forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
        /**
         * src/Part9/ren/ren.txt
         * src/Part9/ren/tmp
         * --------------
         * src/Part9/ren/ren.txt
         */


        System.out.println("=============");
        // find()。递归查询是以第二个参数10，查询的深度决定的
        /**
         * 参数名       类型                                                 说明
         * ----------  --------------------------------------------------  ------------------------------------------------------------------------------------------------
         * start       Path                                                起始路径。从这个目录开始遍历（包括该目录本身）
         * maxDepth    int                                                 最大递归深度。0 表示只查找起始路径；1 表示起始路径下第一层；依此类推
         * matcher     BiPredicate<Path, BasicFileAttributes>             用于判断每个路径是否匹配的条件。返回 true 的路径将包含在结果中
         * options     FileVisitOption...                                  可变参数，指定访问行为（如是否跟随符号链接），常用值如：FOLLOW_LINKS
         */
        Path p = Paths.get("src/Part9/ren");
        long dateF = 1596790800000L;
        try {
            Files.find(p,
                            10,
                            (path5, attr) ->
                                    path5.toString().endsWith(".txt") &&
                                            attr.creationTime().toMillis() > dateF)
                    .forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
        /**
         * src/Part9/ren/ren.txt
         * src/Part9/ren/tmp/x/y/ren.txt
         */


        System.out.println("=============");
        // lines()
        /**
         * 📌 使用注意事项
         * 返回的 Stream<String> 是惰性读取的（lazy-loaded），效率高，适合处理大文件。
         * 使用后应调用 try-with-resources 或手动关闭 Stream，以释放底层文件句柄。
         */
        Path path6 = Paths.get("src/Part9/ren/ren.txt");
        try {
            System.out.println(
                    Files.lines(path6)
                            .filter(s -> s.startsWith("1"))
                            .map(word -> word.length())
                            .collect(Collectors.toList()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        /**
         * [7, 12]
         */
        /**
         * 文件内容
         * 1======
         * 2
         * 3
         * 4
         * 5
         * 6
         * 7
         * 8
         * 9
         * 10==========
         */

    }
}