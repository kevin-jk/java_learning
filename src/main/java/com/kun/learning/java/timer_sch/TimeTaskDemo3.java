package com.kun.learning.java.timer_sch;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @Author: jrjiakun
 * @Date: 2019/2/27 18:06
 */
public class TimeTaskDemo3 {
    public static void main(String[]args){
        Timer timer = new Timer("测试Timer");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date begin = null;
        try{
            begin  = sdf.parse("2019-02-27 19:03:00");
            }catch (Exception e){

            }


        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Task 1 "+ sdf.format(new Date()));
            }
        },begin);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Task 2 "+ sdf.format(new Date()));
                try{
                    Thread.sleep(5000);
                }catch (Exception e){
                }
            }
        },begin);

        System.out.println("end00");

        new Thread(()->{
            try{
                System.out.println("New Thread start run");
                Thread.sleep(10000);
            }catch (Exception e){

            }
        }).start();

        System.out.println("end11");
        return;
    }


}
