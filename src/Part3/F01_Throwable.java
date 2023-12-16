package Part3;

import java.lang.reflect.Method;
import java.sql.SQLException;

public class F01_Throwable {
	/**
	 * Throwable
	 * try-with-resources文の概念と組み合わせて、「抑制された例外」という概念がサポートされている
	 * 
	 * Throwableクラスのメソッド：
	 * ①：final void addSuppressed(Throwable exception)
	 * 		この例外を提供する目的で抑制された例外に、指定された例外を追加する
	 * ②：final Throwable[] getSuppressed()
	 * 		try-with-resources文によって抑制された例外をすべて含む配列を返す
	 */

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		try(MyResource2 myResource21 = new MyResource2("myResource21");
				MyResource2 myResource22 = new MyResource2("myResource22")) {
			myResource21.method();
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("e.getMessage() :" + e.getMessage());
			System.out.println("e.getSuppressed()情報");
			Throwable[] aThrowables = e.getSuppressed();
			for (Throwable throwable : aThrowables) {
				System.out.println("    "+throwable.getMessage());
			}
		}finally {
			System.out.println("finally処理");
		}
		/* method()でも、close()でもいずれもSQLException例外がスローされていたが、
		 * close()からスローされたSQLExceptionが抑制されていましたが、
		 * ThrowableクラスのgetSuppressedから抑制された例外を取得できる
		 * 
		 * 出力結果:
			myResource21  Start
			myResource22  Start
			MyResource2.method()
			closed:myResource22
			closed:myResource21
			e.getMessage() :MyResource2.method()のエラー
			e.getSuppressed()情報
			    MyResource2.close()のエラー
			    MyResource2.close()のエラー
			finally処理
		 */

	}

}

class MyResource2 implements AutoCloseable{
	private String msgString ;
	public MyResource2(String msgString) {
		this.msgString = msgString;
		System.out.println(msgString+"  Start");
	}
	public void method() throws SQLException{
		System.out.println("MyResource2.method()");
		throw new SQLException("MyResource2.method()のエラー");
	}
	@Override
	public void close() throws SQLException {
		// TODO 自動生成されたメソッド・スタブ
		System.out.println("closed:" + msgString);
		throw new SQLException("MyResource2.close()のエラー");
	}
}