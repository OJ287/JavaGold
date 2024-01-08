package Part4;

import java.util.Arrays;
import java.util.List;

public class K01_CollectionsMethod {
	/**
	 * コレクションが提供する便利なメソッド
	 */

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		/*
		 * Collections
		 * Collections クラスは、コレクションに関する様々な操作をまとめたクラスです。
		 * このクラスでもソート機能が提供されています。J01で使用したsortメソッドはオーバーロードされており、
		 * リストのみを引数に渡すと、その要素の自然順序づけに従って昇順にソートします。
		 * また、第1引数にリスト、第2引数にComparator インタフェースを実装したクラスのオブジェクト（コンパレータ）を渡すと、
		 * コンパレータが示す順序に従ってリストがソートされます。
		 * 
		 * ①：static <T extends Comparable<? super T>> void sort(List<T> list)
		 * 　　要素の自然順序づけにしたがって、指定されリストを昇順にソートする
		 * ②：static <T> void sort(List<T> list, Comparator<? super T> c)
		 * 　　指定されコンパレータが示す順序に従って、指定されたリストをソートする。なお第二引数のnullが指定されると自然順序になる
		 * ③：static void reverse(List<T> list)
		 * 　　指定されリストの要素の順序を逆になる
		 */
		
		/*
		 * Array
		 * Collections クラスがコレクションに対する操作を行うクラスであるのに対し、
		 * 配列の操作を行うクラスがArrays クラスです。
		 * Arraysクラスでも、配列に対して自然順序に従ったソートを行えます。
		 * 
		 * ①：static <T> List<T> asList(T... a)
		 * 　　引数で指定された配列を元にリストを作成する
		 * ②：static void sort(Object[] a)
		 * 　　要素の自然順序づけに従って、指定されたオブジェクトの配列を昇順でソートする。
		 * 　　全ての要素はComparableインタフェースを実装している必要がある
		 * ③：static <T> void sort(T a, Comparator<? super T> c)
		 * 　　指定されたコンパレータが示す順序に従って、指定されたオブジェクトの配列をソートする
		 * 　　なお、第二引数にnullが指定されると自然順序になる
		 */
		/*
		 * sort(）メソッドにより、引数で指定された配列内の要素をソートできます。
		 * このメソッドの引数は、各種データ型に対応できるようにオーバーロードされています。
		 * ただし、様々な参照型のオブジェクトを要素にもつ配列をSor()メソッドでソートしようとすると、
		 * 実行時に ClassCastException 例外が発生
		 */
		int[] array1 = {3,1,2};
		print(array1);//312
		System.out.println();
		Arrays.sort(array1);
		print(array1);//123
		Object[] array2 = {"aa", 10};
//		Arrays.sort(array2);//Exception in thread "main" java.lang.ClassCastException: class java.lang.String cannot be cast to class java.lang.Integer (java.lang.String and java.lang.Integer are in module java.base of loader 'bootstrap')
	
		/*
		 * Arraysクラスでは、配列をリストに変換するためのasListl）メソッド提供されています。
		 * このメソッドはリストに変換した配列を引数に渡します。
		 * ただし、固定サイズのリストとして変換されるため、要素の上書きは可能ですが、要素の追加や削除はできません。
		 */
		String[] array3 = {"A", "B", "C"};
		List<String> list1 = Arrays.asList(array3);//asList()によって作成されたリストは固定サイズのリストである
//		list1.add("D");//コンパイルは成功するが、実行時エラーになるjava.lang.UnsupportedOperationException
		list1.set(2, "E");
		for (String string : list1) {
			System.out.print(string + "   ");//ABE
		}
	
	}
	private static void print(int[] ary) {
		for (int i : ary) {
			System.out.println(i + "   ");
		}
	}
}














