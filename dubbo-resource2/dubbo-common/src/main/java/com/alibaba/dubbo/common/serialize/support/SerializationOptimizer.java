package com.alibaba.dubbo.common.serialize.support;

import java.util.Collection;

/**
 * Created by gaojianqun on 2018/3/2.
 */
public interface SerializationOptimizer {

    Collection<Class> getSerializableClasses();
}
