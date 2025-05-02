package Part8;

public class A01_Threads {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
	    // スレッドの作成
        Thread a = new ThreadA();
	    ThreadB b = new ThreadB();
	    // スレッドの実行開始
	    a.start();
	    b.start();
	    // B:0 B:1 B:2 B:3 B:4 A:0 A:1 A:2 A:3 A:4 
        // A:0 A:1 A:2 A:3 A:4 B:0 B:1 B:2 B:3 B:4
        // 实行结果不一样，每个线程都是独立运行的
	}

}
	class ThreadA extends Thread { // スレッドクラス
		  public void run() {     // スレッドが実行する処理
		    for(int i = 0; i < 5; i++){
		      System.out.print("A:" + i + " ");
		    }
		  }
		}
		class ThreadB extends Thread { // スレッドクラス
		  public void run() {     // スレッドが実行する処理
		    for(int i = 0; i < 5; i++){
		      System.out.print("B:" + i + " ");
		    }
		  }

}
