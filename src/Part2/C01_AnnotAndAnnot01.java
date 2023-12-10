package Part2;

import java.lang.annotation.Documented;
/**
 *ソースコードを元にAPIドキュメントを作成する際に、アノテーション情報も含める場合、 @Documentedを使用
 *APIにC01_AnnotAndAnnot01アノテーションの情報を書きだしたい場合、C01_AnnotAndAnnot01に@Documentedを使用
 *
 */

@Documented
public @interface C01_AnnotAndAnnot01 {
	public enum RANK {A,B,C}
	RANK rank();
	String item();
	int num();

}
