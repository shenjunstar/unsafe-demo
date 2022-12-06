package com.wangoon.demo.unsafe.memory;

import java.nio.ByteBuffer;

/**
 * @author Jeff.Shen
 * @version 1.0.0
 * @title RevisedObjectInHeap
 * @description TODO
 * @create 2022/12/6 15:58
 */
public class ByteBufferBindHeapObject {
    private final ByteBuffer buffer;
    
    /**
     * 让对象占用堆内存,触发 Full GC,从而回收堆外内存
     */
    private byte[] bytes = null;

    public ByteBufferBindHeapObject() {
        buffer = ByteBuffer.allocateDirect(2 * 1024 * 1024);
        bytes = new byte[1024 * 1024];
    }

    public static void main(String[] args) {
        while (true)
        {
            ByteBufferBindHeapObject obj = new ByteBufferBindHeapObject();
            System.out.println("obj=" + obj.hashCode());
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
