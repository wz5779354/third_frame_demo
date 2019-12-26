package com.letmecook.letmecook.javalib.observer;

/**
 * Author by wangze, Date on 2019/11/28.
 */

public interface Subject {
    //注册观察者
    public void registObserver(Observer observer);
    //取消观察者
    public void removeObserver(Observer observer);
    //通知观察者
    public void notifyObserver();

}
