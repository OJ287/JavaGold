package Part12;

/**
 * @author liyanpeng
 * @date 2025/5/11
 * @description TODO
 */

/**
 * ✅ SEC00-J：センシティブな情報を特権ブロックから信頼境界を越えて漏えいさせない
 * ✅✅✅ 安全规范：将AccessController.doPrivileged()封装起来，并且使用private
 * <p>
 * ■ 规则概要
 * Java 安全模型中，AccessController.doPrivileged() 块内的代码拥有比外部调用者更高的权限。
 * 此时若泄露敏感信息（如系统属性、文件路径、认证令牌等）至“外部不可信调用者”，就会构成安全漏洞。
 * <p>
 * ■ 攻击风险
 * 特権コード泄露敏感信息 → 恶意外部调用者利用其结果进行攻击
 * 特权代码执行后返回的数据，若无妥善处理，可能被恶意外部代码获取，
 * 例如读取某些敏感系统属性或内部路径，甚至间接导致沙箱逃逸（sandbox escape）。
 * <p>
 * ■ 不良例（敏感数据泄露）
 * public class SecretLeaker {
 * public static String getJavaHome() {
 * return AccessController.doPrivileged(
 * (PrivilegedAction<String>) () -> System.getProperty("java.home")
 * );
 * }
 * }
 * 🔴 该方法可能被任意调用者调用，返回的 java.home 属于潜在敏感信息，存在跨越信赖边界的信息泄露风险。
 * <p>
 * ■ 改善例（在特权块内使用，外部不返回）
 * public class SecretLeakerSafe {
 * public static void printJavaHome() {
 * AccessController.doPrivileged((PrivilegedAction<Void>) () -> {
 * System.out.println(System.getProperty("java.home"));
 * return null;
 * });
 * }
 * }
 * ✅ 将敏感信息控制在特权块中处理，不外泄对象引用或数据结果。
 * <p>
 * ✅ AccessController.doPrivileged 的方法签名
 * public static <T> T doPrivileged(PrivilegedAction<T> action)
 * 参数：
 * action — 要以特权执行的操作，必须实现 PrivilegedAction<T> 接口。
 * 返回值：
 * T — action.run() 的返回值。
 * 作用：
 * 在当前访问控制上下文中，以调用者的权限执行 action.run() 方法（即使栈上其他代码权限较低）。
 * <p>
 * ✅ 接口 PrivilegedAction<T> 的完整定义
 *
 * @FunctionalInterface public interface PrivilegedAction<T> {
 * /**
 * * 执行计算。以调用者权限执行。
 * *
 * * @return 计算结果类型为 T，可以是 null
 * T run();
 * }
 * <p>
 * <p>
 * ■ 可疑的返回值处理方式一览
 * ┌──────────────────────────────┬────────────────────────────────────┐
 * │ 危险操作                     │ 原因说明                           │
 * ├──────────────────────────────┼────────────────────────────────────┤
 * │ 返回 System.getProperty()     │ 可能返回用户目录、系统路径等敏感信息  │
 * │ 返回 Files.readAllLines()    │ 文件内容暴露                       │
 * │ 返回 Runtime.getRuntime()    │ 调用 exec 后门风险                 │
 * └──────────────────────────────┴────────────────────────────────────┘
 * <p>
 * ■ 防御对策总结
 * ┌──────────────────────────────┬────────────────────────────────────────────┐
 * │ 对策                           │ 内容                                      │
 * ├──────────────────────────────┼────────────────────────────────────────────┤
 * │ 特権块中只处理不返回数据的逻辑     │ 如打印、写入日志，不返回对象或信息              │
 * │ 若必须返回，确保结果已脱敏或中立化 │ 如掩码处理、只返回非敏感的常规值                │
 * │ 使用 AccessController 需谨慎      │ 封装特权逻辑，不暴露实现细节                    │
 * └──────────────────────────────┴────────────────────────────────────────────┘
 */
public class D01_14_SEC00_J_Privileged {
    // 自动生成 main 方法
    public static void main(String[] args) {
        // TODO
    }
}