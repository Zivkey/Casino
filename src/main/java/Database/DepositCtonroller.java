package Database;

import Classes.Deposit;
import Classes.Withdraw;
import Exceptions.DepositException;
import Exceptions.WithdrawException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepositCtonroller {
    public static Connection connection;

    /**
     * Metoda koja pravi novi depozit kada user uplati novac u aplikaciju
     * @param deposit
     */
    public static void createDeposit(Deposit deposit) {
        try {
            connection = DBConnector.openConnection();
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO deposit" +
                    "(user_id, amount) VALUES (?, ?)");
            stmt.setInt(1, deposit.getId());
            stmt.setInt(2, deposit.getAmount());
            stmt.execute();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteDeposit(Deposit deposit) {
        try {
            connection = DBConnector.openConnection();
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM deposit WHERE user_id = ?");
            stmt.setInt(1, deposit.getId());
            stmt.execute();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateDeposit(Deposit deposit) {
        try {
            connection = DBConnector.openConnection();
            PreparedStatement stmt = connection.prepareStatement("UPDATE withdraw SET" +
                    "amount = ? WHERE user_id = ?");
            stmt.setInt(1, deposit.getAmount());
            stmt.setInt(2, deposit.getId());
            stmt.execute();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Deposit> readDeposits() {
        List<Deposit> listOfDeposits = new ArrayList<>();
        try {
            connection = DBConnector.openConnection();
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM deposit");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Deposit deposit = new Deposit();
                deposit.setId(rs.getInt("user_id"));
                deposit.setAmount(rs.getInt("amount"));
                listOfDeposits.add(deposit);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (DepositException e) {
            throw new RuntimeException(e);
        }
        return listOfDeposits;
    }
}
