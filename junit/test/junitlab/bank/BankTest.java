package junitlab.bank;

import junitlab.bank.impl.FirstNationalBank;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BankTest {
    FirstNationalBank bank;

    @Before
    public void setUp() {
        bank = new FirstNationalBank();
    }

    @Test
    public void testOpenAccount() throws AccountNotExistsException {
        String account = bank.openAccount();
        Assert.assertEquals(0,bank.getBalance(account));
    }

    @Test
    public void testUniqueAccount() {
        String account1 = bank.openAccount();
        String account2 = bank.openAccount();
        Assert.assertNotEquals(account1,account2);
    }

    @Test(expected=AccountNotExistsException.class)
    public void testInvalidAccount() throws AccountNotExistsException {
        bank.getBalance("XDDDD");
    }

    @Test
    public void testDeposit() throws AccountNotExistsException {
        String acc = bank.openAccount();
        bank.deposit(acc,2000);
        Assert.assertEquals(2000,bank.getBalance(acc));
    }
}
