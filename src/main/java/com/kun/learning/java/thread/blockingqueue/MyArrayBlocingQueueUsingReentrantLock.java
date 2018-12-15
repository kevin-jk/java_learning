package com.kun.learning.java.thread.blockingqueue;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by jrjiakun on 2018/12/3
 */
public class MyArrayBlocingQueueUsingReentrantLock {
    Object[] holder;
    int size;
    int readPos;
    int writePos;

    ReentrantLock reentrantLock;
    Condition NOT_FULL_CONDITION;
    Condition NOT_EMPTY_CONDITION;

    private final static int DEFAULT_CAPACITY = 1 << 3;

    public MyArrayBlocingQueueUsingReentrantLock() {
        this(DEFAULT_CAPACITY);
    }

    public MyArrayBlocingQueueUsingReentrantLock(int capacity) {
        holder = new Object[capacity];
        reentrantLock = new ReentrantLock();
        NOT_FULL_CONDITION = reentrantLock.newCondition();
        NOT_EMPTY_CONDITION = reentrantLock.newCondition();
    }

    public Object put(Object element) {
        reentrantLock.lock();
        try {
            while (size == holder.length) {
                NOT_FULL_CONDITION.await();
            }
            if(holder[writePos]!=null){
                System.out.println("Put element, put the original is not remove.");
                throw new RuntimeException("System error-put error");
            }
            holder[writePos++] = element;
            if (writePos == holder.length) {
                writePos = 0;
            }
            size++;
            NOT_EMPTY_CONDITION.notifyAll();
        } catch (Exception e) {

        } finally {
            reentrantLock.unlock();
        }
        return element;
    }

    public Object get() {
        reentrantLock.lock();
        Object ele = null;
        try {
            while (size == 0) {
                NOT_EMPTY_CONDITION.await();
            }
            ele = holder[readPos++];
            if(ele==null){
                System.out.println("Get element, put the element is null.");
                throw new RuntimeException("System error, get-error");
            }
            if (readPos == holder.length) {
                readPos = 0;
            }
            NOT_FULL_CONDITION.signalAll();
        } catch (Exception e) {

        } finally {

        }
        return ele;
    }


    public static void main(String[] arg) {
        MyArrayBlocingQueueUsingReentrantLock myArrayBlockingQueue = new MyArrayBlocingQueueUsingReentrantLock();

        new Thread(() -> {
            try {

                while (true) {
                    int ele = new Random().nextInt(10);
                    System.out.println("Put "+myArrayBlockingQueue.put(ele));
                    //   Thread.sleep(50);
                }
            } catch (Exception e) {

            }

        }, "Producer").start();


        new Thread(() -> {
            try {

                while (true) {
                    int ele = new Random().nextInt(10);
                    Object elez = myArrayBlockingQueue.put(ele);
                    System.out.println("Put "+elez);
                    //   Thread.sleep(50);
                }
            } catch (Exception e) {

            }

        }, "Producer1").start();


        new Thread(() -> {

            while (true) {
                try {
                    int ele = (Integer) myArrayBlockingQueue.get();
                    System.out.println("Get "+ ele);
                } catch (Exception e) {

                }
            }


        }, "Consumer").start();
    }
}
