package com.gwang.spring.mybatis.demo.datasource;

public class DataSourceTypeManager {
	/**
	 * 如果对ThreadLocal设置默认值则会使applicationContext-mybatis.xml里multipleDataSource的defaultTargetDataSource失效
	 * 因为在AbstractRoutingDataSource的源码里getConnection()的determineTargetDataSource()方法里是先去拿multipleDataSource
	 * 的key，如果拿不到再走defaultTargetDataSource，而ThreadLocal的默认值会使multipleDataSource取到key。
	 */
//	private static final ThreadLocal<DataSources> dataSourceTypes = new ThreadLocal<DataSources>(){
//        @Override
//        protected DataSources initialValue(){
//            return DataSources.MASTER;
//        }
//    };
	private static final ThreadLocal<DataSources> dataSourceTypes = new ThreadLocal<DataSources>();
     
    public static DataSources get(){
        return dataSourceTypes.get();
    }
     
    public static void set(DataSources dataSourceType){
        dataSourceTypes.set(dataSourceType);
    }
     
    public static void reset(){
        dataSourceTypes.set(DataSources.SLAVE);
    }
}
