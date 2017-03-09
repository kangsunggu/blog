package test;

import junit.framework.TestCase;

public class AccountTest extends TestCase {

	Account account;
	
	protected void setUp() throws Exception {
		account = new Account(100000);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testAccount() {
		//fail("Not yet implemented");
	}

	public void testGetBalance() {
		assertEquals(100000, account.getBalance());
	}

	public void testWithdraw() {
		account.withdraw(10000);
		assertEquals(90000, account.getBalance());
	}

	public void testDeposit() {
		account.deposit(10000);
		assertEquals(110000, account.getBalance());
	}

}
