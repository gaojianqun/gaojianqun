package cn.base;

import java.util.Scanner;

/**
 * Created by gaojianqun on 2017/11/5.
 * 利用链表实现栈操作
 */
public class Stack<Item> {
    public static void main(String [] args){
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            String item = scanner.nextLine();
            String [] str = item.split(" ");
            Stack<String> stack = new Stack<String>();
            for (int i =0;i<str.length;i++){
                String string = str[i];
                if(!"-".equals(string)){
                    stack.push(string);
                }else if(!item.isEmpty()){
                    System.out.println(stack.pop() + " ");
                }
            }

        }


    }

    private Node first; //栈顶元素（最近添加的元素）
    private int N; //元素的数量
    private class Node{
        Item item;
        Node next;
    }
    public Boolean isEmpty(){
        return first==null;
    }
    public int size(){
        return N;
    }

    //压入元素
    public void push(Item item){
        Node node = first;
        first = new Node();
        first.item = item;
        first.next = node;
        N++;
    }

    //弹出元素
    public Item pop(){
        Item item = first.item;
        first = first.next;
        N--;
        return item;
    }
}
