package Part8;

/**
 * @author liyanpeng
 * @date 2025/5/2
 * @description TODO
 */

/**
 * タスクスケジューラ（Task Scheduler） 主要指的是通过 ScheduledExecutorService 接口提供的定时与周期任务调度机制，
 * 它是 ExecutorService 的子接口，属于 java.util.concurrent 包中的一部分，
 * 用于管理 定时执行（delay） 或 周期执行（interval） 的任务。
 * <p>
 * <p>
 * ✅ ScheduledExecutorService とは（是什么）
 * public interface ScheduledExecutorService extends ExecutorService
 * 它支持以下功能：
 * ✅ 指定延迟后执行一次任务（One-time delayed execution）
 * ✅ 周期性地执行任务（Fixed-rate / Fixed-delay）
 * <p>
 * <p>
 * ScheduledExecutorService 的主要方法一览表
 * +----------------------------+---------------------------------------------------------------------------------------------+--------------------------------------------------------------+
 * | 返回值类型                 | 方法签名                                                                                   | 说明                                                         |
 * +----------------------------+---------------------------------------------------------------------------------------------+--------------------------------------------------------------+
 * | ScheduledFuture<?>         | schedule(Runnable command, long delay, TimeUnit unit)                                      | 延迟指定时间后执行一次任务                                   |
 * | ScheduledFuture<V>         | schedule(Callable<V> callable, long delay, TimeUnit unit)                                  | 延迟指定时间后执行一次带返回值的任务                         |
 * | ScheduledFuture<?>         | scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit)       | 初始延迟后以固定频率重复执行任务（不等待上一次是否完成）     |
 * | ScheduledFuture<?>         | scheduleWithFixedDelay(Runnable command, long initialDelay, long delay, TimeUnit unit)     | 初始延迟后每次任务完成后再延迟执行下一次（不会任务重叠）     |
 * +----------------------------+---------------------------------------------------------------------------------------------+--------------------------------------------------------------+
 * Fixed方法的第一参数是Runcale没有callable
 */

import java.util.Date;
import java.util.concurrent.*;

public class F03_ScheduledExecutorService {
    // 自动生成 main 方法
    public static void main(String[] args) {
        ScheduledExecutorService service = null;
        try {
            service = Executors.newSingleThreadScheduledExecutor();
            Runnable task1 = () -> System.out.println("task1");
            Callable<Date> task2 = () -> new Date();

            ScheduledFuture<?> result1 =
                    service.schedule(task1, 3, TimeUnit.SECONDS);
            ScheduledFuture<Date> result2 =
                    service.schedule(task2, 1, TimeUnit.MILLISECONDS);
            System.out.println(result2.get());

            /**
             * Fri May 02 14:49:34 JST 2025
             * task1
             */
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            if (service != null) service.shutdown();
        }


        ScheduledExecutorService service1 = null;
        try {
            service1 = Executors.newSingleThreadScheduledExecutor();
            Runnable task = () -> System.out.println(new Date());
            service1.scheduleWithFixedDelay(task, 2, 2, TimeUnit.SECONDS);
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (service1 != null) service1.shutdown();
        }

        /**
         * Fri May 02 14:54:41 JST 2025
         * Fri May 02 14:54:43 JST 2025
         * Fri May 02 14:54:45 JST 2025
         * Fri May 02 14:54:47 JST 2025
         */
    }
}