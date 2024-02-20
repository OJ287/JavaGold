package Part6;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 中間操作：
 * 	いてれションは終端操作時でのみ実行される
 * 		中間操作ではメソッドを使用して処理の登録だけを行っておく
 */
/**
 * 中間操作の主なメソッド
 * 	メソッド名					説明
 * 	Stream<T> filter(Predicate<? super T> predicate)	指定された条件に一致するものから構成されるストリームを返す
 * 	Stream<T> distinct()		重複を除いた要素から構成されたストリームを返す	
 * 	Stream<T> limit(long macSize) maxSize以内の長さに切り詰めた結果から構成されたストリームを返す
 * 	Stream<T> skip(long n)		先頭からn個の要素を破棄した残りの要素で構成されたストリームを返す
 * 	<R> Stream<R> map(Function<? super T, ? extends R> mapper) 指定された関数を適用した結果から構成されたストリームを返す
 * 	<R> Stream<R> flatMap(Function<? super T, ? super Stream<? extends R>> mapper) 指定された関数を適用した結果から構成される一つのストリームを返す
 *  Stream<T> sorted()			自然順序に従ってソートした結果から構成されたストリームを返す
 *  Stream<T> sorted(Comparator<? super T> comparator)	指定されたComparatorに従ってソートした結果から構成されたストリームを返す
 *  Stream<T> peek(Consumer<? super T> action)	このストリームの要素から成るストリームを返す
 *  	要素がパイプラインを通過する際にその内容を確認するようなデバッグとして使用する
 * 
 */

public class C01_StreamIntermediateOperations {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		//filter,distinct
		Stream<String> stream1 = Stream.of("ami", "naoki", "akko");
		stream1.filter(s -> s.startsWith("a"))
		.forEach(x -> System.out.print(x + "  "));//ami  akko 
		System.out.println();
		
		Stream<String> stream2 = Stream.of("", "naoki", "akko");
		stream2.filter(Predicate.not(s -> s.isEmpty())).forEach(x -> System.out.print(x + "  "));//naoki  akko 
		System.out.println();

		Stream<String> stream3 = Stream.of("ami", "naoki", "akko", "ami");
		stream3.distinct().forEach(x -> System.out.print(x + "  "));//ami  naoki  akko
		System.out.println();
		
		//limit,skip
		IntStream.iterate(1, n -> n+1)//無限ストリームを返す
		.limit(10L)
		.forEach(x -> System.out.print(x + "  "));//1  2  3  4  5  6  7  8  9  10  
		System.out.println();

		IntStream.rangeClosed(1, 10).skip(5)
		.forEach(x -> System.out.print(x + "  "));//6  7  8  9  10  
		System.out.println();

		IntStream.iterate(1, n -> n+1).skip(100).limit(5L)
		.forEach(x -> System.out.print(x + "  "));//101  102  103  104  105  
		System.out.println();

		Stream<String> stream21 = Stream.generate(() -> "Java");//無限ストリームを返す
		stream21.limit(3L)
		.forEach(x -> System.out.print(x + "  "));//Java  Java  Java
		System.out.println();
		
		//map
		//Stream<String> → Stream<String>
		Stream<String> stream31 = Stream.of("naoki", "akko", "ami");
		Stream<String> stream32 = stream31.map(s -> s.toUpperCase());
		stream32.forEach(x -> System.out.print(x + " "));//NAOKI AKKO AMI 
		System.out.println();
		
		//Stream<String> → Stream<Integer>
		Stream<String> stream33 = Stream.of("naoki", "akko", "ami");
		Stream<Integer> stream34 = stream33.map(s -> s.length());
		stream34.forEach(x -> System.out.print(x + " "));//5 4 3 
		System.out.println();
		
		//Integer → Integer
		/*
		 * DoubleStream,LongStreamでも同様な記述が可能
		 */
		IntStream stream35 = IntStream.of(1,2,3);
		IntStream stream36 = stream35.map(n -> n * 10);
		stream36.forEach(x -> System.out.print(x + " "));//10 20 30 
		
		//flatMap
		List<Integer> data1 = Arrays.asList(10);
		List<Integer> data2 = Arrays.asList(20, 30);
		List<Integer> data3 = Arrays.asList(40,50,60);
		List<List<Integer>> dataList = Arrays.asList(data1,data2,data3);
		System.out.println();
		//mapを使用
		dataList.stream().map(data -> data.stream()).forEach(l -> {
			l.forEach(x -> System.out.print(x + " "));//10 20 30 40 50 60 
		});
		System.out.println();
		//faltMapを使用する
		dataList.stream().flatMap(data -> data.stream())
		.forEach(x -> System.out.print(x + " "));//10 20 30 40 50 60 
		/*
		 * map
		 * data1:10
		 * data2:20, 30
		 * data3:40, 50, 60
		 * 
		 * flatMapを使用：平坦化にする
		 * 10,20,30,40,50,60
		 */
		System.out.println();
		
		//sorted
		Stream.of("naoki", "akko", "ami")
		.sorted()
		.forEach(x -> System.out.print(x + " "));//akko ami naoki 
		System.out.println();

		Stream.of("naoki", "akko", "ami")
		.sorted(Comparator.reverseOrder())
		.forEach(x -> System.out.print(x + " "));//naoki ami akko 
		System.out.println();
		
		//peek
		/*
		 * After the filter   :three
		 * After the distinct   :three
		 * After the distinct   :THREE
		 * After the filter   :three
		 * After the filter   :four
		 * After the distinct   :four
		 * After the distinct   :FOUR
		 * 139行目では3文字を超える単話を抽出し、10行目では重後する単語があれば開し、
		 * 12行目では要素に対して大文字に変換しています。実行結果を見ると、地理される順番は、
		 * 1つずつの要素に対して各処理が実行されていることがわおります。
		 * そして、8行目のfilterc）メソッドによる条件外の要素（oneやtW）は、
		 * 9行目以降が実行されていないこと、また、10行目のdistinct（）による条件外の要素（2つのthreeのうち1つ）は、
		 * 12行目以降が実行されていないことを確認してください。つまり、全要素をスキャンしているのは1回だけであることがわかります。
		 */
		List<String> list =Stream.of("one", "three", "two", "three", "four")
				.filter(s -> s.length() > 3)
				.peek(e -> System.out.println("After the filter   :"  + e))
				.distinct()
				.peek(e -> System.out.println("After the distinct   :"  + e))
				.map(String::toUpperCase)
				.peek(e -> System.out.println("After the distinct   :"  + e))
				.collect(Collectors.toList());
		
		
		
		
		
		
	}

}




















