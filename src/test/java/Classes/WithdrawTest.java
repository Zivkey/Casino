package Classes;

import Exceptions.WithdrawException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WithdrawTest {

    @Test
    void setIdNormal() throws WithdrawException {
        Withdraw withdraw = new Withdraw();
        withdraw.setId(2);
    }

    @Test
    void setIdZero() {
        Withdraw withdraw = new Withdraw();
        Assertions.assertThrows(WithdrawException.class, () -> {
            withdraw.setId(0);
        });
    }

    @Test
    void setIdException() {
        Withdraw withdraw = new Withdraw();
        Assertions.assertThrows(WithdrawException.class, () -> {
            withdraw.setId(-2);
        });
    }

    @Test
    void setAmountNormal() throws WithdrawException {
        Withdraw withdraw = new Withdraw();
        withdraw.setAmount(100);
    }

    @Test
    void setAmountZero() throws WithdrawException {
        Withdraw withdraw = new Withdraw();
        withdraw.setAmount(0);
    }

    @Test
    void setAmountException() {
        Withdraw withdraw = new Withdraw();
        Assertions.assertThrows(WithdrawException.class, () -> {
            withdraw.setAmount(-100);
        });
    }
}