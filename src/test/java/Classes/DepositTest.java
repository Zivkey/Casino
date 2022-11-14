package Classes;

import Exceptions.DepositException;
import Exceptions.WithdrawException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DepositTest {

    @Test
    void setIdNormal() throws DepositException {
        Deposit deposit = new Deposit();
        deposit.setId(2);
    }

    @Test
    void setIdZero() {
        Deposit deposit = new Deposit();
        Assertions.assertThrows(DepositException.class, () -> {
            deposit.setId(0);
        });
    }

    @Test
    void setIdException() {
        Deposit deposit = new Deposit();
        Assertions.assertThrows(DepositException.class, () -> {
            deposit.setId(-2);
        });
    }

    @Test
    void setAmountNormal() throws DepositException {
        Deposit deposit = new Deposit();
        deposit.setAmount(100);
    }

    @Test
    void setAmountZero() throws DepositException {
        Deposit deposit = new Deposit();
        deposit.setAmount(0);
    }

    @Test
    void setAmountException() {
        Deposit deposit = new Deposit();
        Assertions.assertThrows(DepositException.class, () -> {
            deposit.setAmount(-100);
        });
    }
}