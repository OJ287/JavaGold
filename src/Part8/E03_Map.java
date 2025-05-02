package Part8;

/**
 * @author liyanpeng
 * @date 2025/5/1
 * @description TODO
 */

/**
 * ╔════════════════════╦════════════════════════════════════════════════════════════════════╗
 * ║ 项目               ║ 内容                                                               ║
 * ╠════════════════════╬════════════════════════════════════════════════════════════════════╣
 * ║ 接口名             ║ ConcurrentMap<K, V>                                                ║
 * ║ 属于               ║ java.util.concurrent                                               ║
 * ║ 主要新增方法       ║ - V putIfAbsent(K key, V value)                                    ║
 * ║                    ║ - boolean remove(Object key, Object value)                         ║
 * ║                    ║ - boolean replace(K key, V oldValue, V newValue)                   ║
 * ║                    ║ - V replace(K key, V value)                                        ║
 * ║ 特点               ║ - 为 Map 增加了线程安全的原子操作                                 ║
 * ╠════════════════════╬════════════════════════════════════════════════════════════════════╣
 * ║ 实现类             ║ ConcurrentHashMap<K, V>                                            ║
 * ║ 属于               ║ java.util.concurrent                                               ║
 * ║ 线程安全机制       ║ 使用分段锁（Java 7）或 CAS + synchronized（Java 8 以后）           ║
 * ║ Null 值支持        ║ key 和 value 都不允许为 null                                       ║
 * ║ 性能特性           ║ - 支持高并发访问                                                   ║
 * ║                    ║ - 大多数操作不会全表加锁，效率优于 Hashtable                      ║
 * ║ 特殊方法支持       ║ - forEach, computeIfAbsent, merge, replaceAll（Java 8 以后）        ║
 * ╚════════════════════╩════════════════════════════════════════════════════════════════════╝
 * <p>
 * ╔══════════════════════════════════════════════╦════════════════════════════════════════════════════════════════════════╗
 * ║ 方法名                                         ║ 说明                                                                   ║
 * ╠══════════════════════════════════════════════╬════════════════════════════════════════════════════════════════════════╣
 * ║ V putIfAbsent(K key, V value)                 ║ 如果指定的 key 当前不存在于 Map 中，则将 key-value 对放入 Map 中并返回 null；如果 key 已存在，则不做任何操作。  ║
 * ╠══════════════════════════════════════════════╬════════════════════════════════════════════════════════════════════════╣
 * ║ boolean remove(Object key, Object value)      ║ 如果 key 对应的值等于指定的 value，则删除该 key-value 对并返回 true；否则返回 false。                       ║
 * ╠══════════════════════════════════════════════╬════════════════════════════════════════════════════════════════════════╣
 * ║ V replace(K key, V value)                     ║ 如果 key 存在，则将该 key 对应的值替换为 value 并返回旧值；如果 key 不存在，则返回 null。                      ║
 * ╠══════════════════════════════════════════════╬════════════════════════════════════════════════════════════════════════╣
 * ║ boolean replace(K key, V oldValue, V newValue) ║ 如果 key 存在且值等于 oldValue，则用 newValue 替换原值并返回 true；否则返回 false。                        ║
 * ╠══════════════════════════════════════════════╬════════════════════════════════════════════════════════════════════════╣
 * ║ V put(K key, V value)                        ║ 如果 key 已经存在，则替换旧的 value 并返回旧值；如果 key 不存在，则插入新的 key-value 对并返回 null。  ║
 * ╠══════════════════════════════════════════════╬════════════════════════════════════════════════════════════════════════╣
 * ║ V remove(Object key)                         ║ 如果 key 存在，则移除该 key 对应的 value 并返回 value；如果 key 不存在，则返回 null。                   ║
 * ╚══════════════════════════════════════════════╩════════════════════════════════════════════════════════════════════════╝
 * <p>
 * <p>
 * -------------
 * ║ void forEach(BiConsumer<? super K, ? super V> action) ║ 遍历 Map 中的每一个键值对，并对每一对执行给定的 action 操作。                                    ║
 * ╠══════════════════════════════════════════════╬════════════════════════════════════════════════════════════════════════╣
 * ║ V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction) ║ 如果 key 不存在，则根据 mappingFunction 计算并添加 value；否则直接返回已存在的 value。 ║
 * ╠══════════════════════════════════════════════╬════════════════════════════════════════════════════════════════════════╣
 * ║ V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) ║ 如果 key 存在，则根据 remappingFunction 更新值；否则返回 null。                            ║
 * ╠══════════════════════════════════════════════╬════════════════════════════════════════════════════════════════════════╣
 * ║ V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction) ║ 如果 key 存在，合并现有值和给定的 value；如果不存在，则直接添加新的 key-value 对。        ║
 * ╠══════════════════════════════════════════════╬════════════════════════════════════════════════════════════════════════╣
 * ║ void replaceAll(BiFunction<? super K, ? super V, ? extends V> function) ║ 遍历 Map 中的所有键值对，并通过 function 来更新每个值。                                     ║
 * ╚══════════════════════════════════════════════╩════════════════════════════════════════════════════════════════════════╝
 */

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class E03_Map {
    // 自动生成 main 方法
    public static void main(String[] args) {
        // TODO
        // 创建一个 ConcurrentMap
        ConcurrentMap<String, Integer> map = new ConcurrentHashMap<>();

        // 向 map 中添加一些初始数据
        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);

        // 使用 forEach 遍历 map，执行 put 操作
        map.forEach((key, value) -> {
            // 假设我们需要将所有值乘以 10
            map.put(key, value * 10);
        });

        // 输出修改后的 map
        System.out.println("Modified map: " + map);

        // 使用 forEach 遍历 map，执行 remove 操作
        map.forEach((key, value) -> {
            // 假设我们需要删除键为 "B" 的条目
            if ("B".equals(key)) {
                map.remove(key);
            }
        });

        // 输出修改后的 map
        System.out.println("Map after removal: " + map);
        /**
         * Modified map: {A=10, B=20, C=30}
         * Map after removal: {A=10, C=30}
         */
    }
}