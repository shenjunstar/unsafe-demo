package com.wangoon.demo.unsafe.sysinfo;

import com.wangoon.demo.unsafe.base.BaseOperation;

/**
 * @author Jeff.Shen
 * @version 1.0.0
 * @title SysInfoOper
 * @description TODO
 * @create 2022/12/7 18:41
 */
public class SysInfoOper extends BaseOperation {
    
    public static void oper1(){
        int pageSize = UNSAFE.pageSize();
        int addressSize = UNSAFE.addressSize();
        System.out.println("pageSize:"+pageSize);
        System.out.println("addressSize:"+addressSize);
    }

    public static void main(String[] args) {
        oper1();
    }
    
}
