package Part7;

/**
 * @author liyanpeng
 * @date 2025/4/30
 * @description TODO
 */

/**
 * jlink 是 Java 9 引入的一个工具，属于 Java 模块系统（JPMS） 的一部分。
 * 它可以用来创建一个定制化的 Java 运行时镜像（custom runtime image），只包含你项目运行所需的模块。
 * <p>
 * ✨ 主要作用
 * 使用 jlink，你可以：
 * 打包一个只包含必要模块的最小 Java 运行环境
 * 不再需要安装整个 JDK/JRE
 * 减小体积，提升启动速度
 * 增强安全性（减少攻击面）
 * <p>
 * <p>
 * 假设你开发了一个 Java 应用 myapp.jar，只依赖 java.base 和 java.sql，你可以这样使用 jlink：
 * jlink --module-path $JAVA_HOME/jmods:mods \
 * --add-modules my.module,java.sql \
 * --output my-runtime
 * 生成的 my-runtime 文件夹就是一个可执行的、独立的 Java 运行环境，可以打包发布，不需要额外安装 Java。
 * <p>
 * 常用的option
 * --module-path	指定模块路径（通常为 jmods 文件夹或自己编译的模块）	--module-path $JAVA_HOME/jmods:mods
 * --add-modules	指定要包含的模块（多个用逗号分隔）	--add-modules java.base,java.sql
 * --compress	压缩级别（0 不压缩，1 定数文字列的共有，2 最强压缩）	--compress 2
 * --launcher	创建可执行命令（指定模块中的主类）	--launcher myapp=my.module/my.pkg.Main
 * --output	指定生成的运行时输出目录	--output my-runtime
 */
public class C01_Jlink {
    // 自动生成 main 方法
    public static void main(String[] args) {
        // TODO
    }
}