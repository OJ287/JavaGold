package Part5;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

/**Java8で導入された新しい文法。
 * インタフェースの実装クラスで、再利用することはなく、その場だけで使用したいクラスを定義する場合、
 * 匿名クラスを利用することができますが、可読性があまり良くなくコードも冗長になりがちです。
 * ラムダ式を使用することで、同じことをシンプルに実装できます。
 * 関数型インタフェースは、ラムダ式で実装することを前提として提供されています。
 * ローカルクラスと無名クラスを利用して記述する内容を省略して処理記述を簡潔にする。
 * 代表的な例としてCollections.sortやStreamAPIのメソッドが恩恵を受けた。
 * ラムダ式の構文：
 * 		（実装するメソッドの引数）　-> {処理}；
 * ->	:
 * 	左辺には実装するメソッドの引数を、右辺には実装する処理を記述
 * 
 * 
 * 
 * 
 */

public class C01_LambdaExpression {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		//	例：
		List<String> list1 = Arrays.asList("Tanaka", "Sato");
		
		//匿名クラスで実装した場合
		list1.replaceAll(new UnaryOperator<String>() {
			
			@Override
			public String apply(String t) {
				// TODO 自動生成されたメソッド・スタブ
				return t.toUpperCase();
			}
		});
		/*
		 * 関数型インタフェース：replaceAll(UnaryOperator<E> operator)  のUnaryOperator
		 * ->左辺を見ると、applyメソッドの引数であるString型の引数は記述していますが、メソッド名applyが記述されていない
		 * これは関数型インタフェースは抽象メソッドが一つしかなく、ラムダ式がどのメソッドを実装するかを判断できる
		 */
		list1.replaceAll((String str) -> {return str.toUpperCase();});
		list1.replaceAll((var str) -> {return str.toUpperCase();});//SE11以降ではラムダ式の引数の型varの指定が可能
		System.out.println(list1);
		/*
		 * 主な関数型インタフェース
		 * インタフェース名：				抽象メソッド			概要
		 * ・Function<T, R> 				R apply(T t)		実装するメソッドは引数としてTを受け取り、結果としてRを返す
		 * ・BiFunction<T, U, R>			R apply(T t, U u)	実装するメソッドは引数としてTとUを受け取り、結果としてRを返す
		 * ・Consumer<T>					void accept(T t)	実装するメソッドは引数としてTを受け取り、結果を返さない
		 * ・BiConsumer<T, U>			void accept(T t, U u)	実装するメソッドは引数としてTとUを受け取り、結果を返さない
		 * ・Predicate<T>				boolean test(T t)	実装するメソッドは引数としてTを受け取り、結果としてbooleanを返す
		 * ・BiPredicate<T,U>			boolean test(T t, U u)	実装するメソッドは引数としてTとUを受け取り、結果としてbooleanを返す
		 * ・Supplier<T>					T get()				実装するメソッドは何も引数としてを受け取ラズ、結果としてTを返す
		 * ・UnaryOperator<T>			T apply(T t)		実装するメソッドは引数としてTを受け取り、結果としてTを返す。Functionを拡張したもの
		 * ・BinaryOperator<T>			T apply(T t1, T t2)	実装するメソッドは引数としてTを二つ受け取り、結果としてTを返す。BiFunctionを拡張したもの
		 * 目的ごとに、FunctionやConsumer といった名前でインタフェースが用意されています。
		 * また、Supplier 以外は引数を1つとりますが、
		 * 引数が2つ必要な場合は、インタフェース名の前に「Bi」が付与されています。
		 * また、各メソッドの引数や戻り値はジェネリックスであって、
		 * 基本データ型は使用できないため、int値やdouble値を使用する場合はラッパークラスを使用します。
		 */
		
		
		/*
		 * ラムダ式の省略記法
		 * さへん：
		 * SE11から抽象メソッドの引数の型にvarの使用は可能、
		 * という意味は推論できる、だから型の省略はできる
		 * また左辺の（）については、引数が一つしかない場合は省略可能
		 * 引数がない場合や複数のh引数がある場合は省略できない、そして引数型の省略していない場合は（）も省略できない
		 * 引数が一つしかない、かつ引数型は省略された場合のみ、（）の省略は可能
		 * 
		 * 右辺：
		 * returnを記述した場合は、最後が文で終わるようにセミコロンが必須
		 * return を省略した場合、{}も省略する必要がある。セミコロンの省略は可能になる
		 */
		list1.replaceAll( str -> str.toUpperCase());
		System.out.println(list1);
		Function<String, String> function1 = (String str) -> {
			return "省略なし" + str;
		};
		System.out.println(function1.apply("function1"));
		Function<String, String> function2 = str -> "省略あり" + str;
		System.out.println(function2.apply("function2"));
		
		
		/*
		 * 実質的final
		 * 1章の匿名クラスで紹介しましたが、匿名クラスでは外側のクラスのメンバにアクセス可能ですが、
		 * ローカル変数の場合、アクセス可能なものはfinal（定数）のみとなります。
		 * ラムダ式からメンバ変数、ローカル変数のアクセス有無を確認しています。
		 * ラムダ式から外側の変数を使う場合は、その変数は final修飾子が付与されているか、
		 * もしくは実質的finalでなければいけません。
		 * 実質的finalとはfinal修飾子が付与されていなくても、宣言時から変更されていない場合を意味します。
		 */
		new C01_LambdaExpression().method();
		
		
	}
	//実質的final
	int a = 10;
	public void method() {
		final int b = 20;
		int c = 30;//実質的final　　メソッドで宣言時から、method()で値の再代入をしていないので、実質的finalである
		int d = 40;
		d = 50;
		int e = 60;  //実質的final　　　メソッドで宣言時から、method()で値の再代入をしていないので、実質的finalである
		int i = 25;
		Function<String, String> function1 = str -> {
			System.out.println("a:" + a);
			System.out.println("b:" + b);
			System.out.println("c:" + c);
//			System.out.println("d:" + d);//コンパイルエラー
//			e = 100;//コンパイルエラー   実質的finalとして扱い、だからfinalへ値の代入処理はコンパイルエラーになる
			return "Hello " + str;
		};
		System.out.println(function1.apply("method"));
		
		/*
		 * iは初期化してから、ラムダ式まで再代入していないので、実質的finalとして() -> i　ラムダ式では扱っている。
		 * ラムダ式後続にもiに再代入しても、実質的finalではなくなるので、ラムダ式にはコンパイルエラーになる
		 */
		Supplier<Integer> supplier = () -> i;//Local variable i defined in an enclosing scope must be final or effectively final
//		i++;
		System.out.println(supplier.get());
	}
}


































