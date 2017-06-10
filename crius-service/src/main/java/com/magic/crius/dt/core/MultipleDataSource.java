package com.magic.crius.dt.core;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * User: joey
 * Date: 2017/6/9
 * Time: 22:06
 */
public class MultipleDataSource extends AbstractRoutingDataSource {

    private static final ThreadLocal<String> dataSourceKey = new InheritableThreadLocal<String>();

    public static void setDataSourceKey(String dataSource) {
        dataSourceKey.set(dataSource);
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return dataSourceKey.get();
    }
}
