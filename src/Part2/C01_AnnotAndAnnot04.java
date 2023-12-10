package Part2;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Inherited：
 * アノテーションを使用しているクラスをもとにサブクラスを作成した際に、そのサブクラスにアノテーションを継承したい場合、使用する
 * スーパークラスからアノテーションを継承させるだけ、インタフェースに対する注釈は無効
 * また、クラスアノテーションのみ適用され、メソッドやフィールドアノテーションでは適用できない
 * 
 */
@Inherited
//METHOD,FIELDを指定しても、メソッドやフィールドアノテーションでは継承は適用できない
@Target({ElementType.TYPE,ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface C01_AnnotAndAnnot04 {
	String value();
}
