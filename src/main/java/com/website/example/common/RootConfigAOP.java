/*
 * 	주제(Subject): 자바 환경설정 기반의 Spring Framework 4.2.4(Spring JDBC) 트랜젝션 AOP 구현
 *  파일명(Filename): RootConfigAOP.java
 *  저자(Author): Dodo / rabbit.white at daum dot net
 *  생성일자(Create date): 2020-10-10
 *  설명(Description):
 * 
 */

package com.website.example.common;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@Import({DBConfigAOP.class})
@ComponentScan(basePackages = {"com.website.example"})
@EnableAspectJAutoProxy
//@ComponentScan(basePackages = {"com.local.example.beans", "com.local.example.advisor"})
public class RootConfigAOP {



}