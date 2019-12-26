package com.letmecook.letmecook.javalib.observer;

/**
 * Author by wangze, Date on 2019/11/28.
 */

public class Test {

    public static void main(String [] str){

        Sport3DSubject sport3DSubject = new Sport3DSubject();
        PersonObserver personObserver1 = new PersonObserver("张三",sport3DSubject);
        PersonObserver personObserver2 = new PersonObserver("李四",sport3DSubject);
        PersonObserver personObserver3 = new PersonObserver("王五",sport3DSubject);


        sport3DSubject.setMessage("中奖号码： 12 45 23 74 53 30 89 67");
    }
}
