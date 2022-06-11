/**
 * @title CheckingAccount.java
 *
 * @version 1.0
 *
 * @date 2020-09-25 05:12:53 
 * 
 * @author Yi Gu,SSE of BJTU
 * 
 */

package banking.domain;

/**
 * @author Yi Gu,SSE of BJTU
 *
 * @title CheckingAccount
 *
 * @description CheckingAccount
 *
 */
public class CheckingAccount extends Account {
	private double overdraftProtection;

	public CheckingAccount(double balance) {
		super(balance);
		// TODO Auto-generated constructor stub
	}

	public CheckingAccount(double balance, double protect) {
		super(balance);
		// TODO Auto-generated constructor stub
		overdraftProtection = protect;
	}

	public void withdraw(double amt) throws OverdraftException {
		// removes the amount parameter from the current balance and judges the
		// following situations
		if (amt <= balance) {
			balance -= amt;
		} else if (overdraftProtection == 0) {
			throw new OverdraftException("No overdraft protection", amt - balance);
		} else if (amt <= (overdraftProtection + balance)) {
			overdraftProtection -= (amt - balance);
			balance = 0;
		} else {
			throw new OverdraftException("Insufficient funds for overdraft protection", amt - balance);
		}
	}

	/**
	 * @return the overdraftProtection
	 */
	public double getOverdraftProtection() {
		return overdraftProtection;
	}

	/**
	 * @param overdraftProtection the overdraftProtection to set
	 */
	public void setOverdraftProtection(double overdraftProtection) {
		this.overdraftProtection = overdraftProtection;
	}

}
