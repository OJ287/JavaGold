package Part1;

public class F01_INNER {
	/**
	 * https://qiita.com/tamatan/items/c6f079e557f00be1fd6c
	 * https://workteria.forward-soft.co.jp/blog/detail/10160
	 * JAVA言語ではクラス定義の中にさらにクラスを定義できる
	 * 使用場所が限定され、その存在を外部から隠したいクラスに適用するにが一般的です
	 * ネストクラスは外側クラスのメンバの一つですので、ネクスクラスもstaticクラスと非staticクラスがある
	 * 		特に非staticaクラスのことをインナークラスとよび
	 * 			＠ローカラクラス
	 * 			＠匿名クラス
	 * 		staticクラス
	 * 
	 */

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

		/*
		 * ネストクラスのルール
		 * ネストクラスのメソッドから外側のクラスのメンバにアクセスできる
		 * staticクラスと非staticクラスの共通：
		 * 		＠外側のクラスと同じ名前（クラス名）を使用できない
		 * 		＠アクセス修飾子（public,protected,private）を使用できる
		 * 		＠abstract修飾子、final修飾子を使用できる
		 * staticクラスのみ：
		 * 		＠非staticメンバ、staticメンバを持つことができる
		 * 		＠外側クラスで定義したインスタンス変数にアクセスできない
		 * 非staticクラウ：
		 * 		＠staticメンバを持つことができない
		 * 		＠外側クラスで定義したインスタンス変数、static変数にアクセスできる
		 */
		
		/*
		 * ネストクラスは単体で使用するものではない、あくまで外側クラスのメンバとして使用する
		 * 外側クラスをコンパイルすると、ネストクラスもコンパイルする
		 * 生成されたクラス名は「外側クラス名$ネストクラス名.class」
		 * OutClass.class   OutClass$A.class   OutClass$B.class
		 */
		
		
		/*
		 * ネストクラスへのアクセス
		 * ネストクラスのメンバは、ネストクラスをインスタンスした後利用できる
		 * ネストクラスのインスタンス方法：
		 * 		①外部クラスでネストクラスをインスタンスする
		 * 		②外側クラスのメソッド内でネストクラスをインスタンス化する
		 * ①：staticクラスと非staticクラスによって異なる
		 * 	ネストクラスのオブジェクトを参照する変数の型宣言は「外側クラス名.ネストクラス名」になる
		 * 	非staticの場合：
		 * 		外側クラス名.ネストクラス名　変数　＝　new 外側クラス名（）.new 非staticクラス名（）；
		 * 		OutClass.A a = new OutClass().new A();
		 * staticクラスの場合：
		 * 		外側クラス名.staticネストクラス名　変数　＝　new 外側クラス名.非staticクラス名（）；
		 * 		OutClass.B a = new OutClass.B();
		 * ②：外側クラスのmainメソッドでネストクラスをインスタンス化にする（例:F02_INNER）
		 * 
		 */
		Outer.A a = new Outer().new A();
		Outer.B b = new Outer.B();
		a.method1();
        Outer.B.method2();
		
	}

}
class Outer {
	private final int val1=100;
	private static final int val2 =200;

	//INNER Clsss
	class A{
		void method1() {
			System.out.println("Outer A method1 instance val1" + val1);
			System.out.println("Outer A method1 static val2" + val2);
		}
//		static void method2() {//インスタンスクラス内でstaticメソッドを定義できない
////			System.out.println("Outer A method1 instance val1" + val1);//staticメソッド内で外側クラスのインスタンス変数へアクセスできない
//			System.out.println("Outer A method1 static val2" + val2);
//		}
	}
	
	// static Class
	static class B{
		void method1() {
//			System.out.println("Outer B method1 instance val1" + val1);//staticクラス内で外側クラスのインスタンス変数へアクセスできない
			System.out.println("Outer B method1 static val2" + val2);
		}
		static void method2() {//インスタンスクラス内でstaticメソッドを定義できない
//			System.out.println("Outer B method1 instance val1" + val1);//staticクラス内t（or staticメソッド）で外側クラスのインスタンス変数へアクセスできない
			System.out.println("Outer B method1 static val2" + val2);
		}
	}
}