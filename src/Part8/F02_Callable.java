package Part8;

/**
 * @author liyanpeng
 * @date 2025/5/2
 * @description TODO
 */

import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * submit(Callable<T>) 是为了 在线程池中执行任务，并能获取返回值或处理异常，相比于 execute(Runnable)，它有以下优势：
 * <p>
 * ✅ submit(Callable<T>) 的用途和优势：
 * 特点	                说明
 * ✅ 有返回值	        可以通过返回的 Future<T> 获取执行结果。例如：String result = future.get();
 * ✅ 支持抛出异常	    Callable.call() 可以抛出 checked exception，异常会封装在 Future.get() 中抛出。
 * ✅ 异步执行	        submit() 方法会立即返回一个 Future，主线程可做其他事，再获取结果。
 * ✅ 任务调度控制更灵活	可配合 Future.cancel() 实现任务中断。
 * <p>
 * <p>
 * 🧩 Callable 接口方法简述，是一个函数接口関数型インタフェース
 * 　　　T call() throws Exception;
 * 项目	　　　　　说明
 * 返回值  　　　　T —— 方法执行的返回结果，可为任意类型。
 * 抛出异常	     可抛出受检异常（throws Exception），这是 Runnable.run() 不具备的
 * 用途	         配合 ExecutorService.submit() 提交任务，异步执行后取得结果
 */
public class F02_Callable {
    // 自动生成 main 方法
    public static void main(String[] args) {
        // TODO

        ExecutorService service = null;
        try {
            service = Executors.newSingleThreadExecutor();
            Future<Date> result =
                    service.submit(() -> new Date());
            System.out.println(result.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            if (service != null) service.shutdown();
        }
        /**
         *Fri May 02 00:43:02 JST 2025
         */
    }
}