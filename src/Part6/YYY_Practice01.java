package Part6;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.DoubleBinaryOperator;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;


/**
 * 答え：
 *
 * 第一次复习:BE    CEF    D→F    EF    F→D    E    E→F    BD→AD    E→A    A→C    BF    E    D    ??F→ABF    B→C    D    A    BF    B?→AB     E→D
 *
 * 	1:BE
 *  2:CEF
 *  3×:D　→　F
 *  4×:CE　→　EF
 *  5×:F　→　D
 *  6:E
 *  7×:C　→　F
 *  8:AD
 *  9:A
 *  10×:B　→　C
 *  11:BF
 *  12:E
 *  13×:A　→　D
 *  14×:F　→　ABF
 *  15×:　→　C
 *  16×:A　→　D
 *  17:A
 *  18:BF
 *  19×:AD　→　AB
 *  20:D
 */

public class YYY_Practice01 {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		
		// No.3 merge()
		/*
		 * merge（）メソッドは、第1引数にキー、第2引数に値、第3引数にBiFunction型をとります。
		 * 問題文のmerge１行目を例にすると、キー1の値に対して、ラムダ式で指定された処理が実行されます。
		 * その際、aにはキー1に格納されている値である10、bにはmerge（）メソッドの第2引数で指定された3が渡されます。
		 * merge２行目も同様に処理が行われるため、キー1の値は13、2はnull、3は33が格納される
		 */
		Predicate<? super String> predicate  = s -> s.length() < 5;
		Map<Integer, Integer> map3 = new HashMap<>();
		map3.put(1, 10);
		map3.put(2, 10);
		map3.put(3, 10);
		map3.merge(1, 3, (a, b) -> a + b);
		map3.merge(3, 3, (a, b) -> a + b);
		System.out.println(map3);//{1=13, 2=10, 3=13}
		
		/*
		 * No.4
		 * 中間操作は終端操作の処理後の呼び出しことができない！！！！！
		 * 一つのストリームは一つのみの終端操作がある
		 * 終端操作はパイプラインの結果を取得ために必要な処理である
		 */
		
		/*
		 * No.5
		 * 実装が問題ないですが、generateが無限に生成するため、anyMatchも無限に判断になっちゃう。実行するとハングする
		 */
		Stream<String> stream5 = Stream.generate(() -> "123456");
//		System.out.println(stream5.anyMatch(s -> s.length() < 5));
		
		/*
		 * No.10
		 */
		double result10 = DoubleStream.of(10.0, 20.0, 30.0)
//				.mapToInt(x -> x)  コンパイルエラー　　mapToInt　→　IntStream　　xの戻り値が10.0のdoubleのままだ
				.mapToInt(x -> (int)x)
				.boxed()
				.collect(Collectors.averagingInt(x -> x));
		System.out.println(result10);
		

		/*
		 * No.14
		 */
		String s14 = Stream.iterate(1, i -> i++)
				.limit(5)
				.map(i -> "" + i)
				.collect(Collectors.joining());
		System.out.println(s14);//11111
		// joiningは文字列項目を結合するため、mapによるStream<Integer>→Stream<String>が必要になる
//		String s142 = Stream.iterate(1, i -> ++i)
//				.limit(5)
//				.map(i -> i)
//				.collect(Collectors.joining());//型 Stream<Integer> のメソッド collect(Collector<? super Integer,A,R>) は引数 (Collector<CharSequence,capture#13-of ?,String>) に適用できません
//		System.out.println(s142);
		String s143 = Stream.iterate(1, i -> ++i)
				.limit(5)
				.map(i -> "" + i)
				.collect(Collectors.joining());
		System.out.println(s143);//12345
		
		/*
		 * No.16
		 */
		Stream<Double> stream16 = Stream.empty();
		//型 Optional<Double> のメソッド orElseGet(Supplier<? extends Double>) は引数 (() -> {}) に適用できません
		//型の不一致: String から Double には変換できません
		//Stream<Double>のfindFirst()　→　Optional<Double>　　
		// Optional<Double>.orElseGet　→　必ずdouble型を返す
//		System.out.println(stream16.findFirst().orElseGet(() -> "empty"));
		System.out.println(stream16.findFirst().orElseGet(() -> 1.0));
		
		
		/*
		 * No.19
		 * Optional<>にget()のみ
		 * OptionalIntにgetAsInt()のみ
		 * OptionalDoubleにgetAsDouble()のみ
		 * OptionalLongにgetAsLong()のみ
		 */
		double dou191 = Stream.of(10.0, 20.0, 30.0)
		.map(x -> x*2)
		.reduce((a, b) -> a + b).get();
		DoubleBinaryOperator op = (a,b) -> a + b;
		BinaryOperator<Double> op1 = (a,b) -> a + b;
		double dou192 = Stream.of(10.0, 20.0, 30.0)
		.mapToDouble(x -> x*2)
//		.reduce(0.0, (a, b) -> a+b);  reduce(double, DoubleBinaryOperator)    型 DoubleStream からのメソッド reduce(double, DoubleBinaryOperator) は欠落している型 DoubleBinaryOperator を参照しています
//		.reduce(0.0, op1);//型 DoubleStream のメソッド reduce(double, DoubleBinaryOperator) は引数 (double, BinaryOperator<Double>) に適用できません
		.reduce(0.0, op);
//		.reduce(0.0, (a, b) -> a + b);
		System.out.println(dou192);//120.0
	}

}























