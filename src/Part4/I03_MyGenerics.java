package Part4;
interface MyIn<T>{
	T method(T t);
}
class Foo1 implements MyIn<String>{

	@Override
	public String method(String t) {
		// TODO 自動生成されたメソッド・スタブ
		return t;
	}
	
}
class Bar implements MyIn<Integer>{

	@Override
	public Integer method(Integer t) {
		// TODO 自動生成されたメソッド・スタブ
		return t;
	}
	
}
public class I03_MyGenerics {
	/**
	 * インタフェース定義で使用
	 * インタフェースはインスタンス化できないため、インスタンスを実装するクラス側で型パタメータに対して使用する型を指定する
	 * 
	 */

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		System.out.println(new Foo1().method("new Foo().method()"));
		System.out.println(
		new MyIn<String>() {//匿名クラス使用例
			public String method(String string) {
				return string;
			}
		}.method("new MyIn<String>()"));
	}

}
