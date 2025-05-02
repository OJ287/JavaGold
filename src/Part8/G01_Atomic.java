package Part8;

/**
 * @author liyanpeng
 * @date 2025/5/2
 * @description TODO
 */

/**
 * 在 Java 中，Atomic 是一个用于原子操作的概念，主要用于处理共享数据时的并发问题。
 * java.util.concurrent.atomic 包提供了一组类，用于实现线程安全的原子操作。
 * <p>
 * <p>
 * 1. 原子操作的概念
 * 原子操作是指在执行过程中不会被中断的操作。
 * 这意味着一个原子操作要么完全执行，要么完全不执行，不会出现部分完成的情况。
 * 原子性保证了操作的完整性，避免了并发操作时出现的问题。
 * <p>
 * 例如：
 * 非原子操作：读取一个共享变量 x，然后增加其值，最后再存储回 x。
 * 如果两个线程同时执行此操作，可能会读取到相同的 x 值并覆盖对方的修改，导致数据不一致。
 * 原子操作：原子操作则可以确保这类操作是不可分割的，不会被其他线程干扰。
 * <p>
 * <p>
 * 以下是 java.util.concurrent.atomic 包中常用类的整理：
 * +----------------------------+-----------------------------------------------------------+
 * | 类名                       | 说明                                                      |
 * +----------------------------+-----------------------------------------------------------+
 * | AtomicBoolean               | 提供对布尔值的原子操作，支持 `get()`、`set()` 和 `compareAndSet()` |
 * | AtomicInteger               | 提供对 `int` 类型的原子操作，支持常见的加、减、修改等操作    |
 * | AtomicLong                  | 提供对 `long` 类型的原子操作，支持常见的加、减、
 * 修改等操作  |
 * | AtomicReference<T>          | 提供对引用类型（例如对象）的原子操作                        |
 * <p>
 * 以下不在教科书内
 * | AtomicIntegerArray          | 提供对 `int` 数组元素的原子操作                             |
 * | AtomicLongArray             | 提供对 `long` 数组元素的原子操作                            |
 * | AtomicMarkableReference<T>  | 提供对引用类型的原子操作，并支持一个标志位                   |
 * | AtomicStampedReference<T>   | 提供对引用类型的原子操作，并支持一个时间戳（版本）标记      |
 * +----------------------------+-----------------------------------------------------------+
 * <p>
 * <p>
 * AtomicInteger 的常用方法一览表
 * +---------------------------------------------+------------------------------+---------------------------+
 * | 方法                                        | 说明                         | 返回值                    |
 * +---------------------------------------------+------------------------------+---------------------------+
 * | final int addAndGet(int delta)              | 加 delta 并返回新值          | 新值                      |
 * | final boolean compareAndSet(int expect, int update) | 如果当前值为期望值，则设为新值 | true/false              |
 * | final int incrementAndGet()                 | 自增 1 并返回新值            | 新值                      |
 * | final int get()                             | 获取当前值                   | 当前值                    |
 * | final int getAndIncrement()                 | 返回当前值并自增 1           | 原值                      |
 * | 以下不在教科书                             |                              |                           |
 * | final void set(int newValue)                | 设置为指定的新值             | 无                        |
 * | final int getAndSet(int newValue)           | 设置新值，并返回旧值         | 原值                      |
 * | final int getAndDecrement()                 | 返回当前值并自减 1           | 原值                      |
 * | final int decrementAndGet()                 | 自减 1 并返回新值            | 新值                      |
 * | final int getAndAdd(int delta)              | 返回当前值并加 delta         | 原值                      |
 * | final int updateAndGet(IntUnaryOperator update) | 用函数更新值并返回新值       | 新值                      |
 * | final int getAndUpdate(IntUnaryOperator update) | 用函数更新值并返回旧值       | 原值                      |
 * +---------------------------------------------+------------------------------+---------------------------+
 */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class IntegerTest {
    private final AtomicInteger atomicInteger;
    private Integer syncInteger;

    public IntegerTest() {
        syncInteger = 0;
        atomicInteger = new AtomicInteger(0);
    }

    synchronized public void addSyncInteger() {
        syncInteger++;
    }

    public void addAtomicInteger() {
        atomicInteger.getAndIncrement();
    }

    public void showData() {
        System.out.println("syncInt   : " + syncInteger);
        System.out.println("atomicInt : " + atomicInteger.get());
    }
}

public class G01_Atomic {
    // 自动生成 main 方法
    public static void main(String[] args)
            throws InterruptedException {
        IntegerTest obj = new IntegerTest();
        exec(() -> obj.addSyncInteger());
        exec(() -> obj.addAtomicInteger());
        obj.showData();
    }

    private static void exec(Runnable task)
            throws InterruptedException {
        ExecutorService service = null;
        try {
            service = Executors.newFixedThreadPool(100);
            for (int i = 0; i < 10000; i++) {
                service.submit(task);
            }
            service.awaitTermination(10, TimeUnit.SECONDS);
        } finally {
            if (service != null) service.shutdown();
        }
    }
}