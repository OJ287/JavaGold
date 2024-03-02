package Part6;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 終端操作の項目ではまだ扱っていないStreamインタフェースのcollectメソッドについて紹介
 * collect()：ストリームから要素をまとめて一つのオブジェクトを取得
 * 構文１：
 * 		<R,A> R collect(Collector<? super T,A,R> collector)
 * 構文２：第８章で紹介
 * 		<R> R collect(Supplier<R> supplier, 
 * 						BiConsumer<R,? super T> accumulator,
 * 						BiConsumer<R,R> combiner)
 *Collector<? super T,A,R>:出題率が高い
 *　T：入力要素となるストリームの型
 *　A：結果を生成する際に使用される型（通常は実装詳細として隠避される）
 *　R：結果の型
 *　例：
 *		　<String,?,List<String>>はStream<String>からList<String>を生成する
 *		　<String,?,Double>はStream<String>からDoubleを生成する
 *
 */
/**
 * Collectorsクラス
 * Collectorオブジェクトを提供するstaticメソッドが多数用意される
 *　メソッド名　　　　　　　　　　　　　　　　           説明
 *　static <T> Collector <T,?,List<T>> toList()　　入力要素をListに蓄積するCollectorを返す
 *　static Collector <CharSequence,?,String> joining()　　入力要素を検出順に連結して一つのStringにするCollectorを返す
 *　static Collector <CharSequence,?,String> joining(CharSequence delimiter)　　入力要素を検出順に指定された区切り文字で区切りながら連結するCollectorを返す
 *　static <T> Collector <T,?,Integer> summingInt(ToIntFunction<? super T> mapper)　　int値関数を適用した結果の合計を生成するCollectorを返す。要素が存在しない場合、結果は０になる
 *　static <T> Collector <T,?,Double> averagingInt(ToIntFunction<? super T> mapper)　　int値関数を適用した結果の算術平均を生成するCollectorを返す。要素が存在しない場合、結果は０になる
 *　static <T> Collector<T,?,Set<T>> toSet() 　　入力要素をSetに蓄積するCollectorを返す
 *　static <T,K,U> Collector<T,?,Map<K,U>> toMap(Function<? super T,? extends K> key,Function<? super T,? extends U> value) 　　入力要素をMapに蓄積するCollectorを返す
 *　static <T,K> Collector<T,?,Map<K,list<T>>> groupingBy(Function<? super T,? extends K> classifier) 　　指定した関数に従って要素をグループ化し、結果をMapに格納してCollectorを返す
 *　static <T> Collector<T,?,Map<Boolean,List<T>>> parttioningBy(Predicate<? super T> predicate)　　指定した関数にしたがって要素をtrueもしくはfalseでグループ化し、結果をMapに格納してCollectorを返す
 *　static <T,U,A,R> Collector<T,?,R> mapping(Function<? super T,? extends U> mapper,Collector<? super U,A,R> downnstream)　　マッピングを行、マップ後に指定された他のCollectorを適用し、結果をMapに格納してCollectorを返す
 *　static<T> Collector<T,?,Optional<T>> maxBy(Comparator<? super T> comparator)　　指定されたComparatorに従ってOptional<T>として記述された最大要素を生成するCollectorを返す
 *　static<T> Collector<T,?,Optional<T>> minBy(Comparator<? super T> comparator)　　指定されたComparatorに従ってOptional<T>として記述された最小要素を生成するCollectorを返す
 *　
 */
public class E01_Collect {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		// toList()
		Stream<String> stream1 = Stream.of("naoki", "akko", "ami");
		List<String> result1 = stream1.collect(Collectors.toList());
		System.out.println(result1);//[naoki, akko, ami]
		
		// joining()
		Stream<String> stream2 = Stream.of("naoki", "akko", "ami");
		String result2 = stream2.collect(Collectors.joining());
		System.out.println(result2);//naokiakkoami
		
		// summing()
		Stream<String> stream3 = Stream.of("naoki", "akko", "ami");
		int result30 = stream3.collect(Collectors.summingInt(t -> t.length()));
		Integer result3 = stream3.collect(Collectors.summingInt(t -> t.length()));
		Double result33 = stream3.collect(Collectors.summingDouble(t -> t.length()));
		System.out.println(result3);//12
		
		// averaging()  averagingInt  averagingDouble   averagingLong戻り値の型は全てDouble型となる
		Stream<String> stream4 = Stream.of("naoki", "akko", "ami");
		Double result4 = stream4.collect(Collectors.averagingDouble(t -> t.length()));
//		Integer result41 = stream4.collect(Collectors.averagingInt(t -> t.length()));//型の不一致: Double から Integer には変換できません
		Double result42 = stream4.collect(Collectors.averagingLong(t -> t.length()));
		System.out.println(result4);//4.0
		
		
		

	}

}


























