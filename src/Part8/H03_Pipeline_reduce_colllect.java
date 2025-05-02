package Part8;

/**
 * @author liyanpeng
 * @date 2025/5/3
 * @description TODO
 */

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * reduce
 * <U> U reduce(U identity,
 * BiFunction<U,? super T,U> accumulator,
 * BinaryOperator<U> combiner)
 * 参数说明：
 * identity：初始值（对于加法为0，乘法为1）
 * accumulator：累加器函数（合并元素到部分结果）
 * combiner：组合器函数（合并并行计算的中间结果）组合器（combiner）合并分片结果时可能任意顺序
 * <p>
 * collect
 * <R> R collect(Supplier<R> supplier,
 * BiConsumer<R, ? super T> accumulator,
 * BiConsumer<R, R> combiner)
 * 参数说明：
 * Supplier<R> supplier：
 * 创建一个新的结果容器（如 new ArrayList<>()）
 * 在并行流中会被调用多次
 * BiConsumer<R, ? super T> accumulator：
 * 将元素累积到结果容器中（如 List::add）
 * 签名：(容器, 元素) -> void
 * BiConsumer<R, R> combiner：
 * 合并两个结果容器（并行流专用）
 * 签名：(容器1, 容器2) -> void
 * <p>
 * Q：为什么 collect 更适合可变累积？
 * A：因为 reduce 要求不可变操作（每次返回新对象），而 collect 允许直接修改容器，性能更高且更直观。
 * <p>
 * +------------------+-----------------------------+-----------------------------+
 * |     比较项       |          collect           |          reduce            |
 * +==================+=============================+=============================+
 * |  主要目的        | 可变结果累积（如集合操作） | 不可变结果合并（如数值计算） |
 * +------------------+-----------------------------+-----------------------------+
 * |  初始值/容器     | 通过Supplier每次新建容器    | 固定初始值（可能被重复使用） |
 * +------------------+-----------------------------+-----------------------------+
 * |  累积方式        | 直接修改容器（如List.add）  | 必须返回新对象（如a+b）      |
 * +------------------+-----------------------------+-----------------------------+
 * |  并行流线程安全  | 需自行保证容器线程安全      | 需满足结合律和恒等值         |
 * +------------------+-----------------------------+-----------------------------+
 * |  典型用例        | 收集到集合/字符串拼接       | 求和/求极值/不可变合并       |
 * +------------------+-----------------------------+-----------------------------+
 * |  合并操作触发    | 仅在并行流调用combiner      | 并行流必须实现combiner       |
 * +------------------+-----------------------------+-----------------------------+
 * |  Javadoc建议     | 推荐用于可变累积操作        | 推荐用于不可变归约操作       |
 * +------------------+-----------------------------+-----------------------------+
 */
public class H03_Pipeline_reduce_colllect {
    // 自动生成 main 方法
    public static void main(String[] args) {
        // TODO
        Integer total = Arrays.asList(10, 20, 30, 40, 50)
                .parallelStream()
                .reduce(0,
                        (sum, a) -> {
                            System.out.println("sum:" + sum + " a:" + a);
                            return sum += a;
                        },
                        (b, c) -> {
                            System.out.println("b:" + b + " c:" + c);
                            return b + c;
                        });
        System.out.println("total : " + total);
        /**
         * 输出顺序不确定性：
         * System.out.println的输出顺序不能保证
         * 但最终结果总是正确的（150）
         *
         *
         * sum:0 a:30
         * sum:0 a:20
         * sum:0 a:10
         * sum:0 a:40
         * sum:0 a:50
         * b:10 c:20
         * b:40 c:50
         * b:30 c:90
         * b:30 c:120
         * total : 150
         *
         *
         * [初始数据]
         * |10|20|30|40|50|
         *
         * [并行分片]
         * 线程1: |10|20| → 计算: 0+10+20=30
         * 线程2: |30|    → 计算: 0+30=30
         * 线程3: |40|50| → 计算: 0+40+50=90
         *
         * [合并过程]
         *       30 (线程1)10＋20+0
         *         \
         *         30 (线程2)30+0
         *           \
         *            90 (线程3)40＋50+0
         *             \
         *             150 (最终结果)
         *
         * .reduce(10,初始值是10的话出力
         * sum:10 a:10
         * sum:10 a:50
         * sum:10 a:40
         * sum:10 a:30
         * sum:10 a:20
         * b:50 c:60
         * b:20 c:30
         * b:40 c:110
         * b:50 c:150
         * total : 200
         * 这是parallelStream reduce的已知特性：
         * 初始值会被每个分片复制使用
         * 导致初始值被多次累加
         *
         * 解决方案：
         * int total = 10 + Arrays.asList(10,20,30,40,50)
         *                     .parallelStream()
         *                     .reduce(0, Integer::sum);
         *  或者使用顺序流：
         * int total = Arrays.asList(10,20,30,40,50)
         *                 .stream()
         *                 .reduce(10, Integer::sum);
         */


        List<String> data = Arrays.asList("orange", "banana", "lemon");
        List<String> list =
                data.parallelStream()
                        .collect(() -> new CopyOnWriteArrayList<>(),
                                (plist, s) -> plist.add(s.toUpperCase()),
                                (alist, blist) -> alist.addAll(blist));
        for (String s : list) {
            System.out.print(s + " ");
        }

        System.out.println();
        Set<String> set =
                data.parallelStream()
                        .collect(CopyOnWriteArraySet::new,
                                Set::add,
                                Set::addAll);
        for (String e : set) {
            System.out.print(e + " ");
        }
        /**
         * ORANGE BANANA LEMON
         * orange banana lemon
         */
    }
}