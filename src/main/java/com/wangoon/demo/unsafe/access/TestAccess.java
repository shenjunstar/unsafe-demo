package com.wangoon.demo.unsafe.access;

import com.wangoon.demo.unsafe.UnsafeFactory;
import com.wangoon.demo.unsafe.objects.User;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * @author Jeff.Shen
 * @version 1.0.0
 * @title TestAccess
 * @description TODO
 * @create 2022/11/30 11:02
 */
public class TestAccess {

    public static void main(String[] args) throws Exception {
        //Unsafe unsafe = Unsafe.getUnsafe();
        Unsafe unsafe = UnsafeFactory.getUnsafe();

        // 使用Unsafe的allocateInstance()方法，可以无需使用构造函数的情况下实例化对象
        // User user1 = new User(); //直接new会报编译错误
        User user = (User) unsafe.allocateInstance(User.class);
        user.setName("Jeff");
        user.setAge(20);
        user.setCreatMillis(System.currentTimeMillis());
        user.setHobbies(new String[]{"swimming", "reading"});
        

        Field name = User.class.getDeclaredField("name");
        Field age = User.class.getDeclaredField("age");
        Field creatMillis = User.class.getDeclaredField("creatMillis");
        Field hobbies = User.class.getDeclaredField("hobbies");
        long nameOffset = unsafe.objectFieldOffset(name);
        long ageOffset = unsafe.objectFieldOffset(age);
        long creatMillisOffset = unsafe.objectFieldOffset(creatMillis);
        long hobbiesOffset = unsafe.objectFieldOffset(hobbies);
        System.out.println(nameOffset);
        System.out.println(ageOffset);
        System.out.println(creatMillisOffset);
        System.out.println(hobbiesOffset);

        System.out.println(user);
        //修改name属性
        unsafe.putObject(user, nameOffset, "James");
        
        //通过class字节数组创建class
        //unsafe.defineClass()

        System.out.println(user);
        
    }
    
}
