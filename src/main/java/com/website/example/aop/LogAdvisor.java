/*
 * 	주제(Subject): 자바 환경설정 기반의 Spring Framework 4.2.4(Spring JDBC) 트랜젝션 AOP 구현
 *  파일명(Filename): LogAdvisor.java
 *  저자(Author): Dodo / rabbit.white at daum dot net
 *  생성일자(Create date): 2020-10-10
 *  설명(Description):
 * 
 */

package com.website.example.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Service;

//관점, 서비스
@Aspect
@Service
public class LogAdvisor {

	// 2단계 - 전 단계 시야)
	
		@Before("execution(* com.website.example.aop.service.AccountServiceAOP.*(..))")
		// @Before("execution(public void sum())")
		// 반환값 없어도 무방
	    public void logBefore() { 
			System.out.println("전 단계");

	    }
	
}
