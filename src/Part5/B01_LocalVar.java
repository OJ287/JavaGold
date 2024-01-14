package Part5;
/**
 * ローカル変数の型推論
 * varによる変数宣言
 * JDK10以降では、Local-Variable Type Inference （ローカル変数型推論）が使用可能です。
 * これにより、ローカル変数の宣言時に特定のデータ型を記述するのではなく、varを利用することができます。
 * 　　　従来の書き方：int num = 10:
 * 　　　ローカル変数型推論の利用：var num = 10:
 *   ローカル変数型推論を利用した場合、変数のデータ型が不明なように見えますが、
 *   初期化時に代入されている右辺の値（上記の例であれば10）により、num変数はint型と推論できます。
 * varが利用できる箇所は以下の通り
 * 　　・ローカル変数の初期化時
 * 　　・通常for文内のインデックス
 * 　　・拡張for文内で宣言されたローカル変数
 * 　　・try-with-resources文内のローカル変数
 * なお、メソッドの引数、コンストラクタの引数、メソッド戻り型、フィールド（メンバ変数）、catchブロックなどでは使用できません。
 * また、varは下位互換性を考慮し、予約語ではありません。よって、識別子としては使用可能です
 *
 */

import java.util.ArrayList;

public class B01_LocalVar {
//    var a = 100;//コンパイルエラー　　　メンバ変数（インスタンス変数）にvarを使用することはできない
//    static var b = 100;//コンパイルエラー　　　メンバ変数（static変数）にvarを使用することはできない
//    public void method(var data) {　　　　メソッドの引数にvarを使用することはできない
    public void method() {
		var c = "hello";    //c変数は、"Hello"の代入により、Stringがたにとなる
//		var d;//コンパイルエラー　　　初期値を指定せずに、varを使用することはできない
//		var e =null;//コンパイルエラー　　　　nullであると、e変数の型を推論できない
		final var f = 100;//定数でもvarの使用は可能
//		var final g = 100;//コンパイルエラー　　　finalを使用するさいに、varおん左側に記載さること。
		var obj = new B01_LocalVar();//obj変数は、インスタンス化したオブジェクトの代入により、推論できる
		var list = new ArrayList<>();//右辺ではダイアモンド演算子を使用しているため、list変数はArrayList＜Object＞型となる
		for(var i = 0; i < 10; i++) {//OK
//			var ary1 = {10, 20};//コンパイルエラー   配列の初期化では、明示的な型は必要のため、コンパイルエラー
			var ary2 = new int[] {10, 20};//芽おじ的な型を指定しているため、有効なコード
		}
	}
    
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

	}

}
