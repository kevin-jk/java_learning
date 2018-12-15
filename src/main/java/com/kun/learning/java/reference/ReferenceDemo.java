package com.kun.learning.java.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

/**
 * Created by jrjiakun on 2018/10/18
 *
 * WeakReference: 当内存空间不足的时候，JVM会GC掉指向的对象
 *
 * SoftReference: 每次GC的时候，都会回收掉对象
 *
 * PhantomReference: 虚引用， 无法通过虚引用访问对象的任何属性或者函数。（可以用来做一些监控） 其get方法总是返回一null, 其意义在于说明一个对象
 * 已经进入finalization阶段，可以被gc回收。用来实现比finalization机制更灵活的回收操作。
 *
 */
public class ReferenceDemo {
    private static final int size = 1024 * 1024;

    private static int cnt = 0;
    private static List<Object> container = new ArrayList<Object>();
    public static void main(String[] arsg) {
//     withNoWeakReference();
       // withWeakReference();
        //withWeakReferenceQueue();
        //weakHashMap();
        withWeakReferenceAndQueue();

    }

    // 显然，在运行程序不久之后，就会抛出异常。
    public static void withNoWeakReference() {
        try {
            while (true) {
                byte[] bytes = new byte[size];
                container.add(bytes);
                cnt++;
            }
        } catch (Throwable e) {
            System.out.println(cnt);
        }
    }

    // 此方法一直会运行
    public static void withWeakReference() {
        try {
            while (true) {
                byte[] bytes = new byte[size];
                WeakReference<byte[]> weakReference = new WeakReference<byte[]>(bytes);
                container.add(weakReference);
                cnt++;
                if (cnt > 1000) {
                    break;
                }
            }
        } catch (Throwable e) {
            System.out.println(cnt);
        }
        System.out.println(cnt);
    }

    /**
     * 没有明白为何这个
     *
     * 抛出了异常  在内存不足的时候，为啥没有被回收？
     *
     * */
    public static void withWeakReferenceQueue() {
        try {
            List<Object> tempContainer = new ArrayList<Object>();
            WeakReference<List<Object>> weakContainer = new WeakReference<List<Object>>(tempContainer);
            while (true){
                List<Object> list = weakContainer.get();
                if(null!=list){
                    list.add(new byte[size]);
                }
                cnt++;
                if(cnt>1000){
                    break;
                }
            }

        } catch (Throwable e) {
            System.out.println("exception"+cnt+e);
        }
        System.out.println(cnt);
    }

    //将一对key, value放入到 WeakHashMap 里并不能避免该key值被GC回收，除非在 WeakHashMap 之外还有对该key的强引用
    private static void weakHashMap(){
        WeakHashMap<Integer,Object> weakHashMap = new WeakHashMap<Integer, Object>();
        try{
            while (true){
                byte[]a = new byte[size];
                weakHashMap.put(cnt,a);
                cnt++;
                if(cnt>1000){
                    break;
                }

            }
        }catch (Throwable e){
            System.out.println("error");
        }
        System.out.println(cnt);
    }

    public static void withWeakReferenceAndQueue() {
        final ReferenceQueue<byte[]> referenceQueue = new ReferenceQueue<byte[]>();
        try {
            new Thread(()->{
                try{
                    if(referenceQueue.remove()!=null){
                        System.out.println("Thread 监听到对象被回收");
                    }
                }catch (Exception e){

                }
            }).start();

            while (true) {

                byte[] bytes = new byte[size];
                // 用referenceQueue监听被gc的对象
                WeakReference<byte[]> weakReference = new WeakReference<byte[]>(bytes,referenceQueue);
                container.add(weakReference);
                cnt++;
                if (cnt > 1000) {
                    break;
                }
            }
        } catch (Throwable e) {
            System.out.println(cnt);
        }
        System.out.println(cnt);
    }
}
