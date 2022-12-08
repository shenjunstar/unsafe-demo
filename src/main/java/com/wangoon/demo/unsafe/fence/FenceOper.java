package com.wangoon.demo.unsafe.fence;

import com.wangoon.demo.unsafe.base.BaseOperation;
import lombok.Getter;

/**
 * @author Jeff.Shen
 * @version 1.0.0
 * @title FenceOper
 * @description 内存屏障操作
 * @create 2022/12/7 17:53
 */
public class FenceOper extends BaseOperation {
    
    @Getter
    static class MyThread extends Thread {
        
        private /*volatile*/ int count = 0;
        
        @Override
        public void run() {
            while (!isInterrupted()) {
                count++;
            }
        }
    }
    
    public static void oper1(){
        MyThread myThread = new MyThread();
        myThread.start();
        while (true) {
            int count = myThread.getCount();
            UNSAFE.loadFence();
            if(count > 100000) {
                myThread.interrupt();
                System.out.println("final count:"+count);
                break;
            }
        }
    }

    public static void main(String[] args) {
        oper1();
    }
    
}
