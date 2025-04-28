package Part6;

import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class D01_StreamIFTypeChange {

	/*
	 * Streamインタフェースでは、型パラメータの指定により
	 * 様々な型の要素に対するストリームを作成可能である
	 * 		Stream<String> stream33 = Stream.of("naoki", "akko", "ami");
	 * 		Stream<Integer> stream34 = stream33.map(s -> s.length());
	 * ストリームインタフェース型の変換を行う場合は、mapToXXX()を使用する
	 * XXXには変換後の型名が入り
	 * 
	 * IF名			Streamの生成		DoubleStreamの生成		IntStreamの生成		LongStreamの生成
	 * Stream		map()			mapToDouble()			mapToInt()			mapToLong()
	 * DoubleStream	mapToObj()		map()					mapToInt()			mapToLong()
	 * IntStream	mapToObj()		mapToDouble()			map()				mapToLong()
	 * LongStream	mapToObj()		mapToDouble()			mapToInt()			map()
	 * ✴︎引数を省略
	 * 例：
	 * 	Stream<String> → IntStream
	 *  Stream<String> stream21 = Stream.of("naoki","akko","ami");
	 *  IntStream stream22 = stream21.mapToInt(s -> s.length());
	 *  
	 *  ストリームインタフェースの型変換を行うメソッドの使用例
	 *  　　　元の型　　　　　　メソッド　　　　引数			                     変更後の型（戻り値）
	 *  ①：Stream<String> →  map → Function<? super T,? extends R> mapper     Stream<Integer>
	 *  ②：Stream<String> →  mapToInt → ToIntFunction<? super T> mapper       IntStream
	 *  ③：Stream<Integer> →  mapToInt → ToIntFunction<? super T> mapper      IntStream
	 *  ④：Stream<Integer> →  map → Function<? super T,? extends R> mapper    Stream<String>
	 *  ⑤：IntStream →  mapToObj → IntFunction<? extends U> mapper            Stream<String>
	 *  ⑥：IntStream →  boxed → なし                                           Stream<Integer>
	 *  	各基本型のストリームにはboxedが提供される
	 *  	IntStream → Stream<Integer>
	 *  	DoubleStream → Stream<Double>
	 *  	LongStream → Stream<Long>
	 *  	ストリームに関連する型はint,long,doubleのみ
	 *  引数：
	 *  Function<T,R>           R apply(T t)                 map()的参数
	 *  ToIntFunction<T>        int applyAsInt(T value)      mapToXXX()的参数
	 *  IntFunction<R>          R apply(int value)           mapToObj()的参数
	 *
	 *  double型については、暗黙の型変換はないが、IntStream,LongStreamに提供されるメソッド
	 *  LongStream asLongStream   IntStreamインタフェースで提供。要素をlongに変換した結果から構成されるLongStreamを返す
	 *  DoubleStream asDoubleStream   IntStreamインタフェースで提供。要素をdoubleに変換した結果から構成されるDoubleStreamを返す
	 *  LongStream asLongStream   LongStreamインタフェースで提供。要素をdoubleに変換した結果から構成されるDoubleStreamを返す
	 *  
	 */
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		// ストリームインタフェースの型変換を行うメソッドの使用例
		Stream<String> stream1i = Stream.of("a", "b");
		Stream<Integer> stream1o = stream1i.map(s -> s.length());

		Stream<String> stream2i = Stream.of("naoki", "akko", "ami");
		IntStream stream2o = stream2i.mapToInt(s -> s.length());
		
		Stream<Integer> stream3i = Stream.of(1, 2, 3);
		IntStream stream3o = stream3i.mapToInt(n -> n);

		Stream<Integer> stream4i = Stream.of(1, 2, 3);
		Stream<String> stream4o = stream4i.map(n -> n + "a");
		
		IntStream stream5i = IntStream.of(1, 2, 3);
		Stream<String> stream5o = stream5i.mapToObj(n -> n + "a");

		IntStream stream6i = IntStream.of(1, 2, 3);
		Stream<Integer> stream6o = stream6i.boxed();
		
		// IntStream,LongStreamに提供されるメソッド
		// IntStream → DoubleStream  mapToDouble
		IntStream stream21i = IntStream.of(5, 2, 3);
		DoubleStream stream21o = stream21i.mapToDouble(n -> n);
		System.out.println(stream21o.average().getAsDouble());// 3.3333333333333335
		// IntStream → DoubleStream  asDoubleStream
		IntStream stream22i = IntStream.of(5, 2, 3);
		DoubleStream stream22o = stream22i.asDoubleStream();
		System.out.println(stream22o.average().getAsDouble());// 3.3333333333333335
		
		
		
		
		

	}

}






















