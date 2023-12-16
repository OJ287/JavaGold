package Part3;

public class H01_Assert {
	/**
	 * アサーションとは、プログラマが前提している条件をチェックし、プログラムの正しい動きを保証するための機能である
	 * 
	 * プログラマが「ここは結果がtrueになるであろう」と思ってプログラムを書いても、本当にtrueになるかどうかは
	 * 実行してみないとわからない。
	 * アサーション機能を使用するとtrueにならなっかた場合、エラーを表示させることができ、バグの検出に有効である
	 * 
	 */
	/*
	 * 構文：
	 * ①：assert boolean;
	 * ②：assert boolean:メッセージ;
	 *　メッセージの有無は任意
	 *assert()カッコ内は、プログラム実行時にtrueになるべき式を記述、つまりそのメソッドの前提条件である
	 *前提条件はOK（true）の場合、assert以降の処理を続き
	 *前提条件は NG（false）の場合、assertまで停止。
	 *		メッセージあれば、デフォルトのエラー（AssertionError）と定義したメッセージが表示される
	 *		メッセージなければ、デフォルトのエラー（AssertionError）が表示される
	 *Throwable
	 * |___Exception
	 * |___Error
	 *       |___AssertionError
	 * AssertionErrorガスローされることがないようにプログラムを修正することが目的
	 * したがって、AssertionErrorのための例外処理コードは書きません
	 */
	public static void checkTest(int point) {
		assert (point > 0):" point :" +point;
		//以下はcheckTest処理
	}
	/*
	 * javacでコンパイル
	 * javaで実行すれば、
	 * 	・java H01_Assertで実行すると、エラーにならない：原因はアサーションは有効させない
	 * 	・java -ea H01_Assertで実行すると、「test.method(-1)」行ではAssertionErrorになる：原因は -ea オペションでアサーションを有効させる
	 * 	・では、アサーションを明示的に無効させるオペションもある、java -da H01_Assert：-da オペションでアサーションを無効させる
	 */
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Test test = new Test();
		System.out.println(test.method(10));
		System.out.println(test.method(-1));
	}

	/*
	 * アサーションの利用場面：
	 * アサーションを利用する場面
		アサーションの実行を正常動作の一部とするようなプログラムは書くべきではありません。
		たとえば、エンドユーザの入力値をチェックし、その結果に応じて処理を分岐させるといったことにアサーションを使ってはいけません。
		アサーションは、プログラムにバグを残さないための1つの手法です。
		したがって、作成したプログラムをリリースする段階では、必ずAssertionErorがスローされないレベルに達している必要があります。
		アサーションは、以下のような条件を検証するときに使用します。
		①事前条件
			メソッドが呼び出されたときにtrueであるべき条件です。
			ただし、publicメソッド内の引数チェックに使用することは推奨されていません。なぜなら
			publicメソッドはアサーションが有効かどうかにかかわらず引数をチェックする必要があるためです。
			したがって、private メソッドの引数などの検証に使用します。
		②事後条件
			メソッドが正常に実行された後にtrueであるべき条件です。
			事後条件のアサーションはpublicメソッド内、その他のメソッド内で使用できます。
		③不変条件
			常にtrueであるべき条件です。
			プログラムが正しい動作をするために、常に満たしていなければならない条件を検証するものです。
	 */
}
class Test{
	private int checkTest(int point) {
		assert (point > 0):" point :" +point;
		//以下はcheckTest処理
		return point * 100;
	}
	int method(int point) {
		return checkTest(point);
	}
}
