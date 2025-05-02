package Part8;

/**
 * @author liyanpeng
 * @date 2025/5/1
 * @description TODO
 */
public class C02_synchronized {
    // 自动生成 main 方法
    public static void main(String[] args) {
        // TODO
    }
}

//✅ 示例一：同步实例方法（锁的是 this）
class Counter1 {
    private int count = 0;

    public synchronized void increment() {
        count++;  // 进入方法时会加锁
    }

    public int getCount() {
        return count;
    }
}
//多个线程调用 increment() 时，会自动加锁，只有一个线程能同时执行该方法。


// ✅ 示例二：同步代码块（可以指定锁对象）
class Counter2 {
    private final Object lock = new Object();
    private int count = 0;

    public void increment() {
        synchronized (lock) {
            count++;
        }
    }
}
//这里我们使用 lock 对象作为锁，灵活控制哪个代码块被排他保护。


// ✅ 示例三：同步静态方法（锁的是类对象）
class Counter3 {
    private static int staticCount = 0;

    public static synchronized void incrementStatic() {
        staticCount++;
    }
}
// 这适用于静态资源（类共享资源）的同步控制，锁住的是 Counter.class 类对象。


// 两者都是 对 Share.class 加锁，多个线程只能有一个线程进入临界区。
class Counter4 {
    // 类锁方式一
    public static synchronized void methodA() {
        // do something
    }

    // 类锁方式二
    public static void methodB() {
        synchronized (Share.class) {
            // do something
        }
    }
}
// *Share.class 是 Share 类的 Class对象，即 Class<Share>。
//        *所以这个 synchronized 是对整个 类对象 加锁。
//        *无论多少个线程，只要它们都试图进入这个同步块（或其他也使用 Share.class 加锁的地方），都必须争抢这把类锁。
//        *与实例锁的区别
// *锁类型	     示例	                     说明
// * 实例锁	     synchronized(this)	         每个实例一把锁，多个实例互不影响
// * 类锁	     synchronized(Share.class)	 所有线程共享同一把锁，不管几个实例
