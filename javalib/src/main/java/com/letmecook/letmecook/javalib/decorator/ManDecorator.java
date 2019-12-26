package com.letmecook.letmecook.javalib.decorator;

/**
 * Author by wangze, Date on 2019/12/9.
 */

public class ManDecorator extends AbstractDecorator {
    public ManDecorator(Human human) {
        super(human);
    }

    private void fly(){
        System.out.println("人可以飞");
    }

    @Override
    public void run() {
        super.run();
        fly();
    }


}
