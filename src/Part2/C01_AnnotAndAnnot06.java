package Part2;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface C01_AnnotAndAnnot06 {
	public C01_AnnotAndAnnot05[] value();
}
