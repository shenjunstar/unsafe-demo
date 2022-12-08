package com.wangoon.demo.unsafe.objects;

import com.wangoon.demo.unsafe.base.BaseOperation;
import com.wangoon.demo.unsafe.base.User;
import com.wangoon.demo.unsafe.base.UserWithDefaultVal;
import org.openjdk.jol.info.ClassLayout;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Jeff.Shen
 * @version 1.0.0
 * @title ObjectsOper
 * @description TODO
 * @create 2022/12/7 16:37
 */
public class ObjectsOper extends BaseOperation {

    /**
     * 查看对象的内存布局
     * @author Jeff.Shen
     * @date 2022/12/7 8:50
     * @param
     * @return
     */
    public static void oper1(){
        UserWithDefaultVal user = new UserWithDefaultVal();
        String layout = ClassLayout.parseInstance(user).toPrintable();
        System.out.println(layout);
    }

    /**
     * 查看当前字节序是大端还是小端
     * @author Jeff.Shen
     * @date 2022/12/7 9:58
     * @param
     * @return
     */
    public static void oper2(){
        long address = UNSAFE.allocateMemory(8L);
        UNSAFE.putLong(address, 0x0102030405060708L);
        byte aByte = UNSAFE.getByte(address);
        System.out.print("当前操作系统字节序为：");
        switch (aByte) {
            case 0x01:
                System.out.print("大端");
                break;
            case 0x08:
                System.out.print("小端");
                break;
            default:
                System.out.println("未知");
        }
    }

    /**
     * User没有空构造函数，使用new以及class.newInstance分别汇报编译错误和运行时找不到构造方法异常
     * 此时通过unsafe实例化User对象，观察是否需要执行其构造方法，并通过unsafe给字段赋值
     * @author Jeff.Shen
     * @date 2022/12/7 10:05
     * @param
     * @return
     */
    public static void oper3(){
        try {
            User user = (User) UNSAFE.allocateInstance(User.class);
            //User user1 = new User();
            //User user2 = (User) Class.forName("com.wangoon.demo.unsafe.base.User").newInstance();
            //User user3 = (User) Class.forName("com.wangoon.demo.unsafe.base.User").getConstructors()[0].newInstance("Jeff", 10, System.currentTimeMillis(), new String[]{"111"});
            Field[] declaredFields = user.getClass().getDeclaredFields();
            Map<String, Long> fieldOffset = new HashMap<>();
            for(Field field : declaredFields) {
                long offset = UNSAFE.objectFieldOffset(field);
                fieldOffset.put(field.getName(), offset);
                System.out.println("field :"+field.getName()+", offset:"+offset);
            }
            UNSAFE.putObject(user, fieldOffset.get("name"), "Jimmy");
            UNSAFE.putInt(user, fieldOffset.get("age"), 8);
            UNSAFE.putLong(user, fieldOffset.get("creatMillis"), System.currentTimeMillis());
            UNSAFE.putObject(user, fieldOffset.get("hobbies"), new String[]{"Singing"});
            System.out.println(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 多线程下静态变量，使用unsafe put和get属性的可见性
     * @author Jeff.Shen
     * @date 2022/12/7 10:56
     * @param
     * @return
     */
    public static void oper4(){
        try {
            Field flagField = User.class.getDeclaredField("flag");
            long offset = UNSAFE.staticFieldOffset(flagField);
            Object base = UNSAFE.staticFieldBase(flagField);
            Runnable r = () -> {
                while (true) {
                    boolean flag = UNSAFE.getBoolean(base, offset);
//                    boolean flag = User.flag;
                    if(flag){
                        break;
                    }
                }
                System.out.println("Thread:"+Thread.currentThread().getName()+" get value finish");
            };
            for(int i=0;i<10;i++){
                new Thread(r, "t"+i).start();
            }
            Thread.sleep(3000);
            UNSAFE.putBoolean(base, offset, true);
//            User.flag = true;
            System.out.println("set static field.");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 多线程下成员变量，使用unsafe put和get属性的可见性
     * @author Jeff.Shen
     * @date 2022/12/7 16:32
     * @param
     * @return
     */
    public static void oper5(){
        try {
            User user = (User)UNSAFE.allocateInstance(User.class);
            Field ageField = User.class.getDeclaredField("age");
            long offset = UNSAFE.objectFieldOffset(ageField);
            Runnable r = () -> {
                while (true) {
                    int age = UNSAFE.getIntVolatile(user, offset);
//                    int age = user.getAge();
                    if(age>0){
                        break;
                    }
                }
                System.out.println("Thread:"+Thread.currentThread().getName()+" get value finish");
            };
            for(int i=0;i<3;i++){
                new Thread(r, "t"+i).start();
            }
            Thread.sleep(3000);
            UNSAFE.putIntVolatile(user, offset, 10);
//            user.setAge(10);
            System.out.println("set age field 10.");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        oper1();
//        oper2();
//        oper3();
//        oper4();
//        oper5();
    }
    
}
