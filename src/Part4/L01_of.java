package Part4;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class L01_of {
	/**
	 * ファクトリメソッド
	 * 
	 * SE 9以降では、List、Set、Mapの各インタフェースにstatic メソッドとしてof()メソッドが提供されました。
	 * このメソッドは変更不可能なリスト、セット、マップを作成します。
	 * 特徴を以下にまとめます。
	 * ●変更不可能であるため、要素を追加、削除、置換することはできない。
	 * 　　それらを行うメソッドを使用すると、UnsupportedOperationException が発生する
	 * ●セットとリストは、null要素は使用できない。
	 * 　　null要素を入れるとNulPointerException が発生する
	 * ●マップは、キーと値ともにnullは使用できない。
	 * 　　nullを入れると NullPointelException が発生する
	 * ●セットの場合、重複する値は使用できない。
	 * 　　重複した値を入れるとIllegalArgumentException が発生する
	 * ●マップの場合、重複するキーは使用できない。
	 * 　　重複したキーを入れるとIllegalArgumentException が発生する
	 */

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		List<Integer> list1 = List.of(1, 2, 1);
		System.out.println("list1:" + list1);
//		list1.add(4);//実行時エラー：UnsupportedOperationException
//		List<Integer> list2 = List.of(1, 2, null);//実行時エラー：NulPointerException
		
		Set<Integer> set1 = Set.of(1,2,3);
//		Set<Integer> set2 = Set.of(1,2,null);//実行時エラー：NulPointerException
//		Set<Integer> set3 = Set.of(1,2,1);//実行時エラー：IllegalArgumentException  duplicate element: 1
		System.out.println("set1:" + set1);
		
		Map<Integer, String> map1 = Map.of(1,"a",2,"b",3,"c");
//		Map<Integer, String> map2 = Map.of(1,"a",2,"b",null,"c");//実行時エラー：NullPointelException
//		Map<Integer, String> map3 = Map.of(1,"a",2,"b",3,"c",1,"d");//実行時エラー：IllegalArgumentException  duplicate key: 1
		System.out.println("map1:" + map1);
		
	}

}
