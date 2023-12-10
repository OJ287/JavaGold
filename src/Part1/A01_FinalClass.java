package Part1;

public class A01_FinalClass {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		// final修飾子；クラス、メソッド、変数に適用できる
		/**
		 * コンストラクタ：構造メソッド
		 * イニシャライザ：Java Initializer(Java 初始化程序)
		 * 		https://qiita.com/kuzumochi/items/02bddefa900e18af7769
		 * 		https://blog.csdn.net/macleane121/article/details/3888668
		 */

		// 変数
		final Foo obj1 = new Foo(20);
//		obj1.num1=11;//final フィールド Foo.num1 には代入できません
//		obj1.num2=21;//final フィールド Foo.num2 には代入できません
//		obj1=new Foo(200);//final のローカル変数 obj1 には代入できません。 ブランクでなければならず、複合代入を使用することはできません
		Foo obj2 = new Foo(2000);
		obj2 = new Foo(20000);

		System.out.println(obj1.num1 + "+" + obj1.num2);
		System.out.println(obj2.num1 + "+" + obj2.num2);

	}

}

class Foo {
	final int num1 = 10;
	final int num2;

	public Foo(int num) {
		num2 = num;
	}
}

// for class
class SuperA {
}

// for class
final class SuperB {
}

//for method
class SuperC {
	void print() {
	}
}

//for method
class SuperD {
	final void print() {
	}
}

//Test
class SubA extends SuperA {
}

//class SubB extends SuperB{}  //型 SubB は final クラス SuperB をサブクラス化できません
class SubC extends SuperC {
	void print() {
		System.out.println("overRide");
	}
}
//class SubD extends SuperD{void print(){System.out.println("overRide");}}  //SuperD からの final メソッドをオーバーライドできません