package Part4;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;

public class YYY_Practice01 {
	/**
	 * 答え：
	 * 	1×:B　→　D
	 *  2:C
	 *  3×:F　→　E
	 *  4×:A　→　AD
	 *  5:AE
	 *  6×:AC　→　AD
	 *  7:B
	 *  8:C
	 *  9:C
	 *  10:E
	 *  11:AE
	 *  12×:BD　→　BC
	 *  13:BDF
	 *  14×:F　→　A
	 *  15×:C　→　A
	 */

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		
		//No.3
		//最初には＜＞指定しないと、OBJECTとして扱う
		List list3 = new ArrayList();
		list3.add("a");
		list3.add(10);
		for (Object object : list3) {//OK
//		for (String object : list3) {//型の不一致: 要素タイプ Object から String には変換できません
			System.out.println(object);
		}
		
		//No.4
		/*
		 * LinkedList
		 *    |
		 *      implements List<E>, Deque<E>,
		 * ArrayList
		 *    |
		 *      implements List<E>,
		 * Vector
		 *    |
		 *      implements List<E>,
		 * Listインタフェースには
		 * 		①boolean remove(E e)  指定しているオブジェクトがあれば削除する
		 * 		②E remove(int index)指定するインデックス要素を削除する
		 * Queueインタフェースには
		 * 		①E remove()  引数ないメソッド：先頭要素を削除する
		 * でも、QueueはCollectionのサブインタフェースなので、
		 * Collectionには①boolean remove(E e)があるので、remove(1)問題ない、
		 * そういう場合、１はインデックスではない、削除したいオブジェクトになる
		 * Queueには１の要素がなければ、削除を行わずに実行していたfalseを返す
		 * 
		 */
		Queue<Integer> list4 = new LinkedList<>();
//		Queue<Integer> list5 = new ArrayList()<>();//NG 
//		Queue<Integer> list6 = new Vector<>();//NG
		list4.add(100);
		list4.add(200);
		list4.remove(1);
		System.out.println(list4);
		
		
		//No.12
		ArrayDeque<?> obj121 = new ArrayDeque<String>();
//		foo(obj121);//型 YYY_Practice01 のメソッド foo(List<?>) は引数 (ArrayDeque<capture#1-of ?>) に適用できません
		
		ArrayList<? super Number> obj122 = new ArrayList<Number>();
		foo(obj122);	

		ArrayList<? extends Number> obj123 = new ArrayList<Integer>();
		foo(obj123);	
		
//		List<?> list124 = new ArrayList<?>();//型 ArrayList<?> のインスタンスを生成できません
//		foo(obj124);
		
//		List<Object> obj125 = new LinkedList<String>();//型の不一致: LinkedList<String> から List<Object> には変換できません
		List<?> obj125 = new LinkedList<String>();//OK
		foo(obj125);
		
		
		//No.15
		var t1 = new Test15("apple", 30);
		var t2 = new Test15("banana", 10);
		var set1  = new TreeSet<Test15>();
		set1.add(t1);
		set1.add(t2);
		System.out.println(set1);//SET要素は必ずComparableを実装した。ディフォルトソート順としてはComparableのcompareTo。オーバーライドしていない場合は自然順序
//		　　　[apple, banana]
		
		var set2 = new TreeSet<Test15>(t1);//引数あるTreeSetはComparatorを実装したクラス。SETのディフォルトソート順は自然順を引数のComparatorのcompareメソッドになる
//		var set2 = new TreeSet<Test15>(new Test15(null, 0));//効果が同じ
		/*
		public TreeSet(Comparator<? super E> comparator) {
        	this(new TreeMap<>(comparator));
    	}
		 */
		set2.add(t1);
		set2.add(t2);
		System.out.println(set2);//[banana, apple]
	}

	//No.12
	public static void foo(List<?> list) {
		System.out.println(list.size());
	}
	
	
}



class Test15 implements Comparable<Test15>,Comparator<Test15>{
	private String msg;
	private int number;
	public Test15(String msg, int number) {
		// TODO 自動生成されたコンストラクター・スタブ
		this.msg = msg;
		this.number = number;
	}
	
	@Override
	public int compare(Test15 o1, Test15 o2) {
		// TODO 自動生成されたメソッド・スタブ
		return o1.number - o2.number;
	}

	@Override
	public int compareTo(Test15 o) {
		// TODO 自動生成されたメソッド・スタブ
		return this.msg.compareTo(o.msg);
	}
	
	@Override
	public String toString() {
		// TODO 自動生成されたメソッド・スタブ
		return msg;
	}
}






















