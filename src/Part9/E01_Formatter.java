package Part9;

/**
 * @author liyanpeng
 * @date 2025/5/6
 * @description TODO
 */

import java.util.Formatter;

/**
 * 📘 一、什么是流的“格式化与解析”？
 * ✅ 格式化（Formatting）
 * 指的是将数据（如数值、字符串、日期等）以特定格式输出到字符流中，例如格式化输出到控制台或文件中。
 * System.out.printf("名字：%s, 年龄：%d", "田中", 25);
 * <p>
 * ✅ 解析（Parsing）
 * 指的是从字符流中读取并“解释”成特定数据类型，比如从控制台或文件中读取文本，然后将其解析成整数、浮点数、日期等。
 * Scanner sc = new Scanner(System.in);
 * int age = sc.nextInt();  // 解析输入的字符串为 int
 * <p>
 * ✅ Formatter
 * 🧾 Formatter 可以格式化的类型。Formatter 支持几乎所有常用数据类型的格式化，包括：
 * +----------------+------------------------------+
 * | 类型           | 对应格式符（format specifier） |
 * +================+==============================+
 * | 整数类型        | %d, %o, %x                   |
 * | 浮点类型        | %f, %e, %g, %a               |
 * | 字符类型        | %c                           |
 * | 布尔类型        | %b                           |
 * | 字符串          | %s                           |
 * | 对象            | %h（十六进制的hashCode）      |
 * | 日期时间        | %tY, %tm, %td, %tH, %tM, %tS  |
 * +----------------+------------------------------+
 * <p>
 * 💡 Formatter 支持的额外功能
 * 指定宽度和对齐方式（如 %10s 右对齐，%-10s 左对齐）
 * 精度控制（如 %.2f 表示保留两位小数）
 * 本地化支持（Locale，可用于格式化语言相关内容如日期、货币）
 * 格式嵌套（如 %1$tY-%1$tm-%1$td）
 * <p>
 * 📄 Formatter 构造函数 & 方法一览表
 * +--------------------------------------------+-------------------------------------------------+
 * | 构造函数 / 方法                             | 说明                                            |
 * +============================================+=================================================+
 * | Formatter()                                | 创建连接到 StringBuilder 的 Formatter 实例      |
 * | Formatter(Locale l)                        | 创建连接到 StringBuilder 的 Formatter，带 Locale |
 * |                                            | 常见的 Locale 值有：Locale.US、Locale.JAPAN、Locale.CHINA  |
 * 下面不是教科书内容
 * | Formatter(OutputStream out)                | 使用默认字符集写入指定输出流                   |
 * | Formatter(OutputStream out, Charset cs)    | 使用指定字符集写入输出流                       |
 * | Formatter(File file)                       | 向文件写入格式化输出                           |
 * | Formatter(PrintStream ps)                  | 写入指定 PrintStream                           |
 * +--------------------------------------------+-------------------------------------------------+
 * | Formatter format(String fmt, Object... args)| 使用指定格式字符串格式化指定对象               |
 * | Formatter format(Locale l, String fmt, Object... args)| 同上，带语言环境      |
 * | String toString()                          | 返回格式化后的结果字符串                       |
 * | void flush()                               | 刷新缓冲区                                     |
 * | void close()                               | 关闭 Formatter，释放资源                        |
 * +--------------------------------------------+-------------------------------------------------+
 * <p>
 * PrintWriter和PrintStream的format()和printf()和Formatter的format完全一致
 * PrintWriter的方法说明请参考【B06_SystemConst】
 * <p>
 * %,4.2f
 * ││││└── f：格式为十进制浮点数（float 或 double）
 * │││└── 2：小数点后保留 2 位
 * ││└── .2：精度说明符（可省略小数点时写成整数）
 * │└── 4：最小总宽度为 4 个字符（含小数点）
 * └── ,：使用千位分隔符（如 1,000.00）
 * <p>
 * %[インデックス$][フラグ][幅][.精度]変換の種類
 * %     开始标志，表示格式说明符开始
 * [インデックス$]  可选参数索引，指定使用第几个参数（从1开始）声明了数字$的话,可以以引数替换
 * [フラグ]         可选标志，用于控制输出格式细节（如左对齐、补0、千位分隔）【+：符号出力。0：不足补0。，：数字分割local依存】
 * [幅]            可选最小宽度，总输出字符数，不足时补空格或0
 * [.精度]         可选精度，控制小数位数或最大字符数（视变换类型而定）
 * 変換の種類       必须字段，表示以何种格式输出，如整数、小数、字符串等
 */
public class E01_Formatter {
    // 自动生成 main 方法
    public static void main(String[] args) {
        // TODO
        String compName = "SE社";
        String name = "tanaka";
        int age = 20;
        Formatter fm = new Formatter();
        fm.format("会社名は %s です。\n", compName);
        fm.format("名前 : %2$s : 年齢 : %1$d \n", age, name);
        System.out.println(fm);
        System.out.format("会社名は %s です。\n", compName);
        System.out.printf("名前 : %2$s : 年齢 : %1$d \n", age, name);
        /**
         * 会社名は SE社 です。
         * 名前 : tanaka : 年齢 : 20
         *
         * 会社名は SE社 です。
         * 名前 : tanaka : 年齢 : 20
         */
    }
}