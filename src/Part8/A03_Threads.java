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

    /**
     * スレッドの状態：
     * 🧵 スレッドの状態（线程的状态）
     * Java 中的线程一共包含 6 种状态，对应于 java.lang.Thread.State 枚举。
     *
     * 状态名	        含义	    说明
     * NEW	            新建	    线程对象已创建，但未调用 start()。
     * RUNNABLE	        可运行	线程已调用 start()，正在运行或等待 CPU 分配时间。
     * BLOCKED	        阻塞	    等待进入同步块（被其他线程锁住的情况）。
     * WAITING	        等待	    调用了 wait() 或 join() 等待其他线程的通知，无限期等待。
     * TIMED_WAITING	限时等待	等待一定时间，如 sleep()、join(1000)、wait(1000)。
     * TERMINATED	    终止	    线程执行完毕或被异常中断。
     *
     * RUNNABLE：可运行状态（Ready）」，也可能是正在运行中（Running）
     * 线程已经调用了 start()，已经准备好运行，但不一定立刻得到 CPU。
     * 什么时候真正「执行（Running）」是由 操作系统线程调度器 决定的。
     *
     * 状态切换例子图（简略）：
     * NEW → RUNNABLE → (BLOCKED / WAITING / TIMED_WAITING) → RUNNABLE → TERMINATED
     *
     *🧭 スレッドの優先度（线程的优先级）
     *Java 线程优先级用整数表示，范围为：
     * 优先级常量	            值	说明
     * Thread.MIN_PRIORITY	1	最低优先级
     * Thread.NORM_PRIORITY	5	默认优先级
     * Thread.MAX_PRIORITY	10	最高优先级
     * 实行可能状态RUNNABLE，可能有好几个线程（已经实行了start）在排队等待运行（run），通过优先级决定先实行哪个线程
     * 但是线程的实行还依赖OS CPU的分配，所以并不能保证优先度高的一定比优先度低的线程先实行
     *
     *设置优先级：
     * Thread t = new Thread(() -> System.out.println("Hello"));
     * t.setPriority(Thread.MAX_PRIORITY); // 设置为最高
     *☝ 注意：
     * Java 的线程调度是 抢占式 + 优先级建议式；
     * 优先级并不能保证执行顺序，取决于 JVM 和操作系统。
     *
     *
     * 方法名	        所属类	返回类型	功能简述	备注
     * currentThread()	Thread	Thread	获取当前正在执行的线程对象	通常用于获取主线程或子线程
     * getName()	    Thread	String	获取线程名称	可用在日志中标识线程
     * setPriority(int)	Thread	void	设置线程优先度（1~10）	默认是 5，数字大优先度高
     * getPriority()	Thread	int	    获取线程当前优先度
     *
     */
}
