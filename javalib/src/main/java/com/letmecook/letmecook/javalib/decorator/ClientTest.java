package com.letmecook.letmecook.javalib.decorator;

/**
 * Author by wangze, Date on 2019/12/9.
 */

public class ClientTest {
    /**
     * 使用装饰模式扩展一个类的功能。好处在于，如果继承关系是纵向的，那么装饰类则是某个类横向的扩展，并不会影响继承链上的其他类。
     * 例如：C extends B , B extends A，如果需要扩展B的功能，可以设计一个B的装饰类，它并不会影响B的子类C。
     * 如果采用在B里面增加方法，势必会使B的所有子类结构被改变。
     */

    public static void main(String[] args){
        Human human = new Man();
        Human superMan = new ManDecorator(human);
        superMan.run();
    }
}
