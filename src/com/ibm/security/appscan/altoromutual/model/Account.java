/**
 * This sample program is provided AS IS and may be used, executed, copied and modified without royalty payment by customer (a) for its own
 * instruction and study, (b) in order to develop applications designed to run with an IBM WebSphere product, either for customer's own internal use
 * or for redistribution by customer, as part of such an application, in customer's own products.
 * 
 * Product 5724-V21,  (C) COPYRIGHT International Business Machines Corp., 2008
 * All Rights Reserved * Licensed Materials - Property of IBM
 */
package com.ibm.security.appscan.altoromutual.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import org.apache.commons.codec.binary.Base64;

import com.ibm.security.appscan.altoromutual.util.DBUtil;

/**
 * This class models user's account
 * @author Alexei
 */
public class Account {
	private long accountId = -1;
	private String accountName = null;
	private double balance = -1;
	
	public static Account getAccount(String accountNo) throws SQLException {
		if (accountNo == null || accountNo.trim().length() == 0)
			return null;

		long account = Long.parseLong(accountNo);

		return getAccount(account);
	}
	
	public static Account getAccount(long account) throws SQLException {
		return DBUtil.getAccount(account);
	}
	
	public Account(long accountId, String accountName, double balance) {
		this.accountId = accountId;
		this.accountName = accountName;
		this.balance = balance;
	}
	
	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(long balance) {
		this.balance = balance;
	}

	public String getAccountName() {
		return accountName;
	}
	
	public static Account[] fromBase64List (String b64accounts){
		String accounts = new String(Base64.decodeBase64(b64accounts)); 
		
		StringTokenizer outerTokens = new StringTokenizer(accounts, "|");
		ArrayList<Account> accountList = new ArrayList<Account>();
		
		while (outerTokens.hasMoreTokens()){
			StringTokenizer tokens = new StringTokenizer(outerTokens.nextToken(), "~");
			
			long acctId = -1;
			String acctName = null;
			double amt = Double.MAX_VALUE;
			if (tokens.hasMoreTokens())
				acctId = Long.valueOf(tokens.nextToken());
			
			if (tokens.hasMoreTokens())
				acctName = tokens.nextToken();
			
			if (tokens.hasMoreTokens())
				amt = Double.valueOf(tokens.nextToken());
			
			if (acctId > -1 && acctName != null && amt != Double.MAX_VALUE){
				accountList.add(new Account(acctId, acctName, amt));
			}
		}
		
		return (accountList.toArray(new Account[accountList.size()]));
	}
	
	public static String toBase64List(Account[] accounts){
	
		StringBuffer accountList = new StringBuffer();
		
		for (Account account:accounts){
			accountList.append(account.getAccountId());
			accountList.append("~");
			accountList.append(account.getAccountName());
			accountList.append("~");
			accountList.append(account.getBalance());
			accountList.append("|");
		}
		
		return Base64.encodeBase64String(accountList.toString().getBytes());
		
	}
}
