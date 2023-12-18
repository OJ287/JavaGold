package Part4;

import java.util.ArrayList;

public class C01_List {
	/**
	 * Listインタフェースの実装
	 * ・要素の重複を許可
	 * ・順序づけを行い
	 * の要件で利用したい場合、Listインタフェースの実装クラスを利用
	 * 配列と比較し、
	 * 	・サイズ変更は可能
	 * 	・添え字を使用して順序立てて要素を管理
	 */

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		/*
		 * Listインタフェースを実装しているおもなクラス：
		 * ArrayList:
		 * 	サイズ変更可能な配列である。通常の配列と同様に、リスト中の特定要素に対しては随時アクセスが行えるが、
		 * 	挿入と削除の処理は線形的に実行される。
		 * 	逆に、挿入と削除を行う頻度が高く、ランダムアクセスをする頻度が低い場合は、LinkedListの使用が適している。
		 * つまり ArrayList は、ランダムアクセス（検索）は高速だが、挿入と削除は低速である。同期性はサポートしていない
		 */
		/*
		 * LinkedList
		 * 	LinkedList中の各ノード（要素）には、個々のデータ項目に加えて次のノードに対するポインタが格納されている。
		 * この場合、特定の要素に対する検索は、1つのノードから次のノードという形で行われる。
		 * これにより、挿入と削除がArrayList より高速に行われる。同期性はサポートしていない
		 */
		/*
		 * Vector
		 * 	ArrayList と同様だが、同期性をサポートしておりスレッドセーフなコレクションである。
		 * 	作成するプログラムがマルチスレッド環境を必要としない場合は、
		 * 	Vectorではなく ArrayListを使用するべきであり、そうしないとパフォーマンスを損なうことになる
		 */
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		Integer i1 =1;
		int  i2 = 2;
		Integer i3 = i2;
		list.add(i1);
		list.add(i2);
		list.add(i3);
		list.add(1, 5);
//		list.add("String");//型 ArrayList<Integer> のメソッド add(Integer) は引数 (String) に適用できません
		System.out.println("size:" + list.size());//size:4
		for (int i = 0; i < list.size(); i++) {
			System.out.print(list.get(i) + "  ");//1  5  2  2  
		}
		System.out.println();
		for (Integer integer : list) {
			System.out.print(integer + "  ");//1  5  2  2  
		}
		System.out.println();
		for (int integer : list) {
			System.out.print(integer + "  ");//1  5  2  2  
		}
	}

}
