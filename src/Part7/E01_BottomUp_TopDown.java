package Part7;

/**
 * @author liyanpeng
 * @date 2025/4/30
 * @description TODO
 */

/**
 * 🔽 ボトムアップ移行（Bottom-Up移行）
 * 含义：
 * 从系统的底层模块（或低层功能）开始进行现代化或模块化的迁移，逐步向上推进。
 * 特征：
 * 从细节或小规模组件出发，先移行低耦合、依赖少的模块。
 * 适合先验证新系统架构的可行性。
 * 风险较低，适合逐步替换。
 * 迁移初期对整体系统影响小。
 * 例子： 将某个功能库（如 CSV 解析模块）或 DAO 层改为模块系统（Java Platform Module System），逐步替换调用它的中层逻辑。
 * <p>
 * <p>
 * 🔼 トップダウン移行（Top-Down移行）
 * 含义：
 * 从系统的最上层（如 UI 层或 API 接口层）开始进行移行，再向下逐步迁移依赖的组件。
 * 特征：
 * 适合在业务逻辑整体变动较大或系统架构要重构时使用。
 * 变更范围大，风险较高，需要充分的测试和设计。
 * 可以快速见到“业务效果”或对用户的直接影响。
 * 比较适用于新旧系统并行开发、替换的情况。
 * 例子： 先重写 Web 前端，使用新的模块系统暴露 API 接口，再逐步替换后端服务或数据处理逻辑。
 * <p>
 * <p>
 * ✅ 总结对比：
 * <p>
 * 项目	        ボトムアップ移行	トップダウン移行
 * 开始位置	    从底层模块开始	从上层接口或UI开始
 * 风险	        相对较低	        相对较高
 * 对现有系统影响	小	            大
 * 开发方式	    渐进式	        通常是全体设计之后一次迁移
 * 适合场景	    模块系统化、部分组件替换	整体系统更新或接口变动大
 */
public class E01_BottomUp_TopDown {
    // 自动生成 main 方法
    public static void main(String[] args) {
        // TODO
    }
}
/**
 * ✅ クラスパス（Class Path）とは？
 * 传统的 Java 加载方式（Java 8 及以前）。
 * 用来指定编译或运行 Java 程序时，要加载的类（.class）或 jar 文件的位置路径。
 * 主要特征：
 * 不关心模块结构，所有 class 文件在一个“扁平”的路径里。
 * 没有模块名，所有类都处于同一命名空间中，可能会发生类冲突。
 * 类路径通过 -cp 或 CLASSPATH 环境变量指定：
 * java -cp lib/mylib.jar Main
 * <p>
 * <p>
 * ✅ モジュールパス（Module Path）とは？
 * Java 9 引入的模块系统之后的新方式，用来指定 模块化 jar（带有 module-info.java 的 jar）所在的路径。
 * 主要特征：
 * 模块 jar 包必须有 module-info.class（编译自 module-info.java）。
 * 模块有明确的模块名、依赖关系和导出包。
 * 通过 --module-path 指定模块路径：
 * <p>
 * java --module-path mods -m com.example.myapp/com.example.Main
 * 避免了类冲突，提高了封装性和安全性。
 * <p>
 * 🔍 クラスパス vs モジュールパス 的区别总结
 * <p>
 * 比较项	   クラスパス（Class Path）	   モジュールパス（Module Path）
 * 引入版本	   Java 1.0 起	               Java 9 起
 * 是否支持模块化	❌ 不支持	               ✅ 必须有 module-info.class
 * 包访问控制	   全部公开（默认 public 可访问）  通过 exports 和 requires 精细控制
 * 类命名冲突风险	高	                       低，模块名唯一
 * 加载机制	   类加载器遍历所有路径	       模块系统根据依赖树加载
 * 启动方式示例	java -cp	               java --module-path -m 模块/主类
 * <p>
 * <p>
 * 🔗 两者的关系（联系）
 * Java 9 以后依然可以使用 class path，并且模块路径和类路径可以混用，例如：
 * java --module-path mods --class-path lib/* -m my.module/com.example.Main
 * 但一旦使用模块化系统，就建议只使用 module path，否则会打破模块边界、降低安全性。
 * <p>
 * 在 模块内 引用未模块化的第三方库时，有时还会用到 automatic modules（自动模块），系统会从 classpath 推断模块名。
 */