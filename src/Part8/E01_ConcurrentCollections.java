package Part8;

/**
 * @author liyanpeng
 * @date 2025/5/1
 * @description TODO
 */

/**
 * java.util.concurrent 包
 * |__并行集合（Concurrent Collections）:并行集合可以在多个线程同时访问时提供线程安全的操作
 * |__ConcurrentHashMap：线程安全的哈希表，适用于高并发的读写操作。
 * |__CopyOnWriteArrayList、CopyOnWriteArraySet：线程安全的列表和集合，通过写时复制技术来保证线程安全。
 * |__BlockingQueue：线程安全的队列，包括 ArrayBlockingQueue、LinkedBlockingQueue、PriorityBlockingQueue 等，常用于生产者-消费者问题。
 * |__Executor框架:Java 5 引入的，用于管理线程池并简化并发任务的执行
 * |__ExecutorService：定义了线程池的基本操作方法（如提交任务、关闭线程池等）。
 * |__Executors 工厂类：提供了静态方法来创建常见类型的线程池，如 newFixedThreadPool() 和 newCachedThreadPool() 等。
 * |__ScheduledExecutorService：用于执行定时任务和周期性任务。
 * |__锁（Lock）
 * |__ReentrantLock：相比 synchronized，提供了更灵活的锁机制，允许显式地加锁和解锁。
 * |__ReadWriteLock：提供了读写锁，允许多个线程同时读取，但写入时需要独占锁。
 * |__同步工具类
 * |__CountDownLatch：一个同步工具，允许一个线程等待其他线程完成一组操作后再继续执行。
 * |__CyclicBarrier：让一组线程互相等待，直到所有线程都到达某个点后再继续执行。
 * |__Semaphore：控制对共享资源的访问数量，限制同时访问某个资源的线程数量。
 */

/**
 * 方法名                         | 参数                                             | 返回值                                   | 说明
 * ------------------------------|--------------------------------------------------|------------------------------------------|----------------------------------------------------------
 * synchronizedCollection         | Collection<T> 类型的集合                         | 线程安全的 Collection<T>                | 返回线程安全集合，所有操作加锁
 * synchronizedSet                | Set<T> 类型的集合                                | 线程安全的 Set<T>                       | 返回线程安全集合，所有操作加锁
 * synchronizedSortedSet          | SortedSet<T> 类型的有序集合                       | 线程安全的 SortedSet<T>                | 返回线程安全有序集合，所有操作加锁
 * synchronizedNavigableSet       | NavigableSet<T> 类型的集合                        | 线程安全的 NavigableSet<T>             | 返回线程安全可导航集合，所有操作加锁
 * synchronizedList               | List<T> 类型的集合                               | 线程安全的 List<T>                      | 返回线程安全列表，所有操作加锁
 * synchronizedMap                | Map<K, V> 类型的映射                              | 线程安全的 Map<K, V>                    | 返回线程安全映射，所有操作加锁
 * synchronizedSortedMap          | SortedMap<K, V> 类型的有序映射                    | 线程安全的 SortedMap<K, V>             | 返回线程安全有序映射，所有操作加锁
 * synchronizedNavigableMap       | NavigableMap<K, V> 类型的映射                     | 线程安全的 NavigableMap<K, V>          | 返回线程安全可导航映射，所有操作加锁
 *
 *
 * unmodifiableList               | List<? extends T> 类型的集合                      | 不可修改的 List<T>                      | 返回不可修改列表，修改操作抛出异常
 * unmodifiableSet                | Set<? extends T> 类型的集合                       | 不可修改的 Set<T>                       | 返回不可修改集合，修改操作抛出异常
 * unmodifiableMap                | Map<? extends K, ? extends V> 类型的映射          | 不可修改的 Map<K, V>                    | 返回不可修改映射，修改操作抛出异常
 * unmodifiableSortedSet          | SortedSet<? extends T> 类型的集合                 | 不可修改的 SortedSet<T>                 | 返回不可修改有序集合，修改操作抛出异常
 * unmodifiableSortedMap          | SortedMap<? extends K, ? extends V> 类型的映射    | 不可修改的 SortedMap<K, V>              | 返回不可修改有序映射，修改操作抛出异常
 * unmodifiableNavigableSet       | NavigableSet<? extends T> 类型的集合              | 不可修改的 NavigableSet<T>              | 返回不可修改可导航集合，修改操作抛出异常
 * unmodifiableNavigableMap       | NavigableMap<? extends K, ? extends V> 类型的映射 | 不可修改的 NavigableMap<K, V>           | 返回不可修改可导航映射，修改操作抛出异常
 * emptyList                      | 无                                                | 空的 List<T>                            | 返回一个空的不可修改列表
 * emptySet                       | 无                                                | 空的 Set<T>                             | 返回一个空的不可修改集合
 * emptyMap                       | 无                                                | 空的 Map<K, V>                          | 返回一个空的不可修改映射
 */

/**
 * ✅ 1. 线程安全只是外壳加锁，遍历时仍需手动同步(明示的同期化是必要的)
 * List<String> list = Collections.synchronizedList(new ArrayList<>());
 *
 * // ✅ 添加、删除是线程安全的
 * list.add("A");
 * list.remove("A");
 *
 * // ⚠️ 遍历时仍需同步(明示的同期化是必要的)，否则可能抛 ConcurrentModificationException
 * synchronized (list) {
 *     for (String s : list) {
 *         System.out.println(s);
 *     }
 * }
 * 原因：iterator() 获取后，遍历过程中结构若发生变化会抛异常。ConcurrentModificationException
 *
 *✅ 2. 返回的集合是包装后的“代理对象”
 *调用如 synchronizedList() 返回的是一个包装类，它内部通过 synchronized 实现加锁，但原始集合仍可能被其他引用直接访问。
 *List<String> origin = new ArrayList<>();
 * List<String> syncList = Collections.synchronizedList(origin);
 *
 * origin.add("直通原始集合"); // ⚠️ 非线程安全！
 *建议：只使用同步后的对象引用，不要保留原始对象的引用。
 *
 *✅ 4. 并不适用于高并发场景
 *如果是大量读写同时存在的场景，建议使用：
 * ConcurrentHashMap 替代 synchronizedMap
 * CopyOnWriteArrayList 替代 synchronizedList
 *
 *
 *wait() / notify() 与 Java 的 java.util.concurrent（并发工具包）相比，
 * 都是处理线程通信和协作的机制，但在设计层级、易用性、安全性等方面有明显区别。下面为你整理出对比：
 *+-------------------------+-----------------------------+--------------------------------------------------------+
 * | 比较项                  | wait()/notify()             | java.util.concurrent 工具类                          |
 * +-------------------------+-----------------------------+--------------------------------------------------------+
 * | 所在包/类               | Object 类                   | java.util.concurrent 包下的类                         |
 * +-------------------------+-----------------------------+--------------------------------------------------------+
 * | 控制粒度                | 低，基于对象                | 高，提供专用同步器（如 Lock、Semaphore 等）         |
 * +-------------------------+-----------------------------+--------------------------------------------------------+
 * | 功能丰富度              | 少                          | 多，支持超时、可中断、公平锁、条件变量等            |
 * +-------------------------+-----------------------------+--------------------------------------------------------+
 * | 使用难度                | 高，需手动管理同步和状态    | 低，封装良好、API 清晰                               |
 * +-------------------------+-----------------------------+--------------------------------------------------------+
 * | 是否需要使用 synchronized | 是                          | 否，使用 ReentrantLock 等时无需 synchronized         |
 * +-------------------------+-----------------------------+--------------------------------------------------------+
 * | 可读性/可维护性         | 差                          | 好                                                    |
 * +-------------------------+-----------------------------+--------------------------------------------------------+
 * | 支持多个条件队列        | 否                          | 是（如 Lock.newCondition() 可创建多个条件）          |
 * +-------------------------+-----------------------------+--------------------------------------------------------+
 * | 应用场景                | 简单同步场景                | 高并发、复杂线程协作场景                             |
 * +-------------------------+-----------------------------+--------------------------------------------------------+
 *
 * ✅ 使用 wait()/notify() 的常见问题
 * 必须在 synchronized 块中使用，否则抛异常
 * 易产生：
 * 死锁
 * 假唤醒（spurious wakeup）
 * 忘记条件检查
 * notify() 唤醒的线程不一定是你期望的那个
 *
 *
 * ✅ java.util.concurrent 提供的替代方案
 * 功能	             替代类
 * 等待 / 唤醒线程	 BlockingQueue、Semaphore、CountDownLatch
 * 通知多个线程	     CyclicBarrier、Phaser
 * 获取 / 释放锁	     ReentrantLock（代替 synchronized）
 * 等待条件满足	     Condition.await() + signal()
 *
 */
public class E01_ConcurrentCollections {
    // 自动生成 main 方法
    public static void main(String[] args) {
        // TODO
    }
}