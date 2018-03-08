package com.alibaba.dubbo.common.serialize.support;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by gaojianqun on 2018/3/2.
 */
public abstract class SerializableClassRegistry {

    private static final Set<Class> registrations = new LinkedHashSet<Class>();

    /**
     * only supposed to be called at startup time
     */
    public static void registerClass(Class clazz) {
        registrations.add(clazz);
    }

    public static Set<Class> getRegisteredClasses() {
        return registrations;
    }
}
