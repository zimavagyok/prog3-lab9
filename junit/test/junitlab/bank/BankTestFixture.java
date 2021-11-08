package junitlab.bank;

import junitlab.bank.impl.FirstNationalBank;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BankTestFixture {
    FirstNationalBank bank;
    String bank1,bank2;

    @Before
    public void setUp() throws AccountNotExistsException {
        bank = new FirstNationalBank();
        bank1 = bank.openAccount();
        bank2=bank.openAccount();
        bank.deposit(bank1,1500);
        bank.deposit(bank2,12000);
    }

    @Test
    public void testTransfer() throws NotEnoughFundsException, AccountNotExistsException {
        bank.transfer(bank2,bank1,3456);
        Assert.assertEquals(4956,bank.getBalance(bank1));
        Assert.assertEquals(8544,bank.getBalance(bank2));
    }

    @Test(expected=NotEnoughFundsException.class)
    public void testTransferWithoutEnoughFunds() throws NotEnoughFundsException, AccountNotExistsException {
        bank.transfer(bank1,bank2,3456);
    }
}
