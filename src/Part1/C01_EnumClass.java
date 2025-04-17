package Part1;
public class C01_EnumClass {
	/**
	 * 列挙型の定義では、enumキーワードを使用 クラスと同様に、コンストラクタ、メソッド、メンバ変数を定義できる コンパイルするとクラスファイルが生成されり
	 * 列挙した値を参照するには「列挙型名.列挙した値」とする（public final static定数だから）
	 * newキーワードによるインスタンス化できない（明示できない） 
	 * 列挙型によって作成されたクラスはfinalクラスであるため、extandsによる継承できない
	 * 抽象メソッドの利用や、インタフェースの実装は可能 
	 * 列挙型はCompareToインタフェースを実装しており、各定数の列記した順番で管理される
	 */

	// 列挙型クラスに具象的なメソッドだけではなく抽象メソッドでも宣言家の可能
	enum Vals {
		// A,B; //Valsで抽象メソッドとしてfooを宣言していますので、オブジェクトABはfooの正しいオーバーライドする必要があり
		A {
			void foo() {
				System.out.println("A");
			}
		},
		B {
			void foo() {
				System.out.println("B");
			}
		};

		abstract void foo();
	}

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		C02_Card card1 = C02_Card.SPADES;
		C02_Card card2 = C02_Card.DIAMONDS;
		System.out.println(card1);
		System.out.println(card2);
		System.out.println(card1.GetA());
		System.out.println(card2.GetA());
		System.err.println(card1.ordinal());//列挙宣言での位置を返す。index
		System.out.println(card2.ordinal());
		System.out.println(C02_Card.HEARTS);
		System.out.println(C02_Card.HEARTS.GetA());
		for (C02_Card card : C02_Card.values()) {// EnumにはcompareTo()メソッドを実装しており、定数が宣言された順番で管理するよう実装されていうます
//			System.out.print(card + "    ");
			System.out.print(card.toString() + "    ");//任意でオーバーライドできる
            // EnumクラスはComparableインタフェースを実装しており、各定数は列記した順番で管理される
            if (card.equals(C02_Card.SPADES)) {
                System.out.print(card.name() + "    ");//オーバーライド出来ない
            }
        }
		Vals.A.foo();// static定数なので、クラスで呼び出すのは可能
	}

}
