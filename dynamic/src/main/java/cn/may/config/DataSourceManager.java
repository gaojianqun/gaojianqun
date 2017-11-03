package cn.may.config;

import javax.xml.crypto.Data;

/**
 * Created by Administrator on 2017/11/2.
 * 操作数据源
 * 数据源A为主数据源
 */
public class DataSourceManager {

    private static final ThreadLocal<DataSources> dataSources = new ThreadLocal<DataSources>(){
        //注入默认数据源A
        @Override
        protected DataSources initialValue() {
            return DataSources.DATASOURCE_A;
        }
    };

    public static DataSources get() {
        return dataSources.get();
    }

    public static void setDataSources(DataSources dataSourcesType){
        dataSources.set(dataSourcesType);
    }

    public static void reset(){
        dataSources.set(DataSources.DATASOURCE_A);
    }

    public static void remove(){
        dataSources.remove();
    }

}
