package Classes;

import Exceptions.DepositException;

/**
 * Klasa depozit koja se dodaje u bazu i sadrzi ko je napravio uplatu i kolika je velicina te uplate u dinarima
 */
public class Deposit {
    private int id;
    private int amount;

    public Deposit() {
    }

    public Deposit(int id, int amount) throws DepositException{
        setId(id);
        setAmount(amount);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) throws DepositException {
        if (id > 0) {
            this.id = id;
        }
        else {
            throw new DepositException("Id must be bigger than 0");
        }
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) throws DepositException {
        if (amount >= 0) {
            this.amount = amount;
        } else {
            throw new DepositException("Amount must be bigger than 0");
        }
    }

    @Override
    public String toString() {
        return "Deposit{" +
                "id=" + id +
                ", amount=" + amount +
                '}';
    }
}
