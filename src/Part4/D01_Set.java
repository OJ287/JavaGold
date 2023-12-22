package Part4;

import java.util.HashSet;
import java.util.TreeSet;

public class D01_Set {
	/**
	 * 	Setインタフェースの実装
	 * 	・要素の重複を許したくない、一意の要素しか格納できない
	 * 	・Setのなかにバラバラに入る、添え字をつけずに順不同で管理する
	 */
			

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		/*
		 * Setインタフェースの主な実装クラス
		 * ・HashSet
		 * 		データ項目へのアクセスは Treesetよりも高速であるが、データ項目を順序づけることはできない。
		 * 		ソートも順序づけも行われないことが前提となる。同期性はサポートしていない
		 */
		/*
		 * ・SortedSet
		 * 		SortedSetインタフェースの実装クラスである。ソートされたデータ項目を得られるが、
		 * 		アクセス速度は HashSet よりも低速である。同期性はサポートしていない
		 */
		/*
		 * ・LinkHashSet 
		 * 		HashSetと同等の機能に加えて、すべてのデータ項目に対する二重リンクリストを追加したものである。
		 * 		これは順序づけられたコレクションの一種であるが、その順序は挿入順であって、ソートされるのではない。
		 * 		同期性はサポートしていない
		 */
		String[] aryStrings = {"AAA","BBB","CCC"};
		HashSet<String> hashSet = new HashSet<String>();
		hashSet.add(aryStrings[0]);
		hashSet.add(aryStrings[1]);
		hashSet.add(aryStrings[2]);
		hashSet.add(aryStrings[0]);
//		hashSet.add(aryStrings[2]);
		System.out.println("hashSet size:" + hashSet.size());//3
		for (String string : hashSet) {
			System.out.print(string);//AAACCCBBB
		}
		System.out.println();
		TreeSet<String> treeSet = new TreeSet<String>();
		treeSet.add(aryStrings[0]);
		treeSet.add(aryStrings[2]);
		treeSet.add(aryStrings[1]);
		treeSet.add(aryStrings[0]);
		System.out.println("treeSet size:" + treeSet.size());//3
		for (String string : treeSet) {
			System.out.print(string);//AAABBBCCC   内部でソートが行われる
		}
		
		System.out.println();
		HashSet<Foo> hashSet2 = new HashSet<Foo>();
		Foo foo1 = new Foo("AAA");
		Foo foo2 = new Foo("BBB");
		Foo foo3 = new Foo("CCC");
		Foo foo4 = new Foo("AAA");
		hashSet2.add(foo1);
		hashSet2.add(foo2);
		hashSet2.add(foo3);
		hashSet2.add(foo4);
		//オブジェクトが同一かどうかの判定にはequalsメソッドが使用される
		System.out.println("hashSet2.size()" + hashSet2.size());//3
		for (Foo foo : hashSet2) {
			System.out.print(foo);//AAA  CCC  BBB 
		}
		
	}
}
class Foo{
	private String string;
	public Foo(String string) {
		this.string = string;
	}
	@Override
	public String toString() {
		// TODO 自動生成されたメソッド・スタブ
		return string + "  ";
	}
	@Override
	public int hashCode() {
		// TODO 自動生成されたメソッド・スタブ
		return string.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		// TODO 自動生成されたメソッド・スタブ
		return this.hashCode() == obj.hashCode();
	}
	
}

