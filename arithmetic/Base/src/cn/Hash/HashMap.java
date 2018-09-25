package cn.Hash;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

/**
 * Created by gaojianqun on 2018/8/29.
 */
public class HashMap<K,V> implements Map<K,V>,Cloneable,Serializable{

    // 0001
    //0001 0000
    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; //2的4次幂

    //计算过程已1<<30为例,首先把1转为二进制数字 0000 0000 0000 0000 0000 0000 0000 0001
    //向左移动30位后面补0得到 0010 0000 0000 0000 0000 0000 0000 0000
    static final int MAXIMUM_CAPACITY = 1 << 30; //2的30次幂

    //默认加载因子
    static final float DEFAULT_LOAD_FACTOR = 0.75f;

    //如果哈希函数不合理，即使扩容也无法减少箱子中链表的长度，因此 Java 的处理方案是当链表太长时，
    // 转换成红黑树。这个值表示当某个箱子中，链表长度大于 8 时，有可能会转化成树。
    static final int TREEIFY_THRESHOLD = 8;

    //在哈希表扩容时，如果发现链表长度小于 6，则会由树重新退化为链表。
    static final int UNTREEIFY_THRESHOLD = 6;

    //在转变成树之前，还会有一次判断，只有键值对数量大于 64 才会发生转换。
    // 这是为了避免在哈希表建立初期，多个键值对恰好被放入了同一个链表中而导致不必要的转化。
    static final int MIN_TREEIFY_CAPACITY = 64;

    /**
     * Holds cached entrySet(). Note that AbstractMap fields are used
     * for keySet() and values().
     */
    transient Set<Map.Entry<K,V>> entrySet;


    /**
     * Basic hash bin node, used for most entries.  (See below for
     * TreeNode subclass, and in LinkedHashMap for its Entry subclass.)
     * 用节点模拟key - value
     */
    static class Node<K,V> implements Map.Entry<K,V> {
        final int hash;
        final K key;
        V value;
        Node<K,V> next;

        Node(int hash, K key, V value, Node<K,V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public final K getKey()        { return key; }
        public final V getValue()      { return value; }
        public final String toString() { return key + "=" + value; }

        public final int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }

        public final V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }

        public final boolean equals(Object o) {
            if (o == this)
                return true;
            if (o instanceof Map.Entry) {
                Map.Entry<?,?> e = (Map.Entry<?,?>)o;
                if (Objects.equals(key, e.getKey()) &&
                        Objects.equals(value, e.getValue()))
                    return true;
            }
            return false;
        }
    }

    //hashMap中的哈希算法
    //在JDK1.8的实现中，优化了高位运算的算法，通过hashCode()的高16位异或低16位实现的：
    // (h = k.hashCode()) ^ (h >>> 16)，主要是从速度、功效、质量来考虑的，
    // 这么做可以在数组table的length比较小的时候，也能保证考虑到高低Bit都参与到Hash的计算 中，
    // 同时不会有太大的开销。
    //例如：
    //h = hashCode(): 1111 1111 1111 1111 1111 0000 1110 1010
    //h >>> 16:       0000 0000 0000 0000 1111 1111 1111 1111
    //h = h ^ h>>>16: 1111 1111 1111 1111 0000 1111 0001 0101 (异或运算)

    //(n-1) & hash :  0000 0000 0000 0000 0000 0000 0000 1111
    //                1111 1111 1111 1111 0000 1111 0001 0101  &-> 0000 0000 0000 0000 0000 0000 0000 0101
    // 0101 -> 5
    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    //节点数组来存储元素
    transient Node<K,V>[] table;

    /**
     * The number of key-value mappings contained in this map.
     * 键值对的总数
     */
    transient int size;

    // HashMap被改变的次数
    transient int modCount;

    /**
     * 如果表数组没有被分配，保存初始数组容量，或表示为零
     * (DEFAULT_INITIAL_CAPACITY)。
     * 散列表中索引的真实数量(容量*负载系数)
     */
    int threshold;

    //加载因子
    float loadFactor;

    /**
     * hashMap构造函数
     * 保证key的数量永远在最大范围内 2^30
     * @param initialCapacity
     * @param loadFactor
     */
    public HashMap(int initialCapacity,float loadFactor){
        if (initialCapacity < 0)
            throw new IllegalArgumentException("Illegal initial capacity: " +
                    initialCapacity);
        if(initialCapacity > MAXIMUM_CAPACITY)
            initialCapacity = MAXIMUM_CAPACITY;
        if (loadFactor <= 0 || Float.isNaN(loadFactor))
            throw new IllegalArgumentException("Illegal load factor: " +
                    loadFactor);
        this.loadFactor = loadFactor;
        this.threshold = tableSizeFor(initialCapacity);
    }

    /**
     * Constructs an empty <tt>HashMap</tt> with the specified initial
     * capacity and the default load factor (0.75).
     *
     * @param  initialCapacity the initial capacity.
     * @throws IllegalArgumentException if the initial capacity is negative.
     */
    public HashMap(int initialCapacity) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }

    /**
     * Constructs an empty <tt>HashMap</tt> with the default initial capacity
     * (16) and the default load factor (0.75).
     */
    public HashMap() {
        this.loadFactor = DEFAULT_LOAD_FACTOR; // all other fields defaulted
    }


    /**
     * Constructs a new <tt>HashMap</tt> with the same mappings as the
     * specified <tt>Map</tt>.  The <tt>HashMap</tt> is created with
     * default load factor (0.75) and an initial capacity sufficient to
     * hold the mappings in the specified <tt>Map</tt>.
     *
     * @param   m the map whose mappings are to be placed in this map
     * @throws  NullPointerException if the specified map is null
     */
    public HashMap(Map<? extends K,? extends V> m){
        this.loadFactor = DEFAULT_LOAD_FACTOR;
        putMapEntries(m,false);
    }


    /**
     * 把所有的映射关系添加到Entry中
     * @param m
     * @param evict
     */
    final void putMapEntries(Map<? extends K,? extends V> m, boolean evict){
        int s = m.size();
        if(s > 0){
            if(table == null){
                //重新定义threshold 和 loadFactor
                float ft = ((float)s / loadFactor) + 1.0F;
                int t = (ft < (float)MAXIMUM_CAPACITY ? (int) ft : MAXIMUM_CAPACITY);
                if(t > threshold)
                    threshold = tableSizeFor(t);

                //如果初始化的哈希表的容量 > 索引数量的最大值，扩容
                else if(s > threshold)
                    //扩容
                    resize();
                //向HashMap中放入数据
                for(Map.Entry<? extends K,? extends V> e : m.entrySet()){
                    K key = e.getKey();
                    V value = e.getValue();
                    putVal(hash(key), key ,value ,false , evict);
                }
            }
        }
    }


    /**
     * 对链表扩容兼初始化
     * @return
     */
    final Node<K,V>[] resize(){
        Node<K,V>[] oldTab = table;
        int oldCap = (oldTab == null) ? 0 :oldTab.length;
        int oldThr = threshold;
        int newCap, newThr = 0;

        if(oldCap > 0) {
            //如果容量超过 2^30 次幂 默认直接返回该HashMap
            //默认是不存在的
            if (oldCap > MAXIMUM_CAPACITY) {
                oldCap = MAXIMUM_CAPACITY;
                threshold = Integer.MAX_VALUE;
                return oldTab;
            }

            //每次扩容是 容量 * 2；索引阀值 * 2
            //左位移 1 位
            else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY
                    && oldCap >= DEFAULT_INITIAL_CAPACITY)
                //索引数量 左位移 1 位
                newThr = oldThr << 1;
        }
        else if (oldThr > 0) // initial capacity was placed in threshold
            newCap = oldThr;
        else{                // zero initial threshold signifies using defaults
            newCap = DEFAULT_INITIAL_CAPACITY;
            newThr = (int) (DEFAULT_INITIAL_CAPACITY * DEFAULT_LOAD_FACTOR);
        }

        if(newThr == 0){
            float ft = (float) newCap * loadFactor;
            newThr = (newCap < MAXIMUM_CAPACITY && ft < (float) MAXIMUM_CAPACITY) ?
                    (int)ft : Integer.MAX_VALUE;
        }

        threshold = newThr;

        @SuppressWarnings({"rawtypes","unchecked"})
            Node<K,V>[] newTab = (Node<K, V>[]) new Node[newCap];
        table = newTab;

        // 如果原来的table有数据，则将数据复制到新的table中
        if(oldTab != null){
            // 根据容量进行循环整个数组，将非空元素进行复制
            for(int j = 0;j < oldTab.length; ++j){
                Node<K,V> e;
                // 获取数组的第j个元素
                if((e = oldTab[j]) != null){
                    //避免原有对象游离
                    oldTab[j] = null;
                    // 如果链表只有一个，则进行直接赋值
                    if (e.next == null)
                        newTab[e.hash & (newCap - 1)] = e;
                    //红黑树操作
                    else if (e instanceof TreeNode)
                        ((TreeNode<K,V>)e).split(this, newTab, j, oldCap);
                    //进行链表复制
                    else{
                        //两种情况：
                        //1、如果哈希值没有变，lo指针移动将原有链表中的元素一一添加到新的链表中
                        //2、如果哈希值变了，hi指针移动将原有链表中的元素一一添加到新的链表中
                        Node<K,V> loHead = null, loTail = null;
                        Node<K,V> hiHead = null, hiTail = null;
                        Node<K,V> next;
                        do{
                            next = e.next;
                            //1、如果哈希值没有变，lo指针移动将原有链表中的元素一一添加到新的链表中
                            if((e.hash & oldCap) == 0){
                                if(loTail == null){
                                    loHead = e;
                                }else{
                                    loTail.next = e;
                                }
                                loTail = e;
                            }
                            //2、如果哈希值变了，hi指针移动将原有链表中的元素一一添加到新的链表中
                            else{
                                if(hiTail == null){
                                    hiHead = e;
                                }else{
                                    hiTail.next = e;
                                }
                                hiTail = e;
                            }
                        }while ((e = next) != null);

                        if(loTail != null){
                            loTail.next = null; //将链表的尾节点 的next 设置为空
                            newTab[j] = loHead; //同时将指针定格在新的元素位置处
                        }

                        if(hiTail != null){
                            hiTail.next = null; //将链表的尾节点 的next 设置为空
                            newTab[newCap + j] = hiHead; //同时将指针定格在新的元素位置处
                        }
                    }
                }
            }
        }
        return newTab;
    }



    /**
     * @param hash key的hash值
     * @param key 键
     * @param value 值
     * @param onlyIfAbsent 设为true表示如果键不存在，才会写入值。
     * @param evict
     * @return 返回value
     */
    final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
                   boolean evict) {
        Node<K,V>[] tab;Node<K,V> p;int n = 0, i = 0;
        // 步骤①：tab为空则创建
        if((tab = table) == null || (n=tab.length)==0)
            n = (tab = resize()).length;
        // 步骤②：计算index，并对null做处理
        if((p = tab[i = (n-1) & hash]) == null)
            tab[i] = newTreeNode(hash,key,value,null);
        else{
            Node<K,V> e; K k;
            // 步骤③：节点key存在，直接覆盖value
            if (p.hash == hash && ((k = p.key) == key || (key != null && key.equals(k))))
                e = p;
            // 步骤④：判断该链为红黑树
            else if(p instanceof TreeNode)
                e = ((TreeNode<K,V>) p).putTreeVal(this,tab,hash,key,value);
            // 步骤⑤：该链为链表
            else{
                for(int binCount = 0; ;++binCount){
                    if((e = p.next) == null){
                        p.next = newNode(hash,key,value,null);
                        //链表长度大于8转换为红黑树进行处理
                        if(binCount >= TREEIFY_THRESHOLD)
                            treeifyBin(tab,hash);
                        break;
                    }
                    // key已经存在直接覆盖value
                    if (e.hash == hash && ((k = e.key) == key || (key != null && key.equals(k))))
                        p = e;
                    if (e != null) { // 说明找了和要写入的key对应的元素，根据情况来决定是否覆盖值
                        V oldValue = e.value; // 旧值
                        if (!onlyIfAbsent || oldValue == null)	// 如果旧值为空  后者  指定了需要覆盖旧值，那么更改元素的值为新值
                            e.value = value;
                        //afterNodeAccess(e); // 元素被访问之后的后置处理， LinkedHashMap中有具体实现
                        return oldValue; // 返回旧值
                    }

                }
            }
        }

        // 执行到这里，说明是增加了新的元素，而不是替换了老的元素，所以相关计数需要累加
        ++modCount; // 修改计数器递增
        // 当前map的元素个数递增
        if(++size > threshold) // 如果当前map的元素个数大于了扩容阀值，那么需要扩容元素数组了
            resize(); //扩容
        //afterNodeInsertion(evict); // 添加新元素之后的后后置处理， LinkedHashMap中有具体实现
        return null; // 返回空
    }


    /**
     * tab：元素数组，
     * hash：hash值（要增加的键值对的key的hash值）
     */
    final void treeifyBin(Node<K,V>[] tab,int hash){
        int n = 0,index = 0; Node<K,V> e;
         /** 如果元素数组为空 或者 数组长度小于 树结构化的最小限制
          *  MIN_TREEIFY_CAPACITY 默认值64，
          *  对于这个值可以理解为：如果元素数组长度小于这个值，没有必要去进行结构转换
          *  当一个数组位置上集中了多个键值对，
          *  那是因为这些key的hash值和数组长度取模之后结果相同。（并不是因为这些key的hash值相同）
          *  因为hash值相同的概率不高，所以可以通过扩容的方式，
          *  来使得最终这些key的hash值在和新的数组长度取模之后，拆分到多个数组位置上。
          */
         if(tab == null || (n = tab.length) < MIN_TREEIFY_CAPACITY)
             resize();
        // 如果元素数组长度已经大于等于了 MIN_TREEIFY_CAPACITY，那么就有必要进行结构转换了
        // 根据hash值和数组长度进行取模运算后，得到链表的首节点
        else if((e = tab[index = (n-1) & hash]) != null){
            TreeNode<K,V> hd = null,tl = null; // 定义首、尾节点
            do{
                TreeNode<K,V> p = replacementTreeNode(e, null); // 将该节点转换为 树节点
                if(tl == null)//如果尾节点为空，说明还没有根节点
                    hd = p;
                else{ // 尾节点不为空，以下两行是一个双向链表结构
                    p.prev = tl; // 当前树节点的 前一个节点指向 尾节点
                    tl.next = p; // 尾节点的 后一个节点指向 当前节点
                }
                tl = p;// 把当前节点设为尾节点
            }while ((e = e.next) != null); //继续遍历链表

             // 把转换后的双向链表，替换原来位置上的单向链表
             if ((tab[index] = hd) != null)
                 hd.treeify(tab);
         }

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean containsKey(Object key) {
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public V get(Object key) {
        return null;
    }

    @Override
    public V put(K key, V value) {
        return null;
    }

    @Override
    public V remove(Object key) {
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set<K> keySet() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        return null;
    }

    /**
     * Returns a power of two size for the given target capacity.
     * 用来计算2 的n次幂，查看是否达到最大容量
     */
    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : (n + 1);
    }

    /**
     * 创建一个TreeNode对象
     * @param hash
     * @param key
     * @param value
     * @param next
     * @return
     */
    TreeNode<K,V> newTreeNode(int hash, K key, V value, Node<K,V> next) {
        return new TreeNode<>(hash, key, value, next);
    }

    /**
     * 将该节点转换为 树节点
     * @param p
     * @param next
     * @return
     */
    TreeNode<K,V> replacementTreeNode(Node<K,V> p, Node<K,V> next) {
        return new TreeNode<>(p.hash, p.key, p.value, next);
    }

    /**
     * 创建一个链表中的Node对象
     * @param hash
     * @param key
     * @param value
     * @param next
     * @return
     */
    Node<K,V> newNode(int hash, K key, V value, Node<K,V> next) {
        return new Node<>(hash, key, value, next);
    }

    static final class TreeNode<K,V> extends HashMap.Entry<K,V> {

        TreeNode<K,V> parent;
        TreeNode<K,V> left;
        TreeNode<K,V> right;
        TreeNode<K,V> prev;
        boolean red;   // needed to unlink next upon deletion

        TreeNode(int hash, K key, V value, Node<K, V> next) {
            super(hash, key, value, next);
        }

        /**
         * 根据hash值和Key获取节点
         * @param h
         * @param k
         * @return
         */
        final TreeNode<K,V> getTreeNode(int h,Object k){
            return ((parent != null) ? root() : this).find(h,k,null);
        }

        final TreeNode<K,V> root(){
            for (TreeNode<K,V> r = this, p;;) {
                if ((p = r.parent) == null)
                    return r;
                r = p;
            }
        }

        /**
         * treeify方法是TreeNode类的一个实例方法，通过TreeNode对象调用，实现该对象打头的链表转换为树结构。
         * 即调用次方法我们要把链表转换为一个红黑树
         * @param tab
         */
        final void treeify(Node<K,V>[] tab) {
            TreeNode<K,V> root = null;
            //遍历整个链表中的数据
            for (TreeNode<K,V> x = this, next; x != null; x = next) {
                next = (TreeNode<K,V>)x.next;
                x.left = x.right = null; //设置当前节点的左右节点为空
                if(root == null){ //如果还没有根节点
                    x.parent = null; // 当前节点的父节点设为空
                    x.red = false; // 当前节点的红色属性设为false（把当前节点设为黑色）
                    root = x; // 根节点指向到当前节点
                }else{ // 如果已经存在根节点了
                    K k = x.key;//取得当前链表节点的key
                    int h = x.hash; //取得当前节点的哈希值
                    Class<?> kc = null; // 定义key所属的Class
                    for(TreeNode<K,V> p = root;;){ //此次循环没有边界，只能从里面跳出
                        int dir = 0,ph = 0; // dir 标识方向（左右）、ph标识当前树节点的hash值
                        K pk = p.key;//当前树节点的key
                        if((ph = p.hash) > h) // 如果当前树节点hash值 大于 当前链表节点的hash值
                            dir = -1; // 标识当前链表节点会放到当前树节点的左侧
                        else if(ph < h)
                            dir = 1; //右侧

                        /*
                         * 如果两个节点的key的hash值相等，那么还要通过其他方式再进行比较
                         * 如果当前链表节点的key实现了comparable接口，并且当前树节点和链表节点是相同Class的实例，那么通过comparable的方式再比较两者。
                         * 如果还是相等，最后再通过tieBreakOrder比较一次
                         */
//                        else if ((kc == null &&
//                                (kc = comparableClassFor(k)) == null) ||
//                                (dir = compareComparables(kc, k, pk)) == 0)
//                            dir = tieBreakOrder(k, pk);
                      TreeNode<K,V> xp = p; // 保存当前树节点
                        /*
                         * 如果dir 小于等于0 ： 当前链表节点一定放置在当前树节点的左侧，但不一定是该树节点的左孩子，也可能是左孩子的右孩子 或者 更深层次的节点。
                         * 如果dir 大于0 ： 当前链表节点一定放置在当前树节点的右侧，但不一定是该树节点的右孩子，也可能是右孩子的左孩子 或者 更深层次的节点。
                         * 如果当前树节点不是叶子节点，那么最终会以当前树节点的左孩子或者右孩子 为 起始节点  再从GOTO1 处开始 重新寻找自己（当前链表节点）的位置
                         * 如果当前树节点就是叶子节点，那么根据dir的值，就可以把当前链表节点挂载到当前树节点的左或者右侧了。
                         * 挂载之后，还需要重新把树进行平衡。平衡之后，就可以针对下一个链表节点进行处理了。
                         */
                        if ((p = (dir <= 0) ? p.left : p.right) == null) {
                            x.parent = xp;
                            if(dir <= 0){
                                xp.left = x; //作为左孩子
                            }else{
                                xp.right = x; //作为右孩子
                            }
                            root = balanceInsertion(root, x); // 重新平衡
                            break;
                        }

                    }
                }
            }
        }


        /**
         * Returns a list of non-TreeNodes replacing those linked from
         * this node.
         */
        final Node<K,V> untreeify(HashMap<K,V> map) {
            Node<K,V> hd = null, tl = null;
            for (Node<K,V> q = this; q != null; q = q.next) {
                Node<K,V> p = map.replacementNode(q, null);
                if (tl == null)
                    hd = p;
                else
                    tl.next = p;
                tl = p;
            }
            return hd;
        }


         //split方法会将树分割为lower 和upper tree两个树，
        //如果子树的节点数小于了UNTREEIFY_THRESHOLD阈值，则将树untreeify，将节点都存放在newTab中。
         //((TreeNode<K,V>)e).split(this, newTab, j, oldCap);
        final void split(HashMap<K,V> map, Node<K,V>[] tab, int index, int bit) {
            TreeNode<K,V> b = this;
            TreeNode<K,V> loHead = null, loTail = null;
            TreeNode<K,V> hiHead = null, hiTail = null;
            int lc = 0,hc =0;
            for(TreeNode<K,V> e = b,next;e != null;e = next){
                next = (TreeNode<K,V>)e.next;
                e.next = null;

                //例如：
                //      n-1  :  0000 0000 0000 0000 0000 0000 0000 1111
                //hash1(key1) : 1111 1111 1111 1111 0000 1111 0000 0101  &-》  0000 0000 0000 0000 0000 0000 0000 0101
                //hash2(key2) : 1111 1111 1111 1111 0000 1111 0001 0101  &-》  0000 0000 0000 0000 0000 0000 0000 0101
                // key1.index: 5  key2.index : 5

                //扩容之后：
                //      n-1  :  0000 0000 0000 0000 0000 0000 0001 1111
                //hash1(key1) : 1111 1111 1111 1111 0000 1111 0000 0101  &-》  0000 0000 0000 0000 0000 0000 0000 0101
                //hash2(key2) : 1111 1111 1111 1111 0000 1111 0001 0101  &-》  0000 0000 0000 0000 0000 0000 0001 0101
                //key1.index: 5  key2.index : 5 + 16 = 21
                if((e.hash & bit) == 0){
                    if((e.prev = loTail) == null){
                        loHead = e;
                    }else{
                        loTail.next = e;
                    }
                    loTail = e;
                    ++lc;
                }
                else{
                    if((e.prev = hiTail) == null){
                        hiHead = e;
                    }else{
                        hiTail.next = e;
                    }
                    hiTail = e;
                    ++hc;
                }
            }

            if(loHead != null){
                //如果在哈希表扩容时，发现链表长度小于 6，则会由树重新退化为链表。
                //否则的话链表转换成红黑树
                if(lc <= UNTREEIFY_THRESHOLD){
                    tab[index] = loHead.untreeify(map);
                }else{
                    tab[index] = loHead;
                    if(loHead != null)
                        loHead.treeify(tab);
                }
            }

            //如果在哈希表扩容时，发现链表长度小于 6，则会由树重新退化为链表。
            //否则的话链表转换成红黑树
            if(hiHead != null){
                if(hc <= UNTREEIFY_THRESHOLD){
                    tab[index + bit] = hiHead.untreeify(map);
                }else {
                    tab[index + bit] = hiHead;
                    if (loHead != null)
                        hiHead.treeify(tab);
                }
            }
        }


        /**
         * 从调用此方法的结点开始查找, 通过hash值和key找到对应的节点
         * 此处是红黑树的遍历, 红黑树是特殊的自平衡二叉查找树
         * 平衡二叉查找树的特点：左节点<根节点<右节点
         */
        final TreeNode<K,V> find(int h, Object k, Class<?> kc) {
            // this为调用此方法的节点
            TreeNode<K,V> p = this;
            do{
                int ph,dir; K pk;
                TreeNode<K,V> pl = p.left, pr = p.right, q;
                // 传入的hash值小于p节点的hash值, 则往p节点的左边遍历
                if(h < (ph = p.hash))
                    p = pl;
                // 传入的hash值大于p节点的hash值, 则往p节点的右边遍历
                else if(h > ph)
                    p = pr;
                // 传入的hash值和key值等于p节点的hash值和key值,则p节点为目标节点,返回p节点
                else if ((pk = p.key) == k || (k != null && k.equals(pk)))
                    return p;
                // p节点的左节点为空则将向右遍历
                else if (pl == null)
                    p = pr;
                // p节点的右节点为空则将向左遍历
                else if (pr == null)
                    p = pl;
                else if ((kc != null ||
                        // 如果传入的key(k)所属的类实现了Comparable接口,则将传入的key跟p节点的key比较
                        (kc = comparableClassFor(k)) != null) && // 此行不为空代表k实现了Comparable
                        (dir = compareComparables(kc, k, pk)) != 0)//k<pk则dir<0, k>pk则dir>0
                    p = (dir < 0) ? pl : pr;    // k < pk则向左遍历(p赋值为p的左节点), 否则向右遍历
                    // 代码走到此处, 代表key所属类没有实现Comparable, 直接指定向p的右边遍历
                else if ((q = pr.find(h, k, kc)) != null)
                    return q;
                else// 代码走到此处代表上一个向右遍历（pr.find(h, k, kc)）为空, 因此直接向左遍历
                    p = pl;
            }while (p != null);
            return null;
        }


        /**
         * 当存在hash碰撞的时候，且元素数量大于8个时候，就会以红黑树的方式将这些元素组织起来
         * map 当前节点所在的HashMap对象
         * tab 当前HashMap对象的元素数组
         * h   指定key的hash值
         * k   指定key
         * v   指定key上要写入的值
         * 返回：指定key所匹配到的节点对象，针对这个对象去修改V（返回空说明创建了一个新节点）
         */
        final TreeNode<K,V> putTreeVal(HashMap<K,V> map, Node<K,V>[] tab,
                                       int h, K k, V v) {
            Class<?> kc = null; // 定义k的Class对象
            boolean searched = false; // 标识是否已经遍历过一次树，未必是从根节点遍历的，但是遍历路径上一定已经包含了后续需要比对的所有节点。
            TreeNode<K,V> root = (parent != null) ? root() : this; // 父节点不为空那么查找根节点，为空那么自身就是根节点
            for(TreeNode<K,V> p = root;;){ // 从根节点开始遍历，没有终止条件，只能从内部退出
                int dir = 0,ph = 0; K pk;  // 声明方向、当前节点hash值、当前节点的键对象
                if((ph = p.hash) > h) // 如果当前节点hash 大于 指定key的hash值
                    dir = -1; //要将指定的key 插入到该节点的左侧
                else if(ph < h) //如果当前节点hash 小于 指定key的hash值
                    dir = 1; //要将指定的key插入到该节点的右侧
                else if((pk = p.key) == k || (k != null && k.equals(pk)))
                    //要添加的就是该节点
                    return p;
                // 走到这一步说明 当前节点的hash值  和 指定key的hash值  是相等的，但是equals不等
                else if ((kc == null &&
                        (kc = comparableClassFor(k)) == null) ||
                        (dir = compareComparables(kc, k, pk)) == 0) {
                     /*
                      * searched 标识是否已经对比过当前节点的左右子节点了
                      * 如果还没有遍历过，那么就递归遍历对比，看是否能够得到那个键对象equals相等的的节点
                      * 如果得到了键的equals相等的的节点就返回
                      * 如果还是没有键的equals相等的节点，那说明应该创建一个新节点了
                      */
                    if (!searched) { // 如果还没有比对过当前节点的所有子节点
                        TreeNode<K,V> q, ch; // 定义要返回的节点、和子节点
                        searched = true; // 标识已经遍历过一次了
                        /*
                         * 红黑树也是二叉树，所以只要沿着左右两侧遍历寻找就可以了
                         * 这是个短路运算，如果先从左侧就已经找到了，右侧就不需要遍历了
                         * find 方法内部还会有递归调用。参见：find方法解析
                         */
                        if (((ch = p.left) != null &&
                                (q = ch.find(h, k, kc)) != null) ||
                                ((ch = p.right) != null &&
                                        (q = ch.find(h, k, kc)) != null))
                            return q; // 找到了指定key键对应的
                    }
                    // 走到这里就说明，遍历了所有子节点也没有找到和当前键equals相等的节点
                    // dir = tieBreakOrder(k, pk); // 再比较一下当前节点键和指定key键的大小
                }
                TreeNode<K,V> xp = p;  // 定义xp指向当前节点
                /*
                 * 如果dir小于等于0，那么看当前节点的左节点是否为空，如果为空，就可以把要添加的元素作为当前节点的左节点，如果不为空，还需要下一轮继续比较
                 * 如果dir大于等于0，那么看当前节点的右节点是否为空，如果为空，就可以把要添加的元素作为当前节点的右节点，如果不为空，还需要下一轮继续比较
                 * 如果以上两条当中有一个子节点不为空，这个if中还做了一件事，那就是把p已经指向了对应的不为空的子节点，开始下一轮的比较
                 */
                if((p = (dir <= 0) ? p.left : p.right) == null){
                    // 如果恰好要添加的方向上的子节点为空，此时节点p已经指向了这个空的子节点
                    Node<K,V> xpn = xp.next;
                    TreeNode<K,V> x = map.newTreeNode(h,k,v,xpn); //创建一个新的树节点
                    if(dir <= 0)
                        xp.left = x; // 左孩子指向到这个新的树节点
                    else
                        xp.right = x; // 右孩子指向到这个新的树节点
                    xp.next = x;
                    x.parent = x.prev = xp; // 这个新的树节点的父节点、前节点均设置为 当前的树节点
                    if(xpn != null){ //如果原来的节点不为空
                        ((TreeNode<K,V>) xpn).prev = x;// 那么原来的next节点的前节点指向到新的树节点
                    }
                    moveRootToFront(tab, balanceInsertion(root, x));// 重新平衡，以及新的根节点置顶
                    return null;
                }
            }
        }


        /**
         * 节点的删除（待定）
         * @param map
         * @param tab
         * @param movable
         */
        final void removeTreeNode(HashMap<K,V> map, Node<K,V>[] tab,
                                  boolean movable) {
            int n;
            if (tab == null || (n = tab.length) == 0)
                return;
            int index = (n - 1) & hash;
            TreeNode<K,V> first = (TreeNode<K,V>)tab[index], root = first, rl;
            TreeNode<K,V> succ = (TreeNode<K,V>)next, pred = prev;
            if (pred == null)
                tab[index] = first = succ;
            else
                pred.next = succ;
            if (succ != null)
                succ.prev = pred;
            if (first == null)
                return;
            if (root.parent != null)
                root = root.root();
            if (root == null || root.right == null ||
                    (rl = root.left) == null || rl.left == null) {
                tab[index] = first.untreeify(map);  // too small
                return;
            }
            TreeNode<K,V> p = this, pl = left, pr = right, replacement;
            if (pl != null && pr != null) {
                TreeNode<K,V> s = pr, sl;
                while ((sl = s.left) != null) // find successor
                    s = sl;
                boolean c = s.red; s.red = p.red; p.red = c; // swap colors
                TreeNode<K,V> sr = s.right;
                TreeNode<K,V> pp = p.parent;
                if (s == pr) { // p was s's direct parent
                    p.parent = s;
                    s.right = p;
                }
                else {
                    TreeNode<K,V> sp = s.parent;
                    if ((p.parent = sp) != null) {
                        if (s == sp.left)
                            sp.left = p;
                        else
                            sp.right = p;
                    }
                    if ((s.right = pr) != null)
                        pr.parent = s;
                }
                p.left = null;
                if ((p.right = sr) != null)
                    sr.parent = p;
                if ((s.left = pl) != null)
                    pl.parent = s;
                if ((s.parent = pp) == null)
                    root = s;
                else if (p == pp.left)
                    pp.left = s;
                else
                    pp.right = s;
                if (sr != null)
                    replacement = sr;
                else
                    replacement = p;
            }
            else if (pl != null)
                replacement = pl;
            else if (pr != null)
                replacement = pr;
            else
                replacement = p;
            if (replacement != p) {
                TreeNode<K,V> pp = replacement.parent = p.parent;
                if (pp == null)
                    root = replacement;
                else if (p == pp.left)
                    pp.left = replacement;
                else
                    pp.right = replacement;
                p.left = p.right = p.parent = null;
            }

            TreeNode<K,V> r = p.red ? root : balanceDeletion(root, replacement);

            if (replacement == p) {  // detach
                TreeNode<K,V> pp = p.parent;
                p.parent = null;
                if (pp != null) {
                    if (p == pp.left)
                        pp.left = null;
                    else if (p == pp.right)
                        pp.right = null;
                }
            }
            if (movable)
                moveRootToFront(tab, r);
        }


            /**
             * 如果当前索引位置的头节点不是root节点, 则将root的上一个节点和下一个节点进行关联,
             * 将root放到头节点的位置, 原头节点放在root的next节点上
             */
        static <K,V> void moveRootToFront(Node<K,V>[] tab, TreeNode<K,V> root){
            int n;
            if(root !=null && tab != null && (n = tab.length) > 0){
                //寻找该root节点所对应的索引
                int index = (n-1) & root.hash;
                //该索引下的第一个节点
                TreeNode<K,V> first = (TreeNode<K, V>) tab[index];
                if(root != first){  // 如果root节点不是该索引位置的头节点
                    Node<K,V> rn;
                    tab[index] = root;
                    TreeNode<K,V> rp = root.prev;

                    // 如果root节点的下一个节点不为空,
                    // 则将root节点的下一个节点的prev属性设置为root节点的上一个节点
                    if((rn = root.next) != null)
                        ((TreeNode<K,V>)rn).prev = rp;
                    // 如果root节点的上一个节点不为空,
                    // 则将root节点的上一个节点的next属性设置为root节点的下一个节点
                    if(rp != null)
                        rp.next = rn;
                    if(first != null) // 如果原头节点不为空, 则将原头节点的prev属性设置为root节点
                        first.prev = root;
                    root.next = first;  // 将root节点的next属性设置为原头节点
                    root.prev = null;
                }
                assert checkInvariants(root);   // 检查红黑树是否正常
            }
        }


        /**
         * 红黑树插入一个红色节点之后如何保持红黑树的平衡性
         * @param root
         * @param x
         * @param <K>
         * @param <V>
         * @return
         */
        static <K,V> TreeNode<K,V> balanceInsertion(TreeNode<K,V> root,
                                                    TreeNode<K,V> x) {
            x.red = true;//保证插入的节点是红色的
            //xp:x.p  xpp:x.p.p xppl:x.p.p.left xppr:x.p.p.right
            for(TreeNode<K,V> xp, xpp, xppl, xppr;;){
                //只有一个节点x，那么为了满足红黑树的性质要求x为黑节点
                if((xp = x.parent)==null){
                    x.red = false;
                    return x;
                }
                //如果插入节点的父节点要是黑色，依然直接返回
                else if((!xp.red) || (xpp = xp.parent)==null)
                    return root;
                //x.p == x.p.p.left
                if (xp == (xppl = xpp.left)) {
                    if ((xppr = xpp.right) != null && xppr.red) {
                        //case 1
                        xppr.red = false;
                        //变色：x.p 为黑；x.p.p为红
                        xp.red = false;
                        xpp.red = true;
                        x = xpp;
                    }else{
                        //case2：左旋
                        if (x == xp.right) {
                            root = rotateLeft(root, x = xp);
                            xpp = (xp = x.parent) == null ? null : xp.parent;
                        }
                        //case3：右旋
                        if (xp != null) {
                            xp.red = false;
                            if (xpp != null) {
                                xpp.red = true;
                                root = rotateRight(root, xpp);
                            }
                        }
                    }
                }
                //x.p == x.p.p.right
                else{
                    //case1
                    if (xppl != null && xppl.red) {
                        xppl.red = false;
                        xp.red = false;
                        xpp.red = true;
                        x = xpp;
                    }
                    //case2
                    if (x == xp.left) {
                        root = rotateRight(root, x = xp);
                        xpp = (xp = x.parent) == null ? null : xp.parent;
                    }
                    //case3
                    if (xp != null) {
                        xp.red = false;
                        if (xpp != null) {
                            xpp.red = true;
                            root = rotateLeft(root, xpp);
                        }
                    }
                }
            }
        }


        /**
         * 红黑树删除指定节点之后如何保持红黑树的平衡性
         * @param root
         * @param x
         * @param <K>
         * @param <V>
         * @return
         */
        static <K,V> TreeNode<K,V> balanceDeletion(TreeNode<K,V> root,
                                                   TreeNode<K,V> x){
            for(TreeNode<K,V> xp,xpl,xpr;;){
                if(x == null || x == root)
                    return root;
                else if((xp = x.parent)==null){
                    x.red = false;
                    return x;
                }
                else if(x.red){
                    x.red = false;
                    return root;
                }
                //x为xp的左节点
                else if((xpl = xp.left) == x){
                    //情况1：x的兄弟节点w是红色的
                    if((xpr = xp.right) != null && xpr.red){
                        xpr.red = false;
                        xp.red = true;
                        //左旋
                        root = rotateLeft(root, xp);
                        xpr = (xp = x.parent) == null ? null : xp.right;
                    }
                    if(xpr == null)
                        x = xp;
                    else{
                        TreeNode<K,V> sl = xpr.left,sr = xpr.right;
                        //情况2：x的兄弟节点w是黑色的，而且w的两个子节点都是黑色的
                       if((sr == null || !sr.red) &&
                               sl == null || !sl.red){
                            xpr.red = true;
                            x = xp;
                       }
                       else{
                           //情况3：x的兄弟节点w是黑色的，w的左孩子是红色的，w的右孩子是黑色的
                            if((sr == null || !sr.red)){
                                if (sl != null)
                                    sl.red = false;
                                xpr.red = true;
                                root = rotateRight(root,xpr);
                                xpr = (xp = x.parent) == null ? null : xp.right;
                            }
                            //情况4：x的兄弟节点w是黑色的，且w的右孩子是红色的
                           if (xpr != null) {
                               xpr.red = (xp == null) ? false : xp.red;
                               if ((sr = xpr.right) != null)
                                   sr.red = false;
                           }
                           if (xp != null) {
                               xp.red = false;
                               root = rotateLeft(root, xp);
                           }
                           x = root;
                       }
                    }
                }
                //x为xp的右节点
                else{
                    if (xpl != null && xpl.red) {
                        xpl.red = false;
                        xp.red = true;
                        root = rotateRight(root, xp);
                        xpl = (xp = x.parent) == null ? null : xp.left;
                    }
                    if (xpl == null)
                        x = xp;
                    else {
                        TreeNode<K,V> sl = xpl.left, sr = xpl.right;
                        if ((sl == null || !sl.red) &&
                                (sr == null || !sr.red)) {
                            xpl.red = true;
                            x = xp;
                        }
                        else {
                            if (sl == null || !sl.red) {
                                if (sr != null)
                                    sr.red = false;
                                xpl.red = true;
                                root = rotateLeft(root, xpl);
                                xpl = (xp = x.parent) == null ?
                                        null : xp.left;
                            }
                            if (xpl != null) {
                                xpl.red = (xp == null) ? false : xp.red;
                                if ((sl = xpl.left) != null)
                                    sl.red = false;
                            }
                            if (xp != null) {
                                xp.red = false;
                                root = rotateRight(root, xp);
                            }
                            x = root;
                        }
                    }
                }
            }

        }



        /**
         * 左旋
         * @param root
         * @param p
         * @param <K>
         * @param <V>
         * @return
         */
        static <K,V> TreeNode<K,V> rotateLeft(TreeNode<K,V> root,
                                              TreeNode<K,V> p) {
            TreeNode<K,V> r, pp, rl;
            if(p != null && (r = p.right) != null){
                //rl是算法导论中的β节点
                if((rl = p.right = r.left) != null)
                    rl.parent = p;
                else if((pp = rl.parent = p.parent) == null)
                    (root = r).red = false;
                //pp节点为算法导论中p节点的父节点
                else if(pp.left == p)
                    pp.left = r;
                else
                    pp.right = r;
                r.left = p;
                p.parent = r;
            }
            return root;
        }


        /**
         * 右旋
         * @param root
         * @param p
         * @param <K>
         * @param <V>
         * @return
         */
        static <K,V> TreeNode<K,V> rotateRight(TreeNode<K,V> root,
                                              TreeNode<K,V> p) {
            TreeNode<K,V> l, pp, lr;
            if(p != null && (l = p.left) != null){
                if((lr = p.left = l.right) != null)
                    lr.parent = p;
                else if((pp = lr.parent = p.parent) == null)
                    (root = l).red = false;
                else if(pp.right == p)
                    pp.right = l;
                else
                    pp.left = l;
                l.right = p;
                p.parent = l;
            }
            return root;
        }

        /**
         * Recursive invariant check
         * 红黑树的 5 大性质
         */
        static <K,V> boolean checkInvariants(TreeNode<K,V> t) {
            TreeNode<K,V> tp = t.parent, tl = t.left, tr = t.right,
                    tb = t.prev, tn = (TreeNode<K,V>)t.next;
            if (tb != null && tb.next != t)
                return false;
            if (tn != null && tn.prev != t)
                return false;
            if (tp != null && t != tp.left && t != tp.right)
                return false;
            if (tl != null && (tl.parent != t || tl.hash > t.hash))
                return false;
            if (tr != null && (tr.parent != t || tr.hash < t.hash))
                return false;
            if (t.red && tl != null && tl.red && tr != null && tr.red)
                return false;
            if (tl != null && !checkInvariants(tl))
                return false;
            if (tr != null && !checkInvariants(tr))
                return false;
            return true;
        }


    }

    //为了模拟LinkedHashMap中的Entry
    static class Entry<K,V> extends HashMap.Node<K,V> {
        Entry<K,V> before, after;
        Entry(int hash, K key, V value, Node<K,V> next) {
            super(hash, key, value, next);
        }
    }

    /**
     * 不是重点，是通过java中的反射机制来判断元素之间的关系
     * @param x
     * @return
     */
    static Class<?> comparableClassFor(Object x) {
        if (x instanceof Comparable) {
            Class<?> c; Type[] ts, as; Type t; ParameterizedType p;
            if ((c = x.getClass()) == String.class) // bypass checks
                return c;
            if ((ts = c.getGenericInterfaces()) != null) {
                for (int i = 0; i < ts.length; ++i) {
                    if (((t = ts[i]) instanceof ParameterizedType) &&
                            ((p = (ParameterizedType)t).getRawType() ==
                                    Comparable.class) &&
                            (as = p.getActualTypeArguments()) != null &&
                            as.length == 1 && as[0] == c) // type arg is c
                        return c;
                }
            }
        }
        return null;
    }

    @SuppressWarnings({"rawtypes","unchecked"}) // for cast to Comparable
    static int compareComparables(Class<?> kc, Object k, Object x) {
        return (x == null || x.getClass() != kc ? 0 :
                ((Comparable)k).compareTo(x));
    }

    // For conversion from TreeNodes to plain nodes
    Node<K,V> replacementNode(Node<K,V> p, Node<K,V> next) {
        return new Node<>(p.hash, p.key, p.value, next);
    }

}
