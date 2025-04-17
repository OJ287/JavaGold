package Part1;

import java.util.HashSet;

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

        HashSet<D01_ObjectClass> set = new HashSet<>();
        set.add(d1);//hashCode
        set.add(d2);//hashCode  equals  num:0
        System.out.println("HashSet size: " + set.size());//HashSet size: 1


	}
	private int num;
	@Override
	public boolean equals(Object obj) {
		// TODO 自動生成されたメソッド・スタブ
        System.out.println("equals");
        System.out.println("num:" + num);//num:0
        return (obj instanceof D01_ObjectClass) && (((D01_ObjectClass) obj).num == this.num);
	}
	@Override
	public int hashCode() {
		// TODO 自動生成されたメソッド・スタブ
        System.out.println("hashCode");  //true
		return num * 5;
	}
    /**
     * 为什么重写了 equals() 就必须配套重写 hashCode()？
     * 如果两个对象通过 equals() 判断为相等，那么它们的 hashCode() 必须相同。
     * 但 hashCode() 相同的两个对象，equals() 不一定要返回 true。
     *
     * Java 重写 hashCode() 时必须遵守的 3 条规则
     * 🔒 规则 1：相等对象的 hashCode 必须相等
     * 如果 a.equals(b) == true，那么 a.hashCode() == b.hashCode() 必须成立。
     *
     * 🔎 这是最核心的规则。
     * Java 集合类依赖这个规则来判断对象是否属于同一“桶”。
     *
     * ⛔ 如果你违反这个规则，会导致：
     *
     * HashSet 添加了两个你以为“相等”的对象，但其实放到了不同的位置；
     *
     * HashMap.get(key) 永远找不到对应的值，即使 key.equals() 成立。
     *
     * ♻️ 规则 2：不等对象的 hashCode 不要求不同，但不同会更好
     * 如果 a.equals(b) == false，那么 a.hashCode() 可以相同，但最好不同。
     *
     * 这是出于性能优化考虑：
     *
     * HashMap / HashSet 中使用哈希桶（bucket）来存储对象；
     *
     * 如果两个对象 hashCode() 一样，就会落到同一个桶，退化为用 equals() 一个个比较。
     *
     * ✅ 所以：尽量让 hashCode() 分布“离散”，可以提升集合操作的性能。
     *
     * 🔁 规则 3：对象状态不变时，hashCode 必须一致
     * 在对象生命周期中，如果你没有改变它影响相等性的字段，那么 hashCode() 的值就不能变。
     *
     * 也就是说：
     *
     * 如果你用某些字段写了 equals() 和 hashCode()，就不能在加入 HashSet 后又去改这些字段。
     *
     * 否则对象的 hashCode 就变了，Java 集合找不到它原来的位置了！
     *
     *
     *
     * 实际写法建议
     * 如果你的 equals() 用某几个字段判断对象是否相等，那么 hashCode() 就应该用这几个字段生成
     *
     * @Override
     * public int hashCode() {
     *     return Objects.hash(field1, field2, field3);
     * }
     *
     *
     * 也可以手动实现：
     * @Override
     * public int hashCode() {
     *     int result = 17;
     *     result = 31 * result + field1;
     *     result = 31 * result + (field2 == null ? 0 : field2.hashCode());
     *     return result;
     * }
     *
     *
     * 示例：正确重写 equals() 和 hashCode()
     *
     *
     *
     * public class Person {
     *     private String name;
     *     private int age;
     *
     *     @Override
     *     public boolean equals(Object o) {
     *         if (this == o) return true;
     *         if (!(o instanceof Person)) return false;
     *         Person p = (Person) o;
     *         return age == p.age && Objects.equals(name, p.name);
     *     }
     *
     *     @Override
     *     public int hashCode() {
     *         return Objects.hash(name, age);
     *     }
     * }
     */

}