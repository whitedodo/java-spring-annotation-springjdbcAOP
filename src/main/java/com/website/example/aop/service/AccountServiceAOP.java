/*
 * 	주제(Subject): 자바 환경설정 기반의 Spring Framework 4.2.4(Spring JDBC) 트랜젝션 AOP 구현
 *  파일명(Filename): AccountServiceAOP.java
 *  저자(Author): Dodo / rabbit.white at daum dot net
 *  생성일자(Create date): 2020-10-10
 *  설명(Description):
 * 
 */

package com.website.example.aop.service;

import java.sql.SQLException;


import com.website.example.vo.AccountVO;


public interface AccountServiceAOP {

	void accountCreate(AccountVO vo) throws SQLException;
	void accountTransfer(String sender, String receiver, int money) throws SQLException;
	
}
