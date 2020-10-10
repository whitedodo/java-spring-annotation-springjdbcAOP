/*
 * 	주제(Subject): 자바 환경설정 기반의 Spring Framework 4.2.4(Spring JDBC) 트랜젝션 AOP 구현
 *  파일명(Filename): DBConfig.java
 *  저자(Author): Dodo / rabbit.white at daum dot net
 *  생성일자(Create date): 2020-10-10
 *  설명(Description):
 * 
 */

package com.website.example.common;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.website.example.dao.AccountDAO;
import com.website.example.dao.AccountDAOImpl;
import com.website.example.service.AccountService;
import com.website.example.service.AccountServiceImpl;

@Configuration
@EnableTransactionManagement
public class DBConfig {

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }
    
    @Bean
    public DataSource dataSource() {
    	
    	DataSource dataSource = new MyDataSourceFactory().getOracleDataSource();
    	
    	/* Apache DBCP
    	 
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/testDb?useUnicode=true&characterEncoding=utf8");
        dataSource.setUsername("test");
        dataSource.setPassword("test123!@#");
        
        */
        return dataSource;
    }
    
    @Bean
    public AccountDAO accountDAOImpl() {
    	
    	AccountDAO dao = new AccountDAOImpl(dataSource());
    	return dao;
    }
    
    @Bean
    public AccountService accountServiceImpl() {
    	
    	AccountService service = new AccountServiceImpl(dataSource());
    	return service;
    }
    
}
