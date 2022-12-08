package com.wangoon.demo.unsafe.array;

import com.wangoon.demo.unsafe.base.BaseOperation;
import sun.misc.Unsafe;

import java.util.Arrays;
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
        System.out.println("int[] base offset:"+base);
        System.out.println("int[] element scale:"+scale);
    }
    
    public static void oper2(){
        //int[] put elements
        int[] array1 = new int[5];
        for(int i=0;i<5;i++){
            UNSAFE.putInt(array1, (long)(Unsafe.ARRAY_INT_BASE_OFFSET+i*Unsafe.ARRAY_INT_INDEX_SCALE), (i+1));
        }
        System.out.println(Arrays.toString(array1));
    }

    public static void main(String[] args) {
        oper1();
        oper2();
    }
    
}
