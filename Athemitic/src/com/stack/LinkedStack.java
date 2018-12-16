package com.stack;

public class LinkedStack {

    //栈中的节点元素
    private Node node;
    //栈中的元素数量
    private int count;

    private class Node{
        Object object; //链表中的元素
        Node next; //存储下一个节点的指针

        public Node(Object object){
            this.object = object;
        }

    }

    //压栈
    public boolean push(Object object){
        Node n = new Node(object);
        n.next = node;
        node = n;
        ++count;
        return true;
    }

    //弹栈
    public Object pop(){
        if(count == 0){
            return null;
        }
        Object object = node.object;
        node = node.next;
        --count;
        return object;
    }

}
