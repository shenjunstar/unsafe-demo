package com.wangoon.demo.unsafe.memory;

import com.wangoon.demo.unsafe.UnsafeFactory;
import sun.misc.Unsafe;

/**
 * @author Jeff.Shen
 * @version 1.0.0
 * @title RevisedObjectInHeap
 * @description TODO
 * @create 2022/12/6 15:58
 */
public class MemoryBindHeapObject {
    private final long address;

    private final Unsafe unsafe = UnsafeFactory.getUnsafe();

    /**
     * 让对象占用堆内存,触发 Full GC,从而回收堆外内存
     */
    private byte[] bytes = null;

    public MemoryBindHeapObject() {
        address = unsafe.allocateMemory(2 * 1024 * 1024);
        bytes = new byte[1024 * 1024];
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize." + bytes.length);
        unsafe.freeMemory(address);
    }

    public static void main(String[] args) {
        while (true)
        {
            MemoryBindHeapObject heap = new MemoryBindHeapObject();
            System.out.println("memory address=" + heap.address);
        }
    }
}
