package cn.Search;

/**
 * Created by gaojianqun on 2018/4/9.
 */
public class BST<Key extends Comparable<Key>,Value>{

    private Node root;       //二叉树的根节点

    private class Node{
        private Key key;      //键
        private Value value;  //值
        private Node left,right;    //指向于子树的链接
        private int N;               //树的节点总数

        public Node(Key key,Value value,int N){
            this.key = key;
            this.value = value;
            this.N = N;
        }
    }

    //树的操作

    //获取树的总数
    public int size(){
        return size(root);
    }

    private int size(Node node){
        if(node==null){
            return 0;
        }else{
            return node.N;
        }
    }

    //树中节点的查找
    public Value get(Key key){
        return get(root,key);
    }

    private Value get(Node node,Key key){
        if(node == null){
            return null;
        }else{
            int cmp = key.compareTo(node.key);
            if(cmp < 0){
                //如果要查找的键比父节点的键小，说明是该节点的左子树
                return get(node.left,key);
            }else if(cmp > 0){
                //如果要查找的键比父节点大，说明是该节点的右子树
                return get(node.right,key);
            }else{
                //如果相等证明是该节点
                return node.value;
            }
        }
    }


    //树中的节点添加
    public Node put(Key key,Value value){
        return put(root,key,value);
    }

    private Node put(Node node,Key key,Value value){
        //如果没有加入节点的话从第一个节点开始创建，即创建根节点
        if(node == null){
            return new Node(key,value,1);
        }
        int cmp = key.compareTo(node.key);
        //说明需要加入的节点在该节点的左子树上
        if(cmp < 0){
            node.left = put(node.left,key,value);
        }else if(cmp > 0){
            //说明需要加入的节点在该节点的右子树上
            node.right = put(node.right,key,value);
        }else{
            node.value = value;
        }
        //节点的总数
        node.N = size(node.left) + size(node.right) + 1;
        return node;
    }


    //查找二叉树中的最小键
    public Key min(){
        return min(root).key;
    }

    public Node min(Node node){
        if(node.left == null){
            return node;
        }else{
            return min(node.left);
        }
    }

    //查找二叉树中的最大键
    public Key max(){
        return max(root).key;
    }

    public Node max(Node node){
        if(node.right == null){
            return node;
        }else{
            return max(node.right);
        }
    }


}
