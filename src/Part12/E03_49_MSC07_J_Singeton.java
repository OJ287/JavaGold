package Part12;

/**
 * @author liyanpeng
 * @date 2025/5/11
 * @description TODO
 */

/**
 * 2.複数のスレッドによって初期化が並列に実行されることを防ぐため、ロックの仕組みを導入する【使用 synchronized 或静态内部类确保线程安全】
 * <p>
 * ❌ 典型的な危険な Singleton 実装（スレッド非安全）
 * public class UnsafeSingleton {
 * private static UnsafeSingleton instance;
 * private UnsafeSingleton() {}
 * public static UnsafeSingleton getInstance() {
 * if (instance == null) {
 * instance = new UnsafeSingleton(); // マルチスレッドで複数回呼ばれる可能性あり！
 * }
 * return instance;
 * }
 * }
 * 下面也不行，两个线程同时判定instance == null的话，还是会分别去new对象
 * if (instance == null) {
 * synchronized（this）{
 * instance = new UnsafeSingleton(); // マルチスレッドで複数回呼ばれる可能性あり！
 * }
 * }
 * <p>
 * 📌 問題点：
 * 2つ以上のスレッドが同時に getInstance() を呼び出すと、複数のインスタンスが生成されてしまう。
 * 没有synchronized
 * <p>
 * ✅ 安全対策①：synchronized を使う
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
 * 📌 特点：
 * 确保同一时间只有一个线程能执行方法，防止并发初始化。
 * 缺点：每次调用都加锁，性能偏低。
 */
public class E03_49_MSC07_J_Singeton {
    // 自动生成 main 方法
    public static void main(String[] args) {
        // TODO
    }
}