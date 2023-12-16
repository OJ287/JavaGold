package Part3;

import java.sql.SQLException;

public class E01_TryWithResources {
	/**
	 * TryWithResources（似ている処理：try-catch-finallyのfinallyで常にリソースアクセルのクローズ処理を行い）
	 * SE7から導入
	 * tryでリソースに関する実装を記述することで、try終了後暗黙的にclose()が呼び出され、
	 * つまり、close()を明示的に記述する必要はない、tryのみに使用も可能
	 * try実行完了次第、try()で声明したresourcesのclose()が呼び出され、その後はcatch-finallyの処理を行う
	 */
	/*
	 * try()内で記述できるものは下記二つインタフェースの実装クラス（）内でインスタンス化OK、外部変数を参照してもOK
	 * java.lang.AutoCloseable
	 * 		void close() throws Exception
	 * 		このリソースをとじ、ベースとなるリソースを全て解放する
	 * java.io.Cloceable（AutoCloseableのサブインタフェース）
	 * 		void close() throws IOException
	 * 		このストリームをとじ、それに関連する全てのシステムリソースを解放する
	 */
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		try(MyResource myResource1 = new MyResource("myResource1");AutoCloseable myResource2 = new AutoCloseable() {
			{
				System.out.println("myResource2 Start");
			}
			@Override
			public void close() throws Exception {
				// TODO 自動生成されたメソッド・スタブ
				System.out.println("closed:myResource2");
			}
		}) {
			System.out.println("try処理");
			throw new SQLException();
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("SQLException");
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exception");
		}finally {
			System.out.println("finally 処理");
		}
		/*
		 *　出力結果：close順はtry()で宣言したリソースの逆
			myResource1 Start
			myResource2 Start
			try処理
			closed:myResource2
			closed:myResource1
			SQLException
			finally 処理
		 */
		
		
		/*
		 * try()の定義例
		 * ①：try()内でリソースをインスタンス化
		 */
		try(MyResource myResource = new MyResource("")){
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		/*
		 * try()の定義例
		 * ②：varを使用した変数宣言
		 */
		try(var myResource = new MyResource("")){
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		/*
		 * try()の定義例
		 * ③：事前にインスタンス化した変数（実質的final）を使用する
		 */
		MyResource myResource = new MyResource("");
		try(myResource){
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		/*
		 * try()の定義例
		 * ④：メソッドで受け取った変数（実質的final）を使用する
		 * void method(MyResource myResource){
				try(myResource){
					
				}catch (Exception e) {
					// TODO: handle exception
				}
		 * }
		 */
		/*
		 * try()で指定された変数は、明示的にfinalを付与されなくても
		 * 実質的finalとなります。したがって、再代入は不可 
		 */
		/*
		 * ⑤コンパイルエラーになるコード
		 */
		try (MyResource myResource2 = new MyResource("")){
//			myResource2=new MyResource("");//NG The resource myResource2 of a try-with-resources statement cannot be assigned
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	/*
	 * ⑥コンパイルエラーになるコード
	 */
	void method(MyResource myResource) {
//		try (myResource = new MyResource("")){//NG  myResource を型に解決できません
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
	}

}

class MyResource implements AutoCloseable{
	private String msgString ;
	public MyResource(String msgString) {
		this.msgString = msgString;
		System.out.println("myResource1 Start");
	}
	@Override
	public void close() throws Exception {
		// TODO 自動生成されたメソッド・スタブ
		System.out.println("closed:" + msgString);
	}
}