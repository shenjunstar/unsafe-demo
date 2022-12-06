package com.wangoon.demo.unsafe.base;

import com.wangoon.demo.unsafe.UnsafeFactory;
import sun.misc.Unsafe;

/**
 * @author Jeff.Shen
 * @version 1.0.0
 * @title BaseOperation
 * @description TODO
 * @create 2022/12/6 11:18
 */
public class BaseOperation {
    protected static final Unsafe UNSAFE = UnsafeFactory.getUnsafe();
}
