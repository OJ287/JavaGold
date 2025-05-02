package Part8;

/**
 * @author liyanpeng
 * @date 2025/4/30
 * @description TODO
 */

/**
 * 1. 排他制御（排他制御 / Exclusive Control）
 * 排他制御，也叫互斥控制，是指在多线程环境中，同一时刻只允许一个线程访问某个共享资源，
 * 其他线程必须等待该资源释放后才能访问。
 * 这种机制能够有效避免多个线程并发访问共享资源时引发的数据竞争（race condition）和一致性问题。
 * <p>
 * 关键概念：
 * 锁（Lock）：为了实现排他制御，通常使用锁来控制对共享资源的访问。
 * 在 Java 中，可以使用 synchronized 关键字、ReentrantLock 等工具来实现锁机制。
 * <p>
 * 临界区（Critical Section）：临界区是指多个线程可能同时访问的代码区域，它通常包含访问共享资源的部分。
 * 通过排他制御确保同一时刻只有一个线程能够进入临界区。
 * <p>
 * <p>
 * 使用场景：
 * 银行账户的余额管理：多个线程对同一个账户进行存取款操作时，必须保证操作的原子性，避免多个线程同时修改账户余额，导致余额错误。
 * 线程安全的集合类：在多线程环境下，访问和修改集合类（如 List、Map）时，必须使用排他控制，确保没有线程在同时对集合进行修改。
 * <p>
 * class Counter {
 * private int count = 0;
 * <p>
 * public synchronized void increment() {
 * count++;  // 只有一个线程可以在此时修改 count
 * }
 * <p>
 * public int getCount() {
 * return count;
 * }
 * }
 * 在这个例子中，increment 方法使用了 synchronized 关键字，保证在同一时刻只有一个线程可以执行 increment 方法。
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * 2. 同期制御（同期制御 / Synchronization）
 * 同期制御是指在多线程程序中，对多个线程的执行进行协调，确保它们按照特定的顺序或条件执行。
 * 它主要解决线程之间的协调与通信问题，通常用来处理线程之间的依赖关系或等待条件。
 * <p>
 * 关键概念：
 * 等待与通知：通过 wait()、notify() 和 notifyAll() 方法，
 * 线程可以在特定的条件下等待或通知其他线程，达到同步的效果。比如一个线程等待另一个线程完成某个操作后再执行。
 * <p>
 * 死锁（Deadlock）：多个线程相互等待对方释放资源，导致所有线程都无法继续执行，这也是需要注意的同步问题之一。
 * <p>
 * 使用场景：
 * 生产者-消费者问题：一个线程负责生产数据，另一个线程负责消费数据，两个线程之间需要通过同步机制来协调生产和消费的顺序。
 * 线程池的管理：线程池中的线程可能需要协调执行任务的顺序，确保任务按特定规则执行。
 * <p>
 * class SharedData {
 * private int data = 0;
 * <p>
 * public synchronized void increment() {
 * data++;
 * notify();  // 唤醒等待的线程
 * }
 * <p>
 * public synchronized void waitForData() throws InterruptedException {
 * while (data == 0) {
 * wait();  // 等待直到数据不为 0
 * }
 * System.out.println("Data: " + data);
 * }
 * }
 * <p>
 * public class Example {
 * public static void main(String[] args) {
 * SharedData sharedData = new SharedData();
 * <p>
 * Thread producer = new Thread(() -> {
 * try {
 * Thread.sleep(1000);  // 模拟一些工作
 * sharedData.increment();
 * } catch (InterruptedException e) {
 * Thread.currentThread().interrupt();
 * }
 * });
 * <p>
 * Thread consumer = new Thread(() -> {
 * try {
 * sharedData.waitForData();
 * } catch (InterruptedException e) {
 * Thread.currentThread().interrupt();
 * }
 * });
 * <p>
 * producer.start();
 * consumer.start();
 * }
 * }
 * 在这个例子中，consumer 线程调用 waitForData 方法时，如果数据为 0，它会进入等待状态，直到 producer 线程调用 increment 方法并通知它。
 * <p>
 * <p>
 * 排他制御与同期制御的区别：
 * 特点	        排他制御（互斥控制）	                         同期制御（同步控制）
 * 目的	        确保同一时刻只有一个线程访问共享资源	             协调多个线程的执行顺序或条件
 * 机制	        使用锁来确保对资源的独占访问（如 synchronized）	 使用 wait()、notify() 等方法来协调线程间的执行
 * 关注点        资源的独占访问，防止数据竞争	                     线程之间的协作与通信
 * 主要应用场景	多线程并发操作共享数据	                         多线程协调、线程间的通信和等待机制
 * <p>
 * <p>
 * 小结：
 * 排他制御 主要解决的是并发访问资源时的互斥问题，确保在同一时刻只有一个线程访问资源，避免资源冲突和数据不一致。
 * 同期制御 主要是线程之间的协调与同步，解决的是多个线程之间的执行顺序和通信问题。
 * 这两者通常是一起使用的，排他制御保证数据的一致性，而同步制御保证多个线程之间的协调与通信。
 */
public class C01_ExclusiveControl_Synchronization {
    // 自动生成 main 方法
    public static void main(String[] args) {
        Share share = new Share();
        ThreadAC01 threadA = new ThreadAC01(share);
        ThreadBC01 threadB = new ThreadBC01(share);
        threadA.start();
        threadB.start();
    }

}

class Share {
    private int a = 0;
    private String b;

    public void set() {
        a++;
        b = "data";
        System.out.println("set() a : " + a + " b: " + b);
    }

    public void print() {
        a--;
        b = null;
        System.out.println(" print() a : " + a + " b: " + b);
    }
}

class ThreadAC01 extends Thread {
    private final Share share;

    public ThreadAC01(Share share) {
        this.share = share;
    }

    public void run() {
        for (int i = 0; i < 5; i++) {
            share.set();
        }
    }
}

class ThreadBC01 extends Thread {
    private final Share share;

    public ThreadBC01(Share share) {
        this.share = share;
    }

    public void run() {
        for (int i = 0; i < 5; i++) {
            share.print();
        }
    }
}

/**
 * 🧩 synchronized 的基本用法
 * synchronized 可以用于：
 * 位置	    说明
 * 实例方法	锁的是当前对象（this）
 * 静态方法	锁的是当前类的 Class 对象
 * 代码块	可以指定锁对象，更灵活
 * <p>
 * <p>
 * 🔄 synchronized 排他制御的运行流程
 * 线程 A ——> 请求进入同步代码块 ——> 获取锁 ——> 执行代码 ——> 释放锁
 * 线程 B ——> 请求进入同步代码块 ——> 被阻塞（等待锁） ——> 等待 A 释放锁 ——> 获取锁后执行
 * 只有一个线程可以持有该锁，其他线程会被阻塞。
 * <p>
 * <p>
 * ⚠️ 注意事项
 * 不要对常量或字符串（如 "lock"）使用同步锁，容易被其他代码共用，造成死锁。
 * synchronized 会带来性能损耗，应避免不必要的同步。
 * synchronized 不能中断被阻塞的线程（例如等待获取锁时），只能等锁释放。
 * <p>
 * <p>
 * 📌 可视化示意图（两个线程争夺锁）
 * 共享资源: synchronized increment()
 * 线程 A       线程 B
 * |             |
 * |  请求锁     | 请求锁（等待）
 * |<----------->|
 * |   执行中    |   阻塞中
 * |-------------|
 * |   释放锁    |
 * |             | 获取锁
 * | 执行中
 */

/**
 *wait()、wait(time)、notify() 和 notifyAll() 的同期制御
 *🔧 它们属于哪类机制？
 * 这几个方法属于：
 * 对象级别的线程协作机制（スレッド間協調）
 * 都是 java.lang.Object 类的方法（不是 Thread 的方法）
 * 必须配合 synchronized 使用，否则会抛出 IllegalMonitorStateException
 *
 *🔁 四个方法的基本作用
 *
 * 方法	        作用说明
 * wait()	    当前线程进入 等待状态（WAITING），直到被其他线程通过 notify() / notifyAll() 唤醒。
 * wait(time)	当前线程进入 限时等待状态（TIMED_WAITING），超过时间后自动苏醒。
 * notify()	    唤醒 一个 正在该对象上等待的线程（随机选择一个）
 * notifyAll()	唤醒 所有 正在该对象上等待的线程（全部变为 RUNNABLE，参与竞争）
 *
 *
 *🚦 使用限制（非常重要）
 * 必须写在 synchronized(obj) 块中，对象 obj 是锁对象：
 *synchronized (lock) {
 *     lock.wait();
 *     // 或 lock.notify(); lock.notifyAll();
 * }
 *
 *如果不加 synchronized，会抛出：
 *java.lang.IllegalMonitorStateException
 *
 *
 *⏱ 状态转移关系
 * 以下是使用 wait/notify 时，线程状态的大致变化：
 *[RUNNABLE]
 *     ↓ (获得锁，执行中)
 * [TIMED_WAITING / WAITING] ←── wait() / wait(time)
 *     ↑
 * [RUNNABLE] ←── notify() / notifyAll()
 *
 *🧪 示例：经典的“生产者-消费者”模型:C03_Share.java
 *
 *
 *🧠 小结对比
 *
 * 方法	        用途	            状态变化	            说明
 * wait()	    释放锁并等待	    → WAITING	        要配合 synchronized 使用
 * wait(ms)	    释放锁并限时等待	→ TIMED_WAITING	    超时后自动唤醒
 * notify()	    唤醒一个等待线程	WAITING → RUNNABLE	谁醒不确定
 * notifyAll()	唤醒所有等待线程	WAITING → RUNNABLE	大家一起抢锁
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */