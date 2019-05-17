package com.doublylink;

public class Node {

    Node prev;
    Object item;
    Node prex;

    public Node(Node prev,Object item,Node prex){
        this.prev = prev;
        this.item = item;
        this.prex = prex;
    }

}
