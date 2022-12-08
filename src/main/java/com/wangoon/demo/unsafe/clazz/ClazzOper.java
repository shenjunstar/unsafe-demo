package com.wangoon.demo.unsafe.clazz;

import com.wangoon.demo.unsafe.base.BaseOperation;
import com.wangoon.demo.unsafe.base.User;


import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Field;


/**
 * @author Jeff.Shen
 * @version 1.0.0
 * @title ClazzOper
 * @description TODO
 * @create 2022/12/6 20:13
 */
public class ClazzOper extends BaseOperation {
    
    /** 
     * 静态变量取值、赋值
     * @author Jeff.Shen
     * @date 2022/12/7 16:47
     * @param 
     * @return 
     */
    public static void oper1(){
        try {
            Field flagField = User.class.getDeclaredField("flag");
            Class<User> base = (Class<User>) UNSAFE.staticFieldBase(flagField);
            long offset = UNSAFE.staticFieldOffset(flagField);
            boolean flag = UNSAFE.getBoolean(base, offset);
            System.out.println(flag);
            UNSAFE.putBoolean(base, offset, true);
            flag = UNSAFE.getBoolean(base, offset);
            System.out.println(flag);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    /** 
     * 观察类加载完成前静态变量的取值
     * @author Jeff.Shen
     * @date 2022/12/7 17:16
     * @param 
     * @return 
     */
    public static void oper2(){
        try {
//            User user = new User();
//            UNSAFE.ensureClassInitialized(User.class);
            boolean flag = UNSAFE.shouldBeInitialized(User.class);
            System.out.println(flag);
            Field valueField = User.class.getDeclaredField("value");
            Class<User> base = (Class<User>) UNSAFE.staticFieldBase(valueField);
            long offset = UNSAFE.staticFieldOffset(valueField);
            System.out.println(UNSAFE.getObject(base, offset));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    /** 
     * 动态创建类，观察classloader
     * @author Jeff.Shen
     * @date 2022/12/7 17:16
     * @param 
     * @return 
     */
    public static void oper3(){
        String fileName="D:\\java-workspace\\unsafe-demo\\target\\classes\\com\\wangoon\\demo\\unsafe\\base\\UserWithDefaultVal.class";
        File file = new File(fileName);
        try(FileInputStream fis = new FileInputStream(file)) {
            byte[] content=new byte[(int)file.length()];
            fis.read(content);
            Class clazz = UNSAFE.defineClass(null, content, 0, content.length, null, null);
            //获取classloader为空
            System.out.println("classloader:"+clazz.getClassLoader());
            Object o = clazz.newInstance();
            Object age = clazz.getMethod("getAge").invoke(o, null);
            System.out.println("age:"+age);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    public static void main(String[] args) {
//        oper1();
//        oper2();
        oper3();
    }
    
}
