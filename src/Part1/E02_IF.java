package Part1;
abstract class Super{
	abstract void method1() ;
}
public class E02_IF extends Super implements E02_IF01,E01_IF02 {

	/*
	 * 継承するSuperに抽象メソッドがありので、E02_IFで実装しなければならない
	 */
	@Override
	void method1() {
		// TODO 自動生成されたメソッド・スタブ
		
	}
	/*
	 *extendsとimplementsと同時に使用することが可能ですが、extendsを先に書く
	 * public class E02_IF extends E01_IF implements E02_IF01,E02_IF02 {
	 */
	
	/*
	 * E02_IF02のサブE02_IF03はmethod３をデフォルトメソッドとしてオーバーライド
	 * MyClass2はE02_IF02の実装クラスとしてmethod３を具象メソッドとしてオーバーライドする
	 * E02_IF03を実装して、MyClass2を継承したMyClass3クラスはコンパイルエラーにならない
	 * 原因：JAVAでは常にクラスが優先されるため、E02_IF03の実装クラスであるMyClass2クラスが優先となる
	 */
	public static void main(String[] args) {
		MyClass3 myClass3 = new MyClass3();
		myClass3.method3();//出力：MyClass2 method3　　E02_IF03 method3ではない。インタフェースよりクラスが優先となる

		MyClass33 myClass33 = new MyClass33();
		myClass33.method3();// MyClass2 method3  E02_IF02に抽象メソッドがあるが、実現クラスMyClass2があれば、エラーとならない

		MyClass4 myClass4 = new MyClass4();
		myClass4.method3();// E02_IF03 method3
	}

	@Override
	public int method3() {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}
	/*
	 * Duplicate default methods named method3 with the parameters () and () are inherited from the types E02_IF04 and E02_IF03
	 * 二つのクイックフィクスが使用可能、method３を呼び出しを行われた際に、どちら呼び出しか判断がつかなくなるから
	 */
//	class MyClass implements E02_IF03,E02_IF04{
//		
//	}
	
	/*
	 * デフォルトメソッドは実装クラスでオーバーライドすることは可能
	 * オーバーライドするメソッド（interfaceで定義するメソッド）が、publicをつける必要がある
	 */
	@Override
	public void method2() {
		// TODO 自動生成されたメソッド・スタブ
//		E02_IF01.super.method2();
		System.out.println("E02_IF method2");
	}
	
	/*
	 * 上記の続きまして、
	 * MyClass1でオーバーライドできますが、もしくはE02_IF03,E02_IF04のmethod３を呼び出したい場合は
	 * 「親インタフェース名.super.メソッド名」で呼び出しできる
	 */
//	class MyClass1 implements E02_IF03,E02_IF04{
//		@Override
//		public void method3() {
//			// TODO 自動生成されたメソッド・スタブ
//			System.out.println("MyClass method3のオーバーライド");
//			E02_IF03.super.method3();
//			E02_IF04.super.method3();
//		}
//	}
//	public static void main(String[] args) {
//		MyClass1 myClass1 = new MyClass1();
//		myClass1.method3();//MyClass method3のオーバーライド   E02_IF03 method3   E02_IF04 method3
//	}
	
	/*
	 * 上記でコンパイルエラーになるけど、以下は大丈夫です
	 * MyClassでオーバーライドすることでコンパイル、実行可能。method３が特定できましたから
	 */
	class MyClass implements E02_IF03,E02_IF04{
		/**
		 * 如果 E02_IF02 只声明抽象方法而 E02_IF03 提供 default 实现：
		 * 这种情况下是合法的，通常不会报错。
		 * <p>
		 * 如果 E02_IF03 和 E02_IF04 都提供 default 实现：
		 * 则必须在类 MyClass 中显式覆盖 default() 以解决冲突
		 * 看最后的MyClass4和MyClass5
		 */
		@Override
		public void method3() {
			// TODO 自動生成されたメソッド・スタブ
//			E02_IF03.super.method3();
			System.out.println("MyClass method3のオーバーライド");
		}
	}
	
}
//「親インタフェース名.super.メソッド名」で呼び出しできる
class MyClass1 implements E02_IF03,E02_IF04{
	@Override
	public void method3() {
		// TODO 自動生成されたメソッド・スタブ
		System.out.println("MyClass method3のオーバーライド");
		E02_IF03.super.method3();
		E02_IF04.super.method3();
	}

}

//「MyClass2はE02_IF02の実装クラスとしてmethod３を具象メソッドとしてオーバーライドする
class MyClass2 implements E02_IF02{

	@Override
	public void  method3() {
		// TODO 自動生成されたメソッド・スタブ
		System.out.println("MyClass2 method3");
	}
	
}
class MyClass3 extends MyClass2 implements E02_IF03 {

}

class MyClass33 extends MyClass2 implements E02_IF02 {

}

// 没问题
class MyClass4 implements E02_IF02, E02_IF03 {

}

// Part1.MyClass5 从类型 Part1.E02_IF03 和 Part1.E02_IF04 继承 method3() 的不相关默认值
// コンパイルエラー
//class MyClass5 implements E02_IF03,E02_IF04{
//
//}

