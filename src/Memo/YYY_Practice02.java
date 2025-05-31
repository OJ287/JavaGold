package Memo;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author liyanpeng
 * @date 2025/5/26
 * @description TODO
 */
public class YYY_Practice02 {
    // 自动生成 main 方法
    public static void main(String[] args) {
        // TODO

        Stream<String> stream1 = Stream.of("a", "b", "ax");
        Stream<String> stream3 = Stream.of();
        Stream<String> stream2 = Stream.empty();
        Map<Boolean, List<String>> map1 =
                stream1.collect(Collectors.partitioningBy(s -> s.startsWith("x")));
        Map<Boolean, List<String>> map3 =
                stream3.collect(Collectors.partitioningBy(s -> s.startsWith("x")));
        Map<Boolean, List<String>> map2 =
                stream2.collect(Collectors.groupingBy(s -> s.startsWith("x")));
        System.out.println(map1);
        System.out.println(map3);
        System.out.println(map2);
        /**
         * {false=[a, b, ax], true=[]}
         * {false=[], true=[]}
         * {}
         */
    }
}