package Part1;

public enum C02_Card {
	/*
	 * 列挙型はクラス定義の中、あるいは列挙型の定義だけを記述したソースファイルです。
	 * 列挙型は、java.lang.Enumクラスを継承したfinalクラスとなり、列挙した値はpublic static
	 * final指定されたクレス変数の名前になり 列挙型の実体は定数やメソッドを持つクラス 明示的なインスタンス化は不可
	 * 
	 */
	SPADES(100), CLUBS(200), DIAMONDS(300), HEARTS();// HEARTS：引数なければ、カッコの省略可能→HEARTS;

	private final int a;

	C02_Card() {
		this.a = 500;
	}

	C02_Card(int a) {
		this.a = a;
	}

	public int GetA() {
		return this.a;
	}

    @Override
    public String toString() {
        return super.toString();//オーバーライド可能
    }

}
