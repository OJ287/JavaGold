package Part4;

import java.util.ArrayList;
import java.util.List;

public class I02_MyGenerics {
	/**
	 * メソッド定義で使用
	 * ・型パラメータリストの有効範囲はそのメソッド内のみ
	 * ・使用位置：メソッド宣言修飾子と戻り値の間
	 * ・メソッドの引数や戻り値の型の指定で扱える
	 * ・ジェネリックスを使用して定義したメソッドに対して、メソッドの呼び出し側で型名を<>で使用する必要がない
	 */

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Gen2 gen2 = new Gen2();
		String string1 = gen2.methodA("gen2.methodA");
		String string2 = gen2.<String>methodA("gen2.<String>methodA");
		List<String> list = new ArrayList<>();
		list.add(string1);
		list.add(string2);
		Gen2.methodB(list);
		Gen2.<String>methodB(list);
		
	}

}

class Gen2{
	public <T> T methodA(T value) {
		return value;
	}
	public static <T> void methodB(List<T> list) {
		for (T t : list) {
			System.out.print(t + "    ");
		}
		System.out.println();
	}
	
}