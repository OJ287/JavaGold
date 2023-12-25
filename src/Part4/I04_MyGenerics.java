package Part4;

public class I04_MyGenerics {
	/**
	 * 継承を使用したジェネリック
	 * 型パラメータリストをくT>とした場合、利用時にどのような型でも指定することが可能でした。
	 * しかし、ジェネリックスを用いた独自クラスで、あるクラスもしくはそのサブクラスのみ扱えるように限定したい場合は、
	 * 次のように型パラメータリストを指定します。
	 * ＜型パタメータ　extends データ型＞
	 */

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		/*
		 * 継承を使用したジェネリックス
		 */
		Gen3<Integer> gen1 = new Gen3<>(100);
//		Gen3<Integer> gen1 = new Gen3<>(100.01);//NG  Cannot infer type arguments for Gen3<>
		gen1.display();
		Gen3<Double> gen2 = new Gen3<>(100.01);
//		Gen3<Double> gen2 = new Gen3<>(100);//NG Cannot infer type arguments for Gen3<>
		gen2.display();
//		Gen3<String> gen3 = new Gen3<>("abc");//NG 制約の不一致: 型 String は、型 Gen3<T> の制約付きパラメーター <T extends Number> の代替として有効ではありません
		
	}

}
/*
 * Number
 *    |___Double
 *    |___Integer
 *    |___Short
 */
class Gen3<T extends Number>{
	private T var;
	public Gen3(T var) {
		this.var = var;
	}
	public void display() {
		System.out.println(var);
	}
}
