package Part2;

public @interface B01_MyAnnotaion01 {
	/*
	 * アノテーション内でvalueを持つことは可能
	 * メンバはインタフェースでの抽象メソッドの定義と同じ、暗黙的にpublic abstractが付与される
	 * メンバ（メソッド）名は任意ですが、
	 * 戻り値は不変タイプであり、基本データ型、String型、Class型、列挙型、アノテーション型、左記の型の一次元配列	{1, 2, 3}，{"a", "b"}
	 * のいずれかである必要があり、
	 * 不可以的类型：
	 *     オブジェクト型
	 *     任意的自定义类（非 Class、非枚举）
	 *     集合（如 List、Map）
	 *     null（注解元素不能为 null）
	 */
	String value();
	
	/*
	 * 複数のメンバを使用する
	 */
    enum RANK{A,B,C}
//	RANK rank();
	RANK rank() default RANK.B;//デフォルト値を設定することが可能、利用する側にrankの指定は任意になる
	
	int num();
	
	/*
	 * 戻り値は不変タイプである必要がある
	 * 要素値は定数式である必要があります、インスタンス化式は使用できない
	 */
//	String str1() default new String("");//注釈属性 B01_MyAnnotaion01.str1 の値は、定数式である必要があります
	String str2() default "";
//	String str3() default null;//注釈属性 B01_MyAnnotaion01.str3 の値は、定数式である必要があります
}
