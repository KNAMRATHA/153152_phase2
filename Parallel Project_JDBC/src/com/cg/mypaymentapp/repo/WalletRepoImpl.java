package com.cg.mypaymentapp.repo;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cg.mypaymentapp.beans.Customer;
import com.cg.mypaymentapp.beans.Transactions;
import com.cg.mypaymentapp.beans.Wallet;
import com.cg.mypaymentapp.exception.InvalidInputException;
import com.cg.mypaymentapp.util.DBUtil;

public class WalletRepoImpl implements WalletRepo{

	private Map<String, Customer> data ; 
	List<Transactions> trans=new ArrayList<Transactions>();
	public WalletRepoImpl(Map<String, Customer> data) {
		super();
		this.data = data;
	}
	public WalletRepoImpl()
	{
		data = new HashMap<String, Customer>();
	}

	public boolean save(Customer customer) {
		
		try(Connection con =DBUtil.getConnection())
		{
			//generate id
			PreparedStatement pstm=con.prepareStatement("insert into customer values(?,?,?)");
			pstm.setString(1, customer.getMobileNo());
			pstm.setString(2, customer.getName());
			pstm.setBigDecimal(3, customer.getWallet().getBalance());
			pstm.execute();
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
			return true;
		
	}

	public Customer findOne(String mobileNo) {
		Customer cust=null;
		try(Connection con =DBUtil.getConnection())
		{
			PreparedStatement pstm=con.prepareStatement("select * from customer where customer.mobileNo=?");
			pstm.setString(1, mobileNo);
			ResultSet rs=pstm.executeQuery();
			if(rs.next()!=false)
			{
				
				cust=new Customer();
				cust.setMobileNo(rs.getString(1));
				cust.setName(rs.getString(2));
				cust.setWallet(new Wallet((rs.getBigDecimal(3))));
			}
			else return null;
			
		}
		catch(Exception e) {
			throw new InvalidInputException(e.getMessage());
		}
			return cust;
		
	}
	public Customer updateBalance(Customer customer,Transactions transaction) 
	{
		BigDecimal balance=customer.getWallet().getBalance();
		String mobileNo=customer.getMobileNo();
		try(Connection con=DBUtil.getConnection()) 
		{
			Statement stmt= con.createStatement();
			String str="update customer set balance='"+balance+"' where mobileno='"+mobileNo+"'";
			stmt.executeUpdate(str);
			
			PreparedStatement pstm=con.prepareStatement("insert into transactions values(?,?,?,?,?)");
			pstm.setString(1, customer.getMobileNo());
			pstm.setString(2, transaction.getTransactionType());
			pstm.setBigDecimal(3, transaction.getAmount());
			pstm.setString(4,transaction.getTransactionDate());
			pstm.setString(5, transaction.getTransactionStatus());
			
			try {
				pstm.execute();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		} 
		catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return customer;
		
	}
	@Override
	public List<Transactions> recentTransactions(String mobileNumber) {
		
		try(Connection con=DBUtil.getConnection()) 
		{
			PreparedStatement pstm=con.prepareStatement("select * from transactions where mobileNo=?");
			pstm.setString(1, mobileNumber);
			ResultSet res=pstm.executeQuery();
			if(res.next()==false) {
				throw new InvalidInputException();
			}
		
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return trans;
	}
	
}
