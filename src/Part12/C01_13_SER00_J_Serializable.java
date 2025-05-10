package Part12;

/**
 * @author liyanpeng
 * @date 2025/5/11
 * @description TODO
 */

/**
 * ✅ SER00-J. 開発中のクラスにおいてシリアライズの互換性を維持する
 * ✅✅✅安全规范:Serializable クラスは明示的に serialVersionUID を宣言する
 * 一般serialVersionUID变量的access是private
 * 但因为是类的常量所以使用public也没问题
 * private static final long serialVersionUID = 1L;
 * <p>
 * <p>
 * ■ 规则概要（ルール概要）
 * 目的：确保序列化对象在不同版本间兼容，防止因类结构变化而导致的 InvalidClassException。
 * Java 中通过 ObjectOutputStream 和 ObjectInputStream 实现对象的序列化与反序列化。
 * 当类结构发生变化（字段增删）但未显式定义 serialVersionUID 时，系统自动生成的 UID 会发生变化，反序列化就会失败。
 * <p>
 * ■ 攻击可能性 / 风险说明
 * 反序列化时 UID 不一致 → InvalidClassException 异常 → 程序中断 / 崩溃
 * 攻击者可利用序列化漏洞实现远程代码执行（RCE）等攻击
 * 即使不是直接攻击，也会造成系统稳定性问题。
 * <p>
 * ■ 不良例（serialVersionUID 未定义）
 * import java.io.Serializable;
 * public class User implements Serializable {
 * private String name;
 * private int age;
 * // serialVersionUID 未指定
 * }
 * 🔴 一旦类结构修改，如新增字段，旧版本序列化的数据将无法再读取。
 * <p>
 * ■ 改善例（显式声明 UID）
 * import java.io.Serializable;
 * public class User implements Serializable {
 * private static final long serialVersionUID = 1L;
 * private String name;
 * private int age;
 * }
 * 👍 添加 serialVersionUID 后，类结构轻微修改不会影响反序列化，兼容性更强。
 * <p>
 * ■ 用于生成 UID 的推荐工具
 * .   serialver User
 * <p>
 * ■ 与序列化有关的典型错误一览
 * ┌────────────────────────────┬─────────────────────────────────────────────┐
 * │ 典型错误                   │ 说明                                        │
 * ├────────────────────────────┼─────────────────────────────────────────────┤
 * │ 未定义 serialVersionUID     │ 版本升级后反序列化失败                      │
 * │ 使用 ObjectInputStream.readObject│ 易引发反序列化漏洞                      │
 * │ 将敏感字段序列化           │ 密码、Token 泄露风险                         │
 * │ 使用不安全的类做字段       │ 例如包含动态执行逻辑的对象，容易被攻击利用     │
 * └────────────────────────────┴─────────────────────────────────────────────┘
 */
public class C01_13_SER00_J_Serializable {
    // 自动生成 main 方法
    public static void main(String[] args) {
        // TODO
    }
}