package Part2;
@B01_MyAnnotaion01(value = "B01_MyAnnotaion",rank = B01_MyAnnotaion01.RANK.A,num = 10)
public class B01_MyAnnotaion {
	/**
	 * カスタマアノテーション
	 * 独自のアノテーションB01_MyAnnotaion01の作成：アノテーションはインタフェースである
	 * しかし、宣言時にinterfaceではなく@interfaceを使用
	 */

	@B01_MyAnnotaion01(value = "B01_MyAnnotaion.main",rank = B01_MyAnnotaion01.RANK.C, num = 10)
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

	}

}
