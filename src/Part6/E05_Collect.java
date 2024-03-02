package Part6;

import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class E05_Collect {

	public static void main(String[] args) {
		/*
		 * mapping()
		 * Streamインタフェースの中間操作のmap()と同様に、ストリームの各要素に対して行いたい処理を指定する
		 * 正し、引数を二つ持っている
		 * 第一引数に要素に対して行たい処理
		 * 第二引数はマップ後に行たい処理を指定
		 */
		// map()
		Stream<String> stream1 = Stream.of("naoki", "akko", "ami");
		String result1 = stream1.map(s -> s.toUpperCase()).collect(Collectors.joining());
		System.out.println(result1);//NAOKIAKKOAMI
		
		// mapping()
		Stream<String> stream2 = Stream.of("naoki", "akko", "ami");
//		String result2 = stream2.collect(Collectors.mapping(s -> s.toUpperCase(), Collectors.joining()));
		String result2 = stream2.collect(Collectors.mapping(String::toUpperCase, Collectors.joining()));
		System.out.println(result2);//NAOKIAKKOAMI
		
		/*
		 * minBy()
		 * maxBy()
		 * 戻り値がCollector<T,?,Optional<T>>であるため、collect()の戻り値を格納する型はoptional<T>となる
		 */
		// minBy()
		Stream<String> stream3 = Stream.of("naoki", "akko", "ami");
		Optional<String> result3 = stream3.collect(Collectors.minBy(Comparator.naturalOrder()));
		System.out.println(result3.get());//akko
		
		// maxBy()
		Stream<String> stream4 = Stream.of("101", "103", "105", "106", "203", "2021", "204");
		Map<String,Optional<String>> result4 = stream4.collect(Collectors.groupingBy(s -> s.substring(0, 1),Collectors.maxBy(Comparator.naturalOrder())));
		System.out.println(result4);//{1=Optional[106], 2=Optional[204]}
		
	}
}





























