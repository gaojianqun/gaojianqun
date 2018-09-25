package cn.Hash;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;

/**
 * 哈希表
 * java 中的哈希表是通过“拉链法”实现的哈希表
 * Created by gaojianqun on 2018/8/20.
 */
public class HashTable<K,V> extends Dictionary<K,V> implements Serializable{

    /**
     * The hash table data.
     * Entry相当于一个单向链表，哈希表的"key-value键值对"都是存储在Entry数组中的
     */
    private transient Entry<?,?>[] table;

    /**
     * The total number of entries in the hash table.
     * count是Hashtable的大小，它是Hashtable保存的索引的数量。
     */
    private transient int count;

    /**
     * The table is rehashed when its size exceeds this threshold.  (The
     * value of this field is (int)(capacity * loadFactor).)
     * threshold是Hashtable的阈值，用于判断是否需要调整Hashtable的容量。threshold的值="容量*加载因子"。
     * 也就是哈希表中现有的索引数量
     * @serial
     */
    private int threshold;

    /**
     * The load factor for the hashtable.
     * loadFactor就是加载因子
     * T= n/m n为哈希表的元素的实际数量，m为槽的数量
     * @serial
     */
    private float loadFactor;

    // Hashtable被改变的次数
    private transient int modCount;

    /** use serialVersionUID from JDK 1.0.2 for interoperability */
    private static final long serialVersionUID = 1421746759512286392L;

    /**
     * Constructs a new, empty hashtable with the specified initial
     * capacity and the specified load factor.
     * 使用指定的初始值，容量和指定的负载系数构造一个新的哈希表
     * @param      initialCapacity   the initial capacity of the hashtable.
     * @param      loadFactor        the load factor of the hashtable.
     * @exception  IllegalArgumentException  if the initial capacity is less
     *             than zero, or if the load factor is nonpositive.
     */
    public HashTable(int initialCapacity,float loadFactor){

        if(initialCapacity < 0)
            throw new IllegalArgumentException("Illegal Capacity: "+
                    initialCapacity);


        if(loadFactor <= 0 || Float.isNaN(loadFactor))
            throw new IllegalArgumentException("Illegal Load: "+loadFactor);


        if(initialCapacity==0)
            initialCapacity = 1;
        this.loadFactor = loadFactor;
        table = new Entry<?,?>[initialCapacity];
        //Math中的min方法是用来比较两个数大小的，比较结果中返回较小的那个数值
        threshold = (int)Math.min(initialCapacity * loadFactor,MAX_ARRAY_SIZE + 1);
    }

    /**
     * Constructs a new, empty hashtable with the specified initial capacity
     * and default load factor (0.75).
     * 利用指定初始值和默认加载因子0.75构造空的哈希表
     * @param     initialCapacity   the initial capacity of the hashtable.
     * @exception IllegalArgumentException if the initial capacity is less
     *              than zero.
     */
    public HashTable(int initialCapacity){
        this(initialCapacity,0.75f);
    }


    /**
     * Constructs a new, empty hashtable with a default initial capacity (11)
     * and load factor (0.75).
     */
    public HashTable(){
        this(11,0.75f);
    }


    /**
     * Constructs a new hashtable with the same mappings as the given
     * Map.  The hashtable is created with an initial capacity sufficient to
     * hold the mappings in the given Map and a default load factor (0.75).
     *
     * @param t the map whose mappings are to be placed in this map.
     * @throws NullPointerException if the specified map is null.
     * @since   1.2
     */
    public HashTable(Map<? extends K,? extends V> t){
        this(Math.max(2*t.size(),11),0.75f);
        putAll(t);
    }

    /**
     * Copies all of the mappings from the specified map to this hashtable.
     * These mappings will replace any mappings that this hashtable had for any
     * of the keys currently in the specified map.
     * 把map中的映射key - value 一一添加到哈希表中
     * @param t mappings to be stored in this map
     * @throws NullPointerException if the specified map is null
     * @since 1.2
     */
   public synchronized void putAll(Map<? extends K,? extends V> t){
       for(Map.Entry<? extends K,? extends V> e:t.entrySet()){
           put(e.getKey(),e.getValue());
       }
   }

    /**
     * Returns the number of keys in this hashtable.
     *
     * @return  the number of keys in this hashtable.
     */
    public synchronized int size() {
        return count;
    }


    /**
     * Tests if this hashtable maps no keys to values.
     *
     * @return  <code>true</code> if this hashtable maps no keys to values;
     *          <code>false</code> otherwise.
     */
    public synchronized boolean isEmpty() {
        return count==0;
    }

    /**
     * Tests if some key maps into the specified value in this hashtable.
     * This operation is more expensive than the {@link #containsKey
     * containsKey} method.
     * 判断哈希表中是否含有值value
     * <p>Note that this method is identical in functionality to
     * {@link #containsValue containsValue}, (which is part of the
     * {@link Map} interface in the collections framework).
     *
     * @param      value   a value to search for
     * @return     <code>true</code> if and only if some key maps to the
     *             <code>value</code> argument in this hashtable as
     *             determined by the <tt>equals</tt> method;
     *             <code>false</code> otherwise.
     * @exception  NullPointerException  if the value is <code>null</code>
     */
    public synchronized boolean contains(Object value){
        if(value == null){
            throw new NullPointerException();
        }

        Entry<?,?> tab[] = table;

        for(int i = tab.length; i-- >0 ; ){
            //遍历Entry中的value
            for(Entry<?,?> e = tab[i]; e !=null ; e=e.next){
                if(e.getValue().equals(value)){
                    return true;
                }
            }
        }

        return false;
    }

    public boolean containsValue(Object value) {
        return contains(value);
    }

    /**
     * Tests if the specified object is a key in this hashtable.
     * 判断一个哈希表中有没有指定的key
     * @param   key   possible key
     * @return  <code>true</code> if and only if the specified object
     *          is a key in this hashtable, as determined by the
     *          <tt>equals</tt> method; <code>false</code> otherwise.
     * @throws  NullPointerException  if the key is <code>null</code>
     * @see     #contains(Object)
     */
    public boolean containsKey(Object key){
        Entry<?,?> tab[] = table;
        int hash = key.hashCode();
        int index = (hash & 0x7FFFFFFF) % table.length;
        for(Entry<?,?> e = tab[index];e !=null ;e = e.next){
            if((e.hash == hash) && (e.key.equals(key))){
                return true;
            }
        }
        return false;
    }


    /**
     * Returns the value to which the specified key is mapped,
     * or {@code null} if this map contains no mapping for the key.
     *
     * <p>More formally, if this map contains a mapping from a key
     * {@code k} to a value {@code v} such that {@code (key.equals(k))},
     * then this method returns {@code v}; otherwise it returns
     * {@code null}.  (There can be at most one such mapping.)
     *
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or
     *         {@code null} if this map contains no mapping for the key
     * @throws NullPointerException if the specified key is null
     * @see     #put(Object, Object)
     */
    @SuppressWarnings("unchecked")
    public V get(Object key) {
        Entry<?,?> tab[] = table;
        int hash = key.hashCode();
        //首先找到哈希表的索引,之后寻找存储在“槽”中的链表中的值
        int index = (hash & 0x7FFFFFFF) % tab.length;
        for(Entry<?,?> e= tab[index] ; e != null;e = e.next){
            //只有保证哈希值和key值都相同才能命中该元素
            if((e.hash == hash) && e.key.equals(key)){
                return (V) e.value;
            }
        }
        return null;
    }


    /**
     * Increases the capacity of and internally reorganizes this
     * hashtable, in order to accommodate and access its entries more
     * efficiently.  This method is called automatically when the
     * number of keys in the hashtable exceeds this hashtable's capacity
     * and load factor.
     *
     * 动态扩容:每次动态扩容 空间为2*N+1
     */
    @SuppressWarnings("unchecked")
    public void rehash(){
        int oldCapacity = table.length;
        Entry<?,?>[] oldMap = table;

        //扩容空间为2*N+1
        int newCapacity = (oldCapacity << 1)+1;
        //判断新的扩容空间是否达到最大容量,如果达到最大容量则置为最大容量
        if(newCapacity - MAX_ARRAY_SIZE > 0){
            //判断旧的空间是否达到最大容量
            if(oldCapacity - MAX_ARRAY_SIZE > 0)
                return;
            newCapacity = MAX_ARRAY_SIZE;
        }

        //将单向链表容量扩大
        Entry<?,?> [] newMap = new Entry<?,?>[newCapacity];

        modCount++;
        //重新定义哈希表中的索引的数量
        threshold = (int)Math.min(newCapacity * loadFactor,MAX_ARRAY_SIZE+1);

        table = newMap;

        //将原哈希表中的键值映射全部存储到新的哈希表中
        for(int i = oldCapacity;i-- >0; ){
            for(Entry<K,V> old = (Entry<K, V>) oldMap[i]; old != null; ){
                Entry<K,V> e = old;
                old = old.next;

                //获取这一链表在哈希表中的“槽”的位置
                int index = (e.hash & 0x7FFFFFFF) % newCapacity;
                //将链表中的所有元素一一遍历，并放置到新链表中的“槽”的位置
                e.next = (Entry<K,V>)newMap[index];
                newMap[index] = e;
            }
        }

    }


    /**
     * Maps the specified <code>key</code> to the specified
     * <code>value</code> in this hashtable. Neither the key nor the
     * value can be <code>null</code>. <p>
     * 将key 和 value 放入到哈希表中
     * The value can be retrieved by calling the <code>get</code> method
     * with a key that is equal to the original key.
     *
     * @param      key     the hashtable key
     * @param      value   the value
     * @return     the previous value of the specified key in this hashtable,
     *             or <code>null</code> if it did not have one
     * @exception  NullPointerException  if the key or value is
     *               <code>null</code>
     * @see     Object#equals(Object)
     * @see     #get(Object)
     */
    public synchronized V put(K key, V value) {
        //插入到哈希表中的数据不能为空
        if(value == null){
            throw new NullPointerException();
        }

        //如果插入的键是相同的话，将新值替换旧值
        Entry<?,?> tab[] = table;
        int hash = key.hashCode();
        int index = (hash & 0x7FFFFFFF) % tab.length;
        @SuppressWarnings("unchecked")
        Entry<K,V> entry = (Entry<K, V>) tab[index];
        for(;entry!=null; entry = entry.next){
            if(entry.hash == hash && entry.key.equals(key)){
                V old = entry.value;
                entry.value = value;
                //把老的值返回去
                return old;
            }
        }

        // 若“Hashtable中不存在键为key的键值对”，
        // (01) 将“修改统计数”+1
        modCount++;
        // (02) 若“Hashtable实际容量” > “阈值”(阈值=总的容量 * 加载因子)
        //  则调整Hashtable的大小
        addEntry(hash, key, value, index);
        return null;
    }

    //将元素添加到哈希表中
    private void addEntry(int hash ,K key, V value, int index){
        modCount ++;

        Entry<?,?> tab[] = table;
        //判断哈希表的容量是否达到阀值
        //如果达到阀值需要扩容，再重新插入数据，如果没有达到阀值则不需要扩容直接插入数据
        if(count > threshold){
            //扩容:把哈希表的容量和单向链表的容量同时扩容
            rehash();
            tab = table;
            hash = key.hashCode();
            index = (hash & 0x7FFFFFFF) % tab.length;
        }

        //如果发生碰撞的话，将继续链表后插入数据；
        // 如果没有发生碰撞的话依旧在这个索引后的数组中添加元素
        @SuppressWarnings("unchecked")
        Entry<K,V> entry = (Entry<K, V>) tab[index];
        tab[index] = new Entry<>(hash, key, value, entry);
        count ++;
    }


    /**
     * Removes the key (and its corresponding value) from this
     * hashtable. This method does nothing if the key is not in the hashtable.
     * 根据key删除哈希表中的元素
     * @param   key   the key that needs to be removed
     * @return  the value to which the key had been mapped in this hashtable,
     *          or <code>null</code> if the key did not have a mapping
     * @throws  NullPointerException  if the key is <code>null</code>
     */
    public synchronized V remove(Object key) {
        Entry<?,?> tab[] = table;
        int hash = key.hashCode();
        int index = (hash & 0x7FFFFFFF) % table.length;

        //找到索引，之后遍历索引后链表的元素
        @SuppressWarnings("unchecked")
        Entry<K,V> e = (Entry<K, V>) tab[index];
        for(Entry<K,V> prev = null ; e != null; prev = e, e = e.next){
            if((e.hash == hash) && (e.key.equals(key))){
                modCount ++;
                if(prev != null){
                    prev.next = e.next;
                }else{
                    tab[index] = e.next;
                }

                count --;
                //把要删除的值放入到一个变量里，删除要链表中的元素
                V oldValue = e.value;
                e.value = null;
                return oldValue;
            }
        }
        return null;
    }


    /**
     * Clears this hashtable so that it contains no keys.
     * 将哈希表中的数据全部清空
     */
    public synchronized void clear(){
        Entry<?,?> tab[] = table;
        modCount ++;
        for(int index = tab.length; --index >= 0;){
            tab[index] = null;
        }
        count = 0;
    }


    /**
     * The maximum size of array to allocate.
     * Some VMs reserve some header words in an array.
     * Attempts to allocate larger arrays may result in
     * OutOfMemoryError: Requested array size exceeds VM limit
     */
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    /**
     * Hashtable bucket collision list entry
     */
    private static class Entry<K,V> implements Map.Entry<K,V> {
        final int hash;
        final K key;
        V value;
        HashTable.Entry<K,V> next;

        protected Entry(int hash, K key, V value, HashTable.Entry<K,V> next) {
            this.hash = hash;
            this.key =  key;
            this.value = value;
            this.next = next;
        }

        @SuppressWarnings("unchecked")
        protected Object clone() {
            return new HashTable.Entry<>(hash, key, value,
                    (next==null ? null : (HashTable.Entry<K,V>) next.clone()));
        }

        // Map.Entry Ops

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public V setValue(V value) {
            if (value == null)
                throw new NullPointerException();

            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }

        public boolean equals(Object o) {
            if (!(o instanceof Map.Entry))
                return false;
            Map.Entry<?,?> e = (Map.Entry<?,?>)o;

            return (key==null ? e.getKey()==null : key.equals(e.getKey())) &&
                    (value==null ? e.getValue()==null : value.equals(e.getValue()));
        }

        public int hashCode() {
            return hash ^ Objects.hashCode(value);
        }

        public String toString() {
            return key.toString()+"="+value.toString();
        }
    }

}
