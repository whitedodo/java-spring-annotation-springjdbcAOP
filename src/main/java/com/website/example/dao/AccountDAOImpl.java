/*
 * 	주제(Subject): 자바 환경설정 기반의 Spring Framework 4.2.4(Spring JDBC) 트랜젝션 AOP 구현
 *  파일명(Filename): AccountDAOImpl.java
 *  저자(Author): Dodo / rabbit.white at daum dot net
 *  생성일자(Create date): 2020-10-10
 *  설명(Description):
 * 
 */

package com.website.example.dao;

import java.sql.SQLException;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import com.website.example.vo.AccountVO;


public class AccountDAOImpl implements AccountDAO {

	// Spring Framework - JDBC
	private JdbcTemplate jdbcTemplate = null;
	private DataSource ds = null;
	
	private final String INSERT = "insert into account_tbl(name, balance, regidate) values(?, ?, ?)";
	private final String SELECT_BALANCE = "select * from account_tbl where name = ?";
	private final String UPDATE_MINUS = "update account_tbl set balance = (select balance from account_tbl where name = ?) - ? " +
										" where name = ?";
	private final String UPDATE_PLUS = "update account_tbl set balance = (select balance from account_tbl where name = ?) + ? " + 
										" where name = ?";
	
	
	public AccountDAOImpl(DataSource ds) {
		this.jdbcTemplate = new JdbcTemplate(ds);
		this.ds = ds;
	}
	
	public void createAccount(AccountVO vo) throws SQLException {
		
	    this.jdbcTemplate.update(INSERT, vo.getName(), vo.getBalance(), vo.getRegidate());		
	}
	
    public int getBalance(String name){
    	
    	Object[] args = {name};
    	
    	AccountVO vo = this.jdbcTemplate.queryForObject(SELECT_BALANCE, args, new AccountRowMapper());
        
        int result = vo.getBalance();
    	
        return result;
    }

    public void minus(String name, int money) throws SQLException{
    	
        this.jdbcTemplate.update(UPDATE_MINUS, name, money, name);
        
    }
    
    public void plus(String name, int money) throws SQLException{

    	// 예외 발생시키기
    	if(true){
			throw new RuntimeException("런타임 에러");
   	    }
    	
        this.jdbcTemplate.update(UPDATE_PLUS, name, money, name);
    }
	
}
