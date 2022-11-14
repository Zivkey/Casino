package Database;

import Classes.User;
import Classes.Withdraw;
import Exceptions.UserException;
import Exceptions.WithdrawException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WithdrawController {
    public static Connection connection;

    /**
     * <Metpda koja dodaje u bazu podataka novi Withdraw koji igrac napravi
     * @param withdraw
     */
    public static void createWithdraw(Withdraw withdraw) {
        try {
            connection = DBConnector.openConnection();
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO withdraw" +
                    "(user_id, amount) VALUES (?, ?)");
            stmt.setInt(1, withdraw.getId());
            stmt.setInt(2, withdraw.getAmount());
            stmt.execute();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteWithdraw(Withdraw withdraw) {
        try {
            connection = DBConnector.openConnection();
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM withdraw WHERE user_id = ?");
            stmt.setInt(1, withdraw.getId());
            stmt.execute();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void updateWithdraw(Withdraw withdraw) {
        try {
            connection = DBConnector.openConnection();
            PreparedStatement stmt = connection.prepareStatement("UPDATE withdraw SET" +
                    "amount = ? WHERE user_id = ?");
            stmt.setInt(1, withdraw.getAmount());
            stmt.setInt(2, withdraw.getId());
            stmt.execute();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Withdraw> readWithdraws() {
        List<Withdraw> listOfWithdraws = new ArrayList<>();
        try {
            connection = DBConnector.openConnection();
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM withdraw");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Withdraw withdraw = new Withdraw();
                withdraw.setId(rs.getInt("user_id"));
                withdraw.setAmount(rs.getInt("amount"));
                listOfWithdraws.add(withdraw);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (WithdrawException e) {
            throw new RuntimeException(e);
        }
        return listOfWithdraws;
    }

}
