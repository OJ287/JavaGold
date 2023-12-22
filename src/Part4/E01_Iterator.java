package Part4;

import java.util.Iterator;
import java.util.TreeSet;

public class E01_Iterator {
	/**
	 * イテレータとは、コレクション内の要素に順番にアクセスする手段です
	 * 目的の異なるコレクションにおいて、共通の操作方法で要素へのアクセスを可能にします。
	 * イテレータは「反復子」とも呼ばれ、アクセスする要素を指し示すカーソルようなものと考えてよいでしょう。
	 * 先頭から順番に要素ヘアクセスしていきます
	 * イテレータは、Iterator インタフェースとして提供されており、
	 * 各実様がラスで提供されているiterator(）メソッドを使用してIterator オブジェクトを取得
	 * 
	 * Iterator<E> iterator()
	 * 		このコレクションの要素に対する反復子を返す
	 */

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		/*
		 * Iteratorインタフェースのメソッド
		 * ・boolean hasNext()
		 * 		次の要素がある場合にtrueを返す
		 * ・E next()
		 * 		次の要素を返す
		 * ・default void remove()
		 * 		next()の呼び出しごとに1回だけ呼び出すことができ、イテレータによって最後に返された要素を削除する
		 */
		TreeSet<String> treeSet = new TreeSet<String>();
		treeSet.add("C");
		treeSet.add("A");
		treeSet.add("B");
		Iterator<String> iterator = treeSet.iterator();
		while (iterator.hasNext()) {
			String string = (String) iterator.next();
			System.out.print(string);//ABC
		}
		
		
	}

}
