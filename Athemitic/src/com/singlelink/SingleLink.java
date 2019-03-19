package com.singlelink;

/**
 * 单向链表：
 * 按从小到大顺序插入数据
 * 删除数据
 */
public class SingleLink{

    private Node head;//表头元素

    private int N;//链表的长度

    public SingleLink(){
        this.head = null;
    }

    //在链表头插入数据
    public void insertNode(Integer item){
        Node node = new Node(item);
        if(head == null){
            head = node;
        }else{
            //在表头前插入数据
            node.next = head;
            //原先表头的位置是node:node.next->head
            head = node;
        }
        N++;
        System.out.println("元素为："+item);
    }

    //在链表头部删除节点
    public Node deleteNode(){
        if(head == null){
            return head;
        }else{
            Node node = head;
            head = node.next;
            N--;
            return node;
        }
    }

}
