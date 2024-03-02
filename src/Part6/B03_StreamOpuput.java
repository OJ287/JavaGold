package Part6;

import java.lang.StackWalker.Option;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Optionalクラス
 * 前述したreduce（）メソッドでjava.uti.Optional クラスを使用しました。
 * Optional クラスはSE 8から追加されたクラスで、実体は1つの値を保存しているクラスです。
 * 特徴として、Optionalクラスの各メソッドは、保持している値が存在するか否かによって処理が異なります。
 * Optional オブジェクトは、保持している値が存在しない場合はemptyというオブジェクトになりますが、
 * Optional オブジェクト自体は、nullや空ではなく、メソッド呼び出しが可能なオブジェクトである
 */
/**
 * メソッド名								説明
 * static<T> Optional<T> empty() 		空のOptionalインスタンスを返す。このOptionalには値は存在しない
 * static<T> Optional<T> of(T value)	引数で指定された非null値を含むOptionalを返す.null指定不可NullPointerException
 * T get()								値が存在する場合は値を返し、それ以外の場合はNoSuchElementExceptionをスローする
 * boolean isPresent()					値が存在する場合はtrue を返し、それ以外の場合はfalse を返す
 * boolean isEmpty()					値が存在しない場合はtrue を返し、それ以外の場合はfalse を返す
 * void ifPresent(Consumer<? super T> consumer)値が存在する場合は指定されたコンシューマをその値で呼び出し、それ以外の場合は何も行わない
 * T orElse(T other)					値が存在する場合は値を返し、それ以外の場合はother を返す
 * T orElseGet(Supplier<? super T> other)値が存在する場合は値を返し、それ以外の場合はサプライヤを呼び出し、その呼び出しの結果を返す
 * <X extends Throwable> T orElseThrow(Supplier<? super X> exceptionSupplier) throws X extends Throwable
 * 			値が存在する場合は値を返し、それ以外の場合は指定されたサプライヤによって作成された例外をスローする
 * 
 */
public class B03_StreamOpuput {

	public static void main(String[] args) {
		/*
		 *  get()
		 *  isPresent()
		 *  isEmpty()
		 */
//		Optional<Integer> op1 = Optional.of(null);//Exception in thread "main" java.lang.NullPointerException
		Optional<Integer> op2 = Optional.of(10);
		Optional<Integer> op3 = Optional.empty();
//		System.out.println("op1:" + op1.get());
		System.out.println("op2:" + op2.get());//10
//		System.out.println("op2:" + op3.get());//NoSuchElementException

//		System.out.println("op1:" + op1.isPresent());
		System.out.println("op2:" + op2.isPresent());//true
		System.out.println("op3:" + op3.isPresent());//false

//		System.out.println("op1:" + op1.isEmpty());
		System.out.println("op2:" + op2.isEmpty());//false
		System.out.println("op3:" + op3.isEmpty());//true
		
		/*
		 * B01_StreamForEachで紹介した主な終端操作のメソッドのうち、戻り値がOptional型を持つ
		 * max()メソッド
		 */
		List<String> list1 = Arrays.asList("aaa", "bb", "c");
		Optional<String> result1 = list1.stream().max(Comparator.naturalOrder());
		Optional<String> result2 = list1.stream().max((s1, s2) -> s1.length() -s2.length());
		result1.ifPresent(System.out::println);//c
		result2.ifPresent(System.out::println);//aaa
		System.out.println(result1.get());
		System.out.println(result2.get());
		System.out.println(list1.stream().sorted((s1, s2) -> s1.length() -s2.length()).findFirst().get() + "-----");//c
		
		/*
		 * B01_StreamForEachで紹介した主な終端操作のメソッドのうち、戻り値がOptional型を持つ
		 * findFirst()
		 * findAny()
		 */
		/*
		 * また、findAay()、findFist（）、max(），min()の各メソッドはStreamインタフェースの他、
		 * 基本データ型に対応したストリームでも提供されています。
		 * ただし、戻り値の型が異なります
		 * 
		 * インスタンス		戻り値			メソッド
		 * Stream			Optional<T>		findFirst,findAny,max,min
		 * IntStream		OptionalDouble	average
		 * IntStream		optionalInt		findFirst,findAny,max,min
		 * IntStream		int				sum
		 * 
		 */
		List<String> list2 = Arrays.asList("c", "a");
		Optional<String> result21 = list2.stream().findFirst();
		Optional<String> result22 = list2.stream().findAny();
		System.out.println("result21:" + result21.get());//result21:c
		System.out.println("result22:" + result22.get());//result22:c
		
		Stream<String> stream = Stream.empty();
		Optional<String> result23 = stream.findFirst();
//		System.out.println("result23:" + result23.get());
		result23.ifPresent(r -> System.out.println("result23:" + result23));
		
		IntStream intStream = IntStream.of(10, 20, 30);
		OptionalInt result24 = intStream.findFirst();
//		System.out.println(result24.get());//メソッド get() は型 OptionalInt で未定義です
		System.out.println(result24.getAsInt());//10
		
		
		/*
		 * Optionalクラスの
		 * orElse()
		 * orElseGet()
		 * orElseThrow()
		 * get()メソッドについては、Optionalオブジェクトがemptyの場合、異常をスロー
		 * 上記のメソッドを使用すれば、返す値を明示的に変更できる
		 */
		Stream<Double> stream2 = Stream.empty();
		Optional<Double> result31 = stream2.findFirst();
//		System.out.println("result31:" + result31.get());
		System.out.println("result31:" + result31.orElse(1.0));
		System.out.println("result31:" + result31.orElseGet(Math::random));
		System.out.println("result31:" + result31.orElseGet(() -> Math.random()));
		System.out.println("result31:" + result31.orElseThrow(IllegalArgumentException::new));
		System.out.println("result31:" + result31.orElseThrow(() -> new IllegalArgumentException()));
		
		//以下はコンパイルエラー
//		result31.orElseGet(IllegalArgumentException::new);//The constructed object of type IllegalArgumentException is incompatible with the descriptor's return type: Double
		/*
		 * result31の型はOptional<Double>
		 * <Double>の指定がありますので、<Double>型を返す必要がある
		 */
		Stream<IllegalArgumentException> stream3 = Stream.empty();
		//stream3.findFirst();の返す型が必ずstream3の定義型と同じ
//		Optional<Exception> result32 = stream3.findFirst();//型の不一致: Optional<IllegalArgumentException> から Optional<Exception> には変換できません
		
		

		Stream<Exception> stream4 = Stream.empty();
		Optional<Exception> result33 = stream4.findFirst();
		result33.orElseGet(IllegalArgumentException::new);//OK
		
		
		
		
	}

}
