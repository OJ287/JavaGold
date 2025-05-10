package Part12;

/**
 * @author liyanpeng
 * @date 2025/5/11
 * @description TODO
 */

/**
 * ✅ MSC06-J：繰り返し処理中に基となるコレクションを変更しない
 * ✅✅✅安全规范：看下面的🧠 总结
 * <p>
 * 🧨 攻击风险・问题背景
 * .  在使用 for-each 或 Iterator 遍历集合时，如果同时修改（add/remove）集合，
 * 会触发 ConcurrentModificationException，或者导致 数据不一致、未定义行为。
 * 攻击者可以利用此异常让系统进入不稳定状态，或导致中间状态泄漏。
 * <p>
 * ❌ 违规示例（典型错误用法）
 * List<String> names = new ArrayList<>();
 * names.add("Alice");
 * names.add("Bob");
 * for (String name : names) {
 * if (name.equals("Bob")) {
 * names.remove(name); // ❌ 同时遍历和修改集合
 * }
 * }
 * <p>
 * 运行时将抛出：java.util.ConcurrentModificationException
 * <p>
 * <p>
 * ✅ 安全示例1：使用 Iterator 并调用 iterator.remove()
 * List<String> names = new ArrayList<>();
 * names.add("Alice");
 * names.add("Bob");
 * <p>
 * Iterator<String> it = names.iterator();
 * while (it.hasNext()) {
 * String name = it.next();
 * if (name.equals("Bob")) {
 * it.remove(); // ✅ 安全：通过 Iterator 修改
 * }
 * }
 * <p>
 * <p>
 * <p>
 * ✅ 安全示例2：遍历复制后的集合
 * List<String> names = new ArrayList<>();
 * names.add("Alice");
 * names.add("Bob");
 * <p>
 * for (String name : new ArrayList<>(names)) {
 * if (name.equals("Bob")) {
 * names.remove(name); // ✅ 安全：原集合被修改，但遍历的是副本
 * }
 * }
 * <p>
 * ✅ 安全示例3：使用 removeIf（Java 8+）
 * List<String> names = new ArrayList<>();
 * names.add("Alice");
 * names.add("Bob");
 * <p>
 * names.removeIf(name -> name.equals("Bob")); // ✅ 简洁且安全
 * <p>
 * <p>
 * 🧠 总结
 * ┌──────────────────────────────────────────────┬───────────────────────────────────────────┐
 * │ 错误做法                                     │ 安全做法                                  │
 * ├──────────────────────────────────────────────┼───────────────────────────────────────────┤
 * │ 遍历时直接使用 list.remove() 或 list.add()    │ 使用 Iterator 并通过 iterator.remove()     │
 * │                                               │ 遍历副本副本后修改原集合                   │
 * │                                               │ 使用 removeIf 等安全 API（Java 8+）        │
 * └──────────────────────────────────────────────┴───────────────────────────────────────────┘
 * default boolean removeIf(Predicate<? super E> filter)
 * 内部仍然使用 Iterator 并调用其 remove() 实现安全删除。
 * <p>
 * ✅ 多线程中如何安全操作集合
 * 1. Collections.synchronizedList / synchronizedMap
 * ✅ 支持线程安全访问（通过 synchronized 实现同步锁）
 * ❌ 不能在 for-each 中直接访问，遍历时必须手动加锁：
 * <p>
 * 2. CopyOnWriteArrayList / CopyOnWriteArraySet
 * List<String> list = new CopyOnWriteArrayList<>();
 * ✅ 线程安全
 * ✅ 可在遍历中直接进行结构修改（背后是写时复制机制）
 * ❌ 写入性能差（每次修改都会复制整个底层数组）
 * ✅ 非阻塞，读操作非常快，适用于读多写少场景
 */
public class E01_49_MSC06_While {
    // 自动生成 main 方法
    public static void main(String[] args) {
        // TODO
    }
}