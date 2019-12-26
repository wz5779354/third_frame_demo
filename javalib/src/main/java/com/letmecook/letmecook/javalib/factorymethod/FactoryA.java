package com.letmecook.letmecook.javalib.factorymethod;


/**
 * Author by wangze, Date on 2019/12/9.
 */

public class FactoryA extends Factory {
    @Override
    public Product manufacture() {
        return new ProductA();
    }
}
