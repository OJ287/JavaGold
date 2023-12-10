package Part2;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 
 * @Retention
 * アノテーションをソースコードか、クラスファイルか、VM実行時かまで残したいかを指定する
 * 指定値は列挙型のElementType
 *
 * 定数：デフォルト値ないため、必ず指定ください、指定しなければコンパイルエラーになる
 * SOURCE:アノテーションはコンパイラによって破棄される
 * CLASS:アノテーションはコンパイラによってクラスファイルに記録されるが、実行時にVMによって無視される。デフォルトの動作である
 * RUNTIME:アノテーションはコンパイラによってクラスファイルに記録され、実行時にVMによって読み取られる
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface C01_AnnotAndAnnot03 {

}
