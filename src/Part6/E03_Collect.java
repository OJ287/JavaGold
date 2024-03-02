package Part6;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class E03_Collect {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		// groupingBy():オーバーロードされている、各メソッドの第一引数を見ると
		// Function（メソッド：R apply(T t)）となる
		// つまり、apply()の引数に要素を渡して、戻り値としてマップのキーとなる値を返すような処理を指定する
		// 同じキーを返せば同じグループに属するという意味
		/*
		 * 構文１：
		 * 		static <T,K> Collector<T,?,Map<K,List<T>>> gropuingBy(Function<? super T,? extends K> classifier)
		 * 
		 * 構文２：
		 * 		static <T,K,A,D> Collector<T,?,Map<K,D>> gropuingBy(Function<? super T,? extends K> classifier,
		 * 													Collector<? super T,A,D> downstream)
		 * 
		 * 構文３：
		 * 		static <T,K,D,A,M extends Map<K,D>> Collector<T,?,M> gropuingBy(
		 * 													Function<? super T,? extends K> classifier,,
		 * 													Supplier<M> mapFactory,
		 * 													Collector<? super T,A,D> downstream)
		 * 
		 */
		// 構文１
		Stream<String> stream1 = Stream.of("belle", "akko", "ami", "bob", "nao", "ami", "bob", "nao");
		Map<String, List<String>> map1 = stream1.collect(Collectors.groupingBy(s -> s.substring(0, 1)));// 頭もじでグルーピング
		System.out.println(map1);//{a=[akko, ami, ami], b=[belle, bob, bob], n=[nao, nao]}
		
		// 構文２,1
		Stream<String> stream2 = Stream.of("belle", "akko", "ami", "bob", "nao", "ami", "bob", "nao");
		Map<String, Set<String>> map2 = stream2.collect(Collectors.groupingBy(s -> s.substring(0, 1), Collectors.toSet()));
		System.out.println(map2);//{a=[akko, ami], b=[bob, belle], n=[nao]}

		// 構文２,2
		Stream<String> stream3 = Stream.of("belle", "akko", "ami", "bob", "nao");
		Map<String, String> map3 = stream3.collect(Collectors.groupingBy(s -> s.substring(0, 1), Collectors.joining()));
		System.out.println(map3);//{a=akkoami, b=bellebob, n=nao}
		System.out.println("map3のクラス名：" + map3.getClass());//map3のクラス名：class java.util.HashMap
		
		// 構文３
		Stream<String> stream4 = Stream.of("belle", "akko", "ami", "bob", "nao");
		Map<String, String> map4 = stream4.collect(Collectors.groupingBy(s -> s.substring(0, 1), TreeMap::new, Collectors.joining()));
		System.out.println(map4);//{a=akkoami, b=bellebob, n=nao}
		System.out.println("map4のクラス名：" + map4.getClass());//map4のクラス名：class java.util.TreeMap
		
		
	}

}

























