/*
 * 	주제(Subject): 자바 환경설정 기반의 Spring Framework 4.2.4(Spring JDBC) 트랜젝션 AOP 구현
 *  파일명(Filename): MainTestAOP.java
 *  저자(Author): Dodo / rabbit.white at daum dot net
 *  생성일자(Create date): 2020-10-10
 *  설명(Description):
 * 
 */

package com.website.example.unit;

import java.sql.SQLException;

import java.sql.Timestamp;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.website.example.common.MyDataSourceFactory;
import com.website.example.common.RootConfigAOP;
import com.website.example.dao.AccountDAOImpl;
import com.website.example.aop.service.AccountServiceAOP;
import com.website.example.aop.service.AccountServiceImplAOP;
import com.website.example.vo.AccountVO;


class MainTestAOP {

	@Test
	void test() throws SQLException {
		
		@SuppressWarnings("resource")
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(RootConfigAOP.class);
		
		AccountServiceAOP service = (AccountServiceAOP) applicationContext.getBean("accountServiceImplAOP");
		
		AccountVO vo = new AccountVO();
		
		// 1. 계정 생성
		vo.setName("홍길동2");
		vo.setBalance(10000);
		vo.setRegidate(Timestamp.valueOf("2020-01-20 11:05:20"));
		service.accountCreate(vo);
		
		// 2. 계정 생성
		vo.setName("홍길자2");
		vo.setBalance(0);
		vo.setRegidate(Timestamp.valueOf("2020-01-20 23:05:20"));
		service.accountCreate(vo);
		
		// 3. 거래 처리
		service.accountTransfer("홍길동", "홍길자", 500);
		
		
	}

}
