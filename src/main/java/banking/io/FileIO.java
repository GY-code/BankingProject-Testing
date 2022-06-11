/**
 * @title FileIO.java
 *
 * @version 1.0
 *
 * @date 2020-10-24 07:40:28 
 * 
 * @author Yi Gu,SSE of BJTU
 * 
 */

package banking.io;

import java.io.*;
import java.util.*;

import banking.domain.*;

/**
 * @author Yi Gu,SSE of BJTU
 *
 * @title FileIO
 *
 * @description operate the IO of the file
 *
 */
public class FileIO {
	public static void inputFile() {
		Bank bank = Bank.getBank();
		Customer customer;
		int i = 0;// for loop to get customer

		try {
			// read information from the file
			BufferedReader in = new BufferedReader(new FileReader("account.txt"));
			// Read line in sequence from the file
			String s;
			while ((s = in.readLine()) != null) {
				String[] split = s.split(" |\t");
				// remove the empty string
				List<String> list = new ArrayList<String>();
				for (int j = 0; j < split.length; j++) {
					if (split[j] != null && split[j].length() != 0) {
						list.add(split[j]);
					}
				}
				split = list.toArray(new String[0]);

				// skip the head line
				if (split[0].charAt(0) == '#') {
					continue;
				}
				// add the customer use first name and last name
				bank.addCustomer(split[1], split[0]);
				customer = bank.getCustomer(i);
				i++;
				// create savings account
				if (!split[2].equals("null")) {
					String[] savings = split[2].split("/");
					for (String value : savings) {
						customer.addAccount(new SavingsAccount(Double.parseDouble(value), 0.05));
					}

				}
				// create checking account
				if (!split[3].equals("null")) {
					String[] checking = split[3].split("/");
					String[] protection = split[4].split("/");
					for (int j = 0; j < checking.length; j++) {
						customer.addAccount(new CheckingAccount(Double.parseDouble(checking[j]),
								Double.parseDouble(protection[j])));
					}

				}
			}

			// Close the buffered reader
			in.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			System.out.println("无账户文件输入！");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void outputFile() {
		Bank bank = Bank.getBank();
		Customer customer;
		// Call the sortCustomers method before generating the report
		bank.sortCustomer();

		try {
			// create the file writer and print writer
			FileWriter fw = new FileWriter("account.txt", false);
			PrintWriter out = new PrintWriter(fw);
			// Generate a report
			out.println("#LastName\tFirstName\tSavingsAccount\tCheckingAccount\toverdraftProtection");

			// iterate over the customer's iterator
			Iterator<Customer> i = bank.getCustomers();

			while (i.hasNext()) {

				customer = i.next();

				out.print(customer.getLastName() + "\t" + customer.getFirstName() + "\t");

				// iterate the account's iterator
				Iterator<Account> j = customer.getAccounts();
				String savings = "";
				String checking = "";
				String protection = "";
				while (j.hasNext()) {

					Account account = j.next();

					// Determine the account type
					/***
					 * Step 1: Use the instance of operator to test what type of account we have and
					 * set account_type to an appropriate value, such as "Savings Account" or
					 * "Checking Account".
					 ***/
					if (account instanceof SavingsAccount) {
						savings += account.getBalance() + "/";
					}
					if (account instanceof CheckingAccount) {
						checking += account.getBalance() + "/";
						protection += ((CheckingAccount) account).getOverdraftProtection() + "/";
					}

				}
				if (savings.equals("")) {
					savings = "null";
				}
				if (checking.equals("")) {
					checking = "null";
				}
				out.println(savings + "\t" + checking + "\t" + protection);
			}
			out.close();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
