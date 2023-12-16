package Part3;

import java.awt.image.ImagingOpException;
import java.io.IOException;
import java.lang.reflect.Method;

public class C01_Throws {
	/**
	 * throws
	 * 例外が発生する可能性があるメソッドを定義する時、「throws 発生する例外クラス名」を指定できる
	 * 例外が発生した際に例外のオブジェクトをメソッドの呼び出しもとに転送
	 */

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		try {
			method();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			System.out.println(e.getMessage());
		}
		
	}
	/*
	 * そのメソッドに発生する例外のうちに、呼び出しもとに転送したい例外をthrowsで定義
	 * 複数定義したい場合、カンマ,で区切り可能
	 */
	
	/*
	 * メソッド内で発生するuncheckedに対して、throwsによる明示しなくてもOK
	 * 		例外発生する時に、呼び出しもとはtryしなければ、JAVA環境にスローされ例外メッセージ表示される
	 * メソッド内で発生するcheckedに対して、throwsによる明示的な指定が必要
	 * 
	 */ 
	static void methodA() throws ArrayIndexOutOfBoundsException {
//		static void methodA() {   //これでもOK、発生するunchecked例外はthrowsで明示しなくてもOK
		throw new ArrayIndexOutOfBoundsException();
	}
	static void methodB() throws IOException {
//		static void methodB() {   //これでもNG、処理されない例外の型 発生するchecked例外はthrowsで明示しなければNG
		throw new IOException();
	}
	
	/*
	 * JAVAライブラリー提供しているメソッドがスローする
	 * ①：java.ioで提供されているFileReaderクラスのコンストクラス
	 * 		public FileReader (String fileName)throws FileNotFounfException
	 * 		例外処理：必須
	 * 		FileNotFounfExceptionはRuntimeExceptionをスーパークラスに持たないため、例外処理が必須
	 * ②：java.lang提供されているIntegerクラスのメソッド
	 * 		public static int parseInt(String s) throws NumberFormatException
	 * 		NumberFormatExceptionはRuntimeExceptionをスーパークラスに持っているため、例外処理が任意
	 */

	
	//続きはmain()で確認する
	public static void method() throws MyExceptionA,MyExceptionB,RuntimeException{
		int num = (int)(Math.random()*10);
		try {
			if (num <4) {
				throw new MyExceptionA();
			}else {
				throw new MyExceptionB();
			}
		} catch (Exception e) {
			// TODO: handle exception
			//加工処理
//			e.setStackTrace(null);
//			e = new RuntimeException();//これがあれば、RuntimeExceptionをスローになる。メソッドにRuntimeExceptionの明示的な指定は必要
			throw e;
		}
	}
	
}
/*
 * rethrow
 * 多くのクラスを使用するアプリケーションでは、スローされた例外をcatchで一旦受け取り、
 * その例外を加工して再度スローすること
 */
class MyExceptionA extends Exception{
	public MyExceptionA() {
		// TODO 自動生成されたコンストラクター・スタブ
		super("MyExceptionA");
	}
}

class MyExceptionB extends Exception{
	public MyExceptionB() {
		// TODO 自動生成されたコンストラクター・スタブ
		super("MyExceptionB");
	}
}
