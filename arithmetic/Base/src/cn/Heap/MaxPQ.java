package cn.Heap;

/**
 * 基于堆的优先队列
 * 堆中某个节点的值总是不大于或不小于其父节点的值；
 * 堆总是一棵完全二叉树。
 * (二叉堆)
 * Created by gaojianqun on 2018/3/31.
 */
public class MaxPQ<Key extends Comparable<Key>> {

    public static void main(String [] args){
        MaxPQ key = new MaxPQ(7);
        key.insert(40);
        key.insert(50);
        key.insert(1);
        key.insert(12);
        key.insert(30);
        key.insert(77);
        key.insert(65);

        for(int i = 0;i < key.pq.length;i++){
            System.out.print(key.pq[i]+" ");
        }

        System.out.println();

        key.delMax();
        key.insert(35);

        for(int i = 0;i < key.pq.length;i++){
            System.out.print(key.pq[i]+" ");
        }
    }

    private Key[] pq;
    private int N = 0;

    //构造方法
    public MaxPQ(){

    }

    public MaxPQ(int maxN){
        pq = (Key[]) new Comparable[maxN+1];
    }

    //判断二叉堆是否为空
    public boolean isEmpty(){
        return N==0;
    }

    //判断二叉堆的大小
    public int size(){
        return N;
    }

    //插入元素
    public void insert(Key key){
        pq[++N] = key;
        swim(N);
    }

    //删除最大元素
    public Key delMax(){
        Key max = pq[1];
        if(max !=null){
            Key p = pq[N--];
            Key temp = p;
            p = pq[1];
            pq[1] = temp;
            //避免对象游离直接置为空
            pq[N+1] = null;
            sink(1);
        }
        return max;
    }

    //元素上浮(由下至上的堆有序上浮)
    private void swim(int k){
        while((k > 1) && (pq[k/2].compareTo(pq[k])==-1)){
            Key temp = pq[k/2];
            pq[k/2] = pq[k];
            pq[k] = temp;
            //继续上浮（继续和上一级节点比较）
            k = k/2;
        }
    }

    //元素下沉（由上至下的堆有序下沉）
    //1.父节点需要跟每一个子节点比较2.两个子节点相互比较，选出最小的，在进行父节点比较
    private void sink(int k){
        while(2*k <= N){
            int j = 2*k;
            //首先比较该节点中的哪一个节点更小
            if((j < N) && (pq[j].compareTo(pq[j+1])==-1)){
                j++;
            }
            if(pq[k].compareTo(pq[j])==-1){
                Key temp = pq[k];
                pq[k] = pq[j];
                pq[j] = temp;
                k = j;
            }else{
                break;
            }
        }
    }

}
