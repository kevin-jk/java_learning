package com.kun.learning.java.timer_sch;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;


/**
 * @Author: jrjiakun
 * @Date: 2019/2/20 14:30
 *
 *
 *Timer
 * sheduleAtFixedRate() 以固定的频率进行执行，如果上一个任务晚点后，任然会执行下一个时间点的任务， 也就是会在下一个时间点，将余下的任务全部执行
 */
public class TimerDemo {

    public static void main(String[] arsg) {
        Timer timer = new Timer();
        timer.schedule(new MyTimerTask(),1000,2000);
       // timer.scheduleAtFixedRate(new MyTimerTask(), 1000, 2000);
    }
}

class MyTimerTask extends java.util.TimerTask {
    private static int TASK_DELAY = 10;
    int init = 0;

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public void run() {
        System.out.println("The " + init + " times execute, and the time is " + simpleDateFormat.format(new Date()));
        init++;
        try {
            Thread.sleep(2000 * (init % 3));
        } catch (Exception e) {
            System.out.println("Run exception");
        }

    }
}
