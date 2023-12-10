package Part1;

public class E01_IF implements E01_IF01,E01_IF02{
	interface Foo extends E01_IF01,E01_IF02{
		static void method() {//暗黙的にpublicが付与される
			System.out.println("E01_IF.Foo static");
		}
		default void foo() {//暗黙的にpublicが付与される
			System.out.println("E01_IF.Foo default");
		}
		
	}
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		/**
		 * キーワード：interface
		 * インスタンス化できず、実装クラス側で抽象メソッドをオーバーライド
		 * 実装クラスを定義するにはimplementsを使用
		 * サブインスタンスの作成はextendsを使用
		 * インスタンスでは定数（public static final）を宣言できる（この定数のみ宣言できる、変数でも自動的にpublic static finalになる）
		 * 		ですので、初期化しておく必要がある
		 * SE７：IF内で宣言できるメソッドは抽象メソッド（自動的にpublic abstractを付与）のみ
		 * 	IF内で抽象メソッドは使用できるアクセス修飾子はpublicのみ(実装クラスで実装したメソッドの修飾子もpublicのみ)
		 * 	staticな抽象メソッドは宣言できない
		 * SE８：デフォルトメソッドとstaticメソッドを定義できる
		 * 	デフォルトメソッド：
		 *	 	アクセス修飾子は指定していない場合は強制的にpublicを付与
		 *		protectedは使用できない
		 * 	staticメソッド：
		 *	 	アクセス修飾子は指定していない場合は強制的にpublicを付与
		 *		protectedは使用できない
		 *		privateは使用可能
		 * SE９：privateメソッドを定義できる
		 */
		/*
		 * staticメソッドの利用
		 */
		Foo.method();
		
		/*
		 * デフォルトメソッド：処理を記述したメソッド
		 * 一つのIFに複数のデフォルトメソッドを定義できる
		 * でも、Objectのequals、hashCode,toStringのデフォルトメソッドとして定義できない
		 * static不可
		 */
		/*
		 * privateメソッド
		 * staticの使用可能
		 * 存在意義はデフォルトメソッドの内部構造を改竄するため
		 * 複数のデフォルトメソッドで実装が重複になる場合、privateメソッドで重複した処理を切り出す
		 */
		System.out.println(E01_IF01.staticMethod1());
		new E01_IF().defaultMethod();
//		System.out.println(E01_IF01.privateMethod2());
		
		/*
		 * IFの継承
		 * interface Foo extends E01_IF01,E01_IF02{
		 * public class E01_IF implements E01_IF01,E01_IF02{
		 * サブIFを実装した具象クラスにサブとスーパーIF全部の抽象メソッドをオーバーライドしなければんならない
		 * IFに対して複数のIFを継承extendsすることが可能、複数のIFを実装Implementsも可能
		 */
		
		/*
		 *実装クラスを具象クラスとする場合、implementsで指定したすべてのIFのメソッドをオーバーライドして実装する必要があり
		 *また、オーバーライドするメソッドにはpublic修飾子をつける必要があり
		 *public double method1() {
		 *public double method3() {
		 */
		MyClass myClass = new MyClass();
		System.out.println(myClass.method1());
		myClass.defaultMethod();
		System.out.println(myClass.method3());
		E01_IF02.method4();
//		myClass.method4();メソッド method4() は型 MyClass で未定義です.
//		具象クラスのstaticメソッドと異なり、参照変数を使用したstaticメソッド呼び出しはできない
		
		
	}
	@Override
	public int method3() {
		// TODO 自動生成されたメソッド・スタブ
		return 3;
	}
	@Override
	public double method1() {
		// TODO 自動生成されたメソッド・スタブ
		return 1;
	}

}
class MyClass implements E01_IF01,E01_IF02{

	@Override
	public int method3() {
		// TODO 自動生成されたメソッド・スタブ
		return 3;
	}
	@Override
	public double method1() {
		// TODO 自動生成されたメソッド・スタブ
		return 1;
	}
}

/*
 * 抽象メソッド、クラスのテスト
 * 可視性：public > protected > 空
 */
abstract class Test{
//	private abstract void foo();//型 Test の 抽象メソッド foo は public または protected の可視性修飾子のどちらか 1 つのみをセットできます
	abstract void foo();//OK
//	public abstract void foo();//OK
//	protected abstract void foo();//OK
}
class TestAbstract extends Test{
	@Override
//	private void foo() {// NG  Test から継承されたメソッドの可視性を下げることはできません
//	protected void foo() {//OK
//	public void foo() {//OK
	void foo() {//OK
		// TODO 自動生成されたメソッド・スタブ
		System.out.println("class TestAbstract extends Test");
	}
	
}