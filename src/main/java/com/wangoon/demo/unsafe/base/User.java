package com.wangoon.demo.unsafe.base;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Jeff.Shen
 * @version 1.0.0
 * @title User
 * @description TODO
 * @create 2022/11/30 11:22
 */
@Data
public class User {
    
    private String name;
    
    private int age;
    
    private long creatMillis;
    
    private String[] hobbies;

    public User(String name, int age, long creatMillis, String[] hobbies) {
        this.name = name;
        this.age = age;
        this.creatMillis = creatMillis;
        this.hobbies = hobbies;
        System.out.println("enter User constructor with args!!");
    }
}
