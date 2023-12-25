package Part4;

import java.util.ArrayList;
import java.util.List;

public class I05_MyGenerics {
	/**
	 * ワイルドカードを使用したジェネリック
	 * 型パラメータリスト内では、ワイルドカード「？」を使用できます。
	 * たとえば、以下のようなコードがあったとします。
	 * Map<Integer, ?> map = method();
	 */

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		/*
		 * method（メソッドを呼び出した結果、キーはInteger型のマップが返るが、
		 * 値の型は実行時に決まるような場合、型パラメータに「？」を指定します。
		 * 次のサンプルコードを見てください。
		 */
		int val = Integer.parseInt("100");
		if (val == 100) {
			print(new Gen4<>(100));
		}else {
			print(new Gen4<>("abc"));
		}
		
		/*
		 * ワイルドカードでは、extends及びsuperを使用して型を限定することも可能
		 * 構文：
		 * 		＜？　extends　タイプ＞//①
		 * 		＜？　super　タイプ＞//②
		 * ①は、extendsキーワードを指定しているため、
		 * タイプに指定したデータ型やそのサブクラス（またはそのサブインタフェース）に対応する、ということを意味します。
		 * ②は、superキーワードを指定しているため、タイプに指定したデータ型やそのスーパークラス
		 * （またはそのスーパーインタフェース）に対応する、ということを意味します。
		 */
		List<X> l1 = new ArrayList<>();l1.add(new X());
		List<Y> l2 = new ArrayList<>();l2.add(new Y());
		method1(l1);//X
		method1(l2);//Y
		method2(l1);//X
		method2(l2);//Y
		

	}
	private static void print(Gen4<?> obj) {
		System.out.println(obj.getVar());
	}
	
	public static void method1(List<? extends X> list) {
//		list.add(new X());//L11   NG  型 List<capture#2-of ? extends X> のメソッド add(capture#2-of ? extends X) は引数 (X) に適用できません
//		list.add(new Y());//L12  NG  型 List<capture#2-of ? extends X> のメソッド add(capture#2-of ? extends X) は引数 (Y) に適用できません
		System.err.println(list.get(0));
	}
	public static void method2(List<? super Y> list) {//L16
//		list.add(new X());//L17   NG  型 List<capture#3-of ? super Y> のメソッド add(capture#3-of ? super Y) は引数 (X) に適用できません
		list.add(new Y());//L18
		System.err.println(list.get(0));
	}
	/*
	 * 今度は11、12、17、18行目を見てください。
	 * ワイルドカードを使用している場合、各メソッド側では実行時まで引数で受け取るオブジェクトの型がわかりません。
	 * したがって、add（）メソッドにより何かしらのオブジェクトを格納するようなコードを記述するとコンパイルエラーとなります。
	 * ただし、く？super タイプ＞の場合（16行目）のみ、タイプと同じ型のオブジェクトであれば要素の追加は可能です。
	 */
}

class Gen4<T>{
	private T var;
	public Gen4(T var) {
		this.var = var;
	}
	public T getVar() {
		return var;
	}
}


class X {
	public String toString() {
		return "X:";
	}
}
class Y extends X {
	public String toString() {
		return "Y:";
	}
}