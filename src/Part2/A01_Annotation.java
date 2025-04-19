package Part2;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class A01_Annotation {
	/**
	 * アノテーションの使用目的:
	 * 注釈と訳される、ソースコードに対して注釈をつけることで、このコードを解釈するコンパイラや
	 * 実行するJVMに付加情報を伝える
	 */
	/**
	 * よく使用されるJAVAアノテーションjava.langパッケージで提供されてるので、インポット文不要
	 * @Override
	 * 		スーパークラスのメソッドをオーバーライドすることを示す
	 * @FunctionalInterface
	 * 		関数型インタフェースであることを示す
	 * @Deprecated
	 * 		非推奨の要素であることを示す
	 * @SuppressWarnings
	 *		コンパイラの警告を無効にする 
	 * @SafeVarargs
	 * 		安全でない可変長引数に対する警告を無効にする
	 */

//	@SuppressWarnings({"unchecked", "deprecation"})
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

		/*
		 * @FunctionalInterface
		 * 	関数型インタフェースとしての要件を満たしているかをチェックする
		 * 要件：
		 * 	＠単一の抽象メソッドを持つインタフェースとする  有且必须有一个抽象方法（Object的方法不算在内）
		 * 	＠ただし、staticメソッドやデフォルトメソッドは定義可能
		 * 	＠Objectクラスのpublicメソッドは抽象メソッドとしての宣言は可能
		 * 	＠関数型インタフェースとして明示する場合は、@FunctionalInterfaceを付与する
		 */
		
		/*
		 * @Deprecated
		 * アノテーションをつけている要素はプログラマが薦められない要素である。
		 * 該当する要素はコンストクラス、メソッド、変数など
		 * JDK9以降、@Deprecatedに補足情報としてsinceとforRemoval要素を指定することが可能
		 * 各要素はデフォルト値があるため、指定は任意
		 * since：型String、説明：非推奨になったバージョンを示す。デフォルト値は空の文字列
		 * forRemoval：型boolean、説明：trueは将来のバージュンで注釈付きプログラム要素を削除する意図を示す
		 * 				falseは注釈月プログラム要素の使用が推奨されないことを示す、デフォルトはfalse
		 */
		@Deprecated(since = "1.0", forRemoval = false)
		int deprecated = 10;
		System.out.println(deprecated);
		
		/*
		 * @SuppressWarnings
		 * 指定できる主な警告は
		 * 	・unchecked：List<String>の代わりにListを使用など、型の使用に関連する警告を抑制する
		 *  ・deprecation：@Deprecatedつけられた要素を使用する警告を抑制する
		 *  書き方は以下の五つがある。メンバがvalue一つだけので、省略してもOK
		 */
//		@SuppressWarnings(value = {"unchecked"})
//		@SuppressWarnings(value = {"unchecked", "deprecation"})
//		@SuppressWarnings("unchecked")
//		@SuppressWarnings({"unchecked"})
//		@SuppressWarnings({"unchecked", "deprecation"})
		@SuppressWarnings("rawtypes")
		List list = new ArrayList<>();
		@SuppressWarnings("deprecation")
		Date date = new Date("2023/12/10");

		/*
		 * @SafeVarargs
		 * 安全でない可変長引数に対する警告を無効にする
		 * @SuppressWarnings({"unchecked"})との違いは、@SafeVarargsは抑制対象メソッドの呼び出し元の警告まで抑制する
		 * @SuppressWarningsは抑制対象メソッドだけの警告を抑制、呼び出し元も抑制したい場合SuppressWarningsは呼び出し元でも使う
		 * 
		 * @SafeVarargsをつけるメソッドは、final,static,privateのいずれか（もしくはその組み合わせ、またはいずれかを含める）
		 * である必要がある。public final,public static,protected final,protected staticでもOK
		 */
		m(new ArrayList<String>(), list);
	}
	@SafeVarargs//アノテーションなければ：警告：Type safety: Potential heap pollution via varargs parameter strings
//	@SuppressWarnings({"unchecked"})
	static void m(List<String>... strings){
		System.out.println(strings);//[Ljava.util.List;@606d8acf
		System.out.println(strings[0]);//[]
		System.out.println(strings[1]);//[]
	}
}
/*
 * @FunctionalInterface
 * アノテーションを使用し、単一の抽象メソッドなければ
 * 抽象メソッドが見つからないから、機能インタフェースではない普通のインタフェースである
 */
@FunctionalInterface
interface FunctionInter<T>{
	void foo(T t);// 単一の抽象メソッドなければ、NG　　Invalid '@FunctionalInterface' annotation; FunctionInter<T> is not a functional interface
	String toString();
	boolean equals(Object object);
	static void X() {}
	default void Y() {}
}



