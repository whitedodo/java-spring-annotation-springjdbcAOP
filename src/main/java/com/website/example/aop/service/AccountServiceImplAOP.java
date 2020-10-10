/*
 * 	주제(Subject): 자바 환경설정 기반의 Spring Framework 4.2.4(Spring JDBC) 트랜젝션 AOP 구현
 *  파일명(Filename): AccountServiceImplAOP.java
 *  저자(Author): Dodo / rabbit.white at daum dot net
 *  생성일자(Create date): 2020-10-10
 *  설명(Description):
 * 
 */

package com.website.example.aop.service;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.website.example.dao.AccountDAO;
import com.website.example.dao.AccountDAOImpl;
import com.website.example.vo.AccountVO;

@Repository
@Transactional
public class AccountServiceImplAOP implements AccountServiceAOP{

	private AccountDAO accountDAO;
	private DataSource ds = null;
	
	public AccountServiceImplAOP(DataSource ds) {
		this.accountDAO = new AccountDAOImpl(ds);
		this.ds = ds;
	}

	@Override
	@Transactional(propagation=Propagation.NEVER)
	public void accountCreate(AccountVO vo) throws SQLException {
		accountDAO.createAccount(vo);	
		System.out.println("create CurrentTransactionName: " + TransactionSynchronizationManager.getCurrentTransactionName());
	}
	
	@Override
	@Transactional
	public void accountTransfer(String sender, String receiver, int money) throws SQLException {
		
    	int balance = accountDAO.getBalance(sender); // 보내는 사람 잔액 체크
    	
        if(balance >= money){ // 보내는 돈이 잔액보다 많으면
    	
        	System.out.println("transfer CurrentTransactionName: " + TransactionSynchronizationManager.getCurrentTransactionName() );
        	
			accountDAO.minus(sender, money);
			accountDAO.plus(receiver, money);
			
        } else{

        	System.out.println("돈 없음");
        	//throw new NoMoneyException();
        }
		
	}


}
