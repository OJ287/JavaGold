package Part4;

import java.awt.print.Printable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.TreeMap;
import java.util.TreeSet;

public class J01_ObjectSort {
	/**
	 * オブジェクトの順序づけ
	 * 順序づけが行われている場合は、要素の検索が高速化される
	 * 個々の項目を特定することも簡単になる
	 */
	/**
	 * Comparableインタフェースと
	 * Comparatorインタフェース
	 * ソート時の順序関係を独自に規定したい場合は、上記２インタフェースを利用する
	 */

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		/*
		 * HashSetは順不同で管理していますが、TreeSetは要素をソートして管理しています。
		 * なお、このソートは自然順序づけ（文字列は辞書順、数値は昇順）に従って行われます。
		 * また、TreeMapはキーをもとにソートして管理しています。
		 */
		HashSet<Integer> hashSet1 = new HashSet<>();
		hashSet1.add(300);
		hashSet1.add(200);
		hashSet1.add(500);
		System.out.println("hashSet1:" + hashSet1);//hashSet1:[500, 200, 300]
		
		TreeSet<Integer> treeSet1 = new TreeSet<>();
		treeSet1.add(300);
		treeSet1.add(200);
		treeSet1.add(500);
		System.out.println("treeSet1:" + treeSet1);//treeSet1:[200, 300, 500]
		
		TreeSet<String> treeSet2 = new TreeSet<>();
		treeSet2.add("nao");
		treeSet2.add("Nao");
		treeSet2.add("100");
		System.out.println("treeSet2:" + treeSet2);//treeSet2:[100, Nao, nao]
		
		TreeMap<String, Integer> treeMap1 = new TreeMap<>();
		treeMap1.put("2", 300);
		treeMap1.put("3", 20);
		treeMap1.put("1", 500);
		System.out.println("treeMap1:" + treeMap1);//treeMap1:{1=500, 2=300, 3=20}
		
		
		/*
		 * Comparable
		 * クラスの自然順序づけを提供するためのインタフェース
		 * 
		 * 引数を持たないコンストラクタを使用してTreeSetクラスをインスタンス化した場合、
		 * 格納する要素はこのComparableインタフェースを実装したオブジェクトであることを前提としています。
		 * だから、Comparableインタフェースを実装していないオブジェクトを格納しようとすると、実行エラーになる
		 * 
		 * 独自のソート順：
		 * 	compareTo()をオーバーライド
		 *  public int compareTo(T o)
		 *  	自オブジェクトと引数oに渡されたオブジェクトと比較する
		 *  	比較した結果、戻り値として整数値を返す
		 *  	比較した結果							戻り値		説明
		 *  	自オブジェクト==比較対象オブジェクト		０			自オブジェクトが保持する値と比較対象オブジェクトの値と同じ
		 *  	自オブジェクト< 比較対象オブジェクト		負の値		自オブジェクト小さい、ソートの時並び順は自オブジェクトが前にくる
		 *  	自オブジェクト> 比較対象オブジェクト		正の値		自オブジェクト大きい、ソートの時並び順は自オブジェクトが後ろにくる
		 */
		
		/*
		 * Comparatorインタフェース
		 * Comparable インタフェースでは、ソートの対象となるオブジェクト自身にcompareloメソッドを実装していました。
		 * ソートの対象となるオブジェクトから、比較ルールを独立したクラスとして定養することも可能です。
		 * その際に、java.utilにパッケージングされているComparator インタフェースを使用する
		 * Comparator インタフェースには、抽象メソッドとしてcompare()およびequals()メソッドが宣言されています。
		 * 各実装クラスでは、compare（）メソッドをオーバーライドし、オブジェクトの並び順を決定する実装を行います。
		 * なお、実装クラスでは、java.lang.Objectクラスをスーパークラスにもつため、equals（）メソッドの実装は任意です
		 */
		Employee employee1 = new Employee("taro", 20);
		Employee employee2 = new Employee("takayuki", 10);
		Employee employee3 = new Employee("hiromi", 50);
		ArrayList<Employee> arrayList1 = new ArrayList<>();
		arrayList1.add(employee1);
		arrayList1.add(employee2);
		arrayList1.add(employee3);
		System.out.println("arrayList1のインデックス順を表示");
		print(arrayList1);//20   10   50

		System.out.println("名前のIDでの表示");
		Collections.sort(arrayList1, new MyRule());
		print(arrayList1);//10  20  50
		
		System.out.println("名前の文字数での表示");
		Collections.sort(arrayList1, 
				new Comparator<Employee>() {
					public int compare(Employee o1, Employee o2) {
						return o1.getName().length() - o2.getName().length();
							}
				}
				);
		print(arrayList1);//20  50  10
		
		/*
		 * Comparator主なメソッド
		 * ①： static <T extends Comparable<? super T>> Comparator<T> naturaOrder()
		 * 		自然順序で比較するComparatorを返す。nullを比較した場合、NullPointerExceptionをスロー
		 * ②： static <T extends Comparable<? super T>> Comparator<T> reverseOrder()
		 * 		自然順序の逆で比較するComparatorを返す。nullを比較した場合、NullPointerExceptionをスロー
		 * ③： static <T> Comparator<T> nullsFirst(Comparable<? super T> comparator)
		 * 		nullを含む比較を行うComparatorを返す。nullは先頭になる
		 * ④： static <T> Comparator<T> nullsLast(Comparable<? super T> comparator)
		 * 		nullを含む比較を行うComparatorを返す。nullは末尾になる
		 * ⑤： default Comparator<T> reversed()
		 * 		このComparatorの逆順を義務付けるComparatorを返す。
		 */
		
		
	}
	private static void print(ArrayList<Employee> arrayList) {
		for (Employee employee : arrayList) {
			System.out.println(employee.getId() + "  " + employee.getName());
		}
	}

}
class Employee{
	private String name;
	private Integer id;
	public Employee(String name, Integer id) {
		this.id = id;
		this.name =name;
	}
	public Integer getId() {
		return id;
	}
	public String getName() {
		return name;
	}
}
class MyRule implements Comparator<Employee>{
	@Override
	public int compare(Employee o1, Employee o2) {
		// TODO 自動生成されたメソッド・スタブ
		return o1.getId().compareTo(o2.getId());
		/*
		 * Integerクラスはimplements Comparable<Integer>を実装して
		 * compareToを使用、Comparableを実装していないクラスなら、カスタマイズできる
	    public int compareTo(Integer anotherInteger) {
	        return compare(this.value, anotherInteger.value);
	    }
	    */
	}
}





















