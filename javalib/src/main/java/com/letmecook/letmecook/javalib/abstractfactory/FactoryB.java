package com.letmecook.letmecook.javalib.abstractfactory;


/**
 * Author by wangze, Date on 2019/12/9.
 */

public class FactoryB extends Factory {

    @Override
    public Product makeContainerProduct() {
        return new ContainerProductB();
    }

    @Override
    public Product makeModleProduct() {
        return new ModleProductB();
    }
}
