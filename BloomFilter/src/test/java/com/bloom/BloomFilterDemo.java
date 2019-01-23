package com.bloom;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.util.*;

/**
 * 测试布隆过滤器的正确与误判
 *
 *  往布隆过滤器里放置100W个元素
 *  测试100个存在的元素和9900个不存在的元素
 */
public class BloomFilterDemo {

    private static final int insertions = 1000000;

    public static void main(String [] args){
        //初始化一个存储String数据的布隆过滤器，初始化大小为100W
        //默认误判率是0.03
        BloomFilter<String> bloomFilter = BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8),insertions);
        //用于存放所有实际存在的key,判断key是否存在
        Set<String> set = new HashSet<>(insertions);

        //用于存放所有实际存在的key,可以取出使用
        List<String> list = new ArrayList<>(insertions);

        //向三个容器初始化100W个随机并唯一的字符串
        for(int i = 0;i<insertions;i++){
            String uuid = UUID.randomUUID().toString();
            bloomFilter.put(uuid);
            set.add(uuid);
            list.add(uuid);

        }
    }

}
