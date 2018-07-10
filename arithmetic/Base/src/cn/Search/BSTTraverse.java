package cn.Search;

/**
 * Created by gaojianqun on 2018/6/15.
 * 二叉搜索树的遍历
 */
public class BSTTraverse <Key extends Comparable<Key>,Value>{

    //数据结构
    private Node root;

    private class Node{
        private Node left,right;
        private int N;
        private Key key;
        private Value value;

        public Node(Key key,Value value,int N){
            this.key = key;
            this.value = value;
            this.N = N;
        }
    }

    //前序遍历
    public void first(Node root){
        if(root!=null){
            System.out.println(root.key);
            first(root.left);
            first(root.right);
        }
    }

    //中序遍历
    public void mid(Node root){
        if(root!=null){
            mid(root.left);
            System.out.println(root.key);
            mid(root.right);
        }
    }

    //后序遍历
    public void end(Node root){
        if(root!=null){
            end(root.left);
            end(root.right);
            System.out.println(root.key);
        }
    }
}
