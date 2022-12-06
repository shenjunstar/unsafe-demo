package com.wangoon.demo.unsafe.thread;

import com.wangoon.demo.unsafe.base.BaseOperation;
/**
 * @author Jeff.Shen
 * @version 1.0.0
 * @title ThreadOper
 * @description TODO
 * @create 2022/12/6 17:54
 */
public class ThreadOper extends BaseOperation {
    
    static class DemoThread extends Thread{
        
        public DemoThread() {
        }
        
        @Override
        public void run() {
            System.out.println("Thread start. current:"+System.currentTimeMillis());
            UNSAFE.park(false, 0L);
            System.out.println("Thread end. current:"+System.currentTimeMillis());
        }
    }
    
    public static void oper1(){
        DemoThread t1 = new DemoThread();
        DemoThread t2 = new DemoThread();
        DemoThread t3 = new DemoThread();
        t1.start();
        t2.start();
        t3.start();
        try {
            Thread.sleep(10000);
            UNSAFE.unpark(t1);
            UNSAFE.unpark(t2);
            UNSAFE.unpark(t3);
            t1.join();
            t2.join();
            t3.join();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        oper1();
    }
    
}
