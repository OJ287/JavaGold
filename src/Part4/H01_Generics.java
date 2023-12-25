package Part4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class H01_Generics {
	/**
	 * ジェネリックス
	 * クラス定義などで汎用的に型を示しておき、利用時に目的のオブジェクトの型を当て跳ねることでより安全で
	 * 再利用性の高い
	 * 
	 * https://java-code.jp/156
	 * List<String>のように、<…>の形式で型を明示することで、String型専用のリストを生成できます。
	 * リストから取り出した要素はすべてObject型と見なされますので、いちいち型キャストしなければならないのです。
	 * ジェネリックスを利用することで、こうした手間や、キャストミスによる例外の発生などを防ぐことができます。
	 * また、リストに要素をセットする際も、想定外の型がセットされることを防げます。
	 * 
	 * ジェネリックスの要素型
	 * 	ジェネリックスの<…>には、任意の型を指定できます。
	 * 	たとえば「List<List<String>>」とすることで、String型のリストを要素に持つリスト、つまり二次元のリストを表現できます。
	 * 	また、「Map<String, String>」のように型を複数受け取るクラスもあります。型はカンマ区切りで指定します。
	 */

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		//従来のコレクション↓、異なる型のオブジェクトが格納できるが、取得は必ずObject型。
//		List list1 = new ArrayList<>();
//		list1.add("111");//OK
//		list1.add("222");//OK
//		list1.add(new Integer(100));//OK
//		for (int i = 0; i < list1.size(); i++) {
//		最初、コレクションの生成時、データの扱う時にもデータ型の指定が不要ですが、GET取得する際にObject型で返す、目的の型にキャストする必要がある、なければ絶対にエラーになるわけではない
//			System.out.println((String)list1.get(i));//NG  class java.lang.Integer cannot be cast to class java.lang.String (java.lang.Integer and java.lang.String are in module java.base of loader 'bootstrap')
//		}
		
		//従来型のコレクション、Boxing
//		従来型のコレクションの記述をしたプログラムをコンパイルすると、型チェックが完全ではないため
//		コンパイル時に警告が出力されている。しかしmあくまで警告でありコンパイルは成功
		List list3 = new ArrayList<>();//警告：List は raw 型です。 総称型 List<E> への参照は、パラメーター化する必要があります
		Integer i1 = 1;int i2 = 2; Integer i3 = i1;
		list3.add(i1);//警告：型の安全性: メソッド add(Object) は raw 型 List に属しています。 総称型 List<E> への参照はパラメーター化される必要があります
		list3.add(i2);
		list3.add(i3);
		for (int i = 0; i < list3.size(); i++) {
//			Integer integer = list3.get(i);//型の不一致: Object から Integer には変換できません
			Integer integer = (Integer)list3.get(i);
		}
		
				
		
		//ジェネリックス対応のコレクション
		List<String> list2 = new ArrayList<String>();
		list2.add("111");
		list2.add("222");
//		list2.add(333);//型 List<String> のメソッド add(int, String) は引数 (int) に適用できません
		for (int i = 0; i < list2.size(); i++) {
			String string = list2.get(i);
			System.out.println(string);
		}
		
		/*
		 * ジェネリックス対応のコレクションの場合
		 * ・コレクションを生成する際に格納する要素のデータ型を指定する
		 * ・コンパイル時に型チェックを行うことが可能
		 * ・要素の取得するときのキャストは不要
		 * ・<E>の記述は型パラメータリストと呼ぶ、格納するオブジェクトの型を指定するために使用される
		 * ・左辺と右辺のデータ型を合わせる必要がある
		 */
		ArrayList<String> list4 = new ArrayList<String>();//OK
//		ArrayList<Object> list5 = new ArrayList<String>();//NG　コンパイルエラー　　型の不一致: ArrayList<String> から ArrayList<Object> には変換できません
//		ArrayList<String> list6 = new ArrayList<Object>();//NG　コンパイルエラー　　型の不一致: ArrayList<Object> から ArrayList<String> には変換できません
		
		/*
		 * ダイヤモンド演算子
		 * ・左辺と右辺のデータ型を合わせる必要があるけど、SE７からは表記を簡略化して記述できるようになった
		 * 		右辺（コンストクラスを呼ぶ）に必要なデータ型が省略できる　　　だって、ダイヤモンド演算子と呼ぶ
		 * ダイヤモンド演算子は、コンパイル時にデータ型が明白であれば利用可能である
		 */
		ArrayList<String> list7 = new ArrayList<>();//OK
		Map<Integer, String> map = new HashMap<>();
		map.put(10, "10");
		List<String> list8 = new ArrayList<>();
		list8.add("list8");
		methodA(list8);
		methodA(new ArrayList<>());
		List<String> list9 = methodB();
		
		
	}
	static void methodA(List<String> list) {
//	static void methodA(List<> list) {//型 List<E> の引数の数が誤っています。引数 <> でパラメーター化できません
		System.out.println("mehtodA");
		list.add("mehtodA");
	}
	static List<String> methodB() {
//	static List<> methodB() {//型 List<E> の引数の数が誤っています。引数 <> でパラメーター化できません
		System.out.println("methodB");
		return new ArrayList<>();
	}

}
