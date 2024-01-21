package Part6;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 
 * 本章では、Java ストリーム APIについて説明します。
 * コレクションや配列に対するマップや集計処理など、ストリームを通じてパイプライン処理を行う APIです。
 * ストリームに関連するインタフェースやメソッドは多数提供されているため、出題範囲を中心に説明します。
 * 
 * ストリームAPIは、コレクション、配列、I/Oリソースなどのデータ提供示となるデータソースをもとに、集計操作を行うAPIです。
 * ストリームは、ある処理結果を次の処理のデータソース（入力）として渡すことができます。
 * そのため、データソースをもとに様々な処理を通じてデータを加工することができます。
 * たとえば、データソースとして次のようなリストがあったとしま。
 * 処理①ではリスト内の各要素を大文字にし、処理②では要素を昇順にソートし、
 * 結果として要素を出力するという処理の流れを実装したいと考えます
 *
 */

public class A01_StreamAPI {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		/*
		 * ストリームAPIは複数の処理の入出力をつなぐための仕組みを提供しており、
		 * これをストリームのパイプライン処理と呼びます。
		 * まとめると、パイプライン処理には、処理のもととなるデータソースが必要です。
		 * データソースをもとにストリームオブジェクトを生成し、後続する処理を行います。
		 * なお、処理①や処理②のように、パイプラインの途中で行う処理を中間操作と呼び、
		 * System.out.結果出力のように、パイプラインの最後に行う処理を終端操作と呼びます。
		 * 
		 * 問い合わせの対象となるデータソース：コレクション、配列、I/Oリソースなど
		 * 中間操作：ストリームに対して処理を行った結果をストリームで返す
		 * 終端操作：処理結果をデータとして返す
		 * 
		 */
		// ソース：問い合わせの対象となるデータソース
		List<String> list1 = Arrays.asList("bb", "aa", "cc");
		// 処理１：中間操作
		for (int i = 0; i < list1.size(); i++) {
			list1.set(i, list1.get(i).toUpperCase());
		}
		// 処理２：中間操作
		Collections.sort(list1);
		System.out.println(list1);//[AA, BB, CC]  要素を出力：終端操作
		
		List<String> list2 = Arrays.asList("bb", "aa", "cc");
		list2.stream().sorted().map(String::toUpperCase).forEach(System.out::print);
		System.out.println(list2);
		
		/*
		 * 中間処理は複数があっても、
		 * 各中間操作のメソッドは、その中間操作結果をソースとする新しいストリームに返す
		 * 
		 * 
		 * 中間操作は遅延実行です。
		 * 中間操作としてfilter()を指定しても、実際には中間操作のフェーズには実行されない、
		 * ただ、filter()を保持っしていたストリームを返されるということになる
		 * つまり、中間操作では「何を行うか」のみをパイプラインで繋げていく
		 * そして、終端操作のメソッドが実装された際に初めて、全ての処理を行われる
		 */
		
		
		/*
		 * ストリームの種類と生成
		 * Collectionインタフェース
		 * 		default Stream<E> stream()
		 * 			List<String> list = Arrays.asList("a","b","c");
		 * 			Stream<String> stream = list.stream();
		 * Arraysクラス
		 * 		static <T> Stream<T> stream(T[] array)
		 * 			String[] ary = {"a","b","c"};
		 * 			Stream<String> stream = Arrays.stream(ary);
		 * 		static IntStream stream(int[] array)
		 * 			int[] ary = {1,2,3};
		 * 			IntStream<String> stream = Arrays.stream(ary);
		 * Streamインタフェース
		 * 		static <T> Stream<T> of(T t)
		 * 			Stream<String> stream = Stream.of("abc");
		 * 		static <T> Stream<T> of(T... values)
		 * 			Stream<Long> stream = Stream.of(1L,2L,3L);
		 * 		static <T> Stream<T> empty()    //空のストリームを作成
		 * 			Stream<String> stream = Stream.empty();
		 * 		static <T> Stream<T> generate(Supplier<T> s)   //指定したラムダ式から構成された要素をソースとするストリームを作成
		 * 			Stream<String> stream = Stream.generate(() -> "JAVA");
		 * IntStreamインタフェース
		 * 		static IntStream of(int... values)
		 * 			IntStream stream = IntStream.of(1,2,3);
		 * 		static IntStream iterate(int seed, IntUnaryOperator f)
		 * 			//指定した初期値とラムダ式から構成された要素をソースとするストリームを生成
		 * 			IntStream stream = IntStream.iterate(1, i -> i + 1);
		 * 		static IntStream iterate(int seed, IntPredicate hasNext, IntUnaryOperator next)
		 * 			//指定した初期値で、hasNext条件を満たす、ラムダ式から構成された要素をソースとするストリームを生成
		 * 			IntStream stream = IntStream.iterate(1, i -> i < 10 , i -> i + 1);
		 * 		static IntStream range(int startInclusive, int endExclusive)
		 * 			//引数で指定した範囲の要素をソースとするストリームを生成。終了値を含まない
		 * 			IntStream stream = IntStream.range(1,10);
		 * 		static IntStream rangeClosed(int startInclusive, int endExclusive)
		 * 			//引数で指定した範囲の要素をソースとするストリームを生成。終了値を含む
		 * 			IntStream stream = IntStream.rangeClosed(1,10);
		 * 		
		 * 
		 * Collectionインタフェースにデフォルトメソッドとしてstream（）が提供されているため、
		 * ArrayListなどのコレクションオブジェクトをデータソースとして、
		 * ストリームオブジェクトを取得しパイプライン処理を行うことができます。
		 * また、Arraysクラスのstream（）メソッドでは、配列からストリームを取得することができます。
		 * また、各メソッドの戻り値は、java.util.stream.Streamの他、IntStream などです。
		 * Streamは型パラメータで扱う型を指定しますが、基本データ型に対応したストリームオプシェクトは、
		 * IntStream、LongStream、DoubleStreamの3種類です
		 * 
		 * Stream<T>:順次及び並列の集約操作をサポートする汎用的なストリーム
		 * IntStream:順次及び並列の集約操作をサポートするint値のストリーム。正し、intの他short,byte,char型に使用可能
		 * LongStream:順次及び並列の集約操作をサポートするlong値のストリーム。
		 * DoubleStream:順次及び並列の集約操作をサポートするDouble値のストリーム。正し、doubleの他float型に使用可能
		 * 
		 */
		
		
		
	}

}





















