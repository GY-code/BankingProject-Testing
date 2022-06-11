package banking.io;

/**
 * @title CustomerDisplay.java
 *
 * @version 1.0
 *
 * @date 2020-09-29 04:53:09 
 * 
 * @author Yi Gu,SSE of BJTU
 * 
 */
import java.util.*;
/**
 * @author Yi Gu,SSE of BJTU
 *
 * @title CustomerReport
 *
 * @description complete the function of showing report
 *
 */

import java.text.NumberFormat;
import banking.domain.*;

public class CustomerDisplay {
	public static String generateReport() {
		NumberFormat currency_format = NumberFormat.getCurrencyInstance();
		Bank bank = Bank.getBank();
		Customer customer;
		String output = "\tAll Customer Account Information\n";
		// Call the sortCustomers method before generating the report
		bank.sortCustomer();

		// iterate over the customer's iterator
		Iterator<Customer> i = bank.getCustomers();

		while (i.hasNext()) {

			customer = i.next();

			output += "\n";
			output += "   Customer: " + customer.getFirstName() + ", " + customer.getLastName() + "\n";

			// iterate the account's iterator
			Iterator<Account> j = customer.getAccounts();

			while (j.hasNext()) {

				Account account = j.next();
				String account_type = "";
				boolean displayProtection = false;
				double protection = 0;

				// Determine the account type
				/***
				 * Step 1: Use the instanceof operator to test what type of account we have and
				 * set account_type to an appropriate value, such as "Savings Account" or
				 * "Checking Account".
				 ***/
				if (account instanceof SavingsAccount) {
					account_type = "Savings Account";
				}
				if (account instanceof CheckingAccount) {
					account_type = "Checking Account";
					displayProtection = true;
					protection = ((CheckingAccount) account).getOverdraftProtection();
				}

				// Print the current balance of the account
				/***
				 * Step 2: Print out the type of account and the balance. Feel free to use the
				 * currency_format formatter to generate a "currency string" for the balance.
				 ***/
				output += "   " + account_type + ":current balance is" + currency_format.format(account.getBalance());
				if (displayProtection) {
					output += "  Overdraft Protection is" + currency_format.format(protection);
				}
				output += "\n";
			}
		}
		return output;
	}
}
