package Part1;

public class B01_StaticClass {
	/**
	 * static:
	 * 	メソッド、変数に適用。クラス、コンストラクタに適用できない
	 * 	クラスの継承については、staticメソッドを非staticメソッドでオーバーライドできません。その逆もできません
	 */

	int instanceVal;
	static int staticVal;

	int methodA() {
		return instanceVal;
	}

	int methodB() {
		return staticVal;
	}

//	static int methodC() {
//		return instanceVal;//非 static フィールド instanceVal を static 参照できません
//	}
	static int methodD() {
//		return staticVal;
		return B01_StaticClass.staticVal;
	}

	static int methodE() {
		B01_StaticClass staticClass = new B01_StaticClass();
		return staticClass.instanceVal;
	}

	static String staticString = "staticString";
	String instanceString = "instanceString";

	@SuppressWarnings({ "null", "static-access" })
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		// Static；メソッド、変数に適用できる
		// Staticメンバのオーバーライドはできない
		System.out.println(methodD());
		System.out.println(methodE());
		B01_StaticClass staticClass = null;

		// 呼び出しできる原因はstatic変数はコーディング時に参照変数名で呼び出しけど、コンパイルする際にクラス名で呼び出しになりました。
		System.out.println(staticClass.staticString);// static フィールド StaticClass.staticString には static にアクセスする必要があります
		System.out.println(staticClass.instanceString);// Null ポインター・アクセス: 変数 staticClass はこのロケーションで NULL
														// の可能性があります。//Cannot read field "instanceString" because
														// "staticClass" is null

	}

}
