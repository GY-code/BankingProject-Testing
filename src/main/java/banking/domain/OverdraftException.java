/**
 * @title OverdraftException.java
 *
 * @version 1.0
 *
 * @date 2020-10-03 11:09:29 
 * 
 * @author Yi Gu,SSE of BJTU
 * 
 */

package banking.domain;

/**
 * @author Yi Gu,SSE of BJTU
 *
 * @title OverdraftException
 *
 * @description withdraw method in the Account class throw an OverdraftException
 *
 */
public class OverdraftException extends Exception {
	private double deficit;

	// overdraft exception with a message
	public OverdraftException(String message, double deficit) {
		super(message);
		this.deficit = deficit;
	}

	public double getDeficit() {
		return deficit;
	}
}
