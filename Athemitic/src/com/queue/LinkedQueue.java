package com.queue;

public class LinkedQueue {

    //队列中的元素
    private Node head;

    private class Node{
        Object object; //链表中的元素
        Node next; //存储下一个节点的指针

        public Node(Object object){
            this.object = object;
        }
    }

    //队列中进入元素
    public boolean enqueue(Object object){
        Node n = new Node(object);
        if(head == null){
            head = n;
        }else {
            Node temp = head;
            while(temp.next != null){
                temp = temp.next;
            }
            temp.next = n;
        }
        return true;
    }

    //队列中删除元素:一个一个删除元素
    public Object dequeue(){
        if(head != null){
            Object object = head.object;
            head = head.next;
            return object;
        }else{
            return null;
        }
    }

}
