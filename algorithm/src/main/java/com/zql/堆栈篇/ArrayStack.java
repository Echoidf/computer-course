package com.zql.堆栈篇;

/**
 * @author 左齐亮
 * @version 1.0
 * 基于数组实现栈
 */
public class ArrayStack {
    private String[] items;
    private int n;          //栈的大小
    private int count;      //栈中元素个数

    // 初始化数组，申请一个大小为n的数组空间
    public ArrayStack(int n) {
        this.n = n;
        this.count = 0;
        this.items = new String[n];
    }

    // 入栈操作
    public boolean push (String item) {
        // 栈满返回false
        if (count == n) return false;
        items[count] = item;
        this.count++;
        return true;
    }

    // 出栈操作
    public String pop (){
        // 栈为空返回null
        if(count == 0) return null;
        String tmp = items[count-1];
        this.count--;
        return tmp;
    }
}
