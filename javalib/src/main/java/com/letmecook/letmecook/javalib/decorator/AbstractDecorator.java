package com.letmecook.letmecook.javalib.decorator;

/**
 * Author by wangze, Date on 2019/12/9.
 */

public abstract class AbstractDecorator implements Human {

    private Human human;
    public AbstractDecorator(Human human) {
        this.human = human;

    }

    @Override
    public void run() {
        human.run();
    }
}
