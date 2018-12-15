package com.kun.learning.java.thread;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class DeadLockDemo extends Thread {



    private String first;
    private String second;

    public DeadLockDemo(String name, String first, String second) {
        super(name);
        this.first = first;
        this.second = second;
    }

    public void run() {
        synchronized (first) {
            System.out.println(this.getName() + " obtained: " + first);
            try {
                Thread.sleep(1000L);
                synchronized (second) {
                    System.out.println(this.getName() + " obtained: " + second);
                }
            } catch (InterruptedException e) {// Do nothing
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        deadLockCheck();
        //验证死循环
        Thread t3 = deadCur("cur");
        t3.join();
        // 验证死锁
//        String lockA = "lockA";
//        String lockB = "lockB";
//        DeadLockDemo t1 = new DeadLockDemo("Thread1", lockA, lockB);
//        DeadLockDemo t2 = new DeadLockDemo("Thread2", lockB, lockA);
//        t1.start();
//        t2.start();
//        t1.join();
//        t2.join();
    }


    // 检测死锁线程
    private static void deadLockCheck(){
        ThreadMXBean mxBean = ManagementFactory.getThreadMXBean();
        Runnable dlCheck = new Runnable() {
            @Override
            public void run() {
                long[] threadIds = mxBean.findDeadlockedThreads();
                if(null!=threadIds){
                    ThreadInfo[] threadInfos = mxBean.getThreadInfo(threadIds);
                    System.out.println("Find Dead Locks：");
                    for (ThreadInfo threadInfo : threadInfos) {

                        System.out.println("threadName:"+threadInfo.getThreadName() );
                    }
                }
            }
        };
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.scheduleAtFixedRate(dlCheck,5L,10L, TimeUnit.SECONDS);
    }

    //可以通过linux下top命令查看cpu使用率较高的java进程，进而用top -Hp + pid查看该java进程下cpu使用率较高的线程。
    // 再用jstack命令查看线程具体调用情况，排查问题。
    private static Thread deadCur(String name){
        Thread t=null;
        (t=new Thread(()->{while (true){;}},name)).start();
        return t;
    }
}
