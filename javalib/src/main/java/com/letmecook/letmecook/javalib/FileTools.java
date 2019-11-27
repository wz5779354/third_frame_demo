package com.letmecook.letmecook.javalib;

import java.io.FileOutputStream;

import sun.misc.ProxyGenerator;

/**
 * Author by wangze, Date on 2019/11/27.
 */

public class FileTools {

    /**
     * 生成代理类文件
     */

    public static void generyProxyFile() {
        byte[] classFile = ProxyGenerator.generateProxyClass("baoqoangProxy", BaoQiang.class.getInterfaces());

        String path = "./baoqoangProxy.class";
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            fileOutputStream.write(classFile);
            fileOutputStream.flush();
            System.out.println("动态代理类class文件 写入成功");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("动态代理类class文件 写入失败");
        }

    }
}
