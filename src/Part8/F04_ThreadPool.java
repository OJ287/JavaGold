package Part8;

/**
 * @author liyanpeng
 * @date 2025/5/2
 * @description TODO
 */

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class F04_ThreadPool {
    // 自动生成 main 方法
    public static void main(String[] args) throws InterruptedException {
        // TODO
        ExecutorService service = null;
        try {
//            service = Executors.newCachedThreadPool();
            service = Executors.newFixedThreadPool(2);
            Runnable task = () -> {
                String name = Thread.currentThread().getName();
                System.out.println(name + " : start");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(name + " : end");
            };
            for (int i = 0; i < 5; i++) {
                service.execute(task);
            }
        } finally {
            if (service != null) service.shutdown();
        }
        /**
         * newCachedThreadPool:
         * pool-1-thread-5 : start
         * pool-1-thread-4 : start
         * pool-1-thread-2 : start
         * pool-1-thread-1 : start
         * pool-1-thread-3 : start
         * pool-1-thread-5 : end
         * pool-1-thread-3 : end
         * pool-1-thread-4 : end
         * pool-1-thread-1 : end
         * pool-1-thread-2 : end
         */
        /**
         * newFixedThreadPool:
         * pool-1-thread-2 : start
         * pool-1-thread-1 : start
         * pool-1-thread-1 : end
         * pool-1-thread-2 : end
         * pool-1-thread-2 : start
         * pool-1-thread-1 : start
         * pool-1-thread-2 : end
         * pool-1-thread-2 : start
         * pool-1-thread-1 : end
         * pool-1-thread-2 : end
         */


        ExecutorService service2 = null;
        try {
            service2 = Executors.newFixedThreadPool(4);
            CyclicBarrier barrier = new CyclicBarrier(2,
                    () -> System.out.println("task "));
            for (int i = 0; i < 4; i++) {
                service2.execute(() -> new F04_ThreadPool().exec(barrier));
            }
        } finally {
            if (service2 != null) service2.shutdown();
        }
        /**
         * pool-1-thread-3start
         * pool-1-thread-1start
         * pool-1-thread-4start
         * pool-1-thread-2start
         * task
         * pool-1-thread-1end
         * pool-1-thread-2end
         * task
         * pool-1-thread-3end
         * pool-1-thread-4end
         */
        /**
         * for(int i = 0; i < 3; i++){
         * 如果这里是3，四个线程3次提交任务的话
         * 前两个会达到规定线程数通过barrierPoint
         * 但还有一个线程，是怎么也达不到规定的2线程，就会一直等待线程会一直阻塞
         * 如果 CyclicBarrier 被中断或有异常处理的机制，可能会抛出 BrokenBarrierException。
         * pool-1-thread-3start
         * pool-1-thread-1start
         * pool-1-thread-4start
         * pool-1-thread-2start
         * task
         * pool-1-thread-1end
         * pool-1-thread-2end
         *
         */
    }

    void exec(CyclicBarrier barrier) {
        try {
            System.out.println(Thread.currentThread().getName() + "start");
            Thread.sleep((int) (Math.random() * 3000));
            barrier.await();
            System.out.println(Thread.currentThread().getName() + "end");
        } catch (BrokenBarrierException | InterruptedException e) {
        }
    }
}