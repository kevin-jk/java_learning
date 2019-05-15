package com.kun.learning.java.timer_sch;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @Author: jrjiakun
 * @Date: 2019/2/27 17:38
 *
 * 根据这个demo可以知道：
 *
 * timer上调用多次schedule，也是前一个task执行完了之后，后一个schedule才开始执行
 *
 * 如果执行时间相同，那执行顺序呢？
 *
 *
 */
public class TimerTaskDemo2 {
    public static void main(String[]args){
        Timer timer = new Timer("测试Timer");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Task 2 "+ sdf.format(new Date()));
                try{
                   Thread.sleep(5000);
                }catch (Exception e){

                }

            }
        },1000,2000);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Task 1 "+ sdf.format(new Date()));
            }
        },1000,2000);
    }
}