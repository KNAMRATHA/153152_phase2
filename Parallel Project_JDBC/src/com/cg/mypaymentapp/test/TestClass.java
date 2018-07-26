package com.cg.mypaymentapp.test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.cg.mypaymentapp.beans.Customer;
import com.cg.mypaymentapp.beans.Wallet;
import com.cg.mypaymentapp.exception.InsufficientBalanceException;
import com.cg.mypaymentapp.exception.InvalidInputException;
import com.cg.mypaymentapp.service.WalletService;
import com.cg.mypaymentapp.service.WalletServiceImpl;


public class TestClass 
{

	
	WalletService service;
	
	@Before
	public void initData()
	{
		 Map<String,Customer> data= new HashMap<String, Customer>();
		 Customer cust1=new Customer("Amit", "9900112212",new Wallet(new BigDecimal(9000)));
		 Customer cust2=new Customer("Ajay", "9963242422",new Wallet(new BigDecimal(6000)));
		 Customer cust3=new Customer("Yogini", "9922950519",new Wallet(new BigDecimal(7000)));
				
		 data.put("9900112212", cust1);
		 data.put("9963242422", cust2);	
		 data.put("9922950519", cust3);	
			service= new WalletServiceImpl(data);
			service = new WalletServiceImpl();
			
			
	}
	@Test(expected = NullPointerException.class)
	public void TestcreateAccount()
	{
		service = new WalletServiceImpl();
		service.createAccount(null,null, null);
	}

	@Test
	public void Test1()
	{
		// Map<String,Customer> data= new HashMap<String, Customer>();
		 Customer cust1=new Customer("Amit", "9900112212",new Wallet(new BigDecimal(11000)));
		 //data.put("9900112212", cust1);
		service = new WalletServiceImpl();
			assertEquals(cust1.getName(), "Amit");
	}
	@Test
	public void Test2()
	{
		 Map<String,Customer> data= new HashMap<String, Customer>();
		 Customer cust1=new Customer("Amit", "9900112212",new Wallet(new BigDecimal(12000)));
		 data.put("9900112212", cust1);
		service = new WalletServiceImpl(data);
			assertEquals(cust1.getMobileNo(), "9900112212");
	}
	@Test
	public void Test3()
	{
		 Map<String,Customer> data= new HashMap<String, Customer>();
		 Customer cust1=new Customer("Amit", "9900112212",new Wallet(new BigDecimal(11000)));
		 data.put("9900112212", cust1);
		service = new WalletServiceImpl(data);
		Wallet w1=cust1.getWallet();
			assertEquals(new BigDecimal(11000),w1.getBalance());
	}
	@Test
	public void Test4()
	{
		 Map<String,Customer> data= new HashMap<String, Customer>();
		 Customer cust1=new Customer(null, "9900112212",new Wallet(new BigDecimal(11000)));
			assertNull(cust1.getName());
	}
	@Test
	public void Test5()
	{
		 Map<String,Customer> data= new HashMap<String, Customer>();
		 Customer cust1=new Customer("Amit", "9900112212",new Wallet(new BigDecimal(11000)));
		assertNotNull(cust1.getName());
	}
	@Test
	public void Test6()
	{
		 Map<String,Customer> data= new HashMap<String, Customer>();
		 Customer cust1=new Customer("Amit", null,new Wallet(new BigDecimal(11000)));
			assertNull(cust1.getMobileNo());
	}
	@Test
	public void Test7()
	{
		 Map<String,Customer> data= new HashMap<String, Customer>();
		 Customer cust1=new Customer("Amit", "9900112212",new Wallet(new BigDecimal(11000)));
		assertNotNull(cust1.getMobileNo());
	}
	@Test
	public void Test8()
	{
		 Map<String,Customer> data= new HashMap<String, Customer>();
		 Customer cust1=new Customer("Amit", "9900112212",new Wallet(null));
			assertNull(cust1.getWallet().getBalance());
	}
	@Test
	public void Test9()
	{
		 Map<String,Customer> data= new HashMap<String, Customer>();
		 Customer cust1=new Customer("Amit", "9900112212",new Wallet(new BigDecimal(11000)));
		assertNotNull(cust1.getWallet().getBalance());
	}
	@Test
	public void Test10()
	{
		 Map<String,Customer> data= new HashMap<String, Customer>();
		 Customer cust1=new Customer("Amit", "9900112212",new Wallet(null));
	}
	@Test
	public void Test11()
	{
		 Map<String,Customer> data= new HashMap<String, Customer>();
		 Customer cust1=new Customer(null, "9900112212",new Wallet(new BigDecimal(11000)));

	}
	
	@Test
	public void Test12()
	{
		 Map<String,Customer> data= new HashMap<String, Customer>();
		 Customer cust1=new Customer("Amit", "9900112212",new Wallet(new BigDecimal(11000)));
		 data.put("9900112212", cust1);
			service = new WalletServiceImpl(data);
				assertNotSame(cust1.getWallet().getBalance(), new BigDecimal(2000));

	}
	@Test
	public void Test13()
	{
		 Map<String,Customer> data= new HashMap<String, Customer>();
		 Customer cust1=new Customer("Amit", "9900112212",new Wallet(new BigDecimal(11000)));
		 data.put("9900112212", cust1);
			service = new WalletServiceImpl(data);
				assertNotSame(cust1.getName(), "Ajay");

	}
	@Test
	public void Test14()
	{
		service = new WalletServiceImpl();
		 service.createAccount("Yogini", "9922950519",(new BigDecimal(7000)));
		 Customer cust=service.showBalance("9922950519");
		 assertEquals(cust.getWallet().getBalance(),new BigDecimal(7000));
		 
	
	}
	@Test
	public void Test15()
	{
		// Map<String,Customer> data= new HashMap<String, Customer>();
		 Customer cust3=new Customer("Yogini", "9922950519",new Wallet(new BigDecimal(7000)));
		  service = new WalletServiceImpl();
		 Customer cust=service.showBalance(cust3.getMobileNo());
		 assertNotEquals(cust.getWallet().getBalance(),new BigDecimal(11000));
		 
	
	}
	@Test
	public void Test16()
	{
		service = new WalletServiceImpl();
		 service.createAccount("Yogini", "9922950519",(new BigDecimal(7000)));;
		 Customer cust=service.showBalance("9922950519");
		 assertEquals(cust.getName(),"Yogini");
		 
	
	}
	@Test
	public void Test17()
	{
		service = new WalletServiceImpl();
		 service.createAccount("Yogini", "9922950519",(new BigDecimal(7000)));
		 Customer cust=service.showBalance("9922950519");
		 assertNotEquals(cust.getName(),"Rajini");
		 
	}
	@Test
	public void Test18()
	{
		service = new WalletServiceImpl();
		 service.createAccount("Yogini", "9922950519",(new BigDecimal(7000)));
		 Customer cust=service.depositAmount("9922950519",new BigDecimal(3000));
		 assertEquals(cust.getWallet().getBalance(),new BigDecimal(10000));
		 
	
	}

	@Test
	public void Test19()
	{
		
		 service = new WalletServiceImpl();
		 service.createAccount("Yogini", "9922950519",(new BigDecimal(7000)));
		 Customer cust=service.withdrawAmount("9922950519",new BigDecimal(3000));
		assertEquals(cust.getWallet().getBalance(),new BigDecimal(4000));

	
	}

//	
	@Test(expected=InvalidInputException.class)
	public void Test20()
	{
		service = new WalletServiceImpl();
		 Customer cust3=service.createAccount("Yogini", "9922950519",(new BigDecimal(7000)));
		 Customer cust2=service.createAccount("Ajay", "9963242422",(new BigDecimal(6000)));
		 Customer cust=service.fundTransfer("9922950519","9922950519",new BigDecimal(1000));
		 
	
	}
	@Test(expected=Exception.class)
	public void Test21()
	{
		service = new WalletServiceImpl();
		 Customer cust3=service.createAccount("Yogini", "9922950519",(new BigDecimal(7000)));
		 Customer cust2=service.createAccount("Ajay", "9963242422",(new BigDecimal(6000)));
		 service.fundTransfer(cust3.getMobileNo(),cust2.getMobileNo(),new BigDecimal(10000));
		 		 
	
	}
}
