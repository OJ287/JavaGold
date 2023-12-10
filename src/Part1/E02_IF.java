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
	 * デフォルトメソッドは実装クラスでオーバーライドすることは可能
	 */
	@Override
	public void method2() {
		// TODO 自動生成されたメソッド・スタブ
//		E02_IF01.super.method2();
		System.out.println("E02_IF method2");
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
	 * 上記でコンパイルエラーになるけど、以下は大丈夫です
	 * MyClassでオーバーライドすることでコンパイル、実行可能。method３が特定できましたから
	 */
	class MyClass implements E02_IF03,E02_IF04{
		@Override
		public void method3() {
			// TODO 自動生成されたメソッド・スタブ
//			E02_IF03.super.method3();
			System.out.println("MyClass method3のオーバーライド");
		}
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
	 * E02_IF02のサブE02_IF03はmethod３をデフォルトメソッドとしてオーバーライド
	 * MyClass2はE02_IF02の実装クラスとしてmethod３を具象メソッドとしてオーバーライドする
	 * E02_IF03を実装して、MyClass2を継承したMyClass3クラスはコンパイルエラーにならない
	 * 原因：JAVAでは常にクラスが優先されるため、E02_IF03の実装クラスであるMyClass2クラスが優先となる
	 */
	public static void main(String[] args) {
		MyClass3 myClass3 = new MyClass3();
		myClass3.method3();//出力：MyClass2 method3　　E02_IF03 method3ではない。インタフェースよりクラスが優先となる
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
class MyClass3 extends MyClass2 implements E02_IF03{
	
}
