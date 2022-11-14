package Database;

import Classes.Rating;
import org.w3c.dom.views.DocumentView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RatingController {

    public static Connection connection;

    /**
     * Metoda koja dodaje novi rating u bazu podataka kada ga user napravi
     * @param rating
     */
    public static void createRating(Rating rating) {
        try {
            connection = DBConnector.openConnection();
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO rating" +
                    "(user_id, user_name, rating, message) VALUES (?, ?, ?, ?)");
            stmt.setInt(1, rating.getId());
            stmt.setString(2, rating.getUserName());
            stmt.setInt(3, rating.getRating());
            stmt.setString(4, rating.getMessage());
            stmt.execute();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metoda koja updejtuje rajting za aplikaciju ako user koji je vec napravio rating nesto promeni
     * @param rating
     */
    public static void updateRating(Rating rating) {
        try {
            connection = DBConnector.openConnection();
            PreparedStatement stmt = connection.prepareStatement("UPDATE rating set rating = ?, " +
                    "message = ? WHERE user_id = ?");
            stmt.setInt(1, rating.getRating());
            stmt.setString(2, rating.getMessage());
            stmt.setInt(3, rating.getId());
            stmt.execute();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void deleteRating(Rating rating) {
        try {
            connection = DBConnector.openConnection();
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM rating WHERE id = ?");
            stmt.setInt(1, rating.getId());
            stmt.execute();
            connection.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metoda koja cita sve rejtinge iz baze podataka i vraca ih kao listu
     * @return lista svih rejtinga
     */
    public static List<Rating> readRatings() {
        List<Rating> ratingList = new ArrayList<>();
        try {
            connection = DBConnector.openConnection();
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM rating");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Rating rating = new Rating();
                rating.setId(rs.getInt("user_id"));
                rating.setUserName(rs.getString("user_name"));
                rating.setRating(rs.getInt("rating"));
                rating.setMessage(rs.getString("message"));
                ratingList.add(rating);
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ratingList;
    }
}
