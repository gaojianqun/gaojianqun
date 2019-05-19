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

    /**
     * 查找树中关键字为key的节点
     * @param key
     * @return
     */
    public Value get(Key key){
        return get(root,key);
//        较为高效
//        return get1(root,key);
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


    /**
     * 查找关键字的第二种写法
     * 迭代版本较为高效
     * @param node
     * @param key
     * @return
     */
    private Value get1(Node node,Key key){
        while(node!=null && key !=node.key){
            if(key.compareTo(node.key) < 0){
                node = node.left;
            }else if(key.compareTo(node.key) > 0){
                node = node.right;
            }
        }
        return node.value;
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
//        较为高效的迭代方法
//        return min1(root).key;
    }

    private Node min(Node node){
        if(node.left == null){
            return node;
        }else{
            return min(node.left);
        }
    }

    private Node min1(Node node){
        while(node!=null){
            node = node.left;
        }
        return node;
    }

    //查找二叉树中的最大键
    public Key max(){
        return max(root).key;
//        较为高效的迭代写法
//        return max1(root).key;
    }

    private Node max(Node node){
        if(node.right == null){
            return node;
        }else{
            return max(node.right);
        }
    }


    /**
     * 较为高效的迭代方式
     * @param node
     * @return
     */
    private Node max1(Node node){
        while (node!=null){
            node = node.right;
        }
        return node;
    }


    //删除某一节点，将该节点的后继放入到此节点的位置上即可
    public void deleteMin(){
        root = getRightMin(root);
    }

    //永远把最小节点的右节点筛选出来
    private Node getRightMin(Node node){
        if(node.left == null)
            return node.right;
        node.left = getRightMin(node.left);
        node.N = size(node.left) + size(node.right) + 1;
        return node;
    }

    //删除某一节点,同时返回新的父节点
    public void delete(Key key){
        root = delete(root,key);
    }

    private Node delete(Node node,Key key){
        if(node == null){
            return null;
        }
        //寻找key
        int cmp = key.compareTo(node.key);
        if(cmp < 0){
            node.left = delete(node,key);
        }else if(cmp > 0){
            node.right = delete(node,key);
        }else{
            //如果有一个子节点
            if(node.right == null){
                return node.left;
            }
            if(node.left == null){
                return node.right;
            }
            //如果有两个子节点
            Node t = node;
            //如果有两个子节点就势必要把该节点临近的最小右节点拿出来
            node = min(t.right);
            //封装新父节点的右节点
            node.right = getRightMin(t.right);
            //封装新父节点的左节点
            node.left = t.left;
        }
        node.N = size(node.left) + size(node.right) + 1;
        return node;
    }

}
