package Part2;

import java.lang.annotation.Repeatable;

/**
 * @Repeatable
 * 同じ場所に同じアノテーションを複数回適用する場合、使用
 * @Repeatableを使用する際に、２種のカスタムアノテーションを定義する必要がある
 * １：複数回使用可能となるRepeatable自体（@Repeatableの値は２のカスタムアノテーションクラス）
 * ２：１のカスタムアノテーションが複数回使用された際にデータを保持する専用のアノテーション（コンテナアノテーション）クラス
 */
@Repeatable(C01_AnnotAndAnnot06.class)
public @interface C01_AnnotAndAnnot05 {
	String value();
}
