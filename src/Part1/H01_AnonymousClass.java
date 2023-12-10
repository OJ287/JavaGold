package Part1;
interface MyInter{
	void methodA();
}
class Anonymous{
	void method() {
		//new MyInterはインタフェース自体がインスタンス化されるのではなく、無名の実装クラスがインスタンス化となる
		new MyInter() {
			@Override
			public void methodA() {
				// TODO 自動生成されたメソッド・スタブ
				System.out.println("IFを実装した匿名クラス、定義とインスタンス化と後ろの.methodA()を一つの式として");
			}
		}.methodA();
	}
}

public class H01_AnonymousClass {
	/**
	 * 匿名クラスとはクラス名を指定せずに、クラス定義とインスタンス化を１つの式として記述したクラス
	 * 匿名クラスはあるクラスのサブクラスまたは、あるインタフェースを実装したクラスになる
	 * 「new スーパークラス」、または「new インタフェース」の後にオーバーライドする処理をブロックとして記述
	 * また匿名クラスは一つの指揮として定義するため、最後にセミコロンは必要
	 * 
	 * 
	 * 使用場面：
	 * 	再利用することはなく、特定の場所のみで実装したい場合に使用する
	 * 　したがって、クラス名をつけずにクラス定義とインスタンス化を同時に行う
	 */
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		/*
		 * 匿名クラスのルール
		 * 		＠アクセス修飾子（public,protected,private）は使用できない
		 * 		＠static修飾子を使用できない
		 * 		＠abstract修飾子、final修飾子を使用できない
		 * 		＠外側クラスのメンバにアクセスできる
		 * 		＠外側クラスのメソッドの引数及びローカル変数にアクセスできる、ただし暗黙的にfinal
		 * 		＠コンストラクタを定義できない
		 */
		new Anonymous().method();//IFを実装した匿名クラス、定義とインスタンス化と後ろの.methodA()を一つの式として
	}

}
