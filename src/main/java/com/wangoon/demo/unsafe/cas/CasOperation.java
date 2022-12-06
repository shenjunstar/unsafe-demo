package com.wangoon.demo.unsafe.cas;

import com.wangoon.demo.unsafe.base.BaseOperation;
import com.wangoon.demo.unsafe.base.UserWithDefaultVal;

import java.lang.reflect.Field;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Jeff.Shen
 * @version 1.0.0
 * @title CasOperation
 * @description cas操作
 * @create 2022/12/6 17:16
 */
public class CasOperation extends BaseOperation {
    
    private static final ExecutorService executor = Executors.newCachedThreadPool();
    
    /** 
     * 开启10个线程，去cas user对象中的age属性，最终只有一个线程能修改成功
     * @author Jeff.Shen
     * @date 2022/12/6 17:39
     * @param 
     * @return 
     */
    public static void oper1() throws Exception{
        UserWithDefaultVal user = new UserWithDefaultVal();
        Field ageField = UserWithDefaultVal.class.getDeclaredField("age");
        //计算变量在对象内存中的偏移量
        long offset = UNSAFE.objectFieldOffset(ageField);
        //模拟线程并发
        CountDownLatch cdl = new CountDownLatch(1);
        for(int i=0;i<10;i++){
            executor.execute(()->{
                String tName = Thread.currentThread().getName();
                System.out.println("cas age field. Thread:" + tName);
                try {
                    cdl.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                boolean flag = UNSAFE.compareAndSwapInt(user, offset, 10, 11);
                if (flag) {
                    System.out.println("cas success. Thread:" + tName);
                }
            });
        }
        cdl.countDown();
        executor.shutdown();
    }

    public static void main(String[] args) throws Exception{
        oper1();
    }
    
}
