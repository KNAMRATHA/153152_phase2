package com.cg.mypaymentapp.repo;

import java.util.List;

import com.cg.mypaymentapp.beans.Customer;
import com.cg.mypaymentapp.beans.Transactions;

public interface WalletRepo {

public boolean save(Customer customer);
	
	public Customer findOne(String mobileNo);
	
	public Customer updateBalance(Customer customer,Transactions transactionType); 
	
	public List<Transactions> recentTransactions(String mobileNumber);
}
