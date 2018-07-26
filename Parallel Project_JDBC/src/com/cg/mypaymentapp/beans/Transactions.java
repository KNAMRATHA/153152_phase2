package com.cg.mypaymentapp.beans;

import java.math.BigDecimal;
import java.sql.Date;

public class Transactions 
{
	String mobileNumber;
	String transactionType;
	BigDecimal amount;
	String transactionDate;
	String transactionStatus;
	public Transactions() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public String getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	public Transactions(String mobileNumber, String transactionType, BigDecimal amount, String transactionDate,
			String transactionStatus) {
		super();
		this.mobileNumber = mobileNumber;
		this.transactionType = transactionType;
		this.amount = amount;
		this.transactionDate = transactionDate;
		this.transactionStatus = transactionStatus;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mobileNumber == null) ? 0 : mobileNumber.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transactions other = (Transactions) obj;
		if (mobileNumber == null) {
			if (other.mobileNumber != null)
				return false;
		} else if (!mobileNumber.equals(other.mobileNumber))
			return false;
		return true;
	}
	
	public String getTransactionStatus() {
		return transactionStatus;
	}
	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}
	@Override
	public String toString() {
		return "Transactions [mobileNumber=" + mobileNumber + ", transactionType=" + transactionType + ", amount="
				+ amount + "]";
	}
	
	

}
