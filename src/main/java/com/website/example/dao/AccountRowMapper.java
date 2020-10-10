/*
 * 	주제(Subject): 자바 환경설정 기반의 Spring Framework 4.2.4(Spring JDBC) 트랜젝션 AOP 구현
 *  파일명(Filename): AccountRowMapper.java
 *  저자(Author): Dodo / rabbit.white at daum dot net
 *  생성일자(Create date): 2020-10-10
 *  설명(Description):
 * 
 */

package com.website.example.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.website.example.vo.AccountVO;

@Service
public class AccountRowMapper implements RowMapper<AccountVO>  {

	@Override
	public AccountVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		AccountVO vo = new AccountVO();

		vo.setIdx(rs.getInt(1));
		vo.setName(rs.getString(2));
		vo.setBalance(rs.getInt(3));
		vo.setRegidate(rs.getTimestamp(4));
		
		return vo;
	}

}
