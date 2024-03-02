package Part6;

import java.lang.StackWalker.Option;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class B02_StreamOpuput {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		/**
		 * count()：要素の個数を返し
		 * forEach()：引数で指定されたアクションを各要素に対して実行する
		 * 			引数はConsumerインタフェース（メソッドは「void accept(T t)」）
		 */
		long result1 = Stream.of("a","b", "c").count();
		System.out.println(result1);
		Stream<String> stream1 = Stream.of("a","b", "c");
		stream1.forEach(System.out::print);
//		for (String string : stream1) {}//StreamインタフェースはIterableを継承していないため、コンパイルエラー
		System.out.println();
		
		/**
		 * reduce()：
		 *			第二引数はBinaryOperatorインタフェース、メソッドはT apply(T t1, T t2)
		 *構文：
		 *	1,T reduce(T identity, BinaryOperator<T> accumulator)
		 *	2,Optional<T> reduce(BinaryOperator<T> accumulator)
		 *	3,<U> U reduce(U identity, BiFunction<U, ? super T,U> accumulator, BinaryOperator<U> combiner)
		 *IntStreamで提供：
		 *	int reduce(int identity, IntBinaryOperator op)
		 *DoubleStreamで提供：
		 *	double reduce(double identity, DoubleBinaryOperator op)
		 *LongStreamで提供：
		 *	long reduce(long identity, LongBinaryOperator op)
		 *各メソッドの戻り値は、対応した基本データです
		 *そして、第二引数はBinaryOperatorではんく、対応したデータ型BinaryOperatorとなる
		 *
		 */
		Integer[] date1 = {10,20,30};
		int result2 = 0;
		for (Integer integer : date1) {
			result2 += integer;
		}
		System.out.println(result2);// 60
		result2 = 0;

		Stream<Integer> stream2 = Stream.of(date1);
//		result2 = stream2.reduce(0, (a,b) -> a + b);
		result2 = stream2.reduce(0, Integer::sum);
		System.out.println(result2);// 60
		
		result2 = 0;
		BinaryOperator<Integer> operator1 = (a,b) -> a+b;
		Stream<Integer> stream3 = Stream.of(date1);
//		result2 = stream3.reduce(operator1);
		Optional<Integer> result3 = stream3.reduce(operator1);
		result3.ifPresent(System.out::println);// 60
		
		Stream<Integer> stream4 = Stream.empty();
		Optional<Integer> result4 = stream4.reduce(operator1);
		System.out.println(result4);//Optional.empty
		result4.ifPresent(System.out::println);// 出力データなし
		/*
		 * operator1 事前にラムダ式の準備をして、stream3 Stream インタフェーズのofメソッドを使用してストリームオブジェクトを生成します。
		 * result3 reduceの引数に初期値の指定はなく、ラムダ式のみ指定しています。
		 * また。長り値はjava.util.Optional型となる点に注意してください。
		 * result2はコンバイルエラーです。
		 * Optional クラスの詳細は後述しますが、実体はひとつの値を保持しているクラスです。
		 * result3はOptionalクラスのifPresentメソッドにより、値がある場合は引数の処理（Systen.outepinth）が実行されます。
		 * stream4ではemptyメンッドを使用して、要茶が空のストリームオブジェクトを生成
		 * 
		 * result4により空のOptional オブジェクトが返ります。
		 * result4　の実行結果でOptional.emptyとあるとおり、Optionalオブジェクト自体はあるものの、
		 * Optionalが保持する値はありません。
		 * したがって、ifPresentでifPresent（）メソッドの呼び出しを行っていますが、出力処理は行われないため何も表示しません。
		 */
		
		/**
		 * toArray()：ストリームから配列に変換する
		 * toArrayも複数あり
		 * Streamインタフェースで提供：
		 * 構文１：Object[] toArray()
		 * 構文２：<A> A[] toArray(IntFunction<A[]> generator)	
		 * 
		 * IntStreamインタフェースで提供：
		 * 	構文３：int[] toArray()
		 * 
		 * 構文1ではtoArray（）メソッドの引数はなく、戻り値はObject型の配列です。
		 * また、構文2を使用することで独自の配列型を指定することも可能です。
		 * 構文2の引数はIntFunction インタフェース（メソッドは「R apply（int value）」）であるため、
		 * ラムダ式やメソッド参照で以下のコード例のように配列の生成処理を指定する
		 * また、構文３のとおり、IntStream インタフェースではint型の配列を取得するtoArrayO）メソッドが提供されています。
		 * DoubleStream や LongStreamでも同様な記述が可能です。
		 * 
		 */
		String[] ary01 = Stream.of("a","b").toArray(String[]::new);
		String[] ary02 = Stream.of("a","b").toArray(i -> new String[i]);
		System.out.println("ary01:" + Arrays.toString(ary01));//ary01:[a, b]
		System.out.println("ary01:" + ary01.getClass());//ary01:class [Ljava.lang.String;
		System.out.println("ary02:" + Arrays.toString(ary01));//ary01:[a, b]
		System.out.println("ary02:" + ary02.getClass());//ary02:class [Ljava.lang.String;
				
		int[] ary1 = IntStream.range(1, 10).toArray();
		int[] ary2 = IntStream.rangeClosed(1, 10).toArray();
		Object[] ary3 = Stream.of("a","b").toArray();
		String[] ary4 = Stream.of("a","b").toArray(String[]::new);//ストリームからString型の配列を取得できる
		System.out.println("ary1:" + Arrays.toString(ary1));//ary1:[1, 2, 3, 4, 5, 6, 7, 8, 9]
		System.out.println("ary2:" + Arrays.toString(ary2));//ary2:[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
		System.out.println("ary3:" + Arrays.toString(ary3));//ary3:[a, b]
		System.out.println("ary4:" + Arrays.toString(ary4));//ary4:[a, b]
		System.out.println("ary3:" + ary3.getClass());//ary3:class [Ljava.lang.Object;
		System.out.println("ary4:" + ary4.getClass());//ary4:class [Ljava.lang.String;
		
		
		
		
	}

}



















