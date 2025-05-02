package Part8;

/**
 * @author liyanpeng
 * @date 2025/5/2
 * @description TODO
 */

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 🧵 parallelStream 简介
 * 在 Java 中，parallelStream() 是 Java 8 引入的 Stream API 的一部分，用于 并行处理数据流。
 * 相较于普通的 stream()（串行处理），parallelStream() 会将任务分解为多个子任务，并利用多核 CPU 并行执行，从而提升处理性能。
 * 并行流基于 Fork/Join 框架 实现，背后使用的是 ForkJoinPool.commonPool() 线程池。
 * <p>
 * ✅ 示例
 * List<String> list = Arrays.asList("a", "b", "c", "d");
 * list.parallelStream().forEach(System.out::println);
 * <p>
 * 💡 补充说明
 * 串行流（Serial Stream）：按顺序一个一个处理。
 * 并行流（Parallel Stream）：拆分为多个子流，同时在不同线程中执行。
 * 适用场景：适用于无状态、无顺序依赖、大数据量处理；对于小数据或有顺序要求的处理反而可能更慢或出错。
 * <p>
 * <p>
 * 💡注意事项（考试重点！）
 * 避免副作用：并行流中的 Lambda 应是无状态的（如不要修改外部变量）。
 * 谨慎使用 forEach：并行流中 forEach 顺序不确定，优先用 collect。
 * 性能测试：并行流不一定更快，需实际测量（上下文切换可能抵消优势）。
 * Q: 如何强制并行流按顺序处理？
 * A: 使用 forEachOrdered 而非 forEach。
 * <p>
 * <p>
 * parallelStream的生成
 * 【Collection 接口】
 * +----------------------------+-------------------------------------------+----------------+
 * | 方法名                     | 说明                                      | 返回值类型     |
 * +----------------------------+-------------------------------------------+----------------+
 * | parallelStream()           | 返回一个并行流，用于并行处理集合中的元素   | Stream<E>      |
 * +----------------------------+-------------------------------------------+----------------+
 * <p>
 * 【BaseStream 接口】
 * +----------------------------+-------------------------------------------+----------------+
 * | parallel()                 | 返回一个并行流实例，原本是并行流则返回自身 | BaseStream     |
 * | isParallel()               | 判断当前流是否是并行流                     | boolean        |
 * | sequential()               | 将当前流标记为串行流                       | BaseStream     |
 * +----------------------------+-------------------------------------------+----------------+
 */
public class H01_ParallelStream {
    // 自动生成 main 方法
    public static void main(String[] args) {
        // TODO
        List<String> list = Arrays.asList("aaa", "bb", "c");
        Stream<String> stream1 = list.parallelStream();
        System.out.println("stream1 : " + stream1.isParallel());
        Stream<String> stream2 = list.stream();
        System.out.println("stream2 : " + stream2.isParallel());
        Stream<String> stream3 = stream2.parallel();
        System.out.println("stream3 : " + stream3.isParallel());
        /**
         * stream1 : true
         * stream2 : false
         * stream3 : true
         */
        /**
         * [初始Stream]
         *    |
         *    |--- [调用 .parallel()] ---> [并行Stream]
         *    |      (触发ForkJoinPool并行执行)
         *    |
         *    |--- [调用 .sequential()] --> [顺序Stream]
         *           (恢复单线程顺序执行)
         *
         * +------------------+       .sequential()       +------------------+
         * |  顺序 Stream     | <------------------------- | 并行 Stream      |
         * |  (stream())      |                           | (parallelStream())|
         * +------------------+ -------------------------> +------------------+
         *                           .parallel()
         */


        Arrays.asList("a", "b", "c", "d", "e")
                .stream()
                .forEach(s -> System.out.print(s + " "));
        System.out.println();
        Arrays.asList("a", "b", "c", "d", "e")
                .parallelStream()
                .forEach(s -> System.out.print(s + " "));

        /**
         * parallelStream顺序不确定
         *
         * 第一次执行
         * a b c d e
         * a b c e d
         * 第二次执行
         * a b c d e
         * c a d e b
         */


        System.out.println("commonPool : " + ForkJoinPool.commonPool()
                .getParallelism());
        IntStream.range(0, 100).parallel()
                .forEach(i -> System.out.println(
                        Thread.currentThread().getName() + ": " + i));
        /**
         * (1) 并行流的行为
         * 线程分配：任务由 ForkJoinPool.commonPool() 的线程执行。
         * 输出乱序：因线程并发执行，数字打印顺序随机（如 worker-1: 3, worker-2: 1）。
         * 线程复用：同一个线程可能处理多个数字（取决于任务拆分策略）。
         *System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "4");
         *
         *
         * 4. 考试常见问题
         * Q: 如何控制并行流的线程数？
         * A: 自定义 ForkJoinPool 提交任务（非考试重点，但需知道）：
         * java
         * ForkJoinPool customPool = new ForkJoinPool(4);
         * customPool.submit(() -> IntStream.range(0,100).parallel().forEach(...));
         *
         * Q: 并行流是否总是更快？
         * A: 不一定！小数据量或高线程协调开销时可能更慢。
         *
         *  Q: 哪些操作会破坏并行性能？
         * A: 有状态操作（如 sorted）、阻塞操作（如 I/O）或共享变量修改。
         */
        /**
         * commonPool : 7
         * ForkJoinPool.commonPool-worker-9: 15
         * ForkJoinPool.commonPool-worker-9: 16
         * ForkJoinPool.commonPool-worker-13: 43
         * main: 65
         * ForkJoinPool.commonPool-worker-15: 40
         * ForkJoinPool.commonPool-worker-9: 17
         * ForkJoinPool.commonPool-worker-15: 41
         * ForkJoinPool.commonPool-worker-15: 42
         * ForkJoinPool.commonPool-worker-3: 56
         * ForkJoinPool.commonPool-worker-3: 57
         * ForkJoinPool.commonPool-worker-3: 58
         * ForkJoinPool.commonPool-worker-15: 37
         * ForkJoinPool.commonPool-worker-15: 38
         * ForkJoinPool.commonPool-worker-15: 39
         * main: 66
         * main: 67
         * ForkJoinPool.commonPool-worker-9: 12
         * main: 62
         * main: 63
         * main: 64
         * ForkJoinPool.commonPool-worker-13: 44
         * ForkJoinPool.commonPool-worker-13: 45
         * ...
         */
    }
}
































