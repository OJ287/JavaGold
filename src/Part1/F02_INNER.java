package Part1;

public class F02_INNER {
	class A {
		void methodA() {
			System.out.println("F02_INNER A methodA");
		}
	}
	static class B {
		static void methodB1() {
			System.out.println("F02_INNER static B static methodB1");
		}
		void methodB2() {
			System.out.println("F02_INNER static B methodB2");
		}
	}

	private int num = 100;
	class C{
		public int num = 200;
		void method(int num) {
			num += 1;
			this.num += 1;
			F02_INNER.this.num += 1;
			System.out.println("num:" + num);
			System.out.println("this.num:" + this.num);
			System.out.println("F02_INNER.this.num:" + F02_INNER.this.num);
		}
	}
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		new F02_INNER().new A().methodA();
//		new A().methodA();// NG アクセス可能な型 F02_INNER のエンクロージング・インスタンスがありません。 型 F02_INNER のエンクロージング・インスタンスで割り振りを限定する必要があります (たとえば x.new A() で、x は F02_INNER のインスタンス)。
		
		new F02_INNER.B().methodB1();
		new B().methodB1();
		
		F02_INNER.B.methodB1();
		B.methodB1();

		new F02_INNER.B().methodB2();
		new B().methodB2();
		
//		F02_INNER.B.methodB2();//NG  methodB2はstaticではないので、インスタンス化してから呼び出しは必要
//		B.methodB2();//NG  methodB2はstaticではないので、インスタンス化してから呼び出しは必要
		
		/*
		 * ネストクラス内でthisといえば、ネストクラスのオブジェクト自身を表す
		 * ネストクラス内で外側のメンバをアクセスする場合は「外側クラス名.this.メンバ」となる
		 */
		new F02_INNER().new C().method(300);//num:301  this.num:201  F02_INNER.this.num:101
		
		
		/*
		 * ネストクラスではIFや抽象クラスの定義も可能
		 */
	}
	abstract class D{
		abstract void foo();
	}
	class E extends D{
		@Override
		void foo() {
			// TODO 自動生成されたメソッド・スタブ
			System.out.println("F02_INNER class E extends D");
		}
	}
	static interface F{
		void foo();
	}
	static class G implements F{
		@Override
		public void foo() {
			// TODO 自動生成されたメソッド・スタブ
			System.out.println("F02_INNER static class G implements F");
		}
		
	}
}

