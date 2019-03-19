package com.singlelink;

public class DemoSingleLink {

    public static void main(String [] args){
       SingleLink singleLink = new SingleLink();
       singleLink.insertNode(10);
       singleLink.insertNode(8);
       singleLink.insertNode(14);
       singleLink.insertNode(6);

       Node node = singleLink.deleteNode();
       if(node!=null){
           System.out.println(node.item);
       }

    }

}
