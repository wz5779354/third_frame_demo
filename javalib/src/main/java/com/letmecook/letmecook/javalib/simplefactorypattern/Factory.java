package com.letmecook.letmecook.javalib.simplefactorypattern;

/**
 * Author by wangze, Date on 2019/12/9.
 */

public class Factory {

    public static Product  product(String productName){
        switch (productName){
            case "A":
                return new ProductA();

            case "B":
                return new ProductB();

            case "C":
                return new ProductC();

                default:
                    return null;
        }

    }
}
