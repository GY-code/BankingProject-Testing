/**
 * @title Bank.java
 *
 * @version 1.0
 *
 * @date 2020-09-22 11:25:05 
 * 
 * @author Yi Gu,SSE of BJTU
 * 
 */

package banking.domain;

import java.util.*;

/**
 * @author Yi Gu,SSE of BJTU
 *
 * @title Bank
 *
 * @description use arrays to implement the multiplicity on the association
 *              between a bank and its customers
 *
 */

public class Bank {
	private static Bank bankInstance = new Bank();
	// Modify the declaration for the customers attribute to be of type List.
	private List<Customer> customer;

	private Bank() {
		// initialize the customers attribute to be a new ArrayList object.
		customer = new ArrayList<Customer>();
	}

	public void addCustomer(String f, String l) {
		// add a customer to the ArrayList
		customer.add(new Customer(f, l));
	}

	public void delCustomer(int index) {
		// add a customer to the ArrayList
		customer.remove(index);
	}

	public int getNumOfCustomers() {
		// return the size of ArrayList
		return customer.size();
	}

	public Customer getCustomer(int index) {
		// get the customer
		return customer.get(index);
	}

	public Iterator<Customer> getCustomers() {
		// returns an Iterator on the customers list
		return customer.iterator();
	}

	public static Bank getBank() {
		return bankInstance;
	}

	public void sortCustomer() {
		// sort the ArrayList of Customer
		Collections.sort(customer);
	}

}
