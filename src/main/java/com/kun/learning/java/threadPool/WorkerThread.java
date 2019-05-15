package com.kun.learning.java.threadPool;

import java.util.concurrent.BlockingQueue;


/**
 * @Author: jrjiakun
 * @Date: 2019/2/20 20:33
 */
public class WorkerThread implements Runnable {
   private   Runnable firstTask;
   private String workerName;
   private BlockingQueue blockingQueue;


   public WorkerThread(String name,Runnable task,BlockingQueue queue){
       this.firstTask = task;
       this.workerName = name;
       this.blockingQueue = queue;

   }

    @Override
    public void run() {
      System.out.println("Thread "+workerName +"start to run");
      Runnable task = firstTask;

      while(task!=null){
          task.run();
          try{
              task = (Runnable) blockingQueue.take();
          }catch (Exception e){

          }
      }
      System.out.println("Thread "+ workerName +" run completed");
    }
}
