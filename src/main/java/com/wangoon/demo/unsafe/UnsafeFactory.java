package com.wangoon.demo.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author Jeff.Shen
 * @version 1.0.0
 * @title UnsafeFactory
 * @description TODO
 * @create 2022/11/30 11:24
 */
public class UnsafeFactory {

    public static final Unsafe getUnsafe() {

        Unsafe unsafe = null;
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            unsafe = (Unsafe) theUnsafe.get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return unsafe;
    }
    
}
