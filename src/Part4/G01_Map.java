package Part4;
import java.util.HashMap;
import java.util.NavigableMap;
import java.util.TreeMap;

public class G01_Map {
	/**
	 * データをキーと値のペアで管理する
	 * キー：一意
	 * 値：重複可能
	 */

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		/*
		 * Mapインタフェースを実装している主なクラス
		 * ・HashMap
		 * 		ハッシュテーブルのデータ構造体をベースとした実装である。マップ内に各キーと値のペアは順不同で格納される
		 * 		またnullをキーあるいは値として使用できる。同期性はサポートしていない
         *     key允许有一个null，value允许有多个null
		 * ・LinkedHashMap
		 * 		全てのエントリに対する二重リンクリストを保持するという点でHashMapと異なっている
		 * 		また、通常はマップにキーに挿入された順番を元にした、繰り返し順序も規定される。
		 * 		データ項目順序づけが維持される必要がある場合、このリストが利用される
		 * 		同期性はサポートしていない
         *     key允许有一个null，value允许有多个null
         * ・TreeMap    实装了sortmap接口也实装了navigablemap接口
		 * 		SortedMapインタフェースのサブインタフェースNavigableMapを実装したクラスである
		 * 		この場合のマップは、キーの昇順による順序づけ維持される（ソートされる）
		 * 		同期性はサポートしていない
         *     key不允许有null，value允许有多个null
		 * 		NavigableMap：
		 * 			指定されたキーに対し、もっと近い要素を返す
		 * 			という意味は、指定したキーが存在していない場合、指定したキーに近いキー（と値）を検索できる
		 */
		
		HashMap<Integer, String> hashMap1 = new HashMap<Integer, String>();
		hashMap1.put(0, "AAA");
		hashMap1.put(1, "BBB");
		hashMap1.put(2, "AAA");
		hashMap1.put(1, "CCC");//キー重複は不可、キーはすでに存在している場合、値を上書き
		System.out.println(hashMap1.containsKey(2));//true
		System.out.println(hashMap1.containsValue("XXX"));//false
		for (int i = 0; i < hashMap1.size(); i++) {
			System.out.print(hashMap1.get(i) + "   ");//AAA   CCC   AAA   
		}
		System.out.println();
		
		NavigableMap<String, String> navigableMap1 = new TreeMap<String, String>();
		navigableMap1.put("1111", "ItemA");
		navigableMap1.put("2222", "ItemB");
		navigableMap1.put("3333", "ItemC");
		navigableMap1.put("4444", "ItemD");
		String keyString = "2000";
		if(navigableMap1.containsKey(keyString)) {
			System.out.println("get(2000)" + navigableMap1.get(keyString));
		}else {
            System.out.println(navigableMap1.higherKey(keyString));//2222   找不到就返回null
            System.out.println(navigableMap1.lowerKey(keyString));//1111   找不到就返回null
		}
		
		NavigableMap<String, String> navigableMap2 = navigableMap1.subMap("2000", false, "3500", false);
		//fromKey low endpoint (inclusive) of the keys in the returned map
		//fromInclusive true if the low endpoint is to be included in the returned view  
		//toKey high endpoint (exclusive) of the keys in the returned map
		//toInclusive true if the high endpoint is to be included in the returned view
		System.out.println("2000 - 3500" + navigableMap2);//2000 - 3500{2222=ItemB, 3333=ItemC}
		
		
		
	}

}
