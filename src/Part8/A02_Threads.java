package Part8;

public class A02_Threads {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

	    // スレッドの作成
	    Thread a = new Thread(new ThreadA_02());
	    Thread b = new Thread(new ThreadB_02());
	    // スレッドの実行開始
	    a.start();
	    b.start();
	    // A:0 B:0 B:1 A:1 B:2 B:3 B:4 A:2 A:3 A:4 
        // A:0 A:1 A:2 A:3 A:4 B:0 B:1 B:2 B:3 B:4

        System.out.println("-------");
        // スレッドの作成
        Thread a1 = new Thread(new ThreadA_02());
        Thread b1 = new Thread(new ThreadB_02());
        // スレッドの実行開始
        a1.setPriority(Thread.MAX_PRIORITY);
        b1.setPriority(Thread.MIN_PRIORITY);
        a1.start();
        b1.start();
//		a1.start();//“每个线程对象只能启动一次，启动后不能再次调用 start() 方法，否则会抛出 IllegalThreadStateException 异常。”
        // A:0 B:0 B:1 A:1 B:2 B:3 B:4 A:2 A:3 A:4
        // A:0 A:1 A:2 A:3 A:4 B:0 B:1 B:2 B:3 B:4
	}
}

	// Runnableインタフェースの実装クラス
	class ThreadA_02 implements Runnable {
	  public void run() {     // スレッドが実行する処理
	    for(int i = 0; i < 5; i++){
	      System.out.print("A:" + i + " ");
	    }
	  }
	}
	// Runnableインタフェースの実装クラス
	class ThreadB_02 implements Runnable {
	  public void run() {     // スレッドが実行する処理
	    for(int i = 0; i < 5; i++){
	      System.out.print("B:" + i + " ");
	    }
	  }
}
