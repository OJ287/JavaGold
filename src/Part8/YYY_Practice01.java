package Part8;

/**
 * @author liyanpeng
 * @date 2025/5/4
 * @description TODO
 */

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

/**
 * 答え：
 * 1:AE
 * 2:D
 * 3:BF
 * 4:A→C
 * 5:D
 * 6:C→D
 * 7:DF
 * 8:BEFG
 * 9:ACE
 * 10:C
 * 11:A
 * 12:D→E       ExecutorService一定要实行shutdown（）不然线程一直待机，不会结束
 * 13:CD
 * 14:AC→AD.   Future<?>不仅可以放入null也可以放进异常对象：get的话就是拿出null或者异常对象<?>
 * 15:E→B
 * 16:D
 */
public class YYY_Practice01 {
    // 自动生成 main 方法
    public static void main(String[] args) {
        // TODO
        // 4
        int num = 0;
//        IntStream.iterate(1, i -> 1)
//                .limit(100)
//                .parallel().forEach(i -> ++num);
        System.out.println(num);
        /**
         * /Users/liyanpeng/IdeaProjects/JavaGold/src/Part8/YYY_Practice01.java:38:44
         * java: ラムダ式から参照されるローカル変数は、finalまたは事実上のfinalである必要があります
         */


        // 6
//        int length = Arrays.asList("123", "456", "789")
//                .parallelStream()
//                .reduce(0, (s1, s2) -> s1.length() + s2.length());//第二因数BinaryOperator：(引数の不一致: intをjava.lang.Stringに変換できません:)
//        System.out.println(length);


        // 14
        ExecutorService service = Executors.newSingleThreadExecutor();
        try {
            List<Future<?>> list = new ArrayList<>();
            IntStream.range(0, 10)
                    .forEach(i -> list.add(
                            service.submit(() -> foo(i))));
            list.stream().forEach(f -> show(f));
        } finally {
            service.shutdown();
        }
    }


    static Integer foo(int num) {
        //
        return 0;
    }

    static void show(Future<?> f) {
        try {
            System.out.print(f.get() + " ");
        } catch (Exception e) {
            System.out.print("error ");
        }
    }
}