package Part8;

/**
 * @author liyanpeng
 * @date 2025/5/1
 * @description TODO
 */

/**
 * +------------------------+----------------------------------------+-------------------------------------------------------------------+
 * | 类/接口名称            | 类型                                   | 说明                                                              |
 * +------------------------+----------------------------------------+-------------------------------------------------------------------+
 * | Executor               | 接口                                   | 执行任务的基本接口，定义了 `execute(Runnable command)` 方法，用于提交任务。     |
 * | ExecutorService        | 接口                                   | 扩展 `Executor`，提供了更多任务管理功能。包括任务提交、任务结束、服务关闭等方法。|
 * | Future                 | 接口                                   | 表示一个异步计算的结果，允许获取任务的返回值，并支持取消任务。                     |
 * | Callable               | 接口                                   | 与 `Runnable` 类似，但允许任务返回值，并且可以抛出异常。                            |
 * | Executors              | 工具类                                 | 提供了多种创建常用线程池的静态方法。通过此类可以轻松创建常见的线程池实例。            |
 * +------------------------+----------------------------------------+-------------------------------------------------------------------+
 * <p>
 * Executorsクラスの主なメソッド
 * 方法名                                      | 返回类型                  | 说明
 * -------------------------------------------|---------------------------|---------------------------------------------------------
 * newSingleThreadExecutor()                  | ExecutorService           | 创建单线程执行器，所有任务按顺序执行
 * newFixedThreadPool(nThreads)               | ExecutorService           | 创建固定线程数的线程池，适用于负载稳定的场景
 * newCachedThreadPool()                      | ExecutorService           | 创建可动态扩展线程数的线程池，适合高并发、短生命周期任务
 * newSingleThreadScheduledExecutor()         | ScheduledExecutorService  | 单线程版定时任务线程池，适用于需要顺序执行定时任务的场景
 * newScheduledThreadPool(corePoolSize)       | ScheduledExecutorService  | 创建固定线程数的定时任务线程池，支持周期性执行任务
 * callable(Runnable task)                    | Callable<Object>          | 将 Runnable 转换为 Callable，返回值为 null
 * callable(Runnable task, T result)          | Callable<T>               | 将 Runnable 转换为 Callable，执行后返回指定的 result
 * <p>
 * 注意事项：
 * 返回类型均为接口，实际实现类在内部，如：ThreadPoolExecutor 或 ScheduledThreadPoolExecutor。
 * 例如：
 * Executors.newFixedThreadPool(int)
 * 返回类型是 ExecutorService（接口）
 * 实际上返回的是 ThreadPoolExecutor（类）
 * Executors.newScheduledThreadPool(int)
 * 返回类型是 ScheduledExecutorService（接口）
 * 实际上返回的是 ScheduledThreadPoolExecutor（类）
 * newCachedThreadPool() 的线程可能无限增长，需注意任务量控制。
 * newSingleThreadExecutor() 适合需要顺序执行的场合。
 * newScheduledThreadPool() 可替代 Timer 实现定时任务。
 * <p>
 * <p>
 * ExecutorServiceインタフェースの主なメソッド
 * +============================+==========================================+===============================================+
 * | 方法名                     | 返回值类型                               | 说明                                          |
 * +============================+==========================================+===============================================+
 * | awaitTermination(time, u)  | boolean    throws InterruptedException    | 最多等待指定时间，直到任务全部完成或超时      |
 * | isShutdown()               | boolean                                   | 判断是否调用了 shutdown()                     |
 * | isTerminated()             | boolean                                   | 判断是否所有任务都已完成                      |
 * | shutdown()                 | void                                      | 启动有序关闭，已提交任务执行，不接受新任务    |
 * | shutdownNow()              | List<Runnable>                            | 尝试停止所有任务，返回尚未开始的任务列表      |
 * +----------------------------+------------------------------------------+-----------------------------------------------+
 * | submit(Callable<T> task)   | Future<T>                                 | 提交带返回值的任务，异步执行，返回 Future     |
 * | submit(Runnable task)      | Future<?>                                 | 提交无返回值的任务，返回 Future 对象          |
 * | submit(Runnable, T result) | Future<T>                                 | 提交任务，执行后(正常执行完了)返回指定的结果                |
 * +----------------------------+------------------------------------------+-----------------------------------------------+
 * | execute(Runnable command)  | void                                      | 立即执行任务，无返回值                        |
 * +-----下面不在书上-------------+------------------------------------------+-----------------------------------------------+
 * | invokeAll(Collection)      | List<Future<T>>                           | 执行一组任务，等待所有完成                    |
 * | invokeAny(Collection)      | T                                         | 执行一组任务，任一完成即返回其结果           |
 * +============================+==========================================+===============================================+
 * <p>
 * <p>
 * Futureインタフェースの主なメソッド
 * +--------------------------------------------------+-------------------------------------------+-----------------------------------------+-----------------------------------------------+
 * | 返回值                                           | 方法名                                      | 说明                                    | 可能抛出的异常                               |
 * +--------------------------------------------------+-------------------------------------------+-----------------------------------------+-----------------------------------------------+
 * | `true` 如果任务成功取消；`false` 如果任务无法取消   | boolean cancel(boolean mayInterruptIfRunning) | 尝试取消任务。如果1任务尚未运行  2任务正在运行且 `mayInterruptIfRunning` 为 `true`，则中断任务；如果任务已经完成，返回 `false`。 | 无                                            |
 * | `true` 如果任务已被取消；`false` 如果任务未被取消   | boolean isCancelled()                       | 返回任务是否已被取消。                        | 无                                            |
 * | `true` 如果任务已完成；`false` 如果任务未完成      | boolean isDone()                            | 返回任务是否已完成。                          | 无                                            |
 * | 任务的执行结果                                   | V get()                                     | 阻塞当前线程直到任务完成，返回任务的结果。              | InterruptedException, ExecutionException      |
 * | 任务的执行结果                                   | V get(long timeout, TimeUnit unit)          | 阻塞当前线程直到任务完成或超时，返回任务的结果。        | InterruptedException, ExecutionException, TimeoutException |
 * +--------------------------------------------------+-------------------------------------------+-----------------------------------------+-----------------------------------------------+
 */

import java.util.concurrent.*;

public class F01_Executor {
    // 自动生成 main 方法
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // TODO
        // 创建一个固定大小为3的线程池
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        // 提交一个Runnable任务，使用execute()
        executorService.execute(new RunnableTask());

        // 不需要获取任务执行结果，因为execute()不会返回Future
        System.out.println("Runnable task has been submitted.");

        // 关闭线程池
        executorService.shutdown();
        /**
         * Runnable task has been submitted.
         * Executing Runnable task in thread: pool-2-thread-1
         */


        // 1. 创建线程池. submit提交任务
        ExecutorService executorService1 = Executors.newFixedThreadPool(3);  // 创建一个固定大小为3的线程池

        // 2. 提交任务
        // 提交一个Runnable任务
        executorService1.submit(new RunnableTask());

        // 提交一个Callable任务，并获取Future
        Future<Integer> future = executorService1.submit(new CallableTask());

        // 3. 获取任务结果（如果是Callable任务）
        Integer result = future.get();  // 阻塞，直到任务执行完成并返回结果
        System.out.println("Callable task result: " + result);

        // 4. 关闭线程池
        executorService1.shutdown();  // 阻止新任务提交，待所有任务完成后会关闭线程池
        /**
         * Executing Callable task in thread: pool-1-thread-2
         * Executing Runnable task in thread: pool-1-thread-1
         * Callable task result: 42
         */

//ExecutorService的newSingleThreadExecutor使用
        ExecutorService service = null;
        try {
            service = Executors.newSingleThreadExecutor();
            System.out.println("service.execute()");
            for (int i = 0; i < 3; i++) {
                // 实行三回execute，前回没实行完的话不会开始下一次execute
                // newSingleThreadExecutor：一つのスレッドでタスク処理を行うExecutorServiceオブジェクトを使用する
                // 参照下面说明：ExecutorService 生命周期（线程池执行多个任务）
                service.execute(() -> {
                    System.out.print("thread task");
                    for (int a = 0; a < 5; a++) {
                        try {
                            Thread.sleep(500);
                            System.out.print(" * ");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println();
                });
            }
        } finally {
            service.shutdown(); // // ExecutorServiceの終了
            System.out.println("ex.shutdown()");
        }
        /**
         * service.execute()
         * ex.shutdown()
         * thread task *  *  *  *  *
         * thread task *  *  *  *  *
         * thread task *  *  *  *  *
         */


//ExecutorService的submit使用返回Future对象
        //本次使用两个submit方法，是返回实行结果，正常完了可以被确认
//        但有时候想返回タスクが処理した結果（計算した合計値など）。就要使用sumit（Callable）：参照F02_Callable

        ExecutorService service1 = null;
        try {
            service1 = Executors.newSingleThreadExecutor();
            Future<?> result1 =
                    service1.submit(() -> System.out.println("hello"));
            System.out.println(result1.get());
            Future<Boolean> result2 =
                    service1.submit(() -> System.out.println("hello"), true);
            System.out.println(result2.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            if (service1 != null) service1.shutdown();
        }
        /**
         * hello
         * null
         * hello
         * true
         */
    }
}

class RunnableTask implements Runnable {
    @Override
    public void run() {
        System.out.println("Executing Runnable task in thread: " + Thread.currentThread().getName());
    }
}

// Callable任务示例
class CallableTask implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("Executing Callable task in thread: " + Thread.currentThread().getName());
        return 42;  // 返回一个计算结果
    }
}

/**
 *
 *ExecutorService 生命周期（线程池执行多个任务）
 * 初始化状态
 * +--------------------------------------------------------+
 * | 状态：                                                 |
 * |   isShutdown()   == false                              |
 * |   isTerminated() == false                              |
 * | 行为：                                                 |
 * |   可提交新任务（execute(), submit(), invokeXxx 等）    |
 * +--------------------------------------------------------+
 *           |
 *           v
 *
 * 提交多个任务
 * +--------------------------------------------------------+
 * | 行为：                                                 |
 * |   线程池调度执行任务                                   |
 * | 状态未变化                                             |
 * |   isShutdown()   == false                              |
 * |   isTerminated() == false                              |
 * +--------------------------------------------------------+
 *           |
 *           v
 *
 * 调用 shutdown()
 * +--------------------------------------------------------+
 * | 行为：                                                 |
 * |   不再接受新任务                                       |
 * |   已提交任务继续执行                                   |
 * | 状态变更：                                             |
 * |   isShutdown()   == true   ← 标志线程池已关闭入口      |
 * |   isTerminated() == false                              |
 * +--------------------------------------------------------+
 *           |
 *           v
 *
 * 等待任务执行完成
 * +--------------------------------------------------------+
 * | 所有已提交任务执行完毕                                |
 * | 所有工作线程终止                                       |
 * | 状态变更：                                             |
 * |   isShutdown()   == true                               |
 * |   isTerminated() == true   ← 所有任务和线程都终止了    |
 * +--------------------------------------------------------+
 *
 *           ▲
 *           │
 *       可选调用：
 *       awaitTermination(timeout, unit)
 *       → 用于等待所有任务结束
 *       → 返回 true：已终止
 *       → 返回 false：超时仍未终止
 *
 */