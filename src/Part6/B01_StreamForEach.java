package Part6;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * https://java-code.jp/1514
 * 終端処理では、Stream内で加工／フィルターされた値を最終的に出力／集計します。
 * Streamでは、中間処理の時点では処理をストックします。
 * そして、終端処理が呼び出されたところでまとめて加工／フィルターなどを実施します（遅延処理）。
 * その性質上、中間処理は省略可能ですが、終端処理は末尾で必ず呼び出さなければなりません。
 * 
 * 主な終端操作を行うメソッド：
 * 	boolean allMatch(Predicate< ? super T> predicate)　　　　　　　　	//リダクションなし
 * 		すべての要素が指定された条件に一致するかどうかを返す。一致しているか、ストリームが空の場合はtrue、それ以外の場合はfalse
 * 	boolean anyMatch(Predicate< ? super T> predicate)　　　　　　　　	//リダクションなし
 * 		いずれかの要素が指定された条件に一致するかどうかを返す。存在する場合はtrue、そうでない場合はfalse
 * 	boolean noneMatch(Predicate< ? super T> predicate)　　　　　　　　	//リダクションなし
 * 		どの要素も指定された条件に一致しないか、ストリームが空の場合はtrue、それ以外の場合はfalseを返す
 * 	<R,A> R collect(Collector< ? super T,A,R> collector)　　　　　　　　	//リダクションあり
 * 		要素に対する可変リダクション操作を実行する
 * 	<R> R collect(Supplier<R> supplier,Biconsumer<R, ? super T> accumulator, Biconsumer<R,R> combiner)　　　　　　　　	//リダクションあり
 * 		要素に対する可変リダクション操作を実行する
 * 	long count()　　　　　　　　	//リダクションあり
 * 		要素の個数を返す
 * 	Optional<T> findAny()　　　　　　　　	//リダクションなし
 * 		いずれかの要素を返す。ストリームが空の場合は空のOptionalを返す
 * 	Option<T> findFirst()　　　　　　　　	//リダクションなし
 * 		最初の要素を返す。ストリームが空の場合は空のOptionalを返す
 * 	void forEach(Consumer< ? super T> action)　　　　　　　　	//リダクションなし
 * 		各要素に対して指定されたアクションを実行する
 * 	Optional<T> min(Comparator< ? super T> comparator)　　　　　　　　	//リダクションあり
 * 		指定された Comparator に従って最小要素を返す。ストリームが空の場合は空のOptionalを返す
 * 	Optional<T> max(Comparator< ? super T> comparator)　　　　　　　　	//リダクションあり
 * 		指定された Comparator に従って最大要素を返す。ストリームが空の場合は空のOptionalを返す
 * 	T reduce(T identity, BinaryOperator<T> accumulator)　　　　　　　　	//リダクションあり
 * 		元の値と結合的な累積関数を使ってこのストリームの要素に対してリダクションを実行し、リデュースされた値を返す
 * 	Object[] toArray()　　　　　　　　	//リダクションなし
 * 		要素を含む配列を返す
 * 	<A> A[] toArray(IntFunction<A[]> generator)　　　　　　　　	//リダクションなし
 * 		指定された型と長さをもつ新しい配列を生成する関数を引数に指定し、配列を返す
 * 
 * リダクション操作とは、SQLのmax（）やcount（）関数のように、人力要素をもとに結合操作を繰り返し実行して、単一の結果を得る操作です。
 */
public class B01_StreamForEach {
	/*
	 * allMatch():指定された条件に全ての要素が一致しているかをboolean値で返す
	 * anyMatch():指定された条件にいずれかの要素が一致しているかをboolean値で返す
	 * noneMatch():指定された条件に全ての要素が一致していないかをboolean値で返す
	 */
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		List<String> list1 = Arrays.asList("mana", "naoki", "ryo");
		boolean result1 = list1.stream().allMatch(str -> str.length() >= 3);
		boolean result2 = list1.stream().anyMatch(str -> str.length() == 4);
		boolean result3 = list1.stream().noneMatch(str -> str.length() == 4);
		System.out.println(result1);//true
		System.out.println(result2);//true
		System.out.println(result3);//false
		
		Stream<String> stream = list1.stream();//①
		boolean result4 = stream.allMatch(str -> str.length() >= 3);// ②
//		boolean result5 = stream.anyMatch(str -> str.length() == 4);// ③　Exception in thread "main" java.lang.IllegalStateException: stream has already been operated upon or closed
		/*
		 * このコメントコードは実行時エラー（正確には③で実行時エラーであるILlegalStateExceptionがスローされる）となります。
		 * 1つのストリームオブジェクトに対して、終端操作は1度しか呼び出せないので注意してください。
		 */
	}

}
