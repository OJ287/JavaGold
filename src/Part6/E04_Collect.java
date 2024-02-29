package Part6;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class E04_Collect {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		/* partitioningBy()
		* groupingBy()と同様に指定した条件に従ってグルーピングを行う
		* 異なる点はgroupingBy()はグループ化の処理をFunction型で行う
		* partitioningBy()はPredicate型で行う、つまり戻り値がBoolean値となる
		* partitioningBy()は二つ提供される
		* 
		* 
		* 構文１：
		* 		static <T> Collector<T,?,Map<Boolean,List<T>>> partitioningBy(Predicate<? super T> predicate)
		* 
		* 構文２：
		* 		static <T,D,A> Collector<T,?,Map<Boolean,D>> partitioningBy(Predicate<? super T> predicate, 
		* 															Collector<? super T,A,D> downstream)
		*/
		
		// 構文１,1
		Stream<Integer> stream1 = Stream.of(3, 5, 7, 9);
		Map<Boolean, List<Integer>> map1 = stream1.collect(Collectors.partitioningBy(s -> s > 5));
		System.out.println(map1);//{false=[3, 5], true=[7, 9]}

		// 構文１,2
		Stream<Integer> stream2 = Stream.of(3, 5, 7, 9);
		Map<Boolean, List<Integer>> map2 = stream2.collect(Collectors.partitioningBy(s -> s > 10));
		System.out.println(map2);//{false=[3, 5, 7, 9], true=[]}
		
		// 構文2
		Stream<Integer> stream3 = Stream.of(3, 5, 7, 9);
		Map<Boolean, Integer> map3 = stream3.collect(Collectors.partitioningBy(s -> s > 5,
				Collectors.summingInt(n -> n)));
		System.out.println(map3);//{false=8, true=16}
		
	}

}
























