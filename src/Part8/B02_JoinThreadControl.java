package Part8;

/**
 * @author liyanpeng
 * @date 2025/4/30
 * @description TODO
 */

class Counter {
    private int count = 0;

    public synchronized void increment() {
        System.out.println(Thread.currentThread().getName() + " 执行 increment");
        count++;
    }

    public int getCount() {
        return count;
    }
}

public class B02_JoinThreadControl {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();

        Thread threadB = new Thread(() -> {
            counter.increment();
        }, "线程B");

        Thread threadA = new Thread(() -> {
            try {
                threadB.join(); // 等 B 先执行完
                counter.increment();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "线程A");

        threadB.start();  // 先启动 B
        threadA.start();  // A 会等待 B 结束再执行
        /**
         * 线程B 执行 increment
         * 线程A 执行 increment
         */
    }
}
