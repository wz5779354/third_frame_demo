package com.letmecook.letmecook.javalib.abstractfactory;


/**
 * Author by wangze, Date on 2019/12/9.
 */

public class FactoryA extends Factory {

    @Override
    public Product makeContainerProduct() {
        return new ContainerProductA();
    }

    @Override
    public Product makeModleProduct() {
        return new ModleProductA();
    }
}
