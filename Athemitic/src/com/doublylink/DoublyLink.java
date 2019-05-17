package com.doublylink;

public class DoublyLink {

    private Node head;//表头元素

    private int size;//链表的长度

    public DoublyLink(){
       /*
        * 头结点不存储值 并且头结点初始化时 就一个头结点。
        * 所以头结点的前后节点都是自己
        * 并且这个链表的长度为0；
        */
        head = new Node(null, null, null);
        head.prev = head.prex;
        head = head.prex;
        size = 0;
    }

    public int getSize(){
        return this.size;
    }

    /**
     * 判断链表是否为空
     */
    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * 判断索引是否超出范围
     */
    public void checkIndex(int index){
        if(index <0 || index>=size){
            throw new IndexOutOfBoundsException();
        }
        return ;
    }

    /**
     * 根据索引查询节点
     */
    public Node getNode(int index){
        /**
         * 检查该索引是否超出范围
         */
        checkIndex(index);

        //链表无论如何都要从遍历查找
        //O(n)->O(index)
        if(index == 1){
            return head;
        }else{
            //从头结点一个一个查找，有多少个index节点就next几次，直至找到合适的节点
            Node node = head.prex;
            for(int i=0;i<index;i++){
                node = node.prex;
            }
            return node;
        }
    }

    /**
     * 获取节点中的值
     */
    public Object getValue(Node node){
        return node.item;
    }

    /**
     * 获取第一个节点的值
     */
    public Object getFirstValue(){
        return getValue(getNode(0));
    }

    /**
     * 获取最后一个节点的值
     */
    public Object getLastValue(){
        return getValue(getNode(size-1));
    }

    /**
     * 插入节点
     */
    public void insert(int index,Object value){
        //如果这次插入时 链表是空的
        if(index ==0){
            Node node = new Node(head,value,head.prex);
            //头结点的后继的前驱是当前节点
            //头结点的后继是当前节点
            head.prex.prev = node;
            head.prex = node;
            size++;
            return;
        }

        /**
         * 想根据指定索引值去查询之前该索引的节点
         */
        Node temp = getNode(index);
        Node node = new Node(temp.prev,value,temp);
        //原来索引的节点的前驱的后继是当前要插入的节点，
        //原来索引的节点的先驱是要插入的节点的后继
        temp.prev.prex = node;
        temp.prev = node;
        size++;
    }

    /**
     * 向表头插入数据
     */
    public void insertToHead(Object value){
        insert(0,value);
    }

    /**
     * 删除节点的方法
     */
    public void deleteNode(int index){
        //先判断是否越界
        checkIndex(index);
        //先查出要删除的节点
        Node node = getNode(index);
        //要删除节点的前驱的后继==要删除节点的后继
        node.prev.prex = node.prex;
        //要删除节点的后继的前驱==要删除节点的前驱
        node.prex.prev = node.prev;
        size--;
        //避免对象游离
        node = null;
    }

    /**
     * 要删除第一个节点
     */
    public void deleteFirst(){
        deleteNode(0);
    }

    /**
     * 要删除最后一个节点
     */
    public void deleteLast(){
        deleteNode(size-1);
    }

}
