package com.wangoon.demo.unsafe.memory;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

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
        //堆外分配2Mb
        buffer = ByteBuffer.allocateDirect(2 * 1024 * 1024);
        //堆内分配1M
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
