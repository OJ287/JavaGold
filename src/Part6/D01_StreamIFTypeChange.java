package Part6;

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
	 */
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

	}

}






















