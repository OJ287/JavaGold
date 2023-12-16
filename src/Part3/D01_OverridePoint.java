package Part3;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

public class D01_OverridePoint {
	/**
	 * オーバーライドの注意点
	 * ・オーバーライドとは、メソッド名、引数リストが全く同じメソッドをサブクラスで定義すること
	 * ・戻り値はスーパークラスで定義したメソッドが返す型と同じか、その型のサブクラスとなる
	 * ・アクセス修飾子はスーパークラスと同じか、それより公開範囲が広いものであれば使用可能
	 * ・サブクラス側throwsにはスーパークラスのメソッドがthrowsに指定した例外クラスとそのサブクラスが指定できる
	 * ・RuntimeExceptionクラス及びそのサブクラスは、制約なしにthrowsに指定できる
	 * ・スーパークラスのメソッドにthrowsがあっても、サブクラス側に指定しなくても良い
	 */

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

	}

}
class Super{
	void method()  throws IOException{
		
	}
}
class SubA extends Super{
	void method() {
		
	}
}
class SubB extends Super{
	void method() throws FileNotFoundException{//OK   スーパークラスの例外のサブクラスはOK
		
	}
}
//class SubC extends Super{
//	void method() throws Exception{//NG   スーパークラスの例外のスーパークラスはだめ　　例外 Exception は Super.method() にある throws 節と矛盾します
//		
//	}
//}
//class SubD extends Super{
//	void method() throws SQLException{//NG   スーパークラスの例外と関係ないし、RuntimeExceptinのサブクラスではない  例外 SQLException は Super.method() にある throws 節と矛盾します
//		
//	}
//}

class SubE extends Super{
	void method() throws RuntimeException{//OK   RuntimeException例外は制約なしにスローできる
		
	}
}