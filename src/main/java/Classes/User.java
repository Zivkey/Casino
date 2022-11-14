package Classes;

import Exceptions.UserException;

/**
 * Klasa user koja sadrzi unique id, svoje ime sifru i trenutno stanje
 * Ona sluzi za login na aplikaciju i da prati koliko novca posedije svaki igrac
 * u ovom momentu
 */
public class User {
    private int id;
    private String username;
    private String password;
    private int balance;

    public User() {
    }

    public User(int id, String username, String password, int balance) throws UserException {
        this.id = id;
        this.username = username;
        this.password = password;
        setBalance(balance);
    }

    public User(String username, String password, int balance) throws UserException {
        this.username = username;
        this.password = password;
        setBalance(balance);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) throws UserException {
        if (balance < 0) {
            throw new UserException("Balance must be above 0!");
        } else {
            this.balance = balance;
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", balance=" + balance +
                '}';
    }
}
