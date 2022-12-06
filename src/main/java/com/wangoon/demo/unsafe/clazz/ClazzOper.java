package com.wangoon.demo.unsafe.clazz;

import com.wangoon.demo.unsafe.base.BaseOperation;
import com.wangoon.demo.unsafe.base.UserWithDefaultVal;
import org.openjdk.jol.info.ClassLayout;

/**
 * @author Jeff.Shen
 * @version 1.0.0
 * @title ClazzOper
 * @description TODO
 * @create 2022/12/6 20:13
 */
public class ClazzOper extends BaseOperation {
    
    public static void oper1(){
        UserWithDefaultVal user = new UserWithDefaultVal();
        String layout = ClassLayout.parseInstance(user).toPrintable();
        System.out.println(layout);
    }

    public static void main(String[] args) {
        oper1();
    }
    
}
