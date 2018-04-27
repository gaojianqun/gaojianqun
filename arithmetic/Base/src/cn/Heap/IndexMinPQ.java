package cn.Heap;

/**
 * Created by gaojianqun on 2018/4/4.
 * 使用二叉堆实现的索引最小优先队列
 * class IndexMinPQ是一个支持泛型的索引优先队列。
 * IndexMinPQ支持普通的insert、delete-the-minimum、delete以及change-the-key方法。
 * 用户可以使用队列中的0到maxN-1号索引执行删除和修改方法。
 * IndexMinPQ支持获取队列最小元素，队列最小元素索引操作。
 * IndexMinPQ支持迭代器迭代所有插入的索引号。
 *
 * IndexMinPQ的实现使用二叉堆。
 * （1）增加数组keys用来保存优先队列元素（item or key）

 （2）pq数组则改用于保存索引（pq[i] = k，k为索引）

 　　　　这里的pq数组仍然保持堆有序，即pq[1]所对应的元素（keys[pq[1]]）大于或等于pq[2]、pq[3]所对应的元素（keys[pq[2]]、keys[pq[3]]）。

 （3）增加数组qp，用于保存pq的逆序。

 　　　　如果pq[i] = k，则qp[k] = i，也就是pq[qp[k]] = k。

 　　　　如果k不在队列之中，则qp[k] = -1。
 *  操作时间复杂度为O(lgN).
 */
public class IndexMinPQ<Key extends Comparable<Key>>{

    

}
