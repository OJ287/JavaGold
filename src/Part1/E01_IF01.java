package Part1;

interface E01_IF01 {
	//A default method cannot override a method from java.lang.Object 
//	public default boolean equals(Object obj) {
//		return true;
//	}
//	default int hashCode() {
//		return 10;
//	}
//	default String toString() {
//		return "hello world";
//	}
//
	// SE8以降はstaticとdefaultの宣言が可能となる
	// staticの抽象メソッドは宣言できない
	// 抽象メソッドは実装したクラス側にオーバーライドが必要となるため、finalの付与ができない
	// public static final変数（定数）だけでOK
	// 抽象メソッドが必ずpublic abstract
	static String staticMethod1() {
		System.out.println(privateMethod2());
//		System.out.println(privateMethod());型 E01_IF01 から非 static メソッド privateMethod() に static 参照することはできません
		return "static String staticMethod1()";
	}

	static void method() {//暗黙的にpublicが付与される、privateでもOKだが、protectedだめ
		System.out.println("E01_IF01");

	}

	//　指定していない場合、強制的にpublicが付与
	//　protected使用できない、付与するとコンパアイルエラー
	// Objectのequals,hashcode,toStringのdefaultとして定義できないコンパアイルエラー
	default void defaultMethod() {
		System.out.println(privateMethod());
		System.out.println(privateMethod2());
	}
	// SE9以降はstaticとdefaultの宣言が可能となる、

	default void defaultMethod2() {
		System.out.println(privateMethod());
		System.out.println(privateMethod2());
	}
	private static String privateMethod2() {
		return "private static int privateMethod2()";
	}

	/**
	 * 接口中方法的访问规则总结：
	 * private 方法（如 privateMethod）：
	 * ✅ 可以在接口自身的 default 或其他 private 方法中调用。
	 * ❌ 不能在接口外部调用,不能在接口自身的 static （会编译错误）。
	 * <p>
	 * private static 方法（如 privateMethod2）：
	 * ✅ 可以在接口自身的 static 或 default 或其他 private 方法中调用。
	 * ❌ 不能在接口外部调用（会编译错误）。
	 * <p>
	 * public static 方法（如 method）：
	 * ✅ 可以通过 A.m2() 在任何地方直接调用（包括类 D 中）。
	 */
	private int privateMethod() {
		return 100;
	}
	double method1();

}
