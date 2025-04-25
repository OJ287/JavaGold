package Part4;

public class B02_Collection {
	/**
	 * コレクション
	 * 複数のオブジェクトをまとめて取り扱うための統一
	 * 一貫性のある管理や操作を行える、取り扱うオブジェクトを柔軟に追加・削除も可能
	 * コレクションに格納するオブジェクトを要素あるいはエレメントをよび
	 */

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		/*
		 * コレクション種類
		 * Collection(インタフェース)
		 *     |____List
		 *     |____Set
		 *           |____SortedSet
		 *     |____Queue
		 * Map(インタフェース)
		 *  |____SortedMap
		 *  
		 *  List:
		 *  	・データ項目に順序づけをしたコレクション、順序づけ要素を管理する
		 *  	・リスト中の要素は重複しても良い
		 *  	・Listインタフェースを実装したクラス：ArrayList,LinkedList,Vector
		 *  Set：
		 *  	・ユニークな値のコレクション、重複した要素は管理できない
		 *  	・順不同で要素を管理する
		 *  	・Setインタフェースを実装したクラス：HashSet,TreeSet,LinkedHashSet
		 *  Queue：
		 *  	・FIFO（first-in-first-out）形式のデータ入出力を行うコレクション
		 *  	・Queueインタフェースを実装したクラス：ArrayDeque
		 *  Map：
		 *  	・ユニークのキーに対応する値をマップしたオブジェクトで、一つのキーには値が割り当てられる
		 *  	・キーの重複はできない、値の重複はできる
		 *  	・MAPインタフェースを実装したクラス：HashMap,TreeMap,LinkedMap
		 */
		/*
		 * Collectionの主なメソッド
		 * ①：boolean add(E e)
		 * 		引数の要素をコレクションに追加する。この呼び出しの結果、コレクションが変更された場合true
		 * ②：void clear()
		 * 		このコレクションから全ての要素を削除する
		 * ③：boolean contains(Object obj)
		 * 		指定された要素objがこのコレクションに存在する場合はtrue
		 * ④：boolean containsAll(Collection<?> c)
		 * 		指定されたコレクションの要素が全てこのコレクションに含まれる場合はtrue
		 * ⑤：Boolean remove(Object obj)
		 * 		引数に指定された要素を削除し、要素が削除された場合はtrue
		 * ⑥：boolean isEmpty()
		 * 		このコレクションに要素は一つでも含まれていない場合はtrue
		 * ⑦：boolean removeAll(Collection<?> c)
		 * 		引数に指定されたコレクションにある全ての要素をこのコレクションから削除し、このメソッドの呼び出しの結果、このコレクションの内容に変化があった場合はtrue
		 * ⑧：Iterator<E> iterator()
		 * 		このコレクションの要素に対する反復子を返し
		 * ⑨：Object[] toArray()
		 * 		このコレクションの要素が全て格納している配列を返し
		 * ⑩：<T> T[] toArray(T[] array)
		 * 		このコレクションの全ての要素を含む配列を返し、Tは配列要素のデータ型を示す
		 * 11：int size()
		 * 		コレクション中の要素数を返し
		 */
		/*
		 * Mapインタフェースの主なメソッド
		 * ①：void clear()
		 * 		マップ中の全てのマッピングを削除する
		 * ②：boolean containsKey(Object key)
		 * 		指定されたキーに対応するキー値ペアがこのアッピングに存在する場合はtrue
		 * ③：boolean containsValue(Object value)
		 * 		指定された値に対応するキー値ペアがこのアッピングに存在する場合はtrue
		 * ④：V get(Object key)
		 * 		指定されたキーに対応する値を返し
		 * ⑤：boolean isEmpty()
		 * 		このマップ中にマッピングを保持していない場合はtrue
		 * ⑥：V put(K key , V Value)
		 * 		指定されたキー値ペアを格納する。指定されたキーに対してすでに値が割り当てれらた場合は新しい値で上書き
		 * ⑦：void putAll(Map<? extends V> m)
		 * 		指定されたマップにある全てのキー値ペアをこのマップに格納する
		 * ⑧：V remove(Object key)
		 * 		指定されたキーに対応するキー値ペアが存在する場合、それを削除する。戻り値は指定キーに割り当てられた値（削除処理前）を返し、
		 * 		値が存在していない場合は（あるいはNULL値の場合）はnullを返し
		 * ⑨：int size()
		 * 		このマップに存在するキー値ペアの数を返し
		 * ⑩：collection<V> values()
		 * 		このマップ中の値をコレクションとして返し
		 */
		
		/*
		 * List,Set,Queue,Mapの利用
		 * ・各種の実装とその機能
				Collection インタフェースは、コレクションフレームワークの基本機能の取り決めを行っているだけで、
				Collection インタフェースを継承したList、Set，Queueの各インタフェースには、要件に応じた独自の機能が追加されています。
				したがって、アプリケーション内でどのインタフェースを使用するかは、次にあげる要件が必要か否かによって判断します。
			・パフォーマンス
				この場合のパフォーマンスとは、要素の検索、挿入、削除などの操作が、データ構造体中の要素数に対して、
				どの程度の処理時間を要するかを指します。あるデータ構造体は検索は高速だが、挿入や削除が低速となる場合があります。
			・順序づけ／ソート
				データ構造体は、その要素が何らかの順番で並べられている場合、順序づけられているといいます。
				たとえば、配列の要素はインデックス順に並んでいるので、これらは順序づけられたデータ構造体です。
				また、「5番目の要素」などのように、インデックスを用いて特定の要素を参照することができます。
				データ構造体は、その要素が自然な順番で並べられている場合（たとえばデータ値の昇順など）、ソートされているといいます。
				この定義から、ソートされているデータ構造体は、順序づけられたデータ構造体です。
			・項目のユニーク性
				データ構造体中の要素に対する要件としては、ユニーク（一意）に識別する必要性から重複を許したくないという場合もあれば、
				あるいは逆に重複を許したいという場合も考えられます。
			・同期性
				実装によっては同期性を備えている場合があり、そうしたものはスレッドセーフをサポートしている
				（マルチスレッド環境で実行可能である）といいます。一方で、同期性を備えていない実装も存在します（同期性については第8章で解説）
		 */

		/**
		 * 如何实现 Map 的“removeAll”效果？
		 * 方式一：移除某些 key（最常见）
		 * Map<String, String> map = new HashMap<>();
		 * map.put("A", "Apple");
		 * map.put("B", "Banana");
		 * map.put("C", "Cherry");
		 *
		 * Set<String> keysToRemove = Set.of("A", "C");
		 *
		 * // 移除这些 key
		 * map.keySet().removeAll(keysToRemove);
		 *
		 * System.out.println(map); // 输出：{B=Banana}
		 *
		 *  方式二：移除 value 在某个列表里的键值对
		 *  Collection<String> valuesToRemove = List.of("Banana", "Cherry");
		 *
		 * // 用 Iterator 安全删除
		 * Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
		 * while (it.hasNext()) {
		 *     if (valuesToRemove.contains(it.next().getValue())) {
		 *         it.remove();
		 *     }
		 * }
		 *
		 * 方式三（Java 8+）：使用 removeIf 简化（推荐）
		 * map.entrySet().removeIf(entry -> valuesToRemove.contains(entry.getValue()));
		 *
		 *
		 *
		 *
		 * keySet() 方法
		 * Set<K> keySet()
		 * 返回这个 Map 所有“键”（key）组成的 Set 视图。
		 * 特点：
		 * 你操作 keySet() 返回的集合，就会影响原始 Map
		 * 常用于：遍历所有键、批量删除 key 等。
		 *
		 * Map<String, String> map = new HashMap<>();
		 * map.put("A", "Apple");
		 * map.put("B", "Banana");
		 *
		 * Set<String> keys = map.keySet(); // keys = ["A", "B"]
		 * System.out.println(keys);
		 *
		 * keys.remove("A"); // 也会从原始 map 中移除"A"
		 * System.out.println(map); // 输出：{B=Banana}
		 *
		 *
		 *
		 * entrySet() 方法
		 * Set<Map.Entry<K, V>> entrySet()
		 * 返回这个 Map 所有“键值对”（Entry）组成的 Set 视图。
		 * 特点：
		 * 每个 Map.Entry<K, V> 对象表示一个 key 和 value。
		 * 你可以通过它同时访问键和值，或者根据值删除 entry。
		 * 常用于：遍历整个 Map 的内容、用条件批量移除 entry。
		 * Map<String, String> map = new HashMap<>();
		 * map.put("A", "Apple");
		 * map.put("B", "Banana");
		 *
		 * for (Map.Entry<String, String> entry : map.entrySet()) {
		 *     System.out.println("key=" + entry.getKey() + ", value=" + entry.getValue());
		 * }
		 *
		 * // 删除 value 是 "Banana" 的项
		 * map.entrySet().removeIf(entry -> "Banana".equals(entry.getValue()));
		 * System.out.println(map); // 输出：{A=Apple}
		 */
	}

}













