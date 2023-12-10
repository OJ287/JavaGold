package Part2;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * このアノテーションはクラス宣言で使用する、またはメソッドに使用するといった適用箇所を限定したい場合
 * ＠Targetを使用
 * 指定値は列挙型のElementType
 * 
 * 定数：デフォルト値ないため、必ず指定ください、指定しなければコンパイルエラーになる
 * TYPE:クラス、インタフェース（アノテーションを含む）、列挙型
 * FIELD:フィールド（列挙型の定数を含む）
 * METHOD:メソッド
 * PARAMETER:メソッドパラメータ（引数）
 * CONSTRUCTOR:コンストラクタ
 * LOCAL_VARIABLE:ローカル変数
 * ANNOTATION_TYPE:メタアノテーション
 * PACKAGE:パッケージ
 * TYPE_PARAMETER:型パラメータ
 * TYPE_USE:型（クラス型の変数宣言時など）
 * MODULE:モジュール
 */
//@Target(ElementType.METHOD)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface C01_AnnotAndAnnot02 {
	String value();
}
