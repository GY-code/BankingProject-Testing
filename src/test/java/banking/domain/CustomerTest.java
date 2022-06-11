package banking.domain;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertEquals;

/**
 * Customer Tester.
 *
 * @author Gu Yi
 * @version 1.0
 * @since 6ÔÂ 11, 2022
 */
public class CustomerTest {

    private CheckingAccount checkingAccount1;
    private CheckingAccount checkingAccount2;
    private SavingsAccount savingsAccount;
    private Customer customer1;
    private Customer customer2;
    private Customer customer3;
    private List<Account> account;
    private Customer customer4;

    @Before
    public void before() throws Exception {
        checkingAccount1 = new CheckingAccount(500);
        checkingAccount2 = new CheckingAccount(500, 100);
        savingsAccount = new SavingsAccount(300, 0.02);
        customer1 = new Customer("Yi", "Gu");
        customer2 = new Customer("", "");
        customer3 = new Customer("######", "######");
        customer4 = new Customer("Yi", "Gu");
        customer1.addAccount(checkingAccount1);
        customer1.addAccount(checkingAccount2);
        customer1.addAccount(savingsAccount);
        account = new ArrayList<Account>();
        account.add(checkingAccount1);
        account.add(checkingAccount2);
        account.add(savingsAccount);
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: getFirstName()
     */
    @Test
    public void testGetFirstName() throws Exception {
        assertEquals("Yi", customer1.getFirstName());
        assertEquals("", customer2.getFirstName());
        assertEquals("######", customer3.getFirstName());
    }

    /**
     * Method: getLastName()
     */
    @Test
    public void testGetLastName() throws Exception {
        assertEquals("Gu", customer1.getLastName());
        assertEquals("", customer2.getLastName());
        assertEquals("######", customer3.getLastName());
    }

    /**
     * Method: addAccount(Account acct)
     */
    @Test
    public void testAddAccount() throws Exception {
        assertEquals(account, customer1.account);
    }

    /**
     * Method: delAccount(int index)
     */
    @Test
    public void testDelAccount() throws Exception {
        customer1.delAccount(0);
        account.remove(0);
        assertEquals(account, customer1.account);
    }

    /**
     * Method: getNumOfAccounts()
     */
    @Test
    public void testGetNumOfAccounts() throws Exception {
        assertEquals(3, customer1.getNumOfAccounts());
    }

    /**
     * Method: getAccount(int index)
     */
    @Test
    public void testGetAccount() throws Exception {
        assertEquals(account.get(0), customer1.getAccount(0));
        assertEquals(account.get(1), customer1.getAccount(1));
        assertEquals(account.get(2), customer1.getAccount(2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetAccount2() throws Exception {
        customer1.getAccount(3);
        customer1.getAccount(-1);
    }

    /**
     * Method: getAccounts()
     */
    @Test
    public void testGetAccounts() throws Exception {
        Iterator<Account> iterator = customer1.getAccounts();
        assertNotNull(iterator);
    }

    /**
     * Method: compareTo(Customer c)
     */
    @Test
    public void testCompareTo() throws Exception {
        assertTrue(customer1.compareTo(customer2)>0);
        assertTrue(customer2.compareTo(customer1)<0);
        assertEquals(0,customer4.compareTo(customer1));
    }


} 
