package com.letmecook.letmecook.javalib.abstractfactory;

/**
 * Author by wangze, Date on 2019/12/9.
 */

public class AbstractFactoryTest {

    public static void main(String[] strgs) {
        /**
         * 工厂方法模式： 一个具体工厂只能创建一类产品
         而在实际过程中，一个工厂往往需要生产多类产品。为了解决上述的问题，我们又使用了一种新的设计模式：抽象工厂模式。
         */

        /**
         * 使用步骤
         步骤1： 创建抽象工厂类，定义具体工厂的公共接口；
         步骤2： 创建抽象产品族类 ，定义抽象产品的公共接口；
         步骤3： 创建抽象产品类 （继承抽象产品族类），定义具体产品的公共接口；
         步骤4： 创建具体产品类（继承抽象产品类） & 定义生产的具体产品；
         步骤5：创建具体工厂类（继承抽象工厂类），定义创建对应具体产品实例的方法；
         步骤6：客户端通过实例化具体的工厂类，并调用其创建不同目标产品的方法创建不同具体产品类的实例
         */

        /**
         * 优点：
         * 降低耦合
         抽象工厂模式将具体产品的创建延迟到具体工厂的子类中，这样将对象的创建封装起来，可以减少客户端与具体产品类之间的依赖，从而使系统耦合度低，这样更有利于后期的维护和扩展；

         更符合开-闭原则
         新增一种产品类时，只需要增加相应的具体产品类和相应的工厂子类即可

         符合单一职责原则
         每个具体工厂类只负责创建对应的产品

         不使用静态工厂方法，可以形成基于继承的等级结构。

         */

        /**
         * 缺点：

         抽象工厂模式很难支持新种类产品的变化。
         这是因为抽象工厂接口中已经确定了可以被创建的产品集合，如果需要添加新产品，此时就必须去修改抽象工厂的接口，
         这样就涉及到抽象工厂类的以及所有子类的改变，这样也就违背了“开发——封闭”原则。

         */

        FactoryA factoryA = new FactoryA();
        factoryA.makeContainerProduct().show();
        factoryA.makeModleProduct().show();

        FactoryB factoryB = new FactoryB();
        factoryB.makeContainerProduct().show();
        factoryB.makeModleProduct().show();
    }
}
