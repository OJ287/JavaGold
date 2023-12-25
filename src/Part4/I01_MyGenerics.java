package Part4;

public class I01_MyGenerics {
	/**
	 * ジェネリックスを用いた独自クラスの定義
	 * 利用場面は、大きく分けて三つのパターンがあり
	 * ・クラス定義及びインタフェース定義で使用
	 * ・メソッド定義で使用
	 * ・コンストクラス定義で使用
	 * 
	 * 型パラメータリストに当たるところで、扱いたい型を指定する
	 * 型パタメータで扱えるデータ型は参照型のみです、基本データ型は指定できない
	 * なお、クラスの型パラメータは、フィールドの宣言、メソッドの引数や戻り値、ローカル変数宣言などで使用可能
	 * staticメンバには使用できない
	 */

	public static void main(String[] args) {
		/*
		 * クラス定義で使用
		 */
		
		// TODO 自動生成されたメソッド・スタブ
		Gen<String> gen1 = new Gen<>("String gen1");
//		Gen<String> gen1 = new Gen("String gen1");//警告：型の安全性: 型 Gen の式は、未検査の型変換を使用して Gen<String> に準拠するようにする必要があります
		System.out.println(gen1.getVar1());
		gen1.setVar1("String gen1 setVar1");
		System.out.println(gen1.getVar1());

		Gen<Integer> gen2 = new Gen(0);//警告：Gen は raw 型です。 総称型 Gen<T> への参照は、パラメーター化する必要があります
//		Gen<Integer> gen2 = new Gen("000");//警告：型の安全性: 型 Gen の式は、未検査の型変換を使用して Gen<Integer> に準拠するようにする必要があります
//		Gen<Integer> gen2 = new Gen<>("000");// NG   Cannot infer type arguments for Gen<>
		System.out.println(gen2.getVar1()); //Gen<Integer> gen2 =  new Gen("000") :  000 ;;;; Gen<Integer> gen2 = new Gen(0):0
		gen2.setVar1(111);
//		gen2.setVar1("111");//型 Gen<Integer> のメソッド setVar1(Integer) は引数 (String) に適用できません
		System.out.println(gen2.getVar1());
		
		
	}

}
class Gen<T>{
	private T var1;
//	private static T var2;//非 static 型 T を static 参照できません
	public Gen(T var1) {
		this.var1 = var1;
	}
	public T getVar1() {
		return var1;
	}
	public void setVar1(T var1) {
		this.var1 = var1;
	}
}