package com.kun.practise.reference;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jrjiakun on 2018/10/18
 */
public class ReferenceDemo {
    private static final int size = 1024 * 1024;

    private static int cnt = 0;
    private static List<Object> container = new ArrayList<Object>();
    public static void main(String[] arsg) {
//     withNoWeakReference();
       // withWeakReference();
        withWeakReferenceQueue();
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
}
