package Part8;

public class A03_Threads {
	/*
	 * Runnableインタフェースは抽象メソッドであるrun()メソッドのみを持つインタフェースであるため
	 * 関数型インタフェースとして定義されています。
	 * したがって、ラムダ式での実装が可能
	 */

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

	    new Thread(new Runnable() {
	      public void run() {
	        System.out.println("hello");
	      }
	    }).start();
	    
	    new Thread(() -> {
	        System.out.println("hello");
	    }).start();
	}

}
