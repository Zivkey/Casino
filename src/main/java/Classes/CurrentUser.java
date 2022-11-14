package Classes;


/**
 * Klasa Current user se setuje pri loginu i sluzi da aplikacija
 * prati i zna ko je trenutni ulogovani igrac na njoj
 */
public class CurrentUser {
    public static int currentId;
    public static String currentUserName;
    public static int currentUserBalance;

    /**
     * Pri loginu se setuje current user
     * @param user
     */
    public static void setCurrentUser(User user) {
        currentId = user.getId();
        currentUserName = user.getUsername();
        currentUserBalance = user.getBalance();
    }

    public static int getCurrentUsedId() {
        return currentId;
    }

    public static String getCurrentUserName() {
        return currentUserName;
    }

    public static int getCurrentUserBalance() {
        return currentUserBalance;
    }

    /**
     * Pri igranju se menja i novac koji trenutni igrac poseduje ovom metodom
     * @param NewBalance
     */
    public static void changeUserBalance(int NewBalance) {
        currentUserBalance = NewBalance;
    }

}
