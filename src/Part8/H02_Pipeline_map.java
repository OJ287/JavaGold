package Part8;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author liyanpeng
 * @date 2025/5/2
 * @description TODO
 */
public class H02_Pipeline_map {
    // 自动生成 main 方法
    public static void main(String[] args) {
        // TODO
        Arrays.asList("hana", "ken", "mika")
                .stream()
                .map(s -> s.toUpperCase())
                .forEach(s -> System.out.print(s + " "));
        System.out.println();
        Arrays.asList("hana", "ken", "mika")
                .parallelStream()
                .map(
                        s -> {
                            System.out.print(s + " ");
                            return s.toUpperCase();
                        })
                .forEach(s -> System.out.print(s + " "));
        /**
         * parallelStream中
         * 首先forEach不是顺序确定
         * 其次，forEach和map是分别在各自线程处理，所以这两个处理的顺序可能也不一样
         *
         *
         * 第一次
         * HANA KEN MIKA
         * ken KEN mika MIKA hana HANA
         * 第二次
         * HANA KEN MIKA
         * ken mika KEN MIKA hana HANA
         *
         */


        System.out.println();
        Arrays.asList("a", "b", "c", "d", "e")
                .parallelStream()
                .forEachOrdered(s -> System.out.print(s + " "));
        System.out.println();
        List<String> list = new CopyOnWriteArrayList<String>();
        Arrays.asList("a", "b", "c", "d", "e")
                .parallelStream()
                .map(s -> {
                    list.add(s);
                    return s.toUpperCase();
                })
                .forEachOrdered(s -> System.out.print(s + " "));
        System.out.println();
        for (String s : list) {
            System.out.print(s + " ");
        }
        /**
         * a b c d e      forEachOrdered按顺序拿取元素进行处理
         * A B C D E      forEachOrdered按顺序拿取元素进行处理
         * c b a e d      map顺序不确定的拿取元素进行处理
         */
    }
}