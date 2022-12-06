package com.wangoon.demo.unsafe.base;

import lombok.Data;

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

    public UserWithDefaultVal(String name, int age, long creatMillis, String[] hobbies) {
        this.name = name;
        this.age = age;
        this.creatMillis = creatMillis;
        this.hobbies = hobbies;
        System.out.println("enter UserWithDefaultVal constructor with args!");
    }
    
}
