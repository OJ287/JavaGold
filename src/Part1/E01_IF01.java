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
	static String staticMethod1() {
		System.out.println(privateMethod2());
//		System.out.println(privateMethod());型 E01_IF01 から非 static メソッド privateMethod() に static 参照することはできません
		return "static String staticMethod1()";
	}
	default void defaultMethod() {
		System.out.println(privateMethod());
		System.out.println(privateMethod2());
	}
	private int privateMethod() {
		return 100;
	}
	private static String privateMethod2() {
		return "private static int privateMethod2()";
	}
	static void method() {//暗黙的にpublicが付与される
		System.out.println("E01_IF01");
		
	}
	double method1();

}
