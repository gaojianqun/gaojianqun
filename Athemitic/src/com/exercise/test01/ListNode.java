package com.exercise.test01;
/**
 * 题目描述：
 *  给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 *  如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 *  您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * 示例：
 *  输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 *  输出：7 -> 0 -> 8
 *  原因：342 + 465 = 807
 */
public class ListNode {

    private Node head;//链表的头指针

    private int N;//链表的长度

    class Node{
        int val;
        Node next;

        public Node(int val){
            this.val = val;
        }

        public Node(int val,Node next){
            this.val = val;
            this.next = next;
        }
    }

    public void put(int val){
        Node node = new Node(val);
        if(head == null){
            head = node;
        }else{
            node.next = head;
            head = node;
        }
        N++;
    }

    //在链表头部删除节点
    public Node pop(){
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