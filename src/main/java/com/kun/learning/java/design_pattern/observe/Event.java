package com.kun.learning.java.design_pattern.observe;

/**
 * @Author: jrjiakun
 * @Date: 2019/3/6 10:27
 *
 *
 * 被观察者
 */
public interface Event {
    void registerListener(Listener listener);
    void removeListener(Listener listener);
    void onEvent( Object param);
}
