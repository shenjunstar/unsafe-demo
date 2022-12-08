package com.wangoon.demo.unsafe.base;

import lombok.Data;
import org.openjdk.jol.info.ClassLayout;

/**
 * @author Jeff.Shen
 * @version 1.0.0
 * @title UserWithDefaultVal
 * @description TODO
 * @create 2022/12/6 11:10
 */
@Data
public class UserWithDefaultVal {

    private String name = "default";

    private int age = 10;

    private long creatMillis = System.currentTimeMillis();

    private String[] hobbies = new String[]{"eating", "sleeping"};
    
    public UserWithDefaultVal(){
        System.out.println("enter UserWithDefaultVal default constructor!");
    }

    /**
     * 打印内存布局
     */
    public void printClassLayout(){
        String layout = ClassLayout.parseInstance(this).toPrintable();
        System.out.println(layout);
    }

    public UserWithDefaultVal(String name, int age, long creatMillis, String[] hobbies) {
        this.name = name;
        this.age = age;
        this.creatMillis = creatMillis;
        this.hobbies = hobbies;
        System.out.println("enter UserWithDefaultVal constructor with args!");
    }
    
}
