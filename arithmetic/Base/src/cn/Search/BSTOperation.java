package cn.Search;

/**
 * Created by gaojianqun on 2018/4/25.
 * 二叉树的操作：
 * 查找排名为k的键；
 * 查找定键的排名
 */
public class BSTOperation <Key extends Comparable<Key>,Value>{

    private Node root;  //二叉树的根节点

    private class Node{

        private Node left,right;    //指向于子树的链接
        private Key key;    //键
        private Value value;    //值
        private int N;  //树的节点总数

        //二叉树的结构
        public Node(Key key,Value value,int N){
            this.key = key;
            this.value = value;
            this.N = N;
        }


        //获取数的总数
        private int size(Node node){
            if(node==null){
                return 0;
            }else{
                return node.N;
            }
        }

        //从根节点开始往下查找第k个节点的键
        public Key select(int k){
            return select(root,k).key;
        }

        /**
         * 返回排名为k的节点
         * 从小到大排列，也就是先遍历左子树，如果左子树的数量 > k的话，说明第k个元素一定在左子树中
         * 如果左子树的数量 < k 的话，说明第k个元素一定右子树中，减少了比较的次数，提高了效率
         */
        private Node select(Node node,int k){
            if(node == null){
                return null;
            }

            int t = size(node.left);
            //如果左子树的数量大于k的话，说明在左子树中
            if(t > k){
                return select(node.left,k);
            }else if(t < k){
                //如果左子树的数量小于k的话，说明在右子树中
                //右子树直接从k-t-1开始即可
                return select(node.right,k-t-1);
            }else{
                //如果t=k的话说明就是此节点
                return node;
            }
        }


        //查找键为k，在树中的位置
        public int rank(Key key){
            return rank(root,key);
        }

        /**
         * 返回键为k在二叉树中的位置
         * 先比较k的键值是否大于该节点的键值，如果等于的话说明为k的键值就是该节点，返回根节点左子树的数量的大小即可
         * 如果k的键值小于该节点的键值，说明为k的键值在树的左子树中，递归左子树即可求解
         * 如果k的键值大于该节点的键值，说明为k的键值在树的右子树中，递归右子树即可求解
         */
        public int rank(Node node,Key key){
            //如果小于该节点的键值，说明在为k的键在根节点的左子树中
            if(key.compareTo(node.key) < 0){
                return rank(node.left,key);
            }else if(key.compareTo(node.key) > 0){
                //如果大于该节点的键值，说明为k的键在根节点的右子树中,从左子树的数量 + 1 开始即可
                return size(node.left) + 1 + rank(node.right,key);
            }else{
                //就是等于该节点的键值，直接返回左子树的数量
                return size(node.left);
            }
        }
    }

}
