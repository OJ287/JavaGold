package Part1;

public class YYY_Practice01 {
	/**
	 * 答え：
	 * 	1:ADE		
	 *  2:A
	 *  3:D
	 *  4×:B →　C
	 *  	列挙型に抽象メソッドを宣言することが可能ですが、各列挙値となる定数内で抽象メソッドのオーバーライドは必要
	 *  5:D
	 *  6:BE
	 *  7:E
	 *  8:ABDE
	 *  9:E
	 *  10:E
	 *  11:C
	 *  12:E
	 *  13:E
	 *  14:E
	 *  15×:ACE　→　ABE
	 *  	オーバーライドルール：非staticメソッドをstaticメソッドでオーバーライドはできない
	 *  	throwsについて、スーパークラスのメソッドがthrowsを使用しない場合、オーバーライドも使用しない、またはRuntimeExceptionをスロー
	 */

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

	}

	/*
	 * 4
	 */
//	enum Vals{
//		X,//列挙定数 X は抽象メソッド methodEnum() を実装する必要があります
//		Y{int methodEnum(){return 10;}},
//		Z;//列挙定数 X は抽象メソッド methodEnum() を実装する必要があります
//		abstract int methodEnum();
//	}
	
	/*
	 * 15
	 */
	void bar() {}
	class Foo extends YYY_Practice01{
//		public void bar() {}//OK
//		final void bar() {}//OK
//		static void bar() {}//NG  この static メソッドは YYY_Practice01 からのインスタンス・メソッドを隠蔽できません
//		void bar() throws Exception{} //NG  型 YYY_Practice01.Foo にメソッド bar() が重複しています
//		void bar(int a) {}//OK
		
	}
}
