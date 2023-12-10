package Part2;

import java.nio.file.NoSuchFileException;

public class C01_AnnotAndAnnot {
	/**
	 * アノテーションのアノテート（アノテーションに注釈をつける）
	 * java.lang.annotationパッケージにはアノテーションのアノテーション（メタアノテーション）が提供された
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 * @Documented：java APIドキュメントの出力にも反映するようにする
	 * @Target：アノテーションを付与する要素を限定する
	 * @Retention：アノテーションをソースコードもしくはクラスファイルまで保持するかなどを制御する
	 * @Inherited：サブクラスにアノテーションを引き継ぐことを示す
	 * @Repeatable：同じ場所に複数回適用する
	 */
	@C01_AnnotAndAnnot01(
			rank = C01_AnnotAndAnnot01.RANK.A,
			item = "itemX",
			num = 100
			)
	@C01_AnnotAndAnnot02(value = "@C01_AnnotAndAnnot02")
	@C01_AnnotAndAnnot03
//	@C01_AnnotAndAnnot03//複数使用されたらNG：Duplicate annotation of non-repeatable type @C01_AnnotAndAnnot03. Only annotation types marked @Repeatable can be used multiple times at one target.
	@C01_AnnotAndAnnot04(value = "@C01_AnnotAndAnnot04")
	@C01_AnnotAndAnnot05(value = "@C01_AnnotAndAnnot05_1")
	@C01_AnnotAndAnnot05(value = "@C01_AnnotAndAnnot05_2")
	public static void main(String[] args) throws NoSuchFileException,NoSuchMethodException, NoSuchFieldException, SecurityException {
		// TODO 自動生成されたメソッド・スタブ
		/*
		 * @Inherited、確認
		 */
		Class[] aryClasses  = {ChildA.class,ChildB.class,ChildC.class};
		for (Class<?> class1 : aryClasses) {
			System.out.println("[" + class1.getSimpleName() + "]");
			System.out.println(getVal(class1.getAnnotation(C01_AnnotAndAnnot04.class)));
			System.out.println(getVal(class1.getDeclaredField("filed").getAnnotation(C01_AnnotAndAnnot04.class)));
			System.out.println(getVal(class1.getDeclaredMethod("method").getAnnotation(C01_AnnotAndAnnot04.class)));
			System.out.println();
		}
		/*
		  [ChildA]
			Parent Type
			NULL
			NULL
			
			[ChildB]
			NULL
			NULL
			NULL
			
			[ChildC]
			ChildC Type
			ChildC filed
			ChildC method

		 */
	}
	static String getVal(C01_AnnotAndAnnot04 an) {
		if (an == null) {
			return "NULL";
		}
		return an.value();
	}
}

/*
 * @Inherited使用例
 */

@C01_AnnotAndAnnot04(value = "MyInter Type")//指定してもインタフェースに対する適用できないため、アノテーションを継承できない
interface MyInter{
	@C01_AnnotAndAnnot04(value = "MyInter method")//指定してもインタフェースに対する適用できないため、アノテーションを継承できない
	void method();
}

@C01_AnnotAndAnnot04(value = "Parent Type")
class Parent{
	@C01_AnnotAndAnnot04(value = "Parent field")  //指定してもメソッドやフィールドアノテーションでは適用できないため、アノテーションを継承できない
	protected String field;
	@C01_AnnotAndAnnot04(value = "Parent method")//指定してもメソッドやフィールドアノテーションでは適用できないため、アノテーションを継承できない
	protected void method() {
	}
}
class ChildA extends Parent{
	protected String filed;
	protected void method() {
	}
}
@C01_AnnotAndAnnot04(value = "ChildC Type")//Parentのアノテーション注釈を上書き
class ChildC extends Parent{
	@C01_AnnotAndAnnot04(value = "ChildC filed")
	protected String filed;
	@C01_AnnotAndAnnot04(value = "ChildC method")
	protected void method() {
	}
}
class ChildB implements MyInter{
	protected String filed;
	public void method() {
	}
}
