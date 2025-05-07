package Part9;

/**
 * @author liyanpeng
 * @date 2025/5/6
 * @description TODO
 */

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 答え：
 * 1:BE
 * 2:A→D
 * 3:E
 * 4:CF
 * 5:BDF→ACE
 * 6:DF→AD
 * 7:ABC→ACE
 * 8:A
 * 9:DE
 * 10:AC
 * 11:ACE
 * 12:F→E
 * 13:ACE
 * 14:B→C
 * 15:D→B
 * 16:BC
 * 17:A
 * 18:F→C
 * 19:C
 * 20:DF→BC
 * 21:E→D
 * 22:C→E
 * 23:C
 */
public class YYY_Practice01 {
    // 自动生成 main 方法
    public static void main(String[] args) {
        // TODO
        /**
         * 2
         * System.console()
         * 的
         * readLine():返回String
         * readPassword:返回char[]
         */

        /**
         * 6
         * System.in定数是InputStream型
         * System.out/error定数是PrintStream型
         *
         *
         */

        /**
         * 7
         * new File的构造函数主要是以下三个
         * File(String path)
         * File(File parent,String child)
         * File(String parent,String child)
         *
         */
        //File file = new File("/home/yuko/mypic/cooking.png");
        //File file = new File("/home/yuko/mypic/" , new File("cooking.png"));
        //File file = new File("/home/yuko", "/mypic/cooking.png");
        //File file = new File("/home", "/yuko", "/mypic", "/cooking.png");
        //File file = new File(new File("/home"), "/yuko/mypic/cooking.png");
//        System.out.println(file);

        /**
         * 14
         * Path path1 = Paths.get("/tmp/.././home", "../my.txt");
         * System.out.println(path1.normalize());
         * ✅ 第一步：路径拼接
         * Paths.get("/tmp/.././home", "../my.txt")
         * 相当于拼接路径：
         * /tmp/.././home/../my.txt
         * 拼接后路径为：
         * /tmp/.././home/../my.txt
         *
         * ✅ 第二步：normalize() 规范化
         * .normalize() 的作用是消除路径中的：
         * . 当前目录
         * .. 上级目录（如果可能）
         * 对 /tmp/.././home/../my.txt 执行 normalize：
         * /tmp/../ → 去掉 tmp，回到 /
         * ./ → 当前目录，不变
         * /home/../ → 去掉 home
         * 剩下的是：/my.txt
         */
        Path path1 = Paths.get("/tmp/.././home", "../my.txt");
        System.out.println(path1.normalize());

        /**
         * 15
         * normalize只是将路径进行拼接后规范化
         * 不会添加绝对路径
         */

        /**
         * シンボリックリンク
         * 是使用文件或者文件夹做成的
         * 所以isDirectory是true
         */

        /**
         * 18
         * resolve()
         * 你给我什么路径我就使用什么路径，不会规范化，不会像normalize一样规范化
         */

        /**
         * 20
         * Files.move(...) 是尝试将 foo.txt 移动为 mydata。不是移动到mydata
         *     Path p1 = Paths.get("foo.txt");
         *     Path p2 = Paths.get("mydata");
         *     Files.move(p1, p2, StandardCopyOption.ATOMIC_MOVE,
         *     LinkOption.NOFOLLOW_LINKS);
         *
         *StandardCopyOption.ATOMIC_MOVE
         * 尝试执行原子移动，也就是说，文件移动要么完全成功，要么完全不发生。常用于避免中间状态。
         * 并 不是所有平台都支持，不支持的话会抛出 AtomicMoveNotSupportedException。
         *
         * LinkOption.NOFOLLOW_LINKS
         * 表示在判断符号链接时不跟随链接对象本身。这个参数在 move 中实际并不强制使用，但它可以与文件存在性判断等配合。
         *
         *
         * ✅ 情况一：mydata 是一个不存在的文件名
         * 结果：
         * ✅ foo.txt 被改名为 mydata。操作成功。
         * 说明：
         * 这是最普通的文件重命名行为。
         *
         * ⚠️ 情况二：mydata 是一个已存在的文件
         * 结果：
         * ❌ 会抛出异常：FileAlreadyExistsException
         * 说明：
         * Files.move() 默认 不覆盖 目标文件，除非你指定 StandardCopyOption.REPLACE_EXISTING。
         *
         *
         * ❌ 情况三：mydata 是一个存在的目录
         * 结果：
         * ❌ 抛出异常：DirectoryNotEmptyException 或 FileAlreadyExistsException
         * 说明：
         * 你不能直接把文件 foo.txt 移动成目录 mydata。
         * 除非你明确指定路径为 mydata/foo.txt，比如：
         * Path p2 = Paths.get("mydata/foo.txt");
         *
         * ⚠️ 情况四：mydata 是一个不存在的目录
         * 结果：
         * ❌ 抛出异常：NoSuchFileException，因为无法创建中间目录。
         * 说明：
         * 你不能直接通过 move 创建目标目录，只能移动到已有路径中。
         * 如果你想移动文件到一个新目录中，必须确保目录存在。
         *
         */

        /**
         * 22
         * try {
         *       Path path = Paths.get("./foo/memo.txt");
         *       Files.lines(path)
         *            .flatMap(p -> Stream.of(p.split(",")))
         *            .map(word -> word.length())
         *            .forEach(System.out::print);
         *     } catch(java.io.IOException e) { }
         *
         *Path path = Paths.get("./foo/memo.txt");
         * Files.lines(path)
         *读取当前目录下 ./foo/memo.txt 文件内容
         * Files.lines() 返回 Stream<String>，每行作为流的一个元素
         * 由于文件只有一行内容 "qwer,qwert,qwer"，流中只有一个元素
         *
         *2. 数据拆分阶段
         *.flatMap(p -> Stream.of(p.split(",")))
         * 对每行内容按逗号拆分：
         * "qwer,qwert,qwer".split(",") → ["qwer", "qwert", "qwer"]
         * flatMap 将拆分后的数组转换为新的流元素：
         * 流内容变为："qwer", "qwert", "qwer"
         *
         * 3. 数据处理阶段
         * .map(word -> word.length())
         * 计算每个单词的长度：
         * "qwer" → 4
         * "qwert" → 5
         * "qwer" → 4
         *
         *4. 结果输出阶段
         * .forEach(System.out::print);
         * 打印每个长度值（无分隔符）：
         * 输出结果：454
         *
         * 文件内容: "qwer,qwert,qwer"
         *        |
         *        v
         * Files.lines() → ["qwer,qwert,qwer"]
         *        |
         *        v
         * flatMap(split(",")) → "qwer" ── "qwert" ── "qwer"
         *        |                   |          |          |
         *        v                   v          v          v
         *    map(length)            4          5          4
         *        |                   |          |          |
         *        v                   v          v          v
         *  forEach(print)         打印4       打印5      打印4
         * 最终输出：454
         *
         * ✅流操作特性：
         * flatMap 用于"展平"嵌套结构（数组→独立元素）
         * map 是1:1转换操作
         * forEach 是终端操作，触发实际计算
         *
         */
    }
}