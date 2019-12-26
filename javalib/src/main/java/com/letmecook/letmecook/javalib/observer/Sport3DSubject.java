package com.letmecook.letmecook.javalib.observer;

import java.util.ArrayList;

/**
 * Author by wangze, Date on 2019/11/28.
 */

public class Sport3DSubject implements Subject {

    private ArrayList<Observer> observerList = new ArrayList();
    private String msg;
    @Override
    public void registObserver(Observer observer) {
        observerList.add(observer);

    }

    @Override
    public void removeObserver(Observer observer) {
        int index = observerList.indexOf(observer);
        if (index >0) {
            observerList.remove(observer);
        }
    }

    @Override
    public void notifyObserver() {
        for (Observer observer: observerList) {
            observer.update(msg);
        }
    }
    public void setMessage(String smg){
        this.msg = smg;
        notifyObserver();
    }
}
