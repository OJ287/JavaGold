package Part8;

/**
 * @author liyanpeng
 * @date 2025/5/1
 * @description TODO
 */
public class C03_Share {
    // 自动生成 main 方法
    public static void main(String[] args) {
        // TODO
        Share1 share1 = new Share1();

        Thread a = new Thread(new ThreadA_C03(share1));
        Thread b = new Thread(new ThreadB_C03(share1));

        a.start();
        b.start();
        /**
         * 生产：10
         * 消费：10
         * 生产：10
         * 消费：10
         * 生产：10
         * 消费：10
         * 生产：10
         * 消费：10
         * 生产：10
         * 消费：10
         */

    }
}

// 🧪 示例：经典的“生产者-消费者”模型
class Share1 {
    private int data;
    private boolean hasData = false;

    public synchronized void put(int value) throws InterruptedException {
        while (hasData) {
            wait(); // 等消费者消费
        }
        this.data = value;
        hasData = true;
        System.out.println("生产：" + value);
        notify(); // 通知消费者
    }

    public synchronized int get() throws InterruptedException {
        while (!hasData) {
            wait(); // 等待生产者生产
        }
        hasData = false;
        System.out.println("消费：" + data);
        notify(); // 通知生产者
        return data;
    }
}


// synchronized 方法其实是“对象级别”的锁！
// Runnableインタフェースの実装クラス
// 只允许一个线程进入 share1 这个对象的 synchronized 方法（无论是 put 还是 get），而不是“只限制进入某个方法”。
// 不是“只能一个线程进 put，另一个线程可以进 get”
// 如果 put() 和 get() 都是 synchronized，那就必须等另一个线程退出任意一个方法后，另一个才能进入。

class ThreadA_C03 implements Runnable {
    private final Share1 share1;

    public ThreadA_C03(Share1 share1) {
        this.share1 = share1;
    }

    public void run() {     // スレッドが実行する処理
        for (int i = 0; i < 5; i++) {
            try {
                share1.put(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

// Runnableインタフェースの実装クラス
class ThreadB_C03 implements Runnable {
    private final Share1 share1;

    public ThreadB_C03(Share1 share1) {
        this.share1 = share1;
    }

    public void run() {     // スレッドが実行する処理
        for (int i = 0; i < 5; i++) {
            try {
                share1.get();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}