package com.stack;

public class LinkedStack {

    //栈中的节点元素
    private Node head;
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
        //n的下一个节点变成了head
        n.next = head;
        //原来的head节点变成了n
        head = n;

        ++count;
        return true;
    }

    //弹栈
    public Object pop(){
        if(count == 0){
            return null;
        }
        Object object = head.object;
        head = head.next;
        --count;
        return object;
    }

}
