package com.letmecook.letmecook.javalib.observer;

/**
 * Author by wangze, Date on 2019/11/28.
 */

public class PersonObserver implements Observer {

    private String name;
    private Subject subject;

    public PersonObserver(String name, Subject subject) {
        this.name = name;
        this.subject = subject;
        subject.registObserver(this);
    }


    @Override
    public void update(String msg) {
        System.out.println(this.name+"  "+ msg);
    }
}
