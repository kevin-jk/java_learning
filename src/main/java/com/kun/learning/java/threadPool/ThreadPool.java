package com.kun.learning.java.threadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @Author: jrjiakun
 * @Date: 2019/2/20 20:37
 *
 * 仅仅理解线程池的 大概流程demo, 忽略线程的状态变化等。。
 *
 *
 */
public class ThreadPool {
    private List<Thread> workerThreads = new ArrayList<>();

    private int coreSize;

    private int maxSize;

    private BlockingQueue workerQueue;

    public ThreadPool(){
        coreSize = 1;
        maxSize = 1;
        workerQueue = new LinkedBlockingDeque();
    }

    public ThreadPool(int maxSize,int coreSize,BlockingQueue workerQueue){
        this.maxSize = maxSize;
        this.coreSize = coreSize;
        this.workerQueue = workerQueue;
    }


    public void execute(Runnable task){
        if(null==task){
            return;
        }
        synchronized (workerThreads){
            int workerThreadCount = 0;
            if((workerThreadCount=workerThreads.size())<coreSize){
                workerThreadCount++;
                Thread t = new Thread(new WorkerThread(String.valueOf(workerThreadCount),task,workerQueue));
                workerThreads.add(t);
                t.start();
                return;
            }
            if(!workerQueue.offer(task)){
                 if(++workerThreadCount<maxSize){
                     Thread t = new Thread(new WorkerThread(String.valueOf(workerThreadCount),task,workerQueue));
                     workerThreads.add(t);
                     t.start();
                 } else{
                     rejectTask(task);
                 }
            }
        }
    }

    private void rejectTask(Runnable task){
        System.out.println("线程池满了。。。。");
        return;
    }

    public static void main(String[]args){
        ThreadPool threadPool = new ThreadPool();
        threadPool.execute(()-> {
                System.out.println("my first task");

        });
    }
}

