package Part4;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

public class F01_Queue {
	/**
	 * FIFO形式によりデータの追加・削除・検査を行う場合は、Queueインタフェースの実装クラスを利用する
	 * FIFOとはFirst In,First Out、先入先出、格納した順にデータを取り出す形式のこと、つまり最後に格納した要素は最後に取り出され
	 * Queue インタフェースには要素を挿入、削除、検査するための操作が提供されています。
	 * これらの操作には2つの種類があり、それぞれにメソッドが提供されています。
	 * 1つは、オペレーション失敗時に例外が発生するものです。
	 * もう1つは、特殊な値（オペレーションに応じてnullまたはfalse）を返すものです。
	 * 後者は、容量の制限されたキューの実装クラス用として特別に設計され
	 */

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		/*
		 * Queueインタフェースの主なメソッド
		 * 
		 * ・挿入
		 * 		・boolean add(E e)
		 * 			要素が追加された場合はtrue、容量制限により要素を追加できない場合はIllegalStateExceptionをスロー
		 * 		・boolean offer(E e)
		 * 			要素が追加された場合はtrue、それ以外の場合はfalse
		 * ・削除
		 * 		・E remove()
		 * 			キューの先頭を取得及び削除する、このキューが空の場合はNoSuchElementExcetionをスロー
		 * 		・E poll()
		 * 			キューの先頭を取得及び削除する、このキューがからの場合はnull
		 * ・検査
		 * 		・E element()
		 * 			キューの先頭を取得する削除しない、このキューが空の場合はNoSuchElementExcetionをスロー
		 * 		・E peek()
		 * 			キューの先頭を取得するが削除しない、このキューがからの場合はnull
		 */

		/**
		 * Queueのサブインターンとして、Duqueインタフェースがあり
		 * Queueと逆だ、Last In,First Out、後入れ先出だ
		 * Deque:double ended queueの略語
		 */
		/*
		 * Dequeの主なメソッド：
		 * ・先頭操作用
		 * 		・挿入
		 * 			・void addFirst(E e)       例外のスロー
		 * 			・boolean offerFirst(E e)　特殊の値
		 * 				指定された要素を先頭に挿入する
		 * 		・削除
		 * 			・E removeFirst()       例外のスロー
		 * 			・E pollFirst(E e)　特殊の値
		 * 				キュー先頭の要素を取得及び削除する
		 * 		・検査
		 * 			・E getFirst()       例外のスロー
		 * 			・E peekFirst()　特殊の値
		 * 				キュー先頭の要素を取得するが削除しない
		 * ・末尾操作用
		 * 		・挿入
		 * 			・void addLast(E e)       例外のスロー
		 * 			・boolean offerLast(E e)　特殊の値
		 * 				指定された要素を末尾に挿入する
		 * 		・削除
		 * 			・E removeLast()       例外のスロー
		 * 			・E pollLast(E e)　特殊の値
		 * 				キュー末尾の要素を取得及び削除する
		 * 		・検査
		 * 			・E getLast()       例外のスロー
		 * 			・E peekLast()　特殊の値
		 * 				キュー末尾の要素を取得するが削除しない
		 */
		
		/*
		 * Queue
		 *   |___Deque
		 *         |___ArrayDeque
		 */
		/*
		 * Queueのメソッドを使用
		 */
		Queue<String> queue1 = new ArrayDeque<String>();
		queue1.add("1");
		queue1.add("2");
		queue1.add("3");
		System.out.println(queue1);//123
		System.out.println("queue1.element" + queue1.element());//1
		System.out.println(queue1);//123
		System.out.println("queue1.remove" + queue1.remove());//1
		System.out.println(queue1);//23
		
		
		Queue<String> queue2 = new ArrayDeque<String>();
		queue2.offer("1");
		queue2.offer("2");
		for (String string : queue2) {
			queue2.poll();
		}
//		System.out.println(queue2.element());//java.util.NoSuchElementException
		System.out.println(queue2.peek());//null
		
		
		/*
		 * スタックメソッド
		 * 次のサンプルでは、LIFO（後入れ先出し）スタックとして使用しています。
		 * スタックとして使用する場合、両端キューの先頭から要素のプッシュとポップを行います。
		 * スタックのメソッドは、次の表に示すようにDequeメソッドと同等です。
		 * 
		 * スタックのメソッド				等価なDequeメソッド（例外をスロー）
		 * void push(E e)				void addFirst(E e)
		 * E pop()						E removeFirst()
		 * E peek()						E getFirst()
		 */
		Deque<String> deque1 = new ArrayDeque<String>();
		deque1.push("1");
		deque1.push("2");
		deque1.push("3");
		System.out.println(deque1);//321
		System.out.println(deque1.pop());//3
		System.out.println(deque1.peek());//2
		System.out.println(deque1);//21
 	}

}















