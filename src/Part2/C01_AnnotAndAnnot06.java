package Part2;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 複数使用可能とするカスタムアノテーションに@Repeatableを付与する。その際、引数にデータを保持する専用（コンテナアノテーション）クラスを指定する
 * コンテナアノテーションは、通常通りアノテーションとして定義するただし、戻り値を複数回利用するアノテーションの配列をとるメンバを定義する
 */

@Retention(RetentionPolicy.RUNTIME)
public @interface C01_AnnotAndAnnot06 {
	C01_AnnotAndAnnot05[] value();
}
