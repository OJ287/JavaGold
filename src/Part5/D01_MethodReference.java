package Part5;

import java.util.*;
import java.util.function.*;

/**
 * Function<String, Integer> f = str -> Integer.parseInt(str);
 * このように、抽象メソッドの引数の個数および型と、apply的引数和返回值是定义的Function<String, Integer>
 * 処理内で使用しているメソッド（Integer.parseInt）の引数の個数および型が一致している場合、parseInt的引数是String返回值是int
 * メソッド参照の記述が可能です。
 * apply的参数给parseInt，parseInt的返回值作为apply的返回值
 * apply的第一个参数给処理内で使用しているメソッド的実行する対象のオブジェクト，不需要実行する対象のオブジェクト的话再给
 * 処理内で使用しているメソッド的参数，参数也没有的话给処理内で使用しているメソッド的返回值
 * 所以顺序  実行する対象のオブジェクト →  処理内で使用しているメソッド的参数   →  処理内で使用しているメソッド的返回值
 *
 * 構文：
 * 	クラス名／インスタンス変数名：：メソッド名
 * 　クラス名／インスタンス変数名の後に「：」を指定し、呼び出すメソッド名をを指定します。
 * 　メソッド参照では引数が省略可能で、（）記述しません。では入っド参照を使用したサンプルを見てみましょう。
 */

public class D01_MethodReference {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
//		Function<String, Integer> f1 = str -> Integer.parseInt(str);
		/*
		 * メソッド参照は、呼び出すメソッドの種類によって次の三種類がある
		 * 	・staticメソッド参照
		 * 	・インスタンスメソッド参照
		 * 	・コンストクラス参照
		 */
		Function<String, Integer> f2 = Integer::parseInt;//Integer::parseInt;　　staticメソッド
		
		/*
		 * staticメソッド参照
		 * 	「クラス名：：メソッド名」
		 */
		List<Integer> list1 = Arrays.asList(3,1,2);
		Consumer<List<Integer>> consumer1 = new Consumer<List<Integer>>() {
			@Override
			public void accept(List<Integer> t) {
				// TODO 自動生成されたメソッド・スタブ
				Collections.sort(t);
			}
		};
		System.out.println(list1);//[3, 1, 2]
		consumer1.accept(list1);
		System.out.println(list1);//[1, 2, 3]
		List<Integer> list2 = Arrays.asList(3,1,2);
		//ラムダ式
		Consumer<List<Integer>> consumer2 = list -> Collections.sort(list2);
		System.out.println(list2);//[3, 1, 2]
		consumer2.accept(list2);
		System.out.println(list2);//[1, 2, 3]
		List<Integer> list3 = Arrays.asList(3,1,2);
		//staticメソッド参照
		Consumer<List<Integer>> consumer3 = Collections::sort;
		System.out.println(list3);//[3, 1, 2]
		consumer3.accept(list3);
		System.out.println(list3);//[1, 2, 3]
		
		//staticメソッド参照２
		Comparator<Integer> comparator1 = (x,y) -> Integer.compare(x, y);
		System.out.println(comparator1.compare(1, 2));//-1
		System.out.println(comparator1.compare(2, 1));//1
		
		Comparator<Integer> comparator2 = Integer::compare;
		System.out.println(comparator2.compare(1, 2));//-1
		System.out.println(comparator2.compare(2, 1));//1
		
		
		/*
		 * インスタンスメソッド参照 
		 * 	「インスタンス変数名：：メソッド名」
		 * java.lang.Iterableインタフェースはオブジェクトを拡張for文の対象にすることができます、
		 * CollectionインタフェースはIterableインタフェースを継承しています。
		 * Iterableインタフェースではデフォルトメソッドとしてforeathが実装されており、
		 * 引数がnullの場合、NillPointerException
		 * default void forEach(Consumer<? super T> action) 
		 */
		List<Integer> list4 = Arrays.asList(3,1,2);
		//foreach使用しない
		for (int a : list4) {
			System.out.print(a);//312
		}
		//forEachを使用、かつラムダ式を引数に渡す
		System.out.println();
		list4.forEach(a -> System.out.print(a));//312
		
		//インスタンスメソッド参照
		System.out.println();
		list4.forEach(System.out::print);//312

		list4.forEach(a -> System.out.print(a + ","));//OK
//		list4.forEach(System.out::print + ",");//NG
		System.out.println();

		//インスタンスメソッド参照２。apply（）を呼び出す時に指定した引数が、toUpperCaseを実行する対象のオブジェクトとして扱われる
		UnaryOperator<String> unaryOperator1 = String::toUpperCase;
		System.out.println(unaryOperator1.apply("test1"));
		String string2 = "test2";
//		UnaryOperator<String> unaryOperator2 = string2::toUpperCase;//The type String does not define toUpperCase(String) that is applicable here

		//インスタンスメソッド参照３　引数あり
		//ラムダ式1 OK
		BiFunction<String, Integer, Character> bFunction1 = (s,i) -> s.charAt(i);
		System.out.println(bFunction1.apply("Java", 2));

		//ラムダ式2 OK
		BiFunction<Integer, String, Character> bFunction2 = (i,s) -> s.charAt(i);
		System.out.println(bFunction2.apply(2, "Java"));
		
		/*
		 * 第一引数がメソッドのを実行する対象のオブジェクトとして渡され、第二引数がそのメソッドの引数
		 * として渡されます
		 */
		//インスタンスメソッド参照１　OK
		BiFunction<String, Integer, Character> bFunction3 = String::charAt;
		System.out.println(bFunction3.apply("Java", 2));
		//インスタンスメソッド参照2　NG
//		BiFunction<Integer, String, Character> bFunction4 = String::charAt;//The type String does not define charAt(Integer, String) that is applicable here
		
		//Stringクラスのインスタンスメソッドであるlength()
//		Function<String, Integer> function1 = s -> s.length();
		Function<String, Integer> function1 = String::length;
		System.out.println(function1.apply("Java"));
		
		//StringクラスのstaticメソッドであるvalueOd(int)
//		Function<Integer, String> function2 = i -> String.valueOf(i);
		Function<Integer, String> function2 = String::valueOf;
		System.err.println(function2.apply(19));
		
		//IntegerクラスのインスタンスメソッドであるhashCode()
		Function<Integer, Integer> function3 = i -> i.hashCode();
		System.out.println(function3.apply(19));
		/*
		 * IntegerにはhashCode()あり
		 * IntegerにはhashCode(int)あり
		 * 指定するのはどれか判定できない（hashCodeメソッド名だけ）ので、エラー
		 */
//		Function<Integer, Integer> function4 = Integer::hashCode;
		
		//コンストラクタ参照
		/*
		 * 通常、クラスをインスタンス化するには、newキーワードの後にコンストラクタを呼び出しています。
		 * これをメソッド参照で行うのが、コンストラクタ参照です。コンストラクタ参照の構文は次のとおりです。
		 * 		クラス名：：new;
		 */
//		Supplier<Foo> supplier1 = () -> new Foo();
		Supplier<Foo> supplier1 = Foo::new;
		System.out.println(supplier1.get().a);
		
//		Function<Integer, Foo> function11 = i -> new Foo(i);
		Function<Integer, Foo> function11 = Foo::new;
		System.out.println(function11.apply(10).a);
		
//		Supplier<List<Foo>> supplier22 = () -> new ArrayList<>();
		Supplier<List<Foo>> supplier22 = ArrayList::new ;
		System.out.println(supplier22.get().size());
		
		Set<Foo> set11 = Set.of(new Foo(10), new Foo(20));
		//Function<Set<Foo>, List<Foo>> set22 = s -> new ArrayList<>();
		Function<Set<Foo>, List<Foo>> set22 = ArrayList::new;
		System.out.println(set22.apply(set11).size());//2
		
		//Function<Integer, String[]> function100 = i -> new String[i];
		// コンストラクタ参照はクラスのインスタンス化だけではなく、配列の生成にも使用可能
		Function<Integer, String[]> function100 = String[]::new;
		System.out.println(function100.apply(5).length);
		
	}

}

class Foo{
	int a=0;
	Foo(){
		
	}
	Foo(int a){
		
	}
}














