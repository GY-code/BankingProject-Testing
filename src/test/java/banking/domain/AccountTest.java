package banking.domain; 

import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import static junit.framework.TestCase.assertEquals;


/**
* Account Tester. 
* 
* @author Gu Yi
* @since 6ÔÂ 11, 2022
* @version 1.0 
*/ 
public class AccountTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: getBalance() 
* 
*/ 
@Test
public void testGetBalance() throws Exception { 
    assertEquals(100,new Account(100).getBalance(),0.0001);
    assertEquals(0,new Account(0).getBalance(),0.0001);
}

/** 
* 
* Method: setBalance(double balance) 
* 
*/ 
@Test
public void testSetBalance() throws Exception { 
    Account account=new Account(0);
    assertEquals(0,account.getBalance(),0.0001);
    account.setBalance(500);
    assertEquals(500,account.getBalance(),0.0001);
    account.setBalance(0);
    assertEquals(0,account.getBalance(),0.0001);
}

/** 
* 
* Method: deposit(double amt) 
* 
*/ 
@Test
public void testDeposit() throws Exception {
    Account account=new Account(50);
    account.deposit(200);
    assertEquals(250,account.getBalance(),0.0001);
} 

/** 
* 
* Method: withdraw(double amt) 
* 
*/ 
@Test
public void testWithdraw1() throws Exception {
    Account account=new Account(500);
    account.withdraw(100);
    assertEquals(400,account.getBalance(),0.0001);
} 
@Test(expected = OverdraftException.class)
public void testWithdraw2() throws Exception {
    Account account=new Account(50);
    account.withdraw(2000);
}


} 
