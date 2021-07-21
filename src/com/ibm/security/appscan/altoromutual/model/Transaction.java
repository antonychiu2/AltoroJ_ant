/**
 * This sample program is provided AS IS and may be used, executed, copied and modified without royalty payment by customer (a) for its own
 * instruction and study, (b) in order to develop applications designed to run with an IBM WebSphere product, either for customer's own internal use
 * or for redistribution by customer, as part of such an application, in customer's own products.
 * 
 * Product 5724-V21,  (C) COPYRIGHT International Business Machines Corp., 2008
 * All Rights Reserved * Licensed Materials - Property of IBM
 */
package com.ibm.security.appscan.altoromutual.model;

import java.util.Date;

/**
 * This class models a transactions
 * @author Alexei
 *
 */
public class Transaction {

	private int transactionId;
	private long accountId;
	private String transactionType;
	private double amount;
	private Date date;
	
	public Transaction(int transactionId, long accountId, Date date, String transactionType, double amount) {
		this.accountId = accountId;
		this.amount = amount;
		this.transactionId = transactionId;
		this.transactionType = transactionType;
		this.date = date;
	}

	public int getTransactionId() {
		return transactionId;
	}

	public long getAccountId() {
		return accountId;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public double getAmount() {
		return amount;
	}

	public Date getDate() {
		return date;
	}
}
