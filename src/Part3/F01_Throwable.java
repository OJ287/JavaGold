package Part3;

import java.sql.SQLException;

public class F01_Throwable {
	/**
	 * Throwable
	 * try-with-resources文の概念と組み合わせて、「抑制された例外」という概念がサポートされている
	 * 
	 * Throwableクラスのメソッド：
	 * ①：final void addSuppressed(Throwable exception)
	 * 		この例外を提供する目的で抑制された例外に、指定された例外を追加する
     * 项目	内容
     * 📌 方法是 final	子类不能重写它
     * ❌ Null 报错	传入 null 会抛 NullPointerException
     * ❌ 自己 suppress 自己	会抛 IllegalArgumentException，不能把异常自己挂自己身上
     * 那如果你这么写：
     * Exception ex = new Exception("我是异常");
     * ex.addSuppressed(ex); // ❌ 错误：自己 suppress 自己
     * 你就是试图把 ex 自己加到自己身上作为附属异常，这是 不允许的！
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
				MyResource2.close()のエラー：myResource22
				MyResource2.close()のエラー：myResource21
			finally処理
		 */

	}

}

class MyResource2 implements AutoCloseable{
	private final String msgString ;
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
        throw new SQLException("MyResource2.close()のエラー：" + msgString);
	}
}