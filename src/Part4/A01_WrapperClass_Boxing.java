package Part4;

import java.util.HashMap;
import java.util.Map;

public class A01_WrapperClass_Boxing {
	/**
	 * 基本データ型の値を参照型として扱う専用のクラスはラッパークラス
	 * Boolean
	 * Byte
	 * Character
	 * Short
	 * Integer
	 * Long
	 * Float
	 * Double
	 */
	

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		/*
		 * Boxing
		 * 基本データ型からラッパークラスへの自動変換とBoxingとよび
		 * ラッパークラスから基本データ型への自動変換をUnboxingとよび
		 */
		int i1 = 100;
		Integer ibjiInteger = i1; //Boxing
		int i2 = ibjiInteger; //Unboxing
		method(i2);//200//Boxing int型でメソッドの引数に渡す
		
		/*
		 * 不適切な使用例
		 */
		double d1 = 10;//① OK
		double dd2 = ibjiInteger;// OK
//		Double double1 = 10;//② NG 型の不一致: int から Double には変換できません
		Double double2 = 10.0;// OK
		
		int i = 10;//OK
		Integer integer = 10;//OK
//		Integer integer2 = double2;//NG 型の不一致: Double から Integer には変換できません
//		Integer integer = 10.0;//NG 型の不一致: double から Integer には変換できません
		
		long l = 100;//OK
//		Long long1 = 100;//NG 型の不一致: int から Long には変換できません
		Long long1 = 100l;//OK
//		Long long2 = 100.0;//NG 型の不一致: double から Long には変換できません
//		Long long2 = double2;//NG 型の不一致: Double から Long には変換できません
//		Double double1 = long1;//NG 型の不一致: Long から Double には変換できません
//		Long long2 = integer;//NG 型の不一致: Integer から Long には変換できませ
//		Integer integer2 = long1;//NG 型の不一致: Long から Integer には変換できません
		
		Long long2 = null;//③
		long l1 = long2;//NG NullPointerException発生
		
		Long long3 = null;
		long3++;//④　NG NullPointerException発生
		
		Map<String, Long>  map = new HashMap<String, Long>();
		long l2 = map.get("l2");//⑤NG NullPointerException発生	
		/*
		 * ①のように基本データ型同士ではルールに従った暗黙の型変換が行われますが、
		 * ②のようにBoxingにおいては暗黙の型変換は行われません。そのため、②はコンパイルエラーとなります。
		　* ③の例では、ラッパークラスで言したIVal変数にはnullが代入されています。
		基本データ型で言したL1変数にはIVal 変数を代入していますが、
		内部でLongクラスのlongValue（）メソッドが実行され、
		基本データ型に変換してから代入処理に入ります。
		したがって、nullに対するメソッド呼び出しが行われ、NullPointerException 例外が発生します。
		同様の理由から④、⑤いずれもNullPointerException 例外が発生します。
		 */
		
	}
	public static void method(Integer objInteger) {
		int i = objInteger + 100;//計算も可能//Unboxing
		System.out.println(i);
	}
}
