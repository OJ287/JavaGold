package Part6;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class E02_Collect {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		// toSet()
		Stream<String> stream1 = Stream.of("naoki", "akko", "ami");
		Set<String> set = stream1.collect(Collectors.toSet());
		System.out.println(set);//[naoki, akko, ami]
		
		// toMap()
		Stream<String> stream2 = Stream.of("naoki", "akko", "ami");
		Map<String, String> map = stream2.collect(Collectors.toMap(s -> s, String :: toUpperCase));
		System.out.println(map);//{naoki=NAOKI, akko=AKKO, ami=AMI}
		
		// toMap()はオーバーロードされており
		/*
		 * 構文１：
		 * 		static <T,K,U> Collector<T,?,Map<K,U>> toMap(Function<? super T,? extends K> keyMapper,
		 * 										             Function<? super T,? extends U> valueMapper)
		 * 
		 * 構文２：
		 * 		static <T,K,U> Collector<T,?,Map<K,U>> toMap(Function<? super T,? extends K> keyMapper,
		 * 										             Function<? super T,? extends U> valueMapper,
		 * 										             BinaryOperator<U> mergeFunction)
		 * 
		 * 構文３：
		 * 		static <T,K,U,M extends Map<K,U>> Collector<T,?,M> toMap(Function<? super T,? extends K> keyMapper,
		 * 										             Function<? super T,? extends U> valueMapper,
		 * 										             BinaryOperator<U> mergeFunction,
		 * 										             Supplier<M> mapSupplier)
		 * 
		 */
		/*
		 * naoとamiは同じ３文字
		 * toMapでは、マップ先のキーが重複する場合、実行時にIllegalStateExceptionがスローされる
		 */
		Stream<String> stream3 = Stream.of("nao", "akko", "ami");
//		Map<Integer, String> map3 = stream3.collect(Collectors.toMap(s -> s.length(), s -> s));//Exception in thread "main" java.lang.IllegalStateException: Duplicate key 3 (attempted merging values nao and ami)
//		System.out.println(map3);
		
		/*
		 * 構文２のtoMapを使用すると、キーが重複している場合は、
		 * マッピングしてマージした結果を返すことができる
		 * BinaryOperator：キーが同じだった場合のマージ処理を指定する
		 *　また、マージ結果を別のマップに格納する場合は構文３のtoMapを使用する
		 */
		Stream<String> stream4 = Stream.of("nao", "akko", "ami");
		Map<Integer, String> map4 = stream4.collect(Collectors.toMap(String::length, s -> s, (s1, s2) -> s1 + ":" + s2));
		System.out.println(map4);//{3=nao:ami, 4=akko}
		System.out.println(map4.getClass());//class java.util.HashMap

		Stream<String> stream5 = Stream.of("nao", "akko", "ami");
		Map<Integer, String> map5 = stream5.collect(Collectors.toMap(String::length, s -> s, (s1, s2) -> s1 + ":" + s2, TreeMap::new));
		System.out.println(map5);//{3=nao:ami, 4=akko}
		System.out.println(map5.getClass());//class java.util.TreeMap

	}

}





















