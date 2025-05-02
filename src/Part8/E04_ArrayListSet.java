package Part8;

/**
 * @author liyanpeng
 * @date 2025/5/1
 * @description TODO
 */

/**
 * 1. CopyOnWriteArrayList
 * CopyOnWriteArrayList 是一个线程安全的 List 实现，底层使用一个数组来存储元素。在每次修改（如添加、删除）时，
 * 它会创建一个新副本，因此它可以在多线程环境下提供安全的读操作，尤其适用于读多写少的场景。
 * <p>
 * 特点：
 * 线程安全：它是通过在修改时复制整个数组来确保线程安全的，因此它在并发环境中不会出现数据竞争。
 * 读写分离：读操作非常高效，因为它没有加锁，直接读取底层数组即可；但写操作需要复制数组，因此写操作的性能较差。
 * 不支持快速失败：由于它对集合进行了复制，所以在修改期间，不会对正在迭代的线程产生影响。
 * <p>
 * 方法：
 * add(E e)：在尾部添加元素，会创建一个新的数组。
 * remove(int index)：移除指定位置的元素，会创建一个新的数组。
 * set(int index, E element)：替换指定位置的元素，会创建一个新的数组。
 * 其他与普通 ArrayList 类似的方法。
 * <p>
 * 使用场景：
 * 适用于读操作远多于写操作的场景。例如，读取配置数据、事件监听等。
 * 不适用于频繁修改集合元素的场景，因为每次修改都涉及到复制数组。
 * <p>
 * <p>
 * 2. CopyOnWriteArraySet
 * CopyOnWriteArraySet 是一个线程安全的 Set 实现，底层使用 CopyOnWriteArrayList 来实现 Set 的功能。
 * 它与 CopyOnWriteArrayList 类似，但由于 Set 不允许重复元素，因此它会确保集合中的元素唯一。
 * <p>
 * 特点：
 * 线程安全：同样通过复制底层数组来实现线程安全，确保并发环境下不会发生数据竞争。
 * 无重复元素：和 Set 的行为一致，CopyOnWriteArraySet 保证集合中的元素是唯一的。
 * 高效的读取操作：读操作非常快速，不需要锁定任何资源，但写操作涉及到数组复制，因此写操作的性能较差。
 * <p>
 * 方法：
 * add(E e)：添加元素，底层会复制数组并插入新元素。
 * remove(Object o)：移除元素，底层会复制数组并移除指定元素。
 * contains(Object o)：检查元素是否存在，直接在当前数组中查找。
 * 其他与 Set 类似的方法。
 * <p>
 * 使用场景：
 * 适用于读操作多于写操作的场景，并且不需要对集合进行频繁的修改。
 * 适用于不需要保证元素顺序的场景。
 * <p>
 * <p>
 * <p>
 * 3. 总结
 * CopyOnWriteArrayList：
 * 是一个线程安全的 List 实现，适用于读多写少的场景。
 * 每次修改操作都涉及复制底层数组，因此写操作的开销较大。
 * 读操作不需要加锁，性能非常高，适用于多线程环境中的并发读操作。
 * CopyOnWriteArraySet：
 * 是一个线程安全的 Set 实现，基于 CopyOnWriteArrayList。
 * 适用于读多写少且不允许重复元素的场景。
 * 与 CopyOnWriteArrayList 类似，写操作性能较差，但读操作非常高效。
 * <p>
 * <p>
 * 4. 优缺点
 * 优点：
 * 读操作高效且线程安全。
 * 适合读取频繁但修改较少的场景。
 * 适用于事件驱动或配置读取等场景。
 * 缺点：
 * 写操作性能较差，因为每次修改都需要复制底层数组。
 * 不适用于频繁修改元素的场景。
 * 内存开销较大，因为每次修改都需要复制数组。
 */

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class E04_ArrayListSet {
    // 自动生成 main 方法
    public static void main(String[] args) {
        // TODO
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        list.add("Apple");
        list.add("Banana");
        list.add("Cherry");

        // 迭代并打印
        for (String item : list) {
            System.out.println(item);
        }

        // 修改集合
        list.add("Date");
        list.remove("Banana");

        // 再次迭代
        System.out.println("After modification:");
        for (String item : list) {
            System.out.println(item);
        }
        /**
         * Apple
         * Banana
         * Cherry
         * After modification:
         * Apple
         * Cherry
         * Date
         */

        CopyOnWriteArraySet<String> set = new CopyOnWriteArraySet<>();
        set.add("Apple");
        set.add("Banana");
        set.add("Cherry");

        // 迭代并打印
        for (String item : set) {
            System.out.println(item);
        }

        // 修改集合
        set.add("Date");
        set.remove("Banana");

        // 再次迭代
        System.out.println("After modification:");
        for (String item : set) {
            System.out.println(item);
        }
        /**
         * Apple
         * Banana
         * Cherry
         * After modification:
         * Apple
         * Cherry
         * Date
         */
    }
}