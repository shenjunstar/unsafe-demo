package com.wangoon.demo.unsafe.access;

import sun.misc.Unsafe;

/**
 * @author Jeff.Shen
 * @version 1.0.0
 * @title TryAccessDirectly
 * @description 直接获取Unsafe对象，会报异常
 * 1.把运行的jar加入到bootclasspath中就能运行
 * java -Xbootclasspath/a:.\\unsafe-demo.jar -cp .\\unsafe-demo.jar com.wangoon.demo.unsafe.access.TryAccessDirectly
 * 2.idea运行加入vm options: -Xbootclasspath/a:D:\\java-workspace\\unsafe-demo\\target\\unsafe-demo.jar
 * @create 2022/12/6 10:11
 */
public class TryAccessDirectly {

    public static void main(String[] args) {
        Unsafe unsafe = Unsafe.getUnsafe();
        //do unsafe operation
        //allocate direct memory
        long address = unsafe.allocateMemory(8L);
        System.out.println("unsafe allocate mem directly success! address: "+address);
        //free memory
        unsafe.freeMemory(address);
        System.out.println("memory in address : "+address +" freed.");
    }
    
}
