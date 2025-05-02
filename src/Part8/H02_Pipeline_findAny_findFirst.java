package Part8;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author liyanpeng
 * @date 2025/5/3
 * @description TODO
 */

/**
 * 在并行流（parallelStream）中，findFirst、limit 和 skip 的行为与顺序流不同，它们会带来额外的性能开销以保证正确性。
 * <p>
 * 1. findFirst vs findAny
 * (1) findFirst
 * 保证返回第一个元素（按原始顺序），即使在并行流中。
 * 性能开销：
 * 并行流中需要协调线程，确保最终返回的是 原始顺序的第一个元素（即使其他元素先处理完）。
 * <p>
 * (2) findAny
 * 返回任意一个元素（可能是最快处理完的）。
 * 性能更高：
 * 无需保证顺序，直接返回首个完成的线程结果。
 * <p>
 * +----------------+-------------------+-------------------+
 * |                | 顺序流            | 并行流            |
 * +================+===================+===================+
 * | Limit(n)       | 直接截取，O(1)    | 全局协调，O(n)    |
 * +----------------+-------------------+-------------------+
 * | Skip(n)        | 直接跳过，O(1)    | 位置计算，O(n)    |
 * +----------------+-------------------+-------------------+
 * | 线程安全       | 无需考虑          | 自动处理          |
 * +----------------+-------------------+-------------------+
 * | 性能           | 高效              | 额外开销          |
 * +----------------+-------------------+-------------------+
 */
public class H02_Pipeline_findAny_findFirst {
    // 自动生成 main 方法
    public static void main(String[] args) {
        // TODO
        List<String> data = Arrays.asList("c", "a", "d", "b");
        Optional<String> rerult1 = data.parallelStream().findFirst();
        Optional<String> rerult2 = data.parallelStream().findAny();
        System.out.println(rerult1.get() + " " + rerult2.get());
        /**
         * 1:
         * c d
         *
         * 2:
         *c b
         */
    }
}