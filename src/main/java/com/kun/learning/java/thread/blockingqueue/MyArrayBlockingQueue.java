package com.kun.learning.java.thread.blockingqueue;

import java.util.Random;



/**
 * Created by jrjiakun on 2018/12/3
 *
 *
 * wait()使当前线程阻塞，前提是 必须先获得锁，一般配合synchronized 关键字使用,因此一般在synchronized 同步代码块里使用 wait()、notify/notifyAll() 方法。
 *
 * 当线程执行wait()方法时候，会释放当前的锁，然后让出CPU，进入等待状态。
 *
 * 只有当 notify/notifyAll() 被执行时候，才会唤醒一个或多个正处于等待状态的线程，然后继续往下执行，
 * 直到执行完synchronized 代码块的代码或是中途遇到wait() ，再次释放锁。
 *
 * 也就是说，notify/notifyAll() 的执行只是唤醒沉睡的线程，而不会立即释放锁，锁的释放要看代码块的具体执行情况。所以在编程中，
 * 尽量在使用了notify/notifyAll() 后立即退出临界区，以唤醒其他线程
 *
 *
 */
public class MyArrayBlockingQueue {
    Object[] holder;
    Object lock = new Object();
    //队列长度
    int size = 0;
    // 读取位置
    int readPos = 0;
    // 写位置
    int writePos = 0;

    private static int DEFAULT_CAPCITY = 1<<3;

    public MyArrayBlockingQueue() {
        this(DEFAULT_CAPCITY);
    }

    public MyArrayBlockingQueue(int capcity) {
        holder = new Object[capcity];

    }

    public Object put(Object element) {
        try {
            synchronized (lock) {
                //notify唤醒沉睡的线程后，线程会接着上次的执行继续往下执行。所以在进行条件判断时候，可以先把 wait 语句忽略不计来进行考虑，
                // 显然，要确保程序一定要执行，并且要保证程序直到满足一定的条件再执行，要使用while来执行，以确保条件满足和一定执行。
                while (size == holder.length) {
                    lock.wait();
                }
                put0(element);
            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {

        }
        return element;
    }

    private Object put0(Object element) {
        if(holder[writePos]!=null){
            System.out.println("Put element, put the original is not remove.");
            throw new RuntimeException("System error-put error");
        }
        holder[writePos++] = element;
        if (writePos==holder.length){
            writePos=0;
        }
        size++;
        lock.notifyAll();
        return element;
    }

    public Object get() {
        Object ele = null;
        try {
            synchronized (lock) {
                while (size == 0) {

                    lock.wait();
                }
                ele = get0();
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {

        }
        return ele;
    }

    private Object get0() {

        Object e = holder[readPos];
        if(e==null){
            System.out.println("Get element, put the element is null.");
            throw new RuntimeException("System error, get-error");
        }
        holder[readPos]=null;
        readPos++;
        if(readPos==holder.length){
            readPos=0;
        }
        size--;
        lock.notifyAll();
        return e;
    }

    public static void main(String[] arg) {
        MyArrayBlockingQueue myArrayBlockingQueue = new MyArrayBlockingQueue();

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
