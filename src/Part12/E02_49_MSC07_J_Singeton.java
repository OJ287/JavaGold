package Part12;

/**
 * @author liyanpeng
 * @date 2025/5/11
 * @description TODO
 */

/**
 * 🔐 MSC07-J: シングルトンオブジェクトのインスタンスを複数作らない
 * <p>
 * ✅ 规则概要（What）
 * 「シングルトン（Singleton）」はクラスのインスタンスが1つだけ存在することを保証するデザインパターン。
 * 该规则要求我们在实现 Singleton 模式时，应防止创建多个实例，以保障程序逻辑一致性与资源安全性。
 * <p>
 * 🎯 攻击可能性（Why）
 * 如果 Singleton 实现不当，可能出现多个实例，带来以下安全风险：
 * 状态破坏：多个实例间共享资源不一致
 * 并发问题：非线程安全 Singleton 会在多线程中创建多个对象
 * 资源泄漏：比如重复打开数据库连接、重复注册监听器
 * 反序列化漏洞：攻击者可通过反序列化创建新的实例
 * <p>
 * <p>
 * ✅シングルトンデザインパターンを実装するクラスは、インスタンスが複数生成されることを防がなければならない。
 * ✅それを実現する手法としては以下が挙げられる。
 * 1.コンストラクタを private にする【防止外部直接实例化】
 * 2.複数のスレッドによって初期化が並列に実行されることを防ぐため、ロックの仕組みを導入する【使用 synchronized 或静态内部类确保线程安全】
 * 3.クラスがシリアライズできないようにする【防止反序列化生成新实例】
 * 4.クラスのクローンをつくれないようにする【防止通过 clone 创建新实例】
 * 5.独自のクラスローダでロードされたクラスがガベージコレクションで回収されないようにする【static字段引用防止被GC，注意内存泄漏风险】
 * <p>
 * <p>
 * <p>
 * 🧨 典型错误代码示例【1.コンストラクタを private にする【防止外部直接实例化】】
 * public class UnsafeSingleton {
 * private static UnsafeSingleton instance;
 * public static UnsafeSingleton getInstance() {
 * if (instance == null) {
 * instance = new UnsafeSingleton();  // ⚠️ 多线程中可能创建多个实例
 * }
 * return instance;
 * }
 * }
 * 在多线程同时调用 getInstance() 时，可能创建多个实例，违反 Singleton 原则。
 * <p>
 * ✅ 安全对策示例（防止被攻击的开发例）
 * <p>
 * public class SafeSingleton {
 * private static volatile SafeSingleton instance;
 * private SafeSingleton() {}
 * public static SafeSingleton getInstance() {
 * if (instance == null) {
 * synchronized (SafeSingleton.class) {
 * if (instance == null) {
 * instance = new SafeSingleton();
 * }
 * }
 * }
 * return instance;
 * }
 * }
 * <p>
 * 📊 对比一览表
 * ┌────────────────────────────┬──────────────┬───────────────┬───────────────────────────────┐
 * │ 实现方式                   │ 是否线程安全 │ 是否懒加载     │ 特点                          │
 * ├────────────────────────────┼──────────────┼───────────────┼───────────────────────────────┤
 * │ 懒汉式（非同步）           │ 否           │ 是             │ 多线程不安全，不推荐           │
 * │ synchronized 懒汉式        │ 是           │ 是             │ 性能差                         │
 * │ Double-Checked Locking     │ 是           │ 是             │ 推荐，兼顾效率与安全           │
 * │ 静态内部类（推荐）         │ 是           │ 是             │ 推荐，简单、安全、高效         │
 * │ 枚举（Java Effective推荐） │ 是           │ 否             │ 非懒加载，可防反射/反序列化   │
 * └────────────────────────────┴──────────────┴───────────────┴───────────────────────────────┘
 */
public class E02_49_MSC07_J_Singeton {
    // 自动生成 main 方法
    public static void main(String[] args) {
        // TODO
    }
}