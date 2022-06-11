/**
 * @title Account.java
 *
 * @version 1.0
 *
 * @date 2020-09-15 09:29:10 
 * 
 * @author Yi Gu,SSE of BJTU
 * 
 */

package banking.domain;

/**
 * @author Yi Gu,SSE of BJTU
 *
 * @title Account
 *
 * @description modify the withdraw method to return a boolean value to specify
 *              if the transaction was successful
 *
 */
public class Account {
	protected double balance;
	// hold the current (or "running") balance of the bank account

	public Account(double init_balance) {
		// populates the balance attribute
		balance = init_balance;

	}

	public double getBalance() {
		// retrieves the current balance
		return balance;
	}

	/**
	 * @param balance the balance to set
	 */
	public void setBalance(double balance) {
		this.balance = balance;
	}

	public boolean deposit(double amt) {
		// adds the amount parameter to the current balance
		balance += amt;
		return true;
	}

	// Rewrite the withdraw method so that it does not return a value
	public void withdraw(double amt) throws OverdraftException {
		// removes the amount parameter from the current balance
		if (amt <= balance) {
			balance -= amt;
		} else {
			throw new OverdraftException("Insufficient funds", amt - balance);
		}

	}

}
