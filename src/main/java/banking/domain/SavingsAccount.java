/**
 * @title SavingAccount.java
 *
 * @version 1.0
 *
 * @date 2020-09-25 05:03:30 
 * 
 * @author Yi Gu,SSE of BJTU
 * 
 */

package banking.domain;

/**
 * @author Yi Gu,SSE of BJTU
 *
 * @title SavingAccount
 *
 * @description SavingAccount
 *
 */
public class SavingsAccount extends Account {
	private double interestRate;

	public SavingsAccount(double balance, double interest_rate) {
		super(balance);
		// TODO Auto-generated constructor stub
		interestRate = interest_rate;
	}

}
