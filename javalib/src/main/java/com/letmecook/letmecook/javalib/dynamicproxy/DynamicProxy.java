package com.letmecook.letmecook.javalib.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxy {

    public static void main(String[] src){

        final BaoQiang baoQiang = new BaoQiang();
       IPlay play= (IPlay) Proxy.newProxyInstance(baoQiang.getClass().getClassLoader(), new Class[]{IPlay.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                System.out.println("经纪人公司派陈凯做宝强的经纪人，帮助他协定拍摄时间薪酬");
//                baoQiang.film();
                return baoQiang.film();
            }
        });
        FileTools.generyProxyFile();
        String result = play.film();
        System.out.println(result);
    }
}
