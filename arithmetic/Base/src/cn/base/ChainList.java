package cn.base;

import org.omg.CORBA.NO_IMPLEMENT;

/**
 * Created by gaojianqun on 2017/11/5.
 * 链表
 */
public class ChainList {

    private class Node{
        Object item;
        Node next;
    }

    Node first = new Node();
    Node second = new Node();
    Node third = new Node();

    /**
     * 构造链表
     */
    public void constructor(){
        first.item = "to";
        second.item = "be";
        third.item = "or";
        first.next = second;
        second.next = third;
    }

    /**
     * 在表头插入节点
     */
    public void insertFirst(Node oldFirst){
        oldFirst = first;
        first.item = "not";
        first.next = oldFirst;
    }

    /**
     * 在表头删除节点
     */
    public void removeFist(){
        //节点自己存储自己，与其他人无关
        first = first.next;
    }

}
