package com.stack;

public class ArrayStack {
    //栈中的节点元素
    private Object[] object;
    //栈中的元素数量
    private int count;
    //栈的大小
    private int n;

    public ArrayStack(int n){
        this.n = n;
        this.object = new Object[n];
        this.count = 0;
    }

    //向栈中添加元素
    public boolean push(Object obj){
        //栈的大小是否比增加的数量大
        if(n == 0 || count > n){
            return false;
        }
        object[count] = obj;
        //栈中的元素数量增加
        ++count;
        return true;
    }

    //将栈中元素弹出
    public Object pop(){
        if(count == 0){
            return null;
        }
        Object obj = object[count-1];
        --count;
        return obj;
    }

}
