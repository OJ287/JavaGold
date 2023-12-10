package Part1;
class Foo_D01{}
public class D01_ObjectClass {
	@Override
	public String toString() {
		// TODO 自動生成されたメソッド・スタブ
		return "@Override";
	}
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		/**
		 * クラスは「すべてOBJECTクラスのサブクラスとなる
		 * 配列も参照型オブジェクトである
		 */
		
		/*
		 * toString()
		 * 通常の戻り値：クラス名＠符号なしのハッシュコード、１６進数
		 * 常にオーバーライドとして利用する
		 */
		int[] ary = {1,2};
		String string = "string";
		Foo_D01 foo = new Foo_D01();
		D01_ObjectClass d01_ObjectClass = new D01_ObjectClass();
		System.out.println(ary);//[I@5eb5c224
		System.out.println(string);//string  StringオブジェクトはすでにtoString()をオーバーライドした
		System.out.println(foo);//Part1.Foo_D01@3f8f9dd6
		System.out.println(d01_ObjectClass);//@Override
		
		/*
		 * equals()
		 * これは==演算子と同じ振る舞いです（同じ参照かどうかの比較）
		 */
		Foo_D01 f1 = new Foo_D01();
		Foo_D01 f2 = new Foo_D01();
		Foo_D01 f3 = new Foo_D01();
		Foo_D01 f4 = f3;
		System.out.println(f1.equals(f2));//false
		System.out.println(f3.equals(f4));//true
		System.out.println(f3.equals(null));//false
		
		/*
		 * hashCode()
		 * ハッシュコードを返す。JAVA実行環境がオブジェクトの識別を行うために使用する
		 * 異なるオブジェクトから取り出したハッシュコードは異なる値となり、同じオブジェクトでは同じ値になる
		 */
		System.out.println(f1.hashCode());//183264084
		System.out.println(f2.hashCode());//476402209
		System.out.println(f3.hashCode());//1490180672
		System.out.println(f4.hashCode());//1490180672
		
		/*
		 * 実際にはequals()でhascodeにより判定になりますので
		 * オブジェクトに対して、別々でインスタンス化しても保持している値がすべて同じであれば等価でかると判断したい場合：equals()hasCode()
		 * のオーバーライドは必要になる
		 * 
		 * オーバーライドする場合、従うべきルール：
		 * １、同じオブジェクトに対してhasCode()メソッドが複数回呼び出されても同一の整数値を返す
		 * ２、二つのオブジェクトをequals()メソッドで比較してtrueが帰る場合は、二つのオブジェクトのハッシュコードは同じとなる
		 * ３、二つのオブジェクトをequals()メソッドで比較してfalseが帰る場合は、二つのオブジェクトのハッシュコードは同じでも異なるでもどちらでも良い。
		 * 　　ただし、異なるを返す方はパフォーマンスが向上する場合がある
		 * ４、二つのオブジェクトのハッシュコードの値は異なる場合は、二つのオブジェクトをequals()メソッドで比較してもfalseを返す
		 * ハッシュコードは同じでも、equals()でtrueわけではない。
		 * でも、equals()がtrueを返されるため、ハッシュコードは必ず同じになる！！
		 */
		D01_ObjectClass d1 = new D01_ObjectClass();
		D01_ObjectClass d2 = new D01_ObjectClass();
		System.out.println(d1.equals(d2));  //true
		
	}
	private int num;
	@Override
	public boolean equals(Object obj) {
		// TODO 自動生成されたメソッド・スタブ
		if ((obj instanceof D01_ObjectClass) && (((D01_ObjectClass)obj).num == this.num)) {
			return true;
		}else {
			return false;
		}
	}
	@Override
	public int hashCode() {
		// TODO 自動生成されたメソッド・スタブ
		return num * 5;
	}
	

}