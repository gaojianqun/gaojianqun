package cn.Hash;

/**
 * 实现java中的API
 * 定义一个字典
 * Created by gaojianqun on 2018/8/20.
 */
public abstract class Dictionary<K,V> {

    //构造字典
    public Dictionary(){

    }

    //字典的大小
    /**
     * Returns the number of entries (distinct keys) in this dictionary.
     *
     * @return  the number of keys in this dictionary.
     */
    abstract public int size();

    //判断字典是否为空
    /**
     * Tests if this dictionary maps no keys to value. The general contract
     * for the <tt>isEmpty</tt> method is that the result is true if and only
     * if this dictionary contains no entries.
     *
     * @return  <code>true</code> if this dictionary maps no keys to values;
     *          <code>false</code> otherwise.
     */
    abstract public boolean isEmpty();

    //根据键获取值
    /**
     * Returns the value to which the key is mapped in this dictionary.
     * The general contract for the <tt>isEmpty</tt> method is that if this
     * dictionary contains an entry for the specified key, the associated
     * value is returned; otherwise, <tt>null</tt> is returned.
     *
     * @return  the value to which the key is mapped in this dictionary;
     * @param   key   a key in this dictionary.
     *          <code>null</code> if the key is not mapped to any value in
     *          this dictionary.
     * @exception NullPointerException if the <tt>key</tt> is <tt>null</tt>.
     * @see     java.util.Dictionary#put(java.lang.Object, java.lang.Object)
     */
    abstract public V get(Object key);

    //将键和值放入到字典中
    /**
     * Maps the specified <code>key</code> to the specified
     * <code>value</code> in this dictionary. Neither the key nor the
     * value can be <code>null</code>.
     * <p>
     * If this dictionary already contains an entry for the specified
     * <tt>key</tt>, the value already in this dictionary for that
     * <tt>key</tt> is returned, after modifying the entry to contain the
     *  new element. <p>If this dictionary does not already have an entry
     *  for the specified <tt>key</tt>, an entry is created for the
     *  specified <tt>key</tt> and <tt>value</tt>, and <tt>null</tt> is
     *  returned.
     * <p>
     * The <code>value</code> can be retrieved by calling the
     * <code>get</code> method with a <code>key</code> that is equal to
     * the original <code>key</code>.
     *
     * @param      key     the hashtable key.
     * @param      value   the value.
     * @return     the previous value to which the <code>key</code> was mapped
     *             in this dictionary, or <code>null</code> if the key did not
     *             have a previous mapping.
     * @exception  NullPointerException  if the <code>key</code> or
     *               <code>value</code> is <code>null</code>.
     * @see        java.lang.Object#equals(java.lang.Object)
     * @see        java.util.Dictionary#get(java.lang.Object)
     */
    abstract public V put(K key,V value);


    //根据键删除一个元素
    /**
     * Removes the <code>key</code> (and its corresponding
     * <code>value</code>) from this dictionary. This method does nothing
     * if the <code>key</code> is not in this dictionary.
     *
     * @param   key   the key that needs to be removed.
     * @return  the value to which the <code>key</code> had been mapped in this
     *          dictionary, or <code>null</code> if the key did not have a
     *          mapping.
     * @exception NullPointerException if <tt>key</tt> is <tt>null</tt>.
     */
    abstract public V remove(Object key);
}
