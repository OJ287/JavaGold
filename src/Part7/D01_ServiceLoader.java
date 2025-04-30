package Part7;

/**
 * @author liyanpeng
 * @date 2025/4/30
 * @description TODO
 */

/**
 * ✅ 一、ServiceLoader 是什么？
 * ServiceLoader 是 Java 提供的一种 服务发现机制，用于在运行时 查找和加载服务的实现类。
 * 关键词：
 * 服务接口（Service Interface）：公共接口或抽象类。
 * 服务实现（Service Provider）：实现该接口的类。
 * 服务加载器（ServiceLoader）：用于加载实现类的工具。
 * <p>
 * 二、ServiceLoader 的基本用法
 * ServiceLoader<MyService> loader = ServiceLoader.load(MyService.class);
 * for (MyService service : loader) {
 * service.doSomething();
 * }
 * <p>
 * 目录结构要求（传统方式）：
 * 在资源路径下：
 * META-INF/services/com.example.MyService
 * 内容为：com.example.impl.MyServiceImpl
 * <p>
 * <p>
 * ✅ 三、模块系统下的使用（Java 9+）
 * 从 Java 9 开始，模块系统（module system） 引入了更安全、更显式的服务机制：
 * 步骤如下：
 * 1. 服务接口模块：声明自己是服务 消费者
 * module consumer.module {
 * requires provider.module;
 * uses com.example.MyService;  // 声明需要这个服务//service：除了IF以外，抽象类也可以
 * }
 * 2. 实现类模块：声明自己是服务 提供者
 * module provider.module {
 * provides com.example.MyService  // IF
 * with com.example.impl.MyServiceImpl1,com.example.impl.MyServiceImpl2;  // 实现类
 * }
 * 3. 使用 ServiceLoader 加载：
 * ServiceLoader<MyService> loader = ServiceLoader.load(MyService.class);
 * 无需再依赖 META-INF/services，模块系统自己会处理。
 * <p>
 * ✅ 四、ServiceLoader 和系统（system）关系
 * 这里的“系统”大多数情况下指的是 JVM 运行时平台（比如 Java Runtime Environment），而不是 OS。
 * 与系统的关系：
 * ServiceLoader 是运行时机制，在 JVM 启动后通过类加载器动态工作。
 * jlink 可以结合模块系统和 ServiceLoader 创建一个 定制的运行环境，把服务消费者和提供者打包进去。
 * --bind-services 选项可以把服务实现也静态绑定到定制运行环境中。
 * <p>
 * ✅ 五、总结图解（逻辑流程）：
 * [模块A: consumer] ── uses ──► [接口: MyService]
 * ▲
 * │
 * [模块B: provider] ── provides ──┘ 实现类：MyServiceImpl
 */
public class D01_ServiceLoader {
    // 自动生成 main 方法
    public static void main(String[] args) {
        // TODO
    }
}