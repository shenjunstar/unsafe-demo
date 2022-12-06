package com.wangoon.demo.unsafe.memory;

import com.wangoon.demo.unsafe.base.BaseOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Jeff.Shen
 * @version 1.0.0
 * @title MemoryOperation
 * @description Unsafe 内存操作
 * @create 2022/12/6 11:15
 */
@Slf4j
public class MemoryOperation extends BaseOperation {
    
    /** 
     * 分配4字节，并且放入int类型，观察内存地址，最后释放内存
     * @author Jeff.Shen
     * @date 2022/12/6 14:28
     * @param 
     * @return 
     */
    public static void oper1(){
        long allocateBytes = Integer.BYTES;
        long address = UNSAFE.allocateMemory(allocateBytes);
        log.info("allocate {} bytes, memory address:{}", allocateBytes, address);
        // 在已经分配的内存地址中赋值
        UNSAFE.putInt(address, Integer.MIN_VALUE);
        log.info("get int from memory:{}, value:{}", address, UNSAFE.getInt(address));
        // 覆盖原值
        UNSAFE.putInt(address, Integer.MAX_VALUE);
        log.info("get int from memory:{}, value:{}", address, UNSAFE.getInt(address));
        // 获取
        log.info("native address:{}", UNSAFE.getAddress(address));
        //释放内存
        UNSAFE.freeMemory(address);
        log.info("free memory : {}", address);
    }
    
    /** 
     * 分配4字节，但是存入的是8字节的long类型，观察
     * @author Jeff.Shen
     * @date 2022/12/6 14:38
     * @param 
     * @return 
     */
    public static void oper2(){
        //分配4字节，并赋值
        long intAddress = UNSAFE.allocateMemory(Integer.BYTES);
        UNSAFE.putInt(intAddress, 100);
        //分配8字节，存储上一个指针
        long allocateBytes = Long.BYTES;
        long address = UNSAFE.allocateMemory(allocateBytes);
        log.info("allocate {} bytes, memory address:{}", allocateBytes, address);
        UNSAFE.putAddress(address, intAddress);
        log.info("get address : {}, int value:{}", UNSAFE.getAddress(address), UNSAFE.getInt(UNSAFE.getAddress(address)));
        UNSAFE.freeMemory(intAddress);
        UNSAFE.freeMemory(address);
    }
    
    
    public static void oper3(){
        long size = 16L;
        long address = UNSAFE.allocateMemory(size);
        UNSAFE.setMemory(address, size, (byte)1);
        System.out.println(Long.toBinaryString(UNSAFE.getLong(address)));
    }

    public static void main(String[] args) {
        oper1();
        System.out.println("=====================");
        oper2();
        System.out.println("=====================");
        oper3();
    }
    
}
