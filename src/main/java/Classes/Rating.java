package Classes;

/**
 * Klasa rating koja se dodaje u bazu podataka i sadrzi ko je dao rejting
 * ocenu koju je dao kao i poruku koju je napisao
 */
public class Rating {
    private int id;
    private String userName;
    private int rating;
    private String message;

    public Rating() {
    }

    public Rating(int id, String userName, int rating, String message) {
        this.id = id;
        this.userName = userName;
        this.rating = rating;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "rating{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", rating=" + rating +
                ", message='" + message + '\'' +
                '}';
    }
}
