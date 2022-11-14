package Database;

import Classes.CurrentUser;
import Classes.User;
import Exceptions.UserException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserController {
    public static Connection connection;

    /**
     * Metoda koja dodaje novog usera u bazu pri registraciji u aplikaciju
     * @param user
     */
    public static void addUser(User user) {
        try {
            connection = DBConnector.openConnection();
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO users" +
                    "(user_name, password, balance) VALUES (?, ?, ?)");
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setInt(3, user.getBalance());
            stmt.execute();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Metoda koja sluzi da se live updejtuje balance igraca dok igra
     * @param id koristi id da bi znala kog usera da updejtuje
     * @param newBalance updejtuje mu balance
     */
    public static void updateBalance(int id, int newBalance) {
        try {
            connection = DBConnector.openConnection();
            PreparedStatement stmt = connection.prepareStatement("UPDATE users SET balance = ? WHERE id = ?");
            stmt.setInt(1, newBalance);
            stmt.setInt(2, id);
            stmt.execute();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Dodaj pare na nalog od igraca
     * @param id koristi id da bi znala kog usera da updejtuje
     * @param addMoney dodaje mu novac dat u konstruktoru
     */
    public static void addMoney(int id, int addMoney) {
        try {
            connection = DBConnector.openConnection();
            int currentMoney = CurrentUser.getCurrentUserBalance();
            int newbalance = currentMoney + addMoney;
            PreparedStatement stmt = connection.prepareStatement("UPDATE users SET balance = ? WHERE id = ?");
            stmt.setInt(1, newbalance);
            stmt.setInt(2, id);
            stmt.execute();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Skloni novac sa naloga od igraca
     * @param id
     * @param removeMoney
     */

    public static void removeMoney(int id, int removeMoney) {
        try {
            connection = DBConnector.openConnection();
            int currentMoney = CurrentUser.getCurrentUserBalance();
            int newbalance = currentMoney - removeMoney;
            PreparedStatement stmt = connection.prepareStatement("UPDATE users SET balance = ? WHERE id = ?");
            stmt.setInt(1, newbalance);
            stmt.setInt(2, id);
            stmt.execute();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Metoda koja cita sve usere sa baze podataka
     * @return lista svih usera u bazi podataka
     */
    public static List<User> readUsers() {
        List<User> listOfUsers = new ArrayList<>();
        try {
            connection = DBConnector.openConnection();
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM users");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setBalance(rs.getInt("balance"));
                user.setPassword(rs.getString("password"));
                user.setUsername(rs.getString("user_name"));
                listOfUsers.add(user);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (UserException e) {
            throw new RuntimeException(e);
        }
        return listOfUsers;
    }

    public static void deleteUser(User user) {
        try {
            connection = DBConnector.openConnection();
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM user WHERE id = ?");
            stmt.setInt(1, user.getId());
            stmt.execute();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
