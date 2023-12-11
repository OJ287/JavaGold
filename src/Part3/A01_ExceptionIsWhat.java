package Part3;

public class A01_ExceptionIsWhat {
	/**
	 * コンパイルが成功しても、実行した際に発生するエラーを例外と呼ぶ
	 * 例外がスローされた際に、プログラム側でその例外をキャッチしないと、プログラムを強制終了
	 * 
	 * 稼働し続けるアプリケーションは実行時に発生したエラーによって強制的に異常終了を未然に
	 * 防がなくてはいけないので、例外処理というメカニズムを使用し、
	 * エラーがあっても強制終了されることなく実行を継続できる
	 */

	/**
	 * JAVAで扱われる例外クラス：
	 * ①checked例外
	 * 		定義：データベースなどJAVA実行環境以外の環境は原因で発生する例外
	 * 		特徴：例外処理が必須であること
	 * ②unchecked例外
	 * 		定義：実行中のプログラムが原因で発生する例外（実行時例外）、や
	 * 		　　　メモリ不足などプログラムの例外処理で復旧できない例外
	 * 		特徴：例外処理は必須ではなく任意である
	 */
	
	/**
	 * 例外クラスの三種類：
	 * ①：Errorクラス及びそのサブクラス(unchecked例外)
	 * ②：RuntimeExceptionクラス及びそのサブクラス(unchecked例外)
	 * ③：RuntimeExceptionクラス以外のExceptionのサブクラス(checked例外)
	 * 
	 * 例外処理が必須か任意かは、処理内容ではなく、処理した結果、
	 * 発生する可能性のある例外クラスが何であるかによって決定する。
	 * RuntimeExceptionクラスおよびそのサブクラスである場合には任意であり、
	 * RuntimeExceptionクラス以外のExceptionのサブクラスである場合には必須です。
	 */
	/**
	 * 主な例外クラス：
	 * Errorのサブクラス（unchecked例外、例外処理は任意）： 
	 * 		①：AssertionError
	 * 			assert文を使用している際にboolean式でfalseが返ると発生
	 * 		②：StackOverflowError
	 * 			アプリケーションでの再帰の回数が多すぎる場合に発生
	 * 		③：NoClassDefFoundError
	 * 			読み込もうとしてクラスファイルが見つからない場合に発生
	 * RuntimeExceptionのサブクラス（unchecked例外、例外処理は任意）： 
	 * 		①：ArrayIndexOutOfBounds
	 * 			不正なインデックスで要素にアクセス使用とした場合に発生
	 * 		②：ArrayStoreException
	 * 			不正な型のオブジェクトを配列に格納した場合に発生
	 * 		③：ClassCastException
	 * 			参照変数において間違えったキャストを行った場合に発生
	 * 		④：IllegalStateException
	 * 			めそっどの呼び出しが正しくない状態で行われた場合に発生
	 * 		⑤：DateTimeException
	 * 			日付・時間の計算時に誤った処理を行った場合に発生
	 * 		⑥：MissingResourceException
	 * 			リソースが見つからない場合に発生
	 * 		⑦：ArithmeticException
	 * 			整数をゼロで除算した場合に発生
	 * 		⑧：NullPointerException
	 * 			nullが代入されている参照変数に対して、メソッド呼び出しを行った場合に発生
	 * 		⑨：NumberFormatException
	 * 			整数を表さない文字列を整数に変換しようとした場合に発生
	 * RuntimeException以外のExceptionのサブクラス（checked例外、例外処理は必須）：
	 * 		①：IOException
	 * 			入出力を行う場合に発生
	 * 		②：FileNotFoundExecption
	 * 			ファイルの入出力において、目的のファイルがなかった場合に発生
	 * 		③：ParseException
	 * 			解析中に予想外のエラーがあった場合に発生
	 * 		④：SQLException
	 * 			データベース・アクセス時にエラーがあった場合に発生
	 * 
	 */
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		/*
		 * 独自例外クラスんの作成：Exceptionを継承して作成
		 * Throwable
		 * 	 |__Exception
		 * 			|__独自の例外クラス
		 * 
		 * 
		 * Throwableクラスの主なメソッド
		 * ①：void printStackTrace()
		 * 		エラートレース（エラーを追跡し発生箇所を特定する）を出力する
		 * ②：String getMessage()
		 * 		エラーメッセージを取得する
		 */
		
		MyException myException = new MyException();
		myException.printStackTrace();
		System.out.println(myException.getMessage());
	}

}
class MyException extends Exception{

	private static final long serialVersionUID = -6513105765401854346L;
}
