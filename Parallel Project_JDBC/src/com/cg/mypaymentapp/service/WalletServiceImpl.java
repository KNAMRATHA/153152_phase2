
package com.cg.mypaymentapp.service;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.cg.mypaymentapp.beans.Customer;
import com.cg.mypaymentapp.beans.Transactions;
import com.cg.mypaymentapp.beans.Wallet;
import com.cg.mypaymentapp.exception.InsufficientBalanceException;
import com.cg.mypaymentapp.exception.InvalidInputException;
import com.cg.mypaymentapp.repo.WalletRepo;
import com.cg.mypaymentapp.repo.WalletRepoImpl;



public class WalletServiceImpl implements WalletService
{
	

	Customer customer=new Customer();
	Transactions trans=new Transactions();
private WalletRepo repo=new WalletRepoImpl();
WalletRepo repo1=new WalletRepoImpl();
Map<String, Customer> data=new HashMap<>();
	public WalletServiceImpl(Map<String, Customer> data)
	{
		repo= new WalletRepoImpl(data);
	}
	public WalletServiceImpl(WalletRepo repo) 
	{
		this.repo=repo;
	}

	public WalletServiceImpl() {
		repo1=new WalletRepoImpl();
		Customer customer=new Customer();
	}
	@Override
	public Customer createAccount(String name, String mobileno, BigDecimal amount)
	{
		
		if(name==null || mobileno==null || amount==null)
		throw new NullPointerException();
		if(isPhoneNumbervalid(mobileno)&&isAmountValid(amount) &&isNameValid(name))
		
		customer.setName(name);
		customer.setMobileNo(mobileno);
		customer.setWallet(new Wallet(amount));
		
		boolean b=repo.save(customer);
		
		if(b)
			{
			repo.updateBalance(customer, new Transactions(customer.getMobileNo(),"CREATE ACCOUNT",customer.getWallet().getBalance(),new Date().toString(),"Success"));
			return customer;
			}
		else
			throw new InvalidInputException("Account is already existing");	
	
		
	}	
	@Override
	public Customer showBalance(String mobileno) 
	{
		customer=repo.findOne(mobileno);
		if(customer==null )
		throw new InvalidInputException("Invalid mobile no ");
		else	
		return customer;
		
	}
	@Override
	public Customer fundTransfer(String sourceMobileNo, String targetMobileNo, BigDecimal amount)
	{
	Customer customer=null;
	Customer customer1=null;
	if(sourceMobileNo.equals(targetMobileNo))
	{
		throw new InvalidInputException("Source and target mobile numbers are equal");
	}
	if(isPhoneNumbervalid(sourceMobileNo)&&isPhoneNumbervalid(targetMobileNo))
	{
	customer=repo.findOne(sourceMobileNo);
	customer1=repo.findOne(targetMobileNo);
	
	BigDecimal balance1;
	BigDecimal balance2;
	
    balance1=customer.getWallet().getBalance();
	balance2=customer1.getWallet().getBalance();
	if(isAmountValid(amount))
		if(( balance1).compareTo(amount)>=0) {
	balance1=balance1.subtract(amount);
	customer.setWallet(new Wallet(balance1));
	repo.updateBalance(customer, new Transactions(customer.getMobileNo(),"FUND TRANSFERRED",customer.getWallet().getBalance(),new Date().toString(),"Success"));

	
	
	balance2=balance2.add(amount);	
	customer1.setWallet(new Wallet(balance2));
	repo.updateBalance(customer1, new Transactions(customer1.getMobileNo(),"FUND RECEIVED",customer1.getWallet().getBalance(),new Date().toString(),"Success"));
		}
		


	else
	System.out.println("Insufficient balance");
	}
	else throw new InvalidInputException("Invalid mobile number");
	return customer ;
}

public Customer depositAmount(String mobileNo, BigDecimal amount) {
	
	if(isPhoneNumbervalid(mobileNo))
	{
		customer=repo.findOne(mobileNo);
	
	
	BigDecimal dbalance;
	dbalance=customer.getWallet().getBalance();
	if(isAmountValid(amount))
	dbalance=dbalance.add(amount);
	customer.setWallet(new Wallet(dbalance));
	repo.updateBalance(customer, new Transactions(customer.getMobileNo(),"AMOUNT DEPOSITED",customer.getWallet().getBalance(),new Date().toString(),"Success"));
	
	return customer ;
	}
	else
		throw new InvalidInputException("Invalid mobile number");
}

public Customer withdrawAmount(String mobileNo, BigDecimal amount) 
{
	
	if(isPhoneNumbervalid(mobileNo))
	{
	customer=repo.findOne(mobileNo);
	BigDecimal wbalance;
	wbalance=customer.getWallet().getBalance();
	if(isAmountValid(amount))
	wbalance=wbalance.subtract(amount);
	customer.setWallet(new Wallet(wbalance));
	repo.updateBalance(customer, new Transactions(customer.getMobileNo(),"AMOUNT WITHDRAWN",customer.getWallet().getBalance(),new Date().toString(),"Success"));
	
	return customer ;
	}
	else
		throw new InvalidInputException("Invalid mobile number");
   } 
public List<Transactions> recentTransactions(String mobileNumber) 
{
	if(!isPhoneNumbervalid(mobileNumber))
	throw new InvalidInputException("Invalid mobile number");
	
	else
	{
		List<Transactions> trans=repo.recentTransactions(mobileNumber);
		if(trans!=null)
			return trans;
		else
			throw new InvalidInputException("Invalid input");
	}
	
	
}


		public boolean isPhoneNumbervalid( String phoneNumber )
		{
			if(phoneNumber.matches("[1-9][0-9]{9}")) 
			{
				return true;
			}		
			else 
				return false;
		}
		public boolean isNameValid(String name)
		{
			if(name.matches("^[a-zA-Z]{1,30}$"))
			{
				return true;
			}
			else
				return false;
		}
		public boolean isAmountValid(BigDecimal amount)
		{
			int val=amount.compareTo(new BigDecimal("0"));
			if(val==0|| val<0)
				return false;
			return true;
		}
		}
