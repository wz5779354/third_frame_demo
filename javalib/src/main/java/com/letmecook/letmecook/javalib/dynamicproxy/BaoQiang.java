package com.letmecook.letmecook.javalib.dynamicproxy;

/**
 * Author by wangze, Date on 2019/11/27.
 */

public class BaoQiang implements IPlay {
    @Override
    public String film() {
        System.out.println("宝强拍电影");
        return "result--宝强拍电影";
    }

}
