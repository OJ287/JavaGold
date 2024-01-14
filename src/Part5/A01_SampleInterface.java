package Part5;

import java.time.Year;
import java.util.function.Function;

import javax.swing.DefaultCellEditor;

/**
 * SampleInterface
 * https://qiita.com/j1403239/items/fc7b9600382f09ea979a
 * 関数型インタフェースとは、定義されている抽象メソッドが一つだけのインタフェース。
 * 　　なお、staticメソッドやデフォルトメソッドが含まれていても、抽象メソッドが一つだけであれば関数型インタフェースである
 * 関数型インタフェースはjava.util.functionパッケージで多数提供されている
 */
public class A01_SampleInterface {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		/*
		 * 匿名使用すると、可読性が良くなくなる
		 * ラムダ式を使用すると、シンプルになる
		 */
		String string1 = new Function<String, String>() {
			@Override
			public String apply(String t) {
				// TODO 自動生成されたメソッド・スタブ
				return "Hello " + t;
			}
		}.apply("Test");
		System.out.println(string1);
		
		/*
		 * 独自の関数型インタフェースの定義
		 * 要件：
		 * 　・単一の抽象メソッドを持つインタフェースとする
		 * 　・正し、staticメソッドやディフォルトメソッドは定義可能
		 * 　・java.lang.Objectクラスのpublicメソッドは抽象メソッドとしての宣言は可能
		 * 　・関数型インタフェースとして明示する場合は、@FunctionInterfaceを付与する
		 */
	}

}

@FunctionalInterface
/*
 * @FunctionalInterfaceの付与は任意ですが、関数がたインタフェースとしての定義を満たしているかをチェックする役割をする
 * @FunctionalInterfaceの付与があれば、抽象メソッドを二つ宣言するとコンパイルエラーになる
 */
interface MyFuncInter<T> {//Invalid '@FunctionalInterface' annotation; MyFuncInter<T> is not a functional interface
	void foo(T t); //抽象メソッド
//	void foo2(T t); //抽象メソッド
	String toString();  //Objectクラスのpublicメソッド
	boolean equals(Object object);//Objectクラスのpublicメソッド
	static void X() {};//staticメソッド
	default void Year() {};//defaultメソッド
}
























