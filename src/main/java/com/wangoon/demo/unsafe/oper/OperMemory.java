package com.wangoon.demo.unsafe.oper;

import com.wangoon.demo.unsafe.UnsafeFactory;
import sun.misc.Unsafe;

import java.nio.ByteBuffer;

/**
 * @author Jeff.Shen
 * @version 1.0.0
 * @title OperMemory
 * @description TODO
 * @create 2022/11/30 15:02
 */
public class OperMemory {
    public static void main(String[] args) {
        Unsafe unsafe = UnsafeFactory.getUnsafe();
        
        //堆外内存操作
        //申请一个字节，获得内存地址
        long l = unsafe.allocateMemory(1L);
        System.out.println(Long.toHexString(l));
        unsafe.freeMemory(l);

        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);


    }
}
