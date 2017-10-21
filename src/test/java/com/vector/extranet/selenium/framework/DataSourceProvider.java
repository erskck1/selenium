package com.vector.extranet.selenium.framework;

import javax.sql.DataSource;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class DataSourceProvider extends AbstractModule {

    @Provides
    @Singleton
    @Inject
    private DataSource getDataSource(Configuration config) {
	MysqlDataSource mysqlDataSource = new MysqlDataSource();
	mysqlDataSource.setURL(config.getDatabaseUrl());
	mysqlDataSource.setUser(config.getDatabaseUser());
	mysqlDataSource.setPassword(config.getDatabasePassword());
	return mysqlDataSource;
    }

    @Override
    protected void configure() {
    }
}
