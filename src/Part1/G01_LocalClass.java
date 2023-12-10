package Part1;

public class G01_LocalClass {
	/**
	 * ローからクラスとは、あるクラスのメソッドないに定義したクラスである
	 * メソッド内でのみ有効、クラスメンバで使用していたアクセス修飾子やstaticは使用できない
	 * ローカラクラスはstaticクラスとして作成出来ない、非staticクラス（インナークラス）のみ作成できる
	 */

	private static int a = 1;
	private int b = 2;
	void methodOuter(final int c, int d) {
		final int e =5;
		int f = 6;
		class A{
			void method() {
				System.out.println(a);
				System.out.println(b);
				System.out.println(c);
				System.out.println(d);
				System.out.println(e);
				System.out.println(f);
				/*
				 * cdefメソッド内のローカラ変数にアクセスしていますが、final定数であることが条件
				 * しかし、dとfは明示的なfinal指定をしていないが、SE8以降ローから変数には暗黙的にfinal修飾子が付与されるため
				 * 再代入はダメ
				 */
//				d = 100;Local variable d defined in an enclosing scope must be final or effectively final
//				f = 200;Local variable f defined in an enclosing scope must be final or effectively final
			}
		}
		new A().method();
	}
	
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		/*
		 * ローカルクラスのルール
		 * 		＠アクセス修飾子（public,protected,private）は使用できない
		 * 		＠static修飾子を使用できない
		 * 		＠abstract修飾子、final修飾子を使用できる
		 * 		＠外側クラスのメンバにアクセスできる
		 * 		＠ローカラクラスから外側クラスのメソッドの引数おおびローカラ変数にアクセスするには、各変数がfinal（定数）ではなければならない
		 * 		　したがって、SE７まで明示的なfinal修飾子の付与が必要であったが、SE８以降では暗黙的にfinalが付与されルため、明示的な付与は不要
		 */
		new G01_LocalClass().methodOuter(3, 4);// 123456

	}

}
