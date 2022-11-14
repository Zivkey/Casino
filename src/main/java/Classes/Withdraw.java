package Classes;

import Exceptions.WithdrawException;

/**
 * Objekat withdraw koji se skladisti u bazu i pravi se kada igrac podigne
 * pare sa svog zamisljenog racuna
 */
public class Withdraw {
    private int id;
    private int amount;

    public Withdraw() {
    }

    public Withdraw(int id, int amount) throws WithdrawException {
        setId(id);
        setAmount(amount);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) throws WithdrawException {
        if (id > 0) {
            this.id = id;
        }
        else {
            throw new WithdrawException("Id must be bigger than 0");
        }
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) throws WithdrawException {
        if (amount >= 0) {
            this.amount = amount;
        } else {
            throw new WithdrawException("Amount must be bigger than 0");
        }
    }

    @Override
    public String toString() {
        return "Withdraw{" +
                "id=" + id +
                ", amount=" + amount +
                '}';
    }
}
