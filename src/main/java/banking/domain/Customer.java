/**
 * @title Customer.java
 *
 * @version 1.0
 *
 * @date 2020-09-15 05:41:33 
 * 
 * @author Yi Gu,SSE of BJTU
 * 
 */

package banking.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Yi Gu,SSE of BJTU
 *
 * @title Customer
 *
 * @description The account part is aggregated, and there are operations related
 *              to the name reading of customer
 * 
 * 
 *
 */

public class Customer implements Comparable<Customer> {
	private String firstName;
	private String lastName;
	protected List<Account> account;

	/**
	 * Modify the Customer class to use an ArrayList to implement the multiplicty on
	 * the accounts association
	 */

	/**
	 * 
	 * @author Yi Gu,SSE of BJTU
	 *
	 * @description constructor: attribute the value to firstName,lastName,account
	 * 
	 * @param f-String
	 * 
	 * @param l-String
	 * 
	 */
	public Customer(String f, String l) {
		firstName = f;
		lastName = l;
		account = new ArrayList<>();
	}

	/**
	 * 
	 * @author Yi Gu,SSE of BJTU
	 *
	 * @description get the firstName
	 * 
	 * @return firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * 
	 * @author Yi Gu,SSE of BJTU
	 *
	 * @description get the lastName
	 * 
	 * @return lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * 
	 * @author Yi Gu,SSE of BJTU
	 *
	 * @description using "add" to add an account
	 * 
	 * @param acct-Account
	 * 
	 */
	public void addAccount(Account acct) {
		account.add(acct);
	}

	/**
	 * 
	 * @author Yi Gu,SSE of BJTU
	 *
	 * @description using "remove" to delete an account
	 * 
	 * @param acct-Account
	 * 
	 */
	public void delAccount(int index) {
		account.remove(index);
	}

	/**
	 * 
	 * @author Yi Gu,SSE of BJTU
	 *
	 * @description get Number Of Accounts, use "size"to return the length of the
	 *              ArrayList
	 * 
	 * 
	 * @return account.size()
	 */
	public int getNumOfAccounts() {
		return account.size();
	}

	/**
	 * @description return the specific account
	 *
	 * @param index-int
	 * @return account.get(index)
	 */
	public Account getAccount(int index) {
		return account.get(index);
	}

	/**
	 * @description returns an Iterator on the accounts list.
	 *
	 * @return account.iterator()
	 */
	public Iterator<Account> getAccounts() {
		return account.iterator();
	}

	/**
	 * @description compare the two customers in a lexigraphical order with the last
	 *              name taking precedence over the first name
	 *
	 * @param c-Customer
	 * @return 0,1,-1
	 */
	@Override
	public int compareTo(Customer c) {
		// TODO Auto-generated method stub
		if (!this.lastName.equals(c.lastName)) {
			// compare the last name and output the result
			return this.lastName.compareTo(c.lastName);
		} else {
			// compare the first name and output the result
			return this.firstName.compareTo(c.firstName);
		}
	}

}
