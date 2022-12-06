package com.wangoon.demo.unsafe.access;

import com.wangoon.demo.unsafe.UnsafeFactory;
import sun.misc.Unsafe;

/**
 * @author Jeff.Shen
 * @version 1.0.0
 * @title TryAccessByReflection
 * @description 通过反射的方式获取Unsafe
 * @create 2022/12/6 10:18
 */
public class TryAccessByReflection {

    public static void main(String[] args) {
        Unsafe unsafe = UnsafeFactory.getUnsafe();
        //do unsafe operation
        //allocate direct memory
        long address = unsafe.allocateMemory(8L);
        System.out.println("unsafe allocate mem by reflection success! address: "+address);
        //free memory
        unsafe.freeMemory(address);
        System.out.println("memory in address : "+address +" freed.");
    }
    
}
