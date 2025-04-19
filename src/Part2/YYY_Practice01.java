package Part2;

import java.lang.annotation.Inherited;

public class YYY_Practice01 {
	/**
	 * E    F   CE   D    C    C    C→BE    CE(第二次)
	 * 答え：
	 * 	1:E
	 *  2:F
	 *  3:CE
	 *  4:D
	 *  5:C
	 *  6:C
	 *  7×:BC　→　BE   @interface里面不可以有Object型，List 数字型 null！！！！
	 *  8:CE
	 */
	
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

	}

}
/*
 * No.７
 */
class Foo{}
@Inherited
@interface MyAnnot{
//	String value() default null; //定数式は必要。インスタンス化式、nullは不可
int num = 10;//通常のインタフェースと同様にstatic（暗黙的に）な定数は宣言できる
	double method() default 10.0;
//	Foo getFoo();//注釈の属性 MyAnnot.getFoo の型 Foo が無効です。基本タイプ、ストリング、クラス、注釈、列挙型またはそれらの 1 次元配列のみが許可されています
}
