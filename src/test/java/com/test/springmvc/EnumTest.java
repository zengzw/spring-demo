/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.springmvc;

/**
 *
 * @author zengzw
 * @date 2017年6月8日
 */
public enum EnumTest {

    Dtl;
    public static void main(String[] args){
        int a = 1;
        int b = 2;

        try {
            a = 3;           //A
            b = 1 / 0;       //B
        } catch (Exception e) {

        } finally {
            System.out.println("a = " + a);
        }
    }
}
