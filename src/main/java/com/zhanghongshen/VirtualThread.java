package com.zhanghongshen;

import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class VirtualThread {

    static volatile int i = 0;

    public static void main(String[] args) {
        // single virtual thread
        Thread.ofVirtual().name("virtual-thread-my").start(() -> {
            System.out.println(Thread.currentThread().getName()+":walk");
        });


        // virtual thread pool
        final ThreadFactory factory = Thread.ofVirtual().name("virtual-thread-", 0).factory();
        try(ExecutorService executor = Executors.newThreadPerTaskExecutor(factory)){
            ReentrantLock lock = new ReentrantLock();
            for(; i < 1000; i++){
                lock.lock();
                final int temp = i;
                executor.submit(()->{
                    System.out.println(Thread.currentThread().getName()+":task"+ temp);
                    try {
                        Thread.sleep(1);
                        lock.unlock();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });

            }
        }
    }
}
