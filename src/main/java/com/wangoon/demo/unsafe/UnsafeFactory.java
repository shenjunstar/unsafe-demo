package com.wangoon.demo.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
/**
 * @author Jeff.Shen
 * @version 1.0.0
 * @title UnsafeFactory
 * @description Unsafe工厂类
 * @create 2022/11/30 11:24
 */
public class UnsafeFactory {

    /** 
     * 通过反射的方式获取Unsafe对象
     * @author Jeff.Shen
     * @date 2022/12/6 10:12
     * @param 
     * @return 
     */
    public static final Unsafe getUnsafe() {
        Unsafe unsafe;
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            unsafe = (Unsafe) theUnsafe.get(null);
        } catch (Exception e) {
            throw new RuntimeException("access Unsafe error!", e);
        }
        return unsafe;
    }
    
}
