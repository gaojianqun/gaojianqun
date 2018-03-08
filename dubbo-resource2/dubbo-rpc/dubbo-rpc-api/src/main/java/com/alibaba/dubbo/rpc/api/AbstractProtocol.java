package com.alibaba.dubbo.rpc.api;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.alibaba.dubbo.common.utils.ConcurrentHashSet;
import com.alibaba.dubbo.rpc.Exporter;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Protocol;
import com.alibaba.dubbo.rpc.support.ProtocolUtils;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by gaojianqun on 2018/3/1.
 */
public abstract class AbstractProtocol implements Protocol{
    protected Logger logger = LoggerFactory.getLogger(getClass());

    protected final Map<String,Exporter<?>> exporterMap = new ConcurrentHashMap<String, Exporter<?>>();

    protected final Set<Invoker<?>> invokers = new ConcurrentHashSet<Invoker<?>>();

    /**
     * 判断此处的URL地址是否是注册中心的地址
     * 如果是注册中心的地址则调用RegistryProtocal,如果不是注册中心的地址则调用DubboProtocal
     * @param url
     * @return
     */
    protected static String serviceKey(URL url){
        int port = url.getParameter("bind.port",url.getPort());
        return serviceKey(port,url.getPath(),url.getParameter("version"),url.getParameter("group"));
    }

    protected static String serviceKey(int port,String serviceName,String serviceVersion,String serviceGroup){
        return ProtocolUtils.serviceKey(port,serviceName,serviceVersion,serviceGroup);
    }

    public void destroy(){

        /**
         * ConcerruntHashSet是dubbo根据ConcerrentHashMap封装的
         * 可以巧妙的避免集合中删除对象报错的现象
         */
        for (Invoker<?> invoker : invokers) {
            if (invoker != null) {
                invokers.remove(invoker);
                try {
                    if (logger.isInfoEnabled()) {
                        logger.info("Destroy reference: " + invoker.getUrl());
                    }
                    invoker.destroy();
                } catch (Throwable t) {
                    logger.warn(t.getMessage(), t);
                }
            }
        }

        /**
         * 利用ConcerruntHashMap删除所有的exporter
         * Exporter:暴露端口的服务
         */
        for (String key : new ArrayList<String>(exporterMap.keySet())) {
            Exporter<?> exporter = exporterMap.remove(key);
            if (exporter != null) {
                try {
                    if (logger.isInfoEnabled()) {
                        logger.info("Unexport service: " + exporter.getInvoker().getUrl());
                    }
                    exporter.unexport();
                } catch (Throwable t) {
                    logger.warn(t.getMessage(), t);
                }
            }
        }
    }

}
