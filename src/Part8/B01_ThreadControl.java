package Part8;

/**
 * @author liyanpeng
 * @date 2025/4/30
 * @description TODO
 */

/**
 * ✅ スレッドの制御方法一覧
 * 方法 / 概念	     说明	                                             注意事项
 * start()	         启动线程，使线程进入 就绪(RUNNABLE) 状态	             不能重复调用，否则抛出异常
 * sleep(ms)	     让当前线程休眠指定毫秒，不释放锁	                     属于 Thread 的静态方法
 * join()	         当前线程等待目标线程执行完毕后再继续	                     可指定超时等待
 * yield()	         当前线程让出 CPU 资源，进入就绪状态	                     不一定马上让出，有操作系统调度决定
 * interrupt()	     中断线程的阻塞状态（如 sleep, join 等），或设置中断标志位	 不会直接停止线程，需要线程主动响应
 * <p>
 * isInterrupted()	 检查线程是否被中断（不清除标志位）	                     实例方法
 * interrupted()	 检查当前线程是否被中断，并清除标志位	                     静态方法
 * wait() / notify() 配合 synchronized 使用，在线程之间协调对象锁的获取与释放	 操作对象的监视器（monitor）
 * synchronized	     实现线程同步，避免资源冲突	                             可用于方法或代码块
 * <p>
 * 🧠 状态转换示意图（简述）
 * NEW → start() → RUNNABLE → RUNNING（操作系统控制）
 * ↘ sleep(), wait(), join() 等 → BLOCKED / WAITING
 * → TERMINATED（执行完毕或异常）
 * <p>
 * 🚫 已废弃的方法（了解即可）
 * stop()：暴力停止线程，已废弃
 * suspend() / resume()：易造成死锁，已废弃
 * <p>
 * 线程状态与控制方法对应关系图
 * <p>
 * [NEW]                          // 新建
 * |
 * | start()
 * v
 * [RUNNABLE]                     // 可运行（等待CPU）
 * |       ^
 * |       | yield()           // 提示线程调度器重新调度
 * |       |
 * |       |
 * v       |
 * [RUNNING]                      // 实际运行中（由操作系统调度）
 * |   |   |
 * |   |   | sleep()           // 暂时停止，指定时间后回到RUNNABLE
 * |   |   | wait()            // 等待其他线程通知
 * |   |   | join()            // 等待其他线程终止
 * |   |   |
 * |   v   v
 * | [BLOCKED/WAITING/TIMED_WAITING] // 不可运行状态
 * |        |
 * |        | notify()/notifyAll()/时间到
 * |        v
 * +----> [RUNNABLE] ←-----------+
 * |
 * | 正常执行完毕或异常
 * v
 * [TERMINATED]                   // 终止（不可再启动）
 * sleep() 和 wait() 都会释放 CPU，但 只有 wait() 会释放对象锁（用于同步通信）。
 * <p>
 * 🔁 状态控制方法总结表
 * <p>
 * 方法	          从状态转移	                             描述
 * start()	      NEW → RUNNABLE	                     启动线程
 * yield()	      RUNNING → RUNNABLE	                 提示调度器让出执行权
 * sleep()	      RUNNING → TIMED_WAITING → RUNNABLE	 睡眠指定时间
 * wait()	      RUNNING → WAITING	                     等待其他线程通知
 * join()	      RUNNING → WAITING/TIMED_WAITING	     等待其他线程结束
 * notify()	      WAITING → RUNNABLE	                 唤醒等待线程
 * interrupt()	  任意状态 → 设置中断标志或抛异常	         中断阻塞线程
 * <p>
 * 🧠 总结口诀：
 * synchronized 控制“进不进”临界区（谁持有锁）。
 * wait() 控制“暂时退出并放锁”。
 * notify() 控制“唤醒别人，但自己还在用锁”。
 * join() 是等线程结束，不管锁。
 * yield() 让出 CPU，不让出锁。
 * interrupt() 是打断状态，不打断锁。
 */

public class B01_ThreadControl {
    public static void main(String[] args) throws InterruptedException {
        MyThread thread = new MyThread();
        thread.start();


        System.out.println("main线程start...");
        // 等待 2 秒钟后中断线程
        Thread.sleep(2000);
        System.out.println("main线程end...");
        thread.interrupt();
        /**
         * main线程start...
         * MyThread线程运行中...
         * MyThread线程运行中...
         * main线程end...
         * MyThread线程被中断，退出...
         *
         * or
         *
         * MyThread线程运行中...
         * main线程start...
         * MyThread线程运行中...
         * main线程end...
         * MyThread线程被中断，退出...
         *
         * 也就是说这个问题是因为在 main() 方法中调用 thread.start() 后，MyThread 线程是异步执行的，
         * 意味着它会在主线程 (main 线程) 启动后立即开始执行，
         * 但并不保证它会在主线程的 System.out.println("main线程start..."); 语句之后立刻输出。
         * Java 线程调度的顺序是由操作系统的线程调度器控制的，并且在多线程环境下，
         * 线程的执行顺序是不确定的，特别是在并发执行时，主线程和子线程可能会交替执行。
         *
         *Java 中多线程的执行顺序是由线程调度器控制的，主线程和子线程之间的执行顺序是不可预测的，
         * 因此即使 MyThread 线程是在 main 线程调用 start() 之前启动的，
         * 它的输出可能会在 main 线程的输出之后，具体顺序取决于操作系统的线程调度。
         *
         */
    }
}

class MyThread extends Thread {
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                System.out.println("MyThread线程运行中...");
                Thread.sleep(1000);  // 模拟任务执行
            } catch (InterruptedException e) {
                System.out.println("MyThread线程被中断，退出...");
                break;
            }
        }
    }
}
