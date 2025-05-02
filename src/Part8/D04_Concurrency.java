package Part8;

/**
 * @author liyanpeng
 * @date 2025/5/1
 * @description TODO
 */

/**
 * Concurrencyユーティリティーズ（并发工具类）是 Java 为了更高效地进行多线程编程而提供的一组高级并发API，
 * 主要集中在 java.util.concurrent 包下。
 * 它们相较于传统的 synchronized 和 wait/notify 更灵活、可扩展性强、易于控制线程间协作与资源竞争。
 * <p>
 * <p>
 * 在使用 forEach 操作 Map 时，如果你尝试在 forEach 的操作过程中调用 remove，会抛出 ConcurrentModificationException 异常。
 * 为什么会抛出异常？
 * 这是因为 forEach 操作会在 Map 上进行迭代，而在迭代过程中直接修改Map（例如，调用 remove）会导致并发修改的问题。
 * 在这种情况下，Java 的集合类会抛出 ConcurrentModificationException 来防止未定义的行为。
 * <p>
 * <p>
 * Map<String, Integer> map = new HashMap<>();
 * map.put("A", 1);
 * map.put("B", 2);
 * map.put("C", 3);
 * for(String key :map.keySet()){map.remove(key);}
 * 改成
 * Map<String, Integer> map = new HashMap<>();
 * map.put("A", 1);
 * map.put("B", 2);
 * map.put("C", 3);
 * <p>
 * Iterator<Map.Entry<String, Integer>> iterator = map.entrySet().iterator();
 * while (iterator.hasNext()) {
 * Map.Entry<String, Integer> entry = iterator.next();
 * System.out.println(entry.getKey() + " = " + entry.getValue());
 * if ("B".equals(entry.getKey())) {
 * iterator.remove();  // 使用 iterator.remove() 来安全移除元素
 * }
 * }
 * <p>
 * // 输出剩余的键值对
 * System.out.println("Modified Map: " + map);
 * <p>
 * <p>
 * Concurrency ユーティリティーズ（Concurrency Utilities） 在 Java 中主要是通过
 * java.util.concurrent
 * java.util.concurrent.atomic
 * 它们提供了一些有助于多线程并发编程的工具和机制，
 * 如并行集合（concurrent collections）、执行框架（Executor framework）和原子变量（atomic variables）等
 */
public class D04_Concurrency {
    // 自动生成 main 方法
    public static void main(String[] args) {
        // TODO
    }
}