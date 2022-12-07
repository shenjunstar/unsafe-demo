package com.wangoon.demo.unsafe.array;

import com.wangoon.demo.unsafe.base.BaseOperation;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @author Jeff.Shen
 * @version 1.0.0
 * @title ArrayOper
 * @description 数组操作
 * @create 2022/12/7 17:17
 */
public class ArrayOper extends BaseOperation {

    public static void oper1(){
        int base = UNSAFE.arrayBaseOffset(int[].class);
        int scale = UNSAFE.arrayIndexScale(int[].class);
        System.out.println(base);
        System.out.println(scale);
    }
    
    public static void oper2(){
        AtomicIntegerArray array = new AtomicIntegerArray(5);
        array.lazySet(0, 1); //16
        array.lazySet(1, 2); //20
        array.lazySet(2, 3); //24
        array.lazySet(3, 4); //28
        array.lazySet(4, 5); //32
    }

    public static void main(String[] args) {
        oper1();
        oper2();
    }
    
}
