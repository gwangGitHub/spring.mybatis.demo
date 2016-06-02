package com.gwang.spring.mybatis.demo.datasource;

public class DataSourceTypeManager {
	private static final ThreadLocal<DataSources> dataSourceTypes = new ThreadLocal<DataSources>(){
        @Override
        protected DataSources initialValue(){
            return DataSources.SLAVE;
        }
    };
     
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
